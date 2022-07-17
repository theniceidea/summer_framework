package com.fmk.framework.valid;

public interface IValidator<T> {
    void valid(T value, String msg);
}
