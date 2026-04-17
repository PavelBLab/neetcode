package onlineassessment;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class CodingInterviewTask7_HotelCheckInQueue {

    /*
     * Problem: Hotel Check-in Queue
     *
     * Guests arrive at a hotel reception in order.
     * Process them one by one in the order they arrived.
     *
     * Given a list of guest names, use a Queue to:
     * 1. Add all guests to the queue
     * 2. Process them one by one (poll from queue)
     * 3. Return a list of strings like "Checked in: Anna"
     *
     * Example:
     *   guests = ["Anna", "Bob", "Clara"]
     *   result = ["Checked in: Anna", "Checked in: Bob", "Checked in: Clara"]
     */
    public static void main(String[] args) {
        System.out.println(solution4(List.of("Anna", "Bob", "Clara")));
        // Expected: [Checked in: Anna, Checked in: Bob, Checked in: Clara]

        System.out.println(solution4(List.of()));
        // Expected: []
    }

    public static List<String> solution4(List<String> guests) {
        var q = new ArrayDeque<String>();
        var result = new ArrayList<String>();

        for (var guest : guests) {
            q.add(guest);
        }

        while (!q.isEmpty()) {
            result.add(String.format("Checked in: %s", q.poll()));
        }

        return result;
    }


    public static List<String> solution3(List<String> guests) {
        var result = new ArrayList<String>();
        var guestArrivedQueue = new ArrayDeque<String>();

        for (var guest : guests) {
            guestArrivedQueue.add(guest);
        }

        while (!guestArrivedQueue.isEmpty()) {
            result.add(String.format("Checked in: %s", guestArrivedQueue.poll()));
        }

        return result;
    }

    public static List<String> solution2(List<String> guests) {
        var result = new ArrayList<String>();
        var checkInQueue = new ArrayDeque<String>();

        for (var guest : guests) {
            checkInQueue.add(guest);
        }

        while (!checkInQueue.isEmpty()) {
            result.add(String.format("Checked in: %s", checkInQueue.poll()));
        }

        return result;
    }

    public static List<String> solution1(List<String> guests) {
        if (guests.isEmpty()) {
            return List.of();
        }

        var queue = new ArrayDeque<String>();
        var result = new ArrayList<String>();

        for (var guest : guests) {
            queue.add(guest);           // add raw name
        }

        while (!queue.isEmpty()) {
            var guest = queue.poll();   // process when polling
            result.add("Checked in: " + guest);
        }

        return result;
    }
}
