package livecodinginterview;

import java.util.*;

public class CodingInterviewTask14_TripItinerary {

    /*
     * Problem: TripItinerary
     *
     * Each ticket: [from, to]
     * Find the starting city (never appears as destination)
     * Reconstruct the full itinerary in order.
     *
     * STAGE 1: Return the itinerary as a list of cities from start to end.
     */
    public static void main(String[] args) {
        var tickets = List.of(
                new String[]{"Amsterdam", "Berlin"},
                new String[]{"London", "Amsterdam"},
                new String[]{"Berlin", "Prague"},
                new String[]{"Paris", "London"}
        );

        System.out.println(solution2(tickets));
        // Expected: [Paris, London, Amsterdam, Berlin, Prague]
    }

    public static List<String> solution2(List<String[]> tickets) {
        var result = new ArrayList<String>();
        var fromSet = new HashSet<String>();
        var toSet = new HashSet<String>();
        var map = new HashMap<String, String>();

        for (var ticket : tickets) {
            var from = ticket[0];
            var to = ticket[1];

            fromSet.add(from);
            toSet.add(to);

            map.put(from, to);
        }

        for (var city : fromSet) {
            if (!toSet.contains(city)) {
                result.add(city);
            }
        }

        var currentCity = result.getFirst();
        while (map.containsKey(currentCity)) {
            var nextCity = map.get(currentCity);
            result.add(nextCity);
            currentCity = nextCity;
        }

        return result;
    }

    public static List<String> solution1(List<String[]> tickets) {
        if (tickets == null || tickets.isEmpty()) {
            return List.of();
        }

        var result = new ArrayList<String>();
        var toSet = new HashSet<String>();
        var cityFromToMap = new HashMap<String, String>();

        String startCity = "";

        for (var ticket : tickets) {
            if (ticket.length != 2) {
                continue;
            }

            toSet.add(ticket[1]);
            cityFromToMap.put(ticket[0], ticket[1]);
        }

        for (var ticket : tickets) {
            if (ticket.length != 2) {
                continue;
            }

            if (toSet.add(ticket[0])) {
                startCity = ticket[0];
                result.add(startCity);
            }
        };

        var currentCity = startCity;
        while (cityFromToMap.containsKey(currentCity)) {
            var nextCity = cityFromToMap.get(currentCity);

            result.add(nextCity);
            currentCity = nextCity;
        }

        return result;
    }
}