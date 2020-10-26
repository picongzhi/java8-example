package com.pcz.example.datetime;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author picongzhi
 */
public class DisplayAllZoneIdDemo {
    public static void main(String[] args) {
        Map<String, String> sortedMap = new LinkedHashMap<>();
        Map<String, String> allZoneIdsAndItsOffset = getAllZoneIdsAndItsOffset();
        allZoneIdsAndItsOffset.entrySet().stream()
                .sorted(Map.Entry.comparingByKey())
                .forEachOrdered(e -> sortedMap.put(e.getKey(), e.getValue()));
        sortedMap.forEach((k, v) -> {
            System.out.println(String.format("%25s (UTC%s)", k, v));
        });

        LocalDateTime localDateTime = LocalDateTime.now();
        long count = ZoneId.getAvailableZoneIds().stream()
                .map(ZoneId::of)
                .map(zoneId -> new AbstractMap.SimpleEntry<>(zoneId.toString(),
                        localDateTime.atZone(zoneId).getOffset().getId().replaceAll("Z", "+00:00")))
                .sorted(Map.Entry.comparingByKey())
                .peek(e -> System.out.println(String.format("%25s (UTC%s)", e.getKey(), e.getValue())))
                .count();
        System.out.println(count);
    }

    private static Map<String, String> getAllZoneIdsAndItsOffset() {
        Map<String, String> result = new HashMap<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        for (String zoneId : ZoneId.getAvailableZoneIds()) {
            ZoneId id = ZoneId.of(zoneId);
            ZonedDateTime zonedDateTime = localDateTime.atZone(id);
            ZoneOffset zoneOffset = zonedDateTime.getOffset();
            String offset = zoneOffset.getId().replaceAll("Z", "+00:00");
            result.put(id.toString(), offset);
        }

        return result;
    }
}
