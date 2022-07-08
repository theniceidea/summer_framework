package com.fmk.framework.daomodel;


import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

public class DaoRefresh extends Summer<Void> implements LocalSummer {
    private Object entity;

    public static void s(Object entity){
        DaoRefresh model = new DaoRefresh();
        model.setEntity(entity);
        model.sum();
    }

    public Object getEntity() {
        return entity;
    }

    public void setEntity(Object entity) {
        this.entity = entity;
    }
}
