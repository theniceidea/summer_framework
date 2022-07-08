package com.fmk.framework.daomodel;

import java.util.List;

public class Update {
    private String sql;
    private List<UpdateValue> values;

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<UpdateValue> getValues() {
        return values;
    }

    public void setValues(List<UpdateValue> values) {
        this.values = values;
    }
}
