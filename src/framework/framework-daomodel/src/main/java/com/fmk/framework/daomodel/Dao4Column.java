package com.fmk.framework.daomodel;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;

import java.util.List;

public class Dao4Column extends BasicSummer<Object> implements LocalSummer{
    private Class<?> kls;
    private String columnName;
    private Object value;
    private int limit;

    public static <T> List<T> s(Class<T> kls, String columnName, Object value,int limit){
        Dao4Column model = new Dao4Column();
        model.setKls(kls);
        model.setColumnName(columnName);
        model.setValue(value);
        model.setLimit(limit);
        model.sum();
        return (List<T>) model.getSummerResult();
    }

    public Class<?> getKls() {
        return kls;
    }

    public void setKls(Class<?> kls) {
        this.kls = kls;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
