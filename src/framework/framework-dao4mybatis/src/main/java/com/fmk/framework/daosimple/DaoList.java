package com.fmk.framework.daosimple;

import java.util.List;

public class DaoList extends Summer<List>{
    /**
     * 目标数据源
     */
    private Object target;
    private Class<?> kls;
    private Integer start;
    private Integer limit;
    private Object sqlSelect;

    public Object getTarget() {
        return target;
    }

    public void setTarget(Object target) {
        this.target = target;
    }

    public Class<?> getKls() {
        return kls;
    }

    public void setKls(Class<?> kls) {
        this.kls = kls;
    }

    public Object getSqlSelect() {
        return sqlSelect;
    }

    public void setSqlSelect(Object sqlSelect) {
        this.sqlSelect = sqlSelect;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
