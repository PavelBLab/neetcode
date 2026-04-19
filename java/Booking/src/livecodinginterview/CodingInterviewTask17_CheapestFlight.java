package livecodinginterview;

import java.util.*;

public class CodingInterviewTask17_CheapestFlight {

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

        System.out.println(solution1(flights, "Amsterdam", "Vienna"));
        // Expected: 300 (Amsterdam→Berlin→Vienna: 100+200)
    }

    private record City(String city, int cost) {}

    public static int solution1(List<String[]> flights, String from, String to) {
        var cityMap = new HashMap<String, List<City>>();
        var cityPq = new PriorityQueue<City>(Comparator.comparingInt(c -> c.cost));
        cityPq.add(new City(from, 0));

        var visitedCities = new HashSet<String>();
        visitedCities.add(from);

        for (var flight : flights) {
            var fromCity = flight[0];
            var toCity = flight[1];
            var cost = Integer.parseInt(flight[2]);

            cityMap.computeIfAbsent(fromCity, l -> new ArrayList<>()).add(new City(toCity, cost));
        }

        while (!cityPq.isEmpty()) {
            var cityNode = cityPq.poll();
            var currentCity = cityNode.city;
            var currentCost = cityNode.cost;

            if (currentCity.equals(to)) {
                return currentCost;
            }

            var neighbours = cityMap.getOrDefault(currentCity, List.of());

            for (var neighbour : neighbours) {
                if (visitedCities.add(neighbour.city)) {
                    cityPq.add(new City(neighbour.city, neighbour.cost + currentCost));
                }
            }
        }

        return -1;
    }
}