package com.fmk.framework.daosimple;

import java.util.List;
import java.util.Map;

public class DaoMap4Sql extends Summer<Map<String, Object>>{
    /**
     * 目标数据源
     */
    private Object target;
    private String sql;
    private List<Object> values;
    private String onNullThrowErrMsg;

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
