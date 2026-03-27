import java.util.*;

public class CodingInterviewTask10 {

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
        System.out.println(solution1(List.of("Anna", "VIP:Bob", "Clara", "VIP:Dan", "Eve")));
        // Expected: [Bob, Dan, Anna, Clara, Eve]

        System.out.println(solution1(List.of("VIP:Zara")));
        // Expected: [Zara]

        System.out.println(solution1(List.of("Anna", "Bob")));
        // Expected: [Anna, Bob]

        System.out.println(solution1(List.of()));
        // Expected: []
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
