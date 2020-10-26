package com.pcz.example.method.reference;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author picongzhi
 */
public class ConstructorDemo {
    public static void main(String[] args) {
        Supplier<Map> mapSupplier = () -> new HashMap();
        System.out.println(mapSupplier.get());

        mapSupplier = HashMap::new;
        System.out.println(mapSupplier.get());

        Supplier<Invoice> invoiceSupplier = () -> new Invoice();
        System.out.println(invoiceSupplier.get());

        invoiceSupplier = Invoice::new;
        System.out.println(invoiceSupplier.get());

        List<BigDecimal> unitPrices = Arrays.asList(
                BigDecimal.valueOf(9.99),
                BigDecimal.valueOf(2.99),
                BigDecimal.valueOf(8.99));
        List<Invoice> invoices = fakeInvoice(unitPrices, (unitPrice) -> new Invoice(unitPrice));
        invoices.forEach(System.out::println);

        invoices = fakeInvoice(unitPrices, Invoice::new);
        invoices.forEach(System.out::println);
    }

    private static List<Invoice> fakeInvoice(List<BigDecimal> unitPrices, Function<BigDecimal, Invoice> func) {
        List<Invoice> invoices = new ArrayList<>();
        unitPrices.forEach(unitPrice -> invoices.add(func.apply(unitPrice)));

        return invoices;
    }

    private static class Invoice {
        private String no;
        private BigDecimal unitPrice;
        private Integer qty;

        public Invoice() {
        }

        public Invoice(BigDecimal unitPrice) {
            this.unitPrice = unitPrice;
        }

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
}
