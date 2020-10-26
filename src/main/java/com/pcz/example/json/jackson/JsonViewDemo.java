package com.pcz.example.json.jackson;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author picongzhi
 */
public class JsonViewDemo {
    public static void main(String[] args) {
        ObjectMapper objectMapper = new ObjectMapper();
        Staff staff = createStaff();

        try {
            String jsonStr = objectMapper.writerWithView(CompanyViews.Normal.class).writeValueAsString(staff);
            System.out.println(jsonStr);

            jsonStr = objectMapper.writerWithView(CompanyViews.Manager.class).writeValueAsString(staff);
            System.out.println(jsonStr);

            jsonStr = objectMapper.writerWithView(CompanyViews.HR.class).writeValueAsString(staff);
            System.out.println(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Staff createStaff() {
        Staff staff = new Staff();
        staff.setName("picongzhi");
        staff.setAge(27);
        staff.setPosition(new String[]{"Developer", "CTO"});
        Map<String, BigDecimal> salary = new HashMap<>() {
            {
                put("2010", new BigDecimal(10000));
                put("2015", new BigDecimal(20000));
                put("2020", new BigDecimal(30000));
            }
        };
        staff.setSalary(salary);
        staff.setSkills(Arrays.asList("java", "python", "node", "javascript"));

        return staff;
    }

    private static class Staff {
        @JsonView(CompanyViews.Normal.class)
        private String name;

        @JsonView(CompanyViews.Normal.class)
        private int age;

        @JsonView({CompanyViews.HR.class, CompanyViews.Manager.class})
        private String[] position;

        @JsonView(CompanyViews.Manager.class)
        private List<String> skills;

        @JsonView(CompanyViews.HR.class)
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

    private static class CompanyViews {
        public static class Normal {
        }

        public static class Manager extends Normal {
        }

        public static class HR extends Normal {
        }
    }
}
