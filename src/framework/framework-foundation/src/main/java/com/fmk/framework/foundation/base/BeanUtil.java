package com.fmk.framework.foundation.base;

//import javassist.util.proxy.ProxyObject;
//import org.hibernate.proxy.HibernateProxy;
//import org.springframework.beans.BeanUtils;
//
//import javax.persistence.Entity;
//import java.lang.reflect.Field;
//import java.lang.reflect.Modifier;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Objects;

/**
 * Created by jerry on 16-5-20.
 */
public class BeanUtil {

//    private static HashMap<String, String[]> ignores = new HashMap<>();
//
//    public static <T> T copyProperties(Object from, T t){
//        if(null == from) { return null; }
//        return copyProperties(from, t, null);
//    }
//    public static <T> T copyProperties(Object from, T t, String... ignore){
//        if(null == from) { return null; }
//        BeanUtils.copyProperties(from, t, ignore);
//        return t;
//    }
//    public static <T> T clone(Object from, Class<T> tClass, String... ignore){
//        if(null == from) { return null; }
//        try {
//            T t = tClass.newInstance();
//            BeanUtils.copyProperties(from, t, ignore);
//            return t;
//        } catch (InstantiationException | IllegalAccessException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    public static <T> T clone(Object from, Class<T> tClass){
//        return clone(from, tClass, null);
//    }
//    public static <T> T clone(T from){
//        Class<?> realClass = getRealClass(from.getClass());
//        return (T) clone(from, realClass, null);
//    }
//    public static <T> T clone(T from, String... ignore){
//        Class<?> realClass = getRealClass(from.getClass());
//        return (T) clone(from, realClass, ignore);
//    }
//
//    public static <T> T iclone(T from){
//        if(null == from) { return null; }
//        Class<?> tClass = getRealClass(from.getClass());
//        return iclone(from, (Class<T>) tClass);
//    }
////    public static <T> T iclone(T from, String... ignore){
////        if(null == from) return null;
////        Class<?> tClass = getRealClass(from.getClass());
////        return iclone(from, (Class<T>) tClass, ignore);
////    }
//
//    public static <T> T iclone(Object from, Class<T> tClass){
//        if(null == from) { return null; }
//        return clone(from, tClass, getIgnores(tClass));
//    }
//
////    public static <T> T iclone(Object from, Class<T> tClass, String... ignore){
////        if(null == from) return null;
////        String[] ignores = getIgnores(tClass);
////        ArrayList<String> strings = Lists.newArrayList(ignores);
////        strings.addAll(Lists.newArrayList(ignore));
////        return clone(from, tClass, strings.toArray(new String[]{}));
////    }
//
//    public static Class<?> getClass(Object object){
//        return null==object?null:object.getClass();
//    }
//
//    public static String[] getIgnores(Class<?> klass){
//        klass=getRealClass(klass);
//        String key = klass.getName();
//        if(ignores.containsKey(key)) { return ignores.get(key); }
//
//        List<String> list = new ArrayList<>();
//        while(true) {
//            Field[] fields = klass.getDeclaredFields();
//            for (Field field : fields) {
//                if(Modifier.isStatic(field.getModifiers())) { continue; }
//                Class<?> type = field.getType();
////                System.out.println("field:"+field.getName());
//                Entity annotation = type.getAnnotation(Entity.class);
//                boolean bol = null != annotation || Iterable.class.isAssignableFrom(type);
//                if (bol) {
//                    if (!list.contains(field.getName())) { list.add(field.getName()); }
//                    continue;
//                }
//
//            }
//            klass=klass.getSuperclass();
//            if(null == klass || Objects.equals(klass, Object.class)){
//                break;
//            }
//        }
//
//        String[] strings = list.toArray(new String[]{});
//        ignores.put(key, strings);
//        return strings;
//    }
//
//    private static Class<?> getRealClass(Class<?> klass){
//        if(null == klass) { return null; }
//        while (true) {
//            if (HibernateProxy.class.isAssignableFrom(klass) || ProxyObject.class.isAssignableFrom(klass)) {
//                klass = klass.getSuperclass();
//                continue;
//            }
//            return klass;
//        }
//    }
//    public static void main(String[] args){
////        System.out.println(Arrays.toString(getIgnores(WXAideArticle.class)));
//    }
}
