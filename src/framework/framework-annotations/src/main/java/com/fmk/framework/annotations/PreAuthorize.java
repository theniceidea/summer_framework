package com.fmk.framework.annotations;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Documented
@Target({METHOD, TYPE})
@Retention(RUNTIME)
public @interface PreAuthorize {
    String value() default "";

    String name();
}