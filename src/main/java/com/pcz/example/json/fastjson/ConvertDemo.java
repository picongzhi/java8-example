package com.pcz.example.json.fastjson;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;
import java.util.*;

/**
 * @author picongzhi
 */
public class ConvertDemo {
    public static void main(String[] args) {
        objectToJson();
        jsonToJava();
    }

    private static void jsonToJava() {
        String jsonStr = "{\"name\": \"picongzhi\", \"age\": 27}";
        Staff staff = JSON.parseObject(jsonStr, Staff.class);
        System.out.println(staff);

        jsonStr = "[{\"name\": \"picongzhi\", \"age\": 27}, {\"name\": \"pcz\", \"age\": 18}]";
        List<Staff> staffList = JSON.parseArray(jsonStr, Staff.class);
        System.out.println(staffList);
    }

    private static void objectToJson() {
        Staff staff = createStaff();

        String jsonStr = JSON.toJSONString(staff);
        System.out.println(jsonStr);

        jsonStr = JSON.toJSONString(staff, true);
        System.out.println(jsonStr);

        jsonStr = JSON.toJSONStringWithDateFormat(staff, "dd/MM/yyyy HH:mm:ss");
        System.out.println(jsonStr);

        List<Staff> staffList = Arrays.asList(createStaff(), createStaff());
        jsonStr = JSON.toJSONStringWithDateFormat(staffList, "dd/MM/yyyy HH:mm:ss");
        System.out.println(jsonStr);
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
        staff.setJoinDate(new Date());

        return staff;
    }

    private static class Staff {
        private String name;
        private int age;
        private String[] position;
        private List<String> skills;
        private Map<String, BigDecimal> salary;
        private Date joinDate;

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

        public Date getJoinDate() {
            return joinDate;
        }

        public void setJoinDate(Date joinDate) {
            this.joinDate = joinDate;
        }

        @Override
        public String toString() {
            return "Staff{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", position=" + Arrays.toString(position) +
                    ", skills=" + skills +
                    ", salary=" + salary +
                    ", joinDate=" + joinDate +
                    '}';
        }
    }
}
