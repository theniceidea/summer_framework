package com.comp.entities0.project.project_db;

import cn.hutool.core.util.StrUtil;
import com.fmk.framework.daoannotations.*;
import com.fmk.framework.entitiesbasic.Entity;
import com.fmk.framework.annotations.validation.*;
import java.sql.Timestamp;
import java.math.BigDecimal;
import java.util.Objects;
import lombok.Builder;

/**
* 数据库
*/
@Table("project_db")
public class ProjectDb extends Entity{
//    entity.setId();
//    entity.setCname();
//    entity.setEname();
//    entity.setDbname();
//    entity.setConnnectString();
//    entity.setDbUser();
//    entity.setDbPwd();
//    entity.setDspOrder();
//    entity.setComment();
//    entity.setDeleteStatus();
//    entity.setCreateTime();
//    entity.setUpdateTime();
//    entity
//        .id()
//        .cname()
//        .ename()
//        .dbname()
//        .connnectString()
//        .dbUser()
//        .dbPwd()
//        .dspOrder()
//        .comment()
//        .deleteStatus()
//        .createTime()
//        .updateTime()
	/**
	* id
	*/
	@SkipUpdate
	@IdColumn
	@UniqueColumn
	@Column("id")
	private String id;
	public static final String _id="id";
	/**
	* 中文名
	*/
	@Column("cname")
	private String cname;
	public static final String _cname="cname";
	/**
	* 英文名不一定是数据库名字
	*/
	@Column("ename")
	private String ename;
	public static final String _ename="ename";
	/**
	* 数据库名字
	*/
	@Column("dbname")
	private String dbname;
	public static final String _dbname="dbname";
	/**
	* 连接字符串
	*/
	@Column("connnect_string")
	private String connnectString;
	public static final String _connnectString="connnect_string";
	/**
	* 用户名
	*/
	@Column("db_user")
	private String dbUser;
	public static final String _dbUser="db_user";
	/**
	* 密码
	*/
	@Column("db_pwd")
	private String dbPwd;
	public static final String _dbPwd="db_pwd";
	/**
	* 序号
	*/
	@Column("dsp_order")
	private Integer dspOrder;
	public static final String _dspOrder="dsp_order";
	/**
	* 备注
	*/
	@Column("comment")
	private String comment;
	public static final String _comment="comment";
	/**
	* 删除状态
	*/
	@Column("delete_status")
	private Integer deleteStatus;
	public static final String _deleteStatus="delete_status";
	/**
	* 创建时间
	*/
	@SkipInsert
	@SkipUpdate
	@Column("create_time")
	private Timestamp createTime;
	public static final String _createTime="create_time";
	/**
	* 更新时间
	*/
	@SkipInsert
	@SkipUpdate
	@Column("update_time")
	private Timestamp updateTime;
	public static final String _updateTime="update_time";
    /**
    * id
    */
    public String getId(){
        return this.id;
    }
    /**
    * id
    */
    public void setId(String value){
        this.id=value;
    }
    /**
    * id
    */
    public boolean id_is(String value){
        return Objects.equals(this.id, value);
    }
    /**
    * id
    */
    public ProjectDb id(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(this.id)){
                    return this;
                }
            }
        }

        this.id=value;
        return this;
    }
    /**
    * id
    */
    public ProjectDb id_valid(IValidator<String> validator, String msg){
        validator.valid(this.id, msg);
        return this;
    }
    /**
    * id 如果为null返回默认值 value or default
    */
    public String id_vd(){
        if(null==this.id){
            return "";
        }
        return this.id;
    }
    /**
    * id 如果为null返回默认值 value or default
    */
    public String id_vd(String defaultValue){
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
    * id
    */
    public boolean id_isBlank(){
        return StrUtil.isBlank(this.id);
    }

    /**
    * 中文名
    */
    public String getCname(){
        return this.cname;
    }
    /**
    * 中文名
    */
    public void setCname(String value){
        this.cname=value;
    }
    /**
    * 中文名
    */
    public boolean cname_is(String value){
        return Objects.equals(this.cname, value);
    }
    /**
    * 中文名
    */
    public ProjectDb cname(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(this.cname)){
                    return this;
                }
            }
        }

        this.cname=value;
        return this;
    }
    /**
    * 中文名
    */
    public ProjectDb cname_valid(IValidator<String> validator, String msg){
        validator.valid(this.cname, msg);
        return this;
    }
    /**
    * 中文名 如果为null返回默认值 value or default
    */
    public String cname_vd(){
        if(null==this.cname){
            return "";
        }
        return this.cname;
    }
    /**
    * 中文名 如果为null返回默认值 value or default
    */
    public String cname_vd(String defaultValue){
        if(null==this.cname){
            return defaultValue;
        }
        return this.cname;
    }
    /**
    * 中文名
    */
    public boolean cname_isNull(){
        return null==this.cname;
    }
    /**
    * 中文名
    */
    public boolean cname_isBlank(){
        return StrUtil.isBlank(this.cname);
    }

    /**
    * 英文名不一定是数据库名字
    */
    public String getEname(){
        return this.ename;
    }
    /**
    * 英文名不一定是数据库名字
    */
    public void setEname(String value){
        this.ename=value;
    }
    /**
    * 英文名不一定是数据库名字
    */
    public boolean ename_is(String value){
        return Objects.equals(this.ename, value);
    }
    /**
    * 英文名不一定是数据库名字
    */
    public ProjectDb ename(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(this.ename)){
                    return this;
                }
            }
        }

        this.ename=value;
        return this;
    }
    /**
    * 英文名不一定是数据库名字
    */
    public ProjectDb ename_valid(IValidator<String> validator, String msg){
        validator.valid(this.ename, msg);
        return this;
    }
    /**
    * 英文名不一定是数据库名字 如果为null返回默认值 value or default
    */
    public String ename_vd(){
        if(null==this.ename){
            return "";
        }
        return this.ename;
    }
    /**
    * 英文名不一定是数据库名字 如果为null返回默认值 value or default
    */
    public String ename_vd(String defaultValue){
        if(null==this.ename){
            return defaultValue;
        }
        return this.ename;
    }
    /**
    * 英文名不一定是数据库名字
    */
    public boolean ename_isNull(){
        return null==this.ename;
    }
    /**
    * 英文名不一定是数据库名字
    */
    public boolean ename_isBlank(){
        return StrUtil.isBlank(this.ename);
    }

    /**
    * 数据库名字
    */
    public String getDbname(){
        return this.dbname;
    }
    /**
    * 数据库名字
    */
    public void setDbname(String value){
        this.dbname=value;
    }
    /**
    * 数据库名字
    */
    public boolean dbname_is(String value){
        return Objects.equals(this.dbname, value);
    }
    /**
    * 数据库名字
    */
    public ProjectDb dbname(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(this.dbname)){
                    return this;
                }
            }
        }

        this.dbname=value;
        return this;
    }
    /**
    * 数据库名字
    */
    public ProjectDb dbname_valid(IValidator<String> validator, String msg){
        validator.valid(this.dbname, msg);
        return this;
    }
    /**
    * 数据库名字 如果为null返回默认值 value or default
    */
    public String dbname_vd(){
        if(null==this.dbname){
            return "";
        }
        return this.dbname;
    }
    /**
    * 数据库名字 如果为null返回默认值 value or default
    */
    public String dbname_vd(String defaultValue){
        if(null==this.dbname){
            return defaultValue;
        }
        return this.dbname;
    }
    /**
    * 数据库名字
    */
    public boolean dbname_isNull(){
        return null==this.dbname;
    }
    /**
    * 数据库名字
    */
    public boolean dbname_isBlank(){
        return StrUtil.isBlank(this.dbname);
    }

    /**
    * 连接字符串
    */
    public String getConnnectString(){
        return this.connnectString;
    }
    /**
    * 连接字符串
    */
    public void setConnnectString(String value){
        this.connnectString=value;
    }
    /**
    * 连接字符串
    */
    public boolean connnectString_is(String value){
        return Objects.equals(this.connnectString, value);
    }
    /**
    * 连接字符串
    */
    public ProjectDb connnectString(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(this.connnectString)){
                    return this;
                }
            }
        }

        this.connnectString=value;
        return this;
    }
    /**
    * 连接字符串
    */
    public ProjectDb connnectString_valid(IValidator<String> validator, String msg){
        validator.valid(this.connnectString, msg);
        return this;
    }
    /**
    * 连接字符串 如果为null返回默认值 value or default
    */
    public String connnectString_vd(){
        if(null==this.connnectString){
            return "";
        }
        return this.connnectString;
    }
    /**
    * 连接字符串 如果为null返回默认值 value or default
    */
    public String connnectString_vd(String defaultValue){
        if(null==this.connnectString){
            return defaultValue;
        }
        return this.connnectString;
    }
    /**
    * 连接字符串
    */
    public boolean connnectString_isNull(){
        return null==this.connnectString;
    }
    /**
    * 连接字符串
    */
    public boolean connnectString_isBlank(){
        return StrUtil.isBlank(this.connnectString);
    }

    /**
    * 用户名
    */
    public String getDbUser(){
        return this.dbUser;
    }
    /**
    * 用户名
    */
    public void setDbUser(String value){
        this.dbUser=value;
    }
    /**
    * 用户名
    */
    public boolean dbUser_is(String value){
        return Objects.equals(this.dbUser, value);
    }
    /**
    * 用户名
    */
    public ProjectDb dbUser(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(this.dbUser)){
                    return this;
                }
            }
        }

        this.dbUser=value;
        return this;
    }
    /**
    * 用户名
    */
    public ProjectDb dbUser_valid(IValidator<String> validator, String msg){
        validator.valid(this.dbUser, msg);
        return this;
    }
    /**
    * 用户名 如果为null返回默认值 value or default
    */
    public String dbUser_vd(){
        if(null==this.dbUser){
            return "";
        }
        return this.dbUser;
    }
    /**
    * 用户名 如果为null返回默认值 value or default
    */
    public String dbUser_vd(String defaultValue){
        if(null==this.dbUser){
            return defaultValue;
        }
        return this.dbUser;
    }
    /**
    * 用户名
    */
    public boolean dbUser_isNull(){
        return null==this.dbUser;
    }
    /**
    * 用户名
    */
    public boolean dbUser_isBlank(){
        return StrUtil.isBlank(this.dbUser);
    }

    /**
    * 密码
    */
    public String getDbPwd(){
        return this.dbPwd;
    }
    /**
    * 密码
    */
    public void setDbPwd(String value){
        this.dbPwd=value;
    }
    /**
    * 密码
    */
    public boolean dbPwd_is(String value){
        return Objects.equals(this.dbPwd, value);
    }
    /**
    * 密码
    */
    public ProjectDb dbPwd(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(this.dbPwd)){
                    return this;
                }
            }
        }

        this.dbPwd=value;
        return this;
    }
    /**
    * 密码
    */
    public ProjectDb dbPwd_valid(IValidator<String> validator, String msg){
        validator.valid(this.dbPwd, msg);
        return this;
    }
    /**
    * 密码 如果为null返回默认值 value or default
    */
    public String dbPwd_vd(){
        if(null==this.dbPwd){
            return "";
        }
        return this.dbPwd;
    }
    /**
    * 密码 如果为null返回默认值 value or default
    */
    public String dbPwd_vd(String defaultValue){
        if(null==this.dbPwd){
            return defaultValue;
        }
        return this.dbPwd;
    }
    /**
    * 密码
    */
    public boolean dbPwd_isNull(){
        return null==this.dbPwd;
    }
    /**
    * 密码
    */
    public boolean dbPwd_isBlank(){
        return StrUtil.isBlank(this.dbPwd);
    }

    /**
    * 序号
    */
    public Integer getDspOrder(){
        return this.dspOrder;
    }
    /**
    * 序号
    */
    public void setDspOrder(Integer value){
        this.dspOrder=value;
    }
    /**
    * 序号
    */
    public boolean dspOrder_is(Integer value){
        return Objects.equals(this.dspOrder, value);
    }
    /**
    * 序号
    */
    public ProjectDb dspOrder(Integer value, IValidatorSuccess<Integer> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<Integer> itm : ivs){
                if(!itm.isValidSuccess(this.dspOrder)){
                    return this;
                }
            }
        }

        this.dspOrder=value;
        return this;
    }
    /**
    * 序号
    */
    public ProjectDb dspOrder_valid(IValidator<Integer> validator, String msg){
        validator.valid(this.dspOrder, msg);
        return this;
    }
    /**
    * 序号 如果为null返回默认值 value or default
    */
    public int dspOrder_vd(){
        if(null==this.dspOrder){
            return 0;
        }
        return this.dspOrder;
    }
    /**
    * 序号 如果为null返回默认值 value or default
    */
    public int dspOrder_vd(int defaultValue){
        if(null==this.dspOrder){
            return defaultValue;
        }
        return this.dspOrder;
    }
    /**
    * 序号
    */
    public boolean dspOrder_isNull(){
        return null==this.dspOrder;
    }

    /**
    * 备注
    */
    public String getComment(){
        return this.comment;
    }
    /**
    * 备注
    */
    public void setComment(String value){
        this.comment=value;
    }
    /**
    * 备注
    */
    public boolean comment_is(String value){
        return Objects.equals(this.comment, value);
    }
    /**
    * 备注
    */
    public ProjectDb comment(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(this.comment)){
                    return this;
                }
            }
        }

        this.comment=value;
        return this;
    }
    /**
    * 备注
    */
    public ProjectDb comment_valid(IValidator<String> validator, String msg){
        validator.valid(this.comment, msg);
        return this;
    }
    /**
    * 备注 如果为null返回默认值 value or default
    */
    public String comment_vd(){
        if(null==this.comment){
            return "";
        }
        return this.comment;
    }
    /**
    * 备注 如果为null返回默认值 value or default
    */
    public String comment_vd(String defaultValue){
        if(null==this.comment){
            return defaultValue;
        }
        return this.comment;
    }
    /**
    * 备注
    */
    public boolean comment_isNull(){
        return null==this.comment;
    }
    /**
    * 备注
    */
    public boolean comment_isBlank(){
        return StrUtil.isBlank(this.comment);
    }

    /**
    * 删除状态
    */
    public Integer getDeleteStatus(){
        return this.deleteStatus;
    }
    /**
    * 删除状态
    */
    public void setDeleteStatus(Integer value){
        this.deleteStatus=value;
    }
    /**
    * 删除状态
    */
    public boolean deleteStatus_is(Integer value){
        return Objects.equals(this.deleteStatus, value);
    }
    /**
    * 删除状态
    */
    public ProjectDb deleteStatus(Integer value, IValidatorSuccess<Integer> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<Integer> itm : ivs){
                if(!itm.isValidSuccess(this.deleteStatus)){
                    return this;
                }
            }
        }

        this.deleteStatus=value;
        return this;
    }
    /**
    * 删除状态
    */
    public ProjectDb deleteStatus_valid(IValidator<Integer> validator, String msg){
        validator.valid(this.deleteStatus, msg);
        return this;
    }
    /**
    * 删除状态 如果为null返回默认值 value or default
    */
    public int deleteStatus_vd(){
        if(null==this.deleteStatus){
            return 0;
        }
        return this.deleteStatus;
    }
    /**
    * 删除状态 如果为null返回默认值 value or default
    */
    public int deleteStatus_vd(int defaultValue){
        if(null==this.deleteStatus){
            return defaultValue;
        }
        return this.deleteStatus;
    }
    /**
    * 删除状态 删除
    */
    public void setDeleteStatus_deleted(){
        this.deleteStatus=1;
    }
    /**
    * 删除状态 删除
    */
    public ProjectDb deleteStatus_deleted(){
        this.deleteStatus=1;
        return this;
    }
    /**
    * 删除状态 删除
    */
    public boolean deleteStatus_is_deleted(){
        return Objects.equals(this.deleteStatus, 1);
    }
    /**
    * 删除状态 未删除
    */
    public void setDeleteStatus_unDeleted(){
        this.deleteStatus=0;
    }
    /**
    * 删除状态 未删除
    */
    public ProjectDb deleteStatus_unDeleted(){
        this.deleteStatus=0;
        return this;
    }
    /**
    * 删除状态 未删除
    */
    public boolean deleteStatus_is_unDeleted(){
        return Objects.equals(this.deleteStatus, 0);
    }
    /**
    * 删除状态
    */
    public boolean deleteStatus_isNull(){
        return null==this.deleteStatus;
    }

    /**
    * 创建时间
    */
    public Timestamp getCreateTime(){
        return this.createTime;
    }
    /**
    * 创建时间
    */
    public void setCreateTime(Timestamp value){
        this.createTime=value;
    }
    /**
    * 创建时间
    */
    public boolean createTime_is(Timestamp value){
        return Objects.equals(this.createTime, value);
    }
    /**
    * 创建时间
    */
    public ProjectDb createTime(Timestamp value, IValidatorSuccess<Timestamp> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<Timestamp> itm : ivs){
                if(!itm.isValidSuccess(this.createTime)){
                    return this;
                }
            }
        }

        this.createTime=value;
        return this;
    }
    /**
    * 创建时间
    */
    public ProjectDb createTime_valid(IValidator<Timestamp> validator, String msg){
        validator.valid(this.createTime, msg);
        return this;
    }
    /**
    * 创建时间 如果为null返回默认值 value or default
    */
    public Timestamp createTime_vd(){
        if(null==this.createTime){
            throw new RuntimeException("不支持此类型的默认值, 请手动指定默认值");
        }
        return this.createTime;
    }
    /**
    * 创建时间 如果为null返回默认值 value or default
    */
    public Timestamp createTime_vd(Timestamp defaultValue){
        if(null==this.createTime){
            return defaultValue;
        }
        return this.createTime;
    }
    /**
    * 创建时间
    */
    public boolean createTime_isNull(){
        return null==this.createTime;
    }

    /**
    * 更新时间
    */
    public Timestamp getUpdateTime(){
        return this.updateTime;
    }
    /**
    * 更新时间
    */
    public void setUpdateTime(Timestamp value){
        this.updateTime=value;
    }
    /**
    * 更新时间
    */
    public boolean updateTime_is(Timestamp value){
        return Objects.equals(this.updateTime, value);
    }
    /**
    * 更新时间
    */
    public ProjectDb updateTime(Timestamp value, IValidatorSuccess<Timestamp> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<Timestamp> itm : ivs){
                if(!itm.isValidSuccess(this.updateTime)){
                    return this;
                }
            }
        }

        this.updateTime=value;
        return this;
    }
    /**
    * 更新时间
    */
    public ProjectDb updateTime_valid(IValidator<Timestamp> validator, String msg){
        validator.valid(this.updateTime, msg);
        return this;
    }
    /**
    * 更新时间 如果为null返回默认值 value or default
    */
    public Timestamp updateTime_vd(){
        if(null==this.updateTime){
            throw new RuntimeException("不支持此类型的默认值, 请手动指定默认值");
        }
        return this.updateTime;
    }
    /**
    * 更新时间 如果为null返回默认值 value or default
    */
    public Timestamp updateTime_vd(Timestamp defaultValue){
        if(null==this.updateTime){
            return defaultValue;
        }
        return this.updateTime;
    }
    /**
    * 更新时间
    */
    public boolean updateTime_isNull(){
        return null==this.updateTime;
    }

}
