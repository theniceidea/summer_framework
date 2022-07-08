package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.DateRangeNow;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.basic.ValidateHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class DateRangeNowValidator implements ValidatorItem {

    private DateRangeNow anno;

    private Field field;

    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof DateRangeNow){
            this.anno = (DateRangeNow) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be DateRangeNow");
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
        return ValidateHelper.dateRangeNow(anno, val);
    }

    @Override
    public String apiInfo() {
        return "before: "+anno.before()+"; after: "+anno.after()+"; msg:"+anno.msg();
    }
}
