package com.testcomp.model0.bizdemo.ds2app;
import com.fmk.framework.daoannotations.Column;

/**
* 备注1
*/
public class GetDs2AppM{

    /**
    * ds2demotable.id demo table.id
    */
    @Column("abc")
    private java.lang.Integer abc;

    /**
    * ds2demotable.status demo table.status
    */
    @Column("status")
    private java.lang.Integer status;

    /**
    * ds2demotable.num demo table.数量
    */
    @Column("num")
    private java.lang.Integer num;

    /**
    * ds2demotable.num2 demo table.数量2
    */
    @Column("num2")
    private java.math.BigDecimal num2;

    /**
    * ds2demotable.delete_status demo table.delete status
    */
    @Column("delete_status")
    private java.lang.Integer deleteStatus;
    /**
    * ds2demotable.id demo table.id
    */
    public java.lang.Integer getAbc(){
        return this.abc;
    }
    /**
    * ds2demotable.id demo table.id
    */
    public void setAbc(java.lang.Integer value){
        this.abc=value;
    }
    /**
    * ds2demotable.status demo table.status
    */
    public java.lang.Integer getStatus(){
        return this.status;
    }
    /**
    * ds2demotable.status demo table.status
    */
    public void setStatus(java.lang.Integer value){
        this.status=value;
    }
    /**
    * ds2demotable.num demo table.数量
    */
    public java.lang.Integer getNum(){
        return this.num;
    }
    /**
    * ds2demotable.num demo table.数量
    */
    public void setNum(java.lang.Integer value){
        this.num=value;
    }
    /**
    * ds2demotable.num2 demo table.数量2
    */
    public java.math.BigDecimal getNum2(){
        return this.num2;
    }
    /**
    * ds2demotable.num2 demo table.数量2
    */
    public void setNum2(java.math.BigDecimal value){
        this.num2=value;
    }
    /**
    * ds2demotable.delete_status demo table.delete status
    */
    public java.lang.Integer getDeleteStatus(){
        return this.deleteStatus;
    }
    /**
    * ds2demotable.delete_status demo table.delete status
    */
    public void setDeleteStatus(java.lang.Integer value){
        this.deleteStatus=value;
    }
}
