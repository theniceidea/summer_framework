package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;

/**
 * @author larry
 * @date 2019-03-21
 */
public class CheckUserIsAgree extends BasicSummer<Void> {
    private String agreeUri;
    private String userId;

    public static void s(String agreeUri, String userId) {
        CheckUserIsAgree summer = new CheckUserIsAgree();

        summer.setAgreeUri(agreeUri);
        summer.setUserId(userId);

        summer.sum();
    }

    public String getAgreeUri() {
        return agreeUri;
    }

    public void setAgreeUri(String agreeUri) {
        this.agreeUri = agreeUri;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
