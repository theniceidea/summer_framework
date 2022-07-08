package com.fmk.framework.daomodel;

import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

import java.util.List;
import java.util.Map;

public class DaoMap4Sql extends Summer<Map<String, Object>> implements LocalSummer{
    /**
     * 目标数据源
     */
    private Object target;
    private String sql;
    private List<Object> values;
    private String onNullThrowErrMsg;

    public static <T> T s(String sql, List<Object> values, String onNullThrowErrMsg){
        DaoMap4Sql model = new DaoMap4Sql();
        model.setSql(sql);
        model.setValues(values);
        model.setOnNullThrowErrMsg(onNullThrowErrMsg);
        model.sum();
        return (T) model.getSummerResult();
    }
    public static <T> T s(Object target, String sql, List<Object> values, String onNullThrowErrMsg){
        DaoMap4Sql model = new DaoMap4Sql();
        model.setTarget(target);
        model.setSql(sql);
        model.setValues(values);
        model.setOnNullThrowErrMsg(onNullThrowErrMsg);
        model.sum();
        return (T) model.getSummerResult();
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

    public List<Object> getValues() {
        return values;
    }

    public void setValues(List<Object> values) {
        this.values = values;
    }

    public String getOnNullThrowErrMsg() {
        return onNullThrowErrMsg;
    }

    public void setOnNullThrowErrMsg(String onNullThrowErrMsg) {
        this.onNullThrowErrMsg = onNullThrowErrMsg;
    }
}
