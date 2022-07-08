package com.fmk.framework.sql;

import java.util.List;

public class QueryItem implements IQueryItem{
    private String columnName;
    private String op;
    private Object value;

    public QueryItem(String columnName, String op, Object value){
        this.columnName = columnName;
        this.op = op;
        this.value = value;
    }

    @Override
    public boolean empty(){
        return false;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public String getOp() {
        return op;
    }

    public void setOp(String op) {
        this.op = op;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public void buildSql(StringBuilder builder, List<Parameter> parameters){
//        builder.append()
    }
}
