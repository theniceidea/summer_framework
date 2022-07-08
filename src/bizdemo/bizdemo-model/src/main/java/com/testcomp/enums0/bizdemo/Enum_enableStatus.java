package com.testcomp.enums0.bizdemo;

import com.fmk.framework.basic.IEnum;
public enum Enum_enableStatus implements IEnum<String> {
    /**
    * 有效
    */
    enabled("sv1", "有效"),
    /**
    * 无效
    */
    unEnabled("s\"v2", "无效");
    private String value;
    private String title;
    Enum_enableStatus(String value, String title) {
        this.value = value;
        this.title = title;
    }
    @Override
    public String value() {
        return value;
    }
    @Override
    public String title() {
        return title;
    }

}
