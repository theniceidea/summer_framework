package com.fmk.framework.validation;

import com.fmk.framework.exception.Excep;
import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class VerifyHelper {
    public static void blankThrow(String value, String codeMsg){
        if(StringUtils.isBlank(value)) throw Excep.le(VerifyHelper.class, codeMsg);
    }
    public static void blankThrow(String value, String codeMsg, Map<String, String> params){
        if(StringUtils.isBlank(value)) throw Excep.le(VerifyHelper.class, codeMsg, params);
    }
    public static void notBlankThrow(String value, String codeMsg){
        if(StringUtils.isNotBlank(value)) throw Excep.le(VerifyHelper.class, codeMsg);
    }
    public static void notBlankThrow(String value, String codeMsg, Map<String, String> params){
        if(StringUtils.isNotBlank(value)) throw Excep.le(VerifyHelper.class, codeMsg, params);
    }
}
