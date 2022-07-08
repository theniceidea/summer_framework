package com.fmk.framework.basic;


import jodd.io.FileUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.sql.Timestamp;
import java.util.Random;
import java.util.UUID;

@Service
public class StringUtil {
    private static Random RANDOM = new Random();
    public static final String STR_Lower_Upper_Num = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final String STR_Lower = "abcdefghijklmnopqrstuvwxyz";
    public static final String STR_Upper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String STR_Lower_Num = "abcdefghijklmnopqrstuvwxyz0123456789";
    public static final String STR_Upper_Num = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static final String STR_Num = "0123456789";

    public static final DateTimeFormatter DateTimeFormatterDay = DateTimeFormat.forPattern("yyyy-MM-dd");
    public static final DateTimeFormatter DateTimeFormatterMinute = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm");
    public static final DateTimeFormatter DateTimeFormatterSecond = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
    public static final DateTimeFormatter DateTimeFormatterMillis = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss.SSS");

    public static StringUtil strings;
    public StringUtil(){
        synchronized (StringUtil.class) {
            if (null == strings) strings = this;
        }
    }


    public String trim2Empty(Object o){
        if(null == o) return "";
        return o.toString().trim();
    }

    public String randomStr(String chars, int length) {
        if(length<=0) return "";
        StringBuilder sb = new StringBuilder();

        if (StringUtils.isBlank(chars)) chars = STR_Lower_Upper_Num;
        for (int i=0; i<length; i++) {
            sb.append(chars.charAt(RANDOM.nextInt(chars.length())));
        }
        return sb.toString();
    }

    public String randomStr(int length) {
        return randomStr(STR_Lower_Upper_Num, length);
    }

    public String padNanoTimeLen6(Timestamp timestamp){
        String s = new DateTime(timestamp).toString("yyyy-MM-dd HH:mm:ss.");
        String nanos = timestamp.getNanos()+"";
        if(nanos.length()>6) nanos=nanos.substring(0, 6);
        else if(nanos.length()<6) nanos = StringUtils.leftPad(nanos, 6, "0");
        return s+nanos;
    }
    public String getUUIDString(){
        return UUID.randomUUID().toString().replaceAll("-", "").toLowerCase();
    }
    public int toInt(String s){
        return Integer.valueOf(s);
    }


    public String convStr(String str){
        if (StringUtils.isBlank(str)) return null;

        String str1 = str.substring(0,2);
        String str2 = str.substring(2);

        return str1+":"+str2;


    }

    public static String fromResource(String resourcePath){
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = StringUtil.class.getResourceAsStream(resourcePath);
            return FileUtil.readUTFString(resourceAsStream);
        } catch (Exception e) {
            return "";
        }finally {
            IOUtils.closeQuietly(resourceAsStream);
        }

    }
    public static String firstCharLower(String s){
        if(StringUtils.isBlank(s)) { return s; }
        return s.substring(0, 1).toLowerCase()+s.substring(1);
    }
    public static String firstCharUpper(String s){
        if(StringUtils.isBlank(s)) { return s; }
        return s.substring(0, 1).toUpperCase()+s.substring(1);
    }
}
