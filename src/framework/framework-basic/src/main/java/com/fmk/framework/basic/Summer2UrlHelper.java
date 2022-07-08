package com.fmk.framework.basic;

import com.fmk.framework.annotations.HttpMethod;
import com.fmk.framework.logger.Logger;
import com.google.common.collect.Sets;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;

public class Summer2UrlHelper {

    private static final Logger logger = Logger.getLogger(Summer2UrlHelper.class);

    private static final String LOWER_CHARS = "abcdefghijklmnopqrstuvwxyz0123456789";
    private static final HashSet<String> DEFAULT_ACTIONS = Sets.newHashSet("add", "get", "list", "delete", "update");
//    private static final HashSet<String> BY_ACTIONS = Sets.newHashSet("get", "list", "delete", "update");
//    private static final HashSet<String> NOBY_ACTIONS = Sets.newHashSet("add");
    private static final Kv HTTP_METHOD = Kv.inst("add", "POST").kv("get", "GET").kv("list", "GET").kv("delete", "DELETE").kv("update", "PUT");

    private static final HashMap<Class<?>, Summer2HttpInfo> CACHE = new HashMap<>();

    public static void printCache(){
        CACHE.forEach((aClass, httpInfo) -> {
            logger.info("{} {} {}", aClass.getName(), httpInfo.getMethod(), httpInfo.getUrl());
        });
    }

    public static Summer2HttpInfo summerClass2Http(Class<?> kls){
        Summer2HttpInfo summer2HttpInfo = CACHE.get(kls);
        if(null != summer2HttpInfo) { return summer2HttpInfo; }

        synchronized (Summer2UrlHelper.class){
            summer2HttpInfo = CACHE.get(kls);
            if(null != summer2HttpInfo) { return summer2HttpInfo; }
            summer2HttpInfo = summerClass2Http_(kls);
            CACHE.put(kls, summer2HttpInfo);
            return summer2HttpInfo;
        }
    }
    private static Summer2HttpInfo summerClass2Http_(Class<?> kls){

        String feignNamePart4Path = "";
//        String urlVersion = "/v1";

        String klsName = kls.getName();
        String simpleKlsName = kls.getSimpleName();
        String pkg = klsName.substring(0, klsName.lastIndexOf("."));
        String[] strs = pkg.split("\\.");

        String urlVersion = strs[strs.length-4];
        if(Objects.equals("summer", urlVersion)){
            urlVersion = "v1";
        }
        urlVersion = "/"+urlVersion;
        String namespaceName = strs[strs.length-3];
        String microServiceName = strs[strs.length-2];
        String serviceName = strs[strs.length-1];

        if(serviceName.indexOf("_")>=0){
            throw new RuntimeException("新规范不允许resource name有下划线");
        }
        String[] items = serviceName.split("_");
        String lowerResourceName = items[items.length-1];
        String resourceName = getResourceName(serviceName, kls);
        String convertedResourceName = convertName(resourceName);

        String action = getAction(pkg, kls.getSimpleName(), lowerResourceName);
//        String serviceName4Url = serviceName.replaceAll("_", "-");
        String serviceName4Url = convertedResourceName;

        String varName = pathVariableName(pkg, lowerResourceName, kls.getSimpleName());
        String convertedName = convertName(varName);
        String pathWithVariable = ( StringUtils.isNotBlank(varName) ? "/"+convertedName+"/{"+StringUtil.firstCharLower(varName)+"}":"");

//        String url = "${feignNamePart4Path}${urlVersion}/${namespaceName}/${serviceName4Url}${pathWithVariable}\"";

        String url = "";
        String method = HTTP_METHOD.get(action);
        if("add".equals(action)){
            url = feignNamePart4Path+urlVersion+"/"+namespaceName+"/"+serviceName4Url+pathWithVariable;
        }else if("delete".equals(action)){
            url = feignNamePart4Path+urlVersion+"/"+namespaceName+"/"+serviceName4Url+pathWithVariable;
        }else if("get".equals(action)){
            url = feignNamePart4Path+urlVersion+"/"+namespaceName+"/"+serviceName4Url+pathWithVariable;
        }else if("list".equals(action)){
            String limitName = listLimitName(lowerResourceName, simpleKlsName, action);
            if(StringUtils.isNotBlank(limitName)) {
                if(true) { throw new RuntimeException("新规范没有limit limit name"); }
                limitName = "/"+limitName;
            }
            url = feignNamePart4Path+urlVersion+"/"+namespaceName+"/"+serviceName4Url+pathWithVariable;
        }else if("update".equals(action)){
            url = feignNamePart4Path+urlVersion+"/"+namespaceName+"/"+serviceName4Url+pathWithVariable;
        }else{
            url = feignNamePart4Path+urlVersion+"/"+namespaceName+"/"+serviceName4Url+"/"+action+pathWithVariable;
            method = "PUT";
        }
        HttpMethod httpMethodAnno = kls.getAnnotation(HttpMethod.class);
        if(null != httpMethodAnno){
            if(!HTTP_METHOD.containsValue(httpMethodAnno.value().name())){
                throw new RuntimeException("不支持的httpMethod summer class:"+kls.getName()+";  目前支持:"+HTTP_METHOD.values());
            }
            method = httpMethodAnno.value().name();
        }

        Summer2HttpInfo mod = new Summer2HttpInfo();
        mod.setModuleName(microServiceName);
//        logger.info("========================================================");
//        logger.info(kls.getName());
        mod.setMethod(method);
        mod.setUrl(url);
        mod.setVarName(StringUtil.firstCharLower(varName));
//        logger.info(url+"   "+(null==method?"PUT":method));
        return mod;
    }
    private static String getResourceName(String serviceName, Class<?> kls){
        String simpleClassName = kls.getSimpleName();
        String lowerCase = simpleClassName.toLowerCase();
        int i = lowerCase.indexOf(serviceName);
        if(i<0) throw new RuntimeException("summer service 如果summer类提供给外部作为一个微服务接口,那么包名和类名必须符合url规范,请参考wiki的url规范部分, class:"+kls.getName());
        String resourceName = simpleClassName.substring(i, i + serviceName.length());
        if(StringUtils.isBlank(resourceName)){ throw new RuntimeException("summer service 如果summer类提供给外部作为一个微服务接口,那么包名和类名必须符合url规范,请参考wiki的url规范部分, class:"+kls.getName()); }
        return resourceName;
    }
    private static String listLimitName(String lowerResourceName, String className, String action){
        String upperResourceName = StringUtil.firstCharUpper(lowerResourceName);
        if("list".equals(action)){
            String upperAction = StringUtil.firstCharUpper(action);
            String prefix = upperAction+upperResourceName+"For";
            if(className.toLowerCase().startsWith(prefix.toLowerCase())){
                String varName = className.substring(prefix.length());
                return convertName(varName);
            }
        }
        return "";
    }
    private static String pathVariableName(String pkg, String lowerResourceName, String className){
        return exceptiveActionVariable(className);
//        String firstCharUpperResourceName = firstCharUpper(lowerResourceName);
//        String action = getAction(pkg, className, lowerResourceName);
//        if(BY_ACTIONS.contains(action)){
//            String upperAction = firstCharUpper(action);
//            String prefix = upperAction+firstCharUpperResourceName+"By";
//            if(className.toLowerCase().startsWith(prefix.toLowerCase())){
//                String varName = className.substring(prefix.length());
//                return varName;
//            }
//        }else if(!NOBY_ACTIONS.contains(action)){
//            String varName = exceptiveActionVariable(className);
//            return varName;
//        }
//        return "";
    }
    private static String exceptiveActionVariable(String txt){
        int i = txt.indexOf("By");
        return i<0?"":txt.substring(i+2);
    }
    private static String getAction(String pkg, String txt, String lowerResourceName){
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<txt.length(); i++){
            String chr = txt.charAt(i)+"";
            if(LOWER_CHARS.contains(chr)){
                buffer.append(chr);
            }else if(i==0){
                buffer.append(chr.toLowerCase());
            }else { break; }
        }
        String action = buffer.toString();
        if(DEFAULT_ACTIONS.contains(action)){
            return action;
        }

        txt = exceptiveAction(pkg, txt, lowerResourceName);

        return convertName(txt);
    }
    private static String exceptiveAction(String pkg, String txt, String lowerResourceName){
        int i = txt.indexOf("By");
        String action = i<0?txt:txt.substring(0, i);
        String lowerAction = action.toLowerCase();
        if(lowerAction.startsWith(lowerResourceName)){
            return action.substring(lowerResourceName.length());
        }else if(lowerAction.endsWith(lowerResourceName)){
            return action.substring(0, action.length()-lowerResourceName.length());
        }else { return action; }
    }
    private static String convertName(String txt){
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<txt.length(); i++){
            String chr = txt.charAt(i)+"";
            if(LOWER_CHARS.contains(chr)){
                buffer.append(chr);
            }else if(i==0){
                buffer.append(chr.toLowerCase());
            }else {
                buffer.append("-");
                buffer.append(chr.toLowerCase());
            }
        }
        return buffer.toString();
    }
}
