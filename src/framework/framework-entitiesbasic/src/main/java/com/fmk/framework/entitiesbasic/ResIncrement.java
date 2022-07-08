package com.fmk.framework.entitiesbasic;


import com.fmk.framework.daoannotations.Column;
import com.fmk.framework.daoannotations.Table;

import java.sql.Timestamp;

@Table("res_increment")
public class ResIncrement extends IdEntity {
    /**
     * transactionId
     */
    @Column("transaction_id")
    private String transactionId;
    public static final String _transactionId = "transaction_id";

    /**
     * status
     */
    @Column("status")
    private int status;
    public static final String _status = "status";

    /**
     * content
     */
    @Column("content")
    private String content;
    public static final String _content = "content";

    /**
     * 创建时间
     */
    @Column("create_time")
    private Timestamp createTime;
    public static final String _createTime = "create_time";

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
}
