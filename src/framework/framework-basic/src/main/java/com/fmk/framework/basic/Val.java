package com.fmk.framework.basic;

public class Val<T> {
    private T value;

    public static <T> Val<T> NewVal(T value){
        return new Val<>(value);
    }

    public Val(T value){
        this.value = value;
    }


    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }
}
