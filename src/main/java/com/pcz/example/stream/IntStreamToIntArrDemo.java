package com.pcz.example.stream;

import java.util.Arrays;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * @author picongzhi
 */
public class IntStreamToIntArrDemo {
    public static void main(String[] args) {
        intStreamToInt();
        intStreamToIntArr();
    }

    private static void intStreamToInt() {
        int[] nums = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(nums);
        OptionalInt first = intStream.findFirst();
        System.out.println(first.getAsInt());

        System.out.println(Arrays.stream(nums).findFirst().getAsInt());
    }

    private static void intStreamToIntArr() {
        int[] nums = {1, 2, 3, 4, 5};
        IntStream intStream = Arrays.stream(nums);
        int[] arr = intStream.toArray();
        System.out.println(Arrays.toString(arr));

        Integer[] integers = Arrays.stream(nums)
                .boxed()
                .toArray(Integer[]::new);
        System.out.println(Arrays.toString(integers));
    }
}
