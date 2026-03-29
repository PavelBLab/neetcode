import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

public class CodingInterviewTask8 {

    /*
     * Problem: Hotel Check-in by Group
     *
     * Guests arrive at a hotel in groups. Each group checks in together.
     * Given a list of groups, process them group by group using a queue.
     * Return a list showing which group number each guest was in.
     *
     * Example:
     *   groups = [["Anna", "Bob"], ["Clara"], ["Dan", "Eve", "Frank"]]
     *
     *   Group 1: Anna, Bob
     *   Group 2: Clara
     *   Group 3: Dan, Eve, Frank
     *
     *   result = ["Anna - group 1", "Bob - group 1",
     *             "Clara - group 2",
     *             "Dan - group 3", "Eve - group 3", "Frank - group 3"]
     *
     * CONSTRAINT: You must add ALL guests to the queue first,
     *             then process them using layerSize to track groups.
     */
    public static void main(String[] args) {
        var groups = List.of(
                List.of("Anna", "Bob"),
                List.of("Clara"),
                List.of("Dan", "Eve", "Frank")
        );

        System.out.println(solution2(groups));
    }

    public static List<String> solution2(List<List<String>> groups) {
        var result = new ArrayList<String>();
        var guestGroupQueue = new ArrayDeque<String>();
        var layerSize = new ArrayList<Integer>();

        for (var group : groups) {
            layerSize.add(group.size());

            for (var guest : group) {
                guestGroupQueue.add(guest);
            }
        }

        for (var i = 0; i < layerSize.size(); i++) {
            for (var j = 0; j < layerSize.get(i); j++) {
                result.add(
                        String.format("%s - group %s", guestGroupQueue.poll(), i + 1));
            }
        }

        return result;
    }



    public static List<String> solution1(List<List<String>> groups) {
        var queue = new ArrayDeque<String>();
        var sizes = new ArrayList<Integer>();

        var result = new ArrayList<String>();
        var groupNumber = 1;

        for (var group : groups) {
            sizes.add(group.size());
            for (var guest : group) {
                queue.add(guest);
            }
        }

        for (var i = 0; i < sizes.size(); i++) {
            var groupSize = sizes.get(i);

            for (var j = 0; j < groupSize; j++) {
                var groupName = String.format("%s - group %s", queue.poll(), groupNumber);
                result.add(groupName);
            }
            groupNumber++;
        }

        return result;
    }
}
