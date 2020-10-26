package com.pcz.example.stream;

import java.util.Arrays;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author picongzhi
 */
public class StreamClosedDemo {
    public static void main(String[] args) {
        String[] arr = {"a", "b", "c", "d", "e"};
        Stream<String> stream = Arrays.stream(arr);
        stream.forEach(System.out::println);

//        System.out.println(stream.filter(x -> "b".equals(x)).count());

        Supplier<Stream<String>> supplier = () -> Stream.of(arr);
        supplier.get().forEach(System.out::println);
        System.out.println(supplier.get().filter(x -> "b".equals(x)).count());
    }
}
