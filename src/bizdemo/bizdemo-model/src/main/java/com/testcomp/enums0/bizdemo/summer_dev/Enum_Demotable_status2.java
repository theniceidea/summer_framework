package com.testcomp.enums0.bizdemo.summer_dev;

import com.fmk.framework.basic.IEnum;
public enum Enum_Demotable_status2 implements IEnum<String> {
    /**
    * 待审核
    */
    pending("sv\"1", "待审核"),
    /**
    * 已驳回
    */
    reject("sv2", "已驳回"),
    /**
    * 审批通过
    */
    approved("sv3", "审批通过");
    private String value;
    private String title;
    Enum_Demotable_status2(String value, String title) {
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
