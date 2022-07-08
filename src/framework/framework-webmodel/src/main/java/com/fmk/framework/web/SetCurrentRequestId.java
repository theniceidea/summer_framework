package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;

/**
 * @author larry
 * @date 2019/2/20
 */
public class SetCurrentRequestId extends BasicSummer<Void> implements LocalSummer {
    private String requestId;

    public static void s(String requestId){
        SetCurrentRequestId summer = new SetCurrentRequestId();
        summer.setRequestId(requestId);
        summer.sum();
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }
}
