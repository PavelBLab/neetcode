package livecodinginterview;

public class CodingInterviewTask7_HotelPriceSearch {

    /*
     * Problem: Hotel Price Search
     *
     * Booking.com has a sorted list of hotel prices.
     * Given a budget, find the index of the hotel matching
     * that exact price. Return -1 if no match.
     *
     * Use binary search — O(log n) instead of O(n) linear scan.
     *
     * Example: prices = [50, 80, 120, 200, 350, 500, 750]
     *          budget = 200 → index 3
     *          budget = 100 → -1 (no exact match)
     */
    public static void main(String[] args) {
        var prices = new int[]{50, 80, 120, 200, 350, 500, 750, 800, 850, 900, 950, 1000, 1050, 1100};

        // Test 1: found in middle
        System.out.println(solution4(prices, 200));
        // Expected: 3

        // Test 2: found at start
        System.out.println(solution4(prices, 50));
        // Expected: 0

        // Test 3: found at end
        System.out.println(solution4(prices, 750));
        // Expected: 6

        // Test 4: not found
        System.out.println(solution4(prices, 100));
        // Expected: -1

        // Test 5: single element found
        System.out.println(solution4(new int[]{42}, 42));
        // Expected: 0
    }

    public static int solution4(int[] prices, int budget) {
        var lowPointer = 0;
        var hiPointer = prices.length - 1;

        while (lowPointer <= hiPointer) {
            var midPointer = (lowPointer + hiPointer)/ 2;

            if (prices[midPointer] == budget) {
                return midPointer;
            }

            if (budget < prices[midPointer]) {
                hiPointer = midPointer - 1;
            } else {
                lowPointer = midPointer + 1;
            }
        }

        return -1;
    }


    public static int solution3(int[] prices, int budget) {
        if (prices == null || prices.length == 0) {
            return -1;
        }

        var highPointer = prices.length - 1;
        var lowPointer = 0;

        while (lowPointer <= highPointer) {
            var midPointer = (lowPointer + highPointer) / 2;

            if (budget == prices[midPointer]) {
                return midPointer;
            }

            if (budget < prices[midPointer]) {
                highPointer = midPointer - 1;
            } else {
                lowPointer = midPointer + 1;
            }
        }

        return -1;
    }

    public static int solution2(int[] prices, int budget) {
        var highPointer = prices.length -1;
        var lowPointer = 0;
        var midPointer = (highPointer + lowPointer) / 2;

        while (lowPointer <= highPointer) {
            midPointer = (highPointer + lowPointer) / 2;

            if (prices[midPointer] == budget) {
                return midPointer;
            }

            if (budget < prices[midPointer]) {     // |----------|----------|
                highPointer = midPointer - 1;
            } else {
                lowPointer = midPointer + 1;
            }
        }

        return -1;
    }


    public static int solution1(int[] prices, int budget) {
        var lowPointer = 0;
        var highPointer = prices.length - 1;
        var midPointer = (lowPointer + highPointer) / 2;

        while (lowPointer <= highPointer) {
            midPointer = (lowPointer + highPointer) / 2;

            if (prices[midPointer] == budget) {
                return midPointer;
            }

            if (budget < prices[midPointer]) {
                highPointer = midPointer - 1;
            } else {
                lowPointer = midPointer + 1;
            }
        }

        return -1;
    }
}
