package com.pcz.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
public class ConvertListListStringToListStringDemo {
    public static void main(String[] args) {
        List<String> numbers = Arrays.asList("1", "2", "A", "B", "C1D2E3");
        List<List<String>> collect = numbers.stream()
                .map(x -> new Scanner(x).findAll("\\D+")
                        .map(m -> m.group())
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
        collect.forEach(System.out::println);

        collect.stream()
                .flatMap(List::stream)
                .forEach(System.out::println);
    }
}
