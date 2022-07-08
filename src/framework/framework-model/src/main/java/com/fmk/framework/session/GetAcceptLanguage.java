package com.fmk.framework.session;

import com.fmk.framework.summer.BasicSummer;

public class GetAcceptLanguage extends BasicSummer<String> {
    public static String s(){
        GetAcceptLanguage model = new GetAcceptLanguage();

        return model.sum();
    }
}
