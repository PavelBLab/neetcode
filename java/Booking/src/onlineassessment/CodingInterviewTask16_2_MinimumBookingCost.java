package onlineassessment;

public class CodingInterviewTask16_2_MinimumBookingCost {

    /*
     * Booking.com offers three types of room packages:
     *   - Single night: costs[0] per night (covers 1 night)
     *   - Weekend bundle: costs[1] (covers 2 nights)
     *   - Weekly bundle: costs[2] (covers 3 nights)
     *
     * Given a number of nights n and the costs array,
     * find the minimum cost to cover exactly n nights.
     *
     * Example: n=5, costs=[100, 180, 250]
     *   Option: weekly(3) + weekend(2) = 250 + 180 = 430
     *   Option: weekend(2) + weekly(3) = 180 + 250 = 430
     *   Option: 5 singles = 500
     *   Minimum: 430
     */
    public static void main(String[] args) {
        System.out.println(solution3(1, new int[]{100, 180, 250}));
        // Expected: 100

        System.out.println(solution3(2, new int[]{100, 180, 250}));
        // Expected: 180

        System.out.println(solution3(3, new int[]{100, 180, 250})); // 300, 280, 250
        // Expected: 250

        System.out.println(solution3(4, new int[]{100, 180, 250}));
        // Expected: 350

        System.out.println(solution3(5, new int[]{100, 180, 250}));
        // Expected: 430
    }

    public static int solution3(int n, int[] costs) {
        if (n == 0) {
            throw new IllegalArgumentException("Value n cannot be 0. Minimum 1");
        }

        var dpArr = new int[n + 1];
        dpArr[0] = 0;

        for (var i = 1; i <= n; i++) {
            dpArr[i] = costs[0] + dpArr[i - 1];

            if (i >= 2) dpArr[i] = Math.min(costs[1] + dpArr[i - 2], dpArr[i]);
            if (i >= 3) dpArr[i] = Math.min(costs[2] + dpArr[i - 3], dpArr[i]);
        }

        return dpArr[n];
    }

    public static int solution2(int n, int[] costs) {
        var dpArr = new int[n + 1];
        dpArr[0] = 0;

        for (var i = 1; i <= n;  i++) {
            dpArr[i] = costs[0] + dpArr[i - 1];

            if (i >= 2) {
                dpArr[i] = Math.min(dpArr[i], costs[1] + dpArr[i - 2]);  // weekend
            }

            if (i >= 3) {
                dpArr[i] = Math.min(dpArr[i], costs[2] + dpArr[i - 3]);  // weekend
            }
        }


        return dpArr[n];
    }

    public static int solution1(int n, int[] costs) {
        if (costs.length == 1) return costs[0];
        if (costs.length == 2) return costs[0] + costs[1];

        var dpArr = new int[n + 1];
        dpArr[0] = 0;

        for (var i = 1; i <= n; i++) {
            dpArr[i] = costs[0] + dpArr[i - 1];              // single: always valid

            if (i >= 2) {
                var x1 = dpArr[i];
                var x2 =  costs[1] + dpArr[i - 2];

                dpArr[i] = Math.min(dpArr[i], costs[1] + dpArr[i - 2]);  // weekend
            }

            if (i >= 3) {
                var y1 = dpArr[i];
                var y2 = costs[2] + dpArr[i - 3];

                dpArr[i] = Math.min(dpArr[i], costs[2] + dpArr[i - 3]);  // weekly
            }
            var x = 0;
        }

        return dpArr[n];
    }
}
