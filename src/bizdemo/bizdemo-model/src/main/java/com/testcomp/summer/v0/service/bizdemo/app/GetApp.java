package com.testcomp.summer.v0.service.bizdemo.app;
import com.fmk.framework.annotations.*;
import com.fmk.framework.summer.BasicSummer;
import com.fmk.framework.valid.IValidator;
import com.fmk.framework.valid.IValidatorSuccess;
import com.fmk.framework.valid.FieldValid;
import com.testcomp.model0.bizdemo.app.GetAppM;

/**
* 备注1
*/
@ApiInfo("备注1")
@Publish
public class GetApp extends BasicSummer<GetAppM>{
    /**
    * 备注1
    */
    public static GetAppM s(java.lang.Integer deleteStatus, java.util.List<Object> ds) {
        GetApp summer=new GetApp();
        summer.deleteStatus=deleteStatus;
        summer.ds=ds;
        return summer.sum();
    }
    public static GetApp inst(){
        return new GetApp();
    }
    /**
    * 删除状态
    */
    private java.lang.Integer deleteStatus;
    /**
    * 删除状态
    */
    private java.util.List<Object> ds;
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
    public GetApp deleteStatus(java.lang.Integer value){
        this.deleteStatus=value;
        return this;
    }
    /**
    * 删除状态
    */
    public FieldValid<GetApp, java.lang.Integer> deleteStatus_fv(java.lang.Integer value){
        return new FieldValid<>(this, value, () -> deleteStatus(value));
    }
    /**
    * 删除状态
    */
    public GetApp deleteStatus_valid(IValidator<java.lang.Integer> validator, String msg){
        validator.valid(this.deleteStatus, msg);
        return this;
    }
    /**
    * 删除状态
    */
    public java.util.List<Object> getDs(){
        return this.ds;
    }
    /**
    * 删除状态
    */
    public void setDs(java.util.List<Object> value){
        this.ds=value;
    }
    /**
    * 删除状态
    */
    public GetApp ds(java.util.List<Object> value){
        this.ds=value;
        return this;
    }
    /**
    * 删除状态
    */
    public FieldValid<GetApp, java.util.List<Object>> ds_fv(java.util.List<Object> value){
        return new FieldValid<>(this, value, () -> ds(value));
    }
    /**
    * 删除状态
    */
    public GetApp ds_valid(IValidator<java.util.List<Object>> validator, String msg){
        validator.valid(this.ds, msg);
        return this;
    }
}
