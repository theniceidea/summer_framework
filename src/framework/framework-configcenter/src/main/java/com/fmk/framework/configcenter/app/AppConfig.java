package com.fmk.framework.configcenter.app;

import com.fmk.framework.configannotations.Key;
import com.fmk.framework.configannotations.Prefix;

@Prefix("appConfig")
public class AppConfig {

    @Key("allowLoginFailedCount")
    private int allowLoginFailedCount = 5;

    @Key("enableVerifyCode")
    private boolean enableVerifyCode = true;

    @Key("verifyCodeLifeTime")
    private long verifyCodeLifeTime = 120000;

    @Key("verifyCodeCookieName")
    private String verifyCodeCookieName = "shang_qi_verify";

    @Key("jwtLifeTime")
    private int jwtLifeTime = 604800;

    @Key("enableForBiz")
    private boolean enableForBiz = false;

    @Key("superUserId")
    private String superUserId = "1";

    @Key("bizDBUrlPrefix")
    private String bizDBUrlPrefix = "http://127.0.0.1:8088/cms-bizdb/v1";

    @Key("managerUrlPrefix")
    private String managerUrlPrefix = "http://127.0.0.1:8085/cms-manager/v1";

    @Key("consumeMq")
    private boolean consumeMq = false;

    public int getAllowLoginFailedCount() {
        return allowLoginFailedCount;
    }

    public void setAllowLoginFailedCount(int allowLoginFailedCount) {
        this.allowLoginFailedCount = allowLoginFailedCount;
    }

    public boolean isEnableVerifyCode() {
        return enableVerifyCode;
    }

    public void setEnableVerifyCode(boolean enableVerifyCode) {
        this.enableVerifyCode = enableVerifyCode;
    }

    public long getVerifyCodeLifeTime() {
        return verifyCodeLifeTime;
    }

    public void setVerifyCodeLifeTime(long verifyCodeLifeTime) {
        this.verifyCodeLifeTime = verifyCodeLifeTime;
    }

    public String getVerifyCodeCookieName() {
        return verifyCodeCookieName;
    }

    public void setVerifyCodeCookieName(String verifyCodeCookieName) {
        this.verifyCodeCookieName = verifyCodeCookieName;
    }

    public int getJwtLifeTime() {
        return jwtLifeTime;
    }

    public void setJwtLifeTime(int jwtLifeTime) {
        this.jwtLifeTime = jwtLifeTime;
    }

    public boolean isEnableForBiz() {
        return enableForBiz;
    }

    public void setEnableForBiz(boolean enableForBiz) {
        this.enableForBiz = enableForBiz;
    }

    public String getSuperUserId() {
        return superUserId;
    }

    public void setSuperUserId(String superUserId) {
        this.superUserId = superUserId;
    }

    public String getBizDBUrlPrefix() {
        return bizDBUrlPrefix;
    }

    public void setBizDBUrlPrefix(String bizDBUrlPrefix) {
        this.bizDBUrlPrefix = bizDBUrlPrefix;
    }

    public String getManagerUrlPrefix() {
        return managerUrlPrefix;
    }

    public void setManagerUrlPrefix(String managerUrlPrefix) {
        this.managerUrlPrefix = managerUrlPrefix;
    }

    public boolean isConsumeMq() {
        return consumeMq;
    }

    public void setConsumeMq(boolean consumeMq) {
        this.consumeMq = consumeMq;
    }
}
