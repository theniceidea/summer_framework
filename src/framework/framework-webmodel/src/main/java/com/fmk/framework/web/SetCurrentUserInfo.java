package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;

/**
 * @author larry
 * @date 2019/2/20
 */
public class SetCurrentUserInfo extends BasicSummer<Void> {
    private String userId;
    private Boolean adminUser;

    public static void s(String userId, Boolean adminUser){
        SetCurrentUserInfo summer = new SetCurrentUserInfo();
        summer.setUserId(userId);
        summer.setAdminUser(adminUser);
        summer.sum();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(Boolean adminUser) {
        this.adminUser = adminUser;
    }
}
