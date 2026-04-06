package onlineassessment;

import java.util.*;

public class CodingInterviewTask2_HotelRoomAvailability {


    /*
     * Problem: Hotel Room Availability
     *
     * A hotel has K rooms. The manager receives N advance bookings,
     * each with an arrival day and a departure day.
     * A guest occupies a room from arrival day up to (but NOT including) departure day.
     *
     * Determine whether the hotel can accommodate ALL bookings
     * without running out of rooms.
     *
     * Return true if all bookings can be handled, false otherwise.
     *
     * Example 1: K=1, arrivals=[1,3,5], departures=[2,6,8] → false
     *   Day 3-5: guest 2 is in the room. Day 5: guest 3 arrives but guest 2
     *   hasn't left yet (departs day 6). Two rooms needed, only 1 available.
     *
     * Example 2: K=2, arrivals=[1,2,3], departures=[2,3,4] → true
     *   At most 1 guest at a time, so 2 rooms is more than enough.
     *
     * Example 3: K=1, arrivals=[1,2], departures=[3,4] → false
     *   Guest 1 stays days 1,2. Guest 2 arrives day 2 while guest 1 is still there.
     */
    public static void main(String[] args) {
        // Test 1: should be false (guest 2 and 3 overlap, need 2 rooms)
        System.out.println(solution4(1, new int[]{1, 3, 5}, new int[]{2, 6, 8}));

        // Test 2: should be true (at most 1 guest at a time, 2 rooms is enough)
        System.out.println(solution4(2, new int[]{1, 2, 3}, new int[]{2, 3, 4}));

        // Test 3: should be false (guest 1 stays days 1,2 and guest 2 arrives day 2)
        System.out.println(solution4(1, new int[]{1, 2}, new int[]{3, 4}));

        // Test 4: should be true (back-to-back bookings, no overlap)
        System.out.println(solution4(1, new int[]{1, 3, 5}, new int[]{3, 5, 7}));
    }

    public static boolean solution4(int k, int[] arrivals, int[] departures) {
        if (arrivals.length != departures.length) {
            throw new IllegalArgumentException("Arrivals not the same size as departure");
        }

        Arrays.sort(arrivals);
        Arrays.sort(departures);

        var arrivalPointer = 0;
        var departurePointer = 0;
        var occupiedRooms = 0;

        while (arrivalPointer < arrivals.length) {
            if (arrivals[arrivalPointer] < departures[departurePointer]) {
                occupiedRooms++;
                arrivalPointer++;
            } else {
                occupiedRooms--;
                departurePointer++;
            }

            if (occupiedRooms > k) {
                return false;
            }
        }

        return true;
    }

    public static boolean solution3(int k, int[] arrivals, int[] departures) {
        if (arrivals.length != departures.length) {
            throw new IllegalArgumentException("Arrival and departure length are not equal");
        }

        Arrays.sort(arrivals);
        Arrays.sort(departures);

        var arrivalPointer = 0;
        var departurePointer = 0;
        var roomOccupies = 0;

        while (arrivalPointer < arrivals.length) {
            if (arrivals[arrivalPointer] < departures[departurePointer]) {
                arrivalPointer++;
                roomOccupies++;
            } else {
                departurePointer++;
                roomOccupies--;
            }

            if (roomOccupies > k) {
                return false;
            }
        }

        return true;
    }

    public static boolean solution2(int k, int[] arrivals, int[] departures) {
        if (arrivals.length != departures.length) {
            throw new IllegalArgumentException("arrival and departure length are not equal");
        }

        Arrays.sort(arrivals);
        Arrays.sort(departures);

        var countOccupiedRooms = 0;
        var arrivalPointer = 0;
        var departurePointer = 0;

        while (arrivalPointer < arrivals.length) {   // arrival: 1, 3, 5  departure: 2, 6, 8
            if (arrivals[arrivalPointer] < departures[departurePointer]) {
                countOccupiedRooms++;
                arrivalPointer++;
            } else {
                countOccupiedRooms--;
                departurePointer++;
            }

            if (countOccupiedRooms > k) {
                return false;
            }
        }

        return true;
    }

    public static boolean solution1(int k, int[] arrivals, int[] departures) {
        if (arrivals.length != departures.length) {
            throw new RuntimeException("Arrival dates not equal to departure dates");
        }

        var numberOfRoomUsed = 1;

        Arrays.sort(arrivals);
        Arrays.sort(departures);


        for (var i = 0; i < arrivals.length - 1; i++) { // new int[]{1, 4, 3}, new int[]{2, 3, 4}
            if (departures[i] > arrivals[i + 1]) {
                numberOfRoomUsed++;
            } else {
                if (numberOfRoomUsed > 0) {
                    numberOfRoomUsed--;
                }
            }

            if (k < numberOfRoomUsed) {
                return false;
            }
        }
        return true;
    }
}
