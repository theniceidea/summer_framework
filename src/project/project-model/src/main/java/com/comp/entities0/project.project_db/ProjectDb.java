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
    public ProjectDb id(String value){
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
    public ProjectDb cname(String value){
        this.cname=value;
        return this;
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
    public ProjectDb ename(String value){
        this.ename=value;
        return this;
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
    public ProjectDb dbname(String value){
        this.dbname=value;
        return this;
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
    public ProjectDb connnectString(String value){
        this.connnectString=value;
        return this;
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
    public ProjectDb dbUser(String value){
        this.dbUser=value;
        return this;
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
    public ProjectDb dbPwd(String value){
        this.dbPwd=value;
        return this;
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
    public ProjectDb dspOrder(Integer value){
        this.dspOrder=value;
        return this;
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
    public ProjectDb comment(String value){
        this.comment=value;
        return this;
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
    public ProjectDb deleteStatus(Integer value){
        this.deleteStatus=value;
        return this;
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
    public ProjectDb createTime(Timestamp value){
        this.createTime=value;
        return this;
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
    public ProjectDb updateTime(Timestamp value){
        this.updateTime=value;
        return this;
    }
    /**
    * 更新时间
    */
    public boolean updateTime_isNull(){
        return null==this.updateTime;
    }

}
