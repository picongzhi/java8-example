package com.pcz.example.datetime;

import java.time.Instant;
import java.time.ZoneId;

/**
 * @author picongzhi
 */
public class ConvertEpochTimeToLocalDateTime {
    public static void main(String[] args) {
        long epoch = Instant.now().toEpochMilli();
        System.out.println(epoch);

        System.out.println(Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDate());
        System.out.println(Instant.ofEpochMilli(epoch).atZone(ZoneId.systemDefault()).toLocalDateTime());
    }
}
