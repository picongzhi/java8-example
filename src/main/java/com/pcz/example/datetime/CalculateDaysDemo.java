package com.pcz.example.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

/**
 * @author picongzhi
 */
public class CalculateDaysDemo {
    public static void main(String[] args) {
        calculateDaysBetweenLocalDate();
        calculateDaysBetweenLocalDateTime();
    }

    private static void calculateDaysBetweenLocalDateTime() {
        LocalDateTime from = LocalDateTime.now();
        LocalDateTime to = from.plusDays(10);
        System.out.println(from);
        System.out.println(to);
        System.out.println(ChronoUnit.DAYS.between(from, to));
    }

    private static void calculateDaysBetweenLocalDate() {
        LocalDate from = LocalDate.now();
        LocalDate to = from.plusDays(10);
        System.out.println(from);
        System.out.println(to);
        System.out.println(ChronoUnit.DAYS.between(from, to));
    }
}
