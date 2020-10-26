package com.pcz.example.stream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

/**
 * @author picongzhi
 */
public class ReduceDemo {
    public static void main(String[] args) {
        sum();
        math();
        maxAndMin();
        joinString();
        mapAndReduce();
    }

    private static void mapAndReduce() {
        List<Invoice> invoices = Arrays.asList(
                new Invoice("A01", BigDecimal.valueOf(9.99), BigDecimal.valueOf(1)),
                new Invoice("A02", BigDecimal.valueOf(19.99), BigDecimal.valueOf(1.5)),
                new Invoice("A03", BigDecimal.valueOf(4.99), BigDecimal.valueOf(2))
        );
        System.out.println(invoices.stream()
                .map(invoice -> invoice.getQty().multiply(invoice.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP));
    }

    private static void joinString() {
        String[] strings = {"a", "b", "c", "d", "e"};
        System.out.println(Arrays.stream(strings).reduce("", (a, b) -> a + "|" + b));
        System.out.println(Arrays.stream(strings).reduce("", (a, b) -> !"".equals(a) ? a + "|" + b : b));
        System.out.println(String.join("|", strings));
    }

    private static void sum() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        System.out.println(sum);

        System.out.println(Arrays.stream(nums)
                .reduce(0, (a, b) -> a + b));
        System.out.println(Arrays.stream(nums)
                .reduce(0, Integer::sum));
    }

    private static void math() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println(Arrays.stream(nums).reduce(0, Integer::sum));
        System.out.println(Arrays.stream(nums).reduce(0, (a, b) -> a - b));
        System.out.println(Arrays.stream(nums).reduce(0, (a, b) -> a * b));
        System.out.println(Arrays.stream(nums).reduce(0, (a, b) -> a / b));
    }

    private static void maxAndMin() {
        int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("max: " + Arrays.stream(nums).reduce(0, (a, b) -> a > b ? a : b));
        System.out.println("max: " + Arrays.stream(nums).reduce(0, Integer::max));

        System.out.println("min: " + Arrays.stream(nums).reduce(0, (a, b) -> a < b ? a : b));
        System.out.println("min: " + Arrays.stream(nums).reduce(0, Integer::min));
    }

    private static class Invoice {
        private String no;
        private BigDecimal price;
        private BigDecimal qty;

        public Invoice() {
        }

        public Invoice(String no, BigDecimal price, BigDecimal qty) {
            this.no = no;
            this.price = price;
            this.qty = qty;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        public BigDecimal getQty() {
            return qty;
        }

        public void setQty(BigDecimal qty) {
            this.qty = qty;
        }

        @Override
        public String toString() {
            return "Invoice{" +
                    "no='" + no + '\'' +
                    ", price=" + price +
                    ", qty=" + qty +
                    '}';
        }
    }
}
