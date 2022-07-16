package com.fmk.framework.daosimple;

import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.ObjectDeserializer;
import com.alibaba.fastjson.util.FieldInfo;
import com.alibaba.fastjson.util.JavaBeanInfo;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
//import org.apache.commons.beanutils.BeanUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ReflectUtil {
    private static ConcurrentHashMap<String, Field> Fields = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Class<?>, List<Field>> FieldsCache = new ConcurrentHashMap<>();
    //key=className+AnnotationClassName
    private static ConcurrentHashMap<String, ImmutableList<Field>> AnnotationFieldsCache = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String, Boolean> GetClassCache = new ConcurrentHashMap<>();

    public static Object pathValue(Object o, String path){
        List<String> split = Splitter.on(".").omitEmptyStrings().trimResults().splitToList(path);
        Object result = null;
        for(int i=0; i<split.size(); i++){
            String name = split.get(i);
            Field field = getField(o.getClass(), name, false, false);
            if(isNull(field)) return null;
            o = fieldValueNoThrow(field, o);
            result = o;
            if(isNull(o)) return null;
        }
        return result;
    }
    public static Object fieldValueNoThrow(Field field, Object o){
        try {
            return field.get(o);
        } catch (IllegalAccessException e) {
        }
        return null;
    }
    public static Object fieldValueWrapExcep(Field field, Object o){
        try {
            return field.get(o);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static Class<?> getClass(String name){
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    public static Class<?> getClassNoThrow(String name){
        if(GetClassCache.contains(name)) { return null; }
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            GetClassCache.put(name, true);
            return null;
        }
    }

    public static Field getField(Class<?> kls, String name, boolean currentClass, boolean notFoundThrow){
        String key = kls.getName()+name;
        Field field = Fields.get(key);
        if(nonNull(field)) return field;

        while (nonNull(kls)){
            field = getField(kls, name);
            if(nonNull(field)) {
                field.setAccessible(true);
                Fields.put(key, field);
                return field;
            }
            if(currentClass){
                if(notFoundThrow) throw new RuntimeException(kls.getName()+" field "+name);
                return null;
            }
            kls = kls.getSuperclass();
        }
        if(notFoundThrow) throw new RuntimeException(kls.getName()+" field "+name);
        return null;
    }
    private static Field getField(Class<?> kls, String name){
        try{
            return kls.getDeclaredField(name);
        } catch (NoSuchFieldException e) { }
        return null;
    }
    public static <T extends Annotation> ImmutableList<Field> getAnnotationField(Class<?> kls, Class<T> annotationKls){
        String key = kls.getName() + ":" + annotationKls.getName();
        ImmutableList<Field> immutableList = AnnotationFieldsCache.get(key);
        if(nonNull(immutableList)){
            return immutableList;
        }

        List<Field> fields = new ArrayList<>();
        eachFields(kls, field -> {
            T annotation = field.getAnnotation(annotationKls);
            if(nonNull(annotation)){
                fields.add(field);
            }
        });
        immutableList = ImmutableList.copyOf(fields);
        AnnotationFieldsCache.put(key, immutableList);
        return immutableList;
    }
    public static void eachFields(Class<?> kls, Consumer<Field> consumer){
        List<Field> fields = FieldsCache.get(kls);
        if(nonNull(fields)){
            fields.forEach(consumer);
            return;
        }
        fields = Lists.newArrayList();
        Class<?> cls = kls;
        while (nonNull(cls)){
            Field[] declaredFields = cls.getDeclaredFields();
            if(nonNull(declaredFields)){
                for(Field field : declaredFields){
                    field.setAccessible(true);
                    fields.add(field);
                }
            }
            cls = cls.getSuperclass();
        }
        FieldsCache.put(kls, fields);
        fields.forEach(consumer);
    }
    public static <T> T newInstance(Class<T> kls){
        try {
            return kls.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setFieldValue(Object o, String fieldName, Object value, boolean notFoundThrow){
        try {
            Field field = getField(o.getClass(), fieldName, false, notFoundThrow);
            field.set(o, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setFieldValue(Object o, Field field, Object value){
        try {
            field.set(o, value);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }
//    public static RuntimeException setProperty(Object o, String fieldName, Object value){
//        try {
//            BeanUtils.setProperty(o, fieldName, value);
//            return null;
//        } catch (Exception e) {
//            if(e instanceof RuntimeException){
//                return (RuntimeException) e;
//            }
//            return new RuntimeException(e.getMessage(), e);
//        }
//    }

    public static FieldInfo getFieldInfo(Class<?> kls, String fieldName, boolean notFoundThrow){
        ObjectDeserializer deserializer = ParserConfig.global.getDeserializer(kls);
        JavaBeanInfo beanInfo = (JavaBeanInfo) ReflectUtil.pathValue(deserializer, "beanInfo");
        FieldInfo[] fields = beanInfo.fields;
        for(int i = 0; i< fields.length; i++){
            if(Objects.equals(fieldName, fields[i].name)){
                return fields[i];
            }
        }
        if(notFoundThrow) throw new RuntimeException("class "+kls.getName()+" field:"+fieldName+" notfound");
        return null;
    }
}
