package com.bsyun.xuanxueapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    //    public static final String patterm = "yyyy-MM-dd HH:mm:ss";
    public static final String patterm = "yyyy年MM月dd日 HH时mm分";
//    public static final String patterm = "yyyy年MM月dd日";;
// 1，日期格式：String dateString = "2017-06-20 10:30:30" 对应的格式：String pattern = "yyyy-MM-dd HH:mm:ss";
// 2，日期格式：String dateString = "2017-06-20" 对应的格式：String pattern = "yyyy-MM-dd";
// 3，日期格式：String dateString = "2017年06月20日 10时30分30秒 对应的格式：String pattern = "yyyy年MM月dd日 HH时mm分ss秒";
// 4，日期格式：String dateString = "2017年06月20日" 对应的格式：String pattern = "yyyy年MM月dd日";

    /**
     * 获取系统时间戳
     *
     * @return
     */
    public static long getCurTimeLong() {
        return System.currentTimeMillis();
    }

    /**
     * 获取当前时间
     *
     * @param pattern
     * @return
     */
    public static String getCurDate(String pattern) {
        SimpleDateFormat sDateFormat = new SimpleDateFormat(pattern);
        return sDateFormat.format(new Date());
    }


    public static String getDateToString(long data) {
        SimpleDateFormat format = new SimpleDateFormat(patterm);
        return format.format(data);
    }

    /**
     * 将字符串转为时间戳
     *
     * @param dateString
     * @return
     */
    public static long getStringToDate(String dateString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date();
        try {
            date = dateFormat.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.getTime();
    }


    public static int getYearByTimeStamp(long timeStamp) {
        String date = timeStampToDate(timeStamp);
        String year = date.substring(0, 4);
        return Integer.parseInt(year);
    }

    public static int getMonthByTimeStamp(long timeStamp) {
        String date = timeStampToDate(timeStamp);
        String month = date.substring(5, 7);
        return Integer.parseInt(month);
    }

    public static int getDayByTimeStamp(long timeStamp) {
        String date = timeStampToDate(timeStamp);
        String day = date.substring(8, 10);
        return Integer.parseInt(day);
    }

    public static int getHourByTimeStamp(long timeStamp) {
        String date = timeStampToDate(timeStamp);
        String hour = date.substring(11, 13);
        return Integer.parseInt(hour);
    }

    public static int getMinuteByTimeStamp(long timeStamp) {
        String date = timeStampToDate(timeStamp);
        String hour = date.substring(14, 16);
        return Integer.parseInt(hour);
    }

    public static int getSecondByTimeStamp(long timeStamp) {
        String date = timeStampToDate(timeStamp);
        String hour = date.substring(date.length() - 2);
        return Integer.parseInt(hour);
    }

    public static String timeStampToDate(long timeStamp) {
        Date date = new Date(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }

    public static String getDataString(long timeStamp) {
        Date date = new Date(timeStamp);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss");
        String dateStr = simpleDateFormat.format(date);
        return dateStr;
    }
}
