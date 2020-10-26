package com.pcz.example.datetime;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * @author picongzhi
 */
public class GetCurrentTimestampDemo {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timestamp);

        Date date = new Date();
        System.out.println(new Timestamp(date.getTime()));

        System.out.println(timestamp.getTime());

        System.out.println(DATE_FORMAT.format(timestamp));

        Instant instant = timestamp.toInstant();
        System.out.println(instant);
        System.out.println(instant.toEpochMilli());

        Timestamp timestampFromInstant = Timestamp.from(instant);
        System.out.println(timestampFromInstant.getTime());
    }
}
