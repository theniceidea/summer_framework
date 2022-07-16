package com.fmk.framework.daosimple;

import com.google.common.collect.ImmutableList;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Dao {
//    private static Logger logger = Logger.getLogger(Dao.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void daoExecuteChangeSql(DaoExecuteChangeSql model){
        JdbcTemplate thatTemplate=(null != model.getTarget())?(JdbcTemplate) model.getTarget():jdbcTemplate;
        final int changeNum = thatTemplate.update(model.getSql(), model.getParameters().toArray());
        model.setSummerResult(changeNum);
    }
    public void queryPageList(DaoPageList model){
        JdbcTemplate thatTemplate=(null != model.getTarget())?(JdbcTemplate) model.getTarget():jdbcTemplate;
        QuerySelect selec = (QuerySelect) model.getSqlSelect();
        int start = model.getStart();
        int limit = model.getLimit();

        PageResultList resultList = new PageResultList();

        Long count = thatTemplate.queryForObject(selec.toCountSqlString(), selec.getParameters().toArray(), Long.class);
        List<Map<String, Object>> mapList = thatTemplate.queryForList(selec.toSqlString(start, limit), selec.getParameters().toArray());

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

        List<Map<String, Object>> mapList = thatTemplate.queryForList(sql, parameters);
        //BeanPropertyRowMapper

        ImmutableList<Field> fields = ReflectUtil.getAnnotationField(model.getKls(), Column.class);
        model.setSummerResult(convertMapList2ObjectList(mapList, model.getKls(), fields));
    }

    public void daoOne(DaoMap4Sql model){
        JdbcTemplate thatTemplate=(null != model.getTarget())?(JdbcTemplate) model.getTarget():jdbcTemplate;
        final Map<String, Object> ret = thatTemplate.queryForMap(model.getSql(), model.getValues().toArray());
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
}
