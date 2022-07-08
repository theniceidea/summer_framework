package com.fmk.framework.session;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;


public class SetCurrentUserId extends BasicSummer<Void> implements LocalSummer {
    private String userId;

    public static void s(String userId){
        SetCurrentUserId currentUserId = new SetCurrentUserId();
        currentUserId.setUserId(userId);

        currentUserId.sum();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
