package com.fmk.framework.permission.entities;

import com.fmk.framework.basic.IEnum;

/**
 *  小程序注册用户类型
 */
public enum UserType implements IEnum<Integer> {
    /**
     * GENERALUSER   1 非会员
     * MEMBER_1888   2 老微信和小程序用户（含1888已下定用户）
     * MEMBER_VIP    3 会员
     * MEMBER_APP    4 老app用户
     */
    GENERALUSER(1,"普通用户"),
    MEMBER_1888(2,"1888"),
    MEMBER_VIP(3,"VIP"),
    MEMBER_APP(4,"APP");

    private int value;
    private String title;

    UserType(int value, String title) {
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
}
