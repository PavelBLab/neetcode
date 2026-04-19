package livecodinginterview;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class CodingInterviewTask12_2_DuplicateBookingDetector {

    /*
     * Each booking: [bookingId, hotelId, guestName, checkInDate, amount]
     *
     * Two bookings are duplicates if they share: hotelId + guestName + checkInDate
     * Return a list of bookingIds that are duplicates (keep the first, flag the rest).
     *
     * Follow-up — 2-day window version:
     * Two bookings are now duplicates if they have the same hotelId, guestName, and check-in date
     * is within 2 days of any previously seen booking for that hotel+guest. How would you modify?
     */
    public static void main(String[] args) {
        var bookings = List.of(
                new String[]{"B001", "H1", "Anna",  "2026-04-10", "200"},
                new String[]{"B002", "H2", "Bob",   "2026-04-11", "300"},
                new String[]{"B003", "H1", "Anna",  "2026-04-10", "200"},
                new String[]{"B004", "H1", "Anna",  "2026-04-12", "250"},
                new String[]{"B005", "H2", "Bob",   "2026-04-11", "300"},
                new String[]{"B006", "H2", "Bob",   "2026-04-11", "350"},
                new String[]{"B007", "H3", "Clara", "2026-04-10", "400"}
        );

        System.out.println(solution2(bookings));
        // Expected: [B003, B005, B006]
    }

    public static List<String> solution2(List<String[]> bookings) {
        if (bookings == null || bookings.isEmpty()) {
            return List.of();
        }

        var result = new ArrayList<String>();
        var bookingsMap = new HashMap<List<String>, List<LocalDate>>();

        for (var currentBooking : bookings) {
            if (currentBooking.length != 5) {
                continue;
            }

            var bookingId = currentBooking[0];
            var hotelId = currentBooking[1];
            var guestName = currentBooking[2];
            var checkInDate = LocalDate.parse(currentBooking[3]);

            var key = List.of(hotelId, guestName);

            bookingsMap.computeIfAbsent(key, l -> new ArrayList<>()).add(checkInDate);

            var bookingCheckIndates = bookingsMap.get(key);
            var isWithinRange = false;

            for (var date : bookingCheckIndates) {
                long daysBetween = Math.abs(ChronoUnit.DAYS.between(checkInDate, date));

                if (daysBetween <= 2) isWithinRange = true;
            }

            if (bookingsMap.containsKey(key) && isWithinRange) {
                result.add(bookingId);
            }
        }

        return result;
    }

    public static List<String> solution1(List<String[]> bookings) {
        if (bookings == null || bookings.isEmpty()) {
            return List.of();
        }

        var result = new ArrayList<String>();
        var bookingSet = new HashSet<List<String>>();

        for (var booking : bookings) {
            if (booking == null || booking.length != 5) {
                continue;
            }

            var bookingId = booking[0];
            var hotelId = booking[1];
            var guestName = booking[2];
            var checkInDate = booking[3];

            var key = List.of(hotelId, guestName, checkInDate);

            if (!bookingSet.add(key)) {
                result.add(bookingId);
            }
        }

        return result;
    }
}