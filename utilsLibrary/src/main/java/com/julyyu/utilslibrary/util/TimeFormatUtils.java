package com.julyyu.utilslibrary.util;

import android.content.Context;
import android.support.annotation.NonNull;
import android.text.format.DateUtils;

import org.joda.time.DateTime;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.Locale;

/**
 * Created by JulyYu on 2016/8/27.
 * 时间格式化工具
 */
public class TimeFormatUtils {

    public static String long2StringTime(@NonNull Long time, SimpleDateFormat dateFormat) {
        if (time == null) {
            return "";
        }
        try {
            return dateFormat.format(time);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return "";
    }

    public static String long2StringIntervalTime(Context context, Long start, Long end) {
        Formatter formatter = new Formatter();
        Formatter times = DateUtils.formatDateRange(context,
                formatter, start, end, DateUtils.FORMAT_SHOW_YEAR | DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_TIME, Locale.ENGLISH.getCountry());
        return times.toString();
    }

    /**
     * 开始时间和结束时间的间隔时间
     */
    public static String setTimeInterval(long endTime, long startTime) {
        long   intervalTime = endTime - startTime + 10000;
        long   days         = intervalTime / (24 * 3600 * 1000);
        long   allMinutes   = intervalTime % (24 * 3600 * 1000);
        long   hours        = allMinutes / (3600 * 1000);
        long   minutes      = allMinutes % (3600 * 1000) / 60000;
        String timeDisplay  = "";
        if (days > 0) {
            timeDisplay += (int) days + "" + "天";
        }
        if (hours > 0) {
            timeDisplay += (int) hours + "" + "小时";
        }
        if (minutes > 0) {
            timeDisplay += (int) minutes + "" + "分钟";
        }
        return timeDisplay;
    }

    /**
     * 开始时间和结束时间显示
     */
    public static String getStartEndTimeFormat(long startTime, long overTime) {

        Calendar beginTime = Calendar.getInstance();
        Calendar endTime   = Calendar.getInstance();
        beginTime.setTimeInMillis(startTime);
        endTime.setTimeInMillis(overTime);
        int mIntBeginYear    = beginTime.get(Calendar.YEAR);
        int mIntBeginYearDay = beginTime.get(Calendar.DAY_OF_YEAR);
        int mIntEndYear      = endTime.get(Calendar.YEAR);
        int mIntEndYearDay   = endTime.get(Calendar.DAY_OF_YEAR);

        SimpleDateFormat OverOneDayFormat = new SimpleDateFormat("M/d HH:mm");
        SimpleDateFormat OneDayFormat     = new SimpleDateFormat("HH:mm");
        //设置时间显示格式 一天以上 或 一天
//        if ((mIntBeginYearDay != mIntEndYearDay) && (mIntBeginYear != mIntEndYear)) {
//            String sBeginTime = OverOneDayFormat.format(startTime);
//            String sOverTime  = OverOneDayFormat.format(overTime);
//            return sBeginTime + "~" + sOverTime;
//        } else {
//            String sBeginTime = OverOneDayFormat.format(startTime);
//            String sOverTime  = OneDayFormat.format(overTime);
//            return sBeginTime + "~" + sOverTime;
//        }

        String sBeginTime = OverOneDayFormat.format(startTime);
        String sOverTime  = OverOneDayFormat.format(overTime);
        return sBeginTime + "~" + sOverTime;
    }

    /**
     * 开始时间和结束时间显示
     */
    public static String getStartEndTimeFormat(long time, long startTime, long overTime) {

        DateTime thatTime  = new DateTime(time);
        DateTime beginTime = new DateTime(startTime);
        DateTime endTime   = new DateTime(overTime);

        int mIntThatYear     = thatTime.getYear();
        int mIntThatYearDay  = thatTime.getDayOfYear();
        int mIntBeginYear    = beginTime.getYear();
        int mIntBeginYearDay = beginTime.getDayOfYear();
        int mIntEndYear      = endTime.getYear();
        int mIntEndYearDay   = endTime.getDayOfYear();

        SimpleDateFormat OverOneDayFormat = new SimpleDateFormat("MM/dd HH:mm");
        SimpleDateFormat OneDayFormat     = new SimpleDateFormat("HH:mm");
        if ((mIntThatYearDay == mIntBeginYearDay) && (mIntThatYear == mIntBeginYear) && (mIntThatYearDay == mIntEndYearDay) && (mIntThatYear == mIntEndYear)) {
            String sBeginTime = OneDayFormat.format(startTime);
            String sOverTime  = OneDayFormat.format(overTime);
            return sBeginTime + "~" + sOverTime;
        } else {
            String sBeginTime = OverOneDayFormat.format(startTime);
            String sOverTime  = OverOneDayFormat.format(overTime);
            return sBeginTime + "~" + sOverTime;
        }
//        if((mIntThatYearDay != mIntBeginYearDay) || (mIntThatYear != mIntBeginYear)){
//            //设置时间显示格式 一天以上 或 一天
//            if ((mIntThatYearDay != mIntEndYearDay) || (mIntThatYear != mIntEndYear)) {
//                String sOverTime  = OverOneDayFormat.format(overTime);
//                return "00:00" + "~" + sOverTime;
//            } else {
//                String sOverTime  = OneDayFormat.format(overTime);
//                return "00:00" + "~" + sOverTime;
//            }
//        }else {
//            //设置时间显示格式 一天以上 或 一天
//            if ((mIntBeginYearDay != mIntEndYearDay) || (mIntBeginYear != mIntEndYear)) {
//                String sBeginTime = OneDayFormat.format(startTime);
//                String sOverTime  = OverOneDayFormat.format(overTime);
//                return sBeginTime + "~" + sOverTime;
//            } else {
//                String sBeginTime = OneDayFormat.format(startTime);
//                String sOverTime  = OneDayFormat.format(overTime);
//                return sBeginTime + "~" + sOverTime;
//            }
//        }
    }

    /**
     * 开始时间和结束时间显示
     */
    public static String getStartEndTimeTakeYearFormat(long startTime, long overTime) {
        Calendar beginTime = Calendar.getInstance();
        Calendar endTime   = Calendar.getInstance();
        beginTime.setTimeInMillis(startTime);
        endTime.setTimeInMillis(overTime);
        int mIntBeginYear    = beginTime.get(Calendar.YEAR);
        int mIntBeginYearDay = beginTime.get(Calendar.DAY_OF_YEAR);
        int mIntEndYear      = endTime.get(Calendar.YEAR);
        int mIntEndYearDay   = endTime.get(Calendar.DAY_OF_YEAR);

        SimpleDateFormat OverOneDayFormat = new SimpleDateFormat("MM/dd HH:mm");
        SimpleDateFormat OneDayFormat     = new SimpleDateFormat("HH:mm");
        //设置时间显示格式 一天以上 或 一天
        if ((mIntBeginYearDay != mIntEndYearDay) && (mIntBeginYear != mIntEndYear)) {
            String sBeginTime = OverOneDayFormat.format(startTime);
            String sOverTime  = OverOneDayFormat.format(overTime);
            return sBeginTime + "~" + sOverTime;
        } else {
            String sBeginTime = OverOneDayFormat.format(startTime);
            String sOverTime  = OneDayFormat.format(overTime);
            return sBeginTime + "~" + sOverTime;
        }
    }

    /**
     * 开始时间和结束时间显示
     */
    public static String getStartEndTimeTakeYearFormat(long startTime, long overTime, String inter) {
        Calendar beginTime = Calendar.getInstance();
        Calendar endTime   = Calendar.getInstance();
        beginTime.setTimeInMillis(startTime);
        endTime.setTimeInMillis(overTime);
        int mIntBeginYear    = beginTime.get(Calendar.YEAR);
        int mIntBeginYearDay = beginTime.get(Calendar.DAY_OF_YEAR);
        int mIntEndYear      = endTime.get(Calendar.YEAR);
        int mIntEndYearDay   = endTime.get(Calendar.DAY_OF_YEAR);

        SimpleDateFormat OverOneDayFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm");
        SimpleDateFormat OneDayFormat     = new SimpleDateFormat("HH:mm");
        //设置时间显示格式 一天以上 或 一天
        if ((mIntBeginYearDay != mIntEndYearDay) || (mIntBeginYear != mIntEndYear)) {
            String sBeginTime = OverOneDayFormat.format(startTime);
            String sOverTime  = OverOneDayFormat.format(overTime);
            return sBeginTime + inter + sOverTime;
        } else {
            String sBeginTime = OverOneDayFormat.format(startTime);
            String sOverTime  = OneDayFormat.format(overTime);
            return sBeginTime + inter + sOverTime;
        }
    }

}
