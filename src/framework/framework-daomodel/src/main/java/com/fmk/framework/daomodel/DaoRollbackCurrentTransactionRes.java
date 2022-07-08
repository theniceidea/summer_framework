package com.fmk.framework.daomodel;


import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

public class DaoRollbackCurrentTransactionRes extends Summer<Void> implements LocalSummer {

    public static void s(){
        DaoRollbackCurrentTransactionRes model = new DaoRollbackCurrentTransactionRes();
        model.sum();
    }
}
