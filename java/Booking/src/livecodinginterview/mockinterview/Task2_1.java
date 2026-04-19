package livecodinginterview.mockinterview;

import java.util.*;

public class Task2_1 {

    /*
     * Booking.com tracks flight connections between cities. Given a list of direct flight routes as [from, to, price],
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

    record CityNode(String city, int cost) {
    }

    public static int cheapestFlight(final List<String[]> flights, final String fromCity, final String toCity) {
        if (flights == null || flights.isEmpty()) {
            return -1;
        }

        if (fromCity.equals(toCity)) {
            return 0;
        }

        var graph = createGraph(flights);

        var cityNodePq = new PriorityQueue<>(Comparator.comparingInt(CityNode::cost));
        cityNodePq.add(new CityNode(fromCity, 0));

        var visitedCities = new HashSet<String>();

        while (!cityNodePq.isEmpty()) {
            var cityNode = cityNodePq.poll();
            var currentCity = cityNode.city;
            var currentCost = cityNode.cost;

            if (!visitedCities.add(currentCity)) continue;
            if (toCity.equals(currentCity)) return currentCost;

            var neighbors = graph.getOrDefault(currentCity, List.of());
            for (var node : neighbors) {
                    cityNodePq.add(new CityNode(node.city, currentCost + node.cost));
            }
        }

        return -1;
    }

    private static Map<String, List<CityNode>> createGraph(final List<String[]> flights) {
        var graph = new HashMap<String, List<CityNode>>();

        for (var flight : flights) {
            if (flight.length != 3) {
                continue;
            }

            if (flight[0].equals(flight[1])) {
                continue;
            }

            int flightCosts;

            try {
                flightCosts = Integer.parseInt(flight[2]);
            } catch (NumberFormatException e) {
                // add logging. Continue or throw an exceptiom
                continue;
            }

            var fromCity = flight[0];
            var toCity = flight[1];
            var cost = flightCosts;
            var cityNode = new CityNode(toCity, cost);

            graph.computeIfAbsent(fromCity, l -> new ArrayList<>()).add(cityNode);
        }

        return graph;
    }

}
