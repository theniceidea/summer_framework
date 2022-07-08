package com.fmk.framework.basic;


import org.apache.commons.lang3.StringUtils;

public class FileUtil {
    public static String getExtName(String fileName){
        if(StringUtils.isBlank(fileName)) return "";
        int i = fileName.lastIndexOf(".");
        if(i<0) return "";
        String suffix = fileName.substring(i + 1);
        return StringUtils.lowerCase(suffix);
    }
}
