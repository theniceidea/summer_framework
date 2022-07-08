package com.fmk.framework.basic;

import cn.hutool.core.util.RandomUtil;

import java.util.HashMap;

public class ErrorMsgCodes {
    private static HashMap<String, String> cache=new HashMap<>();
    public static String getErrCode(String msg){
        if(cache.containsKey(msg)){
            return cache.get(msg);
        }
        String code = Math.abs(CityHash.cityHash64(msg))+"";
//        System.out.println("code:"+code);
        String code1="";
        String code2="";
        if(code.length()>12){
            code1=code.substring(0, 12);
            code2=code.substring(12);
//            System.out.println("code1:"+code1);
//            System.out.println("code2:"+code2);
            code = (Long.parseLong(code1) + Long.parseLong(code2))+"";
            if(code.length()>12){
                code1=code.substring(0, 12);
                code2=code.substring(12);
//                System.out.println("code1_:"+code1);
//                System.out.println("code2_:"+code2);
                code = (Long.parseLong(code1) + Long.parseLong(code2))+"";
            }
        }
        cache.put(msg, code);
        return code;
    }
//    public static void main(String[] args) {
//        String s="110uf16yd60q6w5h45ov7qbe71atvv53v81bsr2cpc3g08spta";
//        String a = getErrCode(s);
//        String a1 = getErrCode(s);
//        System.out.println(a);
//        System.out.println(a1);
//    }
//    public static void main(String[] args) {
////        110uf16yd60q6w5h45ov7qbe71atvv53v81bsr2cpc3g08spta
////        217080365389
////        217080365389
//        for(int i=0; i<100; i++) {
//            String s = RandomUtil.randomString(50);
//            if(i==0){
//                s="110uf16yd60q6w5h45ov7qbe71atvv53v81bsr2cpc3g08spta";
//            }
//            System.out.println(s);
//            String a = getErrCode(s);
//            System.out.println(a);
//            System.out.println(a);
//            System.out.println("=====");
//        }
//    }
}
