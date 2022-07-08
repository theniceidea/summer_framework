package com.fmk.framework.basic;

import cn.hutool.core.lang.ObjectId;

public class IdGenerate {
    public static String mongoIdWithSuffix(String suffix){
        return ObjectId.next()+suffix;
    }
}
