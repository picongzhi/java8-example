package com.pcz.example.datetime;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * @author picongzhi
 */
public class DifferenceBetweenLocalDateOrLocalDateTimeDemo {
    public static void main(String[] args) {
        periodBetweenLocalDate();
        durationBetweenLocalDateTime();
        chronoUnitBetweenLocalDateTime();
    }

    private static void chronoUnitBetweenLocalDateTime() {
        LocalDateTime from = LocalDateTime.of(2020, 3, 1, 10, 12, 13);
        LocalDateTime to = LocalDateTime.of(2020, 10, 20, 10, 12, 13);

        System.out.println(ChronoUnit.YEARS.between(from, to));
        System.out.println(ChronoUnit.MONTHS.between(from, to));
        System.out.println(ChronoUnit.WEEKS.between(from, to));
        System.out.println(ChronoUnit.DAYS.between(from, to));
        System.out.println(ChronoUnit.HOURS.between(from, to));
        System.out.println(ChronoUnit.MINUTES.between(from, to));
        System.out.println(ChronoUnit.SECONDS.between(from, to));
        System.out.println(ChronoUnit.MILLIS.between(from, to));
        System.out.println(ChronoUnit.NANOS.between(from, to));
    }

    private static void durationBetweenLocalDateTime() {
        LocalDateTime from = LocalDateTime.of(2020, 3, 1, 10, 12, 13);
        LocalDateTime to = LocalDateTime.of(2020, 10, 20, 10, 12, 13);

        Duration duration = Duration.between(from, to);
        System.out.println(duration.toDays());
        System.out.println(duration.toHours());
        System.out.println(duration.toMinutes());
        System.out.println(duration.toSeconds());
        System.out.println(duration.getSeconds());
    }

    private static void periodBetweenLocalDate() {
        LocalDate from = LocalDate.of(2020, 3, 1);
        LocalDate to = LocalDate.of(2020, 10, 25);
        Period period = Period.between(from, to);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }
}
