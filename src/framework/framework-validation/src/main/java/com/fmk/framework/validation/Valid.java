package com.fmk.framework.validation;

import com.fmk.framework.exception.Excep;
import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class Valid {
    public static void isNull(Object value, String msg){
        if(null != value){
            throw Excep.le(Valid.class, msg);
        }
    }
    public static boolean isNull(Object value){
        return null == value;
    }

    public static void notNull(Object value, String msg){
        if(null == value){
            throw Excep.le(Valid.class, msg);
        }
    }
    public static boolean notNull(Object value){
        return null != value;
    }

    public static void isBlank(String value, String msg){
        if(!StringUtils.isBlank(value)){
            throw Excep.le(Valid.class, msg);
        }
    }
    public static boolean isBlank(String value){
        return StringUtils.isBlank(value);
    }

    public static void notBlank(String value, String msg){
        if(StringUtils.isBlank(value)){
            throw Excep.le(Valid.class, msg);
        }
    }
    public static boolean notBlank(String value){
        return StringUtils.isNotBlank(value);
    }

    public static void isEmpty(Collection value, String msg){
        if(null == value){
            return;
        }
        if(!value.isEmpty()){
            throw Excep.le(Valid.class, msg);
        }
    }
    public static boolean isEmpty(Collection value){
        if(null == value){
            return true;
        }
        return value.isEmpty();
    }

    public static void notEmpty(Collection value, String msg){
        if(null == value){
            throw Excep.le(Valid.class, msg);
        }
        if(value.isEmpty()){
            throw Excep.le(Valid.class, msg);
        }
    }
    public static boolean notEmpty(Collection value){
        if(null == value){
            return false;
        }
        return !value.isEmpty();
    }
}
