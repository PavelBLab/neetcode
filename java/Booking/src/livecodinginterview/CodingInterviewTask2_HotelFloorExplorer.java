package livecodinginterview;

import java.util.*;

public class CodingInterviewTask2_HotelFloorExplorer {

    /*
     * Problem: Hotel Floor Explorer
     *
     * A hotel has floors connected by staircases. Not all floors
     * connect directly — you can only move between specific floors.
     *
     * Given a map of floor connections and a starting floor,
     * return a list of lists showing which floors you can reach
     * at each step (layer by layer).
     *
     * Connections are bidirectional.
     *
     * Example:
     *   Floor 1 ←→ Floor 2
     *   Floor 1 ←→ Floor 3
     *   Floor 2 ←→ Floor 4
     *   Floor 3 ←→ Floor 4
     *   Floor 4 ←→ Floor 5
     *
     *   start: 1
     *
     *   Step 0: [1]           ← starting floor
     *   Step 1: [2, 3]        ← 1 step from floor 1
     *   Step 2: [4]           ← 2 steps (via floor 2 or 3)
     *   Step 3: [5]           ← 3 steps (via floor 4)
     *
     *   Result: [[1], [2, 3], [4], [5]]
     */
    public static void main(String[] args) {
        var connections = Map.of(
                1, List.of(2, 3),
                2, List.of(1, 4),
                3, List.of(1, 4),
                4, List.of(2, 3, 5),
                5, List.of(4)
        );

        System.out.println(solution7(connections, 1));
        // Expected: [[1], [2, 3], [4], [5]]

        System.out.println(solution7(connections, 4));
        // Expected: [[4], [2, 3, 5], [1]]
    }
    public static List<List<Integer>> solution7(Map<Integer, List<Integer>> connections, int start) {
        var result = new ArrayList<List<Integer>>();
        result.add(List.of(start));

        var floorsDeque = new ArrayDeque<Integer>();
        floorsDeque.add(start);

        var visitedFloors = new HashSet<Integer>();
        visitedFloors.add(start);

        while (!floorsDeque.isEmpty()) {
            var layerSize = floorsDeque.size();
            var layer = new ArrayList<Integer>();

            for (var i = 0; i < layerSize; i++) {
                var currentFloor = floorsDeque.poll();
                var connectedFloors = connections.getOrDefault(currentFloor, List.of());

                for (var floor : connectedFloors) {
                    if (visitedFloors.add(floor)) {
                        floorsDeque.add(floor);
                        layer.add(floor);
                    }
                }
            }

            if (!layer.isEmpty()) {
                result.add(layer);
            }
        }

        return result;
    }


    public static List<List<Integer>> solution6(Map<Integer, List<Integer>> connections, int start) {
        if (connections == null || connections.isEmpty()) {
            return List.of();
        }

        if (!connections.containsKey(start)) {
            return List.of();
        }

        var result = new ArrayList<List<Integer>>();
        result.add(List.of(start));

        var floorsQueue = new ArrayDeque<Integer>();
        floorsQueue.add(start);

        var visitedFloors = new HashSet<Integer>();
        visitedFloors.add(start);

        while (!floorsQueue.isEmpty()) {
            var layerSize = floorsQueue.size();
            var layer = new ArrayList<Integer>();

            for (var i = 0; i < layerSize; i++) {
                var currentFloor = floorsQueue.poll();
                var connectionFloors = connections.getOrDefault(currentFloor, List.of());

                for (var floor : connectionFloors) {
                    if (visitedFloors.add(floor)) {
                        floorsQueue.add(floor);
                        layer.add(floor);
                    }
                }
            }

            if (!layer.isEmpty()) {
                result.add(layer);
            }
        }

        return result;
    }

    public static List<List<Integer>> solution5(Map<Integer, List<Integer>> connections, int start) {
        if (connections == null || connections.isEmpty()) {
            return List.of();
        }

        var result = new ArrayList<List<Integer>>();
        result.add(List.of(start));

        var floorQueue = new ArrayDeque<Integer>();
        floorQueue.add(start);

        var visitedFloors = new HashSet<Integer>();
        visitedFloors.add(start);

        while (!floorQueue.isEmpty()) {
            var layerSize = floorQueue.size();
            var layer = new ArrayList<Integer>();

            for (var i = 0; i < layerSize; i++) {
                var currentFloor = floorQueue.poll();
                var connectionFloors = connections.get(currentFloor);

                for (var connection : connectionFloors) {
                    if (visitedFloors.add(connection)) {
                        layer.add(connection);
                        floorQueue.add(connection);
                    }
                }
            }

            if (!layer.isEmpty()) {
                result.add(layer);
            }
        }

        return result;
    }


    public static List<List<Integer>> solution4(Map<Integer, List<Integer>> connections, int start) {
        var result = new ArrayList<List<Integer>>();
        result.add(List.of(start));

        var floorsQueue = new ArrayDeque<Integer>();
        floorsQueue.add(start);

        var visitedFloors = new HashSet<Integer>();
        visitedFloors.add(start);

        while (!floorsQueue.isEmpty()) {
            var layerSize = floorsQueue.size();
            var layer = new ArrayList<Integer>();

            for (var i = 0; i < layerSize; i++) {
                var currentFloor = floorsQueue.poll();
                var connectionFloors = connections.getOrDefault(currentFloor, List.of());

                for (var floor : connectionFloors) {
                    if (visitedFloors.add(floor)) {
                        layer.add(floor);
                        floorsQueue.add(floor);
                    }
                }
            }

            if (!layer.isEmpty()) {
                result.add(layer);
            }
        }

        return result;
    }

    public static List<List<Integer>> solution3(Map<Integer, List<Integer>> connections, int start) {
        var result = new ArrayList<List<Integer>>();
        result.add(List.of(start));

        var floorsQueue = new ArrayDeque<Integer>();
        floorsQueue.add(start);

        var visitedFloors = new HashSet<Integer>();
        visitedFloors.add(start);

        while (!floorsQueue.isEmpty()) {
            var layerSize = floorsQueue.size();
            var layer = new ArrayList<Integer>();

            for (var i = 0; i < layerSize; i++) {
                var currentFloor = floorsQueue.poll();
                var connectionFloors = connections.getOrDefault(currentFloor, List.of());

                for (var connection : connectionFloors) {
                    if (visitedFloors.add(connection)) {
                        layer.add(connection);
                        floorsQueue.add(connection);
                    }
                }
            }

            if (!layer.isEmpty()) {
                result.add(layer);
            }
        }

        return result;
    }

    public static List<List<Integer>> solution2(Map<Integer, List<Integer>> connections, int start) {
        var result = new ArrayList<List<Integer>>();
        result.add(List.of(start));

        if (connections.isEmpty()) {
            return result;
        }

        var floorsQueue = new ArrayDeque<Integer>();
        floorsQueue.add(start);

        var visitedFloorsSet = new HashSet<Integer>();
        visitedFloorsSet.add(start);

        while (!floorsQueue.isEmpty()) {
            var layerSize = floorsQueue.size();
            var layer = new ArrayList<Integer>();

            for (var i = 0; i < layerSize; i++) {
                var currentFloor = floorsQueue.poll();
                var floorConnections = connections.getOrDefault(currentFloor, List.of());

                for (var floor : floorConnections) {
                    if (visitedFloorsSet.add(floor)) {
                        floorsQueue.add(floor);
                        layer.add(floor);
                    }
                }
            }

            if (!layer.isEmpty()) {
                result.add(layer);
            }
        }

        return result;
    }


    public static List<List<Integer>> solution1(Map<Integer, List<Integer>> connections, int start) {
        var result = new ArrayList<List<Integer>>();
        result.add(List.of(start));

        var floorQueue = new ArrayDeque<Integer>();
        floorQueue.add(start);

        var visitedFloorSet = new HashSet<Integer>();
        visitedFloorSet.add(start);

        while (!floorQueue.isEmpty()) {
            var layerSize = floorQueue.size();
            var layer = new ArrayList<Integer>();

            for (var i = 0; i < layerSize; i++) {
                var floor = floorQueue.poll();
                var floorConnections = connections.getOrDefault(floor, List.of());

                for (var floorConnection : floorConnections) {
                    if (visitedFloorSet.add(floorConnection)) {
                        layer.add(floorConnection);
                        floorQueue.add(floorConnection);
                    }
                }
            }

            if (layer.isEmpty()) {
                continue;
            }

            result.add(layer);
        }

        return result;
    }
}
