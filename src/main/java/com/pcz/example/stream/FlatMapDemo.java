package com.pcz.example.stream;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

/**
 * @author picongzhi
 */
public class FlatMapDemo {
    public static void main(String[] args) {
        arrStreamFlatMap();
        setStreamFlatMap();
        primitiveStreamFlatMap();
    }

    private static void primitiveStreamFlatMap() {
        int[] arr = {1, 2, 3, 4, 5, 6};
        Stream.of(arr).flatMapToInt(Arrays::stream).forEach(System.out::println);
    }

    private static void arrStreamFlatMap() {
        String[][] arr = new String[][]{
                {"a", "b"},
                {"c", "d"},
                {"e", "f"}
        };
        Arrays.stream(arr)
                .filter(x -> "a".equals(Arrays.toString(x)))
                .forEach(System.out::println);

        Arrays.stream(arr)
                .flatMap(Arrays::stream)
                .filter("a"::equals)
                .forEach(System.out::println);
    }

    private static void setStreamFlatMap() {
        Student student1 = new Student();
        student1.setName("mkyong");
        student1.addBook("Java 8 in Action");
        student1.addBook("Spring Boot in Action");
        student1.addBook("Effective Java (2nd Edition)");

        Student student2 = new Student();
        student2.setName("zilap");
        student2.addBook("Learning Python, 5th Edition");
        student2.addBook("Effective Java (2nd Edition)");

        List<Student> students = Arrays.asList(student1, student2);
        students.stream()
                .map(Student::getBooks)
                .flatMap(Set::stream)
                .distinct()
                .forEach(System.out::println);
    }

    private static class Student {
        private String name;
        private Set<String> books;

        public void addBook(String book) {
            if (this.books == null) {
                this.books = new HashSet<>();
            }
            this.books.add(book);
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Set<String> getBooks() {
            return books;
        }

        public void setBooks(Set<String> books) {
            this.books = books;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "name='" + name + '\'' +
                    ", books=" + books +
                    '}';
        }
    }
}
