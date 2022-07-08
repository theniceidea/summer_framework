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
* 数据模型
*/
public class SqlProjectModel implements QuerySelect {
    private static final String ID_SUFFIX="00A2";
    private static Object DB_TARGET=null;
    private Object instDbTarget__ =null;
    private static final String SQL_INSERT="INSERT INTO project_model(id,project_id,project_db_id,model_type,table_model,summer_model,summer_ns,imports,cname,ename,extends_info,dsp_order,comment,delete_status)values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE project_model SET project_id=?,project_db_id=?,model_type=?,table_model=?,summer_model=?,summer_ns=?,imports=?,cname=?,ename=?,extends_info=?,dsp_order=?,comment=?,delete_status=? WHERE id=?";
    private static final String SQL_DELETE="DELETE project_model WHERE id=?";
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

    public SqlProjectModel changeDbTarget(String name){
        instDbTarget__ =DaoJdbcTemplate.s(name);
        return this;
    }

    public static SqlProjectModel inst(){
        final SqlProjectModel sqlProjectModel = new SqlProjectModel();
        return sqlProjectModel;
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
        StringBuilder buf=new StringBuilder("UPDATE project_model SET");
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
    public int save(ProjectModel entity){
        if(null==entity.getId()){
            return insert(entity);
        }else{
            return update(entity);
        }
    }
    private int insert(ProjectModel entity){
        List<Object> values = new ArrayList<>(14);
        values.add(entity.getId());
        values.add(entity.getProjectId());
        values.add(entity.getProjectDbId());
        values.add(entity.getModelType());
        values.add(entity.getTableModel());
        values.add(entity.getSummerModel());
        values.add(entity.getSummerNs());
        values.add(entity.getImports());
        values.add(entity.getCname());
        values.add(entity.getEname());
        values.add(entity.getExtendsInfo());
        values.add(entity.getDspOrder());
        values.add(entity.getComment());
        values.add(entity.getDeleteStatus());

        int retNum = DaoExecuteChangeSql.s(ds(), SQL_INSERT, values);
        return retNum;
    }
    private int update(ProjectModel entity){
        List<Object> values = new ArrayList<>(14);
        values.add(entity.getProjectId());
        values.add(entity.getProjectDbId());
        values.add(entity.getModelType());
        values.add(entity.getTableModel());
        values.add(entity.getSummerModel());
        values.add(entity.getSummerNs());
        values.add(entity.getImports());
        values.add(entity.getCname());
        values.add(entity.getEname());
        values.add(entity.getExtendsInfo());
        values.add(entity.getDspOrder());
        values.add(entity.getComment());
        values.add(entity.getDeleteStatus());
        values.add(entity.getId());

        return DaoExecuteChangeSql.s(ds(), SQL_UPDATE, values);
    }
    
    public int del(ProjectModel entity){
        List<Object> values = new ArrayList<>(1);
        values.add(entity.getId());
        return DaoExecuteChangeSql.s(ds(), SQL_DELETE, values);
    }
    public ProjectModel queryOne(){
        final List<ProjectModel> list = DaoList.s(ds(), ProjectModel.class, this, 0, 1);
        return list.size()>0?list.get(0):null;
    }
    public List<ProjectModel> queryList(){
        return DaoList.s(ds(), ProjectModel.class, this);
    }
    public List<ProjectModel> queryList(int start, int limit){
        return DaoList.s(ds(), ProjectModel.class, this, start, limit);
    }
    public PageResultList<ProjectModel> queryPageList(int start, int limit){
        return DaoPageList.s(ds(), ProjectModel.class, this, start, limit);
    }
    /**
    * id
    */
    public SqlProjectModel id_eq(boolean cdn, String value){
        if(!cdn) return this;
        return id_eq(value);
    }
    /**
    * id
    */
    public SqlProjectModel id_eq(String value){
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
    public SqlProjectModel id_notNull_eq(String value){
        return id_eq(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModel id_isdb_null(boolean cdn){
        if(!cdn) return this;
        return id_isdb_null();
    }
    /**
    * id
    */
    public SqlProjectModel id_isdb_null(){
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
    public SqlProjectModel id_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return id_isdb_not_null();
    }
    /**
    * id
    */
    public SqlProjectModel id_isdb_not_null(){
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
    public SqlProjectModel id_gt(boolean cdn, String value){
        if(!cdn) return this;
        return id_gt(value);
    }
    /**
    * id
    */
    public SqlProjectModel id_gt(String value){
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
    public SqlProjectModel id_notNull_gt(String value){
        return id_gt(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModel id_lt(boolean cdn, String value){
        if(!cdn) return this;
        return id_lt(value);
    }
    /**
    * id
    */
    public SqlProjectModel id_lt(String value){
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
    public SqlProjectModel id_notNull_lt(String value){
        return id_lt(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModel id_ge(boolean cdn, String value){
        if(!cdn) return this;
        return id_ge(value);
    }
    /**
    * id
    */
    public SqlProjectModel id_ge(String value){
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
    public SqlProjectModel id_notNull_ge(String value){
        return id_ge(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModel id_le(boolean cdn, String value){
        if(!cdn) return this;
        return id_le(value);
    }
    /**
    * id
    */
    public SqlProjectModel id_le(String value){
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
    public SqlProjectModel id_notNull_le(String value){
        return id_le(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModel id_ne(boolean cdn, String value){
        if(!cdn) return this;
        return id_ne(value);
    }
    /**
    * id
    */
    public SqlProjectModel id_ne(String value){
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
    public SqlProjectModel id_notNull_ne(String value){
        return id_ne(null != value, value);
    }
    /**
    * id
    */
    public SqlProjectModel id_notBlank_eq(String value){
        return id_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProjectModel id_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return id_leftLike(value);
    }
    /**
    * id
    */
    public SqlProjectModel id_leftLike(String value){
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
    public SqlProjectModel id_notBlank_leftLike(String value){
        return id_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProjectModel id_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return id_rightLike(value);
    }
    /**
    * id
    */
    public SqlProjectModel id_rightLike(String value){
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
    public SqlProjectModel id_notBlank_rightLike(String value){
        return id_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProjectModel id_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return id_middleLike(value);
    }
    /**
    * id
    */
    public SqlProjectModel id_middleLike(String value){
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
    public SqlProjectModel id_notBlank_middleLike(String value){
        return id_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProjectModel id_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return id_in(values);
    }
    /**
    * id
    */
    public SqlProjectModel id_in(Set<Object> values){
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
    public SqlProjectModel id_notEmpty_in(Set<Object> values){
        return id_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 项目id
    */
    public SqlProjectModel projectId_eq(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_eq(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_eq(String value){
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
    public SqlProjectModel projectId_notNull_eq(String value){
        return projectId_eq(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_isdb_null(boolean cdn){
        if(!cdn) return this;
        return projectId_isdb_null();
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_isdb_null(){
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
    public SqlProjectModel projectId_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return projectId_isdb_not_null();
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_isdb_not_null(){
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
    public SqlProjectModel projectId_gt(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_gt(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_gt(String value){
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
    public SqlProjectModel projectId_notNull_gt(String value){
        return projectId_gt(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_lt(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_lt(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_lt(String value){
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
    public SqlProjectModel projectId_notNull_lt(String value){
        return projectId_lt(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_ge(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_ge(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_ge(String value){
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
    public SqlProjectModel projectId_notNull_ge(String value){
        return projectId_ge(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_le(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_le(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_le(String value){
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
    public SqlProjectModel projectId_notNull_le(String value){
        return projectId_le(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_ne(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_ne(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_ne(String value){
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
    public SqlProjectModel projectId_notNull_ne(String value){
        return projectId_ne(null != value, value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_notBlank_eq(String value){
        return projectId_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_leftLike(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_leftLike(String value){
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
    public SqlProjectModel projectId_notBlank_leftLike(String value){
        return projectId_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_rightLike(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_rightLike(String value){
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
    public SqlProjectModel projectId_notBlank_rightLike(String value){
        return projectId_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectId_middleLike(value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_middleLike(String value){
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
    public SqlProjectModel projectId_notBlank_middleLike(String value){
        return projectId_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return projectId_in(values);
    }
    /**
    * 项目id
    */
    public SqlProjectModel projectId_in(Set<Object> values){
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
    public SqlProjectModel projectId_notEmpty_in(Set<Object> values){
        return projectId_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_eq(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_eq(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_eq(String value){
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
    public SqlProjectModel projectDbId_notNull_eq(String value){
        return projectDbId_eq(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_isdb_null(boolean cdn){
        if(!cdn) return this;
        return projectDbId_isdb_null();
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_isdb_null(){
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
    public SqlProjectModel projectDbId_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return projectDbId_isdb_not_null();
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_isdb_not_null(){
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
    public SqlProjectModel projectDbId_gt(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_gt(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_gt(String value){
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
    public SqlProjectModel projectDbId_notNull_gt(String value){
        return projectDbId_gt(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_lt(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_lt(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_lt(String value){
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
    public SqlProjectModel projectDbId_notNull_lt(String value){
        return projectDbId_lt(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_ge(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_ge(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_ge(String value){
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
    public SqlProjectModel projectDbId_notNull_ge(String value){
        return projectDbId_ge(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_le(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_le(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_le(String value){
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
    public SqlProjectModel projectDbId_notNull_le(String value){
        return projectDbId_le(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_ne(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_ne(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_ne(String value){
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
    public SqlProjectModel projectDbId_notNull_ne(String value){
        return projectDbId_ne(null != value, value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_notBlank_eq(String value){
        return projectDbId_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_leftLike(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_leftLike(String value){
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
    public SqlProjectModel projectDbId_notBlank_leftLike(String value){
        return projectDbId_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_rightLike(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_rightLike(String value){
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
    public SqlProjectModel projectDbId_notBlank_rightLike(String value){
        return projectDbId_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return projectDbId_middleLike(value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_middleLike(String value){
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
    public SqlProjectModel projectDbId_notBlank_middleLike(String value){
        return projectDbId_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return projectDbId_in(values);
    }
    /**
    * 数据库id
    */
    public SqlProjectModel projectDbId_in(Set<Object> values){
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
    public SqlProjectModel projectDbId_notEmpty_in(Set<Object> values){
        return projectDbId_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 类别
    */
    public SqlProjectModel modelType_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return modelType_eq(value);
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_eq(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" model_type=?");
        parameters.add(value);
        return this;
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_notNull_eq(Integer value){
        return modelType_eq(null != value, value);
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_isdb_null(boolean cdn){
        if(!cdn) return this;
        return modelType_isdb_null();
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" model_type is null");
        return this;
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return modelType_isdb_not_null();
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" model_type is not null");
        return this;
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return modelType_gt(value);
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_gt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" model_type>?");
        parameters.add(value);
        return this;
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_notNull_gt(Integer value){
        return modelType_gt(null != value, value);
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return modelType_lt(value);
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_lt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" model_type<?");
        parameters.add(value);
        return this;
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_notNull_lt(Integer value){
        return modelType_lt(null != value, value);
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return modelType_ge(value);
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_ge(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" model_type>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_notNull_ge(Integer value){
        return modelType_ge(null != value, value);
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return modelType_le(value);
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_le(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" model_type<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_notNull_le(Integer value){
        return modelType_le(null != value, value);
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return modelType_ne(value);
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_ne(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" model_type<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_notNull_ne(Integer value){
        return modelType_ne(null != value, value);
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return modelType_in(values);
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_in(Set<Object> values){
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
        builder.append(pre+" model_type in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 类别
    */
    public SqlProjectModel modelType_notEmpty_in(Set<Object> values){
        return modelType_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return tableModel_eq(value);
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_eq(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" table_model=?");
        parameters.add(value);
        return this;
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_notNull_eq(Integer value){
        return tableModel_eq(null != value, value);
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_isdb_null(boolean cdn){
        if(!cdn) return this;
        return tableModel_isdb_null();
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" table_model is null");
        return this;
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return tableModel_isdb_not_null();
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" table_model is not null");
        return this;
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return tableModel_gt(value);
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_gt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" table_model>?");
        parameters.add(value);
        return this;
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_notNull_gt(Integer value){
        return tableModel_gt(null != value, value);
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return tableModel_lt(value);
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_lt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" table_model<?");
        parameters.add(value);
        return this;
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_notNull_lt(Integer value){
        return tableModel_lt(null != value, value);
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return tableModel_ge(value);
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_ge(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" table_model>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_notNull_ge(Integer value){
        return tableModel_ge(null != value, value);
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return tableModel_le(value);
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_le(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" table_model<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_notNull_le(Integer value){
        return tableModel_le(null != value, value);
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return tableModel_ne(value);
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_ne(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" table_model<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_notNull_ne(Integer value){
        return tableModel_ne(null != value, value);
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return tableModel_in(values);
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_in(Set<Object> values){
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
        builder.append(pre+" table_model in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 是否数据库表mapping
    */
    public SqlProjectModel tableModel_notEmpty_in(Set<Object> values){
        return tableModel_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return summerModel_eq(value);
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_eq(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_model=?");
        parameters.add(value);
        return this;
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_notNull_eq(Integer value){
        return summerModel_eq(null != value, value);
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_isdb_null(boolean cdn){
        if(!cdn) return this;
        return summerModel_isdb_null();
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_model is null");
        return this;
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return summerModel_isdb_not_null();
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_model is not null");
        return this;
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return summerModel_gt(value);
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_gt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_model>?");
        parameters.add(value);
        return this;
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_notNull_gt(Integer value){
        return summerModel_gt(null != value, value);
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return summerModel_lt(value);
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_lt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_model<?");
        parameters.add(value);
        return this;
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_notNull_lt(Integer value){
        return summerModel_lt(null != value, value);
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return summerModel_ge(value);
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_ge(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_model>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_notNull_ge(Integer value){
        return summerModel_ge(null != value, value);
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return summerModel_le(value);
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_le(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_model<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_notNull_le(Integer value){
        return summerModel_le(null != value, value);
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return summerModel_ne(value);
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_ne(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_model<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_notNull_ne(Integer value){
        return summerModel_ne(null != value, value);
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return summerModel_in(values);
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_in(Set<Object> values){
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
        builder.append(pre+" summer_model in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 是否summer对象
    */
    public SqlProjectModel summerModel_notEmpty_in(Set<Object> values){
        return summerModel_in(null != values && !values.isEmpty(), values);
    }


    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_eq(boolean cdn, String value){
        if(!cdn) return this;
        return summerNs_eq(value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_ns=?");
        parameters.add(value);
        return this;
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_notNull_eq(String value){
        return summerNs_eq(null != value, value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_isdb_null(boolean cdn){
        if(!cdn) return this;
        return summerNs_isdb_null();
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_ns is null");
        return this;
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return summerNs_isdb_not_null();
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_ns is not null");
        return this;
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_gt(boolean cdn, String value){
        if(!cdn) return this;
        return summerNs_gt(value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_ns>?");
        parameters.add(value);
        return this;
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_notNull_gt(String value){
        return summerNs_gt(null != value, value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_lt(boolean cdn, String value){
        if(!cdn) return this;
        return summerNs_lt(value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_ns<?");
        parameters.add(value);
        return this;
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_notNull_lt(String value){
        return summerNs_lt(null != value, value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_ge(boolean cdn, String value){
        if(!cdn) return this;
        return summerNs_ge(value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_ns>=?");
        parameters.add(value);
        return this;
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_notNull_ge(String value){
        return summerNs_ge(null != value, value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_le(boolean cdn, String value){
        if(!cdn) return this;
        return summerNs_le(value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_ns<=?");
        parameters.add(value);
        return this;
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_notNull_le(String value){
        return summerNs_le(null != value, value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_ne(boolean cdn, String value){
        if(!cdn) return this;
        return summerNs_ne(value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_ns<>?");
        parameters.add(value);
        return this;
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_notNull_ne(String value){
        return summerNs_ne(null != value, value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_notBlank_eq(String value){
        return summerNs_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return summerNs_leftLike(value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_ns like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_notBlank_leftLike(String value){
        return summerNs_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return summerNs_rightLike(value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_ns like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_notBlank_rightLike(String value){
        return summerNs_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return summerNs_middleLike(value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" summer_ns like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_notBlank_middleLike(String value){
        return summerNs_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return summerNs_in(values);
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_in(Set<Object> values){
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
        builder.append(pre+" summer_ns in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * summer对象名称空间
    */
    public SqlProjectModel summerNs_notEmpty_in(Set<Object> values){
        return summerNs_in(null != values && !values.isEmpty(), values);
    }


    /**
    * imports
    */
    public SqlProjectModel imports_eq(boolean cdn, String value){
        if(!cdn) return this;
        return imports_eq(value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_eq(String value){
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
    public SqlProjectModel imports_notNull_eq(String value){
        return imports_eq(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_isdb_null(boolean cdn){
        if(!cdn) return this;
        return imports_isdb_null();
    }
    /**
    * imports
    */
    public SqlProjectModel imports_isdb_null(){
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
    public SqlProjectModel imports_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return imports_isdb_not_null();
    }
    /**
    * imports
    */
    public SqlProjectModel imports_isdb_not_null(){
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
    public SqlProjectModel imports_gt(boolean cdn, String value){
        if(!cdn) return this;
        return imports_gt(value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_gt(String value){
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
    public SqlProjectModel imports_notNull_gt(String value){
        return imports_gt(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_lt(boolean cdn, String value){
        if(!cdn) return this;
        return imports_lt(value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_lt(String value){
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
    public SqlProjectModel imports_notNull_lt(String value){
        return imports_lt(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_ge(boolean cdn, String value){
        if(!cdn) return this;
        return imports_ge(value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_ge(String value){
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
    public SqlProjectModel imports_notNull_ge(String value){
        return imports_ge(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_le(boolean cdn, String value){
        if(!cdn) return this;
        return imports_le(value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_le(String value){
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
    public SqlProjectModel imports_notNull_le(String value){
        return imports_le(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_ne(boolean cdn, String value){
        if(!cdn) return this;
        return imports_ne(value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_ne(String value){
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
    public SqlProjectModel imports_notNull_ne(String value){
        return imports_ne(null != value, value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_notBlank_eq(String value){
        return imports_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return imports_leftLike(value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_leftLike(String value){
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
    public SqlProjectModel imports_notBlank_leftLike(String value){
        return imports_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return imports_rightLike(value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_rightLike(String value){
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
    public SqlProjectModel imports_notBlank_rightLike(String value){
        return imports_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return imports_middleLike(value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_middleLike(String value){
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
    public SqlProjectModel imports_notBlank_middleLike(String value){
        return imports_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return imports_in(values);
    }
    /**
    * imports
    */
    public SqlProjectModel imports_in(Set<Object> values){
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
    public SqlProjectModel imports_notEmpty_in(Set<Object> values){
        return imports_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 中文名
    */
    public SqlProjectModel cname_eq(boolean cdn, String value){
        if(!cdn) return this;
        return cname_eq(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_eq(String value){
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
    public SqlProjectModel cname_notNull_eq(String value){
        return cname_eq(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_isdb_null(boolean cdn){
        if(!cdn) return this;
        return cname_isdb_null();
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_isdb_null(){
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
    public SqlProjectModel cname_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return cname_isdb_not_null();
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_isdb_not_null(){
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
    public SqlProjectModel cname_gt(boolean cdn, String value){
        if(!cdn) return this;
        return cname_gt(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_gt(String value){
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
    public SqlProjectModel cname_notNull_gt(String value){
        return cname_gt(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_lt(boolean cdn, String value){
        if(!cdn) return this;
        return cname_lt(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_lt(String value){
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
    public SqlProjectModel cname_notNull_lt(String value){
        return cname_lt(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_ge(boolean cdn, String value){
        if(!cdn) return this;
        return cname_ge(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_ge(String value){
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
    public SqlProjectModel cname_notNull_ge(String value){
        return cname_ge(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_le(boolean cdn, String value){
        if(!cdn) return this;
        return cname_le(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_le(String value){
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
    public SqlProjectModel cname_notNull_le(String value){
        return cname_le(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_ne(boolean cdn, String value){
        if(!cdn) return this;
        return cname_ne(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_ne(String value){
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
    public SqlProjectModel cname_notNull_ne(String value){
        return cname_ne(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_notBlank_eq(String value){
        return cname_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return cname_leftLike(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_leftLike(String value){
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
    public SqlProjectModel cname_notBlank_leftLike(String value){
        return cname_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return cname_rightLike(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_rightLike(String value){
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
    public SqlProjectModel cname_notBlank_rightLike(String value){
        return cname_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return cname_middleLike(value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_middleLike(String value){
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
    public SqlProjectModel cname_notBlank_middleLike(String value){
        return cname_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return cname_in(values);
    }
    /**
    * 中文名
    */
    public SqlProjectModel cname_in(Set<Object> values){
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
    public SqlProjectModel cname_notEmpty_in(Set<Object> values){
        return cname_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 英文名
    */
    public SqlProjectModel ename_eq(boolean cdn, String value){
        if(!cdn) return this;
        return ename_eq(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_eq(String value){
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
    public SqlProjectModel ename_notNull_eq(String value){
        return ename_eq(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_isdb_null(boolean cdn){
        if(!cdn) return this;
        return ename_isdb_null();
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_isdb_null(){
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
    public SqlProjectModel ename_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return ename_isdb_not_null();
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_isdb_not_null(){
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
    public SqlProjectModel ename_gt(boolean cdn, String value){
        if(!cdn) return this;
        return ename_gt(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_gt(String value){
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
    public SqlProjectModel ename_notNull_gt(String value){
        return ename_gt(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_lt(boolean cdn, String value){
        if(!cdn) return this;
        return ename_lt(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_lt(String value){
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
    public SqlProjectModel ename_notNull_lt(String value){
        return ename_lt(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_ge(boolean cdn, String value){
        if(!cdn) return this;
        return ename_ge(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_ge(String value){
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
    public SqlProjectModel ename_notNull_ge(String value){
        return ename_ge(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_le(boolean cdn, String value){
        if(!cdn) return this;
        return ename_le(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_le(String value){
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
    public SqlProjectModel ename_notNull_le(String value){
        return ename_le(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_ne(boolean cdn, String value){
        if(!cdn) return this;
        return ename_ne(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_ne(String value){
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
    public SqlProjectModel ename_notNull_ne(String value){
        return ename_ne(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_notBlank_eq(String value){
        return ename_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return ename_leftLike(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_leftLike(String value){
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
    public SqlProjectModel ename_notBlank_leftLike(String value){
        return ename_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return ename_rightLike(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_rightLike(String value){
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
    public SqlProjectModel ename_notBlank_rightLike(String value){
        return ename_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return ename_middleLike(value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_middleLike(String value){
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
    public SqlProjectModel ename_notBlank_middleLike(String value){
        return ename_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return ename_in(values);
    }
    /**
    * 英文名
    */
    public SqlProjectModel ename_in(Set<Object> values){
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
    public SqlProjectModel ename_notEmpty_in(Set<Object> values){
        return ename_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_eq(boolean cdn, String value){
        if(!cdn) return this;
        return extendsInfo_eq(value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" extends_info=?");
        parameters.add(value);
        return this;
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_notNull_eq(String value){
        return extendsInfo_eq(null != value, value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_isdb_null(boolean cdn){
        if(!cdn) return this;
        return extendsInfo_isdb_null();
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" extends_info is null");
        return this;
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return extendsInfo_isdb_not_null();
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" extends_info is not null");
        return this;
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_gt(boolean cdn, String value){
        if(!cdn) return this;
        return extendsInfo_gt(value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" extends_info>?");
        parameters.add(value);
        return this;
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_notNull_gt(String value){
        return extendsInfo_gt(null != value, value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_lt(boolean cdn, String value){
        if(!cdn) return this;
        return extendsInfo_lt(value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" extends_info<?");
        parameters.add(value);
        return this;
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_notNull_lt(String value){
        return extendsInfo_lt(null != value, value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_ge(boolean cdn, String value){
        if(!cdn) return this;
        return extendsInfo_ge(value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" extends_info>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_notNull_ge(String value){
        return extendsInfo_ge(null != value, value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_le(boolean cdn, String value){
        if(!cdn) return this;
        return extendsInfo_le(value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" extends_info<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_notNull_le(String value){
        return extendsInfo_le(null != value, value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_ne(boolean cdn, String value){
        if(!cdn) return this;
        return extendsInfo_ne(value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" extends_info<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_notNull_ne(String value){
        return extendsInfo_ne(null != value, value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_notBlank_eq(String value){
        return extendsInfo_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return extendsInfo_leftLike(value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" extends_info like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_notBlank_leftLike(String value){
        return extendsInfo_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return extendsInfo_rightLike(value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" extends_info like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_notBlank_rightLike(String value){
        return extendsInfo_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return extendsInfo_middleLike(value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" extends_info like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_notBlank_middleLike(String value){
        return extendsInfo_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return extendsInfo_in(values);
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_in(Set<Object> values){
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
        builder.append(pre+" extends_info in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 继承信息
    */
    public SqlProjectModel extendsInfo_notEmpty_in(Set<Object> values){
        return extendsInfo_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 序号
    */
    public SqlProjectModel dspOrder_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_eq(value);
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_eq(Integer value){
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
    public SqlProjectModel dspOrder_notNull_eq(Integer value){
        return dspOrder_eq(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_isdb_null(boolean cdn){
        if(!cdn) return this;
        return dspOrder_isdb_null();
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_isdb_null(){
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
    public SqlProjectModel dspOrder_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return dspOrder_isdb_not_null();
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_isdb_not_null(){
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
    public SqlProjectModel dspOrder_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_gt(value);
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_gt(Integer value){
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
    public SqlProjectModel dspOrder_notNull_gt(Integer value){
        return dspOrder_gt(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_lt(value);
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_lt(Integer value){
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
    public SqlProjectModel dspOrder_notNull_lt(Integer value){
        return dspOrder_lt(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_ge(value);
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_ge(Integer value){
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
    public SqlProjectModel dspOrder_notNull_ge(Integer value){
        return dspOrder_ge(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_le(value);
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_le(Integer value){
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
    public SqlProjectModel dspOrder_notNull_le(Integer value){
        return dspOrder_le(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_ne(value);
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_ne(Integer value){
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
    public SqlProjectModel dspOrder_notNull_ne(Integer value){
        return dspOrder_ne(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return dspOrder_in(values);
    }
    /**
    * 序号
    */
    public SqlProjectModel dspOrder_in(Set<Object> values){
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
    public SqlProjectModel dspOrder_notEmpty_in(Set<Object> values){
        return dspOrder_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 备注
    */
    public SqlProjectModel comment_eq(boolean cdn, String value){
        if(!cdn) return this;
        return comment_eq(value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_eq(String value){
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
    public SqlProjectModel comment_notNull_eq(String value){
        return comment_eq(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_isdb_null(boolean cdn){
        if(!cdn) return this;
        return comment_isdb_null();
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_isdb_null(){
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
    public SqlProjectModel comment_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return comment_isdb_not_null();
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_isdb_not_null(){
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
    public SqlProjectModel comment_gt(boolean cdn, String value){
        if(!cdn) return this;
        return comment_gt(value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_gt(String value){
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
    public SqlProjectModel comment_notNull_gt(String value){
        return comment_gt(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_lt(boolean cdn, String value){
        if(!cdn) return this;
        return comment_lt(value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_lt(String value){
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
    public SqlProjectModel comment_notNull_lt(String value){
        return comment_lt(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_ge(boolean cdn, String value){
        if(!cdn) return this;
        return comment_ge(value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_ge(String value){
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
    public SqlProjectModel comment_notNull_ge(String value){
        return comment_ge(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_le(boolean cdn, String value){
        if(!cdn) return this;
        return comment_le(value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_le(String value){
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
    public SqlProjectModel comment_notNull_le(String value){
        return comment_le(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_ne(boolean cdn, String value){
        if(!cdn) return this;
        return comment_ne(value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_ne(String value){
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
    public SqlProjectModel comment_notNull_ne(String value){
        return comment_ne(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_notBlank_eq(String value){
        return comment_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return comment_leftLike(value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_leftLike(String value){
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
    public SqlProjectModel comment_notBlank_leftLike(String value){
        return comment_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return comment_rightLike(value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_rightLike(String value){
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
    public SqlProjectModel comment_notBlank_rightLike(String value){
        return comment_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return comment_middleLike(value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_middleLike(String value){
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
    public SqlProjectModel comment_notBlank_middleLike(String value){
        return comment_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return comment_in(values);
    }
    /**
    * 备注
    */
    public SqlProjectModel comment_in(Set<Object> values){
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
    public SqlProjectModel comment_notEmpty_in(Set<Object> values){
        return comment_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_eq(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_eq(Integer value){
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
    public SqlProjectModel deleteStatus_notNull_eq(Integer value){
        return deleteStatus_eq(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_isdb_null(boolean cdn){
        if(!cdn) return this;
        return deleteStatus_isdb_null();
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_isdb_null(){
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
    public SqlProjectModel deleteStatus_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return deleteStatus_isdb_not_null();
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_isdb_not_null(){
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
    public SqlProjectModel deleteStatus_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_gt(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_gt(Integer value){
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
    public SqlProjectModel deleteStatus_notNull_gt(Integer value){
        return deleteStatus_gt(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_lt(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_lt(Integer value){
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
    public SqlProjectModel deleteStatus_notNull_lt(Integer value){
        return deleteStatus_lt(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_ge(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_ge(Integer value){
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
    public SqlProjectModel deleteStatus_notNull_ge(Integer value){
        return deleteStatus_ge(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_le(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_le(Integer value){
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
    public SqlProjectModel deleteStatus_notNull_le(Integer value){
        return deleteStatus_le(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_ne(value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_ne(Integer value){
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
    public SqlProjectModel deleteStatus_notNull_ne(Integer value){
        return deleteStatus_ne(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return deleteStatus_in(values);
    }
    /**
    * 删除状态
    */
    public SqlProjectModel deleteStatus_in(Set<Object> values){
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
    public SqlProjectModel deleteStatus_notEmpty_in(Set<Object> values){
        return deleteStatus_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 创建时间
    */
    public SqlProjectModel createTime_eq(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_eq(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_eq(Timestamp value){
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
    public SqlProjectModel createTime_notNull_eq(Timestamp value){
        return createTime_eq(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_eq_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_eq_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_eq_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_eq(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_notNull_eq_yyyyMMdd(Timestamp value){
        return createTime_eq_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_isdb_null(boolean cdn){
        if(!cdn) return this;
        return createTime_isdb_null();
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_isdb_null(){
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
    public SqlProjectModel createTime_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return createTime_isdb_not_null();
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_isdb_not_null(){
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
    public SqlProjectModel createTime_gt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_gt(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_gt(Timestamp value){
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
    public SqlProjectModel createTime_notNull_gt(Timestamp value){
        return createTime_gt(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_gt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_gt_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_gt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_gt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_notNull_gt_yyyyMMdd(Timestamp value){
        return createTime_gt_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_lt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_lt(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_lt(Timestamp value){
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
    public SqlProjectModel createTime_notNull_lt(Timestamp value){
        return createTime_lt(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_lt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_lt_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_lt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_lt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_notNull_lt_yyyyMMdd(Timestamp value){
        return createTime_lt_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModel createTime_lt_nextDay_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_lt_nextDay_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModel createTime_lt_nextDay_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(DateUtil.offsetDay(value, 1)).toTimestamp();
        return createTime_lt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModel createTime_notNull_lt_nextDay_yyyyMMdd(Timestamp value){
        return createTime_lt_nextDay_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_ge(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ge(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_ge(Timestamp value){
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
    public SqlProjectModel createTime_notNull_ge(Timestamp value){
        return createTime_ge(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_ge_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ge_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_ge_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_ge(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_notNull_ge_yyyyMMdd(Timestamp value){
        return createTime_ge_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_le(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_le(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_le(Timestamp value){
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
    public SqlProjectModel createTime_notNull_le(Timestamp value){
        return createTime_le(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_le_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_le_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_le_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_le(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_notNull_le_yyyyMMdd(Timestamp value){
        return createTime_le_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_ne(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ne(value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_ne(Timestamp value){
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
    public SqlProjectModel createTime_notNull_ne(Timestamp value){
        return createTime_ne(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_ne_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ne_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_ne_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_ne(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel createTime_notNull_ne_yyyyMMdd(Timestamp value){
        return createTime_ne_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return createTime_in(values);
    }
    /**
    * 创建时间
    */
    public SqlProjectModel createTime_in(Set<Object> values){
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
    public SqlProjectModel createTime_notEmpty_in(Set<Object> values){
        return createTime_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_eq(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_eq(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_eq(Timestamp value){
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
    public SqlProjectModel updateTime_notNull_eq(Timestamp value){
        return updateTime_eq(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_eq_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_eq_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_eq_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_eq(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_notNull_eq_yyyyMMdd(Timestamp value){
        return updateTime_eq_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_isdb_null(boolean cdn){
        if(!cdn) return this;
        return updateTime_isdb_null();
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_isdb_null(){
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
    public SqlProjectModel updateTime_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return updateTime_isdb_not_null();
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_isdb_not_null(){
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
    public SqlProjectModel updateTime_gt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_gt(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_gt(Timestamp value){
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
    public SqlProjectModel updateTime_notNull_gt(Timestamp value){
        return updateTime_gt(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_gt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_gt_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_gt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_gt(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_notNull_gt_yyyyMMdd(Timestamp value){
        return updateTime_gt_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_lt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_lt(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_lt(Timestamp value){
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
    public SqlProjectModel updateTime_notNull_lt(Timestamp value){
        return updateTime_lt(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_lt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_lt_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_lt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_lt(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_notNull_lt_yyyyMMdd(Timestamp value){
        return updateTime_lt_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModel updateTime_lt_nextDay_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_lt_nextDay_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModel updateTime_lt_nextDay_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(DateUtil.offsetDay(value, 1)).toTimestamp();
        return updateTime_lt(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProjectModel updateTime_notNull_lt_nextDay_yyyyMMdd(Timestamp value){
        return updateTime_lt_nextDay_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_ge(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ge(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_ge(Timestamp value){
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
    public SqlProjectModel updateTime_notNull_ge(Timestamp value){
        return updateTime_ge(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_ge_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ge_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_ge_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_ge(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_notNull_ge_yyyyMMdd(Timestamp value){
        return updateTime_ge_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_le(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_le(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_le(Timestamp value){
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
    public SqlProjectModel updateTime_notNull_le(Timestamp value){
        return updateTime_le(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_le_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_le_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_le_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_le(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_notNull_le_yyyyMMdd(Timestamp value){
        return updateTime_le_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_ne(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ne(value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_ne(Timestamp value){
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
    public SqlProjectModel updateTime_notNull_ne(Timestamp value){
        return updateTime_ne(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_ne_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ne_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_ne_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_ne(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProjectModel updateTime_notNull_ne_yyyyMMdd(Timestamp value){
        return updateTime_ne_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return updateTime_in(values);
    }
    /**
    * 更新时间
    */
    public SqlProjectModel updateTime_in(Set<Object> values){
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
    public SqlProjectModel updateTime_notEmpty_in(Set<Object> values){
        return updateTime_in(null != values && !values.isEmpty(), values);
    }


    @Override
    public String toSqlString() {
        StringBuilder buf=new StringBuilder("SELECT * FROM project_model");
        buf.append(builder);
        return buf.toString();
    }
    @Override
    public String toSqlString(int start, int limit) {
        StringBuilder buf=new StringBuilder("SELECT * FROM project_model");
        buf.append(builder);
        buf.append(" limit ");
        buf.append(start);
        buf.append(" ,");
        buf.append(limit);
        return buf.toString();
    }
    @Override
    public String toCountSqlString() {
        StringBuilder buf=new StringBuilder("SELECT COUNT(1) FROM project_model");
        buf.append(builder);
        return buf.toString();
    }
    @Override
    public List<Object> getParameters() {
        return this.parameters;
    }
}
