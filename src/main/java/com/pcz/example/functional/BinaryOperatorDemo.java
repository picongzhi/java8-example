package com.pcz.example.functional;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.IntBinaryOperator;

/**
 * @author picongzhi
 */
public class BinaryOperatorDemo {
    public static void main(String[] args) {
        biFunctionAndBinaryOperator();
        binaryOperatorAsArgument();
        intBinaryOperator();
        maxByAndMinBy();
    }

    private static void maxByAndMinBy() {
        List<Developer> developers = Arrays.asList(
                new Developer("jordan", BigDecimal.valueOf(9999)),
                new Developer("jack", BigDecimal.valueOf(8888)),
                new Developer("jaden", BigDecimal.valueOf(10000)),
                new Developer("ALI", BigDecimal.valueOf(2000)),
                new Developer("mkyong", BigDecimal.valueOf(100))
        );

        System.out.println(find(developers, BinaryOperator.maxBy(Comparator.comparing(Developer::getSalary))));
        System.out.println(find(developers, BinaryOperator.minBy(Comparator.comparing(Developer::getSalary))));
    }

    private static Developer find(List<Developer> developers, BinaryOperator<Developer> operator) {
        Developer developer = null;
        for (Developer item : developers) {
            developer = developer == null ?
                    item : operator.apply(developer, item);
        }

        return developer;
    }

    private static void intBinaryOperator() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(math(nums, 0, (a, b) -> a + b));
        System.out.println(math(nums, 0, Integer::sum));
    }

    private static int math(int[] list, int init, IntBinaryOperator accumulator) {
        int res = init;
        for (int i : list) {
            res = accumulator.applyAsInt(res, i);
        }

        return res;
    }

    private static void binaryOperatorAsArgument() {
        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(math(nums, 0, (a, b) -> a + b));
        System.out.println(math(nums, 0, Integer::sum));
    }

    private static <T> T math(List<T> list, T init, BinaryOperator<T> accumulator) {
        return list.stream().reduce(init, accumulator);
    }

    private static void biFunctionAndBinaryOperator() {
        BiFunction<Integer, Integer, Integer> sum = Integer::sum;
        System.out.println(sum.apply(2, 3));

        BinaryOperator<Integer> binaryOperator = Integer::sum;
        System.out.println(binaryOperator.apply(2, 3));
    }

    private static class Developer {
        private String name;
        private BigDecimal salary;

        public Developer(String name, BigDecimal salary) {
            this.name = name;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public BigDecimal getSalary() {
            return salary;
        }

        public void setSalary(BigDecimal salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Developer{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    '}';
        }
    }
}
