package com.pcz.example.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author picongzhi
 */
public class StreamToArrayDemo {
    public static void main(String[] args) {
        streamToStringArr();
        streamToIntArr();
    }

    private static void streamToStringArr() {
        String lines = "I Love Java 8 Stream!";
        String[] arr = Arrays.stream(lines.split("\\s+"))
                .map(String::toUpperCase)
                .toArray(String[]::new);
        for (String s : arr) {
            System.out.println(s);
        }
    }

    private static void streamToIntArr() {
        int[] nums = {1, 2, 3, 4, 5};
        Integer[] result = Arrays.stream(nums)
                .map(x -> x * 2)
                .boxed()
                .toArray(Integer[]::new);
        System.out.println(Arrays.asList(result));

        nums = IntStream.rangeClosed(1, 5).toArray();
        System.out.println(Arrays.toString(nums));

        Stream<Integer> stream = Stream.of(1, 2, 3, 4, 5);
        nums = stream.mapToInt(x -> x).toArray();
        System.out.println(Arrays.toString(nums));
    }
}
