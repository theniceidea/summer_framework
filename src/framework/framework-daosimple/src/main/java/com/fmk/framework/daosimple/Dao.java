package com.fmk.framework.daosimple;

import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.daoannotations.Column;
import com.fmk.framework.daomodel.*;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.restful.PageResultList;
import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.summerframework.model.SummerService;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
@SummerService
public class Dao {
    private static Logger logger = Logger.getLogger(Dao.class);
    private static ConcurrentHashMap<Class<?>, String> INSERT_SQL_CACHE = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Class<?>, String> UPDATE_SQL_CACHE = new ConcurrentHashMap<>();

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void currentTransactionId(CurrentTransactionId model){
//        String value = TRANSACTION_ID.get();
//
//        boolean bolThrow = model.isNotFoundThrow() && StringUtils.isEmpty(value);
//        if(bolThrow){
//            throw new RuntimeException("缺少事务id, 请检查当前是否在微服务事务上下文中");
//        }

        model.setSummerResult("");
    }

    public void daoExecuteChangeSql(DaoExecuteChangeSql model){
        JdbcTemplate thatTemplate=(null != model.getTarget())?(JdbcTemplate) model.getTarget():jdbcTemplate;
        Object[] params = model.getParameters().toArray();
        if(logger.isDebugEnabled()) {
            logger.debug(model.getSql());
            StringBuilder build = buildParamsLogString(params);
            logger.debug(build.toString());
        }
        final int changeNum = thatTemplate.update(model.getSql(), params);
        if(logger.isDebugEnabled()) {
            logger.debug("改变行数: {}", changeNum);
        }
        model.setSummerResult(changeNum);
    }

    public void queryPageList(DaoPageList model){
        JdbcTemplate thatTemplate=(null != model.getTarget())?(JdbcTemplate) model.getTarget():jdbcTemplate;
        QuerySelect selec = (QuerySelect) model.getSqlSelect();
        int start = model.getStart();
        int limit = model.getLimit();

        PageResultList resultList = new PageResultList();

        Object[] params = selec.getParameters().toArray();
        final String countSqlString = selec.toCountSqlString();
        final String sqlString = selec.toSqlString(start, limit);
        if(logger.isDebugEnabled()) {
            logger.debug(countSqlString);
            logger.debug(sqlString);
            StringBuilder build = buildParamsLogString(params);
            logger.debug(build.toString());
        }
        Long count = thatTemplate.queryForObject(countSqlString, params, Long.class);
        List<Map<String, Object>> mapList = thatTemplate.queryForList(sqlString, params);

        ImmutableList<Field> fields = ReflectUtil.getAnnotationField(model.getKls(), Column.class);

        resultList.setTotal(count);
        resultList.setList(convertMapList2ObjectList(mapList, model.getKls(), fields));

        model.setSummerResult(resultList);
    }
    public void queryList(DaoList model){
        JdbcTemplate thatTemplate=(null != model.getTarget())?(JdbcTemplate) model.getTarget():jdbcTemplate;
        QuerySelect selec = (QuerySelect) model.getSqlSelect();

        String sql = null;
        if(null != model.getLimit()){
            int start = 0;
            if(null != model.getStart()){
                start = model.getStart();
            }
            sql = selec.toSqlString(start, model.getLimit());
        }else {
            sql = selec.toSqlString();
        }
        Object[] parameters = selec.getParameters().toArray();
        if(logger.isDebugEnabled()) {
            logger.debug(sql);
            StringBuilder build = buildParamsLogString(parameters);
            logger.debug(build.toString());
        }

        List<Map<String, Object>> mapList = thatTemplate.queryForList(sql, parameters);
        //BeanPropertyRowMapper

        ImmutableList<Field> fields = ReflectUtil.getAnnotationField(model.getKls(), Column.class);
        model.setSummerResult(convertMapList2ObjectList(mapList, model.getKls(), fields));
    }

    public void daoOne(DaoMap4Sql model){
        JdbcTemplate thatTemplate=(null != model.getTarget())?(JdbcTemplate) model.getTarget():jdbcTemplate;
        Object[] params = model.getValues().toArray();
        if(logger.isDebugEnabled()) {
            logger.debug(model.getSql());
            StringBuilder build = buildParamsLogString(params);
            logger.debug(build.toString());
        }
        final Map<String, Object> ret = thatTemplate.queryForMap(model.getSql(), params);
        model.setSummerResult(ret);
    }
    private String getColumnName(Field field){
        Column column = field.getAnnotation(Column.class);
        if(StringUtils.isNotBlank(column.value())){
            return column.value();
        }
        return field.getName();
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

    private StringBuilder buildParamsLogString(Object[] params) {
        StringBuilder build = new StringBuilder();
        build.append("参数: [");
        for(int i = 0; i< params.length; i++){
            if(i>0){
                build.append(", ");
            }
            final Object param = params[i];
            if(null == param){
                build.append("null");
            }else{
                build.append(param.getClass().getSimpleName());
                build.append(":");
                build.append(param);
            }
        }
        build.append("]");
        return build;
    }
}
