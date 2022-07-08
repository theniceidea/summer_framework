package com.fmk.framework.daomodel;


import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

public class DaoRollbackByTranId extends Summer<Void> implements LocalSummer {

    private String tranId;

    public static void s(String tranId){
        DaoRollbackByTranId model = new DaoRollbackByTranId();

        model.setTranId(tranId);

        model.sum();
    }

    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }
}
