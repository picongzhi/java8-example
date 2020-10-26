package com.pcz.example.comparator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * @author picongzhi
 */
public class ComparatorDemo {
    public static void main(String[] args) {
        sortWithoutLambda();
        sortWithLambda();
        reverseSort();
    }

    private static List<Developer> getDevelopers() {
        List<Developer> developers = new ArrayList<>();
        developers.add(new Developer("mkyong", new BigDecimal("70000"), 33));
        developers.add(new Developer("alvin", new BigDecimal("80000"), 20));
        developers.add(new Developer("jason", new BigDecimal("100000"), 10));
        developers.add(new Developer("iris", new BigDecimal("170000"), 55));

        return developers;
    }

    private static void reverseSort() {
        List<Developer> developers = getDevelopers();
        System.out.println("before:");
        developers.forEach(System.out::println);

        Comparator<Developer> comparator = (o1, o2) -> o1.getSalary().compareTo(o2.getSalary());
        developers.sort(comparator.reversed());
        System.out.println("after reverse sort by salary:");
        developers.forEach(System.out::println);
    }

    private static void sortWithLambda() {
        List<Developer> developers = getDevelopers();
        System.out.println("before:");
        developers.forEach(System.out::println);

//        developers.sort((Developer o1, Developer o2) -> o1.getAge() - o2.getAge());
        developers.sort((o1, o2) -> o1.getAge() - o2.getAge());
        System.out.println("after sort by age:");
        developers.forEach(System.out::println);

        developers.sort(((o1, o2) -> o1.getName().compareTo(o2.getName())));
        System.out.println("after sort by name:");
        developers.forEach(System.out::println);

        developers.sort(((o1, o2) -> o1.getSalary().compareTo(o2.getSalary())));
        System.out.println("after sort by salary:");
        developers.forEach(System.out::println);
    }

    private static void sortWithoutLambda() {
        List<Developer> developers = getDevelopers();
        System.out.println("before:");
        developers.forEach(System.out::println);

        Collections.sort(developers, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getAge() - o2.getAge();
            }
        });
        System.out.println("after sort by age:");
        developers.forEach(System.out::println);

        Collections.sort(developers, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        System.out.println("after sort by name:");
        developers.forEach(System.out::println);

        Collections.sort(developers, new Comparator<Developer>() {
            @Override
            public int compare(Developer o1, Developer o2) {
                return o1.getSalary().compareTo(o2.getSalary());
            }
        });
        System.out.println("after sort by salary:");
        developers.forEach(System.out::println);
    }

    static class Developer {
        private String name;
        private BigDecimal salary;
        private int age;

        public Developer(String name, BigDecimal salary, int age) {
            this.name = name;
            this.salary = salary;
            this.age = age;
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

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "Developer{" +
                    "name='" + name + '\'' +
                    ", salary=" + salary +
                    ", age=" + age +
                    '}';
        }
    }
}
