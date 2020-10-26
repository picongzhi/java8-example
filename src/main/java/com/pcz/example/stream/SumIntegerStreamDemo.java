package com.pcz.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * @author picongzhi
 */
public class SumIntegerStreamDemo {
    public static void main(String[] args) {
        List<Integer> integers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println("sum: " + integers.stream()
                .mapToInt(Integer::intValue)
                .sum());
        System.out.println("sum: " + Stream.iterate(1, n -> n + 1)
                .limit(10)
                .mapToInt(Integer::intValue)
                .sum());
    }
}
