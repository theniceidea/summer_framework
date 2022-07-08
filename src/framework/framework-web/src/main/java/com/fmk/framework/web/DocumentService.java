package com.fmk.framework.web;

import com.alibaba.fastjson.util.FieldInfo;
import com.fmk.framework.annotations.*;
import com.fmk.framework.annotations.validation.AssertEnum;
import com.fmk.framework.basic.*;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.exception.Excep;
import com.fmk.framework.foundation.base.ClassHelper;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.summer.BasicSummer;
import com.google.common.base.Splitter;
import com.google.common.net.MediaType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.*;
import org.summerframework.model.Summer;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@RestController
public class DocumentService {
    private static final Logger logger = Logger.getLogger(DocumentService.class);

    private static ArrayList<Object> root = new ArrayList<>();
    private static HashMap<String, Object> root2 = new HashMap<>();
    public static final String FIELD_FIELDS = "fields";
    public static final String FIELD_TYPE = "type";
    public static final String FIELD_REQUIRED = "required";
    public static final String FIELD_COMMENT = "comment";

    @Value("${app.debug}")
    private boolean appDebug;

    @GetMapping("/v1/cms/apidocument")
    @SkipUserAuth
    public List<Object> getDocument(){
        if(appDebug) { return root; }
        return null;
    }

    @GetMapping("/feign/v1/cms/apidocument")
    @SkipUserAuth
    public List<Object> getDocumentFeign(){
        if(appDebug) { return root; }
        return null;
    }

    @GetMapping("/v1/cms/apidocument2")
    @SkipUserAuth
    public HashMap<String, Object> getDocument2(){
        if(appDebug) { return root2; }
        return null;
    }

    @GetMapping("/feign/v1/cms/apidocument2")
    @SkipUserAuth
    public HashMap<String, Object> getDocumentFeign2(){
        if(appDebug) { return root2; }
        return null;
    }

    protected void buildDocJson(ApplicationContext applicationContext) {
        ArrayList<Object> apis = new ArrayList<>();
        root = apis;
        root2.put("apis", apis);
        root2.put("errMsgs", getErrMsgs());

        String[] names = applicationContext.getBeanNamesForAnnotation(RestController.class);
        if(null == names) { return; }

        for(int i=0; i<names.length; i++){
            String name = names[i];
            Object bean = applicationContext.getBean(name);

            Class<?> targetClass = AopUtils.getTargetClass(bean);
            if(targetClass.getName().contains("$$")) { targetClass = targetClass.getSuperclass(); }
            if (!ClassUtil.isAppClass(targetClass) || DocumentService.class.equals(targetClass)) {
                continue;
            }
            Method[] methods = targetClass.getMethods();
            if(null == methods) { return; }

            for(int j=0; j<methods.length; j++){
                Method method = methods[j];
                HashMap<String, Object> methodJson = new HashMap<>();
                if(!setUrlMethod(method, targetClass, methodJson)) { continue; }
                if(null != method.getAnnotation(Deprecated.class)) { continue; }

                apis.add(methodJson);

                ApiInfo apiInfo = method.getAnnotation(ApiInfo.class);
                if(null != apiInfo){
                    methodJson.put("apiInfo", apiInfo.value());
                }
                methodJson.put("postType", getPostType(method));
                HashSet<Type> existsTypes = new HashSet<>();
                String varName = getVarName(method);
                methodJson.put("input", getInput(method, existsTypes, varName));
                existsTypes.clear();

                Type grt = getReturnType(method);
                if((grt instanceof ParameterizedType) && grt.getTypeName().startsWith(List.class.getName())){
                    ParameterizedType parameterizedType = (ParameterizedType) grt;
                    Type[] typeArguments = parameterizedType.getActualTypeArguments();
                    Type type = typeArguments[0];
                    HashMap<String, Object> listMap = new HashMap<>();
                    listMap.put("type", grt.getTypeName());
                    listMap.put("fields", getParamTree(type, existsTypes));
                    methodJson.put("output", listMap);
                    continue;
                }
                if(!ClassUtil.isAppClass(grt.getTypeName())){
                    methodJson.put("output", grt.getTypeName());
                }else{
                    methodJson.put("output", getParamTree(grt, existsTypes));
                }
            }
        }
    }
    private String getVarName(Method method){
        Parameter[] parameters = method.getParameters();
        if(null == parameters){
            return "";
        }
        for(int i=0; i<parameters.length; i++){
            Parameter parameter = parameters[i];
            RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
            if(null != requestBody){
                Class<?> kls = (Class<?>) parameter.getType();
                if(Summer.class.isAssignableFrom(kls)) {
                    Summer2HttpInfo httpInfo = Summer2UrlHelper.summerClass2Http(kls);
                    return httpInfo.getVarName();
                }
            }
        }
        return "";
    }
    private Type getReturnType(Method method){
        Parameter[] parameters = method.getParameters();
        if(null == parameters){
            return method.getGenericReturnType();
        }
        for(int i=0; i<parameters.length; i++){
            Parameter parameter = parameters[i];
            RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
            if(null != requestBody){
                Class<?> kls = (Class<?>) parameter.getType();
                if(Summer.class.isAssignableFrom(kls)) {
                    FieldInfo summerResultType = ReflectUtil.getFieldInfo(kls, "summerResult", true);
                    return summerResultType.fieldType;
                }
            }
        }
        return method.getGenericReturnType();
    }
    private String getPostType(Method method){
        Parameter[] parameters = method.getParameters();
        if(null == parameters){
            return MediaType.FORM_DATA.toString();
        }
        for(int i=0; i<parameters.length; i++){
            Parameter parameter = parameters[i];
            try {
                RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
                if (null != requestBody) {
                    return MediaType.JSON_UTF_8.toString();
                }
            }catch (Throwable e){
                logger.error(e.getMessage(), e);
                throw e;
            }
        }
        return MediaType.FORM_DATA.toString();
    }

    private boolean setUrlMethod(Method method, Class<?> kls, HashMap<String, Object> json){
        String url = "";
        RequestMapping classRequestMapping = kls.getAnnotation(RequestMapping.class);
        if(null != classRequestMapping && null != classRequestMapping.value() && classRequestMapping.value().length>0){
            url = classRequestMapping.value()[0];
        }
        PostMapping classPostMapping = kls.getAnnotation(PostMapping.class);
        if(null != classPostMapping && null != classPostMapping.value() && classPostMapping.value().length>0){
            url = classPostMapping.value()[0];
        }
        PutMapping classPutMapping = kls.getAnnotation(PutMapping.class);
        if(null != classPutMapping && null != classPutMapping.value() && classPutMapping.value().length>0){
            url = classPutMapping.value()[0];
        }
        GetMapping classGetMapping = kls.getAnnotation(GetMapping.class);
        if(null != classGetMapping && null != classGetMapping.value() && classGetMapping.value().length>0){
            url = classGetMapping.value()[0];
        }
        DeleteMapping classDeleteMapping = kls.getAnnotation(DeleteMapping.class);
        if(null != classDeleteMapping && null != classDeleteMapping.value() && classDeleteMapping.value().length>0){
            url = classDeleteMapping.value()[0];
        }
        String methodUrl = "";
        RequestMapping methodRequestMapping = method.getAnnotation(RequestMapping.class);
        if(null != methodRequestMapping && null != methodRequestMapping.value() && methodRequestMapping.value().length>0){
            methodUrl = methodRequestMapping.value()[0];
            json.put("method", methodRequestMapping.method());
        }
        PostMapping methodPostMapping = method.getAnnotation(PostMapping.class);
        if(null != methodPostMapping && null != methodPostMapping.value() && methodPostMapping.value().length>0){
            methodUrl = methodPostMapping.value()[0];
            json.put("method", "POST");
        }
        PutMapping methodPutMapping = method.getAnnotation(PutMapping.class);
        if(null != methodPutMapping && null != methodPutMapping.value() && methodPutMapping.value().length>0){
            methodUrl = methodPutMapping.value()[0];
            json.put("method", "PUT");
        }
        GetMapping methodGetMapping = method.getAnnotation(GetMapping.class);
        if(null != methodGetMapping && null != methodGetMapping.value() && methodGetMapping.value().length>0){
            methodUrl = methodGetMapping.value()[0];
            json.put("method", "GET");
        }
        DeleteMapping methodDeleteMapping = method.getAnnotation(DeleteMapping.class);
        if(null != methodDeleteMapping && null != methodDeleteMapping.value() && methodDeleteMapping.value().length>0){
            methodUrl = methodDeleteMapping.value()[0];
            json.put("method", "DELETE");
        }

        if(methodUrl.startsWith("/")){
            methodUrl.substring(1);
        }
        if(url.endsWith("/")){
            url = url+methodUrl;
        }else{
            if(StringUtils.isBlank(url)){
                url = methodUrl;
            }else if(StringUtils.isNotBlank(methodUrl)){
                url = url+"/"+methodUrl;
            }
        }
        json.put("url", url);
        return (StringUtils.isNotBlank(url) && !url.startsWith("/feign/"));
    }
    private Object getInput(Method method, HashSet<Type> existsTypes, String varName){
        Parameter[] parameters = method.getParameters();
        if(null == parameters) { return null; }
        HashMap<String, Object> json = new HashMap<>();
        for(int i=0; i<parameters.length; i++){
            Parameter parameter = parameters[i];

            String parameterName = "";
            PathVariable pathVariable = parameter.getAnnotation(PathVariable.class);
            if(null != pathVariable){
                parameterName = pathVariable.value();
            }
            if(StringUtils.isBlank(parameterName)){
                RequestBody requestBody = parameter.getAnnotation(RequestBody.class);
                if(null != requestBody && Summer.class.isAssignableFrom(parameter.getType())){
                    parameterName = "model";
                }
            }
            if(StringUtils.isBlank(parameterName)){
                parameterName = parameter.getName();
            }

            Class<?> type = parameter.getType();
            if(!ClassUtil.isAppClass(type)){
                json.put(parameterName, type.getName());
                continue;
            }
            json.put(parameterName, getParamTree(type, existsTypes));
        }
//        Object model = json.get("model");
//        if(null != model && model instanceof HashMap){
//            HashMap<String, Object> map = (HashMap)model;
//            if(json.containsKey(varName) && map.containsKey(varName)){
//                map.remove(varName);
//                if(map.size()<=0){
//                    json.remove("model");
//                }
//            }
//        }
        return json;
    }
    private Object getParamTree(Type ptype, HashSet<Type> existsTypes){
        HashMap<String, Object> json = new HashMap<>();
        if(existsTypes.contains(ptype)) {
            return "参考:"+ptype.getTypeName();
        }
        if(ClassUtil.isAppClass(ptype.getTypeName())) {
            existsTypes.add(ptype);
        }
        Class<?> kls = null;
        if(ptype instanceof Class){
            kls = (Class<?>) ptype;
            eachFields(json, kls, null, existsTypes);
        }else if(ptype instanceof ParameterizedType){
            ParameterizedType parameterizedType = (ParameterizedType) ptype;
            eachFields(json, (Class<?>) parameterizedType.getRawType(), parameterizedType, existsTypes);
        }else{
            return json;
        }
        return json;
    }
    private HashMap<String, Type> eachTypes(ParameterizedType parameterizedType){
        HashMap<String, Type> map = new HashMap<>();
        if(null == parameterizedType) { return map; }
        Type[] arguments = parameterizedType.getActualTypeArguments();
        Class<?> rawType = (Class<?>) parameterizedType.getRawType();
        String s = rawType.toGenericString();
        s=s.substring(s.indexOf("<")+1);
        s=s.substring(0, s.length()-1);
        String[] strings = s.split(",");
        if(null == strings) { return map; }
        for(int i=0; i<strings.length; i++){
            map.put(strings[i], arguments[i]);
        }
        return map;
    }

    private void enumField(HashMap<String, Object> json, EnumField enumField){
        Class<?> enumClass = enumField.enumClass();
        Object[] enumConstants = enumClass.getEnumConstants();
        StringBuffer buffer = new StringBuffer();
        if(null != enumConstants && IEnum.class.isAssignableFrom(enumClass)){
            for(int i=0; i<enumConstants.length; i++){
                IEnum enumConstant = (IEnum)enumConstants[i];
                buffer.append(enumConstant.value());
                buffer.append(":");
                buffer.append(enumConstant.title());
                buffer.append("; ");
            }
        }
        if(StringUtils.isNotBlank(enumField.extValue())){
            buffer.append(enumField.extValue());
        }
        json.put("values", buffer.toString());
    }

    private void enumField(HashMap<String, Object> json, AssertEnum enumField){
        Class<?> enumClass = enumField.enumClass();
        Object[] enumConstants = enumClass.getEnumConstants();
        StringBuffer buffer = new StringBuffer();
        if(null != enumConstants && IEnum.class.isAssignableFrom(enumClass)){
            for(int i=0; i<enumConstants.length; i++){
                IEnum enumConstant = (IEnum)enumConstants[i];
                buffer.append(enumConstant.value());
                buffer.append(":");
                buffer.append(enumConstant.title());
                buffer.append("; ");
            }
        }
        if(StringUtils.isNotBlank(enumField.extValue())){
            buffer.append(enumField.extValue());
            if(StringUtils.isNotBlank(enumField.extDesc())){
                buffer.append(":");
                buffer.append(enumField.extDesc());
                buffer.append("; ");
            }
        }
        json.put("values", buffer.toString());
    }

    private void eachFields(HashMap<String, Object> json, Class<?> kls, ParameterizedType parameterizedType, HashSet<Type> existsTypes) {
        HashMap<String, Type> types = eachTypes(parameterizedType);
        ReflectUtil.eachFields(kls, field -> {
            SkipDoc skipDoc = field.getAnnotation(SkipDoc.class);
            if(null != skipDoc) { return; }

            Class<?> declaringClass = field.getDeclaringClass();
            if(BasicSummer.class.equals(declaringClass) || Summer.class.equals(declaringClass)|| Modifier.isStatic(field.getModifiers())){
                return;
            }

            Type type = field.getGenericType();

            HashMap<String, Object> fieldMap = new HashMap<>();
            boolean required = (null != field.getAnnotation(Required.class));
            ApiInfo apiInfo = field.getAnnotation(ApiInfo.class);
            String comment = (null != apiInfo)?apiInfo.value():"";
            json.put(field.getName(), fieldMap);
            fieldMap.put(FIELD_TYPE, type.getTypeName());
            if(required) { fieldMap.put(FIELD_REQUIRED, required); }
            if(StringUtils.isNotBlank(comment)) { fieldMap.put(FIELD_COMMENT, comment); }

            HashMap<String, String> fieldApiInfo = Validator.apiInfo(field);
            fieldApiInfo.entrySet().forEach(entry -> {
                fieldMap.put("validation-"+entry.getKey(), entry.getValue());
            });

            EnumField enumField = field.getAnnotation(EnumField.class);
            if(null != enumField){
                enumField(fieldMap, enumField);
                return;
            }

            AssertEnum assertEnum = field.getAnnotation(AssertEnum.class);
            if(null != assertEnum){
                enumField(fieldMap, assertEnum);
                return;
            }

            if(ClassUtil.isAppClass(type.getTypeName())) {
                fieldMap.put(FIELD_FIELDS, getParamTree(type, existsTypes));
                return;
            }
            if(!isList(type)){
                return;
            }
            ParameterizedType genericType = (ParameterizedType) field.getGenericType();
            Type[] args = genericType.getActualTypeArguments();
            if(null == args || args.length<=0){
                return;
            }
            Type arg = args[0];
            if(types.containsKey(arg.getTypeName())){
                arg = types.get(arg.getTypeName());
            }
            Class<?> gkls = null;
            if(arg instanceof Class){
                gkls = (Class<?>) arg;
            }else if(arg instanceof TypeVariable){
                TypeVariable tv = (TypeVariable)arg;
                gkls = (Class<?>) tv.getGenericDeclaration();
            }

            if(null == gkls){
                return;
            }

            if(ClassUtil.isAppClass(gkls)){
                fieldMap.put(FIELD_TYPE, "List<"+gkls.getTypeName()+">");
                fieldMap.put(FIELD_FIELDS, getParamTree(gkls, existsTypes));
            }
        });
    }
    private boolean isList(Type type){
        if(type instanceof Class && List.class.isAssignableFrom((Class<?>) type)){
            return true;
        }
        if(type instanceof TypeVariable){
            TypeVariable tv = (TypeVariable)type;
            GenericDeclaration declaration = tv.getGenericDeclaration();
            if(declaration instanceof Class && List.class.isAssignableFrom((Class<?>) declaration)){
                return true;
            }
        }else if(type instanceof ParameterizedType){
            ParameterizedType pt = (ParameterizedType) type;
            Type rawType = pt.getRawType();
            if(rawType instanceof Class && List.class.isAssignableFrom((Class<?>) rawType)){
                return true;
            }
        }
        return false;
    }
    public static HashMap<String, List<String>> getErrMsgs() {

        String pkg = Consts.SYSTEM_PKG_COM_COMP_PREFIX +".model";
        HashSet<String> mods = new HashSet<>();
        ClassHelper.scanPackageClass(pkg, classMeta -> {
            String className = classMeta.getClassName();
            int countMatches = StringUtils.countMatches(className, '.');
            if(countMatches!=4){
                return;
            }
            if(!className.endsWith("ErrMsgs")){
                return;
            }
            String simpleClassName = Splitter.on(".")
                .omitEmptyStrings()
                .trimResults()
                .splitToList(className)
                .get(4);
            mods.add(simpleClassName.substring(0, simpleClassName.length()-7));
        });
        HashMap<String, List<String>> results = new HashMap<>();
        mods.forEach(modName->{
            results.put(StringUtil.firstCharLower(modName), Excep.getErrMsgs(modName));
        });
        return results;
    }
}
