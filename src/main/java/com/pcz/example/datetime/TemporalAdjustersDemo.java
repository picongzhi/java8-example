package com.pcz.example.datetime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * @author picongzhi
 */
public class TemporalAdjustersDemo {
    public static void main(String[] args) {
        LocalDate localDate = LocalDate.now();
        System.out.println(localDate);

        System.out.println(localDate.with(TemporalAdjusters.firstDayOfMonth()));
        System.out.println(localDate.with(TemporalAdjusters.lastDayOfMonth()));
        System.out.println(localDate.with(TemporalAdjusters.next(DayOfWeek.MONDAY)));
        System.out.println(localDate.with(TemporalAdjusters.firstDayOfNextMonth()));

        System.out.println(localDate.with(new NextChristmas()));
        System.out.println(localDate.with((temporal -> temporal
                .with(ChronoField.MONTH_OF_YEAR, 12)
                .with(ChronoField.DAY_OF_MONTH, 25))));
    }

    private static class NextChristmas implements TemporalAdjuster {
        @Override
        public Temporal adjustInto(Temporal temporal) {
            return temporal.with(ChronoField.MONTH_OF_YEAR, 12).with(ChronoField.DAY_OF_MONTH, 25);
        }
    }
}
