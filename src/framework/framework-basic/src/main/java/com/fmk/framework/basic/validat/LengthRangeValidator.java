package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.LengthRange;
import com.fmk.framework.basic.ReflectUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class LengthRangeValidator implements ValidatorItem {

    private LengthRange anno;

    private Field field;

    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof LengthRange){
            this.anno = (LengthRange) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be LengthRange");
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
        if(!field.getType().equals(String.class)){
            return false;
        }
        Object value = ReflectUtil.fieldValueNoThrow(field, bean);
        String val = (String)value;
        if(StringUtils.isBlank(val)){
            return anno.min()<=0;
        }
        return anno.max()>=val.length() && val.length()>=anno.min();
    }

    @Override
    public String apiInfo() {
        return "min: "+anno.min()+"; max: "+anno.max()+"; msg: "+anno.msg();
    }
}
