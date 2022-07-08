package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.DateAfter;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.basic.ValidateHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class DateAfterValidator implements ValidatorItem {

    private DateAfter anno;

    private Field field;

    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof DateAfter){
            this.anno = (DateAfter) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be DateAfter");
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
        return ValidateHelper.dateAfter(val);
    }

    @Override
    public String apiInfo() {
        return "msg: "+anno.msg();
    }
}
