package com.fmk.framework.basic;

import org.joda.time.DateTime;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static java.util.Objects.isNull;

public class DateTimeUtil {

    /**
     * 将格式为HH:mm格式的时间转化为字符串"HHmm"格式
     * @param time
     * @return
     */
    public static String getStringByTime(Date time) {

        SimpleDateFormat formatter = new SimpleDateFormat("HHmm");
        String dateString = formatter.format(time);
        return dateString;
   }
//    public static void main(String[] args) throws ParseException, java.text.ParseException {
//
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhh");//yyyyMMddhhmmss
//        String ddy = sdf.format(new Date());
//        long yy = sdf.parse(ddy).getTime();
//        System.out.println("==================>"+yy);
//
//    }

    /**
     *  date类型转换为String类型
     */
    public static String dateToString(Date data, String formatType) {
        return new SimpleDateFormat(formatType).format(data);
    }

    /**
     *   date类型转换为long类型
     */
    public static long dateToLong(Date date,String format) {

        long datelong = 0;

        try{
            SimpleDateFormat sdf = new SimpleDateFormat(format);//yyyyMMddhhmmss
            String ddStr = sdf.format(date);
            datelong = sdf.parse(ddStr).getTime();

        }catch (Exception e){
            e.printStackTrace();
        }
        return datelong;
    }

    /**
     * 比较日期的大小
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {

        if (isNull(date1)) return -1;
        if (isNull(date2)) return 1;

        if (date1.getTime() > date2.getTime()) {
            return 1;
        } else if (date1.getTime() < date2.getTime()) {
            return -1;
        } else {
            return 0;
        }

    }
    public static Timestamp timestamp(long m){
        return new Timestamp(m);
    }
    public static Timestamp timestampNow(){
        return new Timestamp(DateTime.now().getMillis());
    }

}
