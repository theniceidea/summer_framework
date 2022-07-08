package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;

public class SetMicroServiceCall extends BasicSummer<Void> implements LocalSummer {
    private boolean microServiceCall;

    public static void s(boolean microServiceCall){
        SetMicroServiceCall model = new SetMicroServiceCall();

        model.setMicroServiceCall(microServiceCall);

        model.sum();
    }

    public boolean isMicroServiceCall() {
        return microServiceCall;
    }

    public void setMicroServiceCall(boolean microServiceCall) {
        this.microServiceCall = microServiceCall;
    }
}
