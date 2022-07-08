package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;

/**
 * @author larry
 * @date 2019/2/20
 */
public class SetCurrentRequestNumber extends BasicSummer<Void> implements LocalSummer {
    private int requestNumber;

    public static void s(int requestNumber){
        SetCurrentRequestNumber summer = new SetCurrentRequestNumber();
        summer.setRequestNumber(requestNumber);
        summer.sum();
    }

    public int getRequestNumber() {
        return requestNumber;
    }

    public void setRequestNumber(int requestNumber) {
        this.requestNumber = requestNumber;
    }
}
