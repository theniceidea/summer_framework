package com.testcomp.summer.v0.service.bizdemo.ds2app;
import com.fmk.framework.annotations.*;
import com.fmk.framework.summer.BasicSummer;
import com.testcomp.model0.bizdemo.ds2app.GetDs2AppM;

/**
* 备注1
*/
@ApiInfo("备注1")
@Publish
public class GetDs2App extends BasicSummer<GetDs2AppM>{
     /**
     * 备注1
     */
     public static GetDs2AppM s(java.lang.Integer deleteStatus, java.util.List<Object> ds) {
          GetDs2App summer=new GetDs2App();
          summer.deleteStatus=deleteStatus;
          summer.ds=ds;
          return summer.sum();
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
     public java.util.List<Object> getDs(){
          return this.ds;
     }
     /**
     * 删除状态
     */
     public void setDs(java.util.List<Object> value){
          this.ds=value;
     }
}
