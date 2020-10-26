package com.pcz.example.json.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author picongzhi
 */
public class ConvertObjectToJsonDemo {
    public static void main(String[] args) {
        objectToJson();
        jsonToObject();

        convertToJsonElement();
    }

    private static void convertToJsonElement() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (Reader reader = new FileReader(
                "/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/staff.json")) {
            JsonElement jsonElement = gson.fromJson(reader, JsonElement.class);
            System.out.println(gson.toJson(jsonElement));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void jsonToObject() {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(
                "/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/staff.json")) {
            Staff staff = gson.fromJson(reader, Staff.class);
            System.out.println(staff);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void objectToJson() {
//        Gson gson = new Gson();
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Staff staff = createStaff();
        System.out.println(gson.toJson(staff));

        try (FileWriter writer = new FileWriter(
                "/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/staff.json")) {
            gson.toJson(staff, writer);
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
        staff.setSkills(Arrays.asList("java", "python", "node", "go"));

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
