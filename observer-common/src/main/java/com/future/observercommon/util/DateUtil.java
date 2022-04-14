package com.future.observercommon.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期工具类
 */
public final class DateUtil {

    /**
     * 将Date转换为String
     */
    public static String toString(Date date, String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);
        return df.format(date);
    }

    /**
     * 将Date转换为String，默认格式为yyyy-MM-dd HH-mm-ss
     */
    public static String toString(Date date) {
        return toString(date, "yyyy-MM-dd HH-mm-ss");
    }

    /**
     * 获取当前时间
     */
    public static String getNow() {
        return toString(new Date());
    }

    /**
     * 将String转换为Date
     */
    public static Date toDate(String s, String pattern) throws ParseException {
        DateFormat df = new SimpleDateFormat(pattern);
        return df.parse(s);
    }

    /**
     * 将String转换为Date，默认格式为 yyyy-MM-dd HH-mm-ss
     */
    public static Date toDate(String s) throws ParseException {
        return toDate(s, "yyyy-MM-dd HH-mm-ss");
    }
}

