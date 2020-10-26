package com.pcz.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author picongzhi
 */
public class FindFirstDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 2, 1);
        Optional<Integer> first = list.stream().findFirst();
        if (first.isPresent()) {
            System.out.println(first.get());
        } else {
            System.out.println("no value");
        }

        first = list.stream()
                .filter(x -> x > 1)
                .findFirst();
        if (first.isPresent()) {
            System.out.println(first.get());
        } else {
            System.out.println("no value");
        }

        List<String> language = Arrays.asList("node", "java", "python", "ruby");
        System.out.println(language.stream()
                .filter(x -> !x.equalsIgnoreCase("node"))
                .findFirst()
                .orElse("no value"));
    }
}
