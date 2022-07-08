package com.comp.enums0.project.project_db;

import com.fmk.framework.basic.IEnum;
public enum Enum_ProjectModel_summerModel implements IEnum<Integer> {
    /**
    * summer
    */
    summer(1, "summer"),
    /**
    * 非summer对象
    */
    noSummer(2, "非summer对象");
    private Integer value;
    private String title;
    Enum_ProjectModel_summerModel(Integer value, String title) {
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
