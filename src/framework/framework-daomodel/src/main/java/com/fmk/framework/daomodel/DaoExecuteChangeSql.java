package com.fmk.framework.daomodel;


import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

import java.util.List;

public class DaoExecuteChangeSql extends Summer<Integer> implements LocalSummer {
    /**
     * 目标数据源
      */
    private Object target;
    private String sql;
    private List<Object> parameters;

    public static int s(String sql, List<Object> parameters){
        DaoExecuteChangeSql model = new DaoExecuteChangeSql();
        model.setSql(sql);
        model.setParameters(parameters);
        model.sum();
        return model.getSummerResult();
    }
    public static int s(Object target, String sql, List<Object> parameters){
        DaoExecuteChangeSql model = new DaoExecuteChangeSql();
        model.setTarget(target);
        model.setSql(sql);
        model.setParameters(parameters);
        model.sum();
        return model.getSummerResult();
    }

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
