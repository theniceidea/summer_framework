package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.NotNull;
import com.fmk.framework.basic.ReflectUtil;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class NotNullValidator implements ValidatorItem {

    private NotNull anno;

    private Field field;

    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof NotNull){
            this.anno = (NotNull) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be NotNull");
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
        return null != value;
    }

    @Override
    public String apiInfo() {
        return "msg: "+anno.msg();
    }
}
