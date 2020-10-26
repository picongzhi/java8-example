package com.pcz.example.datetime;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author picongzhi
 */
public class ConvertLocalDateTimeToTimestampDemo {
    public static void main(String[] args) {
        localDateTimeToTimestamp();
        localDateToTimestamp();
    }

    private static void localDateToTimestamp() {
        LocalDate localDate = LocalDate.now();
        Timestamp timestamp = Timestamp.valueOf(localDate.atStartOfDay());
        System.out.println(localDate);
        System.out.println(timestamp.getTime());
    }

    private static void localDateTimeToTimestamp() {
        LocalDateTime localDateTime = LocalDateTime.now();
        Timestamp timestamp = Timestamp.valueOf(localDateTime);
        System.out.println(localDateTime);
        System.out.println(timestamp.getTime());
    }
}
