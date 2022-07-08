package com.fmk.framework.session;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;

import java.util.Map;


public class CurrentUserInfo extends BasicSummer<Map<String, Object>> implements LocalSummer {
    private boolean notFoundThrow;

    public static Map<String, Object> s(boolean notFoundThrow){
        CurrentUserInfo currentUserId = new CurrentUserInfo();

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
