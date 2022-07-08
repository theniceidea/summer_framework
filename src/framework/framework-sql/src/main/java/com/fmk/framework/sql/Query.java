package com.fmk.framework.sql;

public class Query {

    private Class<?> viewClass;

    private QueryGroup queryGroup;

    private Query(Class<?> kls){
        viewClass = kls;
    }
    public static Query NewQuery(Class<?> kls){
        return new Query(kls);
    }

}
