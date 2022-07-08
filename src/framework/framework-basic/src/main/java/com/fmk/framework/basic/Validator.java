package com.fmk.framework.basic;

import com.fmk.framework.annotations.validation.*;
import com.fmk.framework.basic.validat.*;
import com.fmk.framework.basic.validattestmodels.TestA;
import com.fmk.framework.basic.validattestmodels.TestB;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.*;

public class Validator {
    private static final HashMap<Class<? extends Annotation>, Class<? extends ValidatorItem>> VALIDATORITEMS = new HashMap<>();
    private static final HashMap<Class<?>, List<ValidatorItem>> VALIDATORS = new HashMap<>();
    private static final HashMap<Class<?>, HashSet<Field>> VALIDATFIELDS = new HashMap<>();

    static {
        VALIDATORITEMS.put(AssertEnum.class, AssertEnumValidator.class);
        VALIDATORITEMS.put(Blank.class, BlankValidator.class);
        VALIDATORITEMS.put(DateAfter.class, DateAfterValidator.class);
        VALIDATORITEMS.put(DateBefore.class, DateBeforeValidator.class);
        VALIDATORITEMS.put(DateRange.class, DateRangeValidator.class);
        VALIDATORITEMS.put(DateRangeNow.class, DateRangeNowValidator.class);
        VALIDATORITEMS.put(DecimalSize.class, DecimalSizeValidator.class);
        VALIDATORITEMS.put(Length.class, LengthValidator.class);
        VALIDATORITEMS.put(LengthRange.class, LengthRangeValidator.class);
        VALIDATORITEMS.put(ListEmpty.class, ListEmptyValidator.class);
        VALIDATORITEMS.put(ListNotEmpty.class, ListNotEmptyValidator.class);
        VALIDATORITEMS.put(NotBlank.class, NotBlankValidator.class);
        VALIDATORITEMS.put(NotNull.class, NotNullValidator.class);
        VALIDATORITEMS.put(Null.class, NullValidator.class);
        VALIDATORITEMS.put(Pattern.class, PatternValidator.class);
        VALIDATORITEMS.put(Size.class, SizeValidator.class);
    }

    public static void putValidatorItem(Class<Annotation> kls, Class<ValidatorItem> viKls){
        VALIDATORITEMS.put(kls, viKls);
    }

    public static String validation(Object bean){
        return validation_(bean, new HashSet<>());
    }
    private static String validation_(Object bean, HashSet<Object> validatedBeans){
        if(null == bean){
            return null;
        }
        if(null == validatedBeans){
            validatedBeans = new HashSet<>();
        }
        if(validatedBeans.contains(bean)){
            return null;
        }
        validatedBeans.add(bean);

        if(bean instanceof Collection){
            Collection coll = (Collection)bean;
            Iterator iterator = coll.iterator();
            while (iterator.hasNext()){
                Object next = iterator.next();
                if(null == next) {
                    continue;
                }
                String s = validation_(next, validatedBeans);
                if(StringUtils.isNotBlank(s)){
                    return s;
                }
            }
            return null;
        }
        if(bean instanceof Map){
            Map coll = (Map) bean;
            Iterator iterator = coll.values().iterator();
            while (iterator.hasNext()){
                Object next = iterator.next();
                if(null == next) {
                    continue;
                }
                String s = validation_(next, validatedBeans);
                if(StringUtils.isNotBlank(s)){
                    return s;
                }
            }
            return null;
        }

        if(!ClassUtil.isAppClass(bean.getClass())){
            return null;
        }
        List<ValidatorItem> itemList = getValidators(bean.getClass());
        for(int i=0; i<itemList.size(); i++){
            ValidatorItem validatorItem = itemList.get(i);
            boolean validation = validatorItem.validation(bean);
            if(!validation){
                return validatorItem.getFailedMsg();
            }
        }

        HashSet<Field> fields = getValidatfields(bean.getClass());
        HashSet<Object> finalValidatedBeans = validatedBeans;
        Iterator<Field> fieldIterator = fields.iterator();
        while (fieldIterator.hasNext()){
            Field field = fieldIterator.next();
            Object fieldvalue = ReflectUtil.fieldValueWrapExcep(field, bean);
            String s = validation_(fieldvalue, finalValidatedBeans);
            if(StringUtils.isNotBlank(s)){
                return s;
            }
        }

        return null;
    }

    private static List<ValidatorItem> getValidators(Class<?> kls){
        List<ValidatorItem> itemList = VALIDATORS.get(kls);
        if(null != itemList) {
            return itemList;
        }

        synchronized (Validator.class) {
            itemList = VALIDATORS.get(kls);
            if(null != itemList){
                return itemList;
            }
            itemList = new ArrayList<>();
            VALIDATORS.put(kls, itemList);
            List<ValidatorItem> finalItemList = itemList;
            ReflectUtil.eachFields(kls, field -> {
                VALIDATORITEMS.forEach((annoKls, viKls) -> {
                    Annotation annotation = field.getAnnotation(annoKls);
                    if (null != annotation) {
                        ValidatorItem item = ReflectUtil.newInstance(viKls);
                        item.setAnnotation(annotation);
                        item.setField(field);
                        finalItemList.add(item);
                    }
                });

                Follow[] annotations = field.getAnnotationsByType(Follow.class);
                for(int i=0; i<annotations.length; i++){
                    Follow follow = annotations[i];
                    Field followField = ReflectUtil.getField(follow.kls(), follow.field(), false, false);
                    if(null == followField){
                        throw new RuntimeException("class:"+kls.getName()+" field:"+field.getName()+" Follow 注解的class:"+follow.kls().getName()+" field:"+follow.field()+" 没找到field");
                    }
                    Annotation fieldAnnotation = followField.getAnnotation(follow.anno());
                    if(null == fieldAnnotation){
                        throw new RuntimeException("class:"+kls.getName()+" field:"+field.getName()+" Follow 注解的class:"+follow.kls().getName()+" field:"+follow.field()+" 没找到注解"+follow.anno().getSimpleName());
                    }
                    Class<? extends ValidatorItem> viKls = VALIDATORITEMS.get(follow.anno());
                    if(null == viKls){
                        throw new RuntimeException("class:"+kls.getName()+" field:"+field.getName()+" Follow 注解的class:"+follow.kls().getName()+" field:"+follow.field()+" 注解"+follow.anno().getSimpleName()+" 没有对应的验证器");
                    }
                    ValidatorItem item = ReflectUtil.newInstance(viKls);
                    item.setAnnotation(fieldAnnotation);
                    item.setField(field);
                    finalItemList.add(item);
                }
            });
            return itemList;
        }
    }
    private static HashSet<Field> getValidatfields(Class<?> kls){
        HashSet<Field> itemList = VALIDATFIELDS.get(kls);
        if(null != itemList) {
            return itemList;
        }
        synchronized (Validator.class) {
            itemList = VALIDATFIELDS.get(kls);
            if(null != itemList) {
                return itemList;
            }

            itemList = new HashSet<>();
            VALIDATFIELDS.put(kls, itemList);
            HashSet<Field> finalItemList = itemList;
            ReflectUtil.eachFields(kls, field -> {
                Class<?> type = field.getType();
                if (Collection.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type) || ClassUtil.isAppClass(kls)) {
                    finalItemList.add(field);
                }
            });
            return itemList;
        }
    }

    public static HashMap<String, String> apiInfo(Field field){
        HashMap<String, String> map = new HashMap<>();

        VALIDATORITEMS.forEach((annoKls, viKls) -> {
            Annotation annotation = field.getAnnotation(annoKls);
            if (null != annotation) {
                ValidatorItem item = ReflectUtil.newInstance(viKls);
                item.setAnnotation(annotation);
                item.setField(field);
                map.put(annoKls.getSimpleName(), item.apiInfo());
            }
        });

        Follow[] annotations = field.getAnnotationsByType(Follow.class);
        for(int i=0; i<annotations.length; i++){
            Follow follow = annotations[i];
            Field followField = ReflectUtil.getField(follow.kls(), follow.field(), false, false);
            if(null == followField){
                continue;
            }
            Annotation fieldAnnotation = followField.getAnnotation(follow.anno());
            if(null == fieldAnnotation){
                continue;
            }
            Class<? extends ValidatorItem> viKls = VALIDATORITEMS.get(follow.anno());
            if(null == viKls){
                continue;
            }
            ValidatorItem item = ReflectUtil.newInstance(viKls);
            item.setAnnotation(fieldAnnotation);
            item.setField(field);
            map.put(follow.anno().getSimpleName(), item.apiInfo());
        }
        return map;
    }

//    public static void main(String[] args){
//        List<TestB> list = new ArrayList<>();
//        TestB testB = new TestB();
//        testB.setField1("1");
//
//        list.add(testB);
//
//        TestA testA = new TestA();
////        testA.setField1("");
//        testA.setFieldEnum(ResourceType.ORDER.value());
//        testA.setFieldEnum(0);
////        testA.setField2(10);
////        testA.setTimestamp(DateTimeUtil.timestamp(DateTime.now().plusMinutes(10).getMillis()));
////        testA.setTimestampBefore(DateTimeUtil.timestamp(DateTime.now().plusMinutes(-10).getMillis()));
////
////        testA.setTimestampRange(DateTimeUtil.timestamp(DateTime.now().plusMinutes(0).getMillis()));
////        testA.setTimestampRangeNow(DateTimeUtil.timestamp(DateTime.now().plusMinutes(0).getMillis()));
//
//        testA.setDateAfter(DateTimeUtil.timestamp(DateTime.now().plusMinutes(10).getMillis()));
//        testA.setDateBefore(DateTimeUtil.timestamp(DateTime.now().plusMinutes(-10).getMillis()));
//
////        testA.setDateRange(DateTimeUtil.timestamp(DateTime.now().plusMinutes(0).getMillis()));
//        testA.setDateRangeNow(DateTimeUtil.timestamp(DateTime.now().plusMinutes(0).getMillis()));
//
//        testA.setBigDecimal(new BigDecimal("1000"));
//        testA.setField2(100);
//        testA.setLength(StringUtils.repeat("0", 10));
//        testA.setLengthRange(StringUtils.repeat("0", 10));
//        testA.setNotnull("");
////        testA.setNullString("");
//        testA.setPattern("10");
//        testA.setFollowField("a");
//
//
//        testA.setList(list);
//
//        HashMap<String, TestB> map = new HashMap<>();
//        testB = new TestB();
//        testB.setField1("1");
//        map.put("1", testB);
//        testA.setMap(map);
//
//        String validation = validation(testA);
//
//        System.out.println("=================00000000000000000000");
////        System.out.println(DateTime.now().plusMinutes(-10).getMillis());
////        System.out.println(DateTime.now().plusMinutes(10).getMillis());
//        System.out.println(validation);
//        System.out.println("=================00000000000000000000");
//
//    }
}
