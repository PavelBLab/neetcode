package onlineassessment;

public class CodingInterviewTask16_MinimumBookingCost {

    /*
     * Dynamic Programming (DP) Problem: Minimum Booking Cost
     *
     * A traveler needs to book hotels for n nights.
     * Each night has a different price at the available hotel.
     * The traveler can stay 1, 2, or 3 consecutive nights
     * at a discounted bundle rate:
     *   - 1 night: pay full price for that night
     *   - 2 nights: pay prices[i] + prices[i+1] - 10 discount
     *   - 3 nights: pay prices[i] + prices[i+1] + prices[i+2] - 25 discount
     *
     * Find the minimum cost to cover all n nights.
     *
     * Example 1: prices = [30, 40, 20, 50]
     *   Option: book night 0-1 as bundle (30+40-10=60) + night 2-3 as bundle (20+50-10=60) = 120
     *   Option: book all 4 as singles = 30+40+20+50 = 140
     *   Option: book night 0 single (30) + night 1-3 as 3-night bundle (40+20+50-25=85) = 115
     *   Best: 115
     *
     * Example 2: prices = [10, 20]
     *   Single: 10+20 = 30
     *   Bundle: 10+20-10 = 20
     *   Best: 20
     *
     * Example 3: prices = [50]
     *   Only option: 50
     */
    public static void main(String[] args) {
        System.out.println(solution1(new int[]{30, 40, 20, 50}));  // Expected: 115
        System.out.println(solution1(new int[]{10, 20}));           // Expected: 20
        System.out.println(solution1(new int[]{50}));               // Expected: 50
    }

    public static int solution1(int[] prices) {
        if (prices.length == 1) return prices[0];
        if (prices.length == 2) return prices[0] + prices[1] - 10;

        var dp = new int[prices.length + 1];

        dp[0] = prices[0];
        dp[1] = Math.min(prices[0] + prices[1], prices[0] + prices[1] - 10);
        dp[2] = Math.min(dp[1] + prices[2], Math.min(dp[0] + prices[1] + prices[2] - 10, prices[0] + prices[1] + prices[2] - 25));

        for (var i = 3; i < prices.length; i++) {
            dp[i] = Math.min(
                    dp[i - 1] + prices[i],
                    Math.min(
                            dp[i - 2] + prices[i - 1] + prices[i] - 10,
                            dp[i - 3] + prices[i - 2] + prices[i - 1] + prices[i] - 25
                    )
            );
        }

        return dp[prices.length - 1];
    }
}
