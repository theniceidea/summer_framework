package com.testcomp.enums0.bizdemo;

import com.fmk.framework.basic.IEnum;
public enum Enum_deleteStatus implements IEnum<Integer> {
    /**
    * 删除
    */
    deleted(1, "删除"),
    /**
    * 未删除
    */
    unDeleted(2, "未删除");
    private Integer value;
    private String title;
    Enum_deleteStatus(Integer value, String title) {
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
