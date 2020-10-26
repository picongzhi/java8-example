package com.pcz.example.stream;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author picongzhi
 */
public class ForEachDemo {
    public static void main(String[] args) {
        loopMap();
        loopList();
    }

    private static void loopMap() {
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 10);
        map.put("B", 20);
        map.put("C", 30);
        map.put("D", 40);
        map.put("E", 50);
        map.put("F", 60);

        System.out.println("map normal loop:");
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey() + ", value: " + entry.getValue());
        }

        System.out.println("map forEach loop:");
        map.forEach((key, value) -> System.out.println("key: " + key + ", value: " + value));
    }

    private static void loopList() {
        List<String> list = new ArrayList<>();
        list.add("A");
        list.add("B");
        list.add("C");
        list.add("D");
        list.add("E");

        System.out.println("list normal loop:");
        for (String item : list) {
            System.out.println(item);
        }

        System.out.println("list forEach loop:");
        list.forEach(System.out::println);
    }
}
