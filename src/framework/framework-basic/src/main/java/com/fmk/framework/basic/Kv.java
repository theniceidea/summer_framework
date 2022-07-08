package com.fmk.framework.basic;

import com.alibaba.fastjson.JSON;

import java.util.HashMap;

public class Kv extends HashMap<String, String> {
    public Kv(String key, String value){
        this.put(key, value);
    }
    public static Kv inst(String key, String value){
        return new Kv(key, value);
    }
    public Kv kv(String key, String value){
        this.put(key, value);
        return this;
    }
    public String toJsonString(){
        return JSON.toJSONString(this);
    }
}
