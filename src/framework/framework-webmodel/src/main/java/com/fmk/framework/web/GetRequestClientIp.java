package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;

public class GetRequestClientIp extends BasicSummer<String> implements LocalSummer {

    public static String s(){
        GetRequestClientIp model = new GetRequestClientIp();

        return model.sum();
    }

}
