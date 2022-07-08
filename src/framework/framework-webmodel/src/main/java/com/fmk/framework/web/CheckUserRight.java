package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;

/**
 * @author larry
 * @date 2019/2/20
 */
public class CheckUserRight extends BasicSummer<Void> {
    private String hasAuthority;
    private String userId;

    public static void s(String hasAuthority, String userId) {
        CheckUserRight summer = new CheckUserRight();

        summer.setHasAuthority(hasAuthority);
        summer.setUserId(userId);

        summer.sum();
    }

    public String getHasAuthority() {
        return hasAuthority;
    }

    public void setHasAuthority(String hasAuthority) {
        this.hasAuthority = hasAuthority;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
