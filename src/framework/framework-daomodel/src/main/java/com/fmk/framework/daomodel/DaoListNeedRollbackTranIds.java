package com.fmk.framework.daomodel;


import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

import java.util.List;

public class DaoListNeedRollbackTranIds extends Summer<List<String>> implements LocalSummer {

    private int limit;

    public static List<String> s(int limit){
        DaoListNeedRollbackTranIds model = new DaoListNeedRollbackTranIds();

        model.setLimit(limit);

        return model.sum();
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
