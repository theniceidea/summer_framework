package com.comp.enums0.project;

import com.fmk.framework.basic.IEnum;
public enum Enum_dbType implements IEnum<String> {
    /**
    * int
    */
    typeInt("int", "int"),
    /**
    * bigint
    */
    typeBigint("bigint", "bigint"),
    /**
    * char
    */
    typeChar("char", "char"),
    /**
    * varchar
    */
    typeVarchar("varchar", "varchar"),
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
    * text
    */
    typeText("text", "text");
    private String value;
    private String title;
    Enum_dbType(String value, String title) {
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
