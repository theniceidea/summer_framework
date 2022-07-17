package com.testcomp.summer.v0.service.bizdemo.count1;
import com.fmk.framework.annotations.*;
import com.fmk.framework.summer.BasicSummer;
import com.fmk.framework.valid.IValidator;
import com.fmk.framework.valid.IValidatorSuccess;
import com.fmk.framework.valid.FieldValid;
import com.testcomp.model0.bizdemo.count1.GetCount1M;

/**
* 备注1
*/
@ApiInfo("备注1")
public class GetCount1 extends BasicSummer<GetCount1M>{
    /**
    * 备注1
    */
    public static GetCount1M s(java.lang.Integer deleteStatus) {
        GetCount1 summer=new GetCount1();
        summer.deleteStatus=deleteStatus;
        return summer.sum();
    }
    public static GetCount1 inst(){
        return new GetCount1();
    }
    /**
    * 删除状态
    */
    private java.lang.Integer deleteStatus;
    /**
    * 删除状态
    */
    public java.lang.Integer getDeleteStatus(){
        return this.deleteStatus;
    }
    /**
    * 删除状态
    */
    public void setDeleteStatus(java.lang.Integer value){
        this.deleteStatus=value;
    }
    /**
    * 删除状态
    */
    public GetCount1 deleteStatus(java.lang.Integer value){
        this.deleteStatus=value;
        return this;
    }
    /**
    * 删除状态
    */
    public FieldValid<GetCount1, java.lang.Integer> deleteStatus_fv(java.lang.Integer value){
        return new FieldValid<>(this, value, () -> deleteStatus(value));
    }
    /**
    * 删除状态
    */
    public GetCount1 deleteStatus_valid(IValidator<java.lang.Integer> validator, String msg){
        validator.valid(this.deleteStatus, msg);
        return this;
    }
}
