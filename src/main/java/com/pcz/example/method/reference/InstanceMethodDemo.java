package com.pcz.example.method.reference;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author picongzhi
 */
public class InstanceMethodDemo {
    public static void main(String[] args) {
        instanceMethodSort();
    }

    private static void instanceMethodSort() {
        List<Employee> employees = Arrays.asList(
                new Employee("mkyong", 38, BigDecimal.valueOf(3800)),
                new Employee("zilap", 5, BigDecimal.valueOf(100)),
                new Employee("ali", 25, BigDecimal.valueOf(2500)),
                new Employee("unknown", 99, BigDecimal.valueOf(9999))
        );
        System.out.println("before:");
        employees.forEach(System.out::println);

        ComparatorProvider provider = new ComparatorProvider();
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee o1, Employee o2) {
                return provider.compareBySalary(o1, o2);
            }
        });
        System.out.println("sort by salary:");
        employees.forEach(System.out::println);

        employees.sort(((o1, o2) -> provider.compareByAge(o1, o2)));
        System.out.println("sort by age:");
        employees.forEach(System.out::println);

        employees.sort(provider::compareByName);
        System.out.println("sort by name:");
        employees.forEach(System.out::println);
    }

    static class Employee {
        private String name;
        private Integer age;
        private BigDecimal salary;

        public Employee(String name, Integer age, BigDecimal salary) {
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

        public Integer getAge() {
            return age;
        }

        public void setAge(Integer age) {
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

    static class ComparatorProvider {
        public int compareByAge(Employee e1, Employee e2) {
            return e1.getAge().compareTo(e2.getAge());
        }

        public int compareByName(Employee e1, Employee e2) {
            return e1.getName().compareTo(e2.getName());
        }

        public int compareBySalary(Employee e1, Employee e2) {
            return e1.getSalary().compareTo(e2.getSalary());
        }
    }
}
