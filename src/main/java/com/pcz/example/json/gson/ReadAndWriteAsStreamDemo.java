package com.pcz.example.json.gson;

import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author picongzhi
 */
public class ReadAndWriteAsStreamDemo {
    public static void main(String[] args) {
        jsonWriter();
        jsonReader();
    }

    private static void jsonReader() {
        try (JsonReader reader = new JsonReader(new FileReader("/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/user.json"))) {
            reader.beginObject();
            while (reader.hasNext()) {
                String name = reader.nextName();
                if (name.equals("name")) {
                    System.out.println(reader.nextString());
                } else if (name.equals("age")) {
                    System.out.println(reader.nextInt());
                } else if (name.equals("messages")) {
                    reader.beginArray();
                    while (reader.hasNext()) {
                        System.out.println(reader.nextString());
                    }
                    reader.endArray();
                }
            }
            reader.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void jsonWriter() {
        try (JsonWriter writer = new JsonWriter(
                new FileWriter("/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/user.json"))) {
            writer.beginObject();

            writer.name("name").value("picongzhi");
            writer.name("age").value(27);
            writer.name("messages");
            writer.beginArray();
            writer.value("1");
            writer.value("2");
            writer.value("3");
            writer.endArray();

            writer.endObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
