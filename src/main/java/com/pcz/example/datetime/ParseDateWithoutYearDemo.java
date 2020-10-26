package com.pcz.example.datetime;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.temporal.ChronoField;
import java.util.Locale;

/**
 * @author picongzhi
 */
public class ParseDateWithoutYearDemo {
    public static void main(String[] args) {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM", Locale.US);
//        String dateStr = "02 Jan";
//        LocalDate localDate = LocalDate.parse(dateStr, formatter);
//        System.out.println(localDate);

        DateTimeFormatter formatter = new DateTimeFormatterBuilder()
                .appendPattern("dd MMM")
                .parseDefaulting(ChronoField.YEAR, 2020)
                .toFormatter(Locale.US);
        String dateStr = "02 Jan";
        LocalDate localDate = LocalDate.parse(dateStr, formatter);
        System.out.println(localDate);
        System.out.println(formatter.format(localDate));
    }
}
