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

        System.out.println(reconstructItinerary(tickets));
        // Expected: [Paris, London, Amsterdam, Berlin, Prague]
    }

    public static List<String> reconstructItinerary(List<String[]> tickets) {
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