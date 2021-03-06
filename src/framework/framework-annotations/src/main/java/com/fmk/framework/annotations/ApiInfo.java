package com.fmk.framework.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({TYPE,METHOD,FIELD})
@Retention(RUNTIME)
public @interface ApiInfo {
    String value() ;
}
