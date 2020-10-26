package com.pcz.example.functional;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
public class BiFunctionDemo {
    public static void main(String[] args) {
        biFunction();
        biFunctionChain();

        GPS gps = factory("40.741896", "-73.989308", GPS::new);
        System.out.println(gps);

        filterByCondition();
    }

    private static void filterByCondition() {
        List<String> list = Arrays.asList("node", "c++", "java", "javascript");
        System.out.println(filterList(list, "c", (item, str) -> item.startsWith(str) ? item : null));
        System.out.println(filterList(list, 3, (item, len) -> item.length() > len ? item : null));
        System.out.println(filterList(list, 3, BiFunctionDemo::filterByLength));

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(filterList(nums, 2, (l, num) -> l % num == 0 ? l : null));
    }

    private static String filterByLength(String str, Integer length) {
        return str.length() > length ? str : null;
    }

    private static <T, U, R> List<R> filterList(List<T> list, U condition, BiFunction<T, U, R> function) {
        return list.stream()
                .map(item -> function.apply(item, condition))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private static void biFunction() {
        BiFunction<Integer, Integer, Integer> sumFunc = Integer::sum;
        System.out.println(sumFunc.apply(1, 2));

        BiFunction<Integer, Integer, Double> powFunc = Math::pow;
        System.out.println(powFunc.apply(2, 4));

        BiFunction<Integer, Integer, List<Integer>> toList = (x1, x2) -> Collections.singletonList(x1 + x2);
        System.out.println(toList.apply(1, 2));
    }

    private static void biFunctionChain() {
        BiFunction<Integer, Integer, Double> powFunc = Math::pow;
        Function<Double, String> toString = input -> "Result: " + String.valueOf(input);
        System.out.println(powFunc.andThen(toString).apply(2, 4));

        System.out.println(powToString(2, 4, powFunc, toString));

        String res = convert(2, 4, (a1, a2) -> Math.pow(a1, a2), r -> "Pow: " + r);
        System.out.println(res);

        res = convert(2, 4, (a1, a2) -> a1 * a2, r -> "Multiply: " + r);
        System.out.println(res);

        res = convert("a", "b", (a1, a2) -> a1 + a2, r -> r + "cde");
        System.out.println(res);

        Integer value = convert("100", "200", (a1, a2) -> a1 + a2, r -> Integer.valueOf(r));
        System.out.println(value);
    }

    private static <T> T powToString(Integer x1, Integer x2,
                                     BiFunction<Integer, Integer, Double> biFunction,
                                     Function<Double, T> function) {
        return biFunction.andThen(function).apply(x1, x2);
    }

    private static <A1, A2, R1, R2> R2 convert(A1 a1, A2 a2,
                                               BiFunction<A1, A2, R1> biFunction,
                                               Function<R1, R2> function) {
        return biFunction.andThen(function).apply(a1, a2);
    }

    private static <T extends GPS> T factory(String latitude, String longitude,
                                             BiFunction<String, String, T> function) {
        return function.apply(latitude, longitude);
    }

    private static class GPS {
        private String latitude;
        private String longitude;

        public GPS(String latitude, String longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "GPS{" +
                    "latitude='" + latitude + '\'' +
                    ", longitude='" + longitude + '\'' +
                    '}';
        }
    }
}
