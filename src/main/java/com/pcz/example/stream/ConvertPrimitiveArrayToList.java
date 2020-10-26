package com.pcz.example.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
public class ConvertPrimitiveArrayToList {
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("list: " + convertIntArrayToList(nums));
        System.out.println("list: " + Arrays.stream(nums)
                .boxed()
                .collect(Collectors.toList()));
    }

    private static List<Integer> convertIntArrayToList(int[] input) {
        List<Integer> list = new ArrayList<>();
        for (int i : input) {
            list.add(i);
        }

        return list;
    }
}
