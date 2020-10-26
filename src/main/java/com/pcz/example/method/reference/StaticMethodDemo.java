package com.pcz.example.method.reference;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
public class StaticMethodDemo {
    public static void main(String[] args) {
        staticMethodPrint();
        staticMethodParseInt();
        staticMethodPlayTwoArgument();
    }

    private static void staticMethodPrint() {
        List<String> list = Arrays.asList("A", "B", "C");

        System.out.println("anonymous class:");
        list.forEach(new Consumer<String>() {
            @Override
            public void accept(String s) {
                SimplePrinter.print(s);
            }
        });

        System.out.println("lambda expression:");
        list.forEach(s -> SimplePrinter.print(s));

        System.out.println("method reference:");
        list.forEach(SimplePrinter::print);
    }

    private static void staticMethodParseInt() {
        List<String> list = Arrays.asList("1", "2", "3");
        System.out.println("anonymous class:");
        list.stream()
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return Integer.parseInt(s);
                    }
                })
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("lambda expression:");
        list.stream()
                .map(s -> Integer.parseInt(s))
                .collect(Collectors.toList())
                .forEach(System.out::println);

        System.out.println("method reference:");
        list.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private static void staticMethodPlayTwoArgument() {
        System.out.println("anonymous class: " +
                playTwoArgument(1, 2, new BiFunction<Integer, Integer, String>() {
                    @Override
                    public String apply(Integer a, Integer b) {
                        return IntegerUtils.join(a, b);
                    }
                }));

        System.out.println("lambda: " + playTwoArgument(1, 2, (a, b) -> IntegerUtils.join(a, b)));

        System.out.println("method reference: " + playTwoArgument(1, 2, IntegerUtils::join));
    }

    private static <T> T playTwoArgument(Integer a, Integer b,
                                         BiFunction<Integer, Integer, T> func) {
        return func.apply(a, b);
    }

    static class SimplePrinter {
        public static void print(String str) {
            System.out.println(str);
        }
    }

    static class IntegerUtils {
        public static String join(Integer a, Integer b) {
            return String.valueOf(a + b);
        }
    }
}
