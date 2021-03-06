package com.fmk.framework.configannotations;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target(ElementType.FIELD)
@Retention(RUNTIME)
public @interface Key {
    String value() ;
}
