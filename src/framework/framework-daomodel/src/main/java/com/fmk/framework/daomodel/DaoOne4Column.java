package com.fmk.framework.daomodel;

import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

public class DaoOne4Column extends Summer<Object> implements LocalSummer{
    private Class<?> kls;
    private String columnName;
    private Object key;
    private String onNullThrowErrMsg;

    public static <T> T s(Class<T> kls, String columnName, Object key, String onNullThrowErrMsg){
        DaoOne4Column model = new DaoOne4Column();
        model.setKls(kls);
        model.setColumnName(columnName);
        model.setKey(key);
        model.setOnNullThrowErrMsg(onNullThrowErrMsg);
        model.sum();
        return (T) model.getSummerResult();
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

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public String getOnNullThrowErrMsg() {
        return onNullThrowErrMsg;
    }

    public void setOnNullThrowErrMsg(String onNullThrowErrMsg) {
        this.onNullThrowErrMsg = onNullThrowErrMsg;
    }
}
