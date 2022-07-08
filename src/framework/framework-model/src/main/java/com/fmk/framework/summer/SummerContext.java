package com.fmk.framework.summer;

public class SummerContext {
    private String transactionId;
    private String currentUserId;
    private Object currentUser;

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getCurrentUserId() {
        return currentUserId;
    }

    public void setCurrentUserId(String currentUserId) {
        this.currentUserId = currentUserId;
    }

    public Object getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(Object currentUser) {
        this.currentUser = currentUser;
    }
}
