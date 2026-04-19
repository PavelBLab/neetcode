package livecodinginterview;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CodingInterviewTask11_2_BookingPaymentReport {

    /*
     * Each log: [bookingId, hotelId, guestName, amount, status]
     * Status: "confirmed", "cancelled", "refunded"
     *
     * Calculate total revenue per hotel (only "confirmed" payments).
     * Return results sorted by revenue descending.
     * If two hotels have the same revenue, sort by hotelId ascending.
     */
    public static void main(String[] args) {
        var logs = List.of(
                new String[]{"B001", "H1", "Anna", "200", "confirmed"},
                new String[]{"B002", "H1", "Bob", "150", "confirmed"},
                new String[]{"B003", "H2", "Clara", "300", "confirmed"},
                new String[]{"B004", "H1", "Dan", "100", "cancelled"},
                new String[]{"B005", "H2", "Eve", "250", "refunded"},
                new String[]{"B006", "H3", "Frank", "400", "confirmed"},
                new String[]{"B007", "H2", "Grace", "175", "confirmed"},
                new String[]{"B008", "H3", "Henry", "350", "confirmed"}
        );

        System.out.println(solution1(logs));
        // Expected: {H3=750, H2=475, H1=350}
    }





    public static Map<String, Integer> solution1(List<String[]> logs) {
        if (logs == null || logs.isEmpty()) {
            return Map.of();
        }

        var revenueMap = new HashMap<String, Integer>();

        for (var log : logs) {
            if (log.length != 5) {
                continue;
            }

            var hotelId = log[1];
            var revenue = 0;
            var status = log[log.length - 1];

            try {
                revenue = Integer.parseInt(log[3]);
            } catch (RuntimeException e) {
                continue;
            }

            if (status.equals("confirmed")) {
                revenueMap.put(hotelId, revenueMap.getOrDefault(hotelId, 0) + revenue);
            }
        }
        return revenueMap.entrySet().stream()
                .sorted(
                        Comparator.comparingInt((Map.Entry<String, Integer> e) -> e.getValue())
                                .reversed()
                                .thenComparing(Map.Entry::getKey)
                )
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (a, b) -> a, LinkedHashMap::new
                ));
    }
}
