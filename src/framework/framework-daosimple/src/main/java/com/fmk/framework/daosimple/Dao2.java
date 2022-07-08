package com.fmk.framework.daosimple;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.lang.ObjectId;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.daoannotations.Column;
import com.fmk.framework.daoannotations.Table;
import com.fmk.framework.daomodel.*;
import com.fmk.framework.entitiesbasic.IdEntity;
import com.fmk.framework.exception.ApplicationException;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.restful.PageResultList;
import com.fmk.framework.validation.Precondition;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//@Service
//@SummerService
public class Dao2 {
    private static Logger logger = Logger.getLogger(Dao2.class);
    private static ConcurrentHashMap<Class<?>, String> INSERT_SQL_CACHE = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Class<?>, String> UPDATE_SQL_CACHE = new ConcurrentHashMap<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void queryList(DaoList model){
        SqlSelect selec = (SqlSelect) model.getSqlSelect();

        String sql = null;
        if(null != model.getLimit()){
            int start = 0;
            if(null != model.getStart()){
                start = model.getStart();
            }
            sql = selec.toString(start, model.getLimit());
        }else {
            sql = selec.toString();
        }
        Object[] parameters = selec.parameters();

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, parameters);
        //BeanPropertyRowMapper

        ImmutableList<Field> fields = ReflectUtil.getAnnotationField(model.getKls(), Column.class);
        model.setSummerResult(convertMapList2ObjectList(mapList, model.getKls(), fields));
    }
    public void queryPageList(DaoPageList model){

        SqlSelect selec = (SqlSelect) model.getSqlSelect();
        int start = model.getStart();
        int limit = model.getLimit();

        PageResultList resultList = new PageResultList();

        Long count = jdbcTemplate.queryForObject(selec.toCountString(), selec.countParameters(), Long.class);
        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(selec.toString(start, limit), selec.parameters());

        ImmutableList<Field> fields = ReflectUtil.getAnnotationField(model.getKls(), Column.class);

        resultList.setTotal(count);
        resultList.setList(convertMapList2ObjectList(mapList, model.getKls(), fields));

        model.setSummerResult(resultList);
    }
    private List<Object> convertMapList2ObjectList(List<Map<String, Object>> mapList, Class<?> kls, ImmutableList<Field> fields){
        if(mapList.isEmpty()) { return new ArrayList<>(0); }
        ArrayList<Object> result = new ArrayList<>();
        for(int i=0; i<mapList.size(); i++){
            Map<String, Object> map = mapList.get(i);
            Object o = ReflectUtil.newInstance(kls);
            result.add(o);
            for(int j=0; j<fields.size(); j++){
                Field field = fields.get(j);
                String columnName = getColumnName(field);
                Object val = map.get(columnName);
                if(null != val) {
                    ReflectUtil.setFieldValue(o, field, val);
                }
            }
        }
        return result;
    }

    public void save(DaoSave model){
        IdEntity entity = (IdEntity) model.getEntity();

        ImmutableList<Field> fields = ReflectUtil.getAnnotationField(entity.getClass(), Column.class);
        String sql = null;
        Object[] values = null;
        if(null == entity.getId()) {
            entity.setId(ObjectId.next());
            sql = buildInsertSql(entity.getClass(), fields);
            values = getInsertValues(entity, fields);
        }else{
            sql = buildUpdateSql(entity.getClass(), fields);
            values = getUpdateValues(entity, fields);
        }
        jdbcTemplate.update(sql, values);
    }

    public void save(DaoImport model){
        Object entity = model.getEntity();

        ImmutableList<Field> fields = ReflectUtil.getAnnotationField(entity.getClass(), Column.class);
        String sql = null;
        Object[] values = null;
        String tableName = getTableName(entity.getClass());
        List<Map<String, Object>> list = jdbcTemplate.queryForList("select * from " + tableName + " where id=?",
                                                                   ReflectUtil.pathValue(entity, "id"));
        if(list.size()<=0) {
            sql = buildInsertSql(entity.getClass(), fields);
            values = getInsertValues(entity, fields);
        }else{
            sql = buildUpdateSql(entity.getClass(), fields);
            values = getUpdateValues(entity, fields);
        }
        jdbcTemplate.update(sql, values);
    }

    public void daoOne(DaoOne model){
        Object key = model.getKey();
        Class<?> kls = model.getKls();
        String tableName = getTableName(kls);
        String sql = "select * from "+tableName+" where id=?";

        ImmutableList<Field> fields = ReflectUtil.getAnnotationField(kls, Column.class);
        final Object[] o = {null};
        jdbcTemplate.query(sql, new Object[]{key}, rs -> {
            o[0] = ReflectUtil.newInstance(kls);
            for(Field field : fields){
                String columnName = getColumnName(field);
                Object value = rs.getObject(columnName);
                ReflectUtil.setFieldValue(o[0], field, value);
            }
        });
        Precondition.checkState(null!=o[0] || StringUtils.isBlank(model.getOnNullThrowErrMsg()), model.getOnNullThrowErrMsg());

        model.setSummerResult(o[0]);
    }
    public void increment(DaoIncrement model){
        IncrementUpdate incrementUpdate = model.getIncrementUpdate();
        String sql = buildIncrementSql(incrementUpdate, false);
        Object[] values = buildIncrementValues(incrementUpdate);

        jdbcTemplate.update(sql, values);
    }
    private String buildIncrementSql(IncrementUpdate update, boolean isDecrement){
        StringBuffer buffer = new StringBuffer();
        buffer.append("UPDATE ");
        buffer.append(update.getTableName());
        buffer.append(" SET ");
        boolean notFirst = false;
        List<IncrementUpdateValue> values = update.getValues();
        for(int i=0; i<values.size(); i++) {
            if(notFirst) { buffer.append(", "); }
            notFirst = true;
            IncrementUpdateValue value = values.get(i);
            buffer.append(value.getFieldName());
            buffer.append('=');
            buffer.append(value.getFieldName());
            buffer.append(isDecrement?"-":"+");
            buffer.append("?");
        }
        buffer.append(" WHERE id=?");
        if(!isDecrement && StringUtils.isNoneBlank(update.getExtCondation())){
            buffer.append(" AND ");
            buffer.append(update.getExtCondation());
        }
        return buffer.toString();
    }
    private Object[] buildIncrementValues(IncrementUpdate update){
        List<IncrementUpdateValue> values = update.getValues();
        Object[] vals = new Object[values.size()+1];
        for(int i=0; i<values.size(); i++) {
            vals[i] = values.get(i).getValue();
        }
        vals[values.size()] = update.getId();
        return vals;
    }

    public void daoRemove(DaoRemove model){
        IdEntity entity = (IdEntity) model.getEntity();

        String tableName = getTableName(entity.getClass());
        String sql = "delete from "+tableName+" where id=?";

        jdbcTemplate.update(sql, new Object[]{entity.getId()});
    }

    private String getTableName(Class<?> kls){
        Table table = kls.getAnnotation(Table.class);
        if(null == table) { throw new ApplicationException("entity class "+kls.getName()+" 需要指定Table注解"); }
        return table.value();
    }
    private String getColumnName(Field field){
        Column column = field.getAnnotation(Column.class);
        if(StringUtils.isNotBlank(column.value())){
            return column.value();
        }
        return field.getName();
    }

    private Object[] getInsertValues(Object entity, ImmutableList<Field> fields){
        Object[] values = new Object[fields.size()];
        for(int i=0; i<fields.size(); i++){
            Field field = fields.get(i);
//            values[i] = ReflectUtil.fieldValueNoThrow(field, entity);
            Object e = ReflectUtil.fieldValueNoThrow(field, entity);
            if(e instanceof Timestamp){
                values[i] = DateTime.of((Date) e).toString(DatePattern.NORM_DATETIME_MS_FORMAT);
            }else {
                values[i] = e;
            }
        }
        return values;
    }
    private Object[] getUpdateValues(Object entity, ImmutableList<Field> fields){
        List<Object> values = new ArrayList<>(fields.size());
        for(int i=0; i<fields.size(); i++){
            Field field = fields.get(i);
            String columnName = getColumnName(field);
            if("id".equals(columnName.toLowerCase())) { continue; }
            Object e = ReflectUtil.fieldValueNoThrow(field, entity);
            if(e instanceof Timestamp){
                values.add(DateTime.of((Date) e).toString(DatePattern.NORM_DATETIME_MS_FORMAT));
            }else {
                values.add(e);
            }
        }
        values.add(ReflectUtil.pathValue(entity, "id"));
        return values.toArray();
    }
    private String buildInsertSql(Class<?> kls, ImmutableList<Field> fields){
        String sql = INSERT_SQL_CACHE.get(kls);
        if(null != sql){
            return sql;
        }
        StringBuffer buffer = new StringBuffer();
        Table table = kls.getAnnotation(Table.class);
        if(null == table) { throw new ApplicationException("entity class "+kls.getName()+" 需要指定Table注解"); }

        buffer.append("INSERT INTO "+table.value()+"(");
        if(fields.isEmpty()){ throw new ApplicationException("entity class "+kls.getName()+" 需要有Column注解的字段"); }
        Field obj = fields.get(0);
        String columnName = getColumnName(obj);
        buffer.append(columnName);
        for(int i=1; i<fields.size(); i++){
            obj = fields.get(i);
            columnName = getColumnName(obj);
            buffer.append(",");
            buffer.append(columnName);
        }
        buffer.append(")VALUES(?");
        for(int i=1; i<fields.size(); i++){
            buffer.append(",?");
        }
        buffer.append(")");

        sql = buffer.toString();
        INSERT_SQL_CACHE.put(kls, sql);
        return sql;
    }
    private String buildUpdateSql(Class<?> kls, ImmutableList<Field> fields){
        String sql = UPDATE_SQL_CACHE.get(kls);
        if(null != sql){
            return sql;
        }
        StringBuffer buffer = new StringBuffer();
        Table table = kls.getAnnotation(Table.class);
        if(null == table) { throw new ApplicationException("entity class "+kls.getName()+" 需要指定Table注解"); }

        buffer.append("UPDATE "+table.value()+" SET ");
        if(fields.size()<=1){ throw new ApplicationException("entity class "+kls.getName()+" 需要有Column注解的字段"); }
        boolean first = true;
        for(int i=0; i<fields.size(); i++){
            Field obj = fields.get(i);
            String columnName = getColumnName(obj);
            if("id".equals(columnName.toLowerCase())) { continue; }

            buffer.append((first?"":",")+columnName+"=?");
            first = false;
        }
        buffer.append(" WHERE id=?");

        sql = buffer.toString();
        UPDATE_SQL_CACHE.put(kls, sql);
        return sql;
    }
}
