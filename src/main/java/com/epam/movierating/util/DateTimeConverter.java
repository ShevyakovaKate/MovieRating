package com.epam.movierating.util;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class DateTimeConverter {
    /*private static final Logger logger = LogManager.getLogger(DateTimeConverter.class.getName());*/

    private static final SimpleDateFormat SDF_DATETIME = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    private static final SimpleDateFormat SDF_DATE = new SimpleDateFormat("yyyy-MM-dd");

    public static LocalDate convertStringToDate(String dateTime) {
        LocalDate localDate;
        try{
            Date date = SDF_DATE.parse(dateTime);
            ZoneId defaultZoneId = ZoneId.systemDefault();
            Instant instant = date.toInstant();
            localDate = instant.atZone(defaultZoneId).toLocalDate();
        }
        catch(ParseException e) {
            /*logger.log(Level.FATAL, "datetime: not correct input parameter" );*/
            throw new IllegalArgumentException("datetime: not correct input parameter");
        }
        return localDate;
    }

    public static Date convertStringToDateTime(String date) {
        Date orderDate;
        try{
            orderDate = SDF_DATETIME.parse(date);
        }
        catch(ParseException e){
           /* logger.log(Level.FATAL, "date: not correct input parameter" );*/
            throw new IllegalArgumentException("date: not correct input parameter");
        }

        return orderDate;
    }

    public static String convertDateToString(Date date) {
        return SDF_DATE.format(date);
    }

    public static String convertDateTimeToString(Date date) {
        return SDF_DATETIME.format(date);
    }

    public static java.sql.Date convertUtilToSql(Date uDate) {


        return new java.sql.Date(uDate.getTime());
    }

}