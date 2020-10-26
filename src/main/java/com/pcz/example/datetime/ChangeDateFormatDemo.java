package com.pcz.example.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

/**
 * @author picongzhi
 */
public class ChangeDateFormatDemo {
    public static void main(String[] args) {
        changeDateFormatByDateTimeFormatter();
        changeDateFormatBySimpleDateFormat();
    }

    private static void changeDateFormatBySimpleDateFormat() {
        String dateStr = "2020-10-21 12:34:56.0";
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").parse(dateStr);
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.S").format(date));
            System.out.println(new SimpleDateFormat("EEEE, MMM d, yyyy HH:mm:ss a").format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    private static void changeDateFormatByDateTimeFormatter() {
        String dateStr = "2020-10-21 12:34:56.0";
        LocalDateTime localDateTime = LocalDateTime.parse(dateStr,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S"));
        System.out.println(localDateTime);
        System.out.println(DateTimeFormatter.ofPattern("EEEE, MMM d, yyyy HH:mm:ss a", Locale.US)
                .format(localDateTime));
    }
}
