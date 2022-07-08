package com.fmk.framework.daomodel;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;

public class CurrentTransactionId extends BasicSummer<String> implements LocalSummer{
    private boolean notFoundThrow;

    public static String s(boolean notFoundThrow){
        CurrentTransactionId currentTransactionId = new CurrentTransactionId();

        currentTransactionId.notFoundThrow = notFoundThrow;

        return currentTransactionId.sum();
    }

    public boolean isNotFoundThrow() {
        return notFoundThrow;
    }

    public void setNotFoundThrow(boolean notFoundThrow) {
        this.notFoundThrow = notFoundThrow;
    }

}
