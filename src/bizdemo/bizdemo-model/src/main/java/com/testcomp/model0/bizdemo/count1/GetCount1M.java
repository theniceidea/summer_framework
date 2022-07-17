package com.testcomp.model0.bizdemo.count1;
import com.fmk.framework.daoannotations.Column;

/**
* 备注1
*/
public class GetCount1M{

    /**
    * 
    */
    @Column("count1")
    private java.lang.Long count1;
    /**
    * 
    */
    public java.lang.Long getCount1(){
        return this.count1;
    }
    /**
    * 
    */
    public void setCount1(java.lang.Long value){
        this.count1=value;
    }
}
