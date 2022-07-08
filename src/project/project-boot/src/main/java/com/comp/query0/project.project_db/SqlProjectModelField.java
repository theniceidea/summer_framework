package com.comp.query0.project.project_db;

import cn.hutool.core.date.DateUtil;
import com.fmk.framework.exception.Excep;
import com.comp.entities0.project.project_db.*;
import com.fmk.framework.daomodel.*;
import com.fmk.framework.restful.PageResultList;
import cn.hutool.core.util.StrUtil;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import java.sql.Timestamp;
import java.math.BigDecimal;

/**
* 字段
*/
public class SqlProjectModelField implements QuerySelect {
    private static final String ID_SUFFIX="00A3";
    private static Object DB_TARGET=null;
    private Object instDbTarget__ =null;
    private static final String SQL_INSERT="INSERT INTO project_model_field(id,project_id,project_db_id,project_model_id,cname,ename,data_type,data_type_map_key,data_type_value,db_type,db_type_ext,create_ext,ref_column,def_value,imports,dsp_order,comment,delete_status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE project_model_field SET project_id=?,project_db_id=?,project_model_id=?,cname=?,ename=?,data_type=?,data_type_map_key=?,data_type_value=?,db_type=?,db_type_ext=?,create_ext=?,ref_column=?,def_value=?,imports=?,dsp_order=?,comment=?,delete_status=? WHERE id=?";
    private static final String SQL_DELETE="DELETE project_model_field WHERE id=?";
    private boolean existsWhere=false;
    private StringBuilder builder=new StringBuilder();
    private List<Object> parameters =new ArrayList<>();
    private List<IncUpdateValue> incValues=null;
    private Object ds(){
        if(null != instDbTarget__){
            return instDbTarget__;
        }

        if(null != DB_TARGET){
            return DB_TARGET;
        }
        DB_TARGET=DaoJdbcTemplate.s("project_db");
        return DB_TARGET;
    }

    public SqlProjectModelField changeDbTarget(String name){
        instDbTarget__ =DaoJdbcTemplate.s(name);
        return this;
    }

    public static SqlProjectModelField inst(){
        final SqlProjectModelField sqlProjectModelField = new SqlProjectModelField();
        return sqlProjectModelField;
    }
    
    private void addIncValue(String fieldName, Object value){
        if(null==incValues){
            incValues=new ArrayList<>(2);
        }
        final IncUpdateValue iuv = new IncUpdateValue();
        iuv.setFieldName(fieldName);
        iuv.setValue(value);
        incValues.add(iuv);
    }
    public int executeIncrementUnChangeThrow(String msg){
        final int count = executeIncrement();
        if(count<=0){
            throw Excep.le(msg);
        }
        return count;
    }

    public int executeIncrement(){
        StringBuilder buf=new StringBuilder("UPDATE project_model_field SET");
        boolean bln=false;
        List<Object> values=new ArrayList<>();
        for(IncUpdateValue iuv : incValues){
            if(!bln){
                bln=true;
            }else{
                buf.append(",");
            }
            buf.append(" ");
            buf.append(iuv.getFieldName());
            buf.append("=");
            buf.append(iuv.getFieldName());
            buf.append("+?");
            values.add(iuv.getValue());
        }
        buf.append(builder);
        values.addAll(parameters);

        return DaoExecuteChangeSql.s(ds(), buf.toString(), values);
    }
    public int save(ProjectModelField entity){
        if(null==entity.getId()){
            return insert(entity);
        }else{
            return update(entity);
        }
    }
    private int insert(ProjectModelField entity){
        List<Object> values = new ArrayList<>(18);
        values.add(entity.getId());
        values.add(entity.getProjectId());
        values.add(entity.getProjectDbId());
        values.add(entity.getProjectModelId());
        values.add(entity.getCname());
        values.add(entity.getEname());
        values.add(entity.getDataType());
        values.add(entity.getDataTypeMapKey());
        values.add(entity.getDataTypeValue());
        values.add(entity.getDbType());
        values.add(entity.getDbTypeExt());
        values.add(entity.getCreateExt());
        values.add(entity.getRefColumn());
        values.add(entity.getDefValue());
        values.add(entity.getImports());
        values.add(entity.getDspOrder());
        values.add(entity.getComment());
        values.add(entity.getDeleteStatus());

        int retNum = DaoExecuteChangeSql.s(ds(), SQL_INSERT, values);
        return retNum;
    }
    private int update(ProjectModelField entity){
        List<Object> values = new ArrayList<>(18);
        values.add(entity.getProjectId());
        values.add(entity.getProjectDbId());
        values.add(entity.getProjectModelId());
        values.add(entity.getCname());
        values.add(entity.getEname());
        values.add(entity.getDataType());
        values.add(entity.getDataTypeMapKey());
        values.add(entity.getDataTypeValue());
        values.add(entity.getDbType());
        values.add(entity.getDbTypeExt());
        values.add(entity.getCreateExt());
        values.add(entity.getRefColumn());
        values.add(entity.getDefValue());
        values.add(entity.getImports());
        values.add(entity.getDspOrder());
        values.add(entity.getComment());
        values.add(entity.getDeleteStatus());
        values.add(entity.getId());

        return DaoExecuteChangeSql.s(ds(), SQL_UPDATE, values);
    }
    
    public int del(ProjectModelField entity){
        List<Object> values = new ArrayList<>(1);
        values.add(entity.getId());
        return DaoExecuteChangeSql.s(ds(), SQL_DELETE, values);
    }
    public ProjectModelField queryOne(){
        final List<ProjectModelField> list = DaoList.s(ds(), ProjectModelField.class, this, 0, 1);
        return list.size()>0?list.get(0):null;
    }
    public List<ProjectModelField> queryList(){
        return DaoList.s(ds(), ProjectModelField.class, this);
    }
    public List<ProjectModelField> queryList(int start, int limit){
        return DaoList.s(ds(), ProjectModelField.class, this, start, limit);
    }
    public PageResultList<ProjectModelField> queryPageList(int start, int limit){
        return DaoPageList.s(ds(), ProjectModelField.class, this, start, limit);
    }
    /**
    * id
    */
    public SqlProjectModelField id_eq(boolean cdn, String value){
        if(!cdn) return this;
        return id_eq(value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" id=?");
        parameters.add(value);
        return this;
    }
    /**
    * id
    */
    public SqlProjectModelField id_notNull_eq(String value){
        return id_eq(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_isdb_null(boolean cdn){
        if(!cdn) return this;
        return id_isdb_null();
    }
    /**
    * id
    */
    public SqlProjectModelField id_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" id is null");
        return this;
    }
    /**
    * id
    */
    public SqlProjectModelField id_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return id_isdb_not_null();
    }
    /**
    * id
    */
    public SqlProjectModelField id_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" id is not null");
        return this;
    }
    /**
    * id
    */
    public SqlProjectModelField id_gt(boolean cdn, String value){
        if(!cdn) return this;
        return id_gt(value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" id>?");
        parameters.add(value);
        return this;
    }
    /**
    * id
    */
    public SqlProjectModelField id_notNull_gt(String value){
        return id_gt(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_lt(boolean cdn, String value){
        if(!cdn) return this;
        return id_lt(value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" id<?");
        parameters.add(value);
        return this;
    }
    /**
    * id
    */
    public SqlProjectModelField id_notNull_lt(String value){
        return id_lt(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_ge(boolean cdn, String value){
        if(!cdn) return this;
        return id_ge(value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" id>=?");
        parameters.add(value);
        return this;
    }
    /**
    * id
    */
    public SqlProjectModelField id_notNull_ge(String value){
        return id_ge(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_le(boolean cdn, String value){
        if(!cdn) return this;
        return id_le(value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" id<=?");
        parameters.add(value);
        return this;
    }
    /**
    * id
    */
    public SqlProjectModelField id_notNull_le(String value){
        return id_le(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_ne(boolean cdn, String value){
        if(!cdn) return this;
        return id_ne(value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" id<>?");
        parameters.add(value);
        return this;
    }
    /**
    * id
    */
    public SqlProjectModelField id_notNull_ne(String value){
        return id_ne(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_notBlank_eq(String value){
        return id_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return id_leftLike(value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" id like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * id
    */
    public SqlProjectModelField id_notBlank_leftLike(String value){
        return id_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return id_rightLike(value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" id like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * id
    */
    public SqlProjectModelField id_notBlank_rightLike(String value){
        return id_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return id_middleLike(value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" id like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * id
    */
    public SqlProjectModelField id_notBlank_middleLike(String value){
        return id_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProjectModelField id_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return id_in(values);
    }
    /**
    * id
    */
    public SqlProjectModelField id_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" id in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * id
    */
    public SqlProjectModelField id_notEmpty_in(Set<Object> values){
        return id_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 项目id
    */
    public SqlProjectModelField projectId_eq(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_eq(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_id=?");
        parameters.add(value);
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_notNull_eq(String value){
        return projectId_eq(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_isdb_null(boolean cdn){
        if(!cdn) return this;
        return projectId_isdb_null();
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_id is null");
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return projectId_isdb_not_null();
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_id is not null");
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_gt(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_gt(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_id>?");
        parameters.add(value);
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_notNull_gt(String value){
        return projectId_gt(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_lt(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_lt(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_id<?");
        parameters.add(value);
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_notNull_lt(String value){
        return projectId_lt(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_ge(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_ge(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_id>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_notNull_ge(String value){
        return projectId_ge(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_le(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_le(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_id<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_notNull_le(String value){
        return projectId_le(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_ne(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_ne(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_id<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_notNull_ne(String value){
        return projectId_ne(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_notBlank_eq(String value){
        return projectId_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_leftLike(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_id like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_notBlank_leftLike(String value){
        return projectId_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_rightLike(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_id like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_notBlank_rightLike(String value){
        return projectId_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_middleLike(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_id like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_notBlank_middleLike(String value){
        return projectId_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return projectId_in(values);
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" project_id in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelField projectId_notEmpty_in(Set<Object> values){
        return projectId_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_eq(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_eq(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_db_id=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_notNull_eq(String value){
        return projectDbId_eq(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_isdb_null(boolean cdn){
        if(!cdn) return this;
        return projectDbId_isdb_null();
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_db_id is null");
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return projectDbId_isdb_not_null();
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_db_id is not null");
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_gt(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_gt(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_db_id>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_notNull_gt(String value){
        return projectDbId_gt(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_lt(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_lt(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_db_id<?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_notNull_lt(String value){
        return projectDbId_lt(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_ge(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_ge(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_db_id>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_notNull_ge(String value){
        return projectDbId_ge(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_le(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_le(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_db_id<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_notNull_le(String value){
        return projectDbId_le(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_ne(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_ne(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_db_id<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_notNull_ne(String value){
        return projectDbId_ne(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_notBlank_eq(String value){
        return projectDbId_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_leftLike(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_db_id like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_notBlank_leftLike(String value){
        return projectDbId_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_rightLike(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_db_id like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_notBlank_rightLike(String value){
        return projectDbId_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_middleLike(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_db_id like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_notBlank_middleLike(String value){
        return projectDbId_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return projectDbId_in(values);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" project_db_id in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelField projectDbId_notEmpty_in(Set<Object> values){
        return projectDbId_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_eq(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_eq(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_id=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_notNull_eq(String value){
        return projectModelId_eq(null != value, value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_isdb_null(boolean cdn){
        if(!cdn) return this;
        return projectModelId_isdb_null();
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_id is null");
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return projectModelId_isdb_not_null();
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_id is not null");
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_gt(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_gt(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_id>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_notNull_gt(String value){
        return projectModelId_gt(null != value, value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_lt(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_lt(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_id<?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_notNull_lt(String value){
        return projectModelId_lt(null != value, value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_ge(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_ge(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_id>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_notNull_ge(String value){
        return projectModelId_ge(null != value, value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_le(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_le(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_id<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_notNull_le(String value){
        return projectModelId_le(null != value, value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_ne(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_ne(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_id<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_notNull_ne(String value){
        return projectModelId_ne(null != value, value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_notBlank_eq(String value){
        return projectModelId_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_leftLike(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_id like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_notBlank_leftLike(String value){
        return projectModelId_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_rightLike(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_id like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_notBlank_rightLike(String value){
        return projectModelId_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_middleLike(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_id like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_notBlank_middleLike(String value){
        return projectModelId_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return projectModelId_in(values);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" project_model_id in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelField projectModelId_notEmpty_in(Set<Object> values){
        return projectModelId_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 中文名
    */
    public SqlProjectModelField cname_eq(boolean cdn, String value){
        if(!cdn) return this;
        return cname_eq(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" cname=?");
        parameters.add(value);
        return this;
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_notNull_eq(String value){
        return cname_eq(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_isdb_null(boolean cdn){
        if(!cdn) return this;
        return cname_isdb_null();
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" cname is null");
        return this;
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return cname_isdb_not_null();
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" cname is not null");
        return this;
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_gt(boolean cdn, String value){
        if(!cdn) return this;
        return cname_gt(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" cname>?");
        parameters.add(value);
        return this;
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_notNull_gt(String value){
        return cname_gt(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_lt(boolean cdn, String value){
        if(!cdn) return this;
        return cname_lt(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" cname<?");
        parameters.add(value);
        return this;
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_notNull_lt(String value){
        return cname_lt(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_ge(boolean cdn, String value){
        if(!cdn) return this;
        return cname_ge(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" cname>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_notNull_ge(String value){
        return cname_ge(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_le(boolean cdn, String value){
        if(!cdn) return this;
        return cname_le(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" cname<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_notNull_le(String value){
        return cname_le(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_ne(boolean cdn, String value){
        if(!cdn) return this;
        return cname_ne(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" cname<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_notNull_ne(String value){
        return cname_ne(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_notBlank_eq(String value){
        return cname_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return cname_leftLike(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" cname like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_notBlank_leftLike(String value){
        return cname_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return cname_rightLike(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" cname like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_notBlank_rightLike(String value){
        return cname_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return cname_middleLike(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" cname like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_notBlank_middleLike(String value){
        return cname_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return cname_in(values);
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" cname in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 中文名
    */
    public SqlProjectModelField cname_notEmpty_in(Set<Object> values){
        return cname_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 英文名
    */
    public SqlProjectModelField ename_eq(boolean cdn, String value){
        if(!cdn) return this;
        return ename_eq(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ename=?");
        parameters.add(value);
        return this;
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_notNull_eq(String value){
        return ename_eq(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_isdb_null(boolean cdn){
        if(!cdn) return this;
        return ename_isdb_null();
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ename is null");
        return this;
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return ename_isdb_not_null();
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ename is not null");
        return this;
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_gt(boolean cdn, String value){
        if(!cdn) return this;
        return ename_gt(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ename>?");
        parameters.add(value);
        return this;
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_notNull_gt(String value){
        return ename_gt(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_lt(boolean cdn, String value){
        if(!cdn) return this;
        return ename_lt(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ename<?");
        parameters.add(value);
        return this;
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_notNull_lt(String value){
        return ename_lt(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_ge(boolean cdn, String value){
        if(!cdn) return this;
        return ename_ge(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ename>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_notNull_ge(String value){
        return ename_ge(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_le(boolean cdn, String value){
        if(!cdn) return this;
        return ename_le(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ename<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_notNull_le(String value){
        return ename_le(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_ne(boolean cdn, String value){
        if(!cdn) return this;
        return ename_ne(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ename<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_notNull_ne(String value){
        return ename_ne(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_notBlank_eq(String value){
        return ename_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return ename_leftLike(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ename like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_notBlank_leftLike(String value){
        return ename_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return ename_rightLike(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ename like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_notBlank_rightLike(String value){
        return ename_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return ename_middleLike(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ename like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_notBlank_middleLike(String value){
        return ename_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return ename_in(values);
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" ename in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 英文名
    */
    public SqlProjectModelField ename_notEmpty_in(Set<Object> values){
        return ename_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_eq(boolean cdn, String value){
        if(!cdn) return this;
        return dataType_eq(value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type=?");
        parameters.add(value);
        return this;
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_notNull_eq(String value){
        return dataType_eq(null != value, value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_isdb_null(boolean cdn){
        if(!cdn) return this;
        return dataType_isdb_null();
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type is null");
        return this;
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return dataType_isdb_not_null();
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type is not null");
        return this;
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_gt(boolean cdn, String value){
        if(!cdn) return this;
        return dataType_gt(value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type>?");
        parameters.add(value);
        return this;
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_notNull_gt(String value){
        return dataType_gt(null != value, value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_lt(boolean cdn, String value){
        if(!cdn) return this;
        return dataType_lt(value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type<?");
        parameters.add(value);
        return this;
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_notNull_lt(String value){
        return dataType_lt(null != value, value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_ge(boolean cdn, String value){
        if(!cdn) return this;
        return dataType_ge(value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_notNull_ge(String value){
        return dataType_ge(null != value, value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_le(boolean cdn, String value){
        if(!cdn) return this;
        return dataType_le(value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_notNull_le(String value){
        return dataType_le(null != value, value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_ne(boolean cdn, String value){
        if(!cdn) return this;
        return dataType_ne(value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_notNull_ne(String value){
        return dataType_ne(null != value, value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_notBlank_eq(String value){
        return dataType_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return dataType_leftLike(value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_notBlank_leftLike(String value){
        return dataType_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return dataType_rightLike(value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_notBlank_rightLike(String value){
        return dataType_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return dataType_middleLike(value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_notBlank_middleLike(String value){
        return dataType_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return dataType_in(values);
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" data_type in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 字段数据类型
    */
    public SqlProjectModelField dataType_notEmpty_in(Set<Object> values){
        return dataType_in(null != values && !values.isEmpty(), values);
    }


    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_eq(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeMapKey_eq(value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_map_key=?");
        parameters.add(value);
        return this;
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_notNull_eq(String value){
        return dataTypeMapKey_eq(null != value, value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_isdb_null(boolean cdn){
        if(!cdn) return this;
        return dataTypeMapKey_isdb_null();
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_map_key is null");
        return this;
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return dataTypeMapKey_isdb_not_null();
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_map_key is not null");
        return this;
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_gt(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeMapKey_gt(value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_map_key>?");
        parameters.add(value);
        return this;
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_notNull_gt(String value){
        return dataTypeMapKey_gt(null != value, value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_lt(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeMapKey_lt(value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_map_key<?");
        parameters.add(value);
        return this;
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_notNull_lt(String value){
        return dataTypeMapKey_lt(null != value, value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_ge(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeMapKey_ge(value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_map_key>=?");
        parameters.add(value);
        return this;
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_notNull_ge(String value){
        return dataTypeMapKey_ge(null != value, value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_le(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeMapKey_le(value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_map_key<=?");
        parameters.add(value);
        return this;
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_notNull_le(String value){
        return dataTypeMapKey_le(null != value, value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_ne(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeMapKey_ne(value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_map_key<>?");
        parameters.add(value);
        return this;
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_notNull_ne(String value){
        return dataTypeMapKey_ne(null != value, value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_notBlank_eq(String value){
        return dataTypeMapKey_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeMapKey_leftLike(value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_map_key like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_notBlank_leftLike(String value){
        return dataTypeMapKey_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeMapKey_rightLike(value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_map_key like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_notBlank_rightLike(String value){
        return dataTypeMapKey_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeMapKey_middleLike(value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_map_key like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_notBlank_middleLike(String value){
        return dataTypeMapKey_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return dataTypeMapKey_in(values);
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" data_type_map_key in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * map字段key
    */
    public SqlProjectModelField dataTypeMapKey_notEmpty_in(Set<Object> values){
        return dataTypeMapKey_in(null != values && !values.isEmpty(), values);
    }


    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_eq(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeValue_eq(value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_value=?");
        parameters.add(value);
        return this;
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_notNull_eq(String value){
        return dataTypeValue_eq(null != value, value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_isdb_null(boolean cdn){
        if(!cdn) return this;
        return dataTypeValue_isdb_null();
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_value is null");
        return this;
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return dataTypeValue_isdb_not_null();
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_value is not null");
        return this;
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_gt(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeValue_gt(value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_value>?");
        parameters.add(value);
        return this;
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_notNull_gt(String value){
        return dataTypeValue_gt(null != value, value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_lt(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeValue_lt(value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_value<?");
        parameters.add(value);
        return this;
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_notNull_lt(String value){
        return dataTypeValue_lt(null != value, value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_ge(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeValue_ge(value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_value>=?");
        parameters.add(value);
        return this;
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_notNull_ge(String value){
        return dataTypeValue_ge(null != value, value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_le(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeValue_le(value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_value<=?");
        parameters.add(value);
        return this;
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_notNull_le(String value){
        return dataTypeValue_le(null != value, value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_ne(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeValue_ne(value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_value<>?");
        parameters.add(value);
        return this;
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_notNull_ne(String value){
        return dataTypeValue_ne(null != value, value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_notBlank_eq(String value){
        return dataTypeValue_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeValue_leftLike(value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_value like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_notBlank_leftLike(String value){
        return dataTypeValue_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeValue_rightLike(value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_value like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_notBlank_rightLike(String value){
        return dataTypeValue_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return dataTypeValue_middleLike(value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" data_type_value like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_notBlank_middleLike(String value){
        return dataTypeValue_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return dataTypeValue_in(values);
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" data_type_value in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * list, set, map 字段value
    */
    public SqlProjectModelField dataTypeValue_notEmpty_in(Set<Object> values){
        return dataTypeValue_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_eq(boolean cdn, String value){
        if(!cdn) return this;
        return dbType_eq(value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_notNull_eq(String value){
        return dbType_eq(null != value, value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_isdb_null(boolean cdn){
        if(!cdn) return this;
        return dbType_isdb_null();
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type is null");
        return this;
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return dbType_isdb_not_null();
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type is not null");
        return this;
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_gt(boolean cdn, String value){
        if(!cdn) return this;
        return dbType_gt(value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_notNull_gt(String value){
        return dbType_gt(null != value, value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_lt(boolean cdn, String value){
        if(!cdn) return this;
        return dbType_lt(value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type<?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_notNull_lt(String value){
        return dbType_lt(null != value, value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_ge(boolean cdn, String value){
        if(!cdn) return this;
        return dbType_ge(value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_notNull_ge(String value){
        return dbType_ge(null != value, value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_le(boolean cdn, String value){
        if(!cdn) return this;
        return dbType_le(value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_notNull_le(String value){
        return dbType_le(null != value, value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_ne(boolean cdn, String value){
        if(!cdn) return this;
        return dbType_ne(value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_notNull_ne(String value){
        return dbType_ne(null != value, value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_notBlank_eq(String value){
        return dbType_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return dbType_leftLike(value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_notBlank_leftLike(String value){
        return dbType_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return dbType_rightLike(value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_notBlank_rightLike(String value){
        return dbType_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return dbType_middleLike(value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_notBlank_middleLike(String value){
        return dbType_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return dbType_in(values);
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" db_type in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 数据库类型
    */
    public SqlProjectModelField dbType_notEmpty_in(Set<Object> values){
        return dbType_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_eq(boolean cdn, String value){
        if(!cdn) return this;
        return dbTypeExt_eq(value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type_ext=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_notNull_eq(String value){
        return dbTypeExt_eq(null != value, value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_isdb_null(boolean cdn){
        if(!cdn) return this;
        return dbTypeExt_isdb_null();
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type_ext is null");
        return this;
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return dbTypeExt_isdb_not_null();
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type_ext is not null");
        return this;
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_gt(boolean cdn, String value){
        if(!cdn) return this;
        return dbTypeExt_gt(value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type_ext>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_notNull_gt(String value){
        return dbTypeExt_gt(null != value, value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_lt(boolean cdn, String value){
        if(!cdn) return this;
        return dbTypeExt_lt(value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type_ext<?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_notNull_lt(String value){
        return dbTypeExt_lt(null != value, value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_ge(boolean cdn, String value){
        if(!cdn) return this;
        return dbTypeExt_ge(value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type_ext>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_notNull_ge(String value){
        return dbTypeExt_ge(null != value, value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_le(boolean cdn, String value){
        if(!cdn) return this;
        return dbTypeExt_le(value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type_ext<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_notNull_le(String value){
        return dbTypeExt_le(null != value, value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_ne(boolean cdn, String value){
        if(!cdn) return this;
        return dbTypeExt_ne(value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type_ext<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_notNull_ne(String value){
        return dbTypeExt_ne(null != value, value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_notBlank_eq(String value){
        return dbTypeExt_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return dbTypeExt_leftLike(value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type_ext like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_notBlank_leftLike(String value){
        return dbTypeExt_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return dbTypeExt_rightLike(value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type_ext like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_notBlank_rightLike(String value){
        return dbTypeExt_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return dbTypeExt_middleLike(value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" db_type_ext like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_notBlank_middleLike(String value){
        return dbTypeExt_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return dbTypeExt_in(values);
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" db_type_ext in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 数据库类型扩展信息
    */
    public SqlProjectModelField dbTypeExt_notEmpty_in(Set<Object> values){
        return dbTypeExt_in(null != values && !values.isEmpty(), values);
    }


    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_eq(boolean cdn, String value){
        if(!cdn) return this;
        return createExt_eq(value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_ext=?");
        parameters.add(value);
        return this;
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_notNull_eq(String value){
        return createExt_eq(null != value, value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_isdb_null(boolean cdn){
        if(!cdn) return this;
        return createExt_isdb_null();
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_ext is null");
        return this;
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return createExt_isdb_not_null();
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_ext is not null");
        return this;
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_gt(boolean cdn, String value){
        if(!cdn) return this;
        return createExt_gt(value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_ext>?");
        parameters.add(value);
        return this;
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_notNull_gt(String value){
        return createExt_gt(null != value, value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_lt(boolean cdn, String value){
        if(!cdn) return this;
        return createExt_lt(value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_ext<?");
        parameters.add(value);
        return this;
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_notNull_lt(String value){
        return createExt_lt(null != value, value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_ge(boolean cdn, String value){
        if(!cdn) return this;
        return createExt_ge(value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_ext>=?");
        parameters.add(value);
        return this;
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_notNull_ge(String value){
        return createExt_ge(null != value, value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_le(boolean cdn, String value){
        if(!cdn) return this;
        return createExt_le(value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_ext<=?");
        parameters.add(value);
        return this;
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_notNull_le(String value){
        return createExt_le(null != value, value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_ne(boolean cdn, String value){
        if(!cdn) return this;
        return createExt_ne(value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_ext<>?");
        parameters.add(value);
        return this;
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_notNull_ne(String value){
        return createExt_ne(null != value, value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_notBlank_eq(String value){
        return createExt_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return createExt_leftLike(value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_ext like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_notBlank_leftLike(String value){
        return createExt_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return createExt_rightLike(value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_ext like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_notBlank_rightLike(String value){
        return createExt_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return createExt_middleLike(value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_ext like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_notBlank_middleLike(String value){
        return createExt_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return createExt_in(values);
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" create_ext in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * DDL语句中的扩展信息
    */
    public SqlProjectModelField createExt_notEmpty_in(Set<Object> values){
        return createExt_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_eq(boolean cdn, String value){
        if(!cdn) return this;
        return refColumn_eq(value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ref_column=?");
        parameters.add(value);
        return this;
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_notNull_eq(String value){
        return refColumn_eq(null != value, value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_isdb_null(boolean cdn){
        if(!cdn) return this;
        return refColumn_isdb_null();
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ref_column is null");
        return this;
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return refColumn_isdb_not_null();
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ref_column is not null");
        return this;
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_gt(boolean cdn, String value){
        if(!cdn) return this;
        return refColumn_gt(value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ref_column>?");
        parameters.add(value);
        return this;
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_notNull_gt(String value){
        return refColumn_gt(null != value, value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_lt(boolean cdn, String value){
        if(!cdn) return this;
        return refColumn_lt(value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ref_column<?");
        parameters.add(value);
        return this;
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_notNull_lt(String value){
        return refColumn_lt(null != value, value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_ge(boolean cdn, String value){
        if(!cdn) return this;
        return refColumn_ge(value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ref_column>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_notNull_ge(String value){
        return refColumn_ge(null != value, value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_le(boolean cdn, String value){
        if(!cdn) return this;
        return refColumn_le(value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ref_column<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_notNull_le(String value){
        return refColumn_le(null != value, value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_ne(boolean cdn, String value){
        if(!cdn) return this;
        return refColumn_ne(value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ref_column<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_notNull_ne(String value){
        return refColumn_ne(null != value, value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_notBlank_eq(String value){
        return refColumn_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return refColumn_leftLike(value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ref_column like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_notBlank_leftLike(String value){
        return refColumn_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return refColumn_rightLike(value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ref_column like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_notBlank_rightLike(String value){
        return refColumn_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return refColumn_middleLike(value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" ref_column like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_notBlank_middleLike(String value){
        return refColumn_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return refColumn_in(values);
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" ref_column in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 关联column
    */
    public SqlProjectModelField refColumn_notEmpty_in(Set<Object> values){
        return refColumn_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 默认值
    */
    public SqlProjectModelField defValue_eq(boolean cdn, String value){
        if(!cdn) return this;
        return defValue_eq(value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" def_value=?");
        parameters.add(value);
        return this;
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_notNull_eq(String value){
        return defValue_eq(null != value, value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_isdb_null(boolean cdn){
        if(!cdn) return this;
        return defValue_isdb_null();
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" def_value is null");
        return this;
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return defValue_isdb_not_null();
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" def_value is not null");
        return this;
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_gt(boolean cdn, String value){
        if(!cdn) return this;
        return defValue_gt(value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" def_value>?");
        parameters.add(value);
        return this;
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_notNull_gt(String value){
        return defValue_gt(null != value, value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_lt(boolean cdn, String value){
        if(!cdn) return this;
        return defValue_lt(value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" def_value<?");
        parameters.add(value);
        return this;
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_notNull_lt(String value){
        return defValue_lt(null != value, value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_ge(boolean cdn, String value){
        if(!cdn) return this;
        return defValue_ge(value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" def_value>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_notNull_ge(String value){
        return defValue_ge(null != value, value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_le(boolean cdn, String value){
        if(!cdn) return this;
        return defValue_le(value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" def_value<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_notNull_le(String value){
        return defValue_le(null != value, value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_ne(boolean cdn, String value){
        if(!cdn) return this;
        return defValue_ne(value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" def_value<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_notNull_ne(String value){
        return defValue_ne(null != value, value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_notBlank_eq(String value){
        return defValue_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return defValue_leftLike(value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" def_value like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_notBlank_leftLike(String value){
        return defValue_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return defValue_rightLike(value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" def_value like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_notBlank_rightLike(String value){
        return defValue_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return defValue_middleLike(value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" def_value like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_notBlank_middleLike(String value){
        return defValue_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return defValue_in(values);
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" def_value in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 默认值
    */
    public SqlProjectModelField defValue_notEmpty_in(Set<Object> values){
        return defValue_in(null != values && !values.isEmpty(), values);
    }


    /**
    * imports
    */
    public SqlProjectModelField imports_eq(boolean cdn, String value){
        if(!cdn) return this;
        return imports_eq(value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" imports=?");
        parameters.add(value);
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_notNull_eq(String value){
        return imports_eq(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_isdb_null(boolean cdn){
        if(!cdn) return this;
        return imports_isdb_null();
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" imports is null");
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return imports_isdb_not_null();
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" imports is not null");
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_gt(boolean cdn, String value){
        if(!cdn) return this;
        return imports_gt(value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" imports>?");
        parameters.add(value);
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_notNull_gt(String value){
        return imports_gt(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_lt(boolean cdn, String value){
        if(!cdn) return this;
        return imports_lt(value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" imports<?");
        parameters.add(value);
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_notNull_lt(String value){
        return imports_lt(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_ge(boolean cdn, String value){
        if(!cdn) return this;
        return imports_ge(value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" imports>=?");
        parameters.add(value);
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_notNull_ge(String value){
        return imports_ge(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_le(boolean cdn, String value){
        if(!cdn) return this;
        return imports_le(value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" imports<=?");
        parameters.add(value);
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_notNull_le(String value){
        return imports_le(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_ne(boolean cdn, String value){
        if(!cdn) return this;
        return imports_ne(value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" imports<>?");
        parameters.add(value);
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_notNull_ne(String value){
        return imports_ne(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_notBlank_eq(String value){
        return imports_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return imports_leftLike(value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" imports like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_notBlank_leftLike(String value){
        return imports_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return imports_rightLike(value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" imports like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_notBlank_rightLike(String value){
        return imports_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return imports_middleLike(value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" imports like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_notBlank_middleLike(String value){
        return imports_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return imports_in(values);
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" imports in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelField imports_notEmpty_in(Set<Object> values){
        return imports_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_eq(value);
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_eq(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" dsp_order=?");
        parameters.add(value);
        return this;
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_notNull_eq(Integer value){
        return dspOrder_eq(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_isdb_null(boolean cdn){
        if(!cdn) return this;
        return dspOrder_isdb_null();
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" dsp_order is null");
        return this;
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return dspOrder_isdb_not_null();
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" dsp_order is not null");
        return this;
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_gt(value);
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_gt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" dsp_order>?");
        parameters.add(value);
        return this;
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_notNull_gt(Integer value){
        return dspOrder_gt(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_lt(value);
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_lt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" dsp_order<?");
        parameters.add(value);
        return this;
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_notNull_lt(Integer value){
        return dspOrder_lt(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_ge(value);
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_ge(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" dsp_order>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_notNull_ge(Integer value){
        return dspOrder_ge(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_le(value);
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_le(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" dsp_order<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_notNull_le(Integer value){
        return dspOrder_le(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_ne(value);
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_ne(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" dsp_order<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_notNull_ne(Integer value){
        return dspOrder_ne(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return dspOrder_in(values);
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" dsp_order in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 序号
    */
    public SqlProjectModelField dspOrder_notEmpty_in(Set<Object> values){
        return dspOrder_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 备注
    */
    public SqlProjectModelField comment_eq(boolean cdn, String value){
        if(!cdn) return this;
        return comment_eq(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" comment=?");
        parameters.add(value);
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_notNull_eq(String value){
        return comment_eq(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_isdb_null(boolean cdn){
        if(!cdn) return this;
        return comment_isdb_null();
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" comment is null");
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return comment_isdb_not_null();
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" comment is not null");
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_gt(boolean cdn, String value){
        if(!cdn) return this;
        return comment_gt(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" comment>?");
        parameters.add(value);
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_notNull_gt(String value){
        return comment_gt(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_lt(boolean cdn, String value){
        if(!cdn) return this;
        return comment_lt(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" comment<?");
        parameters.add(value);
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_notNull_lt(String value){
        return comment_lt(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_ge(boolean cdn, String value){
        if(!cdn) return this;
        return comment_ge(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" comment>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_notNull_ge(String value){
        return comment_ge(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_le(boolean cdn, String value){
        if(!cdn) return this;
        return comment_le(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" comment<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_notNull_le(String value){
        return comment_le(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_ne(boolean cdn, String value){
        if(!cdn) return this;
        return comment_ne(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" comment<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_notNull_ne(String value){
        return comment_ne(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_notBlank_eq(String value){
        return comment_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return comment_leftLike(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" comment like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_notBlank_leftLike(String value){
        return comment_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return comment_rightLike(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" comment like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_notBlank_rightLike(String value){
        return comment_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return comment_middleLike(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" comment like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_notBlank_middleLike(String value){
        return comment_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return comment_in(values);
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" comment in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelField comment_notEmpty_in(Set<Object> values){
        return comment_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_eq(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_eq(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" delete_status=?");
        parameters.add(value);
        return this;
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_notNull_eq(Integer value){
        return deleteStatus_eq(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_isdb_null(boolean cdn){
        if(!cdn) return this;
        return deleteStatus_isdb_null();
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" delete_status is null");
        return this;
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return deleteStatus_isdb_not_null();
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" delete_status is not null");
        return this;
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_gt(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_gt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" delete_status>?");
        parameters.add(value);
        return this;
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_notNull_gt(Integer value){
        return deleteStatus_gt(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_lt(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_lt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" delete_status<?");
        parameters.add(value);
        return this;
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_notNull_lt(Integer value){
        return deleteStatus_lt(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_ge(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_ge(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" delete_status>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_notNull_ge(Integer value){
        return deleteStatus_ge(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_le(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_le(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" delete_status<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_notNull_le(Integer value){
        return deleteStatus_le(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_ne(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_ne(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" delete_status<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_notNull_ne(Integer value){
        return deleteStatus_ne(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return deleteStatus_in(values);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" delete_status in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 删除状态
    */
    public SqlProjectModelField deleteStatus_notEmpty_in(Set<Object> values){
        return deleteStatus_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_eq(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_eq(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_eq(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_time=?");
        parameters.add(value);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_notNull_eq(Timestamp value){
        return createTime_eq(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_eq_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_eq_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_eq_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_eq(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_notNull_eq_yyyyMMdd(Timestamp value){
        return createTime_eq_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_isdb_null(boolean cdn){
        if(!cdn) return this;
        return createTime_isdb_null();
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_time is null");
        return this;
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return createTime_isdb_not_null();
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_time is not null");
        return this;
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_gt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_gt(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_gt(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_time>?");
        parameters.add(value);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_notNull_gt(Timestamp value){
        return createTime_gt(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_gt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_gt_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_gt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_gt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_notNull_gt_yyyyMMdd(Timestamp value){
        return createTime_gt_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_lt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_lt(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_lt(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_time<?");
        parameters.add(value);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_notNull_lt(Timestamp value){
        return createTime_lt(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_lt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_lt_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_lt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_lt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_notNull_lt_yyyyMMdd(Timestamp value){
        return createTime_lt_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModelField createTime_lt_nextDay_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_lt_nextDay_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModelField createTime_lt_nextDay_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(DateUtil.offsetDay(value, 1)).toTimestamp();
        return createTime_lt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModelField createTime_notNull_lt_nextDay_yyyyMMdd(Timestamp value){
        return createTime_lt_nextDay_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_ge(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ge(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_ge(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_time>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_notNull_ge(Timestamp value){
        return createTime_ge(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_ge_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ge_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_ge_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_ge(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_notNull_ge_yyyyMMdd(Timestamp value){
        return createTime_ge_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_le(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_le(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_le(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_time<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_notNull_le(Timestamp value){
        return createTime_le(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_le_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_le_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_le_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_le(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_notNull_le_yyyyMMdd(Timestamp value){
        return createTime_le_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_ne(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ne(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_ne(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" create_time<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_notNull_ne(Timestamp value){
        return createTime_ne(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_ne_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ne_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_ne_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_ne(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField createTime_notNull_ne_yyyyMMdd(Timestamp value){
        return createTime_ne_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return createTime_in(values);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" create_time in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlProjectModelField createTime_notEmpty_in(Set<Object> values){
        return createTime_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_eq(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_eq(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_eq(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" update_time=?");
        parameters.add(value);
        return this;
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_notNull_eq(Timestamp value){
        return updateTime_eq(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_eq_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_eq_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_eq_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_eq(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_notNull_eq_yyyyMMdd(Timestamp value){
        return updateTime_eq_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_isdb_null(boolean cdn){
        if(!cdn) return this;
        return updateTime_isdb_null();
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" update_time is null");
        return this;
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return updateTime_isdb_not_null();
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" update_time is not null");
        return this;
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_gt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_gt(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_gt(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" update_time>?");
        parameters.add(value);
        return this;
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_notNull_gt(Timestamp value){
        return updateTime_gt(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_gt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_gt_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_gt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_gt(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_notNull_gt_yyyyMMdd(Timestamp value){
        return updateTime_gt_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_lt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_lt(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_lt(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" update_time<?");
        parameters.add(value);
        return this;
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_notNull_lt(Timestamp value){
        return updateTime_lt(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_lt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_lt_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_lt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_lt(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_notNull_lt_yyyyMMdd(Timestamp value){
        return updateTime_lt_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModelField updateTime_lt_nextDay_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_lt_nextDay_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModelField updateTime_lt_nextDay_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(DateUtil.offsetDay(value, 1)).toTimestamp();
        return updateTime_lt(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModelField updateTime_notNull_lt_nextDay_yyyyMMdd(Timestamp value){
        return updateTime_lt_nextDay_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_ge(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ge(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_ge(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" update_time>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_notNull_ge(Timestamp value){
        return updateTime_ge(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_ge_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ge_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_ge_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_ge(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_notNull_ge_yyyyMMdd(Timestamp value){
        return updateTime_ge_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_le(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_le(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_le(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" update_time<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_notNull_le(Timestamp value){
        return updateTime_le(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_le_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_le_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_le_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_le(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_notNull_le_yyyyMMdd(Timestamp value){
        return updateTime_le_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_ne(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ne(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_ne(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" update_time<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_notNull_ne(Timestamp value){
        return updateTime_ne(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_ne_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ne_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_ne_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_ne(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelField updateTime_notNull_ne_yyyyMMdd(Timestamp value){
        return updateTime_ne_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return updateTime_in(values);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_in(Set<Object> values){
        if(null == values || values.isEmpty()){
            throw new RuntimeException("values is empty");
        }
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        String txt = StrUtil.repeatAndJoin("?", values.size(), ",");
        builder.append(pre+" update_time in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 更新时间
    */
    public SqlProjectModelField updateTime_notEmpty_in(Set<Object> values){
        return updateTime_in(null != values && !values.isEmpty(), values);
    }


    @Override
    public String toSqlString() {
        StringBuilder buf=new StringBuilder("SELECT * FROM project_model_field");
        buf.append(builder);
        return buf.toString();
    }
    @Override
    public String toSqlString(int start, int limit) {
        StringBuilder buf=new StringBuilder("SELECT * FROM project_model_field");
        buf.append(builder);
        buf.append(" limit ");
        buf.append(start);
        buf.append(" ,");
        buf.append(limit);
        return buf.toString();
    }
    @Override
    public String toCountSqlString() {
        StringBuilder buf=new StringBuilder("SELECT COUNT(1) FROM project_model_field");
        buf.append(builder);
        return buf.toString();
    }
    @Override
    public List<Object> getParameters() {
        return this.parameters;
    }
}
