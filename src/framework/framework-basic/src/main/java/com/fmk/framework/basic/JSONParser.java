package com.fmk.framework.basic;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.fmk.framework.logger.Logger;

import java.util.Optional;

/**
 * Created by jerry on 16-7-6.
 */
public class JSONParser {
    private static Logger logger =Logger.getLogger(JSONParser.class);
    public static <T> T parseObject(String text, Class<T> klass){
        T t = null;
        try {
            t = JSON.parseObject(text, klass);
        }catch (RuntimeException re){
            logger.warn(re.getMessage(), re);
        }
        if(null == t) logger.error(klass.getName() + " convert from error, " + text);
        return t;
    }
    public static Optional<JSONObject> parseObject(String text){
        try {
            return Optional.ofNullable(JSON.parseObject(text));
        }catch (Exception e){
            logger.warn(e.getMessage(), e);
            return Optional.empty();
        }
    }
    public static <T> T parseObject(String text, TypeReference<T> typeReference, Feature... features){
        T t = null;
        try {
            t = JSON.parseObject(text, typeReference, features);
        }catch (RuntimeException re){
            logger.warn(re.getMessage(), re);
        }
        if(null == t) logger.error(typeReference.toString() + " convert from error, " + text);
        return t;
    }
    public static String toJSONString(Object value){
        return JSON.toJSONString(value);
    }
}
