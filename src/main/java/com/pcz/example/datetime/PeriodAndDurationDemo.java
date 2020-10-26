package com.pcz.example.datetime;

import java.time.*;
import java.time.temporal.ChronoUnit;

/**
 * @author picongzhi
 */
public class PeriodAndDurationDemo {
    public static void main(String[] args) {
        displayDuration();
        displayPeriod();
        displayChronoUnit();
    }

    private static void displayChronoUnit() {
        LocalDateTime oldDateTime = LocalDateTime.of(1993, Month.MARCH, 1, 10, 10, 10);
        LocalDateTime newDateTime = LocalDateTime.of(2020, Month.OCTOBER, 19, 20, 20, 30);
        System.out.println(oldDateTime);
        System.out.println(newDateTime);

        System.out.println(ChronoUnit.YEARS.between(oldDateTime, newDateTime));
        System.out.println(ChronoUnit.MONTHS.between(oldDateTime, newDateTime));
        System.out.println(ChronoUnit.WEEKS.between(oldDateTime, newDateTime));
        System.out.println(ChronoUnit.DAYS.between(oldDateTime, newDateTime));
        System.out.println(ChronoUnit.HOURS.between(oldDateTime, newDateTime));
        System.out.println(ChronoUnit.MINUTES.between(oldDateTime, newDateTime));
        System.out.println(ChronoUnit.SECONDS.between(oldDateTime, newDateTime));
        System.out.println(ChronoUnit.NANOS.between(oldDateTime, newDateTime));
    }

    private static void displayPeriod() {
        Period tenDays = Period.ofDays(10);
        System.out.println(tenDays.getDays() + " days");

        Period oneYearTwoMonthsThreeDays = Period.of(1, 2, 3);
        System.out.println(oneYearTwoMonthsThreeDays.getYears());
        System.out.println(oneYearTwoMonthsThreeDays.getMonths());
        System.out.println(oneYearTwoMonthsThreeDays.getDays());

        LocalDate oldDate = LocalDate.of(1993, Month.MARCH, 1);
        LocalDate newDate = LocalDate.of(2020, Month.OCTOBER, 19);
        Period period = Period.between(oldDate, newDate);
        System.out.println(period.getYears());
        System.out.println(period.getMonths());
        System.out.println(period.getDays());
    }

    private static void displayDuration() {
        Duration oneHour = Duration.ofHours(1);
        System.out.println(oneHour.getSeconds() + " seconds");

        oneHour = Duration.of(1, ChronoUnit.HOURS);
        System.out.println(oneHour.getSeconds() + " seconds");

        LocalDateTime oldDateTime = LocalDateTime.of(2020, Month.MAY, 1, 10, 10, 10);
        LocalDateTime newDateTime = LocalDateTime.of(2020, Month.MAY, 2, 20, 20, 30);
        System.out.println(oldDateTime);
        System.out.println(newDateTime);
        System.out.println(Duration.between(oldDateTime, newDateTime).getSeconds() + " seconds");
    }
}
