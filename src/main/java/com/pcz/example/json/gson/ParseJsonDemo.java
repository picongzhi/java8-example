package com.pcz.example.json.gson;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.annotations.Expose;
import com.pcz.example.json.jackson.PrettyPrintDemo;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author picongzhi
 */
public class ParseJsonDemo {
    public static void main(String[] args) {
        objectToJson();
        jsonToObject();

        excludeFields();
        customExclusionStrategy();
    }

    private static void customExclusionStrategy() {
        Gson gson = new GsonBuilder()
                .setExclusionStrategies(new CustomExclusionStrategy(List.class))
                .create();
        Staff staff = createStaff();
        System.out.println(gson.toJson(staff));
    }

    private static void excludeFields() {
        Gson gson = new GsonBuilder()
                .excludeFieldsWithModifiers(Modifier.STATIC, Modifier.TRANSIENT)
                .create();

        gson = new GsonBuilder()
                .setPrettyPrinting()
                .excludeFieldsWithoutExposeAnnotation()
                .create();
        Staff staff = createStaff();
        System.out.println(gson.toJson(staff));
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
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        Staff staff = createStaff();
        String jsonStr = gson.toJson(staff);
        System.out.println(jsonStr);

        try (FileWriter fileWriter = new FileWriter(
                "/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/staff.json")) {
            gson.toJson(staff, fileWriter);
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

    private static class Staff {
        @Expose(serialize = true, deserialize = true)
        private String name;

        @Expose
        private int age;

        @Expose(serialize = false, deserialize = true)
        private String[] position;

        @ExcludeField
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

    private static class CustomExclusionStrategy implements ExclusionStrategy {
        private final Class<?> typeToSkip;

        public CustomExclusionStrategy(Class<?> typeToSkip) {
            this.typeToSkip = typeToSkip;
        }

        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            if ("salary".equals(f.getName())) {
                return true;
            }

            if (f.getAnnotation(ExcludeField.class) != null) {
                return true;
            }

            return false;
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return clazz == typeToSkip;
        }
    }

    @Retention(RetentionPolicy.RUNTIME)
    @Target({ElementType.FIELD})
    private @interface ExcludeField {
    }
}
