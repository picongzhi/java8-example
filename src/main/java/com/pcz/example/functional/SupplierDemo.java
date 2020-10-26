package com.pcz.example.functional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * @author picongzhi
 */
public class SupplierDemo {
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void main(String[] args) {
        Supplier<LocalDateTime> supplier = LocalDateTime::now;
        System.out.println(supplier.get());

        Supplier<String> formatSupplier = () -> FORMATTER.format(LocalDateTime.now());
        System.out.println(formatSupplier.get());

        List list = supplier().get();
        System.out.println(list);

        System.out.println(factory(Developer::new));
        System.out.println(factory(() -> new Developer("pcz")));
    }

    private static Developer factory(Supplier<? extends Developer> supplier) {
        Developer developer = supplier.get();
        if (developer.getName() == null || "".equalsIgnoreCase(developer.getName())) {
            developer.setName("default");
        }
        developer.setSalary(BigDecimal.ONE);
        developer.setStart(LocalDate.of(2020, 10, 12));

        return developer;
    }

    private static <T> Supplier<List<T>> supplier() {
        return ArrayList::new;
    }

    private static class Developer {
        private String name;
        private BigDecimal salary;
        private LocalDate start;

        public Developer() {
        }

        public Developer(String name) {
            this.name = name;
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

        public LocalDate getStart() {
            return start;
        }

        public void setStart(LocalDate start) {
            this.start = start;
        }

        @Override
        public String toString() {
            return "Developer{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    ", start=" + start +
                    '}';
        }
    }
}
