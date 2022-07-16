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
* 项目
*/
public class SqlProject implements QuerySelect {
    private static final String ID_SUFFIX="00A0";
    private static Object DB_TARGET=null;
    private Object instDbTarget__ =null;
    private static final String SQL_INSERT="INSERT INTO project(id,cname,ename,dsp_order,comment,delete_status)values(?,?,?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE project SET cname=?,ename=?,dsp_order=?,comment=?,delete_status=? WHERE id=?";
    private static final String SQL_DELETE="DELETE project WHERE id=?";
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

    public SqlProject changeDbTarget(String name){
        instDbTarget__ =DaoJdbcTemplate.s(name);
        return this;
    }

    public static SqlProject inst(){
        final SqlProject sqlProject = new SqlProject();
        return sqlProject;
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
        StringBuilder buf=new StringBuilder("UPDATE project SET");
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
    public int save(Project entity){
        if(null==entity.getId()){
            return insert(entity);
        }else{
            return update(entity);
        }
    }
    private int insert(Project entity){
        List<Object> values = new ArrayList<>(6);
        values.add(entity.getId());
        values.add(entity.getCname());
        values.add(entity.getEname());
        values.add(entity.getDspOrder());
        values.add(entity.getComment());
        values.add(entity.getDeleteStatus());

        int retNum = DaoExecuteChangeSql.s(ds(), SQL_INSERT, values);
        return retNum;
    }
    private int update(Project entity){
        List<Object> values = new ArrayList<>(6);
        values.add(entity.getCname());
        values.add(entity.getEname());
        values.add(entity.getDspOrder());
        values.add(entity.getComment());
        values.add(entity.getDeleteStatus());
        values.add(entity.getId());

        return DaoExecuteChangeSql.s(ds(), SQL_UPDATE, values);
    }
    
    public int del(Project entity){
        List<Object> values = new ArrayList<>(1);
        values.add(entity.getId());
        return DaoExecuteChangeSql.s(ds(), SQL_DELETE, values);
    }
    public Project queryOne(){
        final List<Project> list = DaoList.s(ds(), Project.class, this, 0, 1);
        return list.size()>0?list.get(0):null;
    }
    public List<Project> queryList(){
        return DaoList.s(ds(), Project.class, this);
    }
    public List<Project> queryList(int start, int limit){
        return DaoList.s(ds(), Project.class, this, start, limit);
    }
    public PageResultList<Project> queryPageList(int start, int limit){
        return DaoPageList.s(ds(), Project.class, this, start, limit);
    }
    /**
    * id
    */
    public SqlProject id_eq(boolean cdn, String value){
        if(!cdn) return this;
        return id_eq(value);
    }
    /**
    * id
    */
    public SqlProject id_eq(String value){
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
    public SqlProject id_notNull_eq(String value){
        return id_eq(null != value, value);
    }
    /**
    * id
    */
    public SqlProject id_isdb_null(boolean cdn){
        if(!cdn) return this;
        return id_isdb_null();
    }
    /**
    * id
    */
    public SqlProject id_isdb_null(){
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
    public SqlProject id_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return id_isdb_not_null();
    }
    /**
    * id
    */
    public SqlProject id_isdb_not_null(){
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
    public SqlProject id_gt(boolean cdn, String value){
        if(!cdn) return this;
        return id_gt(value);
    }
    /**
    * id
    */
    public SqlProject id_gt(String value){
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
    public SqlProject id_notNull_gt(String value){
        return id_gt(null != value, value);
    }
    /**
    * id
    */
    public SqlProject id_lt(boolean cdn, String value){
        if(!cdn) return this;
        return id_lt(value);
    }
    /**
    * id
    */
    public SqlProject id_lt(String value){
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
    public SqlProject id_notNull_lt(String value){
        return id_lt(null != value, value);
    }
    /**
    * id
    */
    public SqlProject id_ge(boolean cdn, String value){
        if(!cdn) return this;
        return id_ge(value);
    }
    /**
    * id
    */
    public SqlProject id_ge(String value){
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
    public SqlProject id_notNull_ge(String value){
        return id_ge(null != value, value);
    }
    /**
    * id
    */
    public SqlProject id_le(boolean cdn, String value){
        if(!cdn) return this;
        return id_le(value);
    }
    /**
    * id
    */
    public SqlProject id_le(String value){
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
    public SqlProject id_notNull_le(String value){
        return id_le(null != value, value);
    }
    /**
    * id
    */
    public SqlProject id_ne(boolean cdn, String value){
        if(!cdn) return this;
        return id_ne(value);
    }
    /**
    * id
    */
    public SqlProject id_ne(String value){
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
    public SqlProject id_notNull_ne(String value){
        return id_ne(null != value, value);
    }
    /**
    * id
    */
    public SqlProject id_notBlank_eq(String value){
        return id_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProject id_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return id_leftLike(value);
    }
    /**
    * id
    */
    public SqlProject id_leftLike(String value){
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
    public SqlProject id_notBlank_leftLike(String value){
        return id_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProject id_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return id_rightLike(value);
    }
    /**
    * id
    */
    public SqlProject id_rightLike(String value){
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
    public SqlProject id_notBlank_rightLike(String value){
        return id_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProject id_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return id_middleLike(value);
    }
    /**
    * id
    */
    public SqlProject id_middleLike(String value){
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
    public SqlProject id_notBlank_middleLike(String value){
        return id_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * id
    */
    public SqlProject id_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return id_in(values);
    }
    /**
    * id
    */
    public SqlProject id_in(Set<Object> values){
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
    public SqlProject id_notEmpty_in(Set<Object> values){
        return id_in(null != values && !values.isEmpty(), values);
    }


    /**
    * id
    */
    public SqlProject orderBy_id_asc(){
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
    public SqlProject orderBy_id_desc(){
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
    * 中文名
    */
    public SqlProject cname_eq(boolean cdn, String value){
        if(!cdn) return this;
        return cname_eq(value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_eq(String value){
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
    public SqlProject cname_notNull_eq(String value){
        return cname_eq(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_isdb_null(boolean cdn){
        if(!cdn) return this;
        return cname_isdb_null();
    }
    /**
    * 中文名
    */
    public SqlProject cname_isdb_null(){
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
    public SqlProject cname_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return cname_isdb_not_null();
    }
    /**
    * 中文名
    */
    public SqlProject cname_isdb_not_null(){
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
    public SqlProject cname_gt(boolean cdn, String value){
        if(!cdn) return this;
        return cname_gt(value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_gt(String value){
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
    public SqlProject cname_notNull_gt(String value){
        return cname_gt(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_lt(boolean cdn, String value){
        if(!cdn) return this;
        return cname_lt(value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_lt(String value){
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
    public SqlProject cname_notNull_lt(String value){
        return cname_lt(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_ge(boolean cdn, String value){
        if(!cdn) return this;
        return cname_ge(value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_ge(String value){
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
    public SqlProject cname_notNull_ge(String value){
        return cname_ge(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_le(boolean cdn, String value){
        if(!cdn) return this;
        return cname_le(value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_le(String value){
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
    public SqlProject cname_notNull_le(String value){
        return cname_le(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_ne(boolean cdn, String value){
        if(!cdn) return this;
        return cname_ne(value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_ne(String value){
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
    public SqlProject cname_notNull_ne(String value){
        return cname_ne(null != value, value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_notBlank_eq(String value){
        return cname_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return cname_leftLike(value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_leftLike(String value){
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
    public SqlProject cname_notBlank_leftLike(String value){
        return cname_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return cname_rightLike(value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_rightLike(String value){
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
    public SqlProject cname_notBlank_rightLike(String value){
        return cname_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return cname_middleLike(value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_middleLike(String value){
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
    public SqlProject cname_notBlank_middleLike(String value){
        return cname_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 中文名
    */
    public SqlProject cname_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return cname_in(values);
    }
    /**
    * 中文名
    */
    public SqlProject cname_in(Set<Object> values){
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
    public SqlProject cname_notEmpty_in(Set<Object> values){
        return cname_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 中文名
    */
    public SqlProject orderBy_cname_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" cname asc");
        return this;
    }
    /**
    * 中文名
    */
    public SqlProject orderBy_cname_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" cname desc");
        return this;
    }
    /**
    * 英文名
    */
    public SqlProject ename_eq(boolean cdn, String value){
        if(!cdn) return this;
        return ename_eq(value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_eq(String value){
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
    public SqlProject ename_notNull_eq(String value){
        return ename_eq(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_isdb_null(boolean cdn){
        if(!cdn) return this;
        return ename_isdb_null();
    }
    /**
    * 英文名
    */
    public SqlProject ename_isdb_null(){
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
    public SqlProject ename_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return ename_isdb_not_null();
    }
    /**
    * 英文名
    */
    public SqlProject ename_isdb_not_null(){
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
    public SqlProject ename_gt(boolean cdn, String value){
        if(!cdn) return this;
        return ename_gt(value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_gt(String value){
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
    public SqlProject ename_notNull_gt(String value){
        return ename_gt(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_lt(boolean cdn, String value){
        if(!cdn) return this;
        return ename_lt(value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_lt(String value){
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
    public SqlProject ename_notNull_lt(String value){
        return ename_lt(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_ge(boolean cdn, String value){
        if(!cdn) return this;
        return ename_ge(value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_ge(String value){
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
    public SqlProject ename_notNull_ge(String value){
        return ename_ge(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_le(boolean cdn, String value){
        if(!cdn) return this;
        return ename_le(value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_le(String value){
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
    public SqlProject ename_notNull_le(String value){
        return ename_le(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_ne(boolean cdn, String value){
        if(!cdn) return this;
        return ename_ne(value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_ne(String value){
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
    public SqlProject ename_notNull_ne(String value){
        return ename_ne(null != value, value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_notBlank_eq(String value){
        return ename_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return ename_leftLike(value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_leftLike(String value){
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
    public SqlProject ename_notBlank_leftLike(String value){
        return ename_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return ename_rightLike(value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_rightLike(String value){
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
    public SqlProject ename_notBlank_rightLike(String value){
        return ename_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return ename_middleLike(value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_middleLike(String value){
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
    public SqlProject ename_notBlank_middleLike(String value){
        return ename_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 英文名
    */
    public SqlProject ename_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return ename_in(values);
    }
    /**
    * 英文名
    */
    public SqlProject ename_in(Set<Object> values){
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
    public SqlProject ename_notEmpty_in(Set<Object> values){
        return ename_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 英文名
    */
    public SqlProject orderBy_ename_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" ename asc");
        return this;
    }
    /**
    * 英文名
    */
    public SqlProject orderBy_ename_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        orderBuilder.append(pre+" ename desc");
        return this;
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_eq(value);
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_eq(Integer value){
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
    public SqlProject dspOrder_notNull_eq(Integer value){
        return dspOrder_eq(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_isdb_null(boolean cdn){
        if(!cdn) return this;
        return dspOrder_isdb_null();
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_isdb_null(){
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
    public SqlProject dspOrder_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return dspOrder_isdb_not_null();
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_isdb_not_null(){
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
    public SqlProject dspOrder_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_gt(value);
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_gt(Integer value){
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
    public SqlProject dspOrder_notNull_gt(Integer value){
        return dspOrder_gt(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_lt(value);
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_lt(Integer value){
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
    public SqlProject dspOrder_notNull_lt(Integer value){
        return dspOrder_lt(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_ge(value);
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_ge(Integer value){
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
    public SqlProject dspOrder_notNull_ge(Integer value){
        return dspOrder_ge(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_le(value);
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_le(Integer value){
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
    public SqlProject dspOrder_notNull_le(Integer value){
        return dspOrder_le(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return dspOrder_ne(value);
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_ne(Integer value){
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
    public SqlProject dspOrder_notNull_ne(Integer value){
        return dspOrder_ne(null != value, value);
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return dspOrder_in(values);
    }
    /**
    * 序号
    */
    public SqlProject dspOrder_in(Set<Object> values){
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
    public SqlProject dspOrder_notEmpty_in(Set<Object> values){
        return dspOrder_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 序号
    */
    public SqlProject orderBy_dspOrder_asc(){
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
    public SqlProject orderBy_dspOrder_desc(){
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
    public SqlProject comment_eq(boolean cdn, String value){
        if(!cdn) return this;
        return comment_eq(value);
    }
    /**
    * 备注
    */
    public SqlProject comment_eq(String value){
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
    public SqlProject comment_notNull_eq(String value){
        return comment_eq(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProject comment_isdb_null(boolean cdn){
        if(!cdn) return this;
        return comment_isdb_null();
    }
    /**
    * 备注
    */
    public SqlProject comment_isdb_null(){
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
    public SqlProject comment_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return comment_isdb_not_null();
    }
    /**
    * 备注
    */
    public SqlProject comment_isdb_not_null(){
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
    public SqlProject comment_gt(boolean cdn, String value){
        if(!cdn) return this;
        return comment_gt(value);
    }
    /**
    * 备注
    */
    public SqlProject comment_gt(String value){
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
    public SqlProject comment_notNull_gt(String value){
        return comment_gt(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProject comment_lt(boolean cdn, String value){
        if(!cdn) return this;
        return comment_lt(value);
    }
    /**
    * 备注
    */
    public SqlProject comment_lt(String value){
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
    public SqlProject comment_notNull_lt(String value){
        return comment_lt(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProject comment_ge(boolean cdn, String value){
        if(!cdn) return this;
        return comment_ge(value);
    }
    /**
    * 备注
    */
    public SqlProject comment_ge(String value){
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
    public SqlProject comment_notNull_ge(String value){
        return comment_ge(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProject comment_le(boolean cdn, String value){
        if(!cdn) return this;
        return comment_le(value);
    }
    /**
    * 备注
    */
    public SqlProject comment_le(String value){
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
    public SqlProject comment_notNull_le(String value){
        return comment_le(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProject comment_ne(boolean cdn, String value){
        if(!cdn) return this;
        return comment_ne(value);
    }
    /**
    * 备注
    */
    public SqlProject comment_ne(String value){
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
    public SqlProject comment_notNull_ne(String value){
        return comment_ne(null != value, value);
    }
    /**
    * 备注
    */
    public SqlProject comment_notBlank_eq(String value){
        return comment_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProject comment_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return comment_leftLike(value);
    }
    /**
    * 备注
    */
    public SqlProject comment_leftLike(String value){
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
    public SqlProject comment_notBlank_leftLike(String value){
        return comment_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProject comment_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return comment_rightLike(value);
    }
    /**
    * 备注
    */
    public SqlProject comment_rightLike(String value){
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
    public SqlProject comment_notBlank_rightLike(String value){
        return comment_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProject comment_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return comment_middleLike(value);
    }
    /**
    * 备注
    */
    public SqlProject comment_middleLike(String value){
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
    public SqlProject comment_notBlank_middleLike(String value){
        return comment_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 备注
    */
    public SqlProject comment_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return comment_in(values);
    }
    /**
    * 备注
    */
    public SqlProject comment_in(Set<Object> values){
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
    public SqlProject comment_notEmpty_in(Set<Object> values){
        return comment_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 备注
    */
    public SqlProject orderBy_comment_asc(){
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
    public SqlProject orderBy_comment_desc(){
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
    public SqlProject deleteStatus_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_eq(value);
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_eq(Integer value){
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
    public SqlProject deleteStatus_notNull_eq(Integer value){
        return deleteStatus_eq(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_isdb_null(boolean cdn){
        if(!cdn) return this;
        return deleteStatus_isdb_null();
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_isdb_null(){
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
    public SqlProject deleteStatus_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return deleteStatus_isdb_not_null();
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_isdb_not_null(){
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
    public SqlProject deleteStatus_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_gt(value);
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_gt(Integer value){
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
    public SqlProject deleteStatus_notNull_gt(Integer value){
        return deleteStatus_gt(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_lt(value);
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_lt(Integer value){
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
    public SqlProject deleteStatus_notNull_lt(Integer value){
        return deleteStatus_lt(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_ge(value);
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_ge(Integer value){
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
    public SqlProject deleteStatus_notNull_ge(Integer value){
        return deleteStatus_ge(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_le(value);
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_le(Integer value){
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
    public SqlProject deleteStatus_notNull_le(Integer value){
        return deleteStatus_le(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_ne(value);
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_ne(Integer value){
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
    public SqlProject deleteStatus_notNull_ne(Integer value){
        return deleteStatus_ne(null != value, value);
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return deleteStatus_in(values);
    }
    /**
    * 删除状态
    */
    public SqlProject deleteStatus_in(Set<Object> values){
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
    public SqlProject deleteStatus_notEmpty_in(Set<Object> values){
        return deleteStatus_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 删除状态
    */
    public SqlProject orderBy_deleteStatus_asc(){
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
    public SqlProject orderBy_deleteStatus_desc(){
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
    public SqlProject createTime_eq(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_eq(value);
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_eq(Timestamp value){
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
    public SqlProject createTime_notNull_eq(Timestamp value){
        return createTime_eq(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_eq_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_eq_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_eq_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_eq(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_notNull_eq_yyyyMMdd(Timestamp value){
        return createTime_eq_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_isdb_null(boolean cdn){
        if(!cdn) return this;
        return createTime_isdb_null();
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_isdb_null(){
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
    public SqlProject createTime_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return createTime_isdb_not_null();
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_isdb_not_null(){
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
    public SqlProject createTime_gt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_gt(value);
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_gt(Timestamp value){
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
    public SqlProject createTime_notNull_gt(Timestamp value){
        return createTime_gt(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_gt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_gt_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_gt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_gt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_notNull_gt_yyyyMMdd(Timestamp value){
        return createTime_gt_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_lt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_lt(value);
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_lt(Timestamp value){
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
    public SqlProject createTime_notNull_lt(Timestamp value){
        return createTime_lt(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_lt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_lt_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_lt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_lt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_notNull_lt_yyyyMMdd(Timestamp value){
        return createTime_lt_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProject createTime_lt_nextDay_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_lt_nextDay_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProject createTime_lt_nextDay_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(DateUtil.offsetDay(value, 1)).toTimestamp();
        return createTime_lt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProject createTime_notNull_lt_nextDay_yyyyMMdd(Timestamp value){
        return createTime_lt_nextDay_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_ge(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ge(value);
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_ge(Timestamp value){
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
    public SqlProject createTime_notNull_ge(Timestamp value){
        return createTime_ge(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_ge_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ge_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_ge_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_ge(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_notNull_ge_yyyyMMdd(Timestamp value){
        return createTime_ge_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_le(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_le(value);
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_le(Timestamp value){
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
    public SqlProject createTime_notNull_le(Timestamp value){
        return createTime_le(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_le_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_le_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_le_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_le(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_notNull_le_yyyyMMdd(Timestamp value){
        return createTime_le_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_ne(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ne(value);
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_ne(Timestamp value){
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
    public SqlProject createTime_notNull_ne(Timestamp value){
        return createTime_ne(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_ne_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return createTime_ne_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_ne_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return createTime_ne(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject createTime_notNull_ne_yyyyMMdd(Timestamp value){
        return createTime_ne_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return createTime_in(values);
    }
    /**
    * 创建时间
    */
    public SqlProject createTime_in(Set<Object> values){
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
    public SqlProject createTime_notEmpty_in(Set<Object> values){
        return createTime_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 创建时间
    */
    public SqlProject orderBy_createTime_asc(){
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
    public SqlProject orderBy_createTime_desc(){
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
    public SqlProject updateTime_eq(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_eq(value);
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_eq(Timestamp value){
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
    public SqlProject updateTime_notNull_eq(Timestamp value){
        return updateTime_eq(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_eq_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_eq_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_eq_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_eq(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_notNull_eq_yyyyMMdd(Timestamp value){
        return updateTime_eq_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_isdb_null(boolean cdn){
        if(!cdn) return this;
        return updateTime_isdb_null();
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_isdb_null(){
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
    public SqlProject updateTime_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return updateTime_isdb_not_null();
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_isdb_not_null(){
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
    public SqlProject updateTime_gt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_gt(value);
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_gt(Timestamp value){
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
    public SqlProject updateTime_notNull_gt(Timestamp value){
        return updateTime_gt(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_gt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_gt_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_gt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_gt(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_notNull_gt_yyyyMMdd(Timestamp value){
        return updateTime_gt_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_lt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_lt(value);
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_lt(Timestamp value){
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
    public SqlProject updateTime_notNull_lt(Timestamp value){
        return updateTime_lt(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_lt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_lt_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_lt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_lt(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_notNull_lt_yyyyMMdd(Timestamp value){
        return updateTime_lt_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProject updateTime_lt_nextDay_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_lt_nextDay_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProject updateTime_lt_nextDay_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(DateUtil.offsetDay(value, 1)).toTimestamp();
        return updateTime_lt(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlProject updateTime_notNull_lt_nextDay_yyyyMMdd(Timestamp value){
        return updateTime_lt_nextDay_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_ge(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ge(value);
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_ge(Timestamp value){
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
    public SqlProject updateTime_notNull_ge(Timestamp value){
        return updateTime_ge(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_ge_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ge_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_ge_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_ge(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_notNull_ge_yyyyMMdd(Timestamp value){
        return updateTime_ge_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_le(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_le(value);
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_le(Timestamp value){
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
    public SqlProject updateTime_notNull_le(Timestamp value){
        return updateTime_le(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_le_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_le_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_le_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_le(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_notNull_le_yyyyMMdd(Timestamp value){
        return updateTime_le_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_ne(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ne(value);
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_ne(Timestamp value){
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
    public SqlProject updateTime_notNull_ne(Timestamp value){
        return updateTime_ne(null != value, value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_ne_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return updateTime_ne_yyyyMMdd(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_ne_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return updateTime_ne(value);
    }
    /**
    * 更新时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlProject updateTime_notNull_ne_yyyyMMdd(Timestamp value){
        return updateTime_ne_yyyyMMdd(null != value, value);
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return updateTime_in(values);
    }
    /**
    * 更新时间
    */
    public SqlProject updateTime_in(Set<Object> values){
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
    public SqlProject updateTime_notEmpty_in(Set<Object> values){
        return updateTime_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 更新时间
    */
    public SqlProject orderBy_updateTime_asc(){
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
    public SqlProject orderBy_updateTime_desc(){
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
        StringBuilder buf=new StringBuilder("SELECT * FROM project");
        buf.append(builder);
        buf.append(orderBuilder);
        return buf.toString();
    }
    @Override
    public String toSqlString(int start, int limit) {
        StringBuilder buf=new StringBuilder("SELECT * FROM project");
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
        StringBuilder buf=new StringBuilder("SELECT COUNT(1) FROM project");
        buf.append(builder);
        return buf.toString();
    }
    @Override
    public List<Object> getParameters() {
        return this.parameters;
    }
}
