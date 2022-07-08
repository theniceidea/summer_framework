package com.fmk.framework.basic.validat;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

public interface ValidatorItem {

    Annotation getAnnotation();
    void setAnnotation(Annotation annotation);

    Field getField();
    void setField(Field field);

    String getFailedMsg();

    boolean validation(Object bean);

    String apiInfo();
}
