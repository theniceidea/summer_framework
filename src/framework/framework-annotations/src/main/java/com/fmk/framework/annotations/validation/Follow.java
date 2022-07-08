package com.fmk.framework.annotations.validation;

import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({FIELD})
@Retention(RUNTIME)
@Repeatable(Follows.class)
public @interface Follow {
    Class<?> kls();
    String field();
    Class<? extends Annotation> anno();
}
