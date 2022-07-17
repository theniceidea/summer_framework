package com.testcomp.summer.v0.service.bizdemo.apps2;
import com.fmk.framework.annotations.*;
import com.fmk.framework.summer.BasicSummer;
import com.fmk.framework.valid.IValidator;
import com.fmk.framework.restful.PageResultList;
import com.testcomp.model0.bizdemo.apps2.ListApps2M;

/**
* 备注3
*/
@ApiInfo("备注3")
@Publish
public class ListApps2 extends BasicSummer<PageResultList<ListApps2M>>{
    /**
    * 备注3
    */
    public static PageResultList<ListApps2M> s(java.lang.Integer deleteStatus, java.util.Set<Object> deleteStatus2, int start, int limit) {
        ListApps2 summer=new ListApps2();
        summer.deleteStatus=deleteStatus;
        summer.deleteStatus2=deleteStatus2;
        summer.start=start;
        summer.limit=limit;
        return summer.sum();
    }
    /**
    * start 分页查询
    */
    private int start;
    /**
    * limit 分页查询
    */
    private int limit;
    public static ListApps2 inst(){
        return new ListApps2();
    }
    /**
    * 
    */
    private java.lang.Integer deleteStatus;
    /**
    * 
    */
    private java.util.Set<Object> deleteStatus2;
    /**
    * start 分页查询
    */
    public int getStart(){
        return this.start;
    }
    /**
    * start 分页查询
    */
    public void setStart(int value){
        this.start=value;
    }
    /**
    * start 分页查询
    */
    public ListApps2 start(int value){
        this.start=value;
        return this;
    }
    /**
    * limit 分页查询
    */
    public int getLimit(){
        return this.limit;
    }
    /**
    * limit 分页查询
    */
    public void setLimit(int value){
        this.limit=value;
    }
    /**
    * limit 分页查询
    */
    public ListApps2 limit(int value){
        this.limit=value;
        return this;
    }
    /**
    * 
    */
    public java.lang.Integer getDeleteStatus(){
        return this.deleteStatus;
    }
    /**
    * 
    */
    public void setDeleteStatus(java.lang.Integer value){
        this.deleteStatus=value;
    }
    /**
    * 
    */
    public ListApps2 deleteStatus(java.lang.Integer value){
        this.deleteStatus=value;
        return this;
    }
    /**
    * 
    */
    public ListApps2 deleteStatus_valid(IValidator<java.lang.Integer> validator, String msg){
        validator.valid(this.deleteStatus, msg);
        return this;
    }
    /**
    * 
    */
    public java.util.Set<Object> getDeleteStatus2(){
        return this.deleteStatus2;
    }
    /**
    * 
    */
    public void setDeleteStatus2(java.util.Set<Object> value){
        this.deleteStatus2=value;
    }
    /**
    * 
    */
    public ListApps2 deleteStatus2(java.util.Set<Object> value){
        this.deleteStatus2=value;
        return this;
    }
    /**
    * 
    */
    public ListApps2 deleteStatus2_valid(IValidator<java.util.Set<Object>> validator, String msg){
        validator.valid(this.deleteStatus2, msg);
        return this;
    }
}
