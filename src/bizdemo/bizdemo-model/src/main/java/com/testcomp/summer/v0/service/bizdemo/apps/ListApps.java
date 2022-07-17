package com.testcomp.summer.v0.service.bizdemo.apps;
import com.fmk.framework.annotations.*;
import com.fmk.framework.summer.BasicSummer;
import com.fmk.framework.valid.IValidator;
import com.fmk.framework.valid.IValidatorSuccess;
import java.util.List;
import com.testcomp.model0.bizdemo.apps.ListAppsM;

/**
* 备注2
*/
@ApiInfo("备注2")
@Publish
public class ListApps extends BasicSummer<List<ListAppsM>>{
    /**
    * 备注2
    */
    public static List<ListAppsM> s(java.lang.Integer deleteStatus) {
        ListApps summer=new ListApps();
        summer.deleteStatus=deleteStatus;
        return summer.sum();
    }
    public static ListApps inst(){
        return new ListApps();
    }
    /**
    * 删除标志
    */
    private java.lang.Integer deleteStatus;
    /**
    * 删除标志
    */
    public java.lang.Integer getDeleteStatus(){
        return this.deleteStatus;
    }
    /**
    * 删除标志
    */
    public void setDeleteStatus(java.lang.Integer value){
        this.deleteStatus=value;
    }
    /**
    * 删除标志
    */
    public ListApps deleteStatus(java.lang.Integer value){
        this.deleteStatus=value;
        return this;
    }
    /**
    * 删除标志
    */
    public ListApps deleteStatus(java.lang.Integer value, IValidatorSuccess<java.lang.Integer> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<java.lang.Integer> itm : ivs){
                if(!itm.isValidSuccess(value)){
                    return this;
                }
            }
        }

        this.deleteStatus=value;
        return this;
    }
    /**
    * 删除标志
    */
    public ListApps deleteStatus_valid(IValidator<java.lang.Integer> validator, String msg){
        validator.valid(this.deleteStatus, msg);
        return this;
    }
}
