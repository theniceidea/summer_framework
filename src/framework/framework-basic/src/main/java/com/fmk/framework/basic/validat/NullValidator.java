package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.Null;
import com.fmk.framework.basic.ReflectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class NullValidator implements ValidatorItem {

    private Null anno;

    private Field field;

    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof Null){
            this.anno = (Null) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be Null");
    }

    @Override
    public Field getField() {
        return field;
    }

    @Override
    public void setField(Field field) {
        this.field = field;
    }

    @Override
    public String getFailedMsg() {
        return anno.msg();
    }

    @Override
    public boolean validation(Object bean) {
        Object value = ReflectUtil.fieldValueNoThrow(field, bean);
        return null == value;
    }

    @Override
    public String apiInfo() {
        return "msg: "+anno.msg();
    }
}
