package com.pcz.example.stream;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.List;

/**
 * @author picongzhi
 */
public class SumBigDecimalDemo {
    public static void main(String[] args) {
        streamReduce();
        mapAndReduce();
    }

    private static void mapAndReduce() {
        List<Invoice> invoices = Arrays.asList(
                new Invoice("I1001", BigDecimal.valueOf(9.99), BigDecimal.valueOf(1)),
                new Invoice("I1002", BigDecimal.valueOf(19.99), BigDecimal.valueOf(1.5)),
                new Invoice("I1003", BigDecimal.valueOf(4.888), BigDecimal.valueOf(2)),
                new Invoice("I1004", BigDecimal.valueOf(4.99), BigDecimal.valueOf(5)),
                new Invoice("I1005", BigDecimal.valueOf(.5), BigDecimal.valueOf(2.3))
        );
        System.out.println("sum: " + invoices.stream()
                .map(invoice -> invoice.getQty().multiply(invoice.getPrice()))
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .setScale(2, RoundingMode.HALF_UP));
    }

    private static void streamReduce() {
        List<BigDecimal> list = Arrays.asList(
                BigDecimal.valueOf(9.9),
                BigDecimal.valueOf(1.0),
                BigDecimal.valueOf(19.99),
                BigDecimal.valueOf(0.2),
                BigDecimal.valueOf(5.5)
        );

        BigDecimal sum = BigDecimal.ZERO;
        for (BigDecimal x : list) {
            sum = sum.add(x);
        }
        System.out.println("sum: " + sum);

        System.out.println("sum: " + list.stream().reduce(BigDecimal.ZERO, BigDecimal::add));
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
