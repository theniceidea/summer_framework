package com.fmk.framework.daoannotations;

import java.math.BigInteger;

public class IdModel {

    @SelectField(name = "id")
    private BigInteger id;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
}
