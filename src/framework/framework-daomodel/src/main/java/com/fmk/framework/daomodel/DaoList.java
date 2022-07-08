package com.fmk.framework.daomodel;

import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

import java.util.List;

public class DaoList extends Summer<List> implements LocalSummer{
    /**
     * 目标数据源
     */
    private Object target;
    private Class<?> kls;
    private Integer start;
    private Integer limit;
    private Object sqlSelect;

    public static <T> List<T> s(Class<T> kls, Object sqlSelect){
        DaoList model = new DaoList();
        model.setKls(kls);
        model.setSqlSelect(sqlSelect);
        model.sum();
        return (List<T>) model.getSummerResult();
    }
    public static <T> List<T> s(Object target, Class<T> kls, Object sqlSelect){
        DaoList model = new DaoList();
        model.setTarget(target);
        model.setKls(kls);
        model.setSqlSelect(sqlSelect);
        model.sum();
        return (List<T>) model.getSummerResult();
    }
    public static <T> List<T> s(Class<T> kls, Object sqlSelect, Integer start, Integer limit){
        DaoList model = new DaoList();
        model.setKls(kls);
        model.setSqlSelect(sqlSelect);
        model.setStart(start);
        model.setLimit(limit);
        model.sum();
        return (List<T>) model.getSummerResult();
    }
    public static <T> List<T> s(Object target, Class<T> kls, Object sqlSelect, Integer start, Integer limit){
        DaoList model = new DaoList();
        model.setTarget(target);
        model.setKls(kls);
        model.setSqlSelect(sqlSelect);
        model.setStart(start);
        model.setLimit(limit);
        model.sum();
        return (List<T>) model.getSummerResult();
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
