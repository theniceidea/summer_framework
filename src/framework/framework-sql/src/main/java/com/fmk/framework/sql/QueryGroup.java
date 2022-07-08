package com.fmk.framework.sql;

import java.util.ArrayList;
import java.util.List;

public class QueryGroup implements IQueryItem{

    private List<IQueryItem> items;
    private boolean and = true;

    public QueryGroup(){
        this.items = new ArrayList<>(5);
        this.and = true;
    }
    public QueryGroup(boolean and){
        this.items = new ArrayList<>(5);
        this.and = and;
    }

    @Override
    public boolean empty(){
        return items.size()<=0;
    }

    public QueryGroup add(QueryGroup group){
        this.items.add(group);
        return this;
    }

    public QueryGroup eq(boolean bln, String name, Object value){
        if(bln) this.items.add(new QueryItem(name, "=", value));
        return this;
    }

    public QueryGroup gt(boolean bln, String name, Object value){
        if(bln) this.items.add(new QueryItem(name, ">", value));
        return this;
    }

    public QueryGroup ge(boolean bln, String name, Object value){
        if(bln) this.items.add(new QueryItem(name, ">=", value));
        return this;
    }

    public QueryGroup lt(boolean bln, String name, Object value){
        if(bln) this.items.add(new QueryItem(name, "<", value));
        return this;
    }

    public QueryGroup le(boolean bln, String name, Object value){
        if(bln) this.items.add(new QueryItem(name, "<=", value));
        return this;
    }

    public QueryGroup startWith(boolean bln, String name, Object value){
        if(bln) this.items.add(new QueryItem(name, "like", value+"%"));
        return this;
    }
    public QueryGroup endWith(boolean bln, String name, Object value){
        if(bln) this.items.add(new QueryItem(name, "like", "%"+value));
        return this;
    }
    public QueryGroup midWith(boolean bln, String name, Object value){
        if(bln) this.items.add(new QueryItem(name, "like", "%"+value+"%"));
        return this;
    }
}
