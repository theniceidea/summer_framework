package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.Size;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.basic.ValidateHelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class SizeValidator implements ValidatorItem {

    private Size anno;

    private Field field;

    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof Size){
            this.anno = (Size) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be Size");
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
        return ValidateHelper.size(anno, val);
    }

    @Override
    public String apiInfo() {
        return "min:"+anno.min()+"; max:"+anno.max()+"; msg:"+anno.msg();
    }
}
