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
* 注解
*/
@Table("project_model_field_anno")
public class ProjectModelFieldAnno extends Entity{
//    entity.setId();
//    entity.setProjectId();
//    entity.setProjectDbId();
//    entity.setProjectModelId();
//    entity.setProjectModelFieldId();
//    entity.setImports();
//    entity.setAnno();
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
//        .projectModelFieldId()
//        .imports()
//        .anno()
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
	* 字段id
	*/
	@Column("project_model_field_id")
	private String projectModelFieldId;
	public static final String _projectModelFieldId="project_model_field_id";
	/**
	* imports
	*/
	@Column("imports")
	private String imports;
	public static final String _imports="imports";
	/**
	* 注解
	*/
	@Column("anno")
	private String anno;
	public static final String _anno="anno";
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
    public ProjectModelFieldAnno id(String value){
        this.id=value;
        return this;
    }
    /**
    * id
    */
    public ProjectModelFieldAnno id(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(value)){
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
    public ProjectModelFieldAnno id_valid(IValidator<String> validator, String msg){
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
    public ProjectModelFieldAnno projectId(String value){
        this.projectId=value;
        return this;
    }
    /**
    * 项目id
    */
    public ProjectModelFieldAnno projectId(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(value)){
                    return this;
                }
            }
        }

        this.projectId=value;
        return this;
    }
    /**
    * 项目id
    */
    public ProjectModelFieldAnno projectId_valid(IValidator<String> validator, String msg){
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
    public ProjectModelFieldAnno projectDbId(String value){
        this.projectDbId=value;
        return this;
    }
    /**
    * 数据库id
    */
    public ProjectModelFieldAnno projectDbId(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(value)){
                    return this;
                }
            }
        }

        this.projectDbId=value;
        return this;
    }
    /**
    * 数据库id
    */
    public ProjectModelFieldAnno projectDbId_valid(IValidator<String> validator, String msg){
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
    public ProjectModelFieldAnno projectModelId(String value){
        this.projectModelId=value;
        return this;
    }
    /**
    * 数据模型id
    */
    public ProjectModelFieldAnno projectModelId(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(value)){
                    return this;
                }
            }
        }

        this.projectModelId=value;
        return this;
    }
    /**
    * 数据模型id
    */
    public ProjectModelFieldAnno projectModelId_valid(IValidator<String> validator, String msg){
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
    * 字段id
    */
    public String getProjectModelFieldId(){
        return this.projectModelFieldId;
    }
    /**
    * 字段id
    */
    public void setProjectModelFieldId(String value){
        this.projectModelFieldId=value;
    }
    /**
    * 字段id
    */
    public boolean projectModelFieldId_is(String value){
        return Objects.equals(this.projectModelFieldId, value);
    }
    /**
    * 字段id
    */
    public ProjectModelFieldAnno projectModelFieldId(String value){
        this.projectModelFieldId=value;
        return this;
    }
    /**
    * 字段id
    */
    public ProjectModelFieldAnno projectModelFieldId(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(value)){
                    return this;
                }
            }
        }

        this.projectModelFieldId=value;
        return this;
    }
    /**
    * 字段id
    */
    public ProjectModelFieldAnno projectModelFieldId_valid(IValidator<String> validator, String msg){
        validator.valid(this.projectModelFieldId, msg);
        return this;
    }
    /**
    * 字段id 如果为null返回默认值 value or default
    */
    public String projectModelFieldId_vd(){
        if(null==this.projectModelFieldId){
            return "";
        }
        return this.projectModelFieldId;
    }
    /**
    * 字段id 如果为null返回默认值 value or default
    */
    public String projectModelFieldId_vd(String defaultValue){
        if(null==this.projectModelFieldId){
            return defaultValue;
        }
        return this.projectModelFieldId;
    }
    /**
    * 字段id
    */
    public boolean projectModelFieldId_isNull(){
        return null==this.projectModelFieldId;
    }
    /**
    * 字段id
    */
    public boolean projectModelFieldId_isBlank(){
        return StrUtil.isBlank(this.projectModelFieldId);
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
    public ProjectModelFieldAnno imports(String value){
        this.imports=value;
        return this;
    }
    /**
    * imports
    */
    public ProjectModelFieldAnno imports(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(value)){
                    return this;
                }
            }
        }

        this.imports=value;
        return this;
    }
    /**
    * imports
    */
    public ProjectModelFieldAnno imports_valid(IValidator<String> validator, String msg){
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
    * 注解
    */
    public String getAnno(){
        return this.anno;
    }
    /**
    * 注解
    */
    public void setAnno(String value){
        this.anno=value;
    }
    /**
    * 注解
    */
    public boolean anno_is(String value){
        return Objects.equals(this.anno, value);
    }
    /**
    * 注解
    */
    public ProjectModelFieldAnno anno(String value){
        this.anno=value;
        return this;
    }
    /**
    * 注解
    */
    public ProjectModelFieldAnno anno(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(value)){
                    return this;
                }
            }
        }

        this.anno=value;
        return this;
    }
    /**
    * 注解
    */
    public ProjectModelFieldAnno anno_valid(IValidator<String> validator, String msg){
        validator.valid(this.anno, msg);
        return this;
    }
    /**
    * 注解 如果为null返回默认值 value or default
    */
    public String anno_vd(){
        if(null==this.anno){
            return "";
        }
        return this.anno;
    }
    /**
    * 注解 如果为null返回默认值 value or default
    */
    public String anno_vd(String defaultValue){
        if(null==this.anno){
            return defaultValue;
        }
        return this.anno;
    }
    /**
    * 注解
    */
    public boolean anno_isNull(){
        return null==this.anno;
    }
    /**
    * 注解
    */
    public boolean anno_isBlank(){
        return StrUtil.isBlank(this.anno);
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
    public ProjectModelFieldAnno dspOrder(Integer value){
        this.dspOrder=value;
        return this;
    }
    /**
    * 序号
    */
    public ProjectModelFieldAnno dspOrder(Integer value, IValidatorSuccess<Integer> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<Integer> itm : ivs){
                if(!itm.isValidSuccess(value)){
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
    public ProjectModelFieldAnno dspOrder_valid(IValidator<Integer> validator, String msg){
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
    public ProjectModelFieldAnno comment(String value){
        this.comment=value;
        return this;
    }
    /**
    * 备注
    */
    public ProjectModelFieldAnno comment(String value, IValidatorSuccess<String> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<String> itm : ivs){
                if(!itm.isValidSuccess(value)){
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
    public ProjectModelFieldAnno comment_valid(IValidator<String> validator, String msg){
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
    public ProjectModelFieldAnno deleteStatus(Integer value){
        this.deleteStatus=value;
        return this;
    }
    /**
    * 删除状态
    */
    public ProjectModelFieldAnno deleteStatus(Integer value, IValidatorSuccess<Integer> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<Integer> itm : ivs){
                if(!itm.isValidSuccess(value)){
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
    public ProjectModelFieldAnno deleteStatus_valid(IValidator<Integer> validator, String msg){
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
    public ProjectModelFieldAnno deleteStatus_deleted(){
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
    public ProjectModelFieldAnno deleteStatus_unDeleted(){
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
    public ProjectModelFieldAnno createTime(Timestamp value){
        this.createTime=value;
        return this;
    }
    /**
    * 创建时间
    */
    public ProjectModelFieldAnno createTime(Timestamp value, IValidatorSuccess<Timestamp> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<Timestamp> itm : ivs){
                if(!itm.isValidSuccess(value)){
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
    public ProjectModelFieldAnno createTime_valid(IValidator<Timestamp> validator, String msg){
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
    public ProjectModelFieldAnno updateTime(Timestamp value){
        this.updateTime=value;
        return this;
    }
    /**
    * 更新时间
    */
    public ProjectModelFieldAnno updateTime(Timestamp value, IValidatorSuccess<Timestamp> ... ivs){
        if(null != ivs){
            for(IValidatorSuccess<Timestamp> itm : ivs){
                if(!itm.isValidSuccess(value)){
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
    public ProjectModelFieldAnno updateTime_valid(IValidator<Timestamp> validator, String msg){
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
