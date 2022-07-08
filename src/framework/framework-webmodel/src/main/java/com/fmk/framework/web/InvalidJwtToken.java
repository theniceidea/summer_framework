package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;

/**
 * @author larry
 * @date 2019-05-28
 */
public class InvalidJwtToken extends BasicSummer<Void> {
    private String token;

    public static void s(String token) {
        InvalidJwtToken summer = new InvalidJwtToken();
        summer.setToken(token);
        summer.sum();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
