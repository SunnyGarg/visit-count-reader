package com.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.util.UUID;

public class CommonUtils {

    public static DateFormat getDateFormat() {
        DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
        dateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        return dateFormat;
    }

    public static Date getCurrentDate() {
        DateFormat dateFormat = getDateFormat();
        String currentDate = dateFormat.format(new Date());
        try {
            return dateFormat.parse(currentDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String convertDateToYearAndMonth(Date date) {
        return new SimpleDateFormat(Constants.SIMPLE_DATE_FORMAT).format(date);
    }
}
