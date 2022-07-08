package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;

public class IsMicroServiceCall extends BasicSummer<Boolean> implements LocalSummer {
    public static boolean s(){
        IsMicroServiceCall model = new IsMicroServiceCall();

        return model.sum();
    }
}
