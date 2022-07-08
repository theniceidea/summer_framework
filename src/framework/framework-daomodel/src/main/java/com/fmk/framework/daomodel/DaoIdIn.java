package com.fmk.framework.daomodel;

import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

import java.util.List;

public class DaoIdIn extends Summer<Object> implements LocalSummer{
    private Class<?> kls;
    private List<Object> keys;

    public static <T> List<T> s(Class<T> kls, List<Object> keys){
        DaoIdIn model = new DaoIdIn();
        model.setKls(kls);
        model.setKeys(keys);
        model.sum();
        return (List<T>) model.getSummerResult();
    }

    public Class<?> getKls() {
        return kls;
    }

    public void setKls(Class<?> kls) {
        this.kls = kls;
    }

    public List<Object> getKeys() {
        return keys;
    }

    public void setKeys(List<Object> keys) {
        this.keys = keys;
    }
}
