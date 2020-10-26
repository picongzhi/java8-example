package com.pcz.example.functional;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
public class BiPredicateDemo {
    public static void main(String[] args) {
        BiPredicate<String, Integer> biPredicate = (x, y) -> x.length() == y;
        System.out.println(biPredicate.test("mkyong", 6));
        System.out.println(biPredicate.test("java", 10));

        biPredicateAsArgument();
    }

    private static void biPredicateAsArgument() {
        List<Domain> domains = Arrays.asList(
                new Domain("google.com", 1),
                new Domain("i-am-spammer.com", 10),
                new Domain("mkyong.com", 0),
                new Domain("microsoft.com", 2)
        );

        BiPredicate<String, Integer> biPredicate = (name, score) ->
                name.equalsIgnoreCase("google.com") || score == 0;
        System.out.println(filterBadDomain(domains, biPredicate));

        System.out.println(filterBadDomain(domains, (name, score) -> score == 0));

        System.out.println(filterBadDomain(domains, (name, score) -> name.startsWith("i") && score > 5));

        System.out.println(filterBadDomain(domains,
                biPredicate.or((name, score) -> name.equalsIgnoreCase("microsoft.com"))));
    }

    private static <T extends Domain> List<T> filterBadDomain(List<T> list, BiPredicate<String, Integer> biPredicate) {
        return list.stream()
                .filter(x -> biPredicate.test(x.getName(), x.getScore()))
                .collect(Collectors.toList());
    }

    private static class Domain {
        private String name;
        private Integer score;

        public Domain(String name, Integer score) {
            this.name = name;
            this.score = score;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getScore() {
            return score;
        }

        public void setScore(Integer score) {
            this.score = score;
        }

        @Override
        public String toString() {
            return "Domain{" +
                    "name='" + name + '\'' +
                    ", score=" + score +
                    '}';
        }
    }
}
