package com.pcz.example.convert;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author picongzhi
 */
public class ConvertListToMapDemo {
    public static void main(String[] args) {
        collectorsToMap();
        duplicatedKey();
        sortAndCollect();
    }

    private static void sortAndCollect() {
        List<Hosting> hostings = new ArrayList<>();
        hostings.add(new Hosting(1, "liquidweb.com", 80000));
        hostings.add(new Hosting(2, "linode.com", 90000));
        hostings.add(new Hosting(3, "digitalocean.com", 120000));
        hostings.add(new Hosting(4, "aws.amazon.com", 200000));
        hostings.add(new Hosting(5, "mkyong.com", 1));
        hostings.add(new Hosting(6, "linode.com", 100000));

        Map<String, Long> map = hostings.stream()
                .sorted(Comparator.comparing(Hosting::getWebsites))
                .collect(Collectors.toMap(Hosting::getName, Hosting::getWebsites,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        System.out.println(map);
    }

    private static void collectorsToMap() {
        List<Hosting> hostings = new ArrayList<>();
        hostings.add(new Hosting(1, "liquidweb.com", 80000));
        hostings.add(new Hosting(2, "linode.com", 90000));
        hostings.add(new Hosting(3, "digitalocean.com", 120000));
        hostings.add(new Hosting(4, "aws.amazon.com", 200000));
        hostings.add(new Hosting(5, "mkyong.com", 1));

        Map<Integer, String> idToNameMap = hostings.stream()
                .collect(Collectors.toMap(Hosting::getId, Hosting::getName));
        System.out.println(idToNameMap);

        Map<String, Long> nameToWebsitesMap = hostings.stream()
                .collect(Collectors.toMap(Hosting::getName, Hosting::getWebsites));
        System.out.println(nameToWebsitesMap);

        idToNameMap = hostings.stream()
                .collect(Collectors.toMap(hosting -> hosting.getId(), hosting -> hosting.getName()));
        System.out.println(idToNameMap);
    }

    private static void duplicatedKey() {
        List<Hosting> hostings = new ArrayList<>();
        hostings.add(new Hosting(1, "liquidweb.com", 80000));
        hostings.add(new Hosting(2, "linode.com", 90000));
        hostings.add(new Hosting(3, "digitalocean.com", 120000));
        hostings.add(new Hosting(4, "aws.amazon.com", 200000));
        hostings.add(new Hosting(5, "mkyong.com", 1));
        hostings.add(new Hosting(6, "linode.com", 100000));

        Map<String, Long> nameToWebsitesMap = hostings.stream()
                .collect(Collectors.toMap(Hosting::getName, Hosting::getWebsites,
                        (oldValue, newValue) -> oldValue));
        System.out.println(nameToWebsitesMap);
    }

    static class Hosting {
        private int id;
        private String name;
        private long websites;

        public Hosting(int id, String name, long websites) {
            this.id = id;
            this.name = name;
            this.websites = websites;
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

        public long getWebsites() {
            return websites;
        }

        public void setWebsites(long websites) {
            this.websites = websites;
        }
    }
}
