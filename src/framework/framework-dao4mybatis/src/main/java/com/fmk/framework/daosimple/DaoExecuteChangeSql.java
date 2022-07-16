package com.fmk.framework.daosimple;


import java.util.List;

public class DaoExecuteChangeSql extends Summer<Integer> {
    /**
     * 目标数据源
      */
    private Object target;
    private String sql;
    private List<Object> parameters;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public List<Object> getParameters() {
        return parameters;
    }

    public void setParameters(List<Object> parameters) {
        this.parameters = parameters;
    }
}
