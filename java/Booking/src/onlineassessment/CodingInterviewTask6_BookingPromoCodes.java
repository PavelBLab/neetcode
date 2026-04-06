package onlineassessment;

import java.util.*;

public class CodingInterviewTask6_BookingPromoCodes {

    /*
     * Problem: Booking Promo Codes
     *
     * Booking.com runs promotions where certain booking IDs get discount codes.
     * A booking ID (from 1 to n) qualifies for a promo if:
     *   - The ID is a multiple of x OR a multiple of y
     *   - BUT NOT a multiple of z
     *
     * Given integers n, x, y, and z, return a list of all qualifying booking IDs
     * in ascending order.
     *
     * Example 1:
     *   n=20, x=2, y=3, z=6
     *   Multiples of 2 or 3 up to 20: [2,3,4,6,8,9,10,12,14,15,16,18,20]
     *   Remove multiples of 6:        [2,3,4,8,9,10,14,15,16,20]
     *   Result: [2, 3, 4, 8, 9, 10, 14, 15, 16, 20]
     *
     * Example 2:
     *   n=10, x=3, y=5, z=15
     *   Multiples of 3 or 5: [3,5,6,9,10]
     *   Remove multiples of 15: none removed
     *   Result: [3, 5, 6, 9, 10]
     *
     * Example 3:
     *   n=15, x=4, y=6, z=2
     *   Multiples of 4 or 6: [4,6,8,12]
     *   Remove multiples of 2: all removed (4,6,8,12 are all multiples of 2)
     *   Result: []
     */
    public static void main(String[] args) {
        System.out.println(solution5(20, 2, 3, 6));
        // Expected: [2, 3, 4, 8, 9, 10, 14, 15, 16, 20]

        System.out.println(solution5(10, 3, 5, 15));
        // Expected: [3, 5, 6, 9, 10]

        System.out.println(solution5(15, 4, 6, 2));
        // Expected: []

        System.out.println(solution5(1, 1, 1, 1));
        // Expected: []
    }

    public static List<Integer> solution5(int n, int x, int y, int z) {
        var result = new ArrayList<Integer>();

        for (var i = 1; i <= n; i++) {
            if ((i % x == 0 || i % y == 0) && i % z != 0) {
                result.add(i);
            }
        }
        return result;
    }

    public static List<Integer> solution4(int n, int x, int y, int z) {
        var result = new ArrayList<Integer>();

        for (var i = 1; i <= n; i++) {
            if ((i % x == 0 || i % y == 0) && i % z != 0) {
                result.add(i);
            }
        }

        return result;
    }

    public static List<Integer> solution3(int n, int x, int y, int z) {
        var result = new ArrayList<Integer>();

        for (var i = 1; i <= n; i++) {
            if (i % z != 0 && (i % x == 0 || i % y == 0)) {
                result.add(i);
            }
        }

        return result;
    }

    public static List<Integer> solution2(int n, int x, int y, int z) {
        var result = new ArrayList<Integer>();

        for (var i = 1; i <= n; i++) {
            if (i % x == 0 || i % y == 0) {
                if (i % z != 0) {
                    result.add(i);
                }
            }
        }

        return result;
    }

    public static List<Integer> solution1(int n, int x, int y, int z) {
        var result = new ArrayList<Integer>();
        var xMultiplier = 0;
        var yMultiplier = 0;

        while (true) {
            xMultiplier += x;

            if (xMultiplier > n) {
                break;
            }

            if (xMultiplier % z != 0) {
                result.add(xMultiplier);
            }
        }

        while (true) {
            yMultiplier += y;

            if (yMultiplier > n) {
                break;
            }

            if (yMultiplier % z != 0) {
                result.add(yMultiplier);
            }
        }

        return result.stream()
                .sorted(
                        Comparator.comparingInt(i -> i)
                ).toList();
    }
}
