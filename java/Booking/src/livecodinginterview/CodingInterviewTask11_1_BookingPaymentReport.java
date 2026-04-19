package livecodinginterview;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class CodingInterviewTask11_1_BookingPaymentReport {

    /*
     * Problem: Booking Payment Report
     *
     * Booking.com's operations team needs a report from payment logs.
     * Each log entry contains: bookingId, hotelId, guestName, amount, status
     * Status can be: "confirmed", "cancelled", "refunded"
     *
     * TASK 1: Given a list of payment logs, calculate the total revenue
     * per hotel (only count "confirmed" payments). Return a map of
     * hotelId → total revenue, sorted by revenue descending.
     *
     * The interviewer will add follow-ups after you solve this.
     *
     * Follow-up 1:
     * The operations team now wants two things per hotel: total confirmed revenue AND the count of
     * cancelled bookings. How would you modify your solution?
     *
     * Follow-up 2:
     * This report will now run in a multi-threaded environment — multiple threads process different
     * batches of logs concurrently and update the same map. How would you make your solution thread-safe?
     *
     * Find the top guest per hotel by spending" (HashMap inside Hotel)
     * How would this scale to billions of logs?" (MapReduce discussion)
     * What if two threads transfer revenue between hotels?" (deadlock prevention)
     *
     * */
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

        System.out.println(solution3(logs));
        // Expected: {H3=750, H2=475, H1=350}
        // H3: 400+350=750, H2: 300+175=475, H1: 200+150=350
    }

    public static Map<String, HotelSolution3> solution3(List<String[]> logs) {
        if (logs == null || logs.isEmpty()) {
            return Map.of();
        }

        var hotelRevenueMap = new ConcurrentHashMap<String, HotelSolution3>();

        for (var log : logs) {
            if (log.length != 5) {
                continue;
            }

            var hotelId = log[1];
            int amount;

            try {
                amount = Integer.parseInt(log[3]);

            } catch (NumberFormatException e) {
                // can be logs and exception;
                continue;
            }

            var status = log[4];

            if (status.equals("confirmed")) {
                final var finalAmount = amount;
                hotelRevenueMap.compute(hotelId, (k, hotel) -> {
                    if (hotel == null) {
                        hotel = new HotelSolution3(hotelId);
                    }
                    hotel.increaseAmount(finalAmount);
                    return hotel;
                });
            }

            if (status.equals("cancelled")) {
                hotelRevenueMap.compute(hotelId, (k, hotel) -> {
                    if (hotel == null) {
                        hotel = new HotelSolution3(hotelId);
                    }

                    hotel.increaseCancelledReservations();
                    return hotel;
                });
            }
        }

        return hotelRevenueMap.entrySet().stream()
                .sorted(
                        Comparator.comparingInt((Map.Entry<String, HotelSolution3> e) -> e.getValue().getAmount())
                                .reversed()
                                .thenComparing(Map.Entry::getKey)
                )
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (a, b) -> a, LinkedHashMap::new
                        )
                );
    }

    public static class HotelSolution3 {
        private final String hotelId;
        private int amount;
        private int cancelledReservations;

        public HotelSolution3(String hotelId) {
            this.hotelId = hotelId;
            this.amount = 0;
            this.cancelledReservations = 0;
        }

        public String getHotelId() {
            return this.hotelId;
        }

        public int getAmount() {
            return this.amount;
        }

        public synchronized void increaseAmount(final int amount) {
            this.amount += amount;
        }

        public int getCancelledReservations() {
            return this.cancelledReservations;
        }

        public synchronized void increaseCancelledReservations() {
            this.cancelledReservations++;
        }

        @Override
        public String toString() {
            return String.format("Hotel: hotelId:%s, amount:%s, cancelled:%s", this.hotelId, this.amount, this.cancelledReservations);
        }
    }




    public static Map<String, Integer> solution2(List<String[]> logs) {
        var revenueMap = new HashMap<String, Integer>();

        for (var log : logs) {
            var bookingId = log[0];
            var hotelId = log[1];
            var guestName = log[2];
            var amount = log[3];
            var status = log[4];

            if (status.equals("confirmed")) {
                revenueMap.put(hotelId, revenueMap.getOrDefault(hotelId, 0) + Integer.parseInt(amount));
            }

        }

        return revenueMap.entrySet().stream()
                .sorted(
                        Comparator.comparingInt((Map.Entry<String, Integer> e) -> e.getValue())
                                .reversed()
                                .thenComparing(Map.Entry::getKey)
                )
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                Map.Entry::getValue,
                                (k, v) -> k,
                                LinkedHashMap::new
                        )
                );
    }


    public static Map<String, Hotel1> solution1(List<String[]> logs) {
        if (logs == null || logs.isEmpty()) {
            return Map.of();
        }

        // One clarification though: synchronized means threads take turns — they block waiting for the lock.
        // At massive scale with heavy contention, this becomes a bottleneck. You could mention:
        // "For high contention I'd consider using ConcurrentHashMap.merge() which uses finer-grained locking per bucket
        // , or partition the logs so each thread works on different hotel IDs to avoid contention entirely."
        // Processing — ConcurrentHashMap.compute() is atomic per key
        var hotelRevenueMap = new ConcurrentHashMap<String, Hotel1>();

        // Thread 1 hits H1 — compute locks the H1 bucket
        // Thread 2 hits H2 — compute locks the H2 bucket — runs in parallel
        // Thread 3 hits H1 — waits for Thread 1 to finish H1

        for (var log : logs) {
            if (log.length != 5) {
                continue;
            }

            var hotelId = log[1];
            var guestName = log[2];
            var revenue = log[3];
            var status = log[4];

            if ("confirmed".equals(status) || "cancelled".equals(status)) {
                BigDecimal amount;

                try {
                    //amount = Integer.parseInt(revenue);
                    amount = BigDecimal.valueOf(Double.parseDouble(revenue));
                } catch (NumberFormatException e) {
                    continue; // skip malformed entry
                }

                hotelRevenueMap.compute(hotelId, (key, existing) -> {
                    if (existing == null) {
                        existing = new Hotel1(key, guestName, BigDecimal.ZERO, 0);
                    }
                    if (status.equals("confirmed")) {
                        existing.setRevenue(amount);
                    } else {
                        existing.setCancelStatus(1);
                    }
                    return existing;
                });
            }

        }

        var sortedEntries = new ArrayList<>(hotelRevenueMap.entrySet());
        sortedEntries.sort(
                Comparator.comparing((Map.Entry<String, Hotel1> e) -> e.getValue().getRevenue()).reversed()
                        .thenComparingInt(e -> e.getValue().getCancelStatus())
                        .thenComparing(e -> e.getValue().getHotelId()) //.thenComparing(e -> e.getValue().getSomething(), Comparator.reverseOrder())
        );

        var sortedMap = new LinkedHashMap<String, Hotel1>();

        for (var e : sortedEntries) {
            sortedMap.put(e.getKey(), e.getValue());
        }

        // Alternative solution
        //        return hotelRevenueMap.entrySet().stream()
        //                .sorted(
        //                        Comparator.comparingInt((Map.Entry<String, Integer> e) -> e.getValue()).reversed()
        //                )
        //                .collect(Collectors.toMap(
        //                        Map.Entry::getKey,
        //                        Map.Entry::getValue,
        //                        (a, b) -> a,
        //                        LinkedHashMap::new
        //                ));

        return sortedMap;
    }

    private static class Hotel1 {
        private final String hotelId;
        private final String guestName;
        private BigDecimal revenue;
        private int cancelStatus;


        public Hotel1(final String hotelId,
                      final String guestName,
                      final BigDecimal revenue,
                      final Integer cancelStatus) {
            this.hotelId = hotelId;
            this.guestName = guestName;
            this.revenue = revenue;
            this.cancelStatus = cancelStatus;
        }

        public String getHotelId() {
            return hotelId;
        }

        public String getGuestName() {
            return guestName;
        }

        public BigDecimal getRevenue() {
            return revenue;
        }

        public void setRevenue(final BigDecimal revenue) {
            this.revenue = revenue.add(this.revenue);
        }

        public Integer getCancelStatus() {
            return cancelStatus;
        }

        public void setCancelStatus(final int cancelStatus) {
            this.cancelStatus += cancelStatus;
        }

        @Override
        public String toString() {
            return "Hotel{" +
                    "hotelId='" + hotelId + '\'' +
                    ", guestName='" + guestName + '\'' +
                    ", revenue=" + revenue +
                    ", cancelStatus=" + cancelStatus +
                    '}';
        }
    }

    //    public static Map<String, Integer> processInBatches(List<String[]> logs, int batchSize) {
//        var finalResult = new HashMap<String, Integer>();
//
//        // Split into chunks and process each one fully
//        for (int i = 0; i < logs.size(); i += batchSize) {
//            int end = Math.min(i + batchSize, logs.size());
//            var batch = logs.subList(i, end);
//
//            // Each batch produces its own complete result
//            var batchResult = totalRevenuePerHotel(batch);
//
//            // YOU have to merge — this is the key difference from MapReduce
//            for (var entry : batchResult.entrySet()) {
//                finalResult.merge(entry.getKey(), entry.getValue(), Integer::sum);
//            }
//        }
//
//        return finalResult;
//    }

}
