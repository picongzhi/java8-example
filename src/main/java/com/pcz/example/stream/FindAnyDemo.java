package com.pcz.example.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * @author picongzhi
 */
public class FindAnyDemo {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Optional<Integer> any = list.stream().filter(x -> x > 1).findAny();
        if (any.isPresent()) {
            System.out.println(any.get());
        }
    }
}
