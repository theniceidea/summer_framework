package com.testcomp.entities0.bizdemo.summer_dev;

import cn.hutool.core.util.StrUtil;
import com.fmk.framework.daoannotations.*;
import com.fmk.framework.entitiesbasic.Entity;
import com.fmk.framework.annotations.validation.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Objects;
import java.sql.Date;

/**
* demotable3
*/
@Table("f_demotable3")
public class Demotable3 extends Entity{
//    entity.setId();
//    entity.setTitle();
//    entity.setType();
//    entity.setNum();
//    entity.setNum2();
//    entity.setNum3();
//    entity.setDemotable2Id();
//    entity.setDeleteStatus();
//    entity.setEnableStatus();
//    entity.setStatus();
//    entity.setStatus2();
//    entity.setCreationDate();
//    entity.setCreationDate2();
//    entity
//        .id()
//        .title()
//        .type()
//        .num()
//        .num2()
//        .num3()
//        .demotable2Id()
//        .deleteStatus()
//        .enableStatus()
//        .status()
//        .status2()
//        .creationDate()
//        .creationDate2()
	/**
	* id
	*/
	@SkipInsert
	@SkipUpdate
	@IdColumn
	@UniqueColumn
	@AutoIncrement
	@NotNull(msg="Err_000300030001")
	@Column("id")
	private Integer id;
	public static final String _id="id";
	/**
	* 标题
	*/
	@LengthRange(msg="Err_000300030001", min=0, max=10)
	@Column("title")
	private String title;
	public static final String _title="title";
	/**
	* 类型
	*/
	@Column("type")
	private String type;
	public static final String _type="type";
	/**
	* 数量
	*/
	@Increment
	@Column("num")
	private Integer num;
	public static final String _num="num";
	/**
	* 数量2
	*/
	@Increment
	@Column("num2")
	private BigDecimal num2;
	public static final String _num2="num2";
	/**
	* 数量3
	*/
	@Increment
	@Column("num3")
	private Double num3;
	public static final String _num3="num3";
	/**
	* demotable2
	*/
	@Column("demotable2_id")
	private Integer demotable2Id;
	public static final String _demotable2Id="demotable2_id";
	/**
	* delete status
	*/
	@Column("delete_status")
	private Integer deleteStatus;
	public static final String _deleteStatus="delete_status";
	/**
	* enable status
	*/
	@Column("enable_status")
	private String enableStatus;
	public static final String _enableStatus="enable_status";
	/**
	* status
	*/
	@Column("status")
	private Integer status;
	public static final String _status="status";
	/**
	* status
	*/
	@Column("status2")
	private String status2;
	public static final String _status2="status2";
	/**
	* 创建时间
	*/
	@SkipInsert
	@SkipUpdate
	@Column("creation_date")
	private Timestamp creationDate;
	public static final String _creationDate="creation_date";
	/**
	* 创建时间2
	*/
	@SkipInsert
	@SkipUpdate
	@Column("creation_date2")
	private Timestamp creationDate2;
	public static final String _creationDate2="creation_date2";
    /**
    * id
    */
    public Integer getId(){
        return this.id;
    }
    /**
    * id
    */
    public void setId(Integer value){
        this.id=value;
    }
    /**
    * id
    */
    public boolean id_is(Integer value){
        return Objects.equals(this.id, value);
    }
    /**
    * id
    */
    public Demotable3 id(Integer value){
        this.id=value;
        return this;
    }
    /**
    * id
    */
    public boolean id_isNull(){
        return null==this.id;
    }

    /**
    * 标题
    */
    public String getTitle(){
        return this.title;
    }
    /**
    * 标题
    */
    public void setTitle(String value){
        this.title=value;
    }
    /**
    * 标题
    */
    public boolean title_is(String value){
        return Objects.equals(this.title, value);
    }
    /**
    * 标题
    */
    public Demotable3 title(String value){
        this.title=value;
        return this;
    }
    /**
    * 标题
    */
    public boolean title_isNull(){
        return null==this.title;
    }
    /**
    * 标题
    */
    public boolean title_isBlank(){
        return StrUtil.isBlank(this.title);
    }

    /**
    * 类型
    */
    public String getType(){
        return this.type;
    }
    /**
    * 类型
    */
    public void setType(String value){
        this.type=value;
    }
    /**
    * 类型
    */
    public boolean type_is(String value){
        return Objects.equals(this.type, value);
    }
    /**
    * 类型
    */
    public Demotable3 type(String value){
        this.type=value;
        return this;
    }
    /**
    * 类型
    */
    public boolean type_isNull(){
        return null==this.type;
    }
    /**
    * 类型
    */
    public boolean type_isBlank(){
        return StrUtil.isBlank(this.type);
    }

    /**
    * 数量
    */
    public Integer getNum(){
        return this.num;
    }
    /**
    * 数量
    */
    public void setNum(Integer value){
        this.num=value;
    }
    /**
    * 数量
    */
    public boolean num_is(Integer value){
        return Objects.equals(this.num, value);
    }
    /**
    * 数量
    */
    public Demotable3 num(Integer value){
        this.num=value;
        return this;
    }
    /**
    * 数量
    */
    public boolean num_isNull(){
        return null==this.num;
    }

    /**
    * 数量2
    */
    public BigDecimal getNum2(){
        return this.num2;
    }
    /**
    * 数量2
    */
    public void setNum2(BigDecimal value){
        this.num2=value;
    }
    /**
    * 数量2
    */
    public boolean num2_is(BigDecimal value){
        return Objects.equals(this.num2, value);
    }
    /**
    * 数量2
    */
    public Demotable3 num2(BigDecimal value){
        this.num2=value;
        return this;
    }
    /**
    * 数量2
    */
    public boolean num2_isNull(){
        return null==this.num2;
    }

    /**
    * 数量3
    */
    public Double getNum3(){
        return this.num3;
    }
    /**
    * 数量3
    */
    public void setNum3(Double value){
        this.num3=value;
    }
    /**
    * 数量3
    */
    public boolean num3_is(Double value){
        return Objects.equals(this.num3, value);
    }
    /**
    * 数量3
    */
    public Demotable3 num3(Double value){
        this.num3=value;
        return this;
    }
    /**
    * 数量3
    */
    public boolean num3_isNull(){
        return null==this.num3;
    }

    /**
    * demotable2
    */
    public Integer getDemotable2Id(){
        return this.demotable2Id;
    }
    /**
    * demotable2
    */
    public void setDemotable2Id(Integer value){
        this.demotable2Id=value;
    }
    /**
    * demotable2
    */
    public boolean demotable2Id_is(Integer value){
        return Objects.equals(this.demotable2Id, value);
    }
    /**
    * demotable2
    */
    public Demotable3 demotable2Id(Integer value){
        this.demotable2Id=value;
        return this;
    }
    /**
    * demotable2
    */
    public boolean demotable2Id_isNull(){
        return null==this.demotable2Id;
    }

    /**
    * delete status
    */
    public Integer getDeleteStatus(){
        return this.deleteStatus;
    }
    /**
    * delete status
    */
    public void setDeleteStatus(Integer value){
        this.deleteStatus=value;
    }
    /**
    * delete status
    */
    public boolean deleteStatus_is(Integer value){
        return Objects.equals(this.deleteStatus, value);
    }
    /**
    * delete status
    */
    public Demotable3 deleteStatus(Integer value){
        this.deleteStatus=value;
        return this;
    }
    /**
    * delete status 删除
    */
    public void setDeleteStatus_deleted(){
        this.deleteStatus=1;
    }
    /**
    * delete status 删除
    */
    public Demotable3 deleteStatus_deleted(){
        this.deleteStatus=1;
        return this;
    }
    /**
    * delete status 删除
    */
    public boolean deleteStatus_is_deleted(){
        return Objects.equals(this.deleteStatus, 1);
    }
    /**
    * delete status 未删除
    */
    public void setDeleteStatus_unDeleted(){
        this.deleteStatus=2;
    }
    /**
    * delete status 未删除
    */
    public Demotable3 deleteStatus_unDeleted(){
        this.deleteStatus=2;
        return this;
    }
    /**
    * delete status 未删除
    */
    public boolean deleteStatus_is_unDeleted(){
        return Objects.equals(this.deleteStatus, 2);
    }
    /**
    * delete status
    */
    public boolean deleteStatus_isNull(){
        return null==this.deleteStatus;
    }

    /**
    * enable status
    */
    public String getEnableStatus(){
        return this.enableStatus;
    }
    /**
    * enable status
    */
    public void setEnableStatus(String value){
        this.enableStatus=value;
    }
    /**
    * enable status
    */
    public boolean enableStatus_is(String value){
        return Objects.equals(this.enableStatus, value);
    }
    /**
    * enable status
    */
    public Demotable3 enableStatus(String value){
        this.enableStatus=value;
        return this;
    }
    /**
    * enable status 有效
    */
    public void setEnableStatus_enabled(){
        this.enableStatus="sv1";
    }
    /**
    * enable status 有效
    */
    public Demotable3 enableStatus_enabled(){
        this.enableStatus="sv1";
        return this;
    }
    /**
    * enable status 有效
    */
    public boolean enableStatus_is_enabled(){
        return Objects.equals(this.enableStatus, "sv1");
    }
    /**
    * enable status 无效
    */
    public void setEnableStatus_unEnabled(){
        this.enableStatus="s\"v2";
    }
    /**
    * enable status 无效
    */
    public Demotable3 enableStatus_unEnabled(){
        this.enableStatus="s\"v2";
        return this;
    }
    /**
    * enable status 无效
    */
    public boolean enableStatus_is_unEnabled(){
        return Objects.equals(this.enableStatus, "s\"v2");
    }
    /**
    * enable status
    */
    public boolean enableStatus_isNull(){
        return null==this.enableStatus;
    }
    /**
    * enable status
    */
    public boolean enableStatus_isBlank(){
        return StrUtil.isBlank(this.enableStatus);
    }

    /**
    * status
    */
    public Integer getStatus(){
        return this.status;
    }
    /**
    * status
    */
    public void setStatus(Integer value){
        this.status=value;
    }
    /**
    * status
    */
    public boolean status_is(Integer value){
        return Objects.equals(this.status, value);
    }
    /**
    * status
    */
    public Demotable3 status(Integer value){
        this.status=value;
        return this;
    }
    /**
    * status 待审核
    */
    public void setStatus_pending(){
        this.status=1;
    }
    /**
    * status 待审核
    */
    public Demotable3 status_pending(){
        this.status=1;
        return this;
    }
    /**
    * status 待审核
    */
    public boolean status_is_pending(){
        return Objects.equals(this.status, 1);
    }
    /**
    * status 已驳回
    */
    public void setStatus_reject(){
        this.status=2;
    }
    /**
    * status 已驳回
    */
    public Demotable3 status_reject(){
        this.status=2;
        return this;
    }
    /**
    * status 已驳回
    */
    public boolean status_is_reject(){
        return Objects.equals(this.status, 2);
    }
    /**
    * status 审批通过
    */
    public void setStatus_approved(){
        this.status=3;
    }
    /**
    * status 审批通过
    */
    public Demotable3 status_approved(){
        this.status=3;
        return this;
    }
    /**
    * status 审批通过
    */
    public boolean status_is_approved(){
        return Objects.equals(this.status, 3);
    }
    /**
    * status
    */
    public boolean status_isNull(){
        return null==this.status;
    }

    /**
    * status
    */
    public String getStatus2(){
        return this.status2;
    }
    /**
    * status
    */
    public void setStatus2(String value){
        this.status2=value;
    }
    /**
    * status
    */
    public boolean status2_is(String value){
        return Objects.equals(this.status2, value);
    }
    /**
    * status
    */
    public Demotable3 status2(String value){
        this.status2=value;
        return this;
    }
    /**
    * status 待审核
    */
    public void setStatus2_pending(){
        this.status2="sv\"1";
    }
    /**
    * status 待审核
    */
    public Demotable3 status2_pending(){
        this.status2="sv\"1";
        return this;
    }
    /**
    * status 待审核
    */
    public boolean status2_is_pending(){
        return Objects.equals(this.status2, "sv\"1");
    }
    /**
    * status 已驳回
    */
    public void setStatus2_reject(){
        this.status2="sv2";
    }
    /**
    * status 已驳回
    */
    public Demotable3 status2_reject(){
        this.status2="sv2";
        return this;
    }
    /**
    * status 已驳回
    */
    public boolean status2_is_reject(){
        return Objects.equals(this.status2, "sv2");
    }
    /**
    * status 审批通过
    */
    public void setStatus2_approved(){
        this.status2="sv3";
    }
    /**
    * status 审批通过
    */
    public Demotable3 status2_approved(){
        this.status2="sv3";
        return this;
    }
    /**
    * status 审批通过
    */
    public boolean status2_is_approved(){
        return Objects.equals(this.status2, "sv3");
    }
    /**
    * status
    */
    public boolean status2_isNull(){
        return null==this.status2;
    }
    /**
    * status
    */
    public boolean status2_isBlank(){
        return StrUtil.isBlank(this.status2);
    }

    /**
    * 创建时间
    */
    public Timestamp getCreationDate(){
        return this.creationDate;
    }
    /**
    * 创建时间
    */
    public void setCreationDate(Timestamp value){
        this.creationDate=value;
    }
    /**
    * 创建时间
    */
    public boolean creationDate_is(Timestamp value){
        return Objects.equals(this.creationDate, value);
    }
    /**
    * 创建时间
    */
    public Demotable3 creationDate(Timestamp value){
        this.creationDate=value;
        return this;
    }
    /**
    * 创建时间
    */
    public boolean creationDate_isNull(){
        return null==this.creationDate;
    }

    /**
    * 创建时间2
    */
    public Timestamp getCreationDate2(){
        return this.creationDate2;
    }
    /**
    * 创建时间2
    */
    public void setCreationDate2(Timestamp value){
        this.creationDate2=value;
    }
    /**
    * 创建时间2
    */
    public boolean creationDate2_is(Timestamp value){
        return Objects.equals(this.creationDate2, value);
    }
    /**
    * 创建时间2
    */
    public Demotable3 creationDate2(Timestamp value){
        this.creationDate2=value;
        return this;
    }
    /**
    * 创建时间2
    */
    public boolean creationDate2_isNull(){
        return null==this.creationDate2;
    }

}
