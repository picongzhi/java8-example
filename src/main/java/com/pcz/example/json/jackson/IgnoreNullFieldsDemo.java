package com.pcz.example.json.jackson;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author picongzhi
 */
public class IgnoreNullFieldsDemo {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        Staff staff = new Staff();
        staff.setName("picongzhi");
        staff.setAge(27);

        try {
            String jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
            System.out.println(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class Staff {
        private String name;
        private int age;
        private String[] position;
        private List<String> skills;
        private Map<String, BigDecimal> salary;

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

        public String[] getPosition() {
            return position;
        }

        public void setPosition(String[] position) {
            this.position = position;
        }

        public List<String> getSkills() {
            return skills;
        }

        public void setSkills(List<String> skills) {
            this.skills = skills;
        }

        public Map<String, BigDecimal> getSalary() {
            return salary;
        }

        public void setSalary(Map<String, BigDecimal> salary) {
            this.salary = salary;
        }

        @Override
        public String toString() {
            return "Staff{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", position=" + Arrays.toString(position) +
                    ", skills=" + skills +
                    ", salary=" + salary +
                    '}';
        }
    }
}
