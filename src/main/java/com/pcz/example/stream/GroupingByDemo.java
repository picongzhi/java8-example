package com.pcz.example.stream;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
public class GroupingByDemo {
    public static void main(String[] args) {
        countAndSort();
        groupByObjects();
    }

    private static void countAndSort() {
        List<String> list = Arrays.asList("apple", "apple", "banana", "apple", "orange", "banana", "papaya");
        System.out.println(list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));

        list.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .forEachOrdered(entry -> System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue()));
    }

    private static void groupByObjects() {
        List<Item> items = Arrays.asList(
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 20, new BigDecimal("19.99")),
                new Item("orange", 10, new BigDecimal("29.99")),
                new Item("watermelon", 10, new BigDecimal("29.99")),
                new Item("papaya", 20, new BigDecimal("9.99")),
                new Item("apple", 10, new BigDecimal("9.99")),
                new Item("banana", 10, new BigDecimal("19.99")),
                new Item("apple", 20, new BigDecimal("9.99"))
        );
        System.out.println("groupingBy counting:");
        items.stream()
                .collect(Collectors.groupingBy(Item::getName, Collectors.counting()))
                .forEach((k, v) -> System.out.println("key: " + k + ", value: " + v));

        System.out.println("groupingBy summingInt:");
        items.stream()
                .collect(Collectors.groupingBy(Item::getName, Collectors.summingInt(Item::getQty)))
                .forEach((k, v) -> System.out.println("key: " + k + ", value: " + v));

        System.out.println("groupingBy:");
        items.stream()
                .collect(Collectors.groupingBy(Item::getPrice))
                .forEach((k, v) -> System.out.println("key: " + k + ", value: " + v));

        System.out.println("groupingBy mapping to set:");
        items.stream()
                .collect(Collectors.groupingBy(Item::getPrice,
                        Collectors.mapping(Item::getName, Collectors.toSet())))
                .forEach((k, v) -> System.out.println("key: " + k + ", value: " + v));
    }

    private static class Item {
        private String name;
        private int qty;
        private BigDecimal price;

        public Item() {
        }

        public Item(String name, int qty, BigDecimal price) {
            this.name = name;
            this.qty = qty;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQty() {
            return qty;
        }

        public void setQty(int qty) {
            this.qty = qty;
        }

        public BigDecimal getPrice() {
            return price;
        }

        public void setPrice(BigDecimal price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Item{" +
                    "name='" + name + '\'' +
                    ", qty=" + qty +
                    ", price=" + price +
                    '}';
        }
    }
}
