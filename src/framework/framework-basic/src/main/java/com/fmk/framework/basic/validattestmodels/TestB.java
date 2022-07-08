package com.fmk.framework.basic.validattestmodels;

import com.fmk.framework.annotations.validation.NotBlank;
import com.fmk.framework.annotations.validation.Size;

public class TestB {

    @NotBlank(msg = "000000000025_请输入testb field1")
    private String field1;
    public static final String _field1 = "field1";

    @Size(min=0, max=100, msg = "000000000026_请输入testb field2")
    private int field2;

    public String getField1() {
        return field1;
    }

    public void setField1(String field1) {
        this.field1 = field1;
    }

    public int getField2() {
        return field2;
    }

    public void setField2(int field2) {
        this.field2 = field2;
    }
}
