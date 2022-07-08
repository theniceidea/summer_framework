package com.comp.enums0.project;

import com.fmk.framework.basic.IEnum;
public enum Enum_dataTypeMapKey implements IEnum<String> {
    /**
    * int
    */
    typeInt("int", "int"),
    /**
    * string
    */
    typeString("string", "string");
    private String value;
    private String title;
    Enum_dataTypeMapKey(String value, String title) {
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
