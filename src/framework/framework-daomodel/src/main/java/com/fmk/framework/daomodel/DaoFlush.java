package com.fmk.framework.daomodel;


import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

public class DaoFlush extends Summer<Void> implements LocalSummer{

    public static void s(){
        DaoFlush model = new DaoFlush();
        model.sum();
    }

}
