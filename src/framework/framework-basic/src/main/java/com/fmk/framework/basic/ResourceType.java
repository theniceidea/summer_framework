package com.fmk.framework.basic;


public enum ResourceType implements IEnum<Integer> {
    PAYMENT(1,"外部导入支付数据"),
    ORDER(2,"外部导入订单数据"),
    USER(3,"外部导入用户数据");

    private Integer value;
    private String title;

    ResourceType(Integer value, String title) {
        this.value = value;
        this.title = title;
    }

    @Override
    public Integer value() {
        return value;
    }

    @Override
    public String title() {
        return title;
    }
}
