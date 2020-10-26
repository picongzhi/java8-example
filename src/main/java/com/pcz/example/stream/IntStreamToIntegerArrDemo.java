package com.pcz.example.stream;

import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author picongzhi
 */
public class IntStreamToIntegerArrDemo {
    public static void main(String[] args) {
        int[] nums = {3, 4, 5};
        IntStream intStream = Arrays.stream(nums);
        Stream<Integer> stream = intStream.boxed();
        Integer[] arr = stream.toArray(Integer[]::new);
        System.out.println(Arrays.toString(arr));

        System.out.println(Arrays.toString(Arrays.stream(nums)
                .boxed()
                .toArray(Integer[]::new)));
    }
}
