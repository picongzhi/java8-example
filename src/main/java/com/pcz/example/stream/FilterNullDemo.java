package com.pcz.example.stream;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author picongzhi
 */
public class FilterNullDemo {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("java", "python", "node", null, "ruby", null, "php");
        System.out.println("origin:");
        stream.collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("filter null:");
        stream = Stream.of("java", "python", "node", null, "ruby", null, "php");
        stream.filter(x -> x != null)
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("filter null:");
        stream = Stream.of("java", "python", "node", null, "ruby", null, "php");
        stream.filter(Objects::nonNull)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }
}
