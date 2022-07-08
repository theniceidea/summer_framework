package com.fmk.framework.daomodel;


import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

public class DaoCommitCurrentTransaction extends Summer<Void> implements LocalSummer {

    public static void s(){
        DaoCommitCurrentTransaction model = new DaoCommitCurrentTransaction();
        model.sum();
    }
}
