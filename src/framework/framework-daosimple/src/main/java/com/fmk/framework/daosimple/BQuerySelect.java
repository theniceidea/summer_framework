package com.fmk.framework.daosimple;

import com.fmk.framework.daomodel.QuerySelect;

import java.util.ArrayList;
import java.util.List;

public class BQuerySelect implements QuerySelect {
    private String select="";
    private StringBuilder builder=new StringBuilder();
    private List<Object> parameters=new ArrayList<>();

    @Override
    public String toSqlString() {
        StringBuilder buf=new StringBuilder();
        buf.append(select);
        buf.append(builder);
        return buf.toString();
    }

    @Override
    public String toSqlString(int start, int limit) {
        StringBuilder buf=new StringBuilder();
        buf.append(select);
        buf.append(builder);
        buf.append(" limit ");
        buf.append(start);
        buf.append(" ,");
        buf.append(limit);
        return buf.toString();
    }

    @Override
    public String toCountSqlString() {
        StringBuilder buf=new StringBuilder();
        buf.append("SELECT COUNT(1) ");
        buf.append(builder);
        return buf.toString();
    }

    @Override
    public List<Object> getParameters() {
        return this.parameters;
    }

    public String getSelect() {
        return select;
    }

    public void setSelect(String select) {
        this.select = select;
    }

    public StringBuilder getBuilder() {
        return builder;
    }

}
