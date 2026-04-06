package onlineassessment;

import java.util.*;

public class CodingInterviewTask5_MissingReservations {

    /*
     * Problem: Missing Reservations
     *
     * Booking.com's system keeps two copies of reservation records for redundancy.
     * Due to a sync issue, some reservations from the primary list are missing
     * in the secondary list.
     *
     * Each reservation has an ID. The same ID can appear multiple times
     * (e.g. a guest books the same hotel twice on different dates).
     *
     * Given the primary list and the secondary list, find all reservation IDs
     * that are in the primary list but missing from the secondary list.
     *
     * Return the missing IDs in the order they appear in the primary list.
     *
     * Example 1:
     *   primary   = [1, 2, 3, 2, 4]
     *   secondary = [2, 1, 4]
     *   result    = [3, 2]
     *   (ID 3 is missing entirely, one copy of ID 2 is missing)
     *
     * Example 2:
     *   primary   = [10, 20, 10, 10]
     *   secondary = [10, 10]
     *   result    = [10, 20]
     *   (one copy of 10 is missing, and 20 is missing entirely)
     *
     * Example 3:
     *   primary   = [5, 6, 7]
     *   secondary = [5, 6, 7]
     *   result    = []
     *   (nothing missing)
     */
    public static void main(String[] args) {
        System.out.println(solution4(
                List.of(1, 2, 3, 2, 4),
                List.of(2, 1, 4)
        )); // Expected: [3, 2]

        System.out.println(solution4(
                List.of(10, 20, 10, 10),
                List.of(10, 10)
        )); // Expected: [20, 10]

        System.out.println(solution4(
                List.of(5, 6, 7),
                List.of(5, 6, 7)
        )); // Expected: []
    }

    public static List<Integer> solution4(List<Integer> primary, List<Integer> secondary) {
        var result = new ArrayList<Integer>();
        var secondaryMap = new HashMap<Integer, Integer>();

        for (var v : secondary) {
            secondaryMap.put(v, secondaryMap.getOrDefault(v, 0) + 1);
        }

        for (var val : primary) {
            if (!secondaryMap.containsKey(val)) {
                result.add(val);
            } else {
                if (secondaryMap.get(val) == 0) {
                    result.add(val);
                }

                secondaryMap.put(val, secondaryMap.get(val) - 1);
            }
        }

        return result;
    }

    public static List<Integer> solution3(List<Integer> primary, List<Integer> secondary) {
        var result = new ArrayList<Integer>();
        var secondaryMap = new HashMap<Integer, Integer>();

        for (var i : secondary) {
            secondaryMap.put(i, secondaryMap.getOrDefault(i, 0) + 1);
        }

        for (var i : primary) {
            var secondaryReservation = secondaryMap.getOrDefault(i, 0);

            if (secondaryReservation == 0) {
                result.add(i);
                continue;
            }

            secondaryMap.put(i, secondaryMap.get(i) - 1);
        }

        return result;
    }

    public static List<Integer> solution2(List<Integer> primary, List<Integer> secondary) {
        var result = new ArrayList<Integer>();
        var secondaryMap = new HashMap<Integer, Integer>();

        for (var i : secondary) {
            secondaryMap.put(i, secondaryMap.getOrDefault(i, 0) + 1);
        }

        for (var i : primary) {
            var secondaryValue = secondaryMap.get(i);
            if (secondaryValue== null || secondaryValue == 0) {
                result.add(i);
            } else {
                secondaryMap.put(i, secondaryValue - 1);
            }
        }

        return result;
    }

    public static List<Integer> solution1(final List<Integer> primary, final List<Integer> secondary) {
        var primaryMap = countReservationIds(primary);
        var secondaryMap = countReservationIds(secondary);
        var result = new ArrayList<Integer>();

        for (var e : primaryMap.entrySet()) {
            if (!secondaryMap.containsKey(e.getKey())) {
                for (var v = 0; v < e.getValue(); v++) {
                    result.add(e.getKey());
                }
            }

            if (secondaryMap.containsKey(e.getKey())) {
                var diff = e.getValue() - secondaryMap.get(e.getKey());

                if (diff != 0) {
                    for (var v = 0; v < diff; v++) {
                        result.add(e.getKey());
                    }
                }
            }
        }

        return result;
    }

    private static Map<Integer, Integer> countReservationIds(final List<Integer> reservations) {
        var map = new HashMap<Integer, Integer>();

        for (var reservation : reservations) {
            map.put(reservation, map.getOrDefault(reservation, 0) + 1);
        }

        return map;
    }
}
