package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.DateBefore;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.basic.ValidateHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class DateBeforeValidator implements ValidatorItem {

    private DateBefore anno;

    private Field field;

    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof DateBefore){
            this.anno = (DateBefore) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be DateBefore");
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
        Object val = ReflectUtil.fieldValueNoThrow(field, bean);
        if(null == val) {
            return true;
        }
        return ValidateHelper.dateBefore(val);
    }

    @Override
    public String apiInfo() {
        return "msg: "+anno.msg();
    }
}
