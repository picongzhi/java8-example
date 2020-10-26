package com.pcz.example.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author picongzhi
 */
public class AddDaysDemo {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

    public static void main(String[] args) {
        calendarAdd();
        java8PlusMinus();
    }

    private static void java8PlusMinus() {
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date));

        LocalDateTime localDateTime = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        System.out.println(localDateTime);

        localDateTime = localDateTime
                .plusYears(1)
                .plusMonths(1)
                .plusDays(1)
                .plusHours(1)
                .plusMinutes(1)
                .plusSeconds(1);
        System.out.println(Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant()));
    }

    private static void calendarAdd() {
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        System.out.println(calendar.getTime());

        calendar.add(Calendar.YEAR, 1);
        calendar.add(Calendar.MONTH, 1);
        calendar.add(Calendar.DATE, 1);
        calendar.add(Calendar.HOUR, 1);
        calendar.add(Calendar.MINUTE, 1);
        calendar.add(Calendar.SECOND, 1);
        System.out.println(calendar.getTime());
    }
}
