package com.ramana.rebelcourse.helper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;

public class DateHelper {

    public static long getCurrentTime() {
        return System.currentTimeMillis();
    }

    public static String getCurrentDateFormatted() {
        return new SimpleDateFormat("MM/dd/yyyy").format(getCurrentTime());
    }

    public static final String DEFAULT_FORMAT = "dd-MM-yyyy";
    public static final String DEFAULT_TIME_FORMAT = "dd-MM-yyyy HH:mm:ss";

    public static Date toDate(String date, String format) {
        if (date != null && !date.isEmpty()) {
            try {
                return new SimpleDateFormat(format, Locale.ENGLISH).parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String toString(Date date, String format) {
        if (date != null) {
            try {
                return new SimpleDateFormat(format, Locale.ENGLISH).format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static String toString(Long datetime, String format) {
        if (datetime != null) {
            try {
                Date date = new Date(datetime);
                return new SimpleDateFormat(format, Locale.ENGLISH).format(date);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static int month() {
        Date date = new Date();
        LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        int month = localDate.getMonthValue();
        return month;
    }

    public static String toString(Long datetime) {
        return toString(datetime, DEFAULT_FORMAT);
    }
}
