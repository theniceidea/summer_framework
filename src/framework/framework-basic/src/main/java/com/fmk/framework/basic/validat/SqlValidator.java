package com.fmk.framework.basic.validat;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;

public class SqlValidator {
    public static boolean notNull(Object value){
        return null != value;
    }
    public static boolean notBlank(String value){
        return StringUtils.isNotBlank(value);
    }
    public static boolean notEmpty(Collection value){
        return null != value && !value.isEmpty();
    }
}
