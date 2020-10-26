package com.pcz.example.datetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * @author picongzhi
 */
public class ConvertInstantToZonedDateTimeDemo {
    public static void main(String[] args) {
        instantToZonedDateTime();
        zonedDateTimeToInstant();
    }

    private static void instantToZonedDateTime() {
        Instant instant = Instant.now();
        System.out.println(instant);

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Asia/Tokyo"));
        System.out.println(zonedDateTime);
        System.out.println(zonedDateTime.getOffset());
    }

    private static void zonedDateTimeToInstant() {
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Tokyo"));
        System.out.println(zonedDateTime);

        Instant instant = zonedDateTime.toInstant();
        System.out.println(instant);
    }
}
