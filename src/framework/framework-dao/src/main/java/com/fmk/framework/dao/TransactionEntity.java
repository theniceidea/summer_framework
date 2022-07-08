package com.fmk.framework.dao;

import com.alibaba.fastjson.JSON;
import com.fmk.framework.entitiesbasic.IdEntity;

public class TransactionEntity {
    private String transactionId;
    /**
     * 0 insert 1 update 2 delete 3 lock
     */
    private int type;
    private String entityId;
    private String entity;
    public TransactionEntity(){}
    public TransactionEntity(String transactionId, int type, IdEntity entity){
        this.transactionId = transactionId;
        this.type = type;
        this.entityId = entity.getId();
        this.entity = JSON.toJSONString(entity);
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public String getEntityId() {
        return entityId;
    }

    public void setEntityId(String entityId) {
        this.entityId = entityId;
    }
}
