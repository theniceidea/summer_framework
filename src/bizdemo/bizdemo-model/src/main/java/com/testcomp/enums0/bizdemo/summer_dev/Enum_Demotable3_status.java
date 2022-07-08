package com.testcomp.enums0.bizdemo.summer_dev;

import com.fmk.framework.basic.IEnum;
public enum Enum_Demotable3_status implements IEnum<Integer> {
    /**
    * 待审核
    */
    pending(1, "待审核"),
    /**
    * 已驳回
    */
    reject(2, "已驳回"),
    /**
    * 审批通过
    */
    approved(3, "审批通过");
    private Integer value;
    private String title;
    Enum_Demotable3_status(Integer value, String title) {
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
