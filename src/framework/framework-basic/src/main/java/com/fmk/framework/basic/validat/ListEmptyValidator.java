package com.fmk.framework.basic.validat;

import com.fmk.framework.annotations.validation.ListEmpty;
import com.fmk.framework.basic.ReflectUtil;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.Map;

public class ListEmptyValidator implements ValidatorItem {

    private ListEmpty anno;

    private Field field;

    @Override
    public Annotation getAnnotation() {
        return anno;
    }

    @Override
    public void setAnnotation(Annotation annotation) {
        if(annotation instanceof ListEmpty){
            this.anno = (ListEmpty) annotation;
            return;
        }
        throw new IllegalArgumentException("annotation type must be ListEmpty");
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
        Object value = ReflectUtil.fieldValueNoThrow(field, bean);
        if(Collection.class.isAssignableFrom(field.getType())){
            return CollectionUtils.isEmpty((Collection<?>) value);
        }else if(Map.class.isAssignableFrom(field.getType())){
            return null == value || ((Map)value).isEmpty();
        }
        return false;
    }

    @Override
    public String apiInfo() {
        return "msg: "+anno.msg();
    }
}
