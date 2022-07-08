package com.fmk.framework.session;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;


public class CurrentUserId extends BasicSummer<String> implements LocalSummer {
    private boolean notFoundThrow;

    public static String s(boolean notFoundThrow){
        CurrentUserId currentUserId = new CurrentUserId();

        currentUserId.notFoundThrow = notFoundThrow;

        return currentUserId.sum();
    }

    public boolean isNotFoundThrow() {
        return notFoundThrow;
    }

    public void setNotFoundThrow(boolean notFoundThrow) {
        this.notFoundThrow = notFoundThrow;
    }
}
