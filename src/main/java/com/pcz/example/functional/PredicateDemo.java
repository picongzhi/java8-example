package com.pcz.example.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
public class PredicateDemo {
    public static void main(String[] args) {
        predicateInFilter();
        predicateAnd();
        predicateOr();
        predicateNegate();
        predicateInFunction();
        predicateChain();
        predicateInObject();
    }

    private static void predicateInObject() {
        List<Hosting> hostings = Arrays.asList(
                new Hosting(1, "amazon", "aws.amazon.com"),
                new Hosting(2, "linode", "linode.com"),
                new Hosting(3, "liquidweb", "liquidweb.com"),
                new Hosting(4, "google", "google.com")
        );

        System.out.println(HostingRepository.filterHosting(hostings, hosting -> hosting.getName().startsWith("g")));
        System.out.println(HostingRepository.filterHosting(hostings, isDeveloperFriendly()));
    }

    private static Predicate<Hosting> isDeveloperFriendly() {
        return x -> x.getName().equals("linode");
    }

    private static class HostingRepository {
        private static List<Hosting> filterHosting(List<Hosting> hostings, Predicate<Hosting> predicate) {
            return hostings.stream().filter(predicate).collect(Collectors.toList());
        }
    }

    private static void predicateChain() {
        Predicate<String> startWithA = x -> x.startsWith("a");
        System.out.println(startWithA.or(x -> x.startsWith("m")).test("mkyong"));
        System.out.println(startWithA.and(x -> x.length() == 3).negate().test("abc"));
    }

    private static void predicateInFunction() {
        List<String> list = Arrays.asList("A", "AA", "AAA", "B", "BB", "BBB");
        System.out.println(StringProcessor.filter(list, x -> x.startsWith("A")));
        System.out.println(StringProcessor.filter(list, x -> x.startsWith("A") && x.length() == 3));
    }

    private static void predicateNegate() {
        List<String> list = Arrays.asList("A", "AA", "AAA", "B", "BB", "BBB");

        Predicate<String> startWithA = x -> x.startsWith("A");
        System.out.println(list.stream().filter(startWithA.negate()).collect(Collectors.toList()));
    }

    private static void predicateOr() {
        List<String> list = Arrays.asList("A", "AA", "AAA", "B", "BB", "BBB");

        Predicate<String> lengthIs3 = x -> x.length() == 3;
        Predicate<String> startWithA = x -> x.startsWith("A");
        System.out.println(list.stream().filter(lengthIs3.or(startWithA)).collect(Collectors.toList()));
    }

    private static void predicateAnd() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list.stream().filter(x -> x > 5 && x < 8).collect(Collectors.toList()));

        Predicate<Integer> noGreaterThan5 = x -> x > 5;
        Predicate<Integer> noLessThan8 = x -> x < 8;
        System.out.println(list.stream().filter(noGreaterThan5.and(noLessThan8)).collect(Collectors.toList()));
    }

    private static void predicateInFilter() {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        System.out.println(list.stream().filter(x -> x > 5).collect(Collectors.toList()));

        Predicate<Integer> predicate = x -> x > 5;
        System.out.println(list.stream().filter(predicate).collect(Collectors.toList()));
    }

    private static class StringProcessor {
        static List<String> filter(List<String> list, Predicate<String> predicate) {
            return list.stream().filter(predicate).collect(Collectors.toList());
        }
    }

    private static class Hosting {
        private int id;
        private String name;
        private String url;

        public Hosting(int id, String name, String url) {
            this.id = id;
            this.name = name;
            this.url = url;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        @Override
        public String toString() {
            return "Hosting{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", url='" + url + '\'' +
                    '}';
        }
    }
}
