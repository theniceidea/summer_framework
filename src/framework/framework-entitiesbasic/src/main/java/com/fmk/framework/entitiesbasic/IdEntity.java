package com.fmk.framework.entitiesbasic;


import com.fmk.framework.annotations.SkipDoc;
import com.fmk.framework.daoannotations.Column;

public class IdEntity {

    @SkipDoc
    private transient boolean forUpdate;
    /**
     * id
     */
    @Column("id")
    private String id;
    public static final String _id = "id";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
