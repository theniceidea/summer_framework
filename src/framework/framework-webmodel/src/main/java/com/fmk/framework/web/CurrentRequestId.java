package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;


public class CurrentRequestId extends BasicSummer<String> implements LocalSummer {
    private boolean notFoundThrow;

    public static String s(boolean notFoundThrow){
        CurrentRequestId currentRequestId = new CurrentRequestId();

        currentRequestId.notFoundThrow = notFoundThrow;

        return currentRequestId.sum();
    }

    public boolean isNotFoundThrow() {
        return notFoundThrow;
    }

    public void setNotFoundThrow(boolean notFoundThrow) {
        this.notFoundThrow = notFoundThrow;
    }
}
