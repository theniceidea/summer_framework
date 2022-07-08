package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.DateRange;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.basic.ValidateHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class DateRangeValidator implements ValidatorItem {

    private DateRange anno;

    private Field field;

    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof DateRange){
            this.anno = (DateRange) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be DateRange");
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
        return ValidateHelper.dateRange(anno, val);
    }

    @Override
    public String apiInfo() {
        return "min: "+anno.min()+"; max: "+anno.max()+"; msg: "+anno.msg();
    }
}
