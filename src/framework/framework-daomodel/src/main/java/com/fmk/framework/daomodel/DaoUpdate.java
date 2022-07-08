package com.fmk.framework.daomodel;


import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

public class DaoUpdate<T> extends Summer<T> implements LocalSummer {
    private T entity;

    public static <R> R s(R entity){
        DaoUpdate<R> model = new DaoUpdate<>();
        model.setEntity(entity);
        model.sum();
        return model.getSummerResult();
    }

    public T getEntity() {
        return entity;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }
}
