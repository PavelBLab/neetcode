package livecodinginterview;

import java.util.*;

public class CodingInterviewTask1_MinimumFlights {

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
                new String[]{"Amsterdam", "London"},
                new String[]{"London", "Paris"},
                new String[]{"Amsterdam", "Berlin"},
                new String[]{"Berlin", "Rome"},
                new String[]{"Paris", "Rome"}
        );

        // Test 1: shortest route
        System.out.println(solution5(connections, "Amsterdam", "Rome")); // Expected: 2

        // Test 2: direct flight
        System.out.println(solution5(connections, "Amsterdam", "London")); // Expected: 1

        // Test 3: same city
        System.out.println(solution5(connections, "Paris", "Paris")); // Expected: 0

        // Test 4: no route
        System.out.println(solution5(connections, "Amsterdam", "Tokyo")); // Expected: -1
    }

    public static int solution5(List<String[]> connections, String start, String end) {
        if (start.equals(end)) return 0;

        var connectionMap = new HashMap<String, List<String>>();

        var citiesQueue = new ArrayDeque<String>();
        citiesQueue.add(start);

        var visitedCites = new HashSet<String>();
        visitedCites.add(start);

        var connectionCounter = 1;

        // We create graph first
        for (var connection: connections) {
            connectionMap.computeIfAbsent(connection[0], c -> new ArrayList<>()).add(connection[1]);
            connectionMap.computeIfAbsent(connection[1], c -> new ArrayList<>()).add(connection[0]);
        }

        while (!citiesQueue.isEmpty()) {
            var layerSize = citiesQueue.size();

            for (var i = 0; i < layerSize; i++) {
                var currentCity = citiesQueue.poll();
                var currentCityConnections = connectionMap.getOrDefault(currentCity, List.of());

                for (var connection: currentCityConnections) {
                    if (connection.equals(end)) {
                        return connectionCounter;
                    }

                    if (visitedCites.add(connection)) {
                        citiesQueue.add(connection);
                    }
                }
            }
            connectionCounter++;
        }

        return -1;
    }

    public static int solution4(List<String[]> connections, String start, String end) {
        if (start.equals(end)) {
            return 0;
        }

        var graph = new HashMap<String, List<String>>();

        var citiesQueue = new ArrayDeque<String>();
        citiesQueue.add(start);

        var visitedCities = new HashSet<String>();
        visitedCities.add(start);

        var connectionCounter = 1;

        for (var i = 0; i < connections.size(); i++) {
            var startCity = connections.get(i)[0];
            var endCity = connections.get(i)[1];

            graph.computeIfAbsent(startCity, c -> new ArrayList<>()).add(endCity);
            graph.computeIfAbsent(endCity, c -> new ArrayList<>()).add(startCity);
        }

        while (!citiesQueue.isEmpty()) {
            var layerSize = citiesQueue.size();

            for (var i = 0; i < layerSize; i++) {
                var currentCity = citiesQueue.poll();
                var cityConnections = graph.getOrDefault(currentCity, List.of());

                for (var city : cityConnections) {
                    if (city.equals(end)) {
                        return connectionCounter;
                    }

                    if (visitedCities.add(city)) {
                        citiesQueue.add(city);
                    }
                }
            }
            connectionCounter++;
        }

        return -1;
    }

    public static int solution3(List<String[]> connections, String start, String end) {
        if (start.equals(end)) {
            return 0;
        }

        var flightsGraph = new HashMap<String, List<String>>();

        for (var i = 0; i < connections.size(); i++) {
            var startConnection = connections.get(i)[0];
            var endConnection = connections.get(i)[1];

            flightsGraph.computeIfAbsent(startConnection, c -> new ArrayList<>()).add(endConnection);
            flightsGraph.computeIfAbsent(endConnection, c -> new ArrayList<>()).add(startConnection);
        }

        var citiesQueue = new ArrayDeque<String>();
        citiesQueue.add(start);

        var visitedCities = new HashSet<String>();
        visitedCities.add(start);

        var connectionCounter = 1;

        while (!citiesQueue.isEmpty()) {
            var layerSize = citiesQueue.size();

            for (var i = 0; i < layerSize; i++) {
                var currentCity = citiesQueue.poll();
                var connectionCities = flightsGraph.getOrDefault(currentCity, List.of());

                for (var city : connectionCities) {
                    if (visitedCities.add(city)) {

                        if (city.equals(end)) {
                            return connectionCounter;
                        }

                        citiesQueue.add(city);
                    }
                }
            }
            connectionCounter++;
        }

        return -1;
    }


    public static int solution2(List<String[]> connections, String start, String end) {
        if (connections.isEmpty()) {
            return -1;
        }

        if (start.equals(end)) {
            return 0;
        }

        var connectionGraph = new HashMap<String, List<String>>();

        var citiesQueue = new ArrayDeque<String>();
        citiesQueue.add(start);

        var visitedCitiesSet = new HashSet<String>();
        visitedCitiesSet.add(start);

        var minimumConnections = 0;

        for (var connection : connections) {
            var cityTo = connection[0];
            var cityFrom = connection[1];

            connectionGraph.computeIfAbsent(cityTo, c -> new ArrayList<>()).add(cityFrom);
            connectionGraph.computeIfAbsent(cityFrom, c -> new ArrayList<>()).add(cityTo);
        }

        while (!citiesQueue.isEmpty()) {
            var layerSize = citiesQueue.size();

            for (var i = 0; i < layerSize; i++) {
                var currentCity = citiesQueue.poll();

                if (Objects.equals(currentCity, end)) {
                    return minimumConnections;
                }

                var connection = connectionGraph.getOrDefault(currentCity, List.of());

                for (var city : connection) {
                    if (visitedCitiesSet.add(city)) {
                        citiesQueue.add(city);
                    }
                }
            }

            minimumConnections++;
        }

        return -1;
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
