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
* 注解
*/
public class SqlProjectModelFieldAnno implements QuerySelect {
    private static final String ID_SUFFIX="00A4";
    private static Object DB_TARGET=null;
    private Object instDbTarget__ =null;
    private static final String SQL_INSERT="INSERT INTO project_model_field_anno(id,project_id,project_db_id,project_model_id,project_model_field_id,imports,anno,dsp_order,comment,delete_status)values(?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE project_model_field_anno SET project_id=?,project_db_id=?,project_model_id=?,project_model_field_id=?,imports=?,anno=?,dsp_order=?,comment=?,delete_status=? WHERE id=?";
    private static final String SQL_DELETE="DELETE project_model_field_anno WHERE id=?";
    private boolean existsWhere=false;
    private boolean existsOrderBy=false;
    private StringBuilder builder=new StringBuilder();
    private StringBuilder orderBuilder=new StringBuilder();
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

    public SqlProjectModelFieldAnno changeDbTarget(String name){
        instDbTarget__ =DaoJdbcTemplate.s(name);
        return this;
    }

    public static SqlProjectModelFieldAnno inst(){
        final SqlProjectModelFieldAnno sqlProjectModelFieldAnno = new SqlProjectModelFieldAnno();
        return sqlProjectModelFieldAnno;
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
        StringBuilder buf=new StringBuilder("UPDATE project_model_field_anno SET");
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
    public int save(ProjectModelFieldAnno entity){
        if(null==entity.getId()){
            return insert(entity);
        }else{
            return update(entity);
        }
    }
    private int insert(ProjectModelFieldAnno entity){
        List<Object> values = new ArrayList<>(10);
        values.add(entity.getId());
        values.add(entity.getProjectId());
        values.add(entity.getProjectDbId());
        values.add(entity.getProjectModelId());
        values.add(entity.getProjectModelFieldId());
        values.add(entity.getImports());
        values.add(entity.getAnno());
        values.add(entity.getDspOrder());
        values.add(entity.getComment());
        values.add(entity.getDeleteStatus());

        int retNum = DaoExecuteChangeSql.s(ds(), SQL_INSERT, values);
        return retNum;
    }
    private int update(ProjectModelFieldAnno entity){
        List<Object> values = new ArrayList<>(10);
        values.add(entity.getProjectId());
        values.add(entity.getProjectDbId());
        values.add(entity.getProjectModelId());
        values.add(entity.getProjectModelFieldId());
        values.add(entity.getImports());
        values.add(entity.getAnno());
        values.add(entity.getDspOrder());
        values.add(entity.getComment());
        values.add(entity.getDeleteStatus());
        values.add(entity.getId());

        return DaoExecuteChangeSql.s(ds(), SQL_UPDATE, values);
    }
    
    public int del(ProjectModelFieldAnno entity){
        List<Object> values = new ArrayList<>(1);
        values.add(entity.getId());
        return DaoExecuteChangeSql.s(ds(), SQL_DELETE, values);
    }
    public ProjectModelFieldAnno queryOne(){
        final List<ProjectModelFieldAnno> list = DaoList.s(ds(), ProjectModelFieldAnno.class, this, 0, 1);
        return list.size()>0?list.get(0):null;
    }
    public List<ProjectModelFieldAnno> queryList(){
        return DaoList.s(ds(), ProjectModelFieldAnno.class, this);
    }
    public List<ProjectModelFieldAnno> queryList(int start, int limit){
        return DaoList.s(ds(), ProjectModelFieldAnno.class, this, start, limit);
    }
    public PageResultList<ProjectModelFieldAnno> queryPageList(int start, int limit){
        return DaoPageList.s(ds(), ProjectModelFieldAnno.class, this, start, limit);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_eq(boolean cdn, String value){
        if(!cdn) return this;
        return id_eq(value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_eq(String value){
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
    public SqlProjectModelFieldAnno id_notNull_eq(String value){
        return id_eq(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_isdb_null(boolean cdn){
        if(!cdn) return this;
        return id_isdb_null();
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_isdb_null(){
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
    public SqlProjectModelFieldAnno id_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return id_isdb_not_null();
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_isdb_not_null(){
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
    public SqlProjectModelFieldAnno id_gt(boolean cdn, String value){
        if(!cdn) return this;
        return id_gt(value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_gt(String value){
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
    public SqlProjectModelFieldAnno id_notNull_gt(String value){
        return id_gt(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_lt(boolean cdn, String value){
        if(!cdn) return this;
        return id_lt(value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_lt(String value){
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
    public SqlProjectModelFieldAnno id_notNull_lt(String value){
        return id_lt(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_ge(boolean cdn, String value){
        if(!cdn) return this;
        return id_ge(value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_ge(String value){
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
    public SqlProjectModelFieldAnno id_notNull_ge(String value){
        return id_ge(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_le(boolean cdn, String value){
        if(!cdn) return this;
        return id_le(value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_le(String value){
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
    public SqlProjectModelFieldAnno id_notNull_le(String value){
        return id_le(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_ne(boolean cdn, String value){
        if(!cdn) return this;
        return id_ne(value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_ne(String value){
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
    public SqlProjectModelFieldAnno id_notNull_ne(String value){
        return id_ne(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_notBlank_eq(String value){
        return id_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return id_leftLike(value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_leftLike(String value){
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
    public SqlProjectModelFieldAnno id_notBlank_leftLike(String value){
        return id_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return id_rightLike(value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_rightLike(String value){
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
    public SqlProjectModelFieldAnno id_notBlank_rightLike(String value){
        return id_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return id_middleLike(value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_middleLike(String value){
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
    public SqlProjectModelFieldAnno id_notBlank_middleLike(String value){
        return id_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return id_in(values);
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno id_in(Set<Object> values){
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
    public SqlProjectModelFieldAnno id_notEmpty_in(Set<Object> values){
        return id_in(null != values && !values.isEmpty(), values);
    }


    /**
    * id
    */
    public SqlProjectModelFieldAnno orderBy_id_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" id asc");
        return this;
    }
    /**
    * id
    */
    public SqlProjectModelFieldAnno orderBy_id_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" id desc");
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_eq(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_eq(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_eq(String value){
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
    public SqlProjectModelFieldAnno projectId_notNull_eq(String value){
        return projectId_eq(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_isdb_null(boolean cdn){
        if(!cdn) return this;
        return projectId_isdb_null();
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_isdb_null(){
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
    public SqlProjectModelFieldAnno projectId_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return projectId_isdb_not_null();
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_isdb_not_null(){
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
    public SqlProjectModelFieldAnno projectId_gt(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_gt(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_gt(String value){
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
    public SqlProjectModelFieldAnno projectId_notNull_gt(String value){
        return projectId_gt(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_lt(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_lt(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_lt(String value){
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
    public SqlProjectModelFieldAnno projectId_notNull_lt(String value){
        return projectId_lt(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_ge(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_ge(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_ge(String value){
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
    public SqlProjectModelFieldAnno projectId_notNull_ge(String value){
        return projectId_ge(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_le(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_le(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_le(String value){
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
    public SqlProjectModelFieldAnno projectId_notNull_le(String value){
        return projectId_le(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_ne(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_ne(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_ne(String value){
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
    public SqlProjectModelFieldAnno projectId_notNull_ne(String value){
        return projectId_ne(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_notBlank_eq(String value){
        return projectId_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_leftLike(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_leftLike(String value){
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
    public SqlProjectModelFieldAnno projectId_notBlank_leftLike(String value){
        return projectId_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_rightLike(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_rightLike(String value){
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
    public SqlProjectModelFieldAnno projectId_notBlank_rightLike(String value){
        return projectId_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_middleLike(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_middleLike(String value){
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
    public SqlProjectModelFieldAnno projectId_notBlank_middleLike(String value){
        return projectId_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return projectId_in(values);
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno projectId_in(Set<Object> values){
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
    public SqlProjectModelFieldAnno projectId_notEmpty_in(Set<Object> values){
        return projectId_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno orderBy_projectId_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" project_id asc");
        return this;
    }
    /**
    * 项目id
    */
    public SqlProjectModelFieldAnno orderBy_projectId_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" project_id desc");
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_eq(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_eq(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_eq(String value){
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
    public SqlProjectModelFieldAnno projectDbId_notNull_eq(String value){
        return projectDbId_eq(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_isdb_null(boolean cdn){
        if(!cdn) return this;
        return projectDbId_isdb_null();
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_isdb_null(){
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
    public SqlProjectModelFieldAnno projectDbId_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return projectDbId_isdb_not_null();
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_isdb_not_null(){
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
    public SqlProjectModelFieldAnno projectDbId_gt(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_gt(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_gt(String value){
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
    public SqlProjectModelFieldAnno projectDbId_notNull_gt(String value){
        return projectDbId_gt(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_lt(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_lt(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_lt(String value){
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
    public SqlProjectModelFieldAnno projectDbId_notNull_lt(String value){
        return projectDbId_lt(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_ge(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_ge(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_ge(String value){
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
    public SqlProjectModelFieldAnno projectDbId_notNull_ge(String value){
        return projectDbId_ge(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_le(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_le(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_le(String value){
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
    public SqlProjectModelFieldAnno projectDbId_notNull_le(String value){
        return projectDbId_le(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_ne(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_ne(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_ne(String value){
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
    public SqlProjectModelFieldAnno projectDbId_notNull_ne(String value){
        return projectDbId_ne(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_notBlank_eq(String value){
        return projectDbId_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_leftLike(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_leftLike(String value){
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
    public SqlProjectModelFieldAnno projectDbId_notBlank_leftLike(String value){
        return projectDbId_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_rightLike(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_rightLike(String value){
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
    public SqlProjectModelFieldAnno projectDbId_notBlank_rightLike(String value){
        return projectDbId_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_middleLike(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_middleLike(String value){
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
    public SqlProjectModelFieldAnno projectDbId_notBlank_middleLike(String value){
        return projectDbId_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return projectDbId_in(values);
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno projectDbId_in(Set<Object> values){
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
    public SqlProjectModelFieldAnno projectDbId_notEmpty_in(Set<Object> values){
        return projectDbId_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno orderBy_projectDbId_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" project_db_id asc");
        return this;
    }
    /**
    * 数据库id
    */
    public SqlProjectModelFieldAnno orderBy_projectDbId_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" project_db_id desc");
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_eq(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_eq(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_eq(String value){
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
    public SqlProjectModelFieldAnno projectModelId_notNull_eq(String value){
        return projectModelId_eq(null != value, value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_isdb_null(boolean cdn){
        if(!cdn) return this;
        return projectModelId_isdb_null();
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_isdb_null(){
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
    public SqlProjectModelFieldAnno projectModelId_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return projectModelId_isdb_not_null();
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_isdb_not_null(){
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
    public SqlProjectModelFieldAnno projectModelId_gt(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_gt(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_gt(String value){
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
    public SqlProjectModelFieldAnno projectModelId_notNull_gt(String value){
        return projectModelId_gt(null != value, value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_lt(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_lt(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_lt(String value){
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
    public SqlProjectModelFieldAnno projectModelId_notNull_lt(String value){
        return projectModelId_lt(null != value, value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_ge(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_ge(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_ge(String value){
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
    public SqlProjectModelFieldAnno projectModelId_notNull_ge(String value){
        return projectModelId_ge(null != value, value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_le(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_le(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_le(String value){
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
    public SqlProjectModelFieldAnno projectModelId_notNull_le(String value){
        return projectModelId_le(null != value, value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_ne(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_ne(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_ne(String value){
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
    public SqlProjectModelFieldAnno projectModelId_notNull_ne(String value){
        return projectModelId_ne(null != value, value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_notBlank_eq(String value){
        return projectModelId_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_leftLike(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_leftLike(String value){
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
    public SqlProjectModelFieldAnno projectModelId_notBlank_leftLike(String value){
        return projectModelId_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_rightLike(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_rightLike(String value){
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
    public SqlProjectModelFieldAnno projectModelId_notBlank_rightLike(String value){
        return projectModelId_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelId_middleLike(value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_middleLike(String value){
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
    public SqlProjectModelFieldAnno projectModelId_notBlank_middleLike(String value){
        return projectModelId_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return projectModelId_in(values);
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno projectModelId_in(Set<Object> values){
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
    public SqlProjectModelFieldAnno projectModelId_notEmpty_in(Set<Object> values){
        return projectModelId_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno orderBy_projectModelId_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" project_model_id asc");
        return this;
    }
    /**
    * 数据模型id
    */
    public SqlProjectModelFieldAnno orderBy_projectModelId_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" project_model_id desc");
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_eq(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelFieldId_eq(value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_field_id=?");
        parameters.add(value);
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_notNull_eq(String value){
        return projectModelFieldId_eq(null != value, value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_isdb_null(boolean cdn){
        if(!cdn) return this;
        return projectModelFieldId_isdb_null();
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_field_id is null");
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return projectModelFieldId_isdb_not_null();
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_field_id is not null");
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_gt(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelFieldId_gt(value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_field_id>?");
        parameters.add(value);
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_notNull_gt(String value){
        return projectModelFieldId_gt(null != value, value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_lt(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelFieldId_lt(value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_field_id<?");
        parameters.add(value);
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_notNull_lt(String value){
        return projectModelFieldId_lt(null != value, value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_ge(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelFieldId_ge(value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_field_id>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_notNull_ge(String value){
        return projectModelFieldId_ge(null != value, value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_le(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelFieldId_le(value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_field_id<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_notNull_le(String value){
        return projectModelFieldId_le(null != value, value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_ne(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelFieldId_ne(value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_field_id<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_notNull_ne(String value){
        return projectModelFieldId_ne(null != value, value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_notBlank_eq(String value){
        return projectModelFieldId_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelFieldId_leftLike(value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_field_id like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_notBlank_leftLike(String value){
        return projectModelFieldId_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelFieldId_rightLike(value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_field_id like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_notBlank_rightLike(String value){
        return projectModelFieldId_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectModelFieldId_middleLike(value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" project_model_field_id like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_notBlank_middleLike(String value){
        return projectModelFieldId_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return projectModelFieldId_in(values);
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_in(Set<Object> values){
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
        builder.append(pre+" project_model_field_id in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno projectModelFieldId_notEmpty_in(Set<Object> values){
        return projectModelFieldId_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno orderBy_projectModelFieldId_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" project_model_field_id asc");
        return this;
    }
    /**
    * 字段id
    */
    public SqlProjectModelFieldAnno orderBy_projectModelFieldId_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" project_model_field_id desc");
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_eq(boolean cdn, String value){
        if(!cdn) return this;
        return imports_eq(value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_eq(String value){
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
    public SqlProjectModelFieldAnno imports_notNull_eq(String value){
        return imports_eq(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_isdb_null(boolean cdn){
        if(!cdn) return this;
        return imports_isdb_null();
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_isdb_null(){
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
    public SqlProjectModelFieldAnno imports_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return imports_isdb_not_null();
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_isdb_not_null(){
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
    public SqlProjectModelFieldAnno imports_gt(boolean cdn, String value){
        if(!cdn) return this;
        return imports_gt(value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_gt(String value){
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
    public SqlProjectModelFieldAnno imports_notNull_gt(String value){
        return imports_gt(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_lt(boolean cdn, String value){
        if(!cdn) return this;
        return imports_lt(value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_lt(String value){
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
    public SqlProjectModelFieldAnno imports_notNull_lt(String value){
        return imports_lt(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_ge(boolean cdn, String value){
        if(!cdn) return this;
        return imports_ge(value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_ge(String value){
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
    public SqlProjectModelFieldAnno imports_notNull_ge(String value){
        return imports_ge(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_le(boolean cdn, String value){
        if(!cdn) return this;
        return imports_le(value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_le(String value){
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
    public SqlProjectModelFieldAnno imports_notNull_le(String value){
        return imports_le(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_ne(boolean cdn, String value){
        if(!cdn) return this;
        return imports_ne(value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_ne(String value){
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
    public SqlProjectModelFieldAnno imports_notNull_ne(String value){
        return imports_ne(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_notBlank_eq(String value){
        return imports_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return imports_leftLike(value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_leftLike(String value){
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
    public SqlProjectModelFieldAnno imports_notBlank_leftLike(String value){
        return imports_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return imports_rightLike(value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_rightLike(String value){
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
    public SqlProjectModelFieldAnno imports_notBlank_rightLike(String value){
        return imports_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return imports_middleLike(value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_middleLike(String value){
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
    public SqlProjectModelFieldAnno imports_notBlank_middleLike(String value){
        return imports_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return imports_in(values);
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno imports_in(Set<Object> values){
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
    public SqlProjectModelFieldAnno imports_notEmpty_in(Set<Object> values){
        return imports_in(null != values && !values.isEmpty(), values);
    }


    /**
    * imports
    */
    public SqlProjectModelFieldAnno orderBy_imports_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" imports asc");
        return this;
    }
    /**
    * imports
    */
    public SqlProjectModelFieldAnno orderBy_imports_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" imports desc");
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_eq(boolean cdn, String value){
        if(!cdn) return this;
        return anno_eq(value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" anno=?");
        parameters.add(value);
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_notNull_eq(String value){
        return anno_eq(null != value, value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_isdb_null(boolean cdn){
        if(!cdn) return this;
        return anno_isdb_null();
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" anno is null");
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return anno_isdb_not_null();
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" anno is not null");
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_gt(boolean cdn, String value){
        if(!cdn) return this;
        return anno_gt(value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" anno>?");
        parameters.add(value);
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_notNull_gt(String value){
        return anno_gt(null != value, value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_lt(boolean cdn, String value){
        if(!cdn) return this;
        return anno_lt(value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" anno<?");
        parameters.add(value);
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_notNull_lt(String value){
        return anno_lt(null != value, value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_ge(boolean cdn, String value){
        if(!cdn) return this;
        return anno_ge(value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" anno>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_notNull_ge(String value){
        return anno_ge(null != value, value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_le(boolean cdn, String value){
        if(!cdn) return this;
        return anno_le(value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" anno<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_notNull_le(String value){
        return anno_le(null != value, value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_ne(boolean cdn, String value){
        if(!cdn) return this;
        return anno_ne(value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" anno<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_notNull_ne(String value){
        return anno_ne(null != value, value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_notBlank_eq(String value){
        return anno_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return anno_leftLike(value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" anno like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_notBlank_leftLike(String value){
        return anno_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return anno_rightLike(value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" anno like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_notBlank_rightLike(String value){
        return anno_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return anno_middleLike(value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" anno like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_notBlank_middleLike(String value){
        return anno_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return anno_in(values);
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_in(Set<Object> values){
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
        builder.append(pre+" anno in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno anno_notEmpty_in(Set<Object> values){
        return anno_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 注解
    */
    public SqlProjectModelFieldAnno orderBy_anno_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" anno asc");
        return this;
    }
    /**
    * 注解
    */
    public SqlProjectModelFieldAnno orderBy_anno_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" anno desc");
        return this;
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_eq(value);
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_eq(Integer value){
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
    public SqlProjectModelFieldAnno dspOrder_notNull_eq(Integer value){
        return dspOrder_eq(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_isdb_null(boolean cdn){
        if(!cdn) return this;
        return dspOrder_isdb_null();
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_isdb_null(){
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
    public SqlProjectModelFieldAnno dspOrder_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return dspOrder_isdb_not_null();
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_isdb_not_null(){
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
    public SqlProjectModelFieldAnno dspOrder_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_gt(value);
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_gt(Integer value){
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
    public SqlProjectModelFieldAnno dspOrder_notNull_gt(Integer value){
        return dspOrder_gt(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_lt(value);
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_lt(Integer value){
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
    public SqlProjectModelFieldAnno dspOrder_notNull_lt(Integer value){
        return dspOrder_lt(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_ge(value);
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_ge(Integer value){
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
    public SqlProjectModelFieldAnno dspOrder_notNull_ge(Integer value){
        return dspOrder_ge(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_le(value);
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_le(Integer value){
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
    public SqlProjectModelFieldAnno dspOrder_notNull_le(Integer value){
        return dspOrder_le(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_ne(value);
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_ne(Integer value){
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
    public SqlProjectModelFieldAnno dspOrder_notNull_ne(Integer value){
        return dspOrder_ne(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return dspOrder_in(values);
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno dspOrder_in(Set<Object> values){
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
    public SqlProjectModelFieldAnno dspOrder_notEmpty_in(Set<Object> values){
        return dspOrder_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 序号
    */
    public SqlProjectModelFieldAnno orderBy_dspOrder_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" dsp_order asc");
        return this;
    }
    /**
    * 序号
    */
    public SqlProjectModelFieldAnno orderBy_dspOrder_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" dsp_order desc");
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_eq(boolean cdn, String value){
        if(!cdn) return this;
        return comment_eq(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_eq(String value){
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
    public SqlProjectModelFieldAnno comment_notNull_eq(String value){
        return comment_eq(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_isdb_null(boolean cdn){
        if(!cdn) return this;
        return comment_isdb_null();
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_isdb_null(){
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
    public SqlProjectModelFieldAnno comment_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return comment_isdb_not_null();
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_isdb_not_null(){
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
    public SqlProjectModelFieldAnno comment_gt(boolean cdn, String value){
        if(!cdn) return this;
        return comment_gt(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_gt(String value){
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
    public SqlProjectModelFieldAnno comment_notNull_gt(String value){
        return comment_gt(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_lt(boolean cdn, String value){
        if(!cdn) return this;
        return comment_lt(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_lt(String value){
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
    public SqlProjectModelFieldAnno comment_notNull_lt(String value){
        return comment_lt(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_ge(boolean cdn, String value){
        if(!cdn) return this;
        return comment_ge(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_ge(String value){
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
    public SqlProjectModelFieldAnno comment_notNull_ge(String value){
        return comment_ge(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_le(boolean cdn, String value){
        if(!cdn) return this;
        return comment_le(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_le(String value){
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
    public SqlProjectModelFieldAnno comment_notNull_le(String value){
        return comment_le(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_ne(boolean cdn, String value){
        if(!cdn) return this;
        return comment_ne(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_ne(String value){
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
    public SqlProjectModelFieldAnno comment_notNull_ne(String value){
        return comment_ne(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_notBlank_eq(String value){
        return comment_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return comment_leftLike(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_leftLike(String value){
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
    public SqlProjectModelFieldAnno comment_notBlank_leftLike(String value){
        return comment_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return comment_rightLike(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_rightLike(String value){
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
    public SqlProjectModelFieldAnno comment_notBlank_rightLike(String value){
        return comment_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return comment_middleLike(value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_middleLike(String value){
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
    public SqlProjectModelFieldAnno comment_notBlank_middleLike(String value){
        return comment_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return comment_in(values);
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno comment_in(Set<Object> values){
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
    public SqlProjectModelFieldAnno comment_notEmpty_in(Set<Object> values){
        return comment_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 备注
    */
    public SqlProjectModelFieldAnno orderBy_comment_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" comment asc");
        return this;
    }
    /**
    * 备注
    */
    public SqlProjectModelFieldAnno orderBy_comment_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" comment desc");
        return this;
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_eq(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_eq(Integer value){
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
    public SqlProjectModelFieldAnno deleteStatus_notNull_eq(Integer value){
        return deleteStatus_eq(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_isdb_null(boolean cdn){
        if(!cdn) return this;
        return deleteStatus_isdb_null();
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_isdb_null(){
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
    public SqlProjectModelFieldAnno deleteStatus_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return deleteStatus_isdb_not_null();
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_isdb_not_null(){
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
    public SqlProjectModelFieldAnno deleteStatus_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_gt(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_gt(Integer value){
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
    public SqlProjectModelFieldAnno deleteStatus_notNull_gt(Integer value){
        return deleteStatus_gt(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_lt(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_lt(Integer value){
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
    public SqlProjectModelFieldAnno deleteStatus_notNull_lt(Integer value){
        return deleteStatus_lt(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_ge(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_ge(Integer value){
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
    public SqlProjectModelFieldAnno deleteStatus_notNull_ge(Integer value){
        return deleteStatus_ge(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_le(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_le(Integer value){
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
    public SqlProjectModelFieldAnno deleteStatus_notNull_le(Integer value){
        return deleteStatus_le(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_ne(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_ne(Integer value){
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
    public SqlProjectModelFieldAnno deleteStatus_notNull_ne(Integer value){
        return deleteStatus_ne(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return deleteStatus_in(values);
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno deleteStatus_in(Set<Object> values){
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
    public SqlProjectModelFieldAnno deleteStatus_notEmpty_in(Set<Object> values){
        return deleteStatus_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno orderBy_deleteStatus_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" delete_status asc");
        return this;
    }
    /**
    * 删除状态
    */
    public SqlProjectModelFieldAnno orderBy_deleteStatus_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" delete_status desc");
        return this;
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_eq(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_eq(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_eq(Timestamp value){
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
    public SqlProjectModelFieldAnno createTime_notNull_eq(Timestamp value){
        return createTime_eq(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_eq_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_eq_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_eq_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_eq(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_notNull_eq_yyyyMMdd(Timestamp value){
        return createTime_eq_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_isdb_null(boolean cdn){
        if(!cdn) return this;
        return createTime_isdb_null();
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_isdb_null(){
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
    public SqlProjectModelFieldAnno createTime_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return createTime_isdb_not_null();
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_isdb_not_null(){
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
    public SqlProjectModelFieldAnno createTime_gt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_gt(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_gt(Timestamp value){
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
    public SqlProjectModelFieldAnno createTime_notNull_gt(Timestamp value){
        return createTime_gt(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_gt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_gt_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_gt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_gt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_notNull_gt_yyyyMMdd(Timestamp value){
        return createTime_gt_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_lt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_lt(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_lt(Timestamp value){
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
    public SqlProjectModelFieldAnno createTime_notNull_lt(Timestamp value){
        return createTime_lt(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_lt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_lt_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_lt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_lt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_notNull_lt_yyyyMMdd(Timestamp value){
        return createTime_lt_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModelFieldAnno createTime_lt_nextDay_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_lt_nextDay_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModelFieldAnno createTime_lt_nextDay_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(DateUtil.offsetDay(value, 1)).toTimestamp();
        return createTime_lt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModelFieldAnno createTime_notNull_lt_nextDay_yyyyMMdd(Timestamp value){
        return createTime_lt_nextDay_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_ge(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ge(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_ge(Timestamp value){
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
    public SqlProjectModelFieldAnno createTime_notNull_ge(Timestamp value){
        return createTime_ge(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_ge_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ge_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_ge_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_ge(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_notNull_ge_yyyyMMdd(Timestamp value){
        return createTime_ge_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_le(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_le(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_le(Timestamp value){
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
    public SqlProjectModelFieldAnno createTime_notNull_le(Timestamp value){
        return createTime_le(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_le_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_le_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_le_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_le(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_notNull_le_yyyyMMdd(Timestamp value){
        return createTime_le_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_ne(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ne(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_ne(Timestamp value){
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
    public SqlProjectModelFieldAnno createTime_notNull_ne(Timestamp value){
        return createTime_ne(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_ne_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ne_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_ne_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_ne(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno createTime_notNull_ne_yyyyMMdd(Timestamp value){
        return createTime_ne_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return createTime_in(values);
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno createTime_in(Set<Object> values){
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
    public SqlProjectModelFieldAnno createTime_notEmpty_in(Set<Object> values){
        return createTime_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno orderBy_createTime_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" create_time asc");
        return this;
    }
    /**
    * 创建时间
    */
    public SqlProjectModelFieldAnno orderBy_createTime_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" create_time desc");
        return this;
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_eq(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_eq(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_eq(Timestamp value){
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
    public SqlProjectModelFieldAnno updateTime_notNull_eq(Timestamp value){
        return updateTime_eq(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_eq_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_eq_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_eq_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_eq(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_notNull_eq_yyyyMMdd(Timestamp value){
        return updateTime_eq_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_isdb_null(boolean cdn){
        if(!cdn) return this;
        return updateTime_isdb_null();
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_isdb_null(){
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
    public SqlProjectModelFieldAnno updateTime_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return updateTime_isdb_not_null();
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_isdb_not_null(){
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
    public SqlProjectModelFieldAnno updateTime_gt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_gt(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_gt(Timestamp value){
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
    public SqlProjectModelFieldAnno updateTime_notNull_gt(Timestamp value){
        return updateTime_gt(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_gt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_gt_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_gt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_gt(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_notNull_gt_yyyyMMdd(Timestamp value){
        return updateTime_gt_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_lt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_lt(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_lt(Timestamp value){
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
    public SqlProjectModelFieldAnno updateTime_notNull_lt(Timestamp value){
        return updateTime_lt(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_lt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_lt_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_lt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_lt(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_notNull_lt_yyyyMMdd(Timestamp value){
        return updateTime_lt_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModelFieldAnno updateTime_lt_nextDay_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_lt_nextDay_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModelFieldAnno updateTime_lt_nextDay_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(DateUtil.offsetDay(value, 1)).toTimestamp();
        return updateTime_lt(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModelFieldAnno updateTime_notNull_lt_nextDay_yyyyMMdd(Timestamp value){
        return updateTime_lt_nextDay_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_ge(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ge(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_ge(Timestamp value){
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
    public SqlProjectModelFieldAnno updateTime_notNull_ge(Timestamp value){
        return updateTime_ge(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_ge_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ge_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_ge_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_ge(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_notNull_ge_yyyyMMdd(Timestamp value){
        return updateTime_ge_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_le(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_le(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_le(Timestamp value){
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
    public SqlProjectModelFieldAnno updateTime_notNull_le(Timestamp value){
        return updateTime_le(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_le_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_le_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_le_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_le(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_notNull_le_yyyyMMdd(Timestamp value){
        return updateTime_le_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_ne(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ne(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_ne(Timestamp value){
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
    public SqlProjectModelFieldAnno updateTime_notNull_ne(Timestamp value){
        return updateTime_ne(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_ne_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ne_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_ne_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_ne(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModelFieldAnno updateTime_notNull_ne_yyyyMMdd(Timestamp value){
        return updateTime_ne_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return updateTime_in(values);
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno updateTime_in(Set<Object> values){
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
    public SqlProjectModelFieldAnno updateTime_notEmpty_in(Set<Object> values){
        return updateTime_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno orderBy_updateTime_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" update_time asc");
        return this;
    }
    /**
    * 更新时间
    */
    public SqlProjectModelFieldAnno orderBy_updateTime_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" update_time desc");
        return this;
    }
    @Override
    public String toSqlString() {
        StringBuilder buf=new StringBuilder("SELECT * FROM project_model_field_anno");
        buf.append(builder);
        buf.append(orderBuilder);
        return buf.toString();
    }
    @Override
    public String toSqlString(int start, int limit) {
        StringBuilder buf=new StringBuilder("SELECT * FROM project_model_field_anno");
        buf.append(builder);
        buf.append(orderBuilder);
        buf.append(" limit ");
        buf.append(start);
        buf.append(", ");
        buf.append(limit);
        return buf.toString();
    }
    @Override
    public String toCountSqlString() {
        StringBuilder buf=new StringBuilder("SELECT COUNT(1) FROM project_model_field_anno");
        buf.append(builder);
        return buf.toString();
    }
    @Override
    public List<Object> getParameters() {
        return this.parameters;
    }
}
