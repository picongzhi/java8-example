package com.pcz.example.functional;

import org.apache.commons.codec.digest.DigestUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
public class FunctionDemo {
    public static void main(String[] args) {
        stringLengthFunction();
        chainFunction();
        listToMap();
        listToList();
    }

    private static void listToList() {
        List<String> languages = Arrays.asList("node", "c++", "java", "javascript");
        System.out.println(convertListToList(languages, FunctionDemo::sha256));
    }

    private static <T, R> List<R> convertListToList(List<T> list, Function<T, R> func) {
        return list.stream()
                .map(func)
                .collect(Collectors.toList());
    }

    private static String sha256(String s) {
        return DigestUtils.sha256Hex(s);
    }

    private static void stringLengthFunction() {
        Function<String, Integer> func = x -> x.length();
        System.out.println(func.apply("hello"));
    }

    private static void chainFunction() {
        Function<String, Integer> lengthFunc = x -> x.length();
        Function<Integer, Integer> doubleFunc = x -> x * 2;
        System.out.println(lengthFunc.andThen(doubleFunc).apply("hello"));
    }

    private static void listToMap() {
        List<String> languages = Arrays.asList("node", "c++", "java", "javascript");
        System.out.println(convertListToMap(languages, String::length));
        System.out.println(convertListToMap(languages, FunctionDemo::getLength));
    }

    private static Integer getLength(String s) {
        return s.length();
    }

    private static <T, R> Map<T, R> convertListToMap(List<T> list, Function<T, R> func) {
        return list.stream()
                .collect(Collectors.toMap(item -> item, func));
    }
}
