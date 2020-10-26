package com.pcz.example.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;

/**
 * @author picongzhi
 */
public class CompareDateDemo {
    public static void main(String[] args) throws Exception {
        dateCompareTo();
        dateBeforeAfterEquals();
        calendarBeforeAfterEquals();
        java8Compare();
    }

    private static void java8Compare() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate1 = LocalDate.of(2020, 3, 1);
        LocalDate localDate2 = LocalDate.of(2020, 10, 21);
        System.out.println(dateTimeFormatter.format(localDate1));
        System.out.println(dateTimeFormatter.format(localDate2));

        if (localDate1.isBefore(localDate2)) {
            System.out.println("date1 is before date2");
        } else if (localDate1.isAfter(localDate2)) {
            System.out.println("date1 is after date2");
        } else {
            System.out.println("date1 is equal to date2");
        }

        if (localDate1.compareTo(localDate2) < 0) {
            System.out.println("date1 is before date2");
        } else if (localDate1.compareTo(localDate2) > 0) {
            System.out.println("date1 is after date2");
        } else {
            System.out.println("date1 is equal to date2");
        }
    }

    private static void calendarBeforeAfterEquals() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse("2020-01-01");
        Date date2 = simpleDateFormat.parse("2020-03-01");

        System.out.println(simpleDateFormat.format(date1));
        System.out.println(simpleDateFormat.format(date2));

        Calendar calendar1 = Calendar.getInstance();
        calendar1.setTime(date1);
        Calendar calendar2 = Calendar.getInstance();
        calendar2.setTime(date2);

        if (calendar1.after(calendar2)) {
            System.out.println("date1 is after date2");
        } else if (calendar1.before(calendar2)) {
            System.out.println("date1 is before date2");
        } else if (calendar1.equals(calendar2)) {
            System.out.println("date1 is equal to date2");
        }
    }

    private static void dateBeforeAfterEquals() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse("2020-01-01");
        Date date2 = simpleDateFormat.parse("2020-03-01");

        System.out.println(simpleDateFormat.format(date1));
        System.out.println(simpleDateFormat.format(date2));

        if (date1.after(date2)) {
            System.out.println("date1 is after date2");
        } else if (date1.before(date2)) {
            System.out.println("date1 is before date2");
        } else if (date1.equals(date2)) {
            System.out.println("date1 is equal to date2");
        }
    }

    private static void dateCompareTo() throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = simpleDateFormat.parse("2020-01-01");
        Date date2 = simpleDateFormat.parse("2020-03-01");

        System.out.println(simpleDateFormat.format(date1));
        System.out.println(simpleDateFormat.format(date2));
        if (date1.compareTo(date2) > 0) {
            System.out.println("date1 is after date2");
        } else if (date1.compareTo(date2) == 0) {
            System.out.println("date1 is equal to date2");
        } else {
            System.out.println("date1 is before date2");
        }
    }
}
