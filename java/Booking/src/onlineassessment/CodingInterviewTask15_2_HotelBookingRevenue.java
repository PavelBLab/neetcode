package onlineassessment;

public class CodingInterviewTask15_2_HotelBookingRevenue {

    /*
     * A hotel has a list of booking requests, each with a revenue value.
     * Adjacent bookings conflict — you cannot accept two bookings
     * that are next to each other in the list.
     *
     * Find the maximum revenue you can earn by selecting
     * non-adjacent bookings.
     *
     * Same as "House Robber" problem.
     *
     * Example: [200, 150, 300, 100, 400]
     * Best: 200 + 300 + 400 = 900 (skip indices 1 and 3)
     */
    public static void main(String[] args) {
        System.out.println(solution3(new int[]{200, 150, 300, 100, 400}));
        // Expected: 900

        System.out.println(solution3(new int[]{100, 200, 300}));
        // Expected: 400 (100 + 300)

        System.out.println(solution3(new int[]{500}));
        // Expected: 500
    }

    public static int solution3(int[] bookings) {
        if (bookings == null || bookings.length == 0) {
            throw new IllegalArgumentException("Bookings is null or empty");
        }

        var firstRevenue = bookings[0];

        if (bookings.length == 1) return firstRevenue;

        var maximisedRevenue = Math.max(firstRevenue, bookings[1]);

        for (var i = 2; i < bookings.length; i++) {
            var temp = maximisedRevenue;
            maximisedRevenue = Math.max(bookings[i] + firstRevenue, maximisedRevenue);
            firstRevenue = temp;
        }

        return maximisedRevenue;
    }


    public static int solution2(int[] bookings) {
        if (bookings == null || bookings.length == 0) {
            throw new IllegalArgumentException("null or empty booking");
        }

        var dpArr = new int[bookings.length];
        dpArr[0] = bookings[0];

        if (bookings.length == 1) {
            return bookings[0];
        }

        dpArr[1] = Math.max(bookings[0], bookings[1]);

        for (var i = 2; i < bookings.length; i++) {
            dpArr[i] = Math.max(bookings[i] + dpArr[i - 2], dpArr[i - 1]);
        }

        return dpArr[bookings.length - 1];
    }



    public static int solution1(int[] bookings) {
        if (bookings.length == 1) {
            return bookings[0];
        }

        var prevTwo = bookings[0];                        // dp[i-2]
        var prevOne = Math.max(bookings[0], bookings[1]); // dp[i-1]

        for (var i = 2; i < bookings.length; i++) {
            var current = Math.max(bookings[i] + prevTwo, prevOne);
            prevTwo = prevOne;
            prevOne = current;
        }

        return prevOne;
    }
}
