package com.pcz.example.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
public class SortListDemo {
    public static void main(String[] args) {
        sortByNaturalOrder();
        sortByReverseOrder();
        sortObject();
    }

    private static void sortObject() {
        List<User> users = Arrays.asList(
                new User("C", 30),
                new User("D", 40),
                new User("A", 10),
                new User("B", 20),
                new User("E", 50)
        );

        System.out.println(users.stream()
                .sorted((u1, u2) -> u1.getAge() - u2.getAge())
                .collect(Collectors.toList()));

        System.out.println(users.stream()
                .sorted(Comparator.comparingInt(User::getAge))
                .collect(Collectors.toList()));

        System.out.println(users.stream()
                .sorted(Comparator.comparingInt(User::getAge).reversed())
                .collect(Collectors.toList()));

        System.out.println(users.stream()
                .sorted((u1, u2) -> u1.getName().compareTo(u2.getName()))
                .collect(Collectors.toList()));

        System.out.println(users.stream()
                .sorted(Comparator.comparing(User::getName))
                .collect(Collectors.toList()));
    }

    private static void sortByReverseOrder() {
        List<String> list = Arrays.asList("9", "A", "Z", "1", "B", "Y", "4", "a", "c");
        System.out.println(list.stream()
                .sorted((a, b) -> b.compareTo(a))
                .collect(Collectors.joining(" ")));
        System.out.println(list.stream()
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.joining(" ")));
    }

    private static void sortByNaturalOrder() {
        List<String> list = Arrays.asList("9", "A", "Z", "1", "B", "Y", "4", "a", "c");
        System.out.println(list.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.joining(" ")));
        System.out.println(list.stream()
                .sorted((a, b) -> a.compareTo(b))
                .collect(Collectors.joining(" ")));
        System.out.println(list.stream()
                .sorted()
                .collect(Collectors.joining(" ")));
    }

    private static class User {
        private String name;
        private int age;

        public User(String name, int age) {
            this.name = name;
            this.age = age;
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

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
