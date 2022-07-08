package com.fmk.framework.daomodel;

import org.summerframework.model.LocalSummer;
import org.summerframework.model.Summer;

public class DaoOne extends Summer<Object> implements LocalSummer{
    private Class<?> kls;
    private Object key;
    private String onNullThrowErrMsg;
//    private boolean selfTransactionSnapshot = true;
//    private String updateConflictsErrMsg;

    public static <T> T s(Class<T> kls, Object key, String onNullThrowErrMsg){
        DaoOne model = new DaoOne();
        model.setKls(kls);
        model.setKey(key);
        model.setOnNullThrowErrMsg(onNullThrowErrMsg);
        model.sum();
        return (T) model.getSummerResult();
    }
//    public static <T> T s(Class<T> kls, Object key, String onNullThrowErrMsg, boolean selfTransactionSnapshot, String updateConflictsErrMsg){
//        DaoOne model = new DaoOne();
//        model.setKls(kls);
//        model.setKey(key);
//        model.setOnNullThrowErrMsg(onNullThrowErrMsg);
//        model.setSelfTransactionSnapshot(selfTransactionSnapshot);
//        model.setUpdateConflictsErrMsg(updateConflictsErrMsg);
//        model.sum();
//        return (T) model.getSummerResult();
//    }

    public Class<?> getKls() {
        return kls;
    }

    public void setKls(Class<?> kls) {
        this.kls = kls;
    }

    public Object getKey() {
        return key;
    }

    public void setKey(Object key) {
        this.key = key;
    }

    public String getOnNullThrowErrMsg() {
        return onNullThrowErrMsg;
    }

    public void setOnNullThrowErrMsg(String onNullThrowErrMsg) {
        this.onNullThrowErrMsg = onNullThrowErrMsg;
    }

//    public boolean isSelfTransactionSnapshot() {
//        return selfTransactionSnapshot;
//    }
//
//    public void setSelfTransactionSnapshot(boolean selfTransactionSnapshot) {
//        this.selfTransactionSnapshot = selfTransactionSnapshot;
//    }
//
//    public String getUpdateConflictsErrMsg() {
//        return updateConflictsErrMsg;
//    }
//
//    public void setUpdateConflictsErrMsg(String updateConflictsErrMsg) {
//        this.updateConflictsErrMsg = updateConflictsErrMsg;
//    }
}
