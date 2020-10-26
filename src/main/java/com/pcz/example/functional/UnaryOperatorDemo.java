package com.pcz.example.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
public class UnaryOperatorDemo {
    public static void main(String[] args) {
        functionAndUnaryOperator();
        unaryOperatorAsArgument();
        chainUnaryOperator();
    }

    private static void chainUnaryOperator() {
        System.out.println(math(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), x -> x * 2, x -> x + 1));
    }

    private static <T> List<T> math(List<T> list, UnaryOperator<T> operator1, UnaryOperator<T> operator2) {
        return list.stream()
                .map(operator1.andThen(operator2))
                .collect(Collectors.toList());
    }

    private static void unaryOperatorAsArgument() {
        System.out.println(math(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10), x -> x * 2));
    }

    private static <T> List<T> math(List<T> list, UnaryOperator<T> unaryOperator) {
        return list.stream()
                .map(unaryOperator)
                .collect(Collectors.toList());
    }

    private static void functionAndUnaryOperator() {
        Function<Integer, Integer> function = x -> x * 2;
        System.out.println(function.apply(2));

        UnaryOperator<Integer> unaryOperator = x -> x * 2;
        System.out.println(unaryOperator.apply(2));
    }
}
