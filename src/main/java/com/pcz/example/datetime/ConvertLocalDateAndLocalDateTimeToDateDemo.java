package com.pcz.example.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author picongzhi
 */
public class ConvertLocalDateAndLocalDateTimeToDateDemo {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.of(2020, 10, 21);
        Date date = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());
        System.out.println(date);

        LocalDateTime localDateTime = LocalDateTime.of(2020, 2, 20, 20, 12, 23);
        date = Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
        System.out.println(date);
    }
}
