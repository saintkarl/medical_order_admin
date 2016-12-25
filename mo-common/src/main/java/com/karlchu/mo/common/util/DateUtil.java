package com.karlchu.mo.common.util;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created with IntelliJ IDEA.
 * User: TriLe
 * Date: 5/25/16
 * Time: 2:33 PM
 * To change this template use File | Settings | File Templates.
 */
public class DateUtil {

    private static final long TZ_MS_OFFSET = TimeZone.getDefault().getRawOffset();
    public static long HOUR = 3600000L;
    public static Date getUCTDate(Date date) {
        return new Date(getUCTMilliSeconds(date.getTime()));
    }

    public static long getUCTMilliSeconds(long milliSeconds) {
        return milliSeconds + TZ_MS_OFFSET;
    }

    public static Date truncateMonthUTC(Date date) {
        Date truncateDate = DateUtils.truncate(date, Calendar.MONTH);
        return getUCTDate(truncateDate);
    }

    public static String date2String(Date input, String format) throws IllegalArgumentException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.format(input);
    }

    public static Date string2Date(String input, String format) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        return formatter.parse(input);
    }

    public static Timestamp move2TheEndOfDay(Timestamp input) {
        Timestamp res = null;
        if (input != null) {
            res = new Timestamp(input.getTime() + 24 * HOUR - 1000);
        }
        return res;
    }

    public static Timestamp move2EndTimeOfDay(Timestamp input) {
        Timestamp res = null;
        if (input != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(input.getTime());
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            res = new Timestamp(cal.getTimeInMillis());
        }
        return res;
    }

    public static Date move2EndTimeOfDay(Date input) {
        Date res = null;
        if (input != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(input);
            cal.set(Calendar.HOUR_OF_DAY, 23);
            cal.set(Calendar.MINUTE, 59);
            cal.set(Calendar.SECOND, 59);
            cal.set(Calendar.MILLISECOND, 999);
            res = cal.getTime();
        }
        return res;
    }

    public static Date move2BeginTimeOfDay(Date input) {
        Date res = null;
        if (input != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTime(input);
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            res = cal.getTime();
        }
        return res;
    }

    public static Timestamp move2BeginTimeOfDay(Timestamp input) {
        Timestamp res = null;
        if (input != null) {
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(input.getTime());
            cal.set(Calendar.HOUR_OF_DAY, 0);
            cal.set(Calendar.MINUTE, 0);
            cal.set(Calendar.SECOND, 0);
            cal.set(Calendar.MILLISECOND, 0);
            res = new Timestamp(cal.getTimeInMillis());
        }
        return res;
    }

    public static boolean validateDateFormatMMDDYYYY(String input) {
        boolean res = false;
        if (StringUtils.isNotBlank(input)) {
            String[] temp = input.split("/");
            if (temp.length == 3) {
                try{
                    int month = Integer.parseInt(temp[0]);
                    int day = Integer.parseInt(temp[1]);
                    int year = Integer.parseInt(temp[2]);

                    if (month >= 1 && month <= 12) {
                        if (year >= 1900) {
                            res = isValidDay(day, month, year);
                        }
                    }
                }catch (Exception e) {
                    res = false;
                }
            }
        }
        return res;
    }
    public static boolean isLeapYear(int year) {
        if (year % 4 == 0 && year % 100 != 0) {
            return true;
        }
        return false;
    }
    private static boolean isValidDay(int day, int month, int year) {
        if (day < 1) {
            return false;
        }
        if (day > 31) return false;
        if (month == 4 || month == 6 || month  == 9 || month == 11) {
            if (day > 30) {
                return false;
            }
        }
        if (month == 2) {
            if (isLeapYear(year)) {
                if (day > 29) {
                    return false;
                }
            }else{
                if (day > 28) {
                    return false;
                }
            }
        }
        return true;
    }

    public static long truncateTime(Date date) {
        try{
            return DateUtil.string2Date(date2String(date, "yyyyMMdd"), "yyyyMMdd").getTime();
        }catch (Exception e) {
            return System.currentTimeMillis();
        }
    }

    public static Date yesterday() {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.add(Calendar.DATE, -1); //minus number would decrement the days
        return cal.getTime();
    }

    public static Date today() {
        Date now = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(now);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }
}
