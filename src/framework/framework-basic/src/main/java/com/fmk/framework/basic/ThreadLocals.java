package com.fmk.framework.basic;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jerry on 16-9-19.
 */
public class ThreadLocals {
    private static List<ThreadLocal> threadLocals = new ArrayList<>();

    public static ThreadLocal newThreadLocal(){
        ThreadLocal tThreadLocal = new ThreadLocal<>();
        threadLocals.add(tThreadLocal);
        return tThreadLocal;
    }

    public static void init(){
        threadLocals.forEach(ThreadLocal::remove);
    }
}
