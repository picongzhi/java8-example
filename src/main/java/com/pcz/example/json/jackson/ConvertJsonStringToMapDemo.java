package com.pcz.example.json.jackson;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author picongzhi
 */
public class ConvertJsonStringToMapDemo {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        jsonStringToMap();
        mapToJsonString();
    }

    private static void mapToJsonString() {
        Map<String, String> map = new HashMap<>();
        map.put("name", "picongzhi");
        map.put("age", "27");

        try {
            String jsonStr = OBJECT_MAPPER.writeValueAsString(map);
            System.out.println(jsonStr);

            jsonStr = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(map);
            System.out.println(jsonStr);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void jsonStringToMap() {
        String jsonStr = "{\"name\": \"picongzhi\", \"age\": \"27\"}";
        try {
            Map<String, String> map = OBJECT_MAPPER.readValue(jsonStr, Map.class);
            System.out.println(map);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
