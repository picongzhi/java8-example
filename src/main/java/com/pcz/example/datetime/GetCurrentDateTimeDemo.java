package com.pcz.example.datetime;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author picongzhi
 */
public class GetCurrentDateTimeDemo {
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Date date = new Date();
        System.out.println(DATE_FORMAT.format(date));

        Calendar calendar = Calendar.getInstance();
        System.out.println(DATE_FORMAT.format(calendar.getTime()));

        LocalDateTime now = LocalDateTime.now();
        System.out.println(DATE_TIME_FORMATTER.format(now));

        LocalDate localDate = LocalDate.now();
        System.out.println(DateTimeFormatter.ofPattern("yyyy-MM-dd").format(localDate));
    }
}
