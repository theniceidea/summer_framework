package com.fmk.framework.entitiesbasic;


import com.fmk.framework.daoannotations.Column;

import java.sql.Timestamp;

public class IdTimeEntity extends IdEntity {
    /**
     * 创建时间
     */
    @Column("creation_date")
    private Timestamp creationDate;
    public static final String _creationDate = "creation_date";

    /**
     * 创建用户
     */
    @Column("created_by")
    private String createdBy;
    public static final String _createdBy = "created_by";

    /**
     * 最后修改时间
     */
    @Column("last_update_date")
    private Timestamp lastUpdateDate;
    public static final String _lastUpdateDate = "last_update_date";

    /**
     * 最后修改用户
     */
    @Column("last_update_by")
    private String lastUpdateBy;
    public static final String _lastUpdateBy = "last_update_by";

    public Timestamp getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Timestamp creationDate) {
        this.creationDate = creationDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Timestamp getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Timestamp lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
}
