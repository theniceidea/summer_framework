package com.fmk.framework.aop;

import com.fmk.framework.summer.BasicSummer;

public class HasRightIdSum extends BasicSummer<Boolean> {

    public static boolean sum(String rightId){

        HasRightIdSum model = new HasRightIdSum();
        model.setRightId(rightId);

        return model.sum();
    }


    private String rightId;

    public String getRightId() {
        return rightId;
    }

    public void setRightId(String rightId) {
        this.rightId = rightId;
    }
}
