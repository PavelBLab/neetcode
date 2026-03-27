import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class CodingInterviewTask7 {

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
        System.out.println(solution1(List.of("Anna", "Bob", "Clara")));
        // Expected: [Checked in: Anna, Checked in: Bob, Checked in: Clara]

        System.out.println(solution1(List.of()));
        // Expected: []
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
