package com.pcz.example.stream;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author picongzhi
 */
public class ShouldStreamCloseDemo {
    public static void main(String[] args) {
        Stream<String> stream = Stream.of("A", "B", "C");
        stream.filter(x -> !x.equalsIgnoreCase("B"))
                .forEach(System.out::println);
//        stream.close();

        String fileName = "/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/lines.txt";
        try (Stream<String> lines = Files.lines(Paths.get(fileName))) {
            System.out.println(lines.collect(Collectors.joining(System.lineSeparator())));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
