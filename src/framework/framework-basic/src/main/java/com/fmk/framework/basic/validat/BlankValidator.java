package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.Blank;
import com.fmk.framework.basic.ReflectUtil;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class BlankValidator implements ValidatorItem {

    private Blank anno;

    private Field field;


    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof Blank){
            this.anno = (Blank) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be Blank");
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
        return field.getType().equals(String.class) && StringUtils.isBlank((String)val);
    }

    @Override
    public String apiInfo() {
        return "msg: "+anno.msg();
    }
}
