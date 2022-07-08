package com.fmk.framework.web;

//import ;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.util.FieldInfo;
import com.fmk.framework.annotations.*;
import com.fmk.framework.basic.*;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.exception.ApplicationException;
import com.fmk.framework.foundation.base.ClassHelper;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.restful.PageParamGitHub;
import com.google.common.base.Splitter;
import javassist.*;
import javassist.bytecode.AnnotationsAttribute;
import javassist.bytecode.ClassFile;
import javassist.bytecode.ConstPool;
import javassist.bytecode.ParameterAnnotationsAttribute;
import javassist.bytecode.annotation.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.web.bind.annotation.*;
import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

import java.lang.reflect.Type;
import java.security.ProtectionDomain;
import java.util.*;

public class ControllerWriter{
    private static final Logger logger = Logger.getLogger(ControllerWriter.class);
    private static ClassPool CLASS_POOL = null;
    private static final Kv HTTP_MAPPING_NAME = Kv
        .inst("POST", PostMapping.class.getName())
        .kv("GET", GetMapping.class.getName())
        .kv("DELETE", DeleteMapping.class.getName())
        .kv("PUT", PutMapping.class.getName());
    private static List<Class<?>> CLASSES = new ArrayList<>();
    private static Set<String> SentinelIds = new HashSet<>();
    static {
        CLASS_POOL = ClassPool.getDefault();
        ClassClassPath classClassPath = new ClassClassPath(ControllerWriter.class);
        CLASS_POOL.insertClassPath(classClassPath);
    }

    public static void printSentnelConfig(){
        List<Object> result = new ArrayList<>();
        SentinelIds.forEach(id->{
            HashMap<String, Object> item = new HashMap<>();
            item.put("resource", id);
            item.put("controlBehavior", 0);
            item.put("count", 20000);
            item.put("grade", 1);
            item.put("limitApp", "default");
            item.put("strategy", 0);
            result.add(item);
        });
        logger.info("==================================sentnel config 流量控制规则 sentinelFlowRules");
        logger.info(JSON.toJSONString(result));
        result.clear();
        SentinelIds.forEach(id->{
            HashMap<String, Object> item = new HashMap<>();
            item.put("resource", id);
            item.put("count", 20000);
            item.put("grade", 0);
            item.put("timeWindow", 10);
            item.put("limitApp", "default");
            result.add(item);
        });
        logger.info("==================================sentnel config 熔断降级规则 sentinelDegradeRules");
        logger.info(JSON.toJSONString(result));
    }

    @Deprecated
    public static void scan(String ... prefixs){
        scanAndWrite(prefixs);
    }
    public static void scanAndWrite(String ... prefixs){
        scanAndWrite(false, prefixs);
    }
    public static void scanAndWrite(boolean biz, String ... prefixs){
        for(int i=0; i<prefixs.length; i++) {
            String prefix = prefixs[i];
            String fmkPrefix = Consts.SYSTEM_PKG_COM_FMK_PREFIX + ".summer."+prefix;
            String compPrefix = Consts.SYSTEM_PKG_COM_COMP_PREFIX + ".summer."+prefix;

            List<Class<?>> list = write(biz, biz, fmkPrefix, compPrefix);
            CLASSES.addAll(list);
        }
    }
//    public static void scanAndWriteBiz(String ... prefixs){
//        for(int i=0; i<prefixs.length; i++) {
//            String prefix = prefixs[i];
//            String compPrefix = Consts.SYSTEM_PKG_COM_COMP_BIZ_PREFIX + ".summer."+prefix;
//
//            List<Class<?>> list = write(true, true, compPrefix);
//            CLASSES.addAll(list);
//        }
//    }
    public static void register(ApplicationContext applicationContext){
        CLASSES.forEach(kls -> addBean(kls, applicationContext));
    }

    private static void addBean(Class<?> kls, ApplicationContext app){
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(kls);
        registerBean(kls.getName(), beanDefinitionBuilder.getRawBeanDefinition(), app);
    }
    private static void registerBean(String beanName, BeanDefinition beanDefinition, ApplicationContext context) {
        ConfigurableApplicationContext configurableApplicationContext = (ConfigurableApplicationContext) context;
        BeanDefinitionRegistry beanDefinitonRegistry = (BeanDefinitionRegistry) configurableApplicationContext
            .getBeanFactory();
        beanDefinitonRegistry.registerBeanDefinition(beanName, beanDefinition);
    }
    public static List<Class<?>> write(boolean biz, boolean onlyPublish, String... prefixs){
        ArrayList<Class<?>> classes = new ArrayList<>();
        if(null == prefixs) { return classes; }
        for(int i=0; i<prefixs.length; i++){
            String prefix = prefixs[i];
            String prefixDot = prefix+".";
            ClassHelper.scanPackageClass(prefix, classMeta -> {
                String className = classMeta.getClassName();
                if(!className.startsWith(prefixDot)){ return; }
                if(className.contains(".summer.local.")){ return; }
                Class<?> aClass = ReflectUtil.getClass(className);
                if(!Summer.class.isAssignableFrom(aClass)){ return; }
                if(LocalSummer.class.isAssignableFrom(aClass)){ return; }
                if(null != aClass.getAnnotation(Deprecated.class)){ return; }
                if(null != aClass.getAnnotation(Publish.class)) {
                    classes.add(writeController((Class<? extends Summer>) aClass, true, biz));
                }
                if(onlyPublish){ return; }
                if(!aClass.getName().startsWith(Consts.SYSTEM_PKG_COM_COMP_BIZ_PREFIX)){
                    classes.add(writeController((Class<? extends Summer>) aClass, false, biz));
                }
            });
        }
        return classes;
    }

    private static Class<?> writeController(Class<? extends Summer> summerClass, boolean publish, boolean biz) {
        try {
            return writeController_(summerClass, publish, biz);
        } catch (CannotCompileException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private static Class<?> writeController_(
        Class<? extends Summer> summerClass, boolean publish, boolean biz
    ) throws CannotCompileException {
        Summer2HttpInfo httpInfo = Summer2UrlHelper.summerClass2Http(summerClass);
        FieldInfo summerResultType = ReflectUtil.getFieldInfo(summerClass, "summerResult", true);
        Type resultType = summerResultType.fieldClass;
        String returnType = resultType.getTypeName();
        if(java.lang.Void.class.equals(resultType)){
            returnType = "void";
        }else{
            returnType = "Object";
        }
        List<String> prefixPart = Splitter.on(".")
            .trimResults()
            .omitEmptyStrings()
            .splitToList(summerClass.getName());

//        String pkgPrefix0 = prefixPart.get(0)+"."+prefixPart.get(1);

//        String pkgPrefix = pkgPrefix0+".summer";
        String part = ".summer.";
        int i = summerClass.getName().indexOf(part);
        if(i<0){
            throw new ApplicationException("类路径格式不正确 class: "+summerClass.getName());
        }
        String pkgPrefix = summerClass.getName().substring(0, i);

        String uname = summerClass.getName().substring(pkgPrefix.length()+part.length());
        String ctlClassName = pkgPrefix+".controller." + uname + (publish?"":"Feign") +"Controller";

        CtClass ctClass = CLASS_POOL.makeClass(ctlClassName);
        addAnnoRestController(ctClass);

        StringBuilder builder = new StringBuilder();
        String methodName = StringUtil.firstCharLower(summerClass.getSimpleName());
        String varName = httpInfo.getVarName();
        Class<?> parameterClass = summerClass;
        ProxySuperSummer proxySuperSummer = summerClass.getAnnotation(ProxySuperSummer.class);
        if(null != proxySuperSummer){
            parameterClass = summerClass.getSuperclass();
        }
        if(StringUtils.isNotBlank(varName)) {
            FieldInfo fieldInfo = ReflectUtil.getFieldInfo(summerClass, varName, true);
            String typeName = fieldInfo.fieldType.getTypeName();
            builder.append("public " + returnType + " " + methodName + "(" + parameterClass.getName() + " model, "+typeName+" "+varName+"){\n");
            builder.append("if(null == model){ model = new "+parameterClass.getName()+"(); }\n");
            if(PageParamGitHub.class.isAssignableFrom(parameterClass)){
                builder.append("model.pagesize2startlimit();\n");
            }
            builder.append("model.set"+ StringUtil.firstCharUpper(varName)+"("+varName+");\n");
        }else{
            builder.append("public " + returnType + " " + methodName + "(" + parameterClass.getName() + " model){\n");
            builder.append("if(null == model){ model = new "+parameterClass.getName()+"(); }\n");
            if(PageParamGitHub.class.isAssignableFrom(parameterClass)){
                builder.append("model.pagesize2startlimit();\n");
            }
        }
        builder.append("com.fmk.framework.validation.Precondition.validation(model);\n");
        builder.append("return model.sum();\n");
        builder.append("}");

        CtMethod method = CtMethod.make(builder.toString(), ctClass);

        String urlPrefix = biz?("/api-"+httpInfo.getModuleName()):"";
        addAnnoMethod(summerClass, ctClass, method, httpInfo, uname, publish, urlPrefix);
        ctClass.addMethod(method);

        ClassLoader classLoader = ControllerWriter.class.getClassLoader();
        ProtectionDomain protectionDomain = ControllerWriter.class.getProtectionDomain();
        return ctClass.toClass(classLoader, protectionDomain);
    }
    private static void addAnnoRestController(CtClass cc){
        ClassFile classFile = cc.getClassFile();
        ConstPool constPool = classFile.getConstPool();
        // 添加类注解
        AnnotationsAttribute bodyAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);
        Annotation bodyAnnot = new Annotation(RestController.class.getName(), constPool);
        bodyAttr.addAnnotation(bodyAnnot);

        classFile.addAttribute(bodyAttr);
    }
    private static void addAnnoMethod(
        Class<?> summerKls, CtClass cc, CtMethod ctMethod, Summer2HttpInfo httpInfo, String uname, boolean publish,
        String urlPrefix
    ){
        ClassFile classFile = cc.getClassFile();
        ConstPool constPool = classFile.getConstPool();

        AnnotationsAttribute methodAttr = new AnnotationsAttribute(constPool, AnnotationsAttribute.visibleTag);

        if(publish) {
            Annotation sentineAnnot = new Annotation("com.alibaba.csp.sentinel.annotation.SentinelResource", constPool);
            sentineAnnot.addMemberValue("value", new StringMemberValue(uname, constPool));
            methodAttr.addAnnotation(sentineAnnot);
            SentinelIds.add(uname);
        }
        if(null != summerKls.getAnnotation(SkipUserAuth.class)){
            Annotation sentineAnnot = new Annotation(SkipUserAuth.class.getName(), constPool);
            methodAttr.addAnnotation(sentineAnnot);
        }
        if(null != summerKls.getAnnotation(SkipResultWrap.class)){
            Annotation sentineAnnot = new Annotation(SkipResultWrap.class.getName(), constPool);
            methodAttr.addAnnotation(sentineAnnot);
        }
        PreAuthorize preAuthorize = summerKls.getAnnotation(PreAuthorize.class);
        if(null != preAuthorize){
            if(StringUtils.isNotBlank(preAuthorize.value())){
                throw new ApplicationException("PreAuthorize 注解无需指定value");
            }
            Annotation sentineAnnot = new Annotation(PreAuthorize.class.getName(), constPool);
            sentineAnnot.addMemberValue("name", new StringMemberValue(preAuthorize.name(), constPool));
            sentineAnnot.addMemberValue("value", new StringMemberValue(uname, constPool));
            methodAttr.addAnnotation(sentineAnnot);
        }
        ApiInfo apiInfo = summerKls.getAnnotation(ApiInfo.class);
        if(null != apiInfo){
            Annotation sentineAnnot = new Annotation(ApiInfo.class.getName(), constPool);
            sentineAnnot.addMemberValue("value", new StringMemberValue(apiInfo.value(), constPool));
            methodAttr.addAnnotation(sentineAnnot);
        }

        String httpMethod = HTTP_MAPPING_NAME.get(httpInfo.getMethod());
        if(StringUtils.isBlank(httpMethod)){
            throw new RuntimeException("httpMethod not found");
        }
        Annotation getMappingAnnot = new Annotation(httpMethod, constPool);
//        if(summerKls.getName().startsWith(Consts.SYSTEM_PKG_COM_COMP_BIZ_PREFIX)){
//        if(StringUtils.isNotBlank(urlPrefix)){
//            urlPrefix = "/api-"+httpInfo.getModuleName();
//        }
        StringMemberValue value = new StringMemberValue((publish?urlPrefix:"/feign")+httpInfo.getUrl(), constPool);
        ArrayMemberValue arrGetMapping = new ArrayMemberValue(constPool);
        arrGetMapping.setValue(new MemberValue[]{value});
        getMappingAnnot.addMemberValue("value", arrGetMapping);
        methodAttr.addAnnotation(getMappingAnnot);

        ctMethod.getMethodInfo().addAttribute(methodAttr);

        boolean hasVar = StringUtils.isNotBlank(httpInfo.getVarName());
        boolean skipRequestBody = (null != summerKls.getAnnotation(SkipRequestBodyAnnotation.class));
        List<Annotation[]> paramList = new ArrayList<>();

        if(!skipRequestBody) {
            Annotation[] annotations = new Annotation[1];
            Annotation requestBodyAnnot = new Annotation(RequestBody.class.getName(), constPool);
            requestBodyAnnot.addMemberValue("required", new BooleanMemberValue(false, constPool));
            annotations[0] = requestBodyAnnot;
            paramList.add(annotations);
        }

        if(hasVar) {
            Annotation[] annotations = new Annotation[1];
            Annotation pathVariableAnnot = new Annotation(PathVariable.class.getName(), constPool);
            pathVariableAnnot.addMemberValue("value", new StringMemberValue(httpInfo.getVarName(), constPool));
            annotations[0] = pathVariableAnnot;
            paramList.add(annotations);
        }

        if(paramList.size()>0) {
            ParameterAnnotationsAttribute parameterAtrribute = new ParameterAnnotationsAttribute(constPool, ParameterAnnotationsAttribute.visibleTag);
            parameterAtrribute.setAnnotations(paramList.toArray(new Annotation[][]{}));
            ctMethod.getMethodInfo().addAttribute(parameterAtrribute);
        }
    }
}
