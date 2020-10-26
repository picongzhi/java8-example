package com.pcz.example.datetime;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.ZoneOffset;

/**
 * @author picongzhi
 */
public class ConvertInstantToLocalDateTimeDemo {
    public static void main(String[] args) {
        instantToLocalDateTime();
        localDateTimeToInstant();
    }

    private static void localDateTimeToInstant() {
        LocalDateTime localDateTime = LocalDateTime.now();
        System.out.println(localDateTime);

        Instant instant = localDateTime.toInstant(ZoneOffset.UTC);
        System.out.println(instant);
    }

    private static void instantToLocalDateTime() {
        Instant instant = Instant.now();
        System.out.println(instant);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneOffset.UTC);
        System.out.println(localDateTime);
    }
}
