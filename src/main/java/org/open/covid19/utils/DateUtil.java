package org.open.covid19.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {
    /**
     * `yyyy-MM-dd`格式转换为UTC时间
     * @param localTime
     * @return
     */
    public static Date localToUTC(String localTime) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date localDate= null;
        try {
            localDate = sdf.parse(localTime);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long localTimeInMillis=localDate.getTime();
        /** long时间转换成Calendar */
        Calendar calendar= Calendar.getInstance();
        calendar.setTimeInMillis(localTimeInMillis);
        /** 取得时间偏移量 */
        int zoneOffset = calendar.get(java.util.Calendar.ZONE_OFFSET);
        /** 取得夏令时差 */
        int dstOffset = calendar.get(java.util.Calendar.DST_OFFSET);
        /** 从本地时间里扣除这些差量，即可以取得UTC时间*/
        calendar.add(java.util.Calendar.MILLISECOND, -(zoneOffset + dstOffset));
        /** 取得的时间就是UTC标准时间 */
        Date utcDate=new Date(calendar.getTimeInMillis());
        return utcDate;
    }

    /**
     * `yyyy-MM-dd`格式时间转换为`yyyy-MM-dd'T'HH:mm:ss'Z'`格式的时间
     * @param dateStr
     * @return
     */
    public static String local2tz(String dateStr) {
        String format = "";
        try {
            Date localDate = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
            format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(localDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return format;
    }

    /**
     * 格式化日期
     * @param date
     * @return
     */
    public static String local2tz(Date date) {
        if (null == date) return "";
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'").format(date);
    }

    /**
     * 格式化日期：yyyy-MM-dd
     * @param date
     * @return
     */
    public static String local2yyyMMdd(Date date){
        if (null == date) return "";
        return new SimpleDateFormat("yyyy-MM-dd").format(date);
    }

    /**
     * 指定日期距离现在几天,不足一天算为0。(取绝对值)。
     * 例如：昨天0点到今天15点算1天
     * @param date
     * @return
     */
    public static int howManyDaysFromNow(Date date) {
        if (null==date) return 0;
        long current = System.currentTimeMillis();
        long past = date.getTime();
        long residue = Math.abs(current - past); // 绝对值
        long days = residue / (1000 * 60 * 60 * 24);
        System.out.println("days:" + days);
        return (int) (residue / (1000 * 60 * 60 * 24));
    }

    /**
     * 字符串转为Date类型
     * @param dateStr
     * @return
     */
    public static Date stringToDate(String dateStr) {
        if(dateStr ==null || "".equals(dateStr)){
            return null;
        }
        Date date = new Date();
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = df.parse(dateStr);
        } catch (ParseException e) {

        }
        return date;
    }
}
