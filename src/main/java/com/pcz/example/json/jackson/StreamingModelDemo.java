package com.pcz.example.json.jackson;

import com.fasterxml.jackson.core.JsonEncoding;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

/**
 * @author picongzhi
 */
public class StreamingModelDemo {
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public static void main(String[] args) {
        jsonGeneratorWriteJson();
        jsonGeneratorWriteJsonArray();

        jsonParserReadJson();
        jsonParserReadJsonArray();
    }

    private static void jsonParserReadJsonArray() {
        String file = "/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/user4.json";
        try (JsonParser parser = OBJECT_MAPPER.getFactory().createParser(new File(file))) {
            if (parser.nextToken() == JsonToken.START_ARRAY) {
                while (parser.nextToken() != JsonToken.END_ARRAY) {
                    while (parser.nextToken() != JsonToken.END_OBJECT) {
                        String fieldName = parser.getCurrentName();
                        if ("name".equals(fieldName)) {
                            parser.nextToken();
                            System.out.println(parser.getText());
                        }

                        if ("age".equals(fieldName)) {
                            parser.nextToken();
                            System.out.println(parser.getIntValue());
                        }

                        if ("messages".equals(fieldName)) {
                            if (parser.nextToken() == JsonToken.START_ARRAY) {
                                while (parser.nextToken() != JsonToken.END_ARRAY) {
                                    System.out.println(parser.getText());
                                }
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void jsonParserReadJson() {
        String file = "/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/user3.json";
        try (JsonParser parser = OBJECT_MAPPER.getFactory().createParser(new File(file))) {
            while (parser.nextToken() != JsonToken.END_OBJECT) {
                String fieldName = parser.getCurrentName();
                if ("name".equals(fieldName)) {
                    parser.nextToken();
                    System.out.println(parser.getText());
                }

                if ("age".equals(fieldName)) {
                    parser.nextToken();
                    System.out.println(parser.getIntValue());
                }

                if ("messages".equals(fieldName)) {
                    if (parser.nextToken() == JsonToken.START_ARRAY) {
                        while (parser.nextToken() != JsonToken.END_ARRAY) {
                            System.out.println(parser.getText());
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void jsonGeneratorWriteJsonArray() {
        String file = "/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/user4.json";
        try (JsonGenerator generator = OBJECT_MAPPER.getFactory().createGenerator(
                new File(file), JsonEncoding.UTF8)) {
            generator.useDefaultPrettyPrinter();

            generator.writeStartArray();

            generator.writeStartObject();

            generator.writeStringField("name", "picongzhi");
            generator.writeNumberField("age", 27);

            generator.writeFieldName("messages");
            generator.writeStartArray();
            generator.writeString("a");
            generator.writeString("b");
            generator.writeString("c");
            generator.writeEndArray();

            generator.writeEndObject();

            generator.writeStartObject();

            generator.writeStringField("name", "pcz");
            generator.writeNumberField("age", 18);

            generator.writeFieldName("messages");
            generator.writeStartArray();
            generator.writeString("c");
            generator.writeString("d");
            generator.writeString("e");
            generator.writeEndArray();

            generator.writeEndObject();

            generator.writeEndArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void jsonGeneratorWriteJson() {
        String file = "/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/user3.json";
        try (JsonGenerator generator = OBJECT_MAPPER.getFactory().createGenerator(
                new File(file), JsonEncoding.UTF8)) {
            generator.writeStartObject();

            generator.writeStringField("name", "picongzhi");
            generator.writeNumberField("age", 28);

            generator.writeFieldName("messages");
            generator.writeStartArray();
            generator.writeString("a");
            generator.writeString("b");
            generator.writeString("c");
            generator.writeEndArray();

            generator.writeEndObject();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
