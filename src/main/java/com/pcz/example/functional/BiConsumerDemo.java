package com.pcz.example.functional;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * @author picongzhi
 */
public class BiConsumerDemo {
    public static void main(String[] args) {
        BiConsumer<Integer, Integer> printSum = (x, y) -> System.out.println(x + y);
        printSum.accept(1, 2);

        consume(1, 2, (x, y) -> System.out.println(x + y));
        consume("Node", ".js", (x, y) -> System.out.println(x + y));

        consume(1, 1, (x, y) -> System.out.println(x + y));
        consume(1, 1, (x, y) -> System.out.println(x - y));
        consume(1, 1, (x, y) -> System.out.println(x * y));
        consume(1, 1, (x, y) -> System.out.println(x / y));

        mapForEach();
    }

    private static void mapForEach() {
        Map<Integer, String> map = new LinkedHashMap<>();
        map.put(1, "Java");
        map.put(2, "C++");
        map.put(3, "Rust");
        map.put(4, "Javascript");
        map.put(5, "Go");
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    private static <T> void consume(T a1, T a2, BiConsumer<T, T> biConsumer) {
        biConsumer.accept(a1, a2);
    }
}
