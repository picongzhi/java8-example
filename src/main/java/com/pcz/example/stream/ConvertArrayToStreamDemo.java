package com.pcz.example.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author picongzhi
 */
public class ConvertArrayToStreamDemo {
    public static void main(String[] args) {
        objectArrays();
        primitiveArrays();
    }

    private static void primitiveArrays() {
        int[] arr = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(arr);
        intStream.forEach(System.out::println);

        Stream<int[]> stream = Stream.of(arr);
        intStream = stream.flatMapToInt(x -> Arrays.stream(x));
        intStream.forEach(System.out::println);
    }

    private static void objectArrays() {
        String[] arr = {"a", "b", "c", "d", "e"};
        Stream<String> stream = Arrays.stream(arr);
        stream.forEach(System.out::println);

        stream = Stream.of(arr);
        stream.forEach(System.out::println);
    }
}
