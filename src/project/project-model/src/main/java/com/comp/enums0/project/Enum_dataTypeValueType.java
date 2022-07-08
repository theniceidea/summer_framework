package com.comp.enums0.project;

import com.fmk.framework.basic.IEnum;
public enum Enum_dataTypeValueType implements IEnum<Integer> {
    /**
    * 数据库类型名字
    */
    baseTypeName(1, "数据库类型名字"),
    /**
    * 数据库类型id
    */
    dbId(2, "数据库类型id");
    private Integer value;
    private String title;
    Enum_dataTypeValueType(Integer value, String title) {
        this.value = value;
        this.title = title;
    }
    @Override
    public Integer value() {
        return value;
    }
    @Override
    public String title() {
        return title;
    }

}
