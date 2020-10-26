package com.pcz.example.stream;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author picongzhi
 */
public class MapDemo {
    public static void main(String[] args) {
        stringsToUppercase();
        objectToString();
        objectToObject();
    }

    private static void objectToObject() {
        List<Staff> staffList = Arrays.asList(
                new Staff("mkyong", 30, new BigDecimal(10000)),
                new Staff("jack", 27, new BigDecimal(20000)),
                new Staff("lawrence", 33, new BigDecimal(30000))
        );

        List<StaffPublic> staffPublicList = new ArrayList<>();
        for (Staff staff : staffList) {
            StaffPublic staffPublic = new StaffPublic();
            staffPublic.setName(staff.getName());
            staffPublic.setAge(staff.getAge());
            if ("mkyong".equals(staff.getName())) {
                staffPublic.setExtra("extra");
            }

            staffPublicList.add(staffPublic);
        }
        System.out.println("before java 8:");
        staffPublicList.forEach(System.out::println);

        System.out.println("java 8:");
        staffList.stream()
                .map(staff -> {
                    StaffPublic staffPublic = new StaffPublic();
                    staffPublic.setName(staff.getName());
                    staffPublic.setAge(staff.getAge());
                    if ("mkyong".equals(staff.getName())) {
                        staffPublic.setExtra("extra");
                    }

                    return staffPublic;
                }).forEach(System.out::println);
    }

    private static void objectToString() {
        List<Staff> staffList = Arrays.asList(
                new Staff("mkyong", 30, new BigDecimal(10000)),
                new Staff("jack", 27, new BigDecimal(20000)),
                new Staff("lawrence", 33, new BigDecimal(30000))
        );

        List<String> names = new ArrayList<>();
        for (Staff staff : staffList) {
            names.add(staff.getName());
        }
        System.out.println("before java 8:");
        names.forEach(System.out::println);

        System.out.println("java 8:");
        staffList.stream().map(Staff::getName).forEach(System.out::println);
    }

    private static void stringsToUppercase() {
        List<String> list = Arrays.asList("a", "b", "c", "d");
        System.out.println("before:");
        list.forEach(System.out::println);

        List<String> upperList = new ArrayList<>();
        for (String s : list) {
            upperList.add(s.toUpperCase());
        }
        System.out.println("before java 8:");
        upperList.forEach(System.out::println);

        System.out.println("java 8:");
        list.stream().map(String::toUpperCase).forEach(System.out::println);

        List<Integer> nums = Arrays.asList(1, 2, 3, 4, 5);
        nums.stream().map(n -> n * 2).forEach(System.out::println);
    }

    private static class Staff {
        private String name;
        private int age;
        private BigDecimal salary;

        public Staff() {
        }

        public Staff(String name, int age, BigDecimal salary) {
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
            return "Staff{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", salary=" + salary +
                    '}';
        }
    }

    private static class StaffPublic {
        private String name;
        private int age;
        private String extra;

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

        public String getExtra() {
            return extra;
        }

        public void setExtra(String extra) {
            this.extra = extra;
        }

        @Override
        public String toString() {
            return "StaffPublic{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", extra='" + extra + '\'' +
                    '}';
        }
    }
}
