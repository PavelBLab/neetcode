package onlineassessment;

public class CodingInterviewTask15_HotelBookingRevenue {

    /*
     * Dynamic Programming (DP) Problem: Hotel Booking Revenue
     *
     * Booking.com tracks daily revenue. Find the maximum sum
     * of non-adjacent days (you can't pick two consecutive days).
     *
     * Example 1: [3, 2, 7, 10] → 13 (day 0: 3 + day 3: 10)
     * Example 2: [3, 2, 5, 10, 7] → 15 (day 0: 3 + day 2: 5 + day 4: 7)
     * Example 3: [5] → 5
     * Example 4: [5, 1] → 5
     */
    public static void main(String[] args) {
//        System.out.println(solution1(new int[]{3, 2, 7, 10}));   // Expected: 13
        System.out.println(solution1(new int[]{3, 2, 5, 10, 7})); // Expected: 15
//        System.out.println(solution1(new int[]{5}));               // Expected: 5
//        System.out.println(solution1(new int[]{5, 1}));            // Expected: 5
    }

    public static int solution1(int[] revenue) {
        var dp = new int[revenue.length + 1];
        dp[0] = revenue[0];
        dp[1] = Math.max(revenue[0], revenue[1]);

        for (var i = 2; i < revenue.length; i++) {
            var y = dp[i - 1];
            var x = dp[i - 2];
            var r = revenue[i];

            dp[i] = Math.max(revenue[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[revenue.length - 1];
    }
}
