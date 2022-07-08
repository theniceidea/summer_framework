package com.fmk.framework.daomodel;

public class IncUpdateValue {
    private String fieldName;
    private Object value;
    //update table set a=a-10 where a>=10
    //update table set a=a-10 where a-10>=0
//    private String limitOperator;
//    private Object limitValue;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
