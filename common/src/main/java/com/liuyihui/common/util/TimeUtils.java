package com.liuyihui.common.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by gaowen on 2017/7/6.
 */

public class TimeUtils {

    private static final int DAY = 86400000;

    /**
     * 秒与毫秒的倍数
     */
    public static final int SEC = 1000;

    /**
     * 将时间戳转为时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param millis 毫秒时间戳
     * @return 时间字符串
     */
    public static String millis2String(final long millis) {
        DateFormat DEFAULT_FORMAT =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return millis2String(millis, DEFAULT_FORMAT);
    }

    /**
     * 将时间戳转为时间字符串
     * <p>格式为format</p>
     *
     * @param millis 毫秒时间戳
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String millis2String(final long millis, final DateFormat format) {
        return format.format(new Date(millis));
    }

    /**
     * 获取当前时间字符串
     * <p>格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @return 时间字符串
     */
    public static String getNowString() {
        DateFormat DEFAULT_FORMAT =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return millis2String(System.currentTimeMillis(), DEFAULT_FORMAT);
    }

    /**
     * 获取当前时间字符串
     * <p>格式为format</p>
     *
     * @param format 时间格式
     * @return 时间字符串
     */
    public static String getNowString(final DateFormat format) {
        return millis2String(System.currentTimeMillis(), format);
    }

    /**
     * 将时间字符串转为时间戳
     * <p>time格式为yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return 毫秒时间戳
     */
    public static long string2Millis(final String time) {
        DateFormat DEFAULT_FORMAT =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return string2Millis(time, DEFAULT_FORMAT);
    }

    /**
     * 将时间字符串转为时间戳
     * <p>time格式为format</p>
     *
     * @param time 时间字符串
     * @param format 时间格式
     * @return 毫秒时间戳
     */
    public static long string2Millis(final String time, final DateFormat format) {
        long timeMillis = -1;
        try {
            timeMillis = format.parse(time).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return timeMillis;
    }

    public static long getTimeSpan(final String time) {
        long time0 = getMenuStartTime("00:00:00");
        long time1 = getMenuStartTime(time);
        return time1 - time0;
    }

    private static long millis2TimeSpan(final long millis, final int unit) {
        return millis / unit;
    }

    public static String getMenuTime(long menuTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return millis2String(calendar.getTimeInMillis() + menuTime * SEC);
    }

    public static String getMenuTime(long menuTime, final DateFormat format) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        return millis2String(calendar.getTimeInMillis() + menuTime * SEC, format);
    }

    public static long getMenuStartTime(String start) {
        return getMenuStartTime(start, SEC);
    }

    public static long getMenuStartTime(String start, int unit) {
        DateFormat TIME_DEFAULT_FORMAT =
                new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
        return string2Millis(start, TIME_DEFAULT_FORMAT) / unit;
    }

    public static boolean isToday(final String time) {
        DateFormat DEFAULT_FORMAT =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return isToday(string2Millis(time, DEFAULT_FORMAT));
    }

    /**
     * 判断是否今天
     *
     * @param millis 毫秒时间戳
     * @return {@code true}: 是<br>{@code false}: 否
     */
    public static boolean isToday(final long millis) {
        long wee = getWeeOfToday();
        return millis >= wee && millis < wee + DAY;
    }

    private static long getWeeOfToday() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTimeInMillis();
    }

    /**
     * 将时间字符串转为 Date 类型
     * <p>time 格式为 yyyy-MM-dd HH:mm:ss</p>
     *
     * @param time 时间字符串
     * @return Date 类型
     */
    public static Date string2Date(final String time) {
        DateFormat DEFAULT_FORMAT =
                new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        return string2Date(time, DEFAULT_FORMAT);
    }

    /**
     * 将时间字符串转为 Date 类型
     * <p>time 格式为 format</p>
     *
     * @param time   时间字符串
     * @param format 时间格式
     * @return Date 类型
     */
    public static Date string2Date(final String time, final DateFormat format) {
        try {
            return format.parse(time);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
