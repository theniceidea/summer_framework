package com.testcomp.model0.bizdemo.apps;
import com.fmk.framework.daoannotations.Column;

/**
* 备注2
*/
public class ListAppsM{

     /**
     * demotable.id demo table.id
     */
     @Column("id")
     private java.lang.Integer id;

     /**
     * demotable.status demo table.status
     */
     @Column("status")
     private java.lang.Integer status;

     /**
     * demotable.num demo table.数量
     */
     @Column("num")
     private java.lang.Integer num;

     /**
     * demotable.num2 demo table.数量2
     */
     @Column("num2")
     private java.math.BigDecimal num2;
     /**
     * demotable.id demo table.id
     */
     public java.lang.Integer getId(){
          return this.id;
     }
     /**
     * demotable.id demo table.id
     */
     public void setId(java.lang.Integer value){
          this.id=value;
     }
     /**
     * demotable.status demo table.status
     */
     public java.lang.Integer getStatus(){
          return this.status;
     }
     /**
     * demotable.status demo table.status
     */
     public void setStatus(java.lang.Integer value){
          this.status=value;
     }
     /**
     * demotable.num demo table.数量
     */
     public java.lang.Integer getNum(){
          return this.num;
     }
     /**
     * demotable.num demo table.数量
     */
     public void setNum(java.lang.Integer value){
          this.num=value;
     }
     /**
     * demotable.num2 demo table.数量2
     */
     public java.math.BigDecimal getNum2(){
          return this.num2;
     }
     /**
     * demotable.num2 demo table.数量2
     */
     public void setNum2(java.math.BigDecimal value){
          this.num2=value;
     }
}
