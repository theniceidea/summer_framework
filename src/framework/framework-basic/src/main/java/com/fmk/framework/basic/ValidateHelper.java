package com.fmk.framework.basic;

import cn.hutool.core.util.ReUtil;
import com.fmk.framework.annotations.validation.*;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * @author larry
 * @date 2019-06-04
 */
public class ValidateHelper {

    private final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    private final static String YYYY_MM_DD = "yyyy-MM-dd";
    private final static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

    /**
     * 枚举值是否有效
     *
     * @param assertEnum
     * @param value
     * @return
     */
    public static boolean assertEnum(AssertEnum assertEnum, Object value) {
        Class<?> enumClass = assertEnum.enumClass();
        String extValue = assertEnum.extValue();

        Object[] enumConstants = enumClass.getEnumConstants();
        if (null != enumConstants && IEnum.class.isAssignableFrom(enumClass)) {
            for (Object constant : enumConstants) {
                IEnum enumConstant = (IEnum) constant;
                if (Objects.equals(enumConstant.value(), value)) {
                    return true;
                }
            }
        }

        return isExtEqual(extValue, value);
    }

    private static boolean isExtEqual(String extValue, Object value) {
        if(StringUtils.isBlank(extValue)){
            return false;
        }
        Class intClass = Integer.class;
        if (intClass.isInstance(value)) {
            return Integer.valueOf(extValue).equals(value);
        }
        Class longClass = Long.class;
        if (longClass.isInstance(value)) {
            return Long.valueOf(extValue).equals(value);
        }
        Class booleanClass = Boolean.class;
        if (booleanClass.isInstance(value)) {
            return Boolean.valueOf(extValue).equals(value);
        }
        Class floatClass = Float.class;
        if (floatClass.isInstance(value)) {
            return Float.valueOf(extValue).equals(value);
        }
        Class doubleClass = Double.class;
        if (doubleClass.isInstance(value)) {
            return Double.valueOf(extValue).equals(value);
        }
        Class stringClass = String.class;
        if (stringClass.isInstance(value)) {
            return String.valueOf(extValue).equals(value);
        }
        return false;
    }


    /**
     * value是否晚于系统当前时间
     *
     * @param value
     * @return
     */
    public static boolean dateAfter(Object value) {

        Class longClass = Long.class;
        if (longClass.isInstance(value)) {
            return new DateTime(value).isAfterNow();
        }

        Class stringClass = String.class;
        if (stringClass.isInstance(value)) {
            DateTime dateTime = dateFormat(value.toString());
            return null != dateTime && dateTime.isAfterNow();
        }

        Class timestampClass = Timestamp.class;
        if (timestampClass.isInstance(value)) {
            Timestamp date = (Timestamp) value;
            return new DateTime(date.getTime()).isAfterNow();
        }

        Class dateClass = Date.class;
        if (dateClass.isInstance(value)) {
            Date date = (Date) value;
            return date.after(new Date());
        }

        return false;
    }

    private static DateTime dateFormat(String value) {
        String format;
        if (value.length() == YYYY_MM_DD.length()) {
            format = YYYY_MM_DD;
        } else if (value.length() == YYYY_MM_DD_HH_MM.length()) {
            format = YYYY_MM_DD_HH_MM;
        } else if (value.length() == YYYY_MM_DD_HH_MM_SS.length()) {
            format = YYYY_MM_DD_HH_MM_SS;
        } else {
            return null;
        }
        DateTimeFormatter dtf = DateTimeFormat.forPattern(format);
        try {
            return dtf.parseDateTime(value);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * value是否早于系统当前时间
     *
     * @param value
     * @return
     */
    public static boolean dateBefore(Object value) {
        Class longClass = Long.class;
        if (longClass.isInstance(value)) {
            return new DateTime(value).isBeforeNow();
        }

        Class stringClass = String.class;
        if (stringClass.isInstance(value)) {
            DateTime dateTime = dateFormat(value.toString());
            return null != dateTime && dateTime.isAfterNow();
        }

        Class timestampClass = Timestamp.class;
        if (timestampClass.isInstance(value)) {
            Timestamp date = (Timestamp) value;
            return new DateTime(date.getTime()).isBeforeNow();
        }

        Class dateClass = Date.class;
        if (dateClass.isInstance(value)) {
            Date date = (Date) value;
            return date.before(new Date());
        }

        return false;
    }

    /**
     * 日期范围检查
     *
     * @param dateRange DateRange注解对象
     * @param value     值
     * @return
     */
    public static boolean dateRange(DateRange dateRange, Object value) {
        long min = dateRange.min();
        long max = dateRange.max();
//        long min =1560096000000L;//2019-06-10 00:00:00
//        long max = 1575907200000L;//2019-12-10 00:00:00

        Class longClass = Long.class;
        if (longClass.isInstance(value)) {
            DateTime dateTime = new DateTime(value);
            return dateTime.isBefore(max) && dateTime.isAfter(min);
        }

        Class stringClass = String.class;
        if (stringClass.isInstance(value)) {
            DateTime dateTime = dateFormat(value.toString());
            return null != dateTime && dateTime.isBefore(max) && dateTime.isAfter(min);
        }

        Class timestampClass = Timestamp.class;
        if (timestampClass.isInstance(value)) {
            DateTime dateTime = new DateTime(((Timestamp) value).getTime());
            return dateTime.isBefore(max) && dateTime.isAfter(min);
        }

        Class dateClass = Date.class;
        if (dateClass.isInstance(value)) {
            Date date = (Date) value;
            return date.before(new Date(max)) && date.after(new Date(min));
        }

        return false;
    }

    /**
     * 日期范围检查
     * now - before < value < now + after
     *
     * @param dateRangeNow
     * @param value
     * @return
     */
    public static boolean dateRangeNow(DateRangeNow dateRangeNow, Object value) {
        long before = dateRangeNow.before();
        long after = dateRangeNow.after();
//        long before =10000L;
//        long after = 20000L;

        Class longClass = Long.class;
        if (longClass.isInstance(value)) {
            DateTime dateTime = new DateTime(value);
            return dateTime.isAfter(DateTime.now().withDurationAdded(before, -1)) &&
                    dateTime.isBefore(DateTime.now().withDurationAdded(after, 1));
        }

        Class stringClass = String.class;
        if (stringClass.isInstance(value)) {
            DateTime dateTime = dateFormat(value.toString());
            return null != dateTime
                    && dateTime.isAfter(DateTime.now().withDurationAdded(before, -1))
                    && dateTime.isBefore(DateTime.now().withDurationAdded(after, 1));
        }

        Class timestampClass = Timestamp.class;
        if (timestampClass.isInstance(value)) {
            DateTime dateTime = new DateTime(((Timestamp) value).getTime());
            return dateTime.isAfter(DateTime.now().withDurationAdded(before, -1))
                    && dateTime.isBefore(DateTime.now().withDurationAdded(after, 1));
        }

        Class dateClass = Date.class;
        if (dateClass.isInstance(value)) {
            DateTime dateTime = new DateTime(((Date) value).getTime());
            return dateTime.isAfter(DateTime.now().withDurationAdded(before, -1))
                    && dateTime.isBefore(DateTime.now().withDurationAdded(after, 1));
        }

        return false;
    }

    /**
     * 是否匹配正则表达式
     *
     * @param pattern Pattern注解对象
     * @param value   值
     * @return
     */
    public static boolean pattern(Pattern pattern, Object value) {
        return ReUtil.isMatch(pattern.value(), value.toString());
    }

    /**
     * 校验size是否在有效值内
     *
     * @param size  Size注解对象
     * @param value 值
     * @return
     */
    public static boolean decimalSize(DecimalSize size, Object value) {
        String min = size.min();
        String max = size.max();
        Class intClass = Integer.class;
        if (intClass.isInstance(value)) {
            return (int) value >= Integer.valueOf(min) && (int) value <= Integer.valueOf(max);
        }
        Class floatClass = Float.class;
        if (floatClass.isInstance(value)) {
            return (float) value >= Float.valueOf(min) && (float) value <= Float.valueOf(max);
        }
        Class longClass = Long.class;
        if (longClass.isInstance(value)) {
            return (long) value >= Long.valueOf(min) && (long) value <= Long.valueOf(max);
        }

        Class stringClass = String.class;
        if (stringClass.isInstance(value)) {
            if (ReUtil.isMatch("\\d+\\.*\\d*", value.toString())) {
                BigDecimal bigDecimalValue = new BigDecimal(value.toString());
                BigDecimal minValue = new BigDecimal(min);
                BigDecimal maxValue = new BigDecimal(max);
                return bigDecimalValue.compareTo(minValue) >= 0 && bigDecimalValue.compareTo(maxValue) <= 0;
            }
        }

        Class bigDecimalClass = BigDecimal.class;
        if (bigDecimalClass.isInstance(value)) {
            BigDecimal bigDecimalValue = (BigDecimal) value;
            BigDecimal minValue = new BigDecimal(min);
            BigDecimal maxValue = new BigDecimal(max);
            return bigDecimalValue.compareTo(minValue) >= 0 && bigDecimalValue.compareTo(maxValue) <= 0;
        }
        return false;
    }

    /**
     * 校验size是否在有效值内
     *
     * @param size  Size注解对象
     * @param value 值
     * @return
     */
    public static boolean size(Size size, Object value) {
        long min = size.min();
        long max = size.max();
        Class intClass = Integer.class;
        if (intClass.isInstance(value)) {
            return (int) value >= min && (int) value <= max;
        }
        Class floatClass = Float.class;
        if (floatClass.isInstance(value)) {
            return (float) value >= min && (float) value <= max;
        }
        Class longClass = Long.class;
        if (longClass.isInstance(value)) {
            return (long) value >= min && (long) value <= max;
        }
        Class stringClass = String.class;
        if (stringClass.isInstance(value)) {
            return ReUtil.isMatch("\\d+", value.toString())
                    && Long.valueOf(value.toString()) >= min && Long.valueOf(value.toString()) <= max;
        }
        if(List.class.isInstance(value)){
            final int valSize = ((List) value).size();
            return valSize>=min && valSize<=max;
        }
        return false;
    }


//    public static void main(String[] args) {
////        System.out.println(dateAfter(DateUtil.offsetDay(new Date(), 3)) ? "TRUE" : "FALSE");
////        System.out.println(dateAfter(DateUtil.offsetDay(new Date(), -3)) ? "TRUE" : "FALSE");
////        System.out.println(dateAfter("2019-18-06") ? "TRUE" : "FALSE");
////        System.out.println(dateAfter(new Timestamp(1659699422331L)) ? "TRUE" : "FALSE");
////        System.out.println(decimalSize(null, "0000.1111") ? "TRUE" : "FALSE");
////        System.out.println(decimalSize(null, 11) ? "TRUE" : "FALSE");
////        System.out.println(decimalSize(null, 11.1f) ? "TRUE" : "FALSE");
////        System.out.println(decimalSize(null, 11L) ? "TRUE" : "FALSE");
////        System.out.println(decimalSize(null, new BigDecimal("11.111")) ? "TRUE" : "FALSE");
////        DateTimeFormatter dtf = DateTimeFormat.forPattern(YYYY_MM_DD_HH_MM_SS);
////        System.out.println(dtf.parseMillis("2019-06-10 00:00:00"));
////        System.out.println(dtf.parseMillis("2019-12-10 00:00:00"));
////        System.out.println(dateRange(null, 1565907200000L) ? "TRUE" : "FALSE");
////        System.out.println(dateRange(null, "2019-07-01") ? "TRUE" : "FALSE");
////        System.out.println(dateRange(null, new Timestamp(1660096000000L)) ? "TRUE" : "FALSE");
////        System.out.println(dateRange(null, new Date()) ? "TRUE" : "FALSE");
////        System.out.println(dateRangeNow(null, 1565907200000L) ? "TRUE" : "FALSE");
////        System.out.println(dateRangeNow(null, "2019-07-01") ? "TRUE" : "FALSE");
////        System.out.println(dateRangeNow(null, new Timestamp(1660096000000L)) ? "TRUE" : "FALSE");
////        System.out.println(dateRangeNow(null, new Date()) ? "TRUE" : "FALSE");
////        System.out.println(dateRangeNow(null, DateUtil.offsetDay(new Date(), 3)) ? "TRUE" : "FALSE");
//    }

    //    public static boolean assertEnum(Class<?> enumClass, String extValue, Object value) {
//
//        Object[] enumConstants = enumClass.getEnumConstants();
//        if (null != enumConstants && IEnum.class.isAssignableFrom(enumClass)) {
//            for (Object constant : enumConstants) {
//                IEnum enumConstant = (IEnum) constant;
//                if (Objects.equals(enumConstant.value(), value)) {
//                    return true;
//                }
//            }
//        }
//
//        return isExtEqual(extValue, value);
//    }

//    public static void main(String[] args) {
//        Class<?> enumClass = ResourceType.class;
//        String value = "0";
//        if (assertEnum(enumClass, "0", value)) {
//            System.out.println("=============True");
//        } else {
//            System.out.println("=============False");
//        }
//    }
}
