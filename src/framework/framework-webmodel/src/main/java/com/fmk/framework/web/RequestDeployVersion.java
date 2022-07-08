package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;


public class RequestDeployVersion extends BasicSummer<String> implements LocalSummer {

    public static String s(){
        RequestDeployVersion model = new RequestDeployVersion();

        return model.sum();
    }
}
