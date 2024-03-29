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
* 字段
*/
@Table("project_model_field")
public class ProjectModelField extends Entity{
//    entity.setId();
//    entity.setProjectId();
//    entity.setProjectDbId();
//    entity.setProjectModelId();
//    entity.setCname();
//    entity.setEname();
//    entity.setDataType();
//    entity.setDataTypeMapKey();
//    entity.setDataTypeValue();
//    entity.setDbType();
//    entity.setDbTypeExt();
//    entity.setCreateExt();
//    entity.setRefColumn();
//    entity.setDefValue();
//    entity.setImports();
//    entity.setDspOrder();
//    entity.setComment();
//    entity.setDeleteStatus();
//    entity.setCreateTime();
//    entity.setUpdateTime();
//    entity
//        .id()
//        .projectId()
//        .projectDbId()
//        .projectModelId()
//        .cname()
//        .ename()
//        .dataType()
//        .dataTypeMapKey()
//        .dataTypeValue()
//        .dbType()
//        .dbTypeExt()
//        .createExt()
//        .refColumn()
//        .defValue()
//        .imports()
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
	* 项目id
	*/
	@Column("project_id")
	private String projectId;
	public static final String _projectId="project_id";
	/**
	* 数据库id
	*/
	@Column("project_db_id")
	private String projectDbId;
	public static final String _projectDbId="project_db_id";
	/**
	* 数据模型id
	*/
	@Column("project_model_id")
	private String projectModelId;
	public static final String _projectModelId="project_model_id";
	/**
	* 中文名
	*/
	@Column("cname")
	private String cname;
	public static final String _cname="cname";
	/**
	* 英文名
	*/
	@Column("ename")
	private String ename;
	public static final String _ename="ename";
	/**
	* 字段数据类型
	*/
	@Column("data_type")
	private String dataType;
	public static final String _dataType="data_type";
	/**
	* map字段key
	*/
	@Column("data_type_map_key")
	private String dataTypeMapKey;
	public static final String _dataTypeMapKey="data_type_map_key";
	/**
	* list, set, map 字段value
	*/
	@Column("data_type_value")
	private String dataTypeValue;
	public static final String _dataTypeValue="data_type_value";
	/**
	* 数据库类型
	*/
	@Column("db_type")
	private String dbType;
	public static final String _dbType="db_type";
	/**
	* 数据库类型扩展信息
	*/
	@Column("db_type_ext")
	private String dbTypeExt;
	public static final String _dbTypeExt="db_type_ext";
	/**
	* DDL语句中的扩展信息
	*/
	@Column("create_ext")
	private String createExt;
	public static final String _createExt="create_ext";
	/**
	* 关联column
	*/
	@Column("ref_column")
	private String refColumn;
	public static final String _refColumn="ref_column";
	/**
	* 默认值
	*/
	@Column("def_value")
	private String defValue;
	public static final String _defValue="def_value";
	/**
	* imports
	*/
	@Column("imports")
	private String imports;
	public static final String _imports="imports";
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
    public ProjectModelField id(String value){
        this.id=value;
        return this;
    }
    /**
    * id
    */
    public FieldValid<ProjectModelField, String> id_fv(String value){
        return new FieldValid<>(this, value, () -> id(value));
    }
    /**
    * id
    */
    public ProjectModelField id_valid(IValidator<String> validator, String msg){
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
    * 项目id
    */
    public String getProjectId(){
        return this.projectId;
    }
    /**
    * 项目id
    */
    public void setProjectId(String value){
        this.projectId=value;
    }
    /**
    * 项目id
    */
    public boolean projectId_is(String value){
        return Objects.equals(this.projectId, value);
    }
    /**
    * 项目id
    */
    public ProjectModelField projectId(String value){
        this.projectId=value;
        return this;
    }
    /**
    * 项目id
    */
    public FieldValid<ProjectModelField, String> projectId_fv(String value){
        return new FieldValid<>(this, value, () -> projectId(value));
    }
    /**
    * 项目id
    */
    public ProjectModelField projectId_valid(IValidator<String> validator, String msg){
        validator.valid(this.projectId, msg);
        return this;
    }
    /**
    * 项目id 如果为null返回默认值 value or default
    */
    public String projectId_vd(){
        if(null==this.projectId){
            return "";
        }
        return this.projectId;
    }
    /**
    * 项目id 如果为null返回默认值 value or default
    */
    public String projectId_vd(String defaultValue){
        if(null==this.projectId){
            return defaultValue;
        }
        return this.projectId;
    }
    /**
    * 项目id
    */
    public boolean projectId_isNull(){
        return null==this.projectId;
    }
    /**
    * 项目id
    */
    public boolean projectId_isBlank(){
        return StrUtil.isBlank(this.projectId);
    }

    /**
    * 数据库id
    */
    public String getProjectDbId(){
        return this.projectDbId;
    }
    /**
    * 数据库id
    */
    public void setProjectDbId(String value){
        this.projectDbId=value;
    }
    /**
    * 数据库id
    */
    public boolean projectDbId_is(String value){
        return Objects.equals(this.projectDbId, value);
    }
    /**
    * 数据库id
    */
    public ProjectModelField projectDbId(String value){
        this.projectDbId=value;
        return this;
    }
    /**
    * 数据库id
    */
    public FieldValid<ProjectModelField, String> projectDbId_fv(String value){
        return new FieldValid<>(this, value, () -> projectDbId(value));
    }
    /**
    * 数据库id
    */
    public ProjectModelField projectDbId_valid(IValidator<String> validator, String msg){
        validator.valid(this.projectDbId, msg);
        return this;
    }
    /**
    * 数据库id 如果为null返回默认值 value or default
    */
    public String projectDbId_vd(){
        if(null==this.projectDbId){
            return "";
        }
        return this.projectDbId;
    }
    /**
    * 数据库id 如果为null返回默认值 value or default
    */
    public String projectDbId_vd(String defaultValue){
        if(null==this.projectDbId){
            return defaultValue;
        }
        return this.projectDbId;
    }
    /**
    * 数据库id
    */
    public boolean projectDbId_isNull(){
        return null==this.projectDbId;
    }
    /**
    * 数据库id
    */
    public boolean projectDbId_isBlank(){
        return StrUtil.isBlank(this.projectDbId);
    }

    /**
    * 数据模型id
    */
    public String getProjectModelId(){
        return this.projectModelId;
    }
    /**
    * 数据模型id
    */
    public void setProjectModelId(String value){
        this.projectModelId=value;
    }
    /**
    * 数据模型id
    */
    public boolean projectModelId_is(String value){
        return Objects.equals(this.projectModelId, value);
    }
    /**
    * 数据模型id
    */
    public ProjectModelField projectModelId(String value){
        this.projectModelId=value;
        return this;
    }
    /**
    * 数据模型id
    */
    public FieldValid<ProjectModelField, String> projectModelId_fv(String value){
        return new FieldValid<>(this, value, () -> projectModelId(value));
    }
    /**
    * 数据模型id
    */
    public ProjectModelField projectModelId_valid(IValidator<String> validator, String msg){
        validator.valid(this.projectModelId, msg);
        return this;
    }
    /**
    * 数据模型id 如果为null返回默认值 value or default
    */
    public String projectModelId_vd(){
        if(null==this.projectModelId){
            return "";
        }
        return this.projectModelId;
    }
    /**
    * 数据模型id 如果为null返回默认值 value or default
    */
    public String projectModelId_vd(String defaultValue){
        if(null==this.projectModelId){
            return defaultValue;
        }
        return this.projectModelId;
    }
    /**
    * 数据模型id
    */
    public boolean projectModelId_isNull(){
        return null==this.projectModelId;
    }
    /**
    * 数据模型id
    */
    public boolean projectModelId_isBlank(){
        return StrUtil.isBlank(this.projectModelId);
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
    public ProjectModelField cname(String value){
        this.cname=value;
        return this;
    }
    /**
    * 中文名
    */
    public FieldValid<ProjectModelField, String> cname_fv(String value){
        return new FieldValid<>(this, value, () -> cname(value));
    }
    /**
    * 中文名
    */
    public ProjectModelField cname_valid(IValidator<String> validator, String msg){
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
    * 英文名
    */
    public String getEname(){
        return this.ename;
    }
    /**
    * 英文名
    */
    public void setEname(String value){
        this.ename=value;
    }
    /**
    * 英文名
    */
    public boolean ename_is(String value){
        return Objects.equals(this.ename, value);
    }
    /**
    * 英文名
    */
    public ProjectModelField ename(String value){
        this.ename=value;
        return this;
    }
    /**
    * 英文名
    */
    public FieldValid<ProjectModelField, String> ename_fv(String value){
        return new FieldValid<>(this, value, () -> ename(value));
    }
    /**
    * 英文名
    */
    public ProjectModelField ename_valid(IValidator<String> validator, String msg){
        validator.valid(this.ename, msg);
        return this;
    }
    /**
    * 英文名 如果为null返回默认值 value or default
    */
    public String ename_vd(){
        if(null==this.ename){
            return "";
        }
        return this.ename;
    }
    /**
    * 英文名 如果为null返回默认值 value or default
    */
    public String ename_vd(String defaultValue){
        if(null==this.ename){
            return defaultValue;
        }
        return this.ename;
    }
    /**
    * 英文名
    */
    public boolean ename_isNull(){
        return null==this.ename;
    }
    /**
    * 英文名
    */
    public boolean ename_isBlank(){
        return StrUtil.isBlank(this.ename);
    }

    /**
    * 字段数据类型
    */
    public String getDataType(){
        return this.dataType;
    }
    /**
    * 字段数据类型
    */
    public void setDataType(String value){
        this.dataType=value;
    }
    /**
    * 字段数据类型
    */
    public boolean dataType_is(String value){
        return Objects.equals(this.dataType, value);
    }
    /**
    * 字段数据类型
    */
    public ProjectModelField dataType(String value){
        this.dataType=value;
        return this;
    }
    /**
    * 字段数据类型
    */
    public FieldValid<ProjectModelField, String> dataType_fv(String value){
        return new FieldValid<>(this, value, () -> dataType(value));
    }
    /**
    * 字段数据类型
    */
    public ProjectModelField dataType_valid(IValidator<String> validator, String msg){
        validator.valid(this.dataType, msg);
        return this;
    }
    /**
    * 字段数据类型 如果为null返回默认值 value or default
    */
    public String dataType_vd(){
        if(null==this.dataType){
            return "";
        }
        return this.dataType;
    }
    /**
    * 字段数据类型 如果为null返回默认值 value or default
    */
    public String dataType_vd(String defaultValue){
        if(null==this.dataType){
            return defaultValue;
        }
        return this.dataType;
    }
    /**
    * 字段数据类型 int
    */
    public void setDataType_typeInt(){
        this.dataType="int";
    }
    /**
    * 字段数据类型 int
    */
    public ProjectModelField dataType_typeInt(){
        this.dataType="int";
        return this;
    }
    /**
    * 字段数据类型 int
    */
    public boolean dataType_is_typeInt(){
        return Objects.equals(this.dataType, "int");
    }
    /**
    * 字段数据类型 long
    */
    public void setDataType_typeLong(){
        this.dataType="long";
    }
    /**
    * 字段数据类型 long
    */
    public ProjectModelField dataType_typeLong(){
        this.dataType="long";
        return this;
    }
    /**
    * 字段数据类型 long
    */
    public boolean dataType_is_typeLong(){
        return Objects.equals(this.dataType, "long");
    }
    /**
    * 字段数据类型 string
    */
    public void setDataType_typeString(){
        this.dataType="string";
    }
    /**
    * 字段数据类型 string
    */
    public ProjectModelField dataType_typeString(){
        this.dataType="string";
        return this;
    }
    /**
    * 字段数据类型 string
    */
    public boolean dataType_is_typeString(){
        return Objects.equals(this.dataType, "string");
    }
    /**
    * 字段数据类型 double
    */
    public void setDataType_typeDouble(){
        this.dataType="double";
    }
    /**
    * 字段数据类型 double
    */
    public ProjectModelField dataType_typeDouble(){
        this.dataType="double";
        return this;
    }
    /**
    * 字段数据类型 double
    */
    public boolean dataType_is_typeDouble(){
        return Objects.equals(this.dataType, "double");
    }
    /**
    * 字段数据类型 decimal
    */
    public void setDataType_typeDecimal(){
        this.dataType="decimal";
    }
    /**
    * 字段数据类型 decimal
    */
    public ProjectModelField dataType_typeDecimal(){
        this.dataType="decimal";
        return this;
    }
    /**
    * 字段数据类型 decimal
    */
    public boolean dataType_is_typeDecimal(){
        return Objects.equals(this.dataType, "decimal");
    }
    /**
    * 字段数据类型 timestamp
    */
    public void setDataType_typeTimestamp(){
        this.dataType="timestamp";
    }
    /**
    * 字段数据类型 timestamp
    */
    public ProjectModelField dataType_typeTimestamp(){
        this.dataType="timestamp";
        return this;
    }
    /**
    * 字段数据类型 timestamp
    */
    public boolean dataType_is_typeTimestamp(){
        return Objects.equals(this.dataType, "timestamp");
    }
    /**
    * 字段数据类型 list
    */
    public void setDataType_typeList(){
        this.dataType="list";
    }
    /**
    * 字段数据类型 list
    */
    public ProjectModelField dataType_typeList(){
        this.dataType="list";
        return this;
    }
    /**
    * 字段数据类型 list
    */
    public boolean dataType_is_typeList(){
        return Objects.equals(this.dataType, "list");
    }
    /**
    * 字段数据类型 set
    */
    public void setDataType_typeSet(){
        this.dataType="set";
    }
    /**
    * 字段数据类型 set
    */
    public ProjectModelField dataType_typeSet(){
        this.dataType="set";
        return this;
    }
    /**
    * 字段数据类型 set
    */
    public boolean dataType_is_typeSet(){
        return Objects.equals(this.dataType, "set");
    }
    /**
    * 字段数据类型 map
    */
    public void setDataType_typeMap(){
        this.dataType="map";
    }
    /**
    * 字段数据类型 map
    */
    public ProjectModelField dataType_typeMap(){
        this.dataType="map";
        return this;
    }
    /**
    * 字段数据类型 map
    */
    public boolean dataType_is_typeMap(){
        return Objects.equals(this.dataType, "map");
    }
    /**
    * 字段数据类型
    */
    public boolean dataType_isNull(){
        return null==this.dataType;
    }
    /**
    * 字段数据类型
    */
    public boolean dataType_isBlank(){
        return StrUtil.isBlank(this.dataType);
    }

    /**
    * map字段key
    */
    public String getDataTypeMapKey(){
        return this.dataTypeMapKey;
    }
    /**
    * map字段key
    */
    public void setDataTypeMapKey(String value){
        this.dataTypeMapKey=value;
    }
    /**
    * map字段key
    */
    public boolean dataTypeMapKey_is(String value){
        return Objects.equals(this.dataTypeMapKey, value);
    }
    /**
    * map字段key
    */
    public ProjectModelField dataTypeMapKey(String value){
        this.dataTypeMapKey=value;
        return this;
    }
    /**
    * map字段key
    */
    public FieldValid<ProjectModelField, String> dataTypeMapKey_fv(String value){
        return new FieldValid<>(this, value, () -> dataTypeMapKey(value));
    }
    /**
    * map字段key
    */
    public ProjectModelField dataTypeMapKey_valid(IValidator<String> validator, String msg){
        validator.valid(this.dataTypeMapKey, msg);
        return this;
    }
    /**
    * map字段key 如果为null返回默认值 value or default
    */
    public String dataTypeMapKey_vd(){
        if(null==this.dataTypeMapKey){
            return "";
        }
        return this.dataTypeMapKey;
    }
    /**
    * map字段key 如果为null返回默认值 value or default
    */
    public String dataTypeMapKey_vd(String defaultValue){
        if(null==this.dataTypeMapKey){
            return defaultValue;
        }
        return this.dataTypeMapKey;
    }
    /**
    * map字段key int
    */
    public void setDataTypeMapKey_typeInt(){
        this.dataTypeMapKey="int";
    }
    /**
    * map字段key int
    */
    public ProjectModelField dataTypeMapKey_typeInt(){
        this.dataTypeMapKey="int";
        return this;
    }
    /**
    * map字段key int
    */
    public boolean dataTypeMapKey_is_typeInt(){
        return Objects.equals(this.dataTypeMapKey, "int");
    }
    /**
    * map字段key string
    */
    public void setDataTypeMapKey_typeString(){
        this.dataTypeMapKey="string";
    }
    /**
    * map字段key string
    */
    public ProjectModelField dataTypeMapKey_typeString(){
        this.dataTypeMapKey="string";
        return this;
    }
    /**
    * map字段key string
    */
    public boolean dataTypeMapKey_is_typeString(){
        return Objects.equals(this.dataTypeMapKey, "string");
    }
    /**
    * map字段key
    */
    public boolean dataTypeMapKey_isNull(){
        return null==this.dataTypeMapKey;
    }
    /**
    * map字段key
    */
    public boolean dataTypeMapKey_isBlank(){
        return StrUtil.isBlank(this.dataTypeMapKey);
    }

    /**
    * list, set, map 字段value
    */
    public String getDataTypeValue(){
        return this.dataTypeValue;
    }
    /**
    * list, set, map 字段value
    */
    public void setDataTypeValue(String value){
        this.dataTypeValue=value;
    }
    /**
    * list, set, map 字段value
    */
    public boolean dataTypeValue_is(String value){
        return Objects.equals(this.dataTypeValue, value);
    }
    /**
    * list, set, map 字段value
    */
    public ProjectModelField dataTypeValue(String value){
        this.dataTypeValue=value;
        return this;
    }
    /**
    * list, set, map 字段value
    */
    public FieldValid<ProjectModelField, String> dataTypeValue_fv(String value){
        return new FieldValid<>(this, value, () -> dataTypeValue(value));
    }
    /**
    * list, set, map 字段value
    */
    public ProjectModelField dataTypeValue_valid(IValidator<String> validator, String msg){
        validator.valid(this.dataTypeValue, msg);
        return this;
    }
    /**
    * list, set, map 字段value 如果为null返回默认值 value or default
    */
    public String dataTypeValue_vd(){
        if(null==this.dataTypeValue){
            return "";
        }
        return this.dataTypeValue;
    }
    /**
    * list, set, map 字段value 如果为null返回默认值 value or default
    */
    public String dataTypeValue_vd(String defaultValue){
        if(null==this.dataTypeValue){
            return defaultValue;
        }
        return this.dataTypeValue;
    }
    /**
    * list, set, map 字段value
    */
    public boolean dataTypeValue_isNull(){
        return null==this.dataTypeValue;
    }
    /**
    * list, set, map 字段value
    */
    public boolean dataTypeValue_isBlank(){
        return StrUtil.isBlank(this.dataTypeValue);
    }

    /**
    * 数据库类型
    */
    public String getDbType(){
        return this.dbType;
    }
    /**
    * 数据库类型
    */
    public void setDbType(String value){
        this.dbType=value;
    }
    /**
    * 数据库类型
    */
    public boolean dbType_is(String value){
        return Objects.equals(this.dbType, value);
    }
    /**
    * 数据库类型
    */
    public ProjectModelField dbType(String value){
        this.dbType=value;
        return this;
    }
    /**
    * 数据库类型
    */
    public FieldValid<ProjectModelField, String> dbType_fv(String value){
        return new FieldValid<>(this, value, () -> dbType(value));
    }
    /**
    * 数据库类型
    */
    public ProjectModelField dbType_valid(IValidator<String> validator, String msg){
        validator.valid(this.dbType, msg);
        return this;
    }
    /**
    * 数据库类型 如果为null返回默认值 value or default
    */
    public String dbType_vd(){
        if(null==this.dbType){
            return "";
        }
        return this.dbType;
    }
    /**
    * 数据库类型 如果为null返回默认值 value or default
    */
    public String dbType_vd(String defaultValue){
        if(null==this.dbType){
            return defaultValue;
        }
        return this.dbType;
    }
    /**
    * 数据库类型 int
    */
    public void setDbType_typeInt(){
        this.dbType="int";
    }
    /**
    * 数据库类型 int
    */
    public ProjectModelField dbType_typeInt(){
        this.dbType="int";
        return this;
    }
    /**
    * 数据库类型 int
    */
    public boolean dbType_is_typeInt(){
        return Objects.equals(this.dbType, "int");
    }
    /**
    * 数据库类型 bigint
    */
    public void setDbType_typeBigint(){
        this.dbType="bigint";
    }
    /**
    * 数据库类型 bigint
    */
    public ProjectModelField dbType_typeBigint(){
        this.dbType="bigint";
        return this;
    }
    /**
    * 数据库类型 bigint
    */
    public boolean dbType_is_typeBigint(){
        return Objects.equals(this.dbType, "bigint");
    }
    /**
    * 数据库类型 char
    */
    public void setDbType_typeChar(){
        this.dbType="char";
    }
    /**
    * 数据库类型 char
    */
    public ProjectModelField dbType_typeChar(){
        this.dbType="char";
        return this;
    }
    /**
    * 数据库类型 char
    */
    public boolean dbType_is_typeChar(){
        return Objects.equals(this.dbType, "char");
    }
    /**
    * 数据库类型 varchar
    */
    public void setDbType_typeVarchar(){
        this.dbType="varchar";
    }
    /**
    * 数据库类型 varchar
    */
    public ProjectModelField dbType_typeVarchar(){
        this.dbType="varchar";
        return this;
    }
    /**
    * 数据库类型 varchar
    */
    public boolean dbType_is_typeVarchar(){
        return Objects.equals(this.dbType, "varchar");
    }
    /**
    * 数据库类型 double
    */
    public void setDbType_typeDouble(){
        this.dbType="double";
    }
    /**
    * 数据库类型 double
    */
    public ProjectModelField dbType_typeDouble(){
        this.dbType="double";
        return this;
    }
    /**
    * 数据库类型 double
    */
    public boolean dbType_is_typeDouble(){
        return Objects.equals(this.dbType, "double");
    }
    /**
    * 数据库类型 decimal
    */
    public void setDbType_typeDecimal(){
        this.dbType="decimal";
    }
    /**
    * 数据库类型 decimal
    */
    public ProjectModelField dbType_typeDecimal(){
        this.dbType="decimal";
        return this;
    }
    /**
    * 数据库类型 decimal
    */
    public boolean dbType_is_typeDecimal(){
        return Objects.equals(this.dbType, "decimal");
    }
    /**
    * 数据库类型 timestamp
    */
    public void setDbType_typeTimestamp(){
        this.dbType="timestamp";
    }
    /**
    * 数据库类型 timestamp
    */
    public ProjectModelField dbType_typeTimestamp(){
        this.dbType="timestamp";
        return this;
    }
    /**
    * 数据库类型 timestamp
    */
    public boolean dbType_is_typeTimestamp(){
        return Objects.equals(this.dbType, "timestamp");
    }
    /**
    * 数据库类型 text
    */
    public void setDbType_typeText(){
        this.dbType="text";
    }
    /**
    * 数据库类型 text
    */
    public ProjectModelField dbType_typeText(){
        this.dbType="text";
        return this;
    }
    /**
    * 数据库类型 text
    */
    public boolean dbType_is_typeText(){
        return Objects.equals(this.dbType, "text");
    }
    /**
    * 数据库类型
    */
    public boolean dbType_isNull(){
        return null==this.dbType;
    }
    /**
    * 数据库类型
    */
    public boolean dbType_isBlank(){
        return StrUtil.isBlank(this.dbType);
    }

    /**
    * 数据库类型扩展信息
    */
    public String getDbTypeExt(){
        return this.dbTypeExt;
    }
    /**
    * 数据库类型扩展信息
    */
    public void setDbTypeExt(String value){
        this.dbTypeExt=value;
    }
    /**
    * 数据库类型扩展信息
    */
    public boolean dbTypeExt_is(String value){
        return Objects.equals(this.dbTypeExt, value);
    }
    /**
    * 数据库类型扩展信息
    */
    public ProjectModelField dbTypeExt(String value){
        this.dbTypeExt=value;
        return this;
    }
    /**
    * 数据库类型扩展信息
    */
    public FieldValid<ProjectModelField, String> dbTypeExt_fv(String value){
        return new FieldValid<>(this, value, () -> dbTypeExt(value));
    }
    /**
    * 数据库类型扩展信息
    */
    public ProjectModelField dbTypeExt_valid(IValidator<String> validator, String msg){
        validator.valid(this.dbTypeExt, msg);
        return this;
    }
    /**
    * 数据库类型扩展信息 如果为null返回默认值 value or default
    */
    public String dbTypeExt_vd(){
        if(null==this.dbTypeExt){
            return "";
        }
        return this.dbTypeExt;
    }
    /**
    * 数据库类型扩展信息 如果为null返回默认值 value or default
    */
    public String dbTypeExt_vd(String defaultValue){
        if(null==this.dbTypeExt){
            return defaultValue;
        }
        return this.dbTypeExt;
    }
    /**
    * 数据库类型扩展信息
    */
    public boolean dbTypeExt_isNull(){
        return null==this.dbTypeExt;
    }
    /**
    * 数据库类型扩展信息
    */
    public boolean dbTypeExt_isBlank(){
        return StrUtil.isBlank(this.dbTypeExt);
    }

    /**
    * DDL语句中的扩展信息
    */
    public String getCreateExt(){
        return this.createExt;
    }
    /**
    * DDL语句中的扩展信息
    */
    public void setCreateExt(String value){
        this.createExt=value;
    }
    /**
    * DDL语句中的扩展信息
    */
    public boolean createExt_is(String value){
        return Objects.equals(this.createExt, value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public ProjectModelField createExt(String value){
        this.createExt=value;
        return this;
    }
    /**
    * DDL语句中的扩展信息
    */
    public FieldValid<ProjectModelField, String> createExt_fv(String value){
        return new FieldValid<>(this, value, () -> createExt(value));
    }
    /**
    * DDL语句中的扩展信息
    */
    public ProjectModelField createExt_valid(IValidator<String> validator, String msg){
        validator.valid(this.createExt, msg);
        return this;
    }
    /**
    * DDL语句中的扩展信息 如果为null返回默认值 value or default
    */
    public String createExt_vd(){
        if(null==this.createExt){
            return "";
        }
        return this.createExt;
    }
    /**
    * DDL语句中的扩展信息 如果为null返回默认值 value or default
    */
    public String createExt_vd(String defaultValue){
        if(null==this.createExt){
            return defaultValue;
        }
        return this.createExt;
    }
    /**
    * DDL语句中的扩展信息
    */
    public boolean createExt_isNull(){
        return null==this.createExt;
    }
    /**
    * DDL语句中的扩展信息
    */
    public boolean createExt_isBlank(){
        return StrUtil.isBlank(this.createExt);
    }

    /**
    * 关联column
    */
    public String getRefColumn(){
        return this.refColumn;
    }
    /**
    * 关联column
    */
    public void setRefColumn(String value){
        this.refColumn=value;
    }
    /**
    * 关联column
    */
    public boolean refColumn_is(String value){
        return Objects.equals(this.refColumn, value);
    }
    /**
    * 关联column
    */
    public ProjectModelField refColumn(String value){
        this.refColumn=value;
        return this;
    }
    /**
    * 关联column
    */
    public FieldValid<ProjectModelField, String> refColumn_fv(String value){
        return new FieldValid<>(this, value, () -> refColumn(value));
    }
    /**
    * 关联column
    */
    public ProjectModelField refColumn_valid(IValidator<String> validator, String msg){
        validator.valid(this.refColumn, msg);
        return this;
    }
    /**
    * 关联column 如果为null返回默认值 value or default
    */
    public String refColumn_vd(){
        if(null==this.refColumn){
            return "";
        }
        return this.refColumn;
    }
    /**
    * 关联column 如果为null返回默认值 value or default
    */
    public String refColumn_vd(String defaultValue){
        if(null==this.refColumn){
            return defaultValue;
        }
        return this.refColumn;
    }
    /**
    * 关联column
    */
    public boolean refColumn_isNull(){
        return null==this.refColumn;
    }
    /**
    * 关联column
    */
    public boolean refColumn_isBlank(){
        return StrUtil.isBlank(this.refColumn);
    }

    /**
    * 默认值
    */
    public String getDefValue(){
        return this.defValue;
    }
    /**
    * 默认值
    */
    public void setDefValue(String value){
        this.defValue=value;
    }
    /**
    * 默认值
    */
    public boolean defValue_is(String value){
        return Objects.equals(this.defValue, value);
    }
    /**
    * 默认值
    */
    public ProjectModelField defValue(String value){
        this.defValue=value;
        return this;
    }
    /**
    * 默认值
    */
    public FieldValid<ProjectModelField, String> defValue_fv(String value){
        return new FieldValid<>(this, value, () -> defValue(value));
    }
    /**
    * 默认值
    */
    public ProjectModelField defValue_valid(IValidator<String> validator, String msg){
        validator.valid(this.defValue, msg);
        return this;
    }
    /**
    * 默认值 如果为null返回默认值 value or default
    */
    public String defValue_vd(){
        if(null==this.defValue){
            return "";
        }
        return this.defValue;
    }
    /**
    * 默认值 如果为null返回默认值 value or default
    */
    public String defValue_vd(String defaultValue){
        if(null==this.defValue){
            return defaultValue;
        }
        return this.defValue;
    }
    /**
    * 默认值
    */
    public boolean defValue_isNull(){
        return null==this.defValue;
    }
    /**
    * 默认值
    */
    public boolean defValue_isBlank(){
        return StrUtil.isBlank(this.defValue);
    }

    /**
    * imports
    */
    public String getImports(){
        return this.imports;
    }
    /**
    * imports
    */
    public void setImports(String value){
        this.imports=value;
    }
    /**
    * imports
    */
    public boolean imports_is(String value){
        return Objects.equals(this.imports, value);
    }
    /**
    * imports
    */
    public ProjectModelField imports(String value){
        this.imports=value;
        return this;
    }
    /**
    * imports
    */
    public FieldValid<ProjectModelField, String> imports_fv(String value){
        return new FieldValid<>(this, value, () -> imports(value));
    }
    /**
    * imports
    */
    public ProjectModelField imports_valid(IValidator<String> validator, String msg){
        validator.valid(this.imports, msg);
        return this;
    }
    /**
    * imports 如果为null返回默认值 value or default
    */
    public String imports_vd(){
        if(null==this.imports){
            return "";
        }
        return this.imports;
    }
    /**
    * imports 如果为null返回默认值 value or default
    */
    public String imports_vd(String defaultValue){
        if(null==this.imports){
            return defaultValue;
        }
        return this.imports;
    }
    /**
    * imports
    */
    public boolean imports_isNull(){
        return null==this.imports;
    }
    /**
    * imports
    */
    public boolean imports_isBlank(){
        return StrUtil.isBlank(this.imports);
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
    public ProjectModelField dspOrder(Integer value){
        this.dspOrder=value;
        return this;
    }
    /**
    * 序号
    */
    public FieldValid<ProjectModelField, Integer> dspOrder_fv(Integer value){
        return new FieldValid<>(this, value, () -> dspOrder(value));
    }
    /**
    * 序号
    */
    public ProjectModelField dspOrder_valid(IValidator<Integer> validator, String msg){
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
    public ProjectModelField comment(String value){
        this.comment=value;
        return this;
    }
    /**
    * 备注
    */
    public FieldValid<ProjectModelField, String> comment_fv(String value){
        return new FieldValid<>(this, value, () -> comment(value));
    }
    /**
    * 备注
    */
    public ProjectModelField comment_valid(IValidator<String> validator, String msg){
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
    public ProjectModelField deleteStatus(Integer value){
        this.deleteStatus=value;
        return this;
    }
    /**
    * 删除状态
    */
    public FieldValid<ProjectModelField, Integer> deleteStatus_fv(Integer value){
        return new FieldValid<>(this, value, () -> deleteStatus(value));
    }
    /**
    * 删除状态
    */
    public ProjectModelField deleteStatus_valid(IValidator<Integer> validator, String msg){
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
    public ProjectModelField deleteStatus_deleted(){
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
    public ProjectModelField deleteStatus_unDeleted(){
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
    public ProjectModelField createTime(Timestamp value){
        this.createTime=value;
        return this;
    }
    /**
    * 创建时间
    */
    public FieldValid<ProjectModelField, Timestamp> createTime_fv(Timestamp value){
        return new FieldValid<>(this, value, () -> createTime(value));
    }
    /**
    * 创建时间
    */
    public ProjectModelField createTime_valid(IValidator<Timestamp> validator, String msg){
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
    public ProjectModelField updateTime(Timestamp value){
        this.updateTime=value;
        return this;
    }
    /**
    * 更新时间
    */
    public FieldValid<ProjectModelField, Timestamp> updateTime_fv(Timestamp value){
        return new FieldValid<>(this, value, () -> updateTime(value));
    }
    /**
    * 更新时间
    */
    public ProjectModelField updateTime_valid(IValidator<Timestamp> validator, String msg){
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
