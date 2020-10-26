package com.pcz.example.datetime;

import java.time.*;
import java.util.Date;

/**
 * @author picongzhi
 */
public class ConvertDateToLocalDateAndLocalDateTimeDemo {
    public static void main(String[] args) {
        dateToLocalDate();
        localDateToDate();
    }

    private static void localDateToDate() {
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("zone: " + zoneId);

        LocalDate localDate = LocalDate.now();
        Date date = Date.from(localDate.atStartOfDay(zoneId).toInstant());
        System.out.println("localDate: " + localDate);
        System.out.println("date: " + date);

        LocalDateTime localDateTime = LocalDateTime.now();
        date = Date.from(localDateTime.atZone(zoneId).toInstant());
        System.out.println("localDateTime: " + localDateTime);
        System.out.println("date: " + date);

        ZonedDateTime zonedDateTime = localDateTime.atZone(zoneId);
        date = Date.from(zonedDateTime.toInstant());
        System.out.println("zonedDateTime: " + zonedDateTime);
        System.out.println("date: " + date);
    }

    private static void dateToLocalDate() {
        ZoneId zoneId = ZoneId.systemDefault();
        System.out.println("zone: " + zoneId);

        Date date = new Date();
        System.out.println("date: " + date);

        Instant instant = date.toInstant();
        System.out.println("instant: " + instant);

        LocalDate localDate = instant.atZone(zoneId).toLocalDate();
        System.out.println("localDate: " + localDate);

        LocalDateTime localDateTime = instant.atZone(zoneId).toLocalDateTime();
        System.out.println("localDateTime: " + localDateTime);

        ZonedDateTime zonedDateTime = instant.atZone(zoneId);
        System.out.println("zonedDateTime: " + zonedDateTime);
    }
}
