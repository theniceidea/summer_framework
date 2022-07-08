package com.fmk.framework.web;

import com.fmk.framework.summer.BasicSummer;
import org.summerframework.model.LocalSummer;

public class SetResponseHeader extends BasicSummer<Void> implements LocalSummer {
    private String name;
    private String value;

    public static void s(String name, String value){
        SetResponseHeader model = new SetResponseHeader();
        model.setName(name);
        model.setValue(value);

        model.sum();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
