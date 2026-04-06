package livecodinginterview;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class CodingInterviewTask12_DuplicateBookingDetector {

    /*
     * Problem: Duplicate Booking Detector
     *
     * Each log: [bookingId, hotelId, guestName, checkInDate, amount]
     * Example: ["B001", "H1", "Anna", "2026-04-10", "200"]
     *
     * STAGE 1: Find all duplicate bookings.
     * Two bookings are duplicates if they share: hotelId + guestName + checkInDate
     * Return a list of bookingIds that are duplicates (keep the first, flag the rest).
     */
    public static void main(String[] args) {
        var bookings = List.of(
                new String[]{"B001", "H1", "Anna", "2026-04-10", "200"},
                new String[]{"B002", "H2", "Bob", "2026-04-11", "300"},
                new String[]{"B003", "H1", "Anna", "2026-04-10", "200"},  // dup of B001
                new String[]{"B004", "H1", "Anna", "2026-04-12", "250"},  // different date — not dup
                new String[]{"B005", "H2", "Bob", "2026-04-11", "300"},  // dup of B002
                new String[]{"B006", "H2", "Bob", "2026-04-11", "350"},  // dup of B002 (diff amount)
                new String[]{"B007", "H3", "Clara", "2026-04-10", "400"}
        );

        System.out.println(findDuplicates(bookings));
        // Expected: [B003, B005, B006]
    }


    public static List<String> findDuplicates(List<String[]> bookings) {
        if (bookings == null || bookings.isEmpty()) {
            return List.of();
        }

        var result = new ArrayList<String>();
        var bookingMap = new HashMap<List<String>, TreeSet<LocalDate>>();

        for (var booking : bookings) {
            if (booking.length != 5) {
                continue;
            }

            var bookingId = booking[0];
            var hotelId = booking[1];
            var guestName = booking[2];
            LocalDate checkInDate;

            try {
                checkInDate = LocalDate.parse(booking[3]);
            } catch (Exception e) {
                continue;
            }


            // List.of() was introduced in Java 9 and explicitly disallows nulls.
            // From the spec: all List.of() factory methods throw NullPointerException if any element is null.
            // List.of("H1", null, "2026-04-10");  // NPE at creation time
            // Arrays.asList("H1", null, "2026-04-10");  // this allows nulls
            if (hotelId == null || guestName == null || checkInDate == null) {
                continue;
            }

            var key = List.of(hotelId, guestName);

            if (bookingMap.containsKey(key)) {
                var existingBookingCheckInDates = bookingMap.get(key);

                if (isDuplicates(existingBookingCheckInDates, checkInDate, 2)) {
                    result.add(bookingId);
                }

                existingBookingCheckInDates.add(checkInDate);
            } else {
                bookingMap.computeIfAbsent(key, c -> new TreeSet<>()).add(checkInDate);
            }
        }

        return result;
    }

    private static boolean isDuplicates(final TreeSet<LocalDate> existingBookingDates, final LocalDate checkInDate, int days) {
        if (existingBookingDates == null || checkInDate == null) {
            return false;
        }

        var floor = existingBookingDates.floor(checkInDate);     // closest date ≤ checkInDate
        var ceiling = existingBookingDates.ceiling(checkInDate); // closest date ≥ checkInDate

        boolean isDup = false;
        if (floor != null && ChronoUnit.DAYS.between(floor, checkInDate) <= days) {
            isDup = true;
        }

        if (ceiling != null && ChronoUnit.DAYS.between(checkInDate, ceiling) <= days) {
            isDup = true;
        }

        return isDup;
    }

}