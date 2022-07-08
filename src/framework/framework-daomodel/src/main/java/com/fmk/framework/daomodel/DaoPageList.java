package com.fmk.framework.daomodel;

import com.fmk.framework.restful.PageResultList;
import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

public class DaoPageList extends Summer<PageResultList> implements LocalSummer{
    /**
     * 目标数据源
     */
    private Object target;
    private Class<?> kls;
    private int start;
    private int limit;
    private Object sqlSelect;

    public static <T> PageResultList<T> s(Class<T> kls, Object sqlSelect, int start, int limit){
        DaoPageList model = new DaoPageList();
        model.setKls(kls);
        model.setStart(start);
        model.setLimit(limit);
        model.setSqlSelect(sqlSelect);
        model.sum();
        return (PageResultList<T>) model.getSummerResult();
    }
    public static <T> PageResultList<T> s(Object target, Class<T> kls, Object sqlSelect, int start, int limit){
        DaoPageList model = new DaoPageList();
        model.setTarget(target);
        model.setKls(kls);
        model.setStart(start);
        model.setLimit(limit);
        model.setSqlSelect(sqlSelect);
        model.sum();
        return (PageResultList<T>) model.getSummerResult();
    }

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
