package com.fmk.framework.daosimple;

public class DaoPageList extends Summer<PageResultList>{
    /**
     * 目标数据源
     */
    private Object target;
    private Class<?> kls;
    private int start;
    private int limit;
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

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public Object getSqlSelect() {
        return sqlSelect;
    }

    public void setSqlSelect(Object sqlSelect) {
        this.sqlSelect = sqlSelect;
    }
}
