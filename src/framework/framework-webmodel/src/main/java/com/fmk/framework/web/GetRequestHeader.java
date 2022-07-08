package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;

public class GetRequestHeader extends BasicSummer<String> implements LocalSummer {
    private String name;

    public static String s(String name){
        GetRequestHeader model = new GetRequestHeader();
        model.setName(name);

        return model.sum();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
