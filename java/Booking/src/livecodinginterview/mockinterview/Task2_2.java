package livecodinginterview.mockinterview;

import java.util.*;

public class Task2_2 {

    /*
     * Booking.com tracks flight connections between cities. Given a list of direct
     * flight routes as [from, to, price],
     * and a start city and destination city, find the cheapest price to fly from start to destination.
     * If no route exists, return -1
     */
    public static void main(String[] args) {
        var flights = List.of(
                new String[]{"Amsterdam", "Berlin", "100"},
                new String[]{"Amsterdam", "London", "200"},
                new String[]{"Berlin", "Prague", "150"},
                new String[]{"London", "Prague", "100"},
                new String[]{"Berlin", "Vienna", "200"},
                new String[]{"Prague", "Vienna", "75"}
        );

        System.out.println(cheapestFlight(flights, "Amsterdam", "Vienna"));
        // Expected: 325 (Amsterdam→Berlin→Prague→Vienna: 100+150+75)
    }

    public record CityNode(String city, int cost) {};

    public static int cheapestFlight(final List<String[]> flights, final String fromCity, final String toCity) {
        if (flights == null || flights.isEmpty()) {
            return -1;
        }

        if (fromCity.equals(toCity)) {
            return 0;
        }

        var graph = createGraph(flights);

        if (!graph.containsKey(fromCity)) {
            return -1;
        }
        var cityPriorityQueue = new PriorityQueue<>(Comparator.comparingInt(CityNode::cost));
        cityPriorityQueue.add(graph.get(fromCity).getFirst());

        var visitedCities = new HashSet<String>();
        visitedCities.add(fromCity);

        while (!cityPriorityQueue.isEmpty()) {
            var cityNode = cityPriorityQueue.poll();
            var currentCity = cityNode.city;
            var currentCost = cityNode.cost;

            if (!visitedCities.add(currentCity)) continue;
            if (toCity.equals(currentCity)) return currentCost;

            var neighbours = graph.getOrDefault(currentCity, List.of());

            for (var neighbour : neighbours) {
                cityPriorityQueue.add(new CityNode(neighbour.city, currentCost + neighbour.cost));
            }
        }



        return -1;
    }



    private static Map<String,List<CityNode>> createGraph(final List<String[]> flights) {
        var graph = new HashMap<String, List<CityNode>>();

        for (var flight : flights) {
            if (flight.length != 3) {
                continue;
            }

            var fromCity = flight[0];
            var toCity = flight[1];

            int cost;

            try {
                cost = Integer.parseInt(flight[2]);
            } catch (NumberFormatException e) {
                // logging
                continue;
            }

            graph.computeIfAbsent(fromCity, l -> new ArrayList<>()).add(new CityNode(toCity, cost));
        }

        return graph;
    }


}
