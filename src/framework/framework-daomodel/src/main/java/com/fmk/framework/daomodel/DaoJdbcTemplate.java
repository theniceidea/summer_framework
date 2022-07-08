package com.fmk.framework.daomodel;

import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

public class DaoJdbcTemplate extends Summer<Object> implements LocalSummer{
    /**
     * bean name
     */
    private String name;
    
    public static Object s(String name){
        DaoJdbcTemplate model = new DaoJdbcTemplate();
        model.setName(name);
        model.sum();
        return model.getSummerResult();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
