package com.fmk.framework.exception;

import com.fmk.framework.summer.BasicSummer;

public class GetExceptionMessageByCode extends BasicSummer<String> {
    private String code;
    private String defaultMessage;

    public static String s(String code, String defaultMessage){
        GetExceptionMessageByCode model = new GetExceptionMessageByCode();
        model.setCode(code);
        model.setDefaultMessage(defaultMessage);

        return model.sum();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDefaultMessage() {
        return defaultMessage;
    }

    public void setDefaultMessage(String defaultMessage) {
        this.defaultMessage = defaultMessage;
    }
}
