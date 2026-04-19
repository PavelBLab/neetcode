package livecodinginterview;

import java.util.*;

public class CodingInterviewTask16_TopKBusiestHotels {

    /*
     * Booking.com tracks the number of bookings per hotel.
     * Given a list of booking logs [hotelId, guestName],
     * find the top K hotels by number of bookings.
     *
     * If two hotels have the same booking count,
     * sort by hotelId ascending.
     *
     * HashMap + sort:
     * Count: O(n)
     * Sort ALL m hotels: O(m log m)
     * Then take top K
     *
     * PriorityQueue of size K:
     * Count: O(n)
     * Maintain only K elements: O(m log k)
     * Already have the answer
     *
     * Sort: O(m log m) = O(1,000,000 × 20) = 20 million operations
     * PQ:   O(m log k) = O(1,000,000 × 3)  = 3 million operations
     */
    public static void main(String[] args) {
        var bookings = List.of(
                new String[]{"H1", "Anna"},
                new String[]{"H2", "Bob"},
                new String[]{"H1", "Clara"},
                new String[]{"H3", "Dan"},
                new String[]{"H2", "Eve"},
                new String[]{"H1", "Frank"},
                new String[]{"H3", "Grace"},
                new String[]{"H2", "Henry"},
                new String[]{"H4", "Ivan"},
                new String[]{"H1", "Julia"}
        );

        System.out.println(solution1(bookings, 2));
        // H1: 4 bookings, H2: 3 bookings, H3: 2 bookings, H4: 1 booking
        // Expected: [H1, H2]
    }

    public static List<String> solution1(List<String[]> bookings, int k) {
        var result = new ArrayList<String>();
        var bookingsMap = new HashMap<String, Integer>();
        var bookingPq = new PriorityQueue<Map.Entry<String, Integer>>(
                Comparator.comparingInt(Map.Entry::getValue));

        for (var booking : bookings) {
            var hotelId = booking[0];
            // bookingsMap.put(hotelId, bookingsMap.getOrDefault(hotelId, 0) + 1);

            bookingsMap.compute(hotelId, (key, counter) -> {
                if (counter == null) return 1;
                return counter + 1;
            });

            // bookingsMap.merge(hotelId, 1, Integer::sum);
        }

        for (var entry : bookingsMap.entrySet()) {
            bookingPq.add(entry);

            if (bookingPq.size() > k) {
                bookingPq.poll();
            }
        }

        while (!bookingPq.isEmpty()) {
            result.add(bookingPq.poll().getKey());
        }

        Collections.reverse(result);  // one O(k) pass

        return result;
    }
}