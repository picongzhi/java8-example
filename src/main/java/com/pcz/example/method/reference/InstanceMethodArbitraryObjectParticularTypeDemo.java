package com.pcz.example.method.reference;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;

/**
 * @author picongzhi
 */
public class InstanceMethodArbitraryObjectParticularTypeDemo {
    public static void main(String[] args) {
        System.out.println(playOneArgument("mkyong", String::length));
        System.out.println(playTwoArgument("mkyong", "y", (a, b) -> a.contains(b)));
        System.out.println(playTwoArgument("mkyong", "y", String::contains));

        System.out.println(playTwoArgument("mkyong", "m", (a, b) -> a.startsWith(b)));
        System.out.println(playTwoArgument("mkyong", "m", String::startsWith));

        Invoice invoice = new Invoice("A001", BigDecimal.valueOf(1.99), 3);
        InvoiceCalculator calculator = new InvoiceCalculator();

        System.out.println(calculate(calculator, invoice, (c, i) -> calculator.normal(invoice)));
        System.out.println(calculate(calculator, invoice, InvoiceCalculator::normal));
        System.out.println(calculate(calculator, invoice, (c, i) -> c.promotion(i)));
        System.out.println(calculate(calculator, invoice, InvoiceCalculator::promotion));
    }

    private static <T> T playOneArgument(String s, Function<String, T> func) {
        return func.apply(s);
    }

    private static boolean playTwoArgument(String s1, String s2, BiPredicate<String, String> func) {
        return func.test(s1, s2);
    }

    private static class Invoice {
        private String no;
        private BigDecimal unitPrice;
        private Integer qty;

        public Invoice(String no, BigDecimal unitPrice, Integer qty) {
            this.no = no;
            this.unitPrice = unitPrice;
            this.qty = qty;
        }

        public String getNo() {
            return no;
        }

        public void setNo(String no) {
            this.no = no;
        }

        public BigDecimal getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

        public Integer getQty() {
            return qty;
        }

        public void setQty(Integer qty) {
            this.qty = qty;
        }

        @Override
        public String toString() {
            return "Invoice{" +
                    "no='" + no + '\'' +
                    ", unitPrice=" + unitPrice +
                    ", qty=" + qty +
                    '}';
        }
    }

    static class InvoiceCalculator {
        public BigDecimal normal(Invoice invoice) {
            return invoice.getUnitPrice()
                    .multiply(BigDecimal.valueOf(invoice.getQty()));
        }

        public BigDecimal promotion(Invoice invoice) {
            return invoice.getUnitPrice()
                    .multiply(BigDecimal.valueOf(invoice.getQty()))
                    .multiply(BigDecimal.valueOf(0.9))
                    .setScale(2, RoundingMode.HALF_UP);
        }
    }

    private static BigDecimal calculate(InvoiceCalculator calculator, Invoice invoice,
                                        BiFunction<InvoiceCalculator, Invoice, BigDecimal> func) {
        return func.apply(calculator, invoice);
    }
}
