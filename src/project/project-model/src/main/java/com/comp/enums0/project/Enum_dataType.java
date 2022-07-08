package com.comp.enums0.project;

import com.fmk.framework.basic.IEnum;
public enum Enum_dataType implements IEnum<String> {
    /**
    * int
    */
    typeInt("int", "int"),
    /**
    * long
    */
    typeLong("long", "long"),
    /**
    * string
    */
    typeString("string", "string"),
    /**
    * double
    */
    typeDouble("double", "double"),
    /**
    * decimal
    */
    typeDecimal("decimal", "decimal"),
    /**
    * timestamp
    */
    typeTimestamp("timestamp", "timestamp"),
    /**
    * list
    */
    typeList("list", "list"),
    /**
    * set
    */
    typeSet("set", "set"),
    /**
    * map
    */
    typeMap("map", "map");
    private String value;
    private String title;
    Enum_dataType(String value, String title) {
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
