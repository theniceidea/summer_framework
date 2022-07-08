package com.comp.enums0.project.project_db;

import com.fmk.framework.basic.IEnum;
public enum Enum_ProjectModel_summerNs implements IEnum<String> {
    /**
    * local
    */
    local("local", "local"),
    /**
    * inner
    */
    inner("inner", "inner"),
    /**
    * cms
    */
    cms("cms", "cms"),
    /**
    * portal
    */
    portal("portal", "portal");
    private String value;
    private String title;
    Enum_ProjectModel_summerNs(String value, String title) {
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
