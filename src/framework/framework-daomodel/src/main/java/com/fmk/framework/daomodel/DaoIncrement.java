package com.fmk.framework.daomodel;


import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

public class DaoIncrement extends Summer<Integer> implements LocalSummer {
    private IncrementUpdate incrementUpdate;

    public static Integer s(IncrementUpdate incrementUpdate){
        DaoIncrement model = new DaoIncrement();
        model.setIncrementUpdate(incrementUpdate);
        model.sum();
        return model.getSummerResult();
    }

    public IncrementUpdate getIncrementUpdate() {
        return incrementUpdate;
    }

    public void setIncrementUpdate(IncrementUpdate incrementUpdate) {
        this.incrementUpdate = incrementUpdate;
    }
}
