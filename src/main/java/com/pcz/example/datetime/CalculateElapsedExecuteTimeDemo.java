package com.pcz.example.datetime;

import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author picongzhi
 */
public class CalculateElapsedExecuteTimeDemo {
    public static void main(String[] args) {
        systemNanoTime();
        systemCurrentTimeMillis();
        instantToEpochMilli();
        dateGetTime();
    }

    private static void dateGetTime() {
        long start = new Date().getTime();
        calculation();
        long end = new Date().getTime();
        System.out.println(end - start);
    }

    private static void instantToEpochMilli() {
        long start = Instant.now().toEpochMilli();
        calculation();
        long end = Instant.now().toEpochMilli();
        System.out.println(end - start);
    }

    private static void systemCurrentTimeMillis() {
        long start = System.currentTimeMillis();
        calculation();
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

    private static void systemNanoTime() {
        long startTime = System.nanoTime();
        calculation();
        long endTime = System.nanoTime();
        System.out.println((endTime - startTime) / 1000_000);
    }

    private static void calculation() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
