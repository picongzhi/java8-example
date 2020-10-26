package com.pcz.example.datetime;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * @author picongzhi
 */
public class ConvertStringToDateDemo {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        String dateStr = "7-Jun-2020";
        printFormatDate(dateFormat, dateStr);

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateStr = "07/07/2020";
        printFormatDate(dateFormat, dateStr);

        dateFormat = new SimpleDateFormat("E, MMM dd yyyy");
        dateStr = "Fri, June 7 2020";
        printFormatDate(dateFormat, dateStr);

        dateFormat = new SimpleDateFormat("EEEE, MMM dd, yyyy HH:mm:ss a");
        dateStr = "Friday, Jun 7, 2020 12:10:10 PM";
        printFormatDate(dateFormat, dateStr);

        parseUTC();
    }

    private static void parseUTC() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        String dateStr = "2020-10-10T10:10:10Z";
        try {
            Date date = dateFormat.parse(dateStr.replaceAll("Z$", "+0000"));
            System.out.println(date);
            System.out.println("time zone: " + TimeZone.getDefault().getID());
            System.out.println(dateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Instant instant = Instant.parse(dateStr);
        System.out.println(instant);

        LocalDateTime localDateTime = LocalDateTime.ofInstant(instant, ZoneId.of(ZoneOffset.UTC.getId()));
        System.out.println(localDateTime);

        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.of("Africa/Tripoli"));
        System.out.println(zonedDateTime);

        zonedDateTime = instant.atZone(ZoneId.of("Europe/Athens"));
        System.out.println(zonedDateTime);
    }

    private static void printFormatDate(SimpleDateFormat dateFormat, String dateStr) {
        try {
            Date date = dateFormat.parse(dateStr);
            System.out.println(date);
            System.out.println(dateFormat.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
