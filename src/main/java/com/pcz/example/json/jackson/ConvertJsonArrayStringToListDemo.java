package com.pcz.example.json.jackson;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * @author picongzhi
 */
public class ConvertJsonArrayStringToListDemo {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        jsonArrayStringToList();
    }

    private static void jsonArrayStringToList() {
        String jsonStr = "[{\"name\": \"picongzhi\", \"age\": 27}, {\"name\": \"pcz\", \"age\": 18}]";
        try {
            Person[] people = OBJECT_MAPPER.readValue(jsonStr, Person[].class);
            for (Person person : people) {
                System.out.println(person);
            }

            List<Person> personList = Arrays.asList(people);
            personList.forEach(System.out::println);

            personList = OBJECT_MAPPER.readValue(jsonStr, new TypeReference<List<Person>>() {
            });
            personList.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static class Person {
        private String name;
        private Integer age;

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

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
