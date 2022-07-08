package com.fmk.framework.daomodel;

import com.fmk.framework.daoannotations.Table;

import java.util.ArrayList;
import java.util.List;
@Deprecated
public class IncrementUpdate {
    private Object id;
    private boolean noRollback;
    private String tableName;
    private String extCondation;
    private List<IncrementUpdateValue> values;

    public void addIncValue(String fieldName, String value){
        IncrementUpdateValue incrementUpdateValue = new IncrementUpdateValue();
        incrementUpdateValue.setFieldName(fieldName);
        incrementUpdateValue.setValue(value);
        if(null == values) values = new ArrayList<>();
        values.add(incrementUpdateValue);
    }
    public void setTableByClass(Class<?> kls){
        Table table = kls.getAnnotation(Table.class);
        this.tableName = table.value();
    }

    public Object getId() {
        return id;
    }

    public void setId(Object id) {
        this.id = id;
    }

    public boolean isNoRollback() {
        return noRollback;
    }

    public void setNoRollback(boolean noRollback) {
        this.noRollback = noRollback;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getExtCondation() {
        return extCondation;
    }

    public void setExtCondation(String extCondation) {
        this.extCondation = extCondation;
    }

    public List<IncrementUpdateValue> getValues() {
        return values;
    }

    public void setValues(List<IncrementUpdateValue> values) {
        this.values = values;
    }
}
