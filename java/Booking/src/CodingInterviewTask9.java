import java.util.*;

public class CodingInterviewTask9 {

    /*
     * Problem: Reachable Floors
     *
     * Same hotel with connected floors. Given a starting floor,
     * simply return ALL floors you can reach (in any order).
     *
     * Just use a queue to explore and a set to track visited.
     * No layers, no counting steps.
     *
     * Example:
     *   start: 1 → can reach: [1, 2, 3, 4, 5]
     *   start: 4 → can reach: [4, 2, 3, 5, 1]
     */
    public static void main(String[] args) {
        Map<Integer, List<Integer>> connections = Map.of(
                1, List.of(2, 3),
                2, List.of(1, 4),
                3, List.of(1, 4),
                4, List.of(2, 3, 5),
                5, List.of(4),
                9, List.of()     // floor 9 is isolated
        );

        System.out.println(solution1(connections, 1));
        // Expected: [1, 2, 3, 4, 5] (order may vary)

        System.out.println(solution1(connections, 9));
        // Expected: [9] (isolated, can only reach itself)
    }

    public static List<Integer> solution1(Map<Integer, List<Integer>> connections, int start) {
        var result = new ArrayList<Integer>();

        var queue = new ArrayDeque<Integer>();
        queue.add(start);

        var visited = new HashSet<Integer>();
        visited.add(start);

        while(!queue.isEmpty()) {
            var floor = queue.poll();
            var neighbours = connections.getOrDefault(floor, List.of());

            for (var neighbour : neighbours) {
                if (!visited.contains(neighbour)) {
                    visited.add(neighbour);
                    queue.add(neighbour);
                }
            }

            result.add(floor);
        }

        return result;
    }
}
