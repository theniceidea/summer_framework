package com.fmk.framework.validation;

import com.fmk.framework.exception.Excep;

public class Check<T> {
    private T value;
    private Check(T value){
        this.value=value;
    }
    public static <T> Check<T> c(T value){
        return new Check<>(value);
    }
    public T val(){
        return this.value;
    }
    public Check<T> stat(boolean bol, String msg){
        if(!bol) throw Excep.le(Check.class, msg);
        return this;
    }
}
