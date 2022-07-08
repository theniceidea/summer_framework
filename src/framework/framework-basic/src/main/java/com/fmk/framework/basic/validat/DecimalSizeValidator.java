package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.DecimalSize;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.basic.ValidateHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class DecimalSizeValidator implements ValidatorItem {

    private DecimalSize anno;

    private Field field;

    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof DecimalSize){
            this.anno = (DecimalSize) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be DecimalSize");
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
        return ValidateHelper.decimalSize(anno, val);
    }

    @Override
    public String apiInfo() {
        return "min: "+anno.min()+"; max: "+anno.max()+"; msg: "+anno.msg();
    }
}
