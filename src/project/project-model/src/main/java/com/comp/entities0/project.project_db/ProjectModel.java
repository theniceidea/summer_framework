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
* 数据模型
*/
@Table("project_model")
public class ProjectModel extends Entity{
//    entity.setId();
//    entity.setProjectId();
//    entity.setProjectDbId();
//    entity.setModelType();
//    entity.setTableModel();
//    entity.setSummerModel();
//    entity.setSummerNs();
//    entity.setImports();
//    entity.setCname();
//    entity.setEname();
//    entity.setExtendsInfo();
//    entity.setDspOrder();
//    entity.setComment();
//    entity.setDeleteStatus();
//    entity.setCreateTime();
//    entity.setUpdateTime();
//    entity
//        .id()
//        .projectId()
//        .projectDbId()
//        .modelType()
//        .tableModel()
//        .summerModel()
//        .summerNs()
//        .imports()
//        .cname()
//        .ename()
//        .extendsInfo()
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
	* 类别
	*/
	@Column("model_type")
	private Integer modelType;
	public static final String _modelType="model_type";
	/**
	* 是否数据库表mapping
	*/
	@Column("table_model")
	private Integer tableModel;
	public static final String _tableModel="table_model";
	/**
	* 是否summer对象
	*/
	@Column("summer_model")
	private Integer summerModel;
	public static final String _summerModel="summer_model";
	/**
	* summer对象名称空间
	*/
	@Column("summer_ns")
	private String summerNs;
	public static final String _summerNs="summer_ns";
	/**
	* imports
	*/
	@Column("imports")
	private String imports;
	public static final String _imports="imports";
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
	* 继承信息
	*/
	@Column("extends_info")
	private String extendsInfo;
	public static final String _extendsInfo="extends_info";
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
    public ProjectModel id(String value){
        this.id=value;
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
    public ProjectModel projectId(String value){
        this.projectId=value;
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
    public ProjectModel projectDbId(String value){
        this.projectDbId=value;
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
    * 类别
    */
    public Integer getModelType(){
        return this.modelType;
    }
    /**
    * 类别
    */
    public void setModelType(Integer value){
        this.modelType=value;
    }
    /**
    * 类别
    */
    public boolean modelType_is(Integer value){
        return Objects.equals(this.modelType, value);
    }
    /**
    * 类别
    */
    public ProjectModel modelType(Integer value){
        this.modelType=value;
        return this;
    }
    /**
    * 类别 如果为null返回默认值 value or default
    */
    public int modelType_vd(){
        if(null==this.modelType){
            return 0;
        }
        return this.modelType;
    }
    /**
    * 类别 如果为null返回默认值 value or default
    */
    public int modelType_vd(int defaultValue){
        if(null==this.modelType){
            return defaultValue;
        }
        return this.modelType;
    }
    /**
    * 类别 类
    */
    public void setModelType_typeClass(){
        this.modelType=1;
    }
    /**
    * 类别 类
    */
    public ProjectModel modelType_typeClass(){
        this.modelType=1;
        return this;
    }
    /**
    * 类别 类
    */
    public boolean modelType_is_typeClass(){
        return Objects.equals(this.modelType, 1);
    }
    /**
    * 类别 枚举
    */
    public void setModelType_typeEnum(){
        this.modelType=2;
    }
    /**
    * 类别 枚举
    */
    public ProjectModel modelType_typeEnum(){
        this.modelType=2;
        return this;
    }
    /**
    * 类别 枚举
    */
    public boolean modelType_is_typeEnum(){
        return Objects.equals(this.modelType, 2);
    }
    /**
    * 类别
    */
    public boolean modelType_isNull(){
        return null==this.modelType;
    }

    /**
    * 是否数据库表mapping
    */
    public Integer getTableModel(){
        return this.tableModel;
    }
    /**
    * 是否数据库表mapping
    */
    public void setTableModel(Integer value){
        this.tableModel=value;
    }
    /**
    * 是否数据库表mapping
    */
    public boolean tableModel_is(Integer value){
        return Objects.equals(this.tableModel, value);
    }
    /**
    * 是否数据库表mapping
    */
    public ProjectModel tableModel(Integer value){
        this.tableModel=value;
        return this;
    }
    /**
    * 是否数据库表mapping 如果为null返回默认值 value or default
    */
    public int tableModel_vd(){
        if(null==this.tableModel){
            return 0;
        }
        return this.tableModel;
    }
    /**
    * 是否数据库表mapping 如果为null返回默认值 value or default
    */
    public int tableModel_vd(int defaultValue){
        if(null==this.tableModel){
            return defaultValue;
        }
        return this.tableModel;
    }
    /**
    * 是否数据库表mapping 数据库表
    */
    public void setTableModel_dbTable(){
        this.tableModel=1;
    }
    /**
    * 是否数据库表mapping 数据库表
    */
    public ProjectModel tableModel_dbTable(){
        this.tableModel=1;
        return this;
    }
    /**
    * 是否数据库表mapping 数据库表
    */
    public boolean tableModel_is_dbTable(){
        return Objects.equals(this.tableModel, 1);
    }
    /**
    * 是否数据库表mapping 非数据库表
    */
    public void setTableModel_noDbTable(){
        this.tableModel=2;
    }
    /**
    * 是否数据库表mapping 非数据库表
    */
    public ProjectModel tableModel_noDbTable(){
        this.tableModel=2;
        return this;
    }
    /**
    * 是否数据库表mapping 非数据库表
    */
    public boolean tableModel_is_noDbTable(){
        return Objects.equals(this.tableModel, 2);
    }
    /**
    * 是否数据库表mapping
    */
    public boolean tableModel_isNull(){
        return null==this.tableModel;
    }

    /**
    * 是否summer对象
    */
    public Integer getSummerModel(){
        return this.summerModel;
    }
    /**
    * 是否summer对象
    */
    public void setSummerModel(Integer value){
        this.summerModel=value;
    }
    /**
    * 是否summer对象
    */
    public boolean summerModel_is(Integer value){
        return Objects.equals(this.summerModel, value);
    }
    /**
    * 是否summer对象
    */
    public ProjectModel summerModel(Integer value){
        this.summerModel=value;
        return this;
    }
    /**
    * 是否summer对象 如果为null返回默认值 value or default
    */
    public int summerModel_vd(){
        if(null==this.summerModel){
            return 0;
        }
        return this.summerModel;
    }
    /**
    * 是否summer对象 如果为null返回默认值 value or default
    */
    public int summerModel_vd(int defaultValue){
        if(null==this.summerModel){
            return defaultValue;
        }
        return this.summerModel;
    }
    /**
    * 是否summer对象 summer
    */
    public void setSummerModel_summer(){
        this.summerModel=1;
    }
    /**
    * 是否summer对象 summer
    */
    public ProjectModel summerModel_summer(){
        this.summerModel=1;
        return this;
    }
    /**
    * 是否summer对象 summer
    */
    public boolean summerModel_is_summer(){
        return Objects.equals(this.summerModel, 1);
    }
    /**
    * 是否summer对象 非summer对象
    */
    public void setSummerModel_noSummer(){
        this.summerModel=2;
    }
    /**
    * 是否summer对象 非summer对象
    */
    public ProjectModel summerModel_noSummer(){
        this.summerModel=2;
        return this;
    }
    /**
    * 是否summer对象 非summer对象
    */
    public boolean summerModel_is_noSummer(){
        return Objects.equals(this.summerModel, 2);
    }
    /**
    * 是否summer对象
    */
    public boolean summerModel_isNull(){
        return null==this.summerModel;
    }

    /**
    * summer对象名称空间
    */
    public String getSummerNs(){
        return this.summerNs;
    }
    /**
    * summer对象名称空间
    */
    public void setSummerNs(String value){
        this.summerNs=value;
    }
    /**
    * summer对象名称空间
    */
    public boolean summerNs_is(String value){
        return Objects.equals(this.summerNs, value);
    }
    /**
    * summer对象名称空间
    */
    public ProjectModel summerNs(String value){
        this.summerNs=value;
        return this;
    }
    /**
    * summer对象名称空间 如果为null返回默认值 value or default
    */
    public String summerNs_vd(){
        if(null==this.summerNs){
            return "";
        }
        return this.summerNs;
    }
    /**
    * summer对象名称空间 如果为null返回默认值 value or default
    */
    public String summerNs_vd(String defaultValue){
        if(null==this.summerNs){
            return defaultValue;
        }
        return this.summerNs;
    }
    /**
    * summer对象名称空间 local
    */
    public void setSummerNs_local(){
        this.summerNs="local";
    }
    /**
    * summer对象名称空间 local
    */
    public ProjectModel summerNs_local(){
        this.summerNs="local";
        return this;
    }
    /**
    * summer对象名称空间 local
    */
    public boolean summerNs_is_local(){
        return Objects.equals(this.summerNs, "local");
    }
    /**
    * summer对象名称空间 inner
    */
    public void setSummerNs_inner(){
        this.summerNs="inner";
    }
    /**
    * summer对象名称空间 inner
    */
    public ProjectModel summerNs_inner(){
        this.summerNs="inner";
        return this;
    }
    /**
    * summer对象名称空间 inner
    */
    public boolean summerNs_is_inner(){
        return Objects.equals(this.summerNs, "inner");
    }
    /**
    * summer对象名称空间 cms
    */
    public void setSummerNs_cms(){
        this.summerNs="cms";
    }
    /**
    * summer对象名称空间 cms
    */
    public ProjectModel summerNs_cms(){
        this.summerNs="cms";
        return this;
    }
    /**
    * summer对象名称空间 cms
    */
    public boolean summerNs_is_cms(){
        return Objects.equals(this.summerNs, "cms");
    }
    /**
    * summer对象名称空间 portal
    */
    public void setSummerNs_portal(){
        this.summerNs="portal";
    }
    /**
    * summer对象名称空间 portal
    */
    public ProjectModel summerNs_portal(){
        this.summerNs="portal";
        return this;
    }
    /**
    * summer对象名称空间 portal
    */
    public boolean summerNs_is_portal(){
        return Objects.equals(this.summerNs, "portal");
    }
    /**
    * summer对象名称空间
    */
    public boolean summerNs_isNull(){
        return null==this.summerNs;
    }
    /**
    * summer对象名称空间
    */
    public boolean summerNs_isBlank(){
        return StrUtil.isBlank(this.summerNs);
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
    public ProjectModel imports(String value){
        this.imports=value;
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
    public ProjectModel cname(String value){
        this.cname=value;
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
    public ProjectModel ename(String value){
        this.ename=value;
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
    * 继承信息
    */
    public String getExtendsInfo(){
        return this.extendsInfo;
    }
    /**
    * 继承信息
    */
    public void setExtendsInfo(String value){
        this.extendsInfo=value;
    }
    /**
    * 继承信息
    */
    public boolean extendsInfo_is(String value){
        return Objects.equals(this.extendsInfo, value);
    }
    /**
    * 继承信息
    */
    public ProjectModel extendsInfo(String value){
        this.extendsInfo=value;
        return this;
    }
    /**
    * 继承信息 如果为null返回默认值 value or default
    */
    public String extendsInfo_vd(){
        if(null==this.extendsInfo){
            return "";
        }
        return this.extendsInfo;
    }
    /**
    * 继承信息 如果为null返回默认值 value or default
    */
    public String extendsInfo_vd(String defaultValue){
        if(null==this.extendsInfo){
            return defaultValue;
        }
        return this.extendsInfo;
    }
    /**
    * 继承信息
    */
    public boolean extendsInfo_isNull(){
        return null==this.extendsInfo;
    }
    /**
    * 继承信息
    */
    public boolean extendsInfo_isBlank(){
        return StrUtil.isBlank(this.extendsInfo);
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
    public ProjectModel dspOrder(Integer value){
        this.dspOrder=value;
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
    public ProjectModel comment(String value){
        this.comment=value;
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
    public ProjectModel deleteStatus(Integer value){
        this.deleteStatus=value;
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
    public ProjectModel deleteStatus_deleted(){
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
    public ProjectModel deleteStatus_unDeleted(){
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
    public ProjectModel createTime(Timestamp value){
        this.createTime=value;
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
    public ProjectModel updateTime(Timestamp value){
        this.updateTime=value;
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
