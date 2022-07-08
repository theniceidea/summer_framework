package com.fmk.framework.basic;

import com.google.common.base.Objects;

/**
 * Created by jerry on 17-6-30.
 */
public interface IEnum<T> {
    T value();
    String title();

    default boolean isValue(T t) {
        return Objects.equal(t, value());
    }
}
