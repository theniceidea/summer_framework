package com.fmk.framework.daomodel;

import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

public class DaoSingleValue extends Summer<Object> implements LocalSummer{
    private Object sqlSelect;

    public static <T> T s(Object sqlSelect){
        DaoSingleValue model = new DaoSingleValue();
        model.setSqlSelect(sqlSelect);
        model.sum();
        return (T)model.getSummerResult();
    }

    public Object getSqlSelect() {
        return sqlSelect;
    }

    public void setSqlSelect(Object sqlSelect) {
        this.sqlSelect = sqlSelect;
    }
}
