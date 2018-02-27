package com.admin.util;

import org.apache.commons.lang3.time.DateUtils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    public static String dateFormatYYYYMMDDHHMM(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return format.format(date);
    }


    public static String dateFormatYYYYMMDDHH(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH");
        return format.format(date);
    }

    public static String dateFormatAll(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return format.format(date);
    }

    public static Date stringFormatDate(String string)  {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date date = null;
            // Fri Feb 24 00:00:00 CST 2012
            date = format.parse(string);
            return date;
        }catch (Exception e)
        {
            return null;
        }
    }

    public static Date stringFormatYYYYMMdd(String string) {
        try {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            Date date = null;
            // Fri Feb 24 00:00:00 CST 2012
            date = format.parse(string);

            return date;
        }catch (Exception e)
        {
            return null;
        }
    }


    /*
     * 将时间转换为时间戳
     */
    public static String dateToStamp(String s) throws ParseException{
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        res = String.valueOf(ts);
        return res;
    }


    /*
       * 将时间戳转换为时间
       */
    public static String stampToDate(String s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lt = new Long(s);
        Date date = new Date(lt);
        res = simpleDateFormat.format(date);
        return res;
    }

    public static String friendlyDate(Date date) {
        Date now = new Date();
        long ys = DateUtils.truncate(now, Calendar.YEAR).getTime();
        long ds = DateUtils.truncate(now, Calendar.DAY_OF_MONTH).getTime();
        long yd = DateUtils.truncate(date, Calendar.DAY_OF_MONTH).getTime();

        long n = now.getTime();

        long e = date.getTime();
        if (e < ys) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        }
        if ((ds - yd) == (24 * 60 * 60 * 1000)) {
            return new SimpleDateFormat("昨天  HH:mm").format(date);
        }
        if (e < ds) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
        }
        if (n - e > 60 * 60 * 1000) {
            return new SimpleDateFormat("今天  HH:mm").format(date);
        }
        if (n - e > 60 * 1000) {
            return (long) Math.floor((n - e) * 1d / 60000) + "分钟前";
        }
        if (n - e > 1 * 1000) {
            return "刚刚";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm").format(date);
    }


}
