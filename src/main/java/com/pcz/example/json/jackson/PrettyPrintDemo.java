package com.pcz.example.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author picongzhi
 */
public class PrettyPrintDemo {
    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);
        Staff staff = createStaff();
        String jsonStr = objectMapper.writeValueAsString(staff);
        System.out.println(jsonStr);

        jsonStr = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
        System.out.println(jsonStr);
    }

    private static Staff createStaff() {
        Staff staff = new Staff();
        staff.setName("picongzhi");
        staff.setAge(27);
        staff.setPosition(new String[]{"Developer", "CTO"});
        Map<String, BigDecimal> salary = new HashMap<>() {
            {
                put("2015", new BigDecimal(10000));
                put("2018", new BigDecimal(20000));
                put("2020", new BigDecimal(30000));
            }
        };
        staff.setSalary(salary);
        staff.setSkills(Arrays.asList("java", "python", "node"));

        return staff;
    }

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
