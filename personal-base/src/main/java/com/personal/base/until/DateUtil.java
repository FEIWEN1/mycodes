package com.personal.base.until;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;


/**
 * TODO:日期工具类
 * 
 * @author wangfei_nbc
 * @version 1.0, 2017年8月28日/下午5:48:29
 * 
 */
public class DateUtil {

    private static final String DEFAULT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    public static String getDate(String interval, Date starttime, String pattern) {
        Calendar temp = Calendar.getInstance(TimeZone.getDefault());
        temp.setTime(starttime);
        temp.add(Calendar.MONTH, Integer.parseInt(interval));
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        return sdf.format(temp.getTime());
    }

    /**
     * 将字符串类型转换为时间类型
     * 
     * @return
     */
    public static Date str2Date(String str) {
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
        try {
            d = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 返回当前时间的时间戳
     * @param str
     * @return
     */
    public static Long str2DateLong(String str) {
        Date date = str2Date(str);
        Calendar temp = Calendar.getInstance();
        temp.setTime(date);
        return temp.getTimeInMillis();
    }

    /**
     * TODO:将时间字符串转换为date
     * 
     * @param str 时间字符串
     * @param pattern  时间格式 如:yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static Date str2Date(String str, String pattern) {
        Date d = null;
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            d = sdf.parse(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return d;
    }

    /**
     * 将时间格式化
     * 
     * @return
     */
    public static Date DatePattern(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
        try {
            String dd = sdf.format(date);
            date = str2Date(dd);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * 将时间格式化
     */
    public static Date DatePattern(Date date, String pattern) {
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        try {
            String dd = sdf.format(date);
            date = str2Date(dd, pattern);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return date;
    }

    /**
     * TODO：将date类型时间转换为字符串
     * 
     * @param date  时间
     * @return 时间字符串
     */
    public static String date2Str(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat(DEFAULT_PATTERN);
        return sdf.format(date);
    }

    /**
     * TODO:将date类型的时间转换为字符串
     * 
     * @param date date类型的时间
     * @param format 时间格式
     * @return
     */
    public static String date2Str(Date date, String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(date);
    }

    /**
     * 获取昨天
     * 
     * @param date
     * @return
     * @throws Exception
     */
    public static Date getLastDate(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(date);

        calendar.add(Calendar.DATE, -1);

        return str2Date(date2Str(calendar.getTime()));
    }

    /**
     * 获取前几天
     * @param date
     * @return
     */
    public static Date getBeforeDate(Date date, int dates) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(date);

        calendar.add(Calendar.DATE, -dates);

        return str2Date(date2Str(calendar.getTime()));
    }

    /**
     * 获取上周第一天（周一）
     * 
     * @param date
     * @return
     * @throws Exception
     */
    public static Date getLastWeekStart(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int startnum = 0;
        if (i == 0) {
            startnum = 7 + 6;
        } else {
            startnum = 7 + i - 1;
        }
        calendar.add(Calendar.DATE, -startnum);

        return str2Date(date2Str(calendar.getTime()));
    }

    /**
     * 获取上周最后一天（周末）
     * 
     * @param date
     * @return
     * @throws Exception
     */
    public static Date getLastWeekEnd(Date date) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        calendar.setTime(date);
        int i = calendar.get(Calendar.DAY_OF_WEEK) - 1;
        int endnum = 0;
        if (i == 0) {
            endnum = 7;
        } else {
            endnum = i;
        }
        calendar.add(Calendar.DATE, -(endnum - 1));

        return str2Date(date2Str(calendar.getTime()));
    }

    /**
     * 改更现在时间
     */
    public static Date changeDate(String type, int value) {
        Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
        if (type.equals("month")) {
            calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + value);
        } else if (type.equals("date")) {
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + value);
        }
        return calendar.getTime();
    }

    /**
     * 更改时间
     */
    public static Date changeDate(Date date, String type, int value) {
        if (date != null) {
            // Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(date);
            // Calendar calendar = Calendar.
            if (type.equals("month")) {
                calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) + value);
            } else if (type.equals("date")) {
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) + value);
            } else if (type.endsWith("year")) {
                calendar.set(Calendar.YEAR, calendar.get(Calendar.YEAR) + value);
            }
            return calendar.getTime();
        }
        return null;
    }


    /**
     * 获取interval天之前的日期
     * 
     * @param interval
     * @param starttime
     * @param pattern
     * @return
     */
    public static Date getIntervalDate(String interval, Date starttime, String pattern) {
        Calendar temp = Calendar.getInstance();
        temp.setTime(starttime);
        temp.add(Calendar.DATE, Integer.parseInt(interval));
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String shijian = sdf.format(temp.getTime());
        return str2Date(shijian);
    }

}
