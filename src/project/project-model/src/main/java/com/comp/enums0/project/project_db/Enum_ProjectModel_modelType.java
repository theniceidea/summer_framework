package com.comp.enums0.project.project_db;

import com.fmk.framework.basic.IEnum;
public enum Enum_ProjectModel_modelType implements IEnum<Integer> {
    /**
    * 类
    */
    typeClass(1, "类"),
    /**
    * 枚举
    */
    typeEnum(2, "枚举");
    private Integer value;
    private String title;
    Enum_ProjectModel_modelType(Integer value, String title) {
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
