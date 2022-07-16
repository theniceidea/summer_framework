package com.testcomp.query0.bizdemo.summer_dev;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.fmk.framework.exception.Excep;
import com.testcomp.entities0.bizdemo.summer_dev.*;
import com.fmk.framework.daomodel.*;
import com.fmk.framework.restful.PageResultList;
import java.util.ArrayList;
import java.util.List;
import java.math.BigInteger;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import java.sql.Timestamp;
import java.math.BigDecimal;

/**
* demo table
*/
public class SqlDemotable implements QuerySelect {
    private static final String ID_SUFFIX="A0A0";
    private static Object DB_TARGET=null;
    private Object instDbTarget__ =null;
    private static final String SQL_INSERT="INSERT INTO demotable(title,type,num,num2,num3,demotable2_id,delete_status,enable_status,status,status2)values(?,?,?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE="UPDATE demotable SET title=?,type=?,demotable2_id=?,delete_status=?,enable_status=?,status=?,status2=? WHERE id=?";
    private static final String SQL_DELETE="DELETE demotable WHERE id=?";
    private boolean existsWhere=false;
    private boolean existsOrderBy=false;
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
        DB_TARGET=DaoJdbcTemplate.s("summer_dev");
        return DB_TARGET;
    }

    public SqlDemotable changeDbTarget(String name){
        instDbTarget__ =DaoJdbcTemplate.s(name);
        return this;
    }

    public static SqlDemotable inst(){
        final SqlDemotable sqlDemotable = new SqlDemotable();
        return sqlDemotable;
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
        StringBuilder buf=new StringBuilder("UPDATE demotable SET");
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
    public int save(Demotable entity){
        if(null==entity.getId()){
            return insert(entity);
        }else{
            return update(entity);
        }
    }
    private int insert(Demotable entity){
        List<Object> values = new ArrayList<>(10);
        values.add(entity.getTitle());
        values.add(entity.getType());
        values.add(entity.getNum());
        values.add(entity.getNum2());
        values.add(entity.getNum3());
        values.add(entity.getDemotable2Id());
        values.add(entity.getDeleteStatus());
        values.add(entity.getEnableStatus());
        values.add(entity.getStatus());
        values.add(entity.getStatus2());

        int retNum = DaoExecuteChangeSql.s(ds(), SQL_INSERT, values);
        Map<String, Object> map = DaoMap4Sql.s(ds(), "SELECT LAST_INSERT_ID() 'id'", new ArrayList<>(0), "");
        final Object id = map.get("id");
        entity.setId(((BigInteger)id).intValue());
        return retNum;
    }
    private int update(Demotable entity){
        List<Object> values = new ArrayList<>(8);
        values.add(entity.getTitle());
        values.add(entity.getType());
        values.add(entity.getDemotable2Id());
        values.add(entity.getDeleteStatus());
        values.add(entity.getEnableStatus());
        values.add(entity.getStatus());
        values.add(entity.getStatus2());
        values.add(entity.getId());

        return DaoExecuteChangeSql.s(ds(), SQL_UPDATE, values);
    }
    
    public int del(Demotable entity){
        List<Object> values = new ArrayList<>(1);
        values.add(entity.getId());
        return DaoExecuteChangeSql.s(ds(), SQL_DELETE, values);
    }
    public Demotable queryOne(){
        final List<Demotable> list = DaoList.s(ds(), Demotable.class, this, 0, 1);
        return list.size()>0?list.get(0):null;
    }
    public List<Demotable> queryList(){
        return DaoList.s(ds(), Demotable.class, this);
    }
    public List<Demotable> queryList(int start, int limit){
        return DaoList.s(ds(), Demotable.class, this, start, limit);
    }
    public PageResultList<Demotable> queryPageList(int start, int limit){
        return DaoPageList.s(ds(), Demotable.class, this, start, limit);
    }
    /**
    * id
    */
    public SqlDemotable id_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return id_eq(value);
    }
    /**
    * id
    */
    public SqlDemotable id_eq(Integer value){
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
    public SqlDemotable id_notNull_eq(Integer value){
        return id_eq(null != value, value);
    }
    /**
    * id
    */
    public SqlDemotable id_isdb_null(boolean cdn){
        if(!cdn) return this;
        return id_isdb_null();
    }
    /**
    * id
    */
    public SqlDemotable id_isdb_null(){
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
    public SqlDemotable id_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return id_isdb_not_null();
    }
    /**
    * id
    */
    public SqlDemotable id_isdb_not_null(){
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
    public SqlDemotable id_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return id_gt(value);
    }
    /**
    * id
    */
    public SqlDemotable id_gt(Integer value){
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
    public SqlDemotable id_notNull_gt(Integer value){
        return id_gt(null != value, value);
    }
    /**
    * id
    */
    public SqlDemotable id_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return id_lt(value);
    }
    /**
    * id
    */
    public SqlDemotable id_lt(Integer value){
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
    public SqlDemotable id_notNull_lt(Integer value){
        return id_lt(null != value, value);
    }
    /**
    * id
    */
    public SqlDemotable id_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return id_ge(value);
    }
    /**
    * id
    */
    public SqlDemotable id_ge(Integer value){
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
    public SqlDemotable id_notNull_ge(Integer value){
        return id_ge(null != value, value);
    }
    /**
    * id
    */
    public SqlDemotable id_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return id_le(value);
    }
    /**
    * id
    */
    public SqlDemotable id_le(Integer value){
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
    public SqlDemotable id_notNull_le(Integer value){
        return id_le(null != value, value);
    }
    /**
    * id
    */
    public SqlDemotable id_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return id_ne(value);
    }
    /**
    * id
    */
    public SqlDemotable id_ne(Integer value){
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
    public SqlDemotable id_notNull_ne(Integer value){
        return id_ne(null != value, value);
    }
    /**
    * id
    */
    public SqlDemotable id_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return id_in(values);
    }
    /**
    * id
    */
    public SqlDemotable id_in(Set<Object> values){
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
    public SqlDemotable id_notEmpty_in(Set<Object> values){
        return id_in(null != values && !values.isEmpty(), values);
    }


    /**
    * id
    */
    public SqlDemotable orderBy_id_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" id asc");
        return this;
    }
    /**
    * id
    */
    public SqlDemotable orderBy_id_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" id desc");
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable title_eq(boolean cdn, String value){
        if(!cdn) return this;
        return title_eq(value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" title=?");
        parameters.add(value);
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable title_notNull_eq(String value){
        return title_eq(null != value, value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_isdb_null(boolean cdn){
        if(!cdn) return this;
        return title_isdb_null();
    }
    /**
    * 标题
    */
    public SqlDemotable title_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" title is null");
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable title_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return title_isdb_not_null();
    }
    /**
    * 标题
    */
    public SqlDemotable title_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" title is not null");
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable title_gt(boolean cdn, String value){
        if(!cdn) return this;
        return title_gt(value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" title>?");
        parameters.add(value);
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable title_notNull_gt(String value){
        return title_gt(null != value, value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_lt(boolean cdn, String value){
        if(!cdn) return this;
        return title_lt(value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" title<?");
        parameters.add(value);
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable title_notNull_lt(String value){
        return title_lt(null != value, value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_ge(boolean cdn, String value){
        if(!cdn) return this;
        return title_ge(value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" title>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable title_notNull_ge(String value){
        return title_ge(null != value, value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_le(boolean cdn, String value){
        if(!cdn) return this;
        return title_le(value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" title<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable title_notNull_le(String value){
        return title_le(null != value, value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_ne(boolean cdn, String value){
        if(!cdn) return this;
        return title_ne(value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" title<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable title_notNull_ne(String value){
        return title_ne(null != value, value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_notBlank_eq(String value){
        return title_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return title_leftLike(value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" title like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable title_notBlank_leftLike(String value){
        return title_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return title_rightLike(value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" title like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable title_notBlank_rightLike(String value){
        return title_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return title_middleLike(value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" title like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable title_notBlank_middleLike(String value){
        return title_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 标题
    */
    public SqlDemotable title_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return title_in(values);
    }
    /**
    * 标题
    */
    public SqlDemotable title_in(Set<Object> values){
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
        builder.append(pre+" title in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable title_notEmpty_in(Set<Object> values){
        return title_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 标题
    */
    public SqlDemotable orderBy_title_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" title asc");
        return this;
    }
    /**
    * 标题
    */
    public SqlDemotable orderBy_title_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" title desc");
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable type_eq(boolean cdn, String value){
        if(!cdn) return this;
        return type_eq(value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" type=?");
        parameters.add(value);
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable type_notNull_eq(String value){
        return type_eq(null != value, value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_isdb_null(boolean cdn){
        if(!cdn) return this;
        return type_isdb_null();
    }
    /**
    * 类型
    */
    public SqlDemotable type_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" type is null");
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable type_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return type_isdb_not_null();
    }
    /**
    * 类型
    */
    public SqlDemotable type_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" type is not null");
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable type_gt(boolean cdn, String value){
        if(!cdn) return this;
        return type_gt(value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" type>?");
        parameters.add(value);
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable type_notNull_gt(String value){
        return type_gt(null != value, value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_lt(boolean cdn, String value){
        if(!cdn) return this;
        return type_lt(value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" type<?");
        parameters.add(value);
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable type_notNull_lt(String value){
        return type_lt(null != value, value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_ge(boolean cdn, String value){
        if(!cdn) return this;
        return type_ge(value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" type>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable type_notNull_ge(String value){
        return type_ge(null != value, value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_le(boolean cdn, String value){
        if(!cdn) return this;
        return type_le(value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" type<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable type_notNull_le(String value){
        return type_le(null != value, value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_ne(boolean cdn, String value){
        if(!cdn) return this;
        return type_ne(value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" type<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable type_notNull_ne(String value){
        return type_ne(null != value, value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_notBlank_eq(String value){
        return type_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return type_leftLike(value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" type like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable type_notBlank_leftLike(String value){
        return type_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return type_rightLike(value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" type like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable type_notBlank_rightLike(String value){
        return type_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return type_middleLike(value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" type like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable type_notBlank_middleLike(String value){
        return type_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * 类型
    */
    public SqlDemotable type_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return type_in(values);
    }
    /**
    * 类型
    */
    public SqlDemotable type_in(Set<Object> values){
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
        builder.append(pre+" type in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable type_notEmpty_in(Set<Object> values){
        return type_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 类型
    */
    public SqlDemotable orderBy_type_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" type asc");
        return this;
    }
    /**
    * 类型
    */
    public SqlDemotable orderBy_type_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" type desc");
        return this;
    }
    /**
    * 数量
    */
    public SqlDemotable num_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return num_eq(value);
    }
    /**
    * 数量
    */
    public SqlDemotable num_eq(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量
    */
    public SqlDemotable num_notNull_eq(Integer value){
        return num_eq(null != value, value);
    }
    /**
    * 数量
    */
    public SqlDemotable num_isdb_null(boolean cdn){
        if(!cdn) return this;
        return num_isdb_null();
    }
    /**
    * 数量
    */
    public SqlDemotable num_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num is null");
        return this;
    }
    /**
    * 数量
    */
    public SqlDemotable num_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return num_isdb_not_null();
    }
    /**
    * 数量
    */
    public SqlDemotable num_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num is not null");
        return this;
    }
    /**
    * 数量
    */
    public SqlDemotable num_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return num_gt(value);
    }
    /**
    * 数量
    */
    public SqlDemotable num_gt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量
    */
    public SqlDemotable num_notNull_gt(Integer value){
        return num_gt(null != value, value);
    }
    /**
    * 数量
    */
    public SqlDemotable num_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return num_lt(value);
    }
    /**
    * 数量
    */
    public SqlDemotable num_lt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num<?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量
    */
    public SqlDemotable num_notNull_lt(Integer value){
        return num_lt(null != value, value);
    }
    /**
    * 数量
    */
    public SqlDemotable num_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return num_ge(value);
    }
    /**
    * 数量
    */
    public SqlDemotable num_ge(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量
    */
    public SqlDemotable num_notNull_ge(Integer value){
        return num_ge(null != value, value);
    }
    /**
    * 数量
    */
    public SqlDemotable num_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return num_le(value);
    }
    /**
    * 数量
    */
    public SqlDemotable num_le(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量
    */
    public SqlDemotable num_notNull_le(Integer value){
        return num_le(null != value, value);
    }
    /**
    * 数量
    */
    public SqlDemotable num_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return num_ne(value);
    }
    /**
    * 数量
    */
    public SqlDemotable num_ne(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量
    */
    public SqlDemotable num_notNull_ne(Integer value){
        return num_ne(null != value, value);
    }
    /**
    * 数量
    */
    public SqlDemotable num_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return num_in(values);
    }
    /**
    * 数量
    */
    public SqlDemotable num_in(Set<Object> values){
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
        builder.append(pre+" num in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 数量
    */
    public SqlDemotable num_notEmpty_in(Set<Object> values){
        return num_in(null != values && !values.isEmpty(), values);
    }

    public SqlDemotable num_inc(Integer value){
        this.addIncValue("num", value);
        return this;
    }

    /**
    * 数量
    */
    public SqlDemotable orderBy_num_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" num asc");
        return this;
    }
    /**
    * 数量
    */
    public SqlDemotable orderBy_num_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" num desc");
        return this;
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_eq(boolean cdn, BigDecimal value){
        if(!cdn) return this;
        return num2_eq(value);
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_eq(BigDecimal value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num2=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_notNull_eq(BigDecimal value){
        return num2_eq(null != value, value);
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_isdb_null(boolean cdn){
        if(!cdn) return this;
        return num2_isdb_null();
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num2 is null");
        return this;
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return num2_isdb_not_null();
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num2 is not null");
        return this;
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_gt(boolean cdn, BigDecimal value){
        if(!cdn) return this;
        return num2_gt(value);
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_gt(BigDecimal value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num2>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_notNull_gt(BigDecimal value){
        return num2_gt(null != value, value);
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_lt(boolean cdn, BigDecimal value){
        if(!cdn) return this;
        return num2_lt(value);
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_lt(BigDecimal value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num2<?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_notNull_lt(BigDecimal value){
        return num2_lt(null != value, value);
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_ge(boolean cdn, BigDecimal value){
        if(!cdn) return this;
        return num2_ge(value);
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_ge(BigDecimal value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num2>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_notNull_ge(BigDecimal value){
        return num2_ge(null != value, value);
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_le(boolean cdn, BigDecimal value){
        if(!cdn) return this;
        return num2_le(value);
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_le(BigDecimal value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num2<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_notNull_le(BigDecimal value){
        return num2_le(null != value, value);
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_ne(boolean cdn, BigDecimal value){
        if(!cdn) return this;
        return num2_ne(value);
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_ne(BigDecimal value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num2<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_notNull_ne(BigDecimal value){
        return num2_ne(null != value, value);
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return num2_in(values);
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_in(Set<Object> values){
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
        builder.append(pre+" num2 in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 数量2
    */
    public SqlDemotable num2_notEmpty_in(Set<Object> values){
        return num2_in(null != values && !values.isEmpty(), values);
    }

    public SqlDemotable num2_inc(BigDecimal value){
        this.addIncValue("num2", value);
        return this;
    }

    /**
    * 数量2
    */
    public SqlDemotable orderBy_num2_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" num2 asc");
        return this;
    }
    /**
    * 数量2
    */
    public SqlDemotable orderBy_num2_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" num2 desc");
        return this;
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_eq(boolean cdn, Double value){
        if(!cdn) return this;
        return num3_eq(value);
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_eq(Double value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num3=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_notNull_eq(Double value){
        return num3_eq(null != value, value);
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_isdb_null(boolean cdn){
        if(!cdn) return this;
        return num3_isdb_null();
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num3 is null");
        return this;
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return num3_isdb_not_null();
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num3 is not null");
        return this;
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_gt(boolean cdn, Double value){
        if(!cdn) return this;
        return num3_gt(value);
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_gt(Double value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num3>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_notNull_gt(Double value){
        return num3_gt(null != value, value);
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_lt(boolean cdn, Double value){
        if(!cdn) return this;
        return num3_lt(value);
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_lt(Double value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num3<?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_notNull_lt(Double value){
        return num3_lt(null != value, value);
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_ge(boolean cdn, Double value){
        if(!cdn) return this;
        return num3_ge(value);
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_ge(Double value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num3>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_notNull_ge(Double value){
        return num3_ge(null != value, value);
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_le(boolean cdn, Double value){
        if(!cdn) return this;
        return num3_le(value);
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_le(Double value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num3<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_notNull_le(Double value){
        return num3_le(null != value, value);
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_ne(boolean cdn, Double value){
        if(!cdn) return this;
        return num3_ne(value);
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_ne(Double value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" num3<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_notNull_ne(Double value){
        return num3_ne(null != value, value);
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return num3_in(values);
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_in(Set<Object> values){
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
        builder.append(pre+" num3 in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 数量3
    */
    public SqlDemotable num3_notEmpty_in(Set<Object> values){
        return num3_in(null != values && !values.isEmpty(), values);
    }

    public SqlDemotable num3_inc(Double value){
        this.addIncValue("num3", value);
        return this;
    }

    /**
    * 数量3
    */
    public SqlDemotable orderBy_num3_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" num3 asc");
        return this;
    }
    /**
    * 数量3
    */
    public SqlDemotable orderBy_num3_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" num3 desc");
        return this;
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return demotable2Id_eq(value);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_eq(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" demotable2_id=?");
        parameters.add(value);
        return this;
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_notNull_eq(Integer value){
        return demotable2Id_eq(null != value, value);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_isdb_null(boolean cdn){
        if(!cdn) return this;
        return demotable2Id_isdb_null();
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" demotable2_id is null");
        return this;
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return demotable2Id_isdb_not_null();
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" demotable2_id is not null");
        return this;
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return demotable2Id_gt(value);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_gt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" demotable2_id>?");
        parameters.add(value);
        return this;
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_notNull_gt(Integer value){
        return demotable2Id_gt(null != value, value);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return demotable2Id_lt(value);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_lt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" demotable2_id<?");
        parameters.add(value);
        return this;
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_notNull_lt(Integer value){
        return demotable2Id_lt(null != value, value);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return demotable2Id_ge(value);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_ge(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" demotable2_id>=?");
        parameters.add(value);
        return this;
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_notNull_ge(Integer value){
        return demotable2Id_ge(null != value, value);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return demotable2Id_le(value);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_le(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" demotable2_id<=?");
        parameters.add(value);
        return this;
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_notNull_le(Integer value){
        return demotable2Id_le(null != value, value);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return demotable2Id_ne(value);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_ne(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" demotable2_id<>?");
        parameters.add(value);
        return this;
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_notNull_ne(Integer value){
        return demotable2Id_ne(null != value, value);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return demotable2Id_in(values);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_in(Set<Object> values){
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
        builder.append(pre+" demotable2_id in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_notEmpty_in(Set<Object> values){
        return demotable2Id_in(null != values && !values.isEmpty(), values);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_in(boolean cdn, List<Demotable2> entities){
        if(!cdn) return this;
        return demotable2Id_in(entities);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_in(List<Demotable2> entities){
        if(null==entities || entities.size()<=0){
            throw new RuntimeException("entities 必须拥有有效的元素");
        }
        final Set<Object> vals = entities.stream().filter(it -> null != it.getId()).map(Demotable2::getId).collect(Collectors.toSet());
        if(vals.isEmpty()){
            throw new RuntimeException("entities 必须拥有有效的id元素");
        }
        return demotable2Id_in(vals);
    }
    /**
    * demotable2
    */
    public SqlDemotable demotable2Id_notEmpty_in(List<Demotable2> entities){
        return demotable2Id_in(null != entities && !entities.isEmpty(), entities);
    }


    /**
    * demotable2
    */
    public SqlDemotable orderBy_demotable2Id_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" demotable2_id asc");
        return this;
    }
    /**
    * demotable2
    */
    public SqlDemotable orderBy_demotable2Id_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" demotable2_id desc");
        return this;
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_eq(value);
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_eq(Integer value){
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
    * delete status
    */
    public SqlDemotable deleteStatus_notNull_eq(Integer value){
        return deleteStatus_eq(null != value, value);
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_isdb_null(boolean cdn){
        if(!cdn) return this;
        return deleteStatus_isdb_null();
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_isdb_null(){
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
    * delete status
    */
    public SqlDemotable deleteStatus_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return deleteStatus_isdb_not_null();
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_isdb_not_null(){
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
    * delete status
    */
    public SqlDemotable deleteStatus_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_gt(value);
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_gt(Integer value){
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
    * delete status
    */
    public SqlDemotable deleteStatus_notNull_gt(Integer value){
        return deleteStatus_gt(null != value, value);
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_lt(value);
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_lt(Integer value){
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
    * delete status
    */
    public SqlDemotable deleteStatus_notNull_lt(Integer value){
        return deleteStatus_lt(null != value, value);
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_ge(value);
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_ge(Integer value){
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
    * delete status
    */
    public SqlDemotable deleteStatus_notNull_ge(Integer value){
        return deleteStatus_ge(null != value, value);
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_le(value);
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_le(Integer value){
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
    * delete status
    */
    public SqlDemotable deleteStatus_notNull_le(Integer value){
        return deleteStatus_le(null != value, value);
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return deleteStatus_ne(value);
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_ne(Integer value){
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
    * delete status
    */
    public SqlDemotable deleteStatus_notNull_ne(Integer value){
        return deleteStatus_ne(null != value, value);
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return deleteStatus_in(values);
    }
    /**
    * delete status
    */
    public SqlDemotable deleteStatus_in(Set<Object> values){
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
    * delete status
    */
    public SqlDemotable deleteStatus_notEmpty_in(Set<Object> values){
        return deleteStatus_in(null != values && !values.isEmpty(), values);
    }


    /**
    * delete status
    */
    public SqlDemotable orderBy_deleteStatus_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" delete_status asc");
        return this;
    }
    /**
    * delete status
    */
    public SqlDemotable orderBy_deleteStatus_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" delete_status desc");
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_eq(boolean cdn, String value){
        if(!cdn) return this;
        return enableStatus_eq(value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" enable_status=?");
        parameters.add(value);
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_notNull_eq(String value){
        return enableStatus_eq(null != value, value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_isdb_null(boolean cdn){
        if(!cdn) return this;
        return enableStatus_isdb_null();
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" enable_status is null");
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return enableStatus_isdb_not_null();
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" enable_status is not null");
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_gt(boolean cdn, String value){
        if(!cdn) return this;
        return enableStatus_gt(value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" enable_status>?");
        parameters.add(value);
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_notNull_gt(String value){
        return enableStatus_gt(null != value, value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_lt(boolean cdn, String value){
        if(!cdn) return this;
        return enableStatus_lt(value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" enable_status<?");
        parameters.add(value);
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_notNull_lt(String value){
        return enableStatus_lt(null != value, value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_ge(boolean cdn, String value){
        if(!cdn) return this;
        return enableStatus_ge(value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" enable_status>=?");
        parameters.add(value);
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_notNull_ge(String value){
        return enableStatus_ge(null != value, value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_le(boolean cdn, String value){
        if(!cdn) return this;
        return enableStatus_le(value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" enable_status<=?");
        parameters.add(value);
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_notNull_le(String value){
        return enableStatus_le(null != value, value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_ne(boolean cdn, String value){
        if(!cdn) return this;
        return enableStatus_ne(value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" enable_status<>?");
        parameters.add(value);
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_notNull_ne(String value){
        return enableStatus_ne(null != value, value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_notBlank_eq(String value){
        return enableStatus_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return enableStatus_leftLike(value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" enable_status like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_notBlank_leftLike(String value){
        return enableStatus_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return enableStatus_rightLike(value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" enable_status like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_notBlank_rightLike(String value){
        return enableStatus_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return enableStatus_middleLike(value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" enable_status like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_notBlank_middleLike(String value){
        return enableStatus_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return enableStatus_in(values);
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_in(Set<Object> values){
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
        builder.append(pre+" enable_status in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable enableStatus_notEmpty_in(Set<Object> values){
        return enableStatus_in(null != values && !values.isEmpty(), values);
    }


    /**
    * enable status
    */
    public SqlDemotable orderBy_enableStatus_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" enable_status asc");
        return this;
    }
    /**
    * enable status
    */
    public SqlDemotable orderBy_enableStatus_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" enable_status desc");
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status_eq(boolean cdn, Integer value){
        if(!cdn) return this;
        return status_eq(value);
    }
    /**
    * status
    */
    public SqlDemotable status_eq(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status=?");
        parameters.add(value);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status_notNull_eq(Integer value){
        return status_eq(null != value, value);
    }
    /**
    * status
    */
    public SqlDemotable status_isdb_null(boolean cdn){
        if(!cdn) return this;
        return status_isdb_null();
    }
    /**
    * status
    */
    public SqlDemotable status_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status is null");
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return status_isdb_not_null();
    }
    /**
    * status
    */
    public SqlDemotable status_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status is not null");
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status_gt(boolean cdn, Integer value){
        if(!cdn) return this;
        return status_gt(value);
    }
    /**
    * status
    */
    public SqlDemotable status_gt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status>?");
        parameters.add(value);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status_notNull_gt(Integer value){
        return status_gt(null != value, value);
    }
    /**
    * status
    */
    public SqlDemotable status_lt(boolean cdn, Integer value){
        if(!cdn) return this;
        return status_lt(value);
    }
    /**
    * status
    */
    public SqlDemotable status_lt(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status<?");
        parameters.add(value);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status_notNull_lt(Integer value){
        return status_lt(null != value, value);
    }
    /**
    * status
    */
    public SqlDemotable status_ge(boolean cdn, Integer value){
        if(!cdn) return this;
        return status_ge(value);
    }
    /**
    * status
    */
    public SqlDemotable status_ge(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status>=?");
        parameters.add(value);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status_notNull_ge(Integer value){
        return status_ge(null != value, value);
    }
    /**
    * status
    */
    public SqlDemotable status_le(boolean cdn, Integer value){
        if(!cdn) return this;
        return status_le(value);
    }
    /**
    * status
    */
    public SqlDemotable status_le(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status<=?");
        parameters.add(value);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status_notNull_le(Integer value){
        return status_le(null != value, value);
    }
    /**
    * status
    */
    public SqlDemotable status_ne(boolean cdn, Integer value){
        if(!cdn) return this;
        return status_ne(value);
    }
    /**
    * status
    */
    public SqlDemotable status_ne(Integer value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status<>?");
        parameters.add(value);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status_notNull_ne(Integer value){
        return status_ne(null != value, value);
    }
    /**
    * status
    */
    public SqlDemotable status_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return status_in(values);
    }
    /**
    * status
    */
    public SqlDemotable status_in(Set<Object> values){
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
        builder.append(pre+" status in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status_notEmpty_in(Set<Object> values){
        return status_in(null != values && !values.isEmpty(), values);
    }


    /**
    * status
    */
    public SqlDemotable orderBy_status_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" status asc");
        return this;
    }
    /**
    * status
    */
    public SqlDemotable orderBy_status_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" status desc");
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status2_eq(boolean cdn, String value){
        if(!cdn) return this;
        return status2_eq(value);
    }
    /**
    * status
    */
    public SqlDemotable status2_eq(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status2=?");
        parameters.add(value);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status2_notNull_eq(String value){
        return status2_eq(null != value, value);
    }
    /**
    * status
    */
    public SqlDemotable status2_isdb_null(boolean cdn){
        if(!cdn) return this;
        return status2_isdb_null();
    }
    /**
    * status
    */
    public SqlDemotable status2_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status2 is null");
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status2_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return status2_isdb_not_null();
    }
    /**
    * status
    */
    public SqlDemotable status2_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status2 is not null");
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status2_gt(boolean cdn, String value){
        if(!cdn) return this;
        return status2_gt(value);
    }
    /**
    * status
    */
    public SqlDemotable status2_gt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status2>?");
        parameters.add(value);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status2_notNull_gt(String value){
        return status2_gt(null != value, value);
    }
    /**
    * status
    */
    public SqlDemotable status2_lt(boolean cdn, String value){
        if(!cdn) return this;
        return status2_lt(value);
    }
    /**
    * status
    */
    public SqlDemotable status2_lt(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status2<?");
        parameters.add(value);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status2_notNull_lt(String value){
        return status2_lt(null != value, value);
    }
    /**
    * status
    */
    public SqlDemotable status2_ge(boolean cdn, String value){
        if(!cdn) return this;
        return status2_ge(value);
    }
    /**
    * status
    */
    public SqlDemotable status2_ge(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status2>=?");
        parameters.add(value);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status2_notNull_ge(String value){
        return status2_ge(null != value, value);
    }
    /**
    * status
    */
    public SqlDemotable status2_le(boolean cdn, String value){
        if(!cdn) return this;
        return status2_le(value);
    }
    /**
    * status
    */
    public SqlDemotable status2_le(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status2<=?");
        parameters.add(value);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status2_notNull_le(String value){
        return status2_le(null != value, value);
    }
    /**
    * status
    */
    public SqlDemotable status2_ne(boolean cdn, String value){
        if(!cdn) return this;
        return status2_ne(value);
    }
    /**
    * status
    */
    public SqlDemotable status2_ne(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status2<>?");
        parameters.add(value);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status2_notNull_ne(String value){
        return status2_ne(null != value, value);
    }
    /**
    * status
    */
    public SqlDemotable status2_notBlank_eq(String value){
        return status2_eq(StrUtil.isNotBlank(value), value);
    }
    /**
    * status
    */
    public SqlDemotable status2_leftLike(boolean cdn, String value){
        if(!cdn) return this;
        return status2_leftLike(value);
    }
    /**
    * status
    */
    public SqlDemotable status2_leftLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status2 like ?");
        parameters.add(value+"%");
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status2_notBlank_leftLike(String value){
        return status2_leftLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * status
    */
    public SqlDemotable status2_rightLike(boolean cdn, String value){
        if(!cdn) return this;
        return status2_rightLike(value);
    }
    /**
    * status
    */
    public SqlDemotable status2_rightLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status2 like ?");
        parameters.add("%"+value);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status2_notBlank_rightLike(String value){
        return status2_rightLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * status
    */
    public SqlDemotable status2_middleLike(boolean cdn, String value){
        if(!cdn) return this;
        return status2_middleLike(value);
    }
    /**
    * status
    */
    public SqlDemotable status2_middleLike(String value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" status2 like ?");
        parameters.add("%"+value+"%");
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status2_notBlank_middleLike(String value){
        return status2_middleLike(StrUtil.isNotBlank(value), value);
    }
    /**
    * status
    */
    public SqlDemotable status2_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return status2_in(values);
    }
    /**
    * status
    */
    public SqlDemotable status2_in(Set<Object> values){
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
        builder.append(pre+" status2 in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * status
    */
    public SqlDemotable status2_notEmpty_in(Set<Object> values){
        return status2_in(null != values && !values.isEmpty(), values);
    }


    /**
    * status
    */
    public SqlDemotable orderBy_status2_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" status2 asc");
        return this;
    }
    /**
    * status
    */
    public SqlDemotable orderBy_status2_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" status2 desc");
        return this;
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_eq(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return creationDate_eq(value);
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_eq(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" creation_date=?");
        parameters.add(value);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_notNull_eq(Timestamp value){
        return creationDate_eq(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_eq_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return creationDate_eq_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_eq_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return creationDate_eq(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_notNull_eq_yyyyMMdd(Timestamp value){
        return creationDate_eq_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_isdb_null(boolean cdn){
        if(!cdn) return this;
        return creationDate_isdb_null();
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_isdb_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" creation_date is null");
        return this;
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_isdb_not_null(boolean cdn){
        if(!cdn) return this;
        return creationDate_isdb_not_null();
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_isdb_not_null(){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" creation_date is not null");
        return this;
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_gt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return creationDate_gt(value);
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_gt(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" creation_date>?");
        parameters.add(value);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_notNull_gt(Timestamp value){
        return creationDate_gt(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_gt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return creationDate_gt_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_gt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return creationDate_gt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_notNull_gt_yyyyMMdd(Timestamp value){
        return creationDate_gt_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_lt(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return creationDate_lt(value);
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_lt(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" creation_date<?");
        parameters.add(value);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_notNull_lt(Timestamp value){
        return creationDate_lt(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_lt_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return creationDate_lt_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_lt_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return creationDate_lt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_notNull_lt_yyyyMMdd(Timestamp value){
        return creationDate_lt_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlDemotable creationDate_lt_nextDay_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return creationDate_lt_nextDay_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlDemotable creationDate_lt_nextDay_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(DateUtil.offsetDay(value, 1)).toTimestamp();
        return creationDate_lt(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日之后加一天,再查询
    */
    public SqlDemotable creationDate_notNull_lt_nextDay_yyyyMMdd(Timestamp value){
        return creationDate_lt_nextDay_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_ge(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return creationDate_ge(value);
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_ge(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" creation_date>=?");
        parameters.add(value);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_notNull_ge(Timestamp value){
        return creationDate_ge(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_ge_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return creationDate_ge_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_ge_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return creationDate_ge(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_notNull_ge_yyyyMMdd(Timestamp value){
        return creationDate_ge_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_le(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return creationDate_le(value);
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_le(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" creation_date<=?");
        parameters.add(value);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_notNull_le(Timestamp value){
        return creationDate_le(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_le_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return creationDate_le_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_le_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return creationDate_le(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_notNull_le_yyyyMMdd(Timestamp value){
        return creationDate_le_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_ne(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return creationDate_ne(value);
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_ne(Timestamp value){
        String pre="";
        if(existsWhere){
            pre=" AND";
        }else{
            pre=" WHERE";
            existsWhere=true;
        }
        builder.append(pre+" creation_date<>?");
        parameters.add(value);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_notNull_ne(Timestamp value){
        return creationDate_ne(null != value, value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_ne_yyyyMMdd(boolean cdn, Timestamp value){
        if(!cdn) return this;
        return creationDate_ne_yyyyMMdd(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_ne_yyyyMMdd(Timestamp value){
        value=DateUtil.beginOfDay(value).toTimestamp();
        return creationDate_ne(value);
    }
    /**
    * 创建时间 把value时间小时及之后的部分舍弃只留年月日,再查询
    */
    public SqlDemotable creationDate_notNull_ne_yyyyMMdd(Timestamp value){
        return creationDate_ne_yyyyMMdd(null != value, value);
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_in(boolean cdn, Set<Object> values){
        if(!cdn) return this;
        return creationDate_in(values);
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_in(Set<Object> values){
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
        builder.append(pre+" creation_date in ("+txt+")");
        parameters.addAll(values);
        return this;
    }
    /**
    * 创建时间
    */
    public SqlDemotable creationDate_notEmpty_in(Set<Object> values){
        return creationDate_in(null != values && !values.isEmpty(), values);
    }


    /**
    * 创建时间
    */
    public SqlDemotable orderBy_creationDate_asc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" creation_date asc");
        return this;
    }
    /**
    * 创建时间
    */
    public SqlDemotable orderBy_creationDate_desc(){
        String pre="";
        if(existsOrderBy){
            pre=",";
        }else{
            pre=" order by";
            existsOrderBy=true;
        }
        builder.append(pre+" creation_date desc");
        return this;
    }
    @Override
    public String toSqlString() {
        StringBuilder buf=new StringBuilder("SELECT * FROM demotable");
        buf.append(builder);
        return buf.toString();
    }
    @Override
    public String toSqlString(int start, int limit) {
        StringBuilder buf=new StringBuilder("SELECT * FROM demotable");
        buf.append(builder);
        buf.append(" limit ");
        buf.append(start);
        buf.append(" ,");
        buf.append(limit);
        return buf.toString();
    }
    @Override
    public String toCountSqlString() {
        StringBuilder buf=new StringBuilder("SELECT COUNT(1) FROM demotable");
        buf.append(builder);
        return buf.toString();
    }
    @Override
    public List<Object> getParameters() {
        return this.parameters;
    }
}
