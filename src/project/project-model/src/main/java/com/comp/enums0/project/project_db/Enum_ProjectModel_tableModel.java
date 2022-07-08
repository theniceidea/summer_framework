package com.comp.enums0.project.project_db;

import com.fmk.framework.basic.IEnum;
public enum Enum_ProjectModel_tableModel implements IEnum<Integer> {
    /**
    * 数据库表
    */
    dbTable(1, "数据库表"),
    /**
    * 非数据库表
    */
    noDbTable(2, "非数据库表");
    private Integer value;
    private String title;
    Enum_ProjectModel_tableModel(Integer value, String title) {
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
