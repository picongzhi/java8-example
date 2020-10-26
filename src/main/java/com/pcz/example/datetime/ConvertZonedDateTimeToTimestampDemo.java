package com.pcz.example.datetime;

import java.sql.Timestamp;
import java.time.*;

/**
 * @author picongzhi
 */
public class ConvertZonedDateTimeToTimestampDemo {
    public static void main(String[] args) {
        zonedDateTimeToTimestamp();
        timestampToZonedDateTime();
    }

    private static void timestampToZonedDateTime() {
        Timestamp timestamp = Timestamp.from(Instant.now());
        System.out.println(timestamp);

        LocalDateTime localDateTime = timestamp.toLocalDateTime();
        System.out.println(localDateTime);

        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.of("+08:00"));
        System.out.println(zonedDateTime);

        zonedDateTime = localDateTime.atZone(ZoneId.of("Asia/Kuala_Lumpur"));
        System.out.println(zonedDateTime);

        zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        System.out.println(zonedDateTime);

        zonedDateTime = localDateTime.atZone(ZoneId.of("-08:00"));
        System.out.println(zonedDateTime);
    }

    private static void zonedDateTimeToTimestamp() {
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);

        Timestamp timestamp = Timestamp.valueOf(now.toLocalDateTime());
        System.out.println(timestamp);

        timestamp = Timestamp.from(now.toInstant());
        System.out.println(timestamp);
    }
}
