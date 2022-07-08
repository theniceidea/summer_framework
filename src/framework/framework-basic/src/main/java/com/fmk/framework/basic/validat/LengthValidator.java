package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.Length;
import com.fmk.framework.basic.ReflectUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class LengthValidator implements ValidatorItem {

    private Length anno;

    private Field field;

    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof Length){
            this.anno = (Length) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be Length");
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
            return anno.value()<=0;
        }
        return anno.value()>=val.length();
    }

    @Override
    public String apiInfo() {
        return "maxLength: "+anno.value()+"; msg: "+anno.msg();
    }
}
