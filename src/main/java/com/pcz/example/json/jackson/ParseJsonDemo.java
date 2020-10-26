package com.pcz.example.json.jackson;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class ParseJsonDemo {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        objectToJson();
        jsonToObject();
        jsonView();
    }

    private static void jsonView() {
        Staff staff = createStaff();
        try {
            String normalView = OBJECT_MAPPER
                    .writerWithView(CompanyViews.Normal.class)
                    .writeValueAsString(staff);
            System.out.println("normal view: " + normalView);

            String managerView = OBJECT_MAPPER
                    .writerWithView(CompanyViews.Manager.class)
                    .writeValueAsString(staff);
            System.out.println("manager view: " + managerView);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void jsonToObject() {
        String jsonStr = "{\"name\": \"picongzhi\", \"age\": 27, \"skills\": [\"java\", \"python\"]}";
        try {
            Staff staff = OBJECT_MAPPER.readValue(jsonStr, Staff.class);
            System.out.println(staff);

            jsonStr = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
            System.out.println(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void objectToJson() {
        Staff staff = createStaff();
        try {
            String jsonStr = OBJECT_MAPPER.writeValueAsString(staff);
            System.out.println(jsonStr);

            jsonStr = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(staff);
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
                put("2015", new BigDecimal(10000));
                put("2018", new BigDecimal(20000));
                put("2020", new BigDecimal(30000));
            }
        };
        staff.setSalary(salary);
        staff.setSkills(Arrays.asList("java", "python", "node"));

        return staff;
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private static class Staff {
        @JsonView(CompanyViews.Normal.class)
        @JsonProperty("name")
        private String name;

        @JsonView(CompanyViews.Normal.class)
        private int age;

        @JsonView(CompanyViews.Manager.class)
        private String[] position;

        @JsonView(CompanyViews.Manager.class)
        private List<String> skills;

        @JsonView(CompanyViews.Manager.class)
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
    }
}
