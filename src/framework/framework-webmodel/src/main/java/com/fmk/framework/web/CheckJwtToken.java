package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;

/**
 * @author larry
 * @date 2019-05-28
 */
public class CheckJwtToken extends BasicSummer<Void> {
    private String token;
    private String userId;

    public static void s(String token,String userId) {
        CheckJwtToken summer = new CheckJwtToken();
        summer.setToken(token);
        summer.setUserId(userId);
        summer.sum();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
