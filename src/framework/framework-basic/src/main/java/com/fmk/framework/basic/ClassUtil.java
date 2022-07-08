package com.fmk.framework.basic;

import com.fmk.framework.consts.Consts;

public class ClassUtil {
    public static boolean isAppClass(Class<?> kls){
        return kls.getName().startsWith(Consts.SYSTEM_PKG_COM_FMK_PREFIX) || kls.getName().startsWith(Consts.SYSTEM_PKG_COM_COMP_PREFIX);
    }
    public static boolean isAppClass(String klsName){
        return klsName.startsWith(Consts.SYSTEM_PKG_COM_FMK_PREFIX) || klsName.startsWith(Consts.SYSTEM_PKG_COM_COMP_PREFIX);
    }
}
