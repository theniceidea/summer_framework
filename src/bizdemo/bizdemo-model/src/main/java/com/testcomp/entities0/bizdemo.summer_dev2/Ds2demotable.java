package com.testcomp.entities0.bizdemo.summer_dev2;

import cn.hutool.core.util.StrUtil;
import com.fmk.framework.daoannotations.*;
import com.fmk.framework.entitiesbasic.Entity;
import com.fmk.framework.annotations.validation.*;
import com.fmk.framework.valid.IValidator;
import com.fmk.framework.valid.IValidatorSuccess;
import com.fmk.framework.valid.FieldValid;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Objects;

/**
* demo table
*/
@Table("ds2demotable")
public class Ds2demotable extends Entity{
//    entity.setId();
//    entity.setTitle();
//    entity.setType();
//    entity.setNum();
//    entity.setNum2();
//    entity.setNum3();
//    entity.setDeleteStatus();
//    entity.setEnableStatus();
//    entity.setStatus();
//    entity.setStatus2();
//    entity.setCreationDate();
//    entity
//        .id()
//        .title()
//        .type()
//        .num()
//        .num2()
//        .num3()
//        .deleteStatus()
//        .enableStatus()
//        .status()
//        .status2()
//        .creationDate()
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
    public Ds2demotable id(Integer value){
        this.id=value;
        return this;
    }
    /**
    * id
    */
    public FieldValid<Ds2demotable, Integer> id_fv(Integer value){
        return new FieldValid<>(this, value, () -> id(value));
    }
    /**
    * id
    */
    public Ds2demotable id_valid(IValidator<Integer> validator, String msg){
        validator.valid(this.id, msg);
        return this;
    }
    /**
    * id 如果为null返回默认值 value or default
    */
    public int id_vd(){
        if(null==this.id){
            return 0;
        }
        return this.id;
    }
    /**
    * id 如果为null返回默认值 value or default
    */
    public int id_vd(int defaultValue){
        if(null==this.id){
            return defaultValue;
        }
        return this.id;
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
    public Ds2demotable title(String value){
        this.title=value;
        return this;
    }
    /**
    * 标题
    */
    public FieldValid<Ds2demotable, String> title_fv(String value){
        return new FieldValid<>(this, value, () -> title(value));
    }
    /**
    * 标题
    */
    public Ds2demotable title_valid(IValidator<String> validator, String msg){
        validator.valid(this.title, msg);
        return this;
    }
    /**
    * 标题 如果为null返回默认值 value or default
    */
    public String title_vd(){
        if(null==this.title){
            return "";
        }
        return this.title;
    }
    /**
    * 标题 如果为null返回默认值 value or default
    */
    public String title_vd(String defaultValue){
        if(null==this.title){
            return defaultValue;
        }
        return this.title;
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
    public Ds2demotable type(String value){
        this.type=value;
        return this;
    }
    /**
    * 类型
    */
    public FieldValid<Ds2demotable, String> type_fv(String value){
        return new FieldValid<>(this, value, () -> type(value));
    }
    /**
    * 类型
    */
    public Ds2demotable type_valid(IValidator<String> validator, String msg){
        validator.valid(this.type, msg);
        return this;
    }
    /**
    * 类型 如果为null返回默认值 value or default
    */
    public String type_vd(){
        if(null==this.type){
            return "";
        }
        return this.type;
    }
    /**
    * 类型 如果为null返回默认值 value or default
    */
    public String type_vd(String defaultValue){
        if(null==this.type){
            return defaultValue;
        }
        return this.type;
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
    public Ds2demotable num(Integer value){
        this.num=value;
        return this;
    }
    /**
    * 数量
    */
    public FieldValid<Ds2demotable, Integer> num_fv(Integer value){
        return new FieldValid<>(this, value, () -> num(value));
    }
    /**
    * 数量
    */
    public Ds2demotable num_valid(IValidator<Integer> validator, String msg){
        validator.valid(this.num, msg);
        return this;
    }
    /**
    * 数量 如果为null返回默认值 value or default
    */
    public int num_vd(){
        if(null==this.num){
            return 0;
        }
        return this.num;
    }
    /**
    * 数量 如果为null返回默认值 value or default
    */
    public int num_vd(int defaultValue){
        if(null==this.num){
            return defaultValue;
        }
        return this.num;
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
    public Ds2demotable num2(BigDecimal value){
        this.num2=value;
        return this;
    }
    /**
    * 数量2
    */
    public FieldValid<Ds2demotable, BigDecimal> num2_fv(BigDecimal value){
        return new FieldValid<>(this, value, () -> num2(value));
    }
    /**
    * 数量2
    */
    public Ds2demotable num2_valid(IValidator<BigDecimal> validator, String msg){
        validator.valid(this.num2, msg);
        return this;
    }
    /**
    * 数量2 如果为null返回默认值 value or default
    */
    public BigDecimal num2_vd(){
        if(null==this.num2){
            return BigDecimal.ZERO;
        }
        return this.num2;
    }
    /**
    * 数量2 如果为null返回默认值 value or default
    */
    public BigDecimal num2_vd(BigDecimal defaultValue){
        if(null==this.num2){
            return defaultValue;
        }
        return this.num2;
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
    public Ds2demotable num3(Double value){
        this.num3=value;
        return this;
    }
    /**
    * 数量3
    */
    public FieldValid<Ds2demotable, Double> num3_fv(Double value){
        return new FieldValid<>(this, value, () -> num3(value));
    }
    /**
    * 数量3
    */
    public Ds2demotable num3_valid(IValidator<Double> validator, String msg){
        validator.valid(this.num3, msg);
        return this;
    }
    /**
    * 数量3 如果为null返回默认值 value or default
    */
    public double num3_vd(){
        if(null==this.num3){
            return 0D;
        }
        return this.num3;
    }
    /**
    * 数量3 如果为null返回默认值 value or default
    */
    public double num3_vd(double defaultValue){
        if(null==this.num3){
            return defaultValue;
        }
        return this.num3;
    }
    /**
    * 数量3
    */
    public boolean num3_isNull(){
        return null==this.num3;
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
    public Ds2demotable deleteStatus(Integer value){
        this.deleteStatus=value;
        return this;
    }
    /**
    * delete status
    */
    public FieldValid<Ds2demotable, Integer> deleteStatus_fv(Integer value){
        return new FieldValid<>(this, value, () -> deleteStatus(value));
    }
    /**
    * delete status
    */
    public Ds2demotable deleteStatus_valid(IValidator<Integer> validator, String msg){
        validator.valid(this.deleteStatus, msg);
        return this;
    }
    /**
    * delete status 如果为null返回默认值 value or default
    */
    public int deleteStatus_vd(){
        if(null==this.deleteStatus){
            return 0;
        }
        return this.deleteStatus;
    }
    /**
    * delete status 如果为null返回默认值 value or default
    */
    public int deleteStatus_vd(int defaultValue){
        if(null==this.deleteStatus){
            return defaultValue;
        }
        return this.deleteStatus;
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
    public Ds2demotable deleteStatus_deleted(){
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
    public Ds2demotable deleteStatus_unDeleted(){
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
    public Ds2demotable enableStatus(String value){
        this.enableStatus=value;
        return this;
    }
    /**
    * enable status
    */
    public FieldValid<Ds2demotable, String> enableStatus_fv(String value){
        return new FieldValid<>(this, value, () -> enableStatus(value));
    }
    /**
    * enable status
    */
    public Ds2demotable enableStatus_valid(IValidator<String> validator, String msg){
        validator.valid(this.enableStatus, msg);
        return this;
    }
    /**
    * enable status 如果为null返回默认值 value or default
    */
    public String enableStatus_vd(){
        if(null==this.enableStatus){
            return "";
        }
        return this.enableStatus;
    }
    /**
    * enable status 如果为null返回默认值 value or default
    */
    public String enableStatus_vd(String defaultValue){
        if(null==this.enableStatus){
            return defaultValue;
        }
        return this.enableStatus;
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
    public Ds2demotable enableStatus_enabled(){
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
    public Ds2demotable enableStatus_unEnabled(){
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
    public Ds2demotable status(Integer value){
        this.status=value;
        return this;
    }
    /**
    * status
    */
    public FieldValid<Ds2demotable, Integer> status_fv(Integer value){
        return new FieldValid<>(this, value, () -> status(value));
    }
    /**
    * status
    */
    public Ds2demotable status_valid(IValidator<Integer> validator, String msg){
        validator.valid(this.status, msg);
        return this;
    }
    /**
    * status 如果为null返回默认值 value or default
    */
    public int status_vd(){
        if(null==this.status){
            return 0;
        }
        return this.status;
    }
    /**
    * status 如果为null返回默认值 value or default
    */
    public int status_vd(int defaultValue){
        if(null==this.status){
            return defaultValue;
        }
        return this.status;
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
    public Ds2demotable status_pending(){
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
    public Ds2demotable status_reject(){
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
    public Ds2demotable status_approved(){
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
    public Ds2demotable status2(String value){
        this.status2=value;
        return this;
    }
    /**
    * status
    */
    public FieldValid<Ds2demotable, String> status2_fv(String value){
        return new FieldValid<>(this, value, () -> status2(value));
    }
    /**
    * status
    */
    public Ds2demotable status2_valid(IValidator<String> validator, String msg){
        validator.valid(this.status2, msg);
        return this;
    }
    /**
    * status 如果为null返回默认值 value or default
    */
    public String status2_vd(){
        if(null==this.status2){
            return "";
        }
        return this.status2;
    }
    /**
    * status 如果为null返回默认值 value or default
    */
    public String status2_vd(String defaultValue){
        if(null==this.status2){
            return defaultValue;
        }
        return this.status2;
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
    public Ds2demotable status2_pending(){
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
    public Ds2demotable status2_reject(){
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
    public Ds2demotable status2_approved(){
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
    public Ds2demotable creationDate(Timestamp value){
        this.creationDate=value;
        return this;
    }
    /**
    * 创建时间
    */
    public FieldValid<Ds2demotable, Timestamp> creationDate_fv(Timestamp value){
        return new FieldValid<>(this, value, () -> creationDate(value));
    }
    /**
    * 创建时间
    */
    public Ds2demotable creationDate_valid(IValidator<Timestamp> validator, String msg){
        validator.valid(this.creationDate, msg);
        return this;
    }
    /**
    * 创建时间 如果为null返回默认值 value or default
    */
    public Timestamp creationDate_vd(){
        if(null==this.creationDate){
            throw new RuntimeException("不支持此类型的默认值, 请手动指定默认值");
        }
        return this.creationDate;
    }
    /**
    * 创建时间 如果为null返回默认值 value or default
    */
    public Timestamp creationDate_vd(Timestamp defaultValue){
        if(null==this.creationDate){
            return defaultValue;
        }
        return this.creationDate;
    }
    /**
    * 创建时间
    */
    public boolean creationDate_isNull(){
        return null==this.creationDate;
    }

}
