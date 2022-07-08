package com.fmk.framework.dao;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.lang.ObjectId;
import com.alibaba.fastjson.JSON;
import com.fmk.framework.basic.DateTimeUtil;
import com.fmk.framework.basic.ReflectUtil;
import com.fmk.framework.basic.ThreadLocals;
import com.fmk.framework.consts.Consts;
import com.fmk.framework.daoannotations.Column;
import com.fmk.framework.daoannotations.Table;
import com.fmk.framework.daomodel.*;
import com.fmk.framework.entitiesbasic.IdEntity;
import com.fmk.framework.entitiesbasic.ResIncrement;
import com.fmk.framework.exception.ApplicationException;
import com.fmk.framework.exception.ErrMsgs;
import com.fmk.framework.logger.Logger;
import com.fmk.framework.restful.PageResultList;
import com.fmk.framework.validation.Precondition;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisStringCommands;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.types.Expiration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.summerframework.model.SummerService;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.fmk.framework.exception.ErrMsgs.Err_000000000014;

@Service
@SummerService
public class Dao {
    private static Logger logger = Logger.getLogger(Dao.class);
    //Logger.getLogger("sysDaoLog");
    private static Logger daoLogger = Logger.getLogger(Dao.class);
    private static ConcurrentHashMap<Class<?>, String> INSERT_SQL_CACHE = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<Class<?>, String> UPDATE_SQL_CACHE = new ConcurrentHashMap<>();
    private static final int TRANS_TIMEOUT_TIME = 10;
    private static final ThreadLocal<String> TRANSACTION_ID = ThreadLocals.newThreadLocal();
    private static final ThreadLocal<String> SIMPLE_TRANSACTION_ID = ThreadLocals.newThreadLocal();
    private static final ThreadLocal<Integer> SQL_LOG_NUM = ThreadLocals.newThreadLocal();

    @Autowired
    private Dao dao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    public void transId(String transId){
        String transactionId = TRANSACTION_ID.get();
        if(StringUtils.isNotBlank(transactionId)){
            throw new RuntimeException("当前已经运行在事务上下文了, 不能改变为另一个事务上下文");
        }
        TRANSACTION_ID.set(transId);
    }
    public String transId(){
        return TRANSACTION_ID.get();
    }
    public String newTransaction(){
        String transactionId = TRANSACTION_ID.get();
        if(StringUtils.isNotBlank(transactionId)){
            throw new RuntimeException("当前已经运行在微服务事务上下文了, 不能再新开一个微服务事务");
        }
        String simpTransactionId = SIMPLE_TRANSACTION_ID.get();
        if(StringUtils.isNotBlank(simpTransactionId)){
            throw new RuntimeException("当前已经运行在Simple事务上下文了, 不能再新开一个微服务事务");
        }
        transactionId = ObjectId.next();
        TRANSACTION_ID.set(transactionId);
        daoLogger.info("newTransId: transId: {} ", transactionId);
        return transactionId;
    }
    public void currentTransactionId(CurrentTransactionId model){
        String value = TRANSACTION_ID.get();

        boolean bolThrow = model.isNotFoundThrow() && StringUtils.isEmpty(value);
        if(bolThrow){
            throw new RuntimeException("缺少事务id, 请检查当前是否在微服务事务上下文中");
        }

        model.setSummerResult(value);
    }
    private int nextSqlLogNum(){
        Integer num = SQL_LOG_NUM.get();
        if(null == num){
            num = 0;
        }else{
            num = num+1;
        }
        SQL_LOG_NUM.set(num);
        return num;
    }
    public String newSimpleTransactionId(){
        String msTransactionId = TRANSACTION_ID.get();
        if(StringUtils.isNotBlank(msTransactionId)){
            throw new RuntimeException("当前已经运行在微服务事务上下文了, 不能再新开一个Simple事务");
        }
        String simpTransactionId = SIMPLE_TRANSACTION_ID.get();
        if(StringUtils.isNotBlank(simpTransactionId)){
            throw new RuntimeException("当前已经运行在Simple事务上下文了, 不能再新开一个Simple事务");
        }
        simpTransactionId = ObjectId.next();
        SIMPLE_TRANSACTION_ID.set(simpTransactionId);
        return simpTransactionId;
    }
    public void removeSimpleTransactionId(){
        SIMPLE_TRANSACTION_ID.remove();
    }
    private String simpleTransId(boolean notFoundThrow){
        String simpTransactionId = SIMPLE_TRANSACTION_ID.get();
        if(notFoundThrow && StringUtils.isBlank(simpTransactionId)){
            throw new RuntimeException("没有 Simple 事务 id");
        }
        return simpTransactionId;
    }
    private boolean isSimpleTransactionContext(){
        return StringUtils.isNotBlank(SIMPLE_TRANSACTION_ID.get());
    }

    public void singleValue(DaoSingleValue model){
        SqlSelect selec = (SqlSelect) model.getSqlSelect();

        String sql = selec.toString();

        Object[] parameters = selec.parameters();

        Object value = jdbcTemplate.queryForObject(sql, parameters, Object.class);
        model.setSummerResult(value);
    }

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
                Column column = field.getAnnotation(Column.class);
                Object val = map.get(column.value());
                if(null != val) {
                    ReflectUtil.setFieldValue(o, field, val);
                }
            }
        }
        return result;
    }

    public void rollback(DaoRollbackCurrentTransactionRes model){
        String tranId = CurrentTransactionId.s(true);
        try{
            rollback_(model);
        }finally {
            cleanRedis(tranId);
        }
    }
    private void rollback_(DaoRollbackCurrentTransactionRes model){
        if(!hasUpdate()) { return; }
        String tranId = CurrentTransactionId.s(true);

        if(!hasTransId(tranId)) { return; }

        SqlSelect sqlSelect = SqlSelect.selec("*")
            .fromClassName(ResIncrement.class)
            .eq(ResIncrement._transactionId, tranId)
            .eq(ResIncrement._status, 0);
        List<ResIncrement> list = DaoList.s(ResIncrement.class, sqlSelect);
        List<SqlUpdate> sqlUpdateList = new ArrayList<>();

        for(int i=0; i<list.size(); i++){
            ResIncrement resIncrement = list.get(i);
            String content = resIncrement.getContent();
            IncrementUpdate incrementUpdate = JSON.parseObject(content, IncrementUpdate.class);
            String sql = buildIncrementSql(incrementUpdate, true);
            Object[] values = buildIncrementValues(incrementUpdate);

            SqlUpdate sqlUpdate = new SqlUpdate();
            sqlUpdate.setSql(sql);
            sqlUpdate.setValues(values);

            sqlUpdateList.add(sqlUpdate);
        }

        SqlUpdate incSqlUpdate = new SqlUpdate();
        String tableName = getTableName(ResIncrement.class);
        incSqlUpdate.setSql("DELETE FROM "+tableName+" WHERE "+ResIncrement._transactionId+"=?");
        incSqlUpdate.setValues(new Object[]{tranId});
        sqlUpdateList.add(incSqlUpdate);

        dao.commitSql(sqlUpdateList);
        daoLogger.info("transId: {} {} rollback", tranId, nextSqlLogNum());
    }
    public void needRollbackTranIds(DaoListNeedRollbackTranIds model){

        long millis = DateTime.now()
            .plusMinutes(-30)
            .getMillis();
        Timestamp timestamp = DateTimeUtil.timestamp(millis);

        SqlSelect sqlSelect = new SqlSelect();
            sqlSelect.select_distinct(ResIncrement._transactionId)
            .fromClassName(ResIncrement.class)
            .lt(ResIncrement._createTime, timestamp)
            .eq(ResIncrement._status, "0");

        List<ResIncrement> list = DaoList.s(ResIncrement.class, sqlSelect, 0, model.getLimit());
        List<String> collect = list.stream()
            .map(ResIncrement::getTransactionId)
            .collect(Collectors.toList());

        model.setSummerResult(collect);
    }
    public void daoRollbackByTranId(DaoRollbackByTranId model){
        String tranId = model.getTranId();

        SqlSelect sqlSelect = SqlSelect.selec("*")
            .fromClassName(ResIncrement.class)
            .eq(ResIncrement._transactionId, tranId)
            .eq(ResIncrement._status, 0);
        List<ResIncrement> list = DaoList.s(ResIncrement.class, sqlSelect);
        List<SqlUpdate> sqlUpdateList = new ArrayList<>();

        for(int i=0; i<list.size(); i++){
            ResIncrement resIncrement = list.get(i);
            String content = resIncrement.getContent();
            IncrementUpdate incrementUpdate = JSON.parseObject(content, IncrementUpdate.class);
            String sql = buildIncrementSql(incrementUpdate, true);
            Object[] values = buildIncrementValues(incrementUpdate);

            SqlUpdate sqlUpdate = new SqlUpdate();
            sqlUpdate.setSql(sql);
            sqlUpdate.setValues(values);

            sqlUpdateList.add(sqlUpdate);
        }

        SqlUpdate incSqlUpdate = new SqlUpdate();
        String tableName = getTableName(ResIncrement.class);
        incSqlUpdate.setSql("DELETE FROM "+tableName+" WHERE "+ResIncrement._transactionId+"=?");
        incSqlUpdate.setValues(new Object[]{tranId});
        sqlUpdateList.add(incSqlUpdate);

        dao.commitSql(sqlUpdateList);
        daoLogger.info("transId: {} {} rollback2", tranId, nextSqlLogNum());
    }

    public void commit(DaoCommitCurrentTransaction model){
        String tranId = CurrentTransactionId.s(true);
        try{
            commit_(model);
        }finally {
            cleanRedis(tranId);
        }
    }
    private void commit_(DaoCommitCurrentTransaction model){
        if(!hasUpdate()) { return; }

        String tranId = CurrentTransactionId.s(true);

        if(!hasTransId(tranId)) { return; }

        List<String> list = getTransValue(tranId);
        List<SqlUpdate> sqlUpdateList = new ArrayList<>();
        for(int i=0; i<list.size(); i++){
            String text = list.get(i);
            Update4Json update4Json = JSON.parseObject(text, Update4Json.class);
            if(null != update4Json.getUpdate()){
                Update update = update4Json.getUpdate();
                SqlUpdate sqlUpdate = new SqlUpdate();
                sqlUpdate.setSql(update.getSql());
                sqlUpdate.setValues(buildValues(update.getValues()));
                sqlUpdateList.add(sqlUpdate);
            }
        }
        if(sqlUpdateList.size()<=0) { return; }
        SqlUpdate incSqlUpdate = new SqlUpdate();
        String tableName = getTableName(ResIncrement.class);
        incSqlUpdate.setSql("DELETE FROM "+tableName+" WHERE "+ResIncrement._transactionId+"=?");
        incSqlUpdate.setValues(new Object[]{tranId});
        sqlUpdateList.add(incSqlUpdate);

        dao.commitSql(sqlUpdateList);
        daoLogger.info("transId: {} {} commit", tranId, nextSqlLogNum());

    }
    private void setUpdateTag(){
        String tranId = CurrentTransactionId.s(true);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        String key = Consts.REDIS_TRANSACTION_ID+tranId+":haveUpdate";

        ops.set(key, "1", TRANS_TIMEOUT_TIME, TimeUnit.MINUTES);
    }
    private boolean hasUpdate(){
        String tranId = CurrentTransactionId.s(true);

        String key = Consts.REDIS_TRANSACTION_ID+tranId+":haveUpdate";

        return redisTemplate.hasKey(key);
    }

    @Transactional(rollbackFor = Exception.class)
    public void commitSql(List<SqlUpdate> sqlUpdates){
        String tranId = CurrentTransactionId.s(true);
        for(int i=0; i<sqlUpdates.size(); i++){
            SqlUpdate sqlUpdate = sqlUpdates.get(i);
            daoLogger.info("transId: {} {} update_sql: {}", tranId, nextSqlLogNum(), JSON.toJSONString(sqlUpdate));
            jdbcTemplate.update(sqlUpdate.getSql(), sqlUpdate.getValues());
        }
    }
    private static Object[] buildValues(List<UpdateValue> updateValues){
        if(CollectionUtils.isEmpty(updateValues)) { return new Object[0]; }

        Object[] values = new Object[updateValues.size()];
        for(int i=0; i<updateValues.size(); i++){
            UpdateValue updateValue = updateValues.get(i);
            String type = updateValue.getType();
            Object value = updateValue.getValue();
            if(StringUtils.isBlank(type)) {
                values[i] = null;
            }else if("Boolean".equals(type)){
                values[i] = value;
            } else if("Byte".equals(type)) {
                values[i] = Convert.convert(Byte.class, value);
            } else if("Short".equals(type)) {
                values[i] = Convert.convert(Short.class, value);
            } else if("Integer".equals(type)){
                values[i] = Convert.convert(Integer.class, value);
            } else if("Long".equals(type)){
                values[i] = Convert.convert(Long.class, value);
            } else if("Float".equals(type)){
                values[i] = Convert.convert(Float.class, value);
            } else if("Double".equals(type)){
                values[i] = Convert.convert(Double.class, value);
            } else if("BigDecimal".equals(type)){
                values[i] = Convert.convert(BigDecimal.class, value);
            } else if("Timestamp".equals(type)){
                values[i] = Convert.convert(Timestamp.class, value);
            } else if("SqlDate".equals(type)){
                values[i] = Convert.convert(java.sql.Date.class, value);
            } else if("Date".equals(type)){
                values[i] = Convert.convert(Date.class, value);
            } else if("String".equals(type)){
//                values[i] = Convert.convert(String.class, value);
                values[i] = value;
            } else {
                throw new ApplicationException("convert err from:"+value.getClass().getName()+" to "+type+"; value:"+value);
            }
            updateValue.setValue(values[i]);
        }
        return values;
    }

    public void increment(DaoIncrement model){
        if(isSimpleTransactionContext()){
            incrementSimple(model);
            return;
        }

        String transId = CurrentTransactionId.s(true);
        daoLogger.info("transId: {} {} incObject: {}", transId, nextSqlLogNum(), JSON.toJSONString(model));
        setUpdateTag();
        IncrementUpdate incrementUpdate = model.getIncrementUpdate();
        String sql = buildIncrementSql(incrementUpdate, false);
        Object[] values = buildIncrementValues(incrementUpdate);

        ResIncrement entity = new ResIncrement();
        entity.setId(ObjectId.next());
        entity.setTransactionId(transId);
        entity.setCreateTime(new Timestamp(DateTime.now().getMillis()));
        entity.setStatus(0);
        entity.setContent(JSON.toJSONString(incrementUpdate));

        ImmutableList<Field> fields = ReflectUtil.getAnnotationField(entity.getClass(), Column.class);
        String sqlEntity = buildInsertSql(entity.getClass(), fields);
        Object[] valuesEntity = getInsertValues(entity, fields);

        setRedis(transId, "", true);
        int updateCount = dao.executeIncrement(entity.getId(), incrementUpdate, sql, values, sqlEntity, valuesEntity);
        daoLogger.info("transId: {} {} increment_over: {} {} {}", transId, nextSqlLogNum(), updateCount, entity.getId(), incrementUpdate.getId());

        model.setSummerResult(updateCount);
    }
    private void incrementSimple(DaoIncrement model){
        IncrementUpdate incrementUpdate = model.getIncrementUpdate();
        String sql = buildIncrementSql(incrementUpdate, false);
        Object[] values = buildIncrementValues(incrementUpdate);

        String transId = simpleTransId(true);
        IncLog incLog = new IncLog();
        incLog.setSql(sql);
        incLog.setValues(values);
        daoLogger.info("simptransId: {} {} increment_sql: {}", transId, nextSqlLogNum(), JSON.toJSONString(incLog));
        int updateCount = jdbcTemplate.update(sql, values);
        daoLogger.info("simptransId: {} {} increment_over: {} {}", transId, nextSqlLogNum(), updateCount, incrementUpdate.getId());
        model.setSummerResult(updateCount);
    }
    private void cleanRedis(String transId){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String key = Consts.REDIS_TRANSACTION_ID+transId;
        String key2 = Consts.REDIS_TRANSACTION_ID+transId+":haveUpdate";
//        if(!redisTemplate.hasKey(key)) { return; }

        ArrayList<String> list = Lists.newArrayList();
        list.add(key);
        list.add(key2);
        String idstr = ops.get(key);
        if(StringUtils.isNotBlank(idstr)) {
            List<String> ids = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToList(idstr);
            list.addAll(ids);
        }

        String keysId = Consts.SERVICE_TRANSACTION_ENTITY_KEYS + transId;
        list.add(keysId);
        idstr = ops.get(keysId);
        if(StringUtils.isNotBlank(idstr)){
            List<String> ids = Splitter.on(',')
                .trimResults()
                .omitEmptyStrings()
                .splitToList(idstr)
                .stream()
                .map(s->Consts.SERVICE_TRANSACTION_ENTITY+s)
                .collect(Collectors.toList());
            list.addAll(ids);
        }

        redisTemplate.delete(list);
        String join = Joiner.on(",")
            .join(list);
        daoLogger.info("clean redis: transId: {} keys: {} ", transId, join);
    }
    private List<String> getTransValue(String transId){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String key = Consts.REDIS_TRANSACTION_ID+transId;
        String idstr = ops.get(key);
        List<String> ids = Splitter.on(',')
            .trimResults()
            .omitEmptyStrings()
            .splitToList(idstr);
        if(CollectionUtils.isEmpty(ids)){
            return ids;
        }
        List<String> values = ops.multiGet(ids);
        if(values.size() != ids.size()){
            throw new ApplicationException("事务缓存记录不完整");
        }
        return values;
    }
    private boolean hasTransId(String transId){
        String key = Consts.REDIS_TRANSACTION_ID+transId;
        return redisTemplate.hasKey(key);
    }
    private void setRedis(String transId, String value, boolean onlyKey){
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        String key = Consts.REDIS_TRANSACTION_ID+transId;
        if(onlyKey){
            if(!redisTemplate.hasKey(key)) {
                ops.set(key, "", TRANS_TIMEOUT_TIME, TimeUnit.MINUTES);
            }
            return;
        }
        String valueKey = Consts.REDIS_TRANSACTION_ITEM + ObjectId.next();
        if(!redisTemplate.hasKey(key)){
            ops.set(key, valueKey, TRANS_TIMEOUT_TIME, TimeUnit.MINUTES);
        }else{
            ops.append(key, ","+valueKey);
        }
        ops.set(valueKey, value, TRANS_TIMEOUT_TIME, TimeUnit.MINUTES);
    }
    private void putTransactionEntity(TransactionEntity entity){
        String entityId = entity.getEntityId();
        String key = Consts.SERVICE_TRANSACTION_ENTITY+ entityId;
        ValueOperations<String, String> ops = redisTemplate.opsForValue();

        String text = JSON.toJSONString(entity);
        String keysId = Consts.SERVICE_TRANSACTION_ENTITY_KEYS + entity.getTransactionId();

        String keys = ops.get(keysId);
        if(null != keys && keys.contains(entityId)){
            ops.set(key, text, TRANS_TIMEOUT_TIME, TimeUnit.MINUTES);
            return;
        }

        Boolean bol = redisTemplate.execute((RedisCallback<Boolean>) conn -> {
            Expiration from = Expiration.from(TRANS_TIMEOUT_TIME, TimeUnit.MINUTES);
            RedisStringCommands.SetOption setIfAbsent = RedisStringCommands.SetOption.SET_IF_ABSENT;
            return conn.set(key.getBytes(), text.getBytes(), from, setIfAbsent);
        });
        if(!bol){
            String txt = ops.get(key);
            String oldTid = "none";
            if(StringUtils.isNotBlank(txt)){
                TransactionEntity oldEntity = JSON.parseObject(txt, TransactionEntity.class);
                oldTid = oldEntity.getTransactionId();
            }
            daoLogger.info("lockFailed1: transId: {} oldTransId: {} entityId: {} ", entity.getTransactionId(), oldTid, entityId);

        }
        Precondition.checkState(bol, this.getClass(), ErrMsgs.Err_000000000014);
        if(StringUtils.isBlank(keys)){
            ops.set(keysId, ","+entityId, TRANS_TIMEOUT_TIME, TimeUnit.MINUTES);
        }else{
            ops.append(keysId, ","+entityId);
        }
    }
    @Transactional(rollbackFor = Exception.class)
    public int executeIncrement(String entityId, IncrementUpdate incrementUpdate, String sql, Object[] values, String sqlEntity, Object[] valuesEntity){
        String tranId = CurrentTransactionId.s(true);
        IncLog incLog = new IncLog();
        incLog.setSql(sql);
        incLog.setValues(values);
        daoLogger.info("transId: {} {} increment_sql: {} {}", tranId, nextSqlLogNum(), entityId, JSON.toJSONString(incLog));
        int updateCount = jdbcTemplate.update(sql, values);
        if(updateCount>0 && !incrementUpdate.isNoRollback()){
            incLog.setSql(sqlEntity);
            incLog.setValues(valuesEntity);
            daoLogger.info("transId: {} {} increment_log_sql: {} {}", tranId, nextSqlLogNum(), entityId, JSON.toJSONString(incLog));
            jdbcTemplate.update(sqlEntity, valuesEntity);
        }
        return updateCount;
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

    public void save(DaoSave model){
        Precondition.validation(model.getEntity());
        if(isSimpleTransactionContext()){
            saveSimple(model);
            return;
        }
        String transId = CurrentTransactionId.s(true);
        daoLogger.info("transId: {} {} saveObject: {}", transId, nextSqlLogNum(), JSON.toJSONString(model));
        setUpdateTag();
        IdEntity entity = (IdEntity) model.getEntity();

        ImmutableList<Field> fields = ReflectUtil.getAnnotationField(entity.getClass(), Column.class);
        String sql = null;
        Object[] values = null;
        int type = -1;
        if(null == entity.getId()) {
            entity.setId(ObjectId.next());
            sql = buildInsertSql(entity.getClass(), fields);
            values = getInsertValues(entity, fields);
            type=0;
        }else{
            checkIs4updateEntity(entity);
            sql = buildUpdateSql(entity.getClass(), fields);
            values = getUpdateValues(entity, fields);
            type=1;
        }
        putTransactionEntity(new TransactionEntity(transId, type, entity));
        push2redisUpdateValue(sql, values);
    }
    private void saveSimple(DaoSave model){
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
        String transId = simpleTransId(true);
        IncLog incLog = new IncLog();
        incLog.setSql(sql);
        incLog.setValues(values);
        daoLogger.info("simptransId: {} {} update_sql: {}", transId, nextSqlLogNum(), JSON.toJSONString(incLog));
        jdbcTemplate.update(sql, values);

    }
    private void push2redisUpdateValue(String sql, Object[] values){
        Update update = new Update();
        update.setSql(sql);
        if(null != values) {
            ArrayList<UpdateValue> updateValues = new ArrayList<>();
            for (int i = 0; i < values.length; i++) {
                Object value = values[i];
                updateValues.add(buildUpdateValue(value));
            }
            update.setValues(updateValues);
        }
        String tranId = CurrentTransactionId.s(true);

        Update4Json update4Json = new Update4Json();
        update4Json.setUpdate(update);
        setRedis(tranId, JSON.toJSONString(update4Json), false);
    }
    private UpdateValue buildUpdateValue(Object value) {
        UpdateValue updateValue = new UpdateValue();
        if (null == value) { return updateValue; }

        updateValue.setValue(value);

        if(value instanceof Boolean){
            updateValue.setType("Boolean");
        } else if(value instanceof Byte) {
            updateValue.setType("Byte");
        } else if(value instanceof Short) {
            updateValue.setType("Short");
        }else if(value instanceof Integer){
            updateValue.setType("Integer");
        }else if(value instanceof Long){
            updateValue.setType("Long");
        }else if(value instanceof Float){
            updateValue.setType("Float");
        }else if(value instanceof Double){
            updateValue.setType("Double");
        }else if(value instanceof BigDecimal){
            updateValue.setType("BigDecimal");
        }else if(value instanceof Timestamp){
            updateValue.setType("Timestamp");
        }else if(value instanceof java.sql.Date){
            updateValue.setType("SqlDate");
        }else if(value instanceof Date){
            updateValue.setType("Date");
        }else if(value instanceof String){
            updateValue.setType("String");
        }else {
            throw new ApplicationException("sql 更新语句不接受此参数类型 :"+value.getClass().getName());
        }

        return updateValue;
    }
    public void daoOne4Update(DaoOne4Update model){
        if(isSimpleTransactionContext()){
            daoOne4UpdateSimple(model);
            return;
        }
        Object key = model.getKey();
        Class<?> kls = model.getKls();

        String transId = CurrentTransactionId.s(true);
        String keyId = Consts.SERVICE_TRANSACTION_ENTITY + key;
        String text = redisTemplate.opsForValue().get(keyId);
        if (StringUtils.isNotBlank(text)) {
            TransactionEntity tentity = JSON.parseObject(text, TransactionEntity.class);
            boolean bol = Objects.equals(tentity.getTransactionId(), transId);
            if(!bol) {
                daoLogger.info("lockFailed2: transId: {} oldTransId: {} entityId: {} ", transId, tentity.getTransactionId(), key);
            }
            Precondition.checkState(bol, Err_000000000014);

            Object enttiy = JSON.parseObject(tentity.getEntity(), kls);
            ReflectUtil.setFieldValue(enttiy, "forUpdate", true, true);
            model.setSummerResult(enttiy);
            return;
        }

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
        if(null != o[0]) {
            IdEntity entity = (IdEntity) o[0];
            ReflectUtil.setFieldValue(entity, "forUpdate", true, true);
            putTransactionEntity(new TransactionEntity(transId, 3, entity));
        }
        model.setSummerResult(o[0]);
    }
    private void daoOne4UpdateSimple(DaoOne4Update model){
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
    private void checkIs4updateEntity(IdEntity entity){
        boolean forUpdate = (boolean)ReflectUtil.pathValue(entity, "forUpdate");
        if(!forUpdate){
            throw new ApplicationException("如果要更新或删除对象，请先锁定对象，使用forUpdate查询对象");
        }
    }
    public void daoOne(DaoOne model){
        if(isSimpleTransactionContext()){
            daoOneSimple(model);
            return;
        }
        Object key = model.getKey();
        Class<?> kls = model.getKls();

        String transId = CurrentTransactionId.s(true);
        String keyId = Consts.SERVICE_TRANSACTION_ENTITY + key;
        String text = redisTemplate.opsForValue().get(keyId);
        if (StringUtils.isNotBlank(text)) {
            TransactionEntity tentity = JSON.parseObject(text, TransactionEntity.class);
            if (Objects.equals(tentity.getTransactionId(), transId)) {
                Object enttiy = JSON.parseObject(tentity.getEntity(), kls);
                model.setSummerResult(enttiy);
                return;
            }
        }

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
    private void daoOneSimple(DaoOne model){
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

    public void one4column(DaoOne4Column model){
        Object key = model.getKey();
        Class<?> kls = model.getKls();

        String tableName = getTableName(kls);
        String sql = "select * from "+tableName+" where "+model.getColumnName()+"=? limit 1";

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
    public void idin(DaoIdIn model){
        List<Object> keys = model.getKeys();
        if(CollectionUtils.isEmpty(keys)){
            model.setSummerResult(new ArrayList<>(0));
            return;
        }
        Class<?> kls = model.getKls();
        List<String> strings = keys.stream()
            .map(s -> "?")
            .collect(Collectors.toList());

        String join = Joiner.on(",").join(strings);

        String tableName = getTableName(kls);
        String sql = "select * from "+tableName+" where id in("+join+")";

        List<Map<String, Object>> mapList = jdbcTemplate.queryForList(sql, keys.toArray());

        ImmutableList<Field> fields = ReflectUtil.getAnnotationField(kls, Column.class);
        model.setSummerResult(convertMapList2ObjectList(mapList, model.getKls(), fields));
    }

    public void daoRemove(DaoRemove model){
        if(isSimpleTransactionContext()){
            daoRemoveSimple(model);
            return;
        }
        String transId = CurrentTransactionId.s(true);
        daoLogger.info("transId: {} {} saveObject: {}", transId, nextSqlLogNum(), JSON.toJSONString(model));
        setUpdateTag();
        IdEntity entity = (IdEntity) model.getEntity();
        checkIs4updateEntity(entity);

        String tableName = getTableName(entity.getClass());
        String sql = "delete from "+tableName+" where id=?";

        putTransactionEntity(new TransactionEntity(transId, 2, entity));
        push2redisUpdateValue(sql, new Object[]{entity.getId()});
    }
    private void daoRemoveSimple(DaoRemove model){
        IdEntity entity = (IdEntity) model.getEntity();

        String tableName = getTableName(entity.getClass());
        String sql = "delete from "+tableName+" where id=?";
        Object[] values = new Object[]{entity.getId()};

        String transId = simpleTransId(true);
        IncLog incLog = new IncLog();
        incLog.setSql(sql);
        incLog.setValues(values);
        daoLogger.info("simptransId: {} {} update_sql: {}", transId, nextSqlLogNum(), JSON.toJSONString(incLog));
        jdbcTemplate.update(sql, values);
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

    private Object[] getInsertValues(IdEntity entity, ImmutableList<Field> fields){
        Object[] values = new Object[fields.size()];
        for(int i=0; i<fields.size(); i++){
            Field field = fields.get(i);
            Object e = ReflectUtil.fieldValueNoThrow(field, entity);
            if(e instanceof Timestamp){
                values[i] = cn.hutool.core.date.DateTime.of((Date) e).toString(DatePattern.NORM_DATETIME_MS_FORMAT);
            }else {
                values[i] = e;
            }
        }
        return values;
    }
    private Object[] getUpdateValues(IdEntity entity, ImmutableList<Field> fields){
        List<Object> values = new ArrayList<>(fields.size());
        for(int i=0; i<fields.size(); i++){
            Field field = fields.get(i);
            Column column = field.getAnnotation(Column.class);
            if("id".equals(column.value().toLowerCase())) { continue; }

            Object e = ReflectUtil.fieldValueNoThrow(field, entity);
            if(e instanceof Timestamp){
                values.add(cn.hutool.core.date.DateTime.of((Date) e).toString(DatePattern.NORM_DATETIME_MS_FORMAT));
            }else {
                values.add(e);
            }
        }
        values.add(entity.getId());
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
        Column column = obj.getAnnotation(Column.class);
        buffer.append(column.value());
        for(int i=1; i<fields.size(); i++){
            obj = fields.get(i);
            column = obj.getAnnotation(Column.class);
            buffer.append(",");
            buffer.append(column.value());
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
            Column column = obj.getAnnotation(Column.class);
            if("id".equals(column.value().toLowerCase())) { continue; }

            buffer.append((first?"":",")+column.value()+"=?");
            first = false;
        }
        buffer.append(" WHERE id=?");

        sql = buffer.toString();
        UPDATE_SQL_CACHE.put(kls, sql);
        return sql;
    }
    static class IncLog{
        private String sql;
        private Object[] values;

        public String getSql() {
            return sql;
        }

        public void setSql(String sql) {
            this.sql = sql;
        }

        public Object[] getValues() {
            return values;
        }

        public void setValues(Object[] values) {
            this.values = values;
        }
    }
}
