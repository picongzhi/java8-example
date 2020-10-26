package com.pcz.example.datetime;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Locale;

/**
 * @author picongzhi
 */
public class ConvertStringToLocalDateDemo {
    public static void main(String[] args) {
        parseISOLocalDate();
        parseWithLocale();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
        String dateStr = "10/10/2020";
        printLocalDate(formatter, dateStr);

        formatter = DateTimeFormatter.ofPattern("E, MMM d yyyy", Locale.US);
        dateStr = "Tue, Aug 16 2016";
        printLocalDate(formatter, dateStr);

        formatter = DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy hh:mm:ss a", Locale.US);
        dateStr = "Tuesday, Aug 16, 2016 12:10:56 PM";
        printLocalDateTime(formatter, dateStr);

        dateStr = "2020-10-10T15:23:01Z";
        Instant instant = Instant.parse(dateStr);
        System.out.println("instance: " + instant);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
        System.out.println("localDate: " + localDateTime.toLocalDate());

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Tokyo"));
        System.out.println(zonedDateTime);

        dateStr = "2016-08-16T10:15:30+08:00";
        zonedDateTime = ZonedDateTime.parse(dateStr, DateTimeFormatter.ISO_DATE_TIME);
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.getZone());
        System.out.println(zonedDateTime.toLocalDate());
    }

    private static void printLocalDateTime(DateTimeFormatter formatter, String dateStr) {
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, formatter);
        System.out.println(localDateTime);
        System.out.println(formatter.format(localDateTime));
    }

    private static void printLocalDate(DateTimeFormatter formatter, String dateStr) {
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        System.out.println(localDate);
        System.out.println(formatter.format(localDate));
    }

    private static void parseWithLocale() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy");
        String dateStr = "10-Aug-2020";
        try {
            printLocalDate(formatter, dateStr);
        } catch (DateTimeParseException e) {
            e.printStackTrace();
        }

        formatter = DateTimeFormatter.ofPattern("d-MMM-yyyy", Locale.US);
        printLocalDate(formatter, dateStr);
    }

    private static void parseISOLocalDate() {
        String dateStr = "2020-10-10";
        System.out.println(LocalDate.parse(dateStr));
    }
}
