package com.testcomp.enums0.bizdemo;

import com.fmk.framework.basic.IEnum;
public enum Enum_demoenum implements IEnum<Integer> {
    /**
    * enum1
    */
    itm1(1, "enum1"),
    /**
    * enum2
    */
    itm2(2, "enum2"),
    /**
    * enum3
    */
    itm3(3, "enum3");
    private Integer value;
    private String title;
    Enum_demoenum(Integer value, String title) {
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
