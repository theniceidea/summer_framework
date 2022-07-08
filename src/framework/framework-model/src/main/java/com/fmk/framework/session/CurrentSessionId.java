package com.fmk.framework.session;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;


public class CurrentSessionId extends BasicSummer<String> implements LocalSummer {
    private boolean notFoundThrow;

    public static String s(boolean notFoundThrow){
        CurrentSessionId currentSessionId = new CurrentSessionId();

        currentSessionId.notFoundThrow = notFoundThrow;

        return currentSessionId.sum();
    }

    public boolean isNotFoundThrow() {
        return notFoundThrow;
    }

    public void setNotFoundThrow(boolean notFoundThrow) {
        this.notFoundThrow = notFoundThrow;
    }
}
