package com.fmk.framework.permission.entities;

import com.fmk.framework.basic.IEnum;

public enum UserIsAgree implements IEnum<Integer> {

    AGREE(1, "同意"),
    NOT_AGREE(0, "不同意");

    private int value;
    private String title;

    UserIsAgree(int value, String title) {
        this.value = value;
        this.title = title;
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public String title() {
        return this.title;
    }

    public int getValue() {
        return value;
    }

    public String getTitle() {
        return title;
    }

}
