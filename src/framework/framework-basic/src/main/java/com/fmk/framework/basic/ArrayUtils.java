package com.fmk.framework.basic;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class ArrayUtils {
    public static <T> List<T> toList(T[] arr){
        if(null == arr) return Lists.newArrayList();
        return Lists.newArrayList(arr);
    }
    public static <T> ArrayList<T> arrayList(Collection collection){
        if(null == collection) return Lists.newArrayList();
        if(collection instanceof ArrayList) return (ArrayList<T>) collection;
        return Lists.newArrayList(collection);
    }
}
