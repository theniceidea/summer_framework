package com.testcomp.summer.v0.service.bizdemo.apps;
import com.fmk.framework.annotations.*;
import com.fmk.framework.summer.BasicSummer;
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
}
