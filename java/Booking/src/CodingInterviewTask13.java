import java.util.*;

public class CodingInterviewTask13 {

    /*
     * Problem: Minimum Flights
     *
     * Booking.com shows travelers the minimum number of flights
     * to get from one city to another.
     *
     * You are given a list of direct flight connections (bidirectional).
     * Find the minimum number of flights to get from "start" to "end".
     * If no route exists, return -1.
     *
     * Example:
     *   Amsterdam → London, London → Paris,
     *   Amsterdam → Berlin, Berlin → Rome, Paris → Rome
     *
     *   Amsterdam to Rome:
     *     Amsterdam → Berlin → Rome = 2 flights ← shortest
     *     Amsterdam → London → Paris → Rome = 3 flights
     *
     *   Result: 2
     */
    public static void main(String[] args) {
        var connections = List.of(
                new String[]{"Amsterdam", "London"}, // Amsterdam : [London, Berlin]
                new String[]{"London", "Paris"}, // London : [Paris]
                new String[]{"Amsterdam", "Berlin"},
                new String[]{"Berlin", "Rome"}, // Berlin: [Rome]
                new String[]{"Paris", "Rome"}  // Paris: [Rome]
        );

        // Test 1: shortest route exists
        System.out.println(solution1(connections, "Amsterdam", "Rome")); // Expected: 2

        // Test 2: direct flight
        System.out.println(solution1(connections, "Amsterdam", "London")); // Expected: 1

        // Test 3: same city
        System.out.println(solution1(connections, "Paris", "Paris")); // Expected: 0

        // Test 4: no route
        System.out.println(solution1(connections, "Amsterdam", "Tokyo")); // Expected: -1
    }

    public static int solution1(List<String[]> connections, String start, String end) {
        if (start.equals(end)) {
            return 0;
        }

        var numberOfFlights = 0;
        var connectionMap = new HashMap<String, List<String>>();
        var flightQueue = new ArrayDeque<String>();
        flightQueue.add(start);

        var visitedCities = new HashSet<String>();
        visitedCities.add(start);

        for (var connection : connections) { // O(n) + O(n)
            connectionMap.computeIfAbsent(connection[0], c -> new ArrayList<>()).add(connection[1]);
            connectionMap.computeIfAbsent(connection[1], c -> new ArrayList<>()).add(connection[0]);
        }

        while (!flightQueue.isEmpty()) {
            var layerSize = flightQueue.size();

            for (var i = 0; i < layerSize; i++) {
                var currentCity = flightQueue.poll();

                if (currentCity.equals(end)) {
                    return numberOfFlights;
                }

                var currentRoutes = connectionMap.getOrDefault(currentCity, List.of());

                for (var nextCity : currentRoutes) {
                    if (visitedCities.add(nextCity)) {
                        flightQueue.add(nextCity);
                    }
                }
            }

            numberOfFlights++;
        }

        return -1;
    }
}
