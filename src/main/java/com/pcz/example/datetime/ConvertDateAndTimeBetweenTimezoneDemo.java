package com.pcz.example.datetime;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author picongzhi
 */
public class ConvertDateAndTimeBetweenTimezoneDemo {
    private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss a";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_FORMAT, Locale.US);
    private static final SimpleDateFormat SIMPLE_DATE_FORMAT = new SimpleDateFormat(DATE_FORMAT);

    public static void main(String[] args) throws ParseException {
        betweenZonedDateTime();
        betweenDate();
        betweenCalendar();
        betweenJodaTime();
    }

    private static void betweenJodaTime() {
        Date date = new Date();
        TimeZone timeZone = TimeZone.getDefault();
        System.out.println(timeZone.getID() + " - " + timeZone.getDisplayName());

        DateTime dateTime = new DateTime(date);
        System.out.println(dateTime);

        DateTimeZone dateTimeZone = DateTimeZone.forID("America/New_York");
        DateTime nyDateTime = dateTime.withZone(dateTimeZone);
        System.out.println(dateTimeZone);
        System.out.println(nyDateTime);

        TimeZone nyTimeZone = dateTimeZone.toTimeZone();

        Date nyDate = nyDateTime.toLocalDateTime().toDate();
        SimpleDateFormat nySimpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        nySimpleDateFormat.setTimeZone(nyTimeZone);
        System.out.println(nyDate);
        System.out.println(nySimpleDateFormat.format(nyDate));
    }

    private static void betweenCalendar() {
        Date date = new Date();
        TimeZone timeZone = TimeZone.getDefault();
        System.out.println(timeZone.getID() + " - " + timeZone.getDisplayName());

        SimpleDateFormat nyDateFormat = new SimpleDateFormat(DATE_FORMAT);
        TimeZone nyTimeZone = TimeZone.getTimeZone("America/New_York");
        System.out.println(nyTimeZone.getID() + " - " + nyTimeZone.getDisplayName());
        nyDateFormat.setTimeZone(nyTimeZone);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.setTimeZone(nyTimeZone);
        System.out.println(nyDateFormat.format(calendar.getTime()));

        System.out.println(calendar.get(Calendar.YEAR));
        System.out.println(calendar.get(Calendar.MONTH));
        System.out.println(calendar.get(Calendar.DAY_OF_MONTH));
        System.out.println(calendar.get(Calendar.HOUR));
        System.out.println(calendar.get(Calendar.HOUR_OF_DAY));
        System.out.println(calendar.get(Calendar.MINUTE));
        System.out.println(calendar.get(Calendar.SECOND));
        System.out.println(calendar.get(Calendar.AM_PM));
    }

    private static void betweenDate() throws ParseException {
        Date date = new Date();
        TimeZone timeZone = TimeZone.getDefault();
        System.out.println(timeZone.getID() + " - " + timeZone.getDisplayName());
        System.out.println(SIMPLE_DATE_FORMAT.format(date));

        TimeZone nyTimeZone = TimeZone.getTimeZone("America/New_York");
        System.out.println(nyTimeZone.getID() + " - " + nyTimeZone.getDisplayName());

        SimpleDateFormat nySimpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        nySimpleDateFormat.setTimeZone(nyTimeZone);

        String nyDateStr = nySimpleDateFormat.format(date);
        System.out.println(nyDateStr);

        Date nyDate = SIMPLE_DATE_FORMAT.parse(nyDateStr);
        System.out.println(nyDate);
    }

    private static void betweenZonedDateTime() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        ZoneId singaporeZoneId = ZoneId.of("Asia/Singapore");
        System.out.println(singaporeZoneId);

        ZonedDateTime asiaZonedDateTime = localDateTime.atZone(singaporeZoneId);
        System.out.println(asiaZonedDateTime);

        ZoneId newYorkZoneId = ZoneId.of("America/New_York");
        System.out.println(newYorkZoneId);
        ZonedDateTime nyDateTime = asiaZonedDateTime.withZoneSameInstant(newYorkZoneId);
        System.out.println(nyDateTime);

        System.out.println(DATE_TIME_FORMATTER.format(asiaZonedDateTime));
        System.out.println(DATE_TIME_FORMATTER.format(nyDateTime));
    }
}
