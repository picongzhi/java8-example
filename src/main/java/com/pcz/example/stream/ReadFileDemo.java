package com.pcz.example.stream;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Stream;

/**
 * @author picongzhi
 */
public class ReadFileDemo {
    public static void main(String[] args) {
        readFileWithStream();
        bufferedReaderWithStream();
        bufferedReaderAndScanner();
    }

    private static void bufferedReaderAndScanner() {
        System.out.println("bufferedReaderAndScanner:");

        String fileName = "/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/lines.txt";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Scanner scanner = new Scanner(new File(fileName))) {
            while (scanner.hasNext()) {
                System.out.println(scanner.nextLine());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void bufferedReaderWithStream() {
        System.out.println("bufferedReaderWithStream:");

        String fileName = "/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/lines.txt";
        try (BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName))) {
            bufferedReader.lines().forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void readFileWithStream() {
        System.out.println("readFileWithStream:");

        String fileName = "/Users/picongzhi/workspace/java/idea/java8-example/src/main/resources/lines.txt";
        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream
                    .filter(line -> !line.startsWith("h"))
                    .map(String::toUpperCase)
                    .forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
