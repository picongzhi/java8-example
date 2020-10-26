package com.pcz.example.datetime;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author picongzhi
 */
public class FormatLocalDateTimeDemo {
    public static void main(String[] args) {
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(formatter.format(now));

        String dateStr = "2020-10-19 18:34";
        formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr, formatter);
        System.out.println(dateStr);
        System.out.println(localDateTime);
        System.out.println(formatter.format(localDateTime));
    }
}
