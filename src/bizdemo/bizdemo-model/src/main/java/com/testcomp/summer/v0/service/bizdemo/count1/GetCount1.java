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
    public static GetCount1M s(java.lang.Integer deleteStatus, java.lang.String param2) {
        GetCount1 summer=new GetCount1();
        summer.deleteStatus=deleteStatus;
        summer.param2=param2;
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
    * 
    */
    private java.lang.String param2;
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
    /**
    * 
    */
    public java.lang.String getParam2(){
        return this.param2;
    }
    /**
    * 
    */
    public void setParam2(java.lang.String value){
        this.param2=value;
    }
    /**
    * 
    */
    public GetCount1 param2(java.lang.String value){
        this.param2=value;
        return this;
    }
    /**
    * 
    */
    public FieldValid<GetCount1, java.lang.String> param2_fv(java.lang.String value){
        return new FieldValid<>(this, value, () -> param2(value));
    }
    /**
    * 
    */
    public GetCount1 param2_valid(IValidator<java.lang.String> validator, String msg){
        validator.valid(this.param2, msg);
        return this;
    }
}
