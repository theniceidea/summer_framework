package com.fmk.framework.restful;

public interface Converter<S, T> {
    T convert(S var1);
}