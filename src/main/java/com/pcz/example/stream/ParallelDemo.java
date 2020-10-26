package com.pcz.example.stream;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author picongzhi
 */
public class ParallelDemo {
    private static final String DIR = System.getProperty("user.dir") + "/test/";

    public static void main(String[] args) throws IOException {
        baseStreamParallel();
        collectionParallel();
        isInParallelMode();
        calculation();
        saveFiles();
    }

    private static void saveFiles() throws IOException {
        Files.createDirectories(Paths.get(DIR));
        List<Employee> employees = generateEmployee(10000);
        employees.parallelStream().forEach(ParallelDemo::save);
    }

    private static void save(Employee employee) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(new File(DIR + employee.getName() + ".txt"));
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(employee);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void calculation() {
        System.out.println(Stream.iterate(0, n -> n + 1)
                .limit(1000)
                .parallel()
                .filter(ParallelDemo::isPrime)
                .peek(x -> System.out.printf("%s\t", x))
                .count());

        List<Employee> employees = generateEmployee(10000);
        System.out.println("average age: " + employees.parallelStream()
                .mapToInt(Employee::getAge)
                .average()
                .getAsDouble());
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }

        return IntStream.rangeClosed(2, num / 2).noneMatch(i -> num % i == 0);
    }

    private static void isInParallelMode() {
        IntStream intStream = IntStream.rangeClosed(1, 10);
        System.out.println("normal:");
        System.out.println(intStream.isParallel());
        intStream.forEach(x -> System.out.println(Thread.currentThread().getName()));

        IntStream parallelIntStream = IntStream.rangeClosed(1, 10).parallel();
        System.out.println("parallel:");
        System.out.println(parallelIntStream.isParallel());
        parallelIntStream.forEach(x -> System.out.println(Thread.currentThread().getName()));
    }

    private static void baseStreamParallel() {
        System.out.println("normal:");
        IntStream.rangeClosed(1, 10).forEach(System.out::println);

        System.out.println("parallel:");
        IntStream.rangeClosed(1, 10).parallel().forEach(System.out::println);
    }

    private static void collectionParallel() {
        System.out.println("normal:");
        getData().forEach(System.out::println);

        System.out.println("parallel:");
        getData().parallelStream().forEach(item -> {
            System.out.println(Thread.currentThread().getName() + ": " + item);
        });
    }

    private static List<String> getData() {
        List<String> list = new ArrayList<>();
        int n = 97;
        while (n <= 122) {
            char c = (char) n;
            list.add(String.valueOf(c));
            n++;
        }

        return list;
    }

    private static List<Employee> generateEmployee(int num) {
        return Stream.iterate(0, n -> n + 1)
                .limit(num)
                .map(n -> new Employee(
                        generateRandomName(4),
                        generateRandomAge(15, 100),
                        generateRandomSalary(900.00, 200_000.00)))
                .collect(Collectors.toList());
    }

    private static String generateRandomName(int length) {
        return new Random()
                .ints(length, 97, 122)
                .mapToObj(x -> String.valueOf((char) x))
                .collect(Collectors.joining());
    }

    private static int generateRandomAge(int min, int max) {
        return new Random()
                .ints(1, min, max)
                .findFirst()
                .getAsInt();
    }

    private static BigDecimal generateRandomSalary(double min, double max) {
        return new BigDecimal(new Random()
                .doubles(1, min, max)
                .findFirst()
                .getAsDouble())
                .setScale(2, RoundingMode.HALF_UP);
    }

    private static class Employee implements Serializable {
        private static final long serialVersionUID = -665807772768500312L;

        private String name;
        private int age;
        private BigDecimal salary;

        public Employee(String name, int age, BigDecimal salary) {
            this.name = name;
            this.age = age;
            this.salary = salary;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public BigDecimal getSalary() {
            return salary;
        }

        public void setSalary(BigDecimal salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Employee{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    '}';
        }
    }
}
