package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.Pattern;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.basic.ValidateHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class PatternValidator implements ValidatorItem {

    private Pattern anno;

    private Field field;

    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof Pattern){
            this.anno = (Pattern) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be Pattern");
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
        return ValidateHelper.pattern(anno, val);
    }

    @Override
    public String apiInfo() {
        return "pattern: "+anno.value()+"; msg: "+anno.msg();
    }
}
