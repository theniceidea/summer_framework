package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;


public class SetRequestDeployVersion extends BasicSummer<Void> implements LocalSummer {
    private String requestVersion;

    public static void s(String requestVersion){
        SetRequestDeployVersion model = new SetRequestDeployVersion();
        model.setRequestVersion(requestVersion);

        model.sum();
    }

    public String getRequestVersion() {
        return requestVersion;
    }

    public void setRequestVersion(String requestVersion) {
        this.requestVersion = requestVersion;
    }
}
