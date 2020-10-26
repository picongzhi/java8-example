package com.pcz.example.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author picongzhi
 */
public class ConsumerDemo {
    public static void main(String[] args) {
        Consumer<String> print = x -> System.out.println(x);
        print.accept("java");

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        forEach(list, System.out::println);

        List<String> strings = Arrays.asList("a", "ab", "abc");
        forEach(strings, s -> System.out.println(s.length()));
    }

    private static <T> void forEach(List<T> list, Consumer<T> consumer) {
        for (T t : list) {
            consumer.accept(t);
        }
    }
}
