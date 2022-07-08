package com.fmk.framework.dao;

import cn.hutool.core.convert.Convert;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.fmk.framework.daomodel.Update;
import com.fmk.framework.daomodel.UpdateValue;
import com.fmk.framework.exception.ApplicationException;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Test {
    public static void main(String[] args){

        SqlSelect select = SqlSelect.selec("*")
            .from("a")
            .eq("f1", 1)
            .in("f2", Lists.newArrayList("1", "2"));

        System.out.println(select.toString());

        select = SqlSelect.selec("*")
            .from("a")
            .in("f2", Lists.newArrayList("1", "2"));

        System.out.println(select.toString());

        select = SqlSelect.selec("*")
            .from("a")
            .in(true, "f2", Lists.newArrayList("1", "2"));
        System.out.println(select.toString());

        select = SqlSelect.selec("*")
            .from("a")
            .in(false, "f2", Lists.newArrayList("1", "2"));

        System.out.println(select.toString());

        if(true) return;

        Update update = new Update();
        update.setSql("sql");
        List<UpdateValue> list = new ArrayList<>();

        UpdateValue updateValue = new UpdateValue();
        list.add(updateValue);

        updateValue = new UpdateValue();
        updateValue.setType("Integer");
        updateValue.setValue(Integer.MAX_VALUE);
        list.add(updateValue);

        updateValue = new UpdateValue();
        updateValue.setType("Boolean");
        updateValue.setValue(true);
        list.add(updateValue);

        updateValue = new UpdateValue();
        updateValue.setType("Byte");
        updateValue.setValue(Byte.MAX_VALUE);
        list.add(updateValue);

        updateValue = new UpdateValue();
        updateValue.setType("Short");
        updateValue.setValue(Short.MAX_VALUE);
        list.add(updateValue);

        updateValue = new UpdateValue();
        updateValue.setType("Long");
        updateValue.setValue(Long.MAX_VALUE);
        list.add(updateValue);

        updateValue = new UpdateValue();
        updateValue.setType("Float");
        updateValue.setValue(Float.MAX_VALUE);
        list.add(updateValue);

        updateValue = new UpdateValue();
        updateValue.setType("Double");
        updateValue.setValue(Double.MAX_VALUE);
        list.add(updateValue);

        updateValue = new UpdateValue();
        updateValue.setType("BigDecimal");
        updateValue.setValue(new BigDecimal("789976545678939998776234234.234234234234"));
        list.add(updateValue);

        updateValue = new UpdateValue();
        updateValue.setType("Timestamp");
        updateValue.setValue(new Timestamp(DateTime.now().getMillis()));
        list.add(updateValue);

        updateValue = new UpdateValue();
        updateValue.setType("SqlDate");
        updateValue.setValue(new java.sql.Date(DateTime.now().getMillis()));
        list.add(updateValue);

        updateValue = new UpdateValue();
        updateValue.setType("Date");
        updateValue.setValue(new Date());
        list.add(updateValue);

        updateValue = new UpdateValue();
        updateValue.setType("String");
        updateValue.setValue("jjjjsd");
        list.add(updateValue);

        update.setValues(list);

        String s = JSON.toJSONString(update, true);
        System.out.println(s);

        Update update1 = JSON.parseObject(s, Update.class);
        System.out.println(update1.getSql());


        List<UpdateValue> v1s = update.getValues();
        List<UpdateValue> v2s = update1.getValues();

        Object[] values = buildValues(update1.getValues());

        System.out.println("===================================================");
        for(int i=0; i<v2s.size(); i++){
            Object value = v1s.get(i)
                .getValue();
            Object value1 = v2s.get(i)
                .getValue();
            boolean equals = Objects.equals(value, value1);
            System.out.println(equals);
        }
        System.out.println("===================================================");

        s = JSON.toJSONString(update1, true);
        System.out.println(s);


    }
    private static Object[] buildValues(List<UpdateValue> updateValues){
        Object[] values = new Object[updateValues.size()];
        for(int i=0; i<updateValues.size(); i++){
            UpdateValue updateValue = updateValues.get(i);
            String type = updateValue.getType();
            Object value = updateValue.getValue();
            if(StringUtils.isBlank(type)) {
                values[i] = null;
            }else if("Boolean".equals(type)){
                values[i] = value;
            } else if("Byte".equals(type)) {
                values[i] = Convert.convert(Byte.class, value);
            } else if("Short".equals(type)) {
                values[i] = Convert.convert(Short.class, value);
            } else if("Integer".equals(type)){
                values[i] = Convert.convert(Integer.class, value);
            } else if("Long".equals(type)){
                values[i] = Convert.convert(Long.class, value);
            } else if("Float".equals(type)){
                values[i] = Convert.convert(Float.class, value);
            } else if("Double".equals(type)){
                values[i] = Convert.convert(Double.class, value);
            } else if("BigDecimal".equals(type)){
                values[i] = Convert.convert(BigDecimal.class, value);
            } else if("Timestamp".equals(type)){
                values[i] = Convert.convert(Timestamp.class, value);
            } else if("SqlDate".equals(type)){
                values[i] = Convert.convert(java.sql.Date.class, value);
            } else if("Date".equals(type)){
                values[i] = Convert.convert(Date.class, value);
            } else if("String".equals(type)){
                values[i] = Convert.convert(String.class, value);
            } else {
                throw new ApplicationException("convert err from:"+value.getClass().getName()+" to "+type+"; value:"+value);
            }
            updateValue.setValue(values[i]);
        }
        return values;
    }
}
