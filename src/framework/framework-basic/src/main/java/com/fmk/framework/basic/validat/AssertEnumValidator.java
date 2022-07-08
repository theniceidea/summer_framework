package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.AssertEnum;
import com.fmk.framework.basic.IEnum;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.basic.ValidateHelper;
import org.apache.commons.lang3.StringUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public class AssertEnumValidator implements ValidatorItem {

    private AssertEnum anno;

    private Field field;

    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof AssertEnum){
            this.anno = (AssertEnum) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be AssertEnum");
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
        return ValidateHelper.assertEnum(anno, val);
    }

    @Override
    public String apiInfo() {
        Class<?> enumClass = anno.enumClass();
        Object[] enumConstants = enumClass.getEnumConstants();
        StringBuffer buffer = new StringBuffer();
        if(null != enumConstants && IEnum.class.isAssignableFrom(enumClass)){
            for(int i=0; i<enumConstants.length; i++){
                IEnum enumConstant = (IEnum)enumConstants[i];
                buffer.append(enumConstant.value());
                buffer.append(":");
                buffer.append(enumConstant.title());
                buffer.append("; ");
            }
        }
        if(StringUtils.isNotBlank(anno.extValue())){
            buffer.append(anno.extValue());
            if(StringUtils.isNotBlank(anno.extDesc())){
                buffer.append(":");
                buffer.append(anno.extDesc());
                buffer.append("; ");
            }
        }
        buffer.append(" msg: ");
        buffer.append(anno.msg());
        return buffer.toString();
    }
}
