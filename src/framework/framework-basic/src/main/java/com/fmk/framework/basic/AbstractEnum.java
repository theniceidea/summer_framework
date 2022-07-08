package com.fmk.framework.basic;

import com.google.common.collect.ImmutableSet;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by jerry on 16-6-1.
 */
public abstract class AbstractEnum<T> implements Serializable {
    private static HashMap<Class<? extends AbstractEnum>, HashMap<Object, AbstractEnum>> Values = new HashMap<>();

    private T value;
    private String title;

    public AbstractEnum(T value, String title){
        this.value=value;
        this.title=title;
        Class<? extends AbstractEnum> aClass = this.getClass();
        HashMap<Object, AbstractEnum> tStringHashMap = Values.get(aClass);
        if(null == tStringHashMap){
            synchronized (AbstractEnum.class) {
                tStringHashMap = Values.get(aClass);
                if(null == tStringHashMap) {
                    tStringHashMap = new HashMap<>();
                    Values.put(aClass, tStringHashMap);
                }
            }
        }
        tStringHashMap.put(value, this);
    }

    public static boolean exists(Class<? extends AbstractEnum> klass, Object key){
        return null != get(klass, key);
    }

    private static void initFields(Class<?> klass){
        Field[] fields = klass.getFields();
        if(fields.length>0) {
            try {
                fields[0].get(null);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }

    }
    public static <K extends AbstractEnum> K get(Class<K> klass, Object key){
        HashMap<Object, AbstractEnum> tStringHashMap = Values.get(klass);
        if(null != tStringHashMap) return (K) tStringHashMap.get(key);

        synchronized (AbstractEnum.class) {
            tStringHashMap = Values.get(klass);
            if(null == tStringHashMap) {
                tStringHashMap = new HashMap<>();
                Values.put(klass, tStringHashMap);
            }
        }
        initFields(klass);

        return (K) tStringHashMap.get(key);
    }

    public static <K extends AbstractEnum> ImmutableSet<?> keys(Class<K> klass){
        HashMap<Object, AbstractEnum> tStringHashMap = Values.get(klass);
        if(null != tStringHashMap) return ImmutableSet.copyOf(tStringHashMap.keySet());

        synchronized (AbstractEnum.class) {
            tStringHashMap = Values.get(klass);
            if(null == tStringHashMap) {
                tStringHashMap = new HashMap<>();
                Values.put(klass, tStringHashMap);
            }
        }
        initFields(klass);

        return ImmutableSet.copyOf(tStringHashMap.keySet());
    }



    public boolean is(T value){
        return Objects.equals(value, this.value);
    }

    public T getValue() {
        return value;
    }


    public String getTitle() {
        return title;
    }

}
