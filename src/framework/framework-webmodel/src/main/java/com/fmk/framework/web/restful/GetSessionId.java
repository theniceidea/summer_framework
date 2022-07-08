package com.fmk.framework.web.restful;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;

public class GetSessionId extends BasicSummer<String> implements LocalSummer{

    public static String s(){
        GetSessionId model = new GetSessionId();
        return model.sum();
    }

}
