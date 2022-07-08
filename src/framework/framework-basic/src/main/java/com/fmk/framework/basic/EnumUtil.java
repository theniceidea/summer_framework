/*
 * Copyright 2017 Wicrenet, Inc. All rights reserved.
 */
package com.fmk.framework.basic;

import java.util.Objects;

/**
 * @author larry
 * @date 2018/12/26
 */
public class EnumUtil {
    public static <I extends IEnum, T> I valueOf(Class<I> iClass, T value) {
        for (I q : iClass.getEnumConstants()) {
            if (Objects.equals(q.value(), value)) {
                return q;
            }
        }
        return null;
    }

    public static <I extends IEnum, T> I valueOf(Class<I> iClass, T value, RuntimeException exception) {
        for (I q : iClass.getEnumConstants()) {
            if (Objects.equals(q.value(), value)) {
                return q;
            }
        }
        throw exception;
    }

    public static <I extends IEnum, T> boolean exists(Class<I> iClass, T value) {
        for (I q : iClass.getEnumConstants()) {
            if (Objects.equals(q.value(), value)) {
                return true;
            }
        }
        return false;
    }

    public static <T, I extends IEnum<T>> boolean is(I value1, T value2) {
        return Objects.equals(value1.value(), value2);
    }
    @Deprecated
    public static boolean is(Object value1, Object value2) {
        return Objects.equals(value1, value2);
    }

}
