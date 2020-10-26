package com.pcz.example.stream;

import java.util.stream.Stream;

/**
 * @author picongzhi
 */
public class IterateDemo {
    public static void main(String[] args) {
        System.out.println("0-10:");
        Stream.iterate(0, n -> n + 1)
                .limit(10)
                .forEach(System.out::println);

        System.out.println("10 odd:");
        Stream.iterate(0, n -> n + 1)
                .filter(x -> x % 2 != 0)
                .limit(10)
                .forEach(System.out::println);

        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(20)
                .map(arr -> arr[0])
                .forEach(System.out::println);

        int sum = Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]})
                .limit(10)
                .mapToInt(arr -> arr[0])
                .sum();
        System.out.println("fibonacci sum of top 10: " + sum);
    }
}
