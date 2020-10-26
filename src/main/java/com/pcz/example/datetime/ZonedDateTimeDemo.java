package com.pcz.example.datetime;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author picongzhi
 */
public class ZonedDateTimeDemo {
    public static void main(String[] args) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm, dd MMM yyyy");
        LocalDateTime localDateTime = LocalDateTime.of(2020, Month.OCTOBER, 20, 11, 11);
        System.out.println("localDateTime: " + formatter.format(localDateTime));

        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Kuala_Lumpur"));
        System.out.println(formatter.format(zonedDateTime));

        zonedDateTime = zonedDateTime.withZoneSameInstant(ZoneId.of("Asia/Tokyo")).plusHours(7);
        System.out.println(formatter.format(zonedDateTime));
    }
}
