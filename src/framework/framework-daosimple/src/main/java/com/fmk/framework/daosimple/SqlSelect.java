package com.fmk.framework.daosimple;

import com.fmk.framework.daomodel.QuerySelect;
import com.google.common.collect.Lists;
import com.fmk.framework.daoannotations.Table;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SqlSelect implements QuerySelect {
    private SQL sql = new SQL();
    private SQL countSql = new SQL().SELECT("count(1)");
    private ArrayList<Object> parameters = new ArrayList<>(2);
    private ArrayList<Object> countParameters = new ArrayList<>(2);
    private static ArrayList<Object> EmptyList = new ArrayList<>(0);

    public static SqlSelect selec(String columns){
        SqlSelect select = new SqlSelect();
        select.select(columns);
        return select;
    }
    public SqlSelect select(String column){
        sql.SELECT(column);
        return this;
    }
    public SqlSelect select(String ... column){
        sql.SELECT(column);
        return this;
    }
    public SqlSelect select_distinct(String columns) {
        sql.SELECT_DISTINCT(columns);
        return this;
    }
    public SqlSelect select_distinct(String... columns) {
        sql.SELECT_DISTINCT(columns);
        return this;
    }
    public SqlSelect fromClassName(Class<?> kls, String alias) {
        Table table = kls.getAnnotation(Table.class);
        return this.from(table.value()+" "+alias);
    }
    public SqlSelect fromClassName(Class<?> kls) {
        Table table = kls.getAnnotation(Table.class);
        return this.from(table.value());
    }
    public SqlSelect from(String table) {
        sql.FROM(table);
        countSql.FROM(table);

        return this;
    }
    public SqlSelect join(String join) {
        sql.JOIN(join);
        countSql.JOIN(join);

        return this;
    }
    public SqlSelect inner_join(String join) {
        sql.INNER_JOIN(join);
        countSql.INNER_JOIN(join);

        return this;
    }

    public SqlSelect left_outer_join(String join) {
        sql.LEFT_OUTER_JOIN(join);
        countSql.LEFT_OUTER_JOIN(join);

        return this;
    }

    public SqlSelect right_outer_join(String join) {
        sql.RIGHT_OUTER_JOIN(join);
        countSql.RIGHT_OUTER_JOIN(join);

        return this;
    }
    public SqlSelect outer_join(String join) {
        sql.OUTER_JOIN(join);
        countSql.OUTER_JOIN(join);

        return this;
    }

    public SqlSelect where(boolean bol, String conditions, List<Object> args) {
        if(!bol) { return this; }
        return this.where(conditions, args);
    }
    public SqlSelect where(boolean bol, String conditions, Object ... args) {
        if(!bol) { return this; }
        return this.where(conditions, args);
    }


    public SqlSelect eq(boolean bol, String fieldName, Object value) {
        if(!bol) { return this; }
        return this.where(fieldName+" = ?", value);
    }
    public SqlSelect eq(String fieldName, Object value) {
        return this.where(fieldName+" = ?", value);
    }
    public SqlSelect neq(boolean bol, String fieldName, Object value) {
        if(!bol) { return this; }
        return this.where(fieldName+" != ?", value);
    }
    public SqlSelect neq(String fieldName, Object value) {
        return this.where(fieldName+" != ?", value);
    }
    public SqlSelect gt(boolean bol, String fieldName, Object value) {
        if(!bol) { return this; }
        return this.where(fieldName+" > ?", value);
    }
    public SqlSelect gt(String fieldName, Object value) {
        return this.where(fieldName+" > ?", value);
    }
    public SqlSelect ge(boolean bol, String fieldName, Object value) {
        if(!bol) { return this; }
        return this.where(fieldName+" >= ?", value);
    }
    public SqlSelect ge(String fieldName, Object value) {
        return this.where(fieldName+" >= ?", value);
    }
    public SqlSelect lt(boolean bol, String fieldName, Object value) {
        if(!bol) { return this; }
        return this.where(fieldName+" < ?", value);
    }
    public SqlSelect lt(String fieldName, Object value) {
        return this.where(fieldName+" < ?", value);
    }
    public SqlSelect le(boolean bol, String fieldName, Object value) {
        if(!bol) { return this; }
        return this.where(fieldName+" <= ?", value);
    }
    public SqlSelect le(String fieldName, Object value) {
        return this.where(fieldName+" <= ?", value);
    }
    public SqlSelect like(boolean bol, String fieldName, Object value) {
        if(!bol) { return this; }
        return this.where(fieldName+" like ?", value);
    }
    public SqlSelect like(String fieldName, Object value) {
        return this.where(fieldName+" like ?", value);
    }
    public SqlSelect in(boolean bol, String fieldName, List<Object> values) {
        if(!bol || CollectionUtils.isEmpty(values)) { return this; }
        String txt = StringUtils.repeat("?", ",", values.size());
        return this.where(fieldName+" in ("+txt+")", values);
    }
    public SqlSelect in(String fieldName, List<Object> values) {
        if(CollectionUtils.isEmpty(values)) { return this; }
        String txt = StringUtils.repeat("?", ",", values.size());
        return this.where(fieldName+" in ("+txt+")", values);
    }


    public SqlSelect where(String conditions, List<Object> args) {
        sql.WHERE(conditions);
        countSql.WHERE(conditions);
        if(null != args && args.size()>0) {
            this.parameters.addAll(args);
            this.countParameters.addAll(args);
        }
        return this;

    }
    public SqlSelect where(String conditions, Object ... args) {
        if(null == args) { return this.where(conditions, EmptyList); }
        else { return this.where(conditions, Arrays.asList(args)); }
    }
    public SqlSelect or() {
        sql.OR();
        countSql.OR();
        return this;
    }
    public SqlSelect and() {
        sql.AND();
        countSql.AND();
        return this;
    }
    public SqlSelect group_by(String columns) {
        sql.GROUP_BY(columns);
        countSql.GROUP_BY(columns);
        return this;
    }
    public SqlSelect having(boolean bol, String conditions, List<Object> args) {
        if(!bol) { return this; }
        return this.having(conditions, args);
    }
    public SqlSelect having(boolean bol, String conditions, Object ... args) {
        if(!bol) { return this; }
        return this.having(conditions, args);
    }
    public SqlSelect having(String conditions, List<Object> args) {
        sql.HAVING(conditions);
        countSql.HAVING(conditions);
        if(null != args && !args.isEmpty()) {
            this.parameters.addAll(args);
            this.countParameters.addAll(args);
        }
        return this;

    }
    public SqlSelect having(String conditions, Object ... args) {
        if(null == args) { return this.having(conditions, EmptyList); }
        else { return this.having(conditions, Arrays.asList(args)); }
    }

    public SqlSelect order_by(String columns) {
        sql.ORDER_BY(columns);
        return this;
    }

    public String toCountString() {
        return countSql.toString();
    }

    public String toString(int start, int limit) {
        return sql.toString()+" limit "+start+", "+limit;
    }

    public String toSimpleConditionString(int start, int limit){
        return toSimpleConditionString()+" limit "+start+", "+limit;
    }
    public String toSimpleConditionString(){
        String s = this.toString();
        int whereIndex = s.indexOf("WHERE");
        if(whereIndex <=0) { return "1=1";}
        else {
            return s.substring(whereIndex + 5);
        }
    }
    @Override
    public String toString() {
        return sql.toString();
    }
    public Object[] parameters(){
        return this.parameters.toArray();
    }
    public Object[] countParameters(){
        return this.countParameters.toArray();
    }

    @Override
    public String toSqlString() {
        return toString();
    }

    @Override
    public String toSqlString(int start, int limit) {
        return toString(start, limit);
    }

    @Override
    public String toCountSqlString() {
        return toCountString();
    }

    @Override
    public List<Object> getParameters() {
        return Lists.newArrayList(this.parameters);
    }
}
