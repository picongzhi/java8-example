package com.pcz.example.stream;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author picongzhi
 */
public class StreamToListDemo {
    public static void main(String[] args) {
        Stream<String> language = Stream.of("java", "python", "node");
        language.collect(Collectors.toList()).forEach(System.out::println);

        Stream<Integer> nums = Stream.of(1, 2, 3, 4, 5);
        nums.filter(x -> x != 3).collect(Collectors.toList()).forEach(System.out::println);
    }
}
