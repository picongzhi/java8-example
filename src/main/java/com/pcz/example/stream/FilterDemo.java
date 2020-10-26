package com.pcz.example.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
public class FilterDemo {
    public static void main(String[] args) {
        filterBeforeJava8();
        filterWithStream();

        getStudentBeforeJava8();
        getStudentWithStream();
    }

    private static void filterWithStream() {
        List<String> list = Arrays.asList("spring", "node", "java");
        System.out.println("filter with stream:");
        list.stream()
                .filter(s -> !"java".equals(s))
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    private static void filterBeforeJava8() {
        List<String> list = Arrays.asList("spring", "node", "java");
        List<String> result = getFilterOutput(list, "spring");
        System.out.println("filter before java 8:");
        for (String s : result) {
            System.out.println(s);
        }
    }

    private static List<String> getFilterOutput(List<String> list, String filter) {
        List<String> result = new ArrayList<>();
        for (String s : list) {
            if (!filter.equals(s)) {
                result.add(s);
            }
        }

        return result;
    }

    private static void getStudentWithStream() {
        List<Person> people = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );
        System.out.println(people.stream()
                .filter(p -> "jack".equals(p.getName()))
                .findAny()
                .orElse(null));
        System.out.println(people.stream()
                .filter(p -> "pcz".equals(p.getName()))
                .findAny()
                .orElse(null));
        System.out.println(people.stream()
                .filter(p -> "jack".equals(p.getName()) && 20 == p.getAge())
                .findAny()
                .orElse(null));

        System.out.println(people.stream()
                .filter(p -> "jack".equals(p.getName()))
                .map(Person::getName)
                .findAny()
                .orElse(""));
        System.out.println(people.stream()
                .map(Person::getName)
                .collect(Collectors.toList()));
    }

    private static void getStudentBeforeJava8() {
        List<Person> people = Arrays.asList(
                new Person("mkyong", 30),
                new Person("jack", 20),
                new Person("lawrence", 40)
        );
        System.out.println(getStudentByName(people, "jack"));
    }

    private static Person getStudentByName(List<Person> people, String name) {
        Person person = null;
        for (Person p : people) {
            if (name.equals(p.getName())) {
                person = p;
                break;
            }
        }

        return person;
    }

    private static class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
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
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
