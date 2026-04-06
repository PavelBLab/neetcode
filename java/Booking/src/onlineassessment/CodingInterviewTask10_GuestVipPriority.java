package onlineassessment;

import java.util.*;

public class CodingInterviewTask10_GuestVipPriority {

    /*
     * Problem: Guest VIP Priority
     *
     * Booking.com reception processes guests in arrival order.
     * But they want to separate VIP and regular guests.
     *
     * Given a list of guests where VIP names start with "VIP:",
     * use TWO queues to process VIPs first, then regular guests.
     * Return the processing order.
     *
     * Example:
     *   guests = ["Anna", "VIP:Bob", "Clara", "VIP:Dan", "Eve"]
     *
     *   VIP queue:     [Bob, Dan]
     *   Regular queue:  [Anna, Clara, Eve]
     *
     *   Process VIPs first, then regular:
     *   Result: ["Bob", "Dan", "Anna", "Clara", "Eve"]
     */
    public static void main(String[] args) {
        System.out.println(solution3(List.of("Anna", "VIP:Bob", "Clara", "VIP:Dan", "Eve")));
        // Expected: [Bob, Dan, Anna, Clara, Eve]

        System.out.println(solution3(List.of("VIP:Zara")));
        // Expected: [Zara]

        System.out.println(solution3(List.of("Anna", "Bob")));
        // Expected: [Anna, Bob]

        System.out.println(solution3(List.of()));
        // Expected: []
    }

    public static List<String> solution3(List<String> guests) {
        var result = new ArrayList<String>();
        var vipGuestsQueue = new ArrayDeque<String>();
        var regularGuestsQueue = new ArrayDeque<String>();

        for (var guest : guests) {
            if (guest.contains("VIP")) {
                vipGuestsQueue.add(guest);
            } else {
                regularGuestsQueue.add(guest);
            }
        }

        while (!vipGuestsQueue.isEmpty()) {
            var formattedVipGuest = vipGuestsQueue.poll().replace("VIP:", "");
            result.add(formattedVipGuest);
        }

        while (!regularGuestsQueue.isEmpty()) {
            result.add(regularGuestsQueue.poll());
        }

        return result;
    }

    public static List<String> solution2(List<String> guests) {
        var result = new ArrayList<String>();
        var vipGuestQueue = new ArrayDeque<String>();
        var regularGuestsQueue = new ArrayDeque<String>();
        var vipString = "VIP:";


        for (var guest : guests) {
            if (guest.contains(vipString)) {
                var formatedVipGuest = guest.replace(vipString, "");
                vipGuestQueue.add(formatedVipGuest);
            } else {
                regularGuestsQueue.add(guest);
            }
        }

        while (!vipGuestQueue.isEmpty()) {
            result.add(vipGuestQueue.poll());
        }

        while(!regularGuestsQueue.isEmpty()) {
            result.add(regularGuestsQueue.poll());
        }

        return result;
    }

    public static List<String> solution1(List<String> guests) {
        var result = new ArrayList<String>();
        var normalQueue = new ArrayDeque<String>();
        var vipQueue = new ArrayDeque<String>();

        for (var guest : guests) {
            if (guest.contains("VIP")) {
                var formattedVipGuest = guest.replace("VIP:", "");
                vipQueue.add(formattedVipGuest);
            } else {
                normalQueue.add(guest);
            }
        }

        while (!vipQueue.isEmpty()) {
            result.add(vipQueue.poll());
        }

        while (!normalQueue.isEmpty()) {
            result.add(normalQueue.poll());
        }

        return result;
    }
}
