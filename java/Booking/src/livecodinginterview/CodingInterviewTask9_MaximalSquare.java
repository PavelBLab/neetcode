package livecodinginterview;

public class CodingInterviewTask9_MaximalSquare {

    /*
     * Problem: Maximal Square (LeetCode 221)
     *
     * Booking.com wants to find the largest square section of
     * available rooms in a hotel floor plan grid.
     *
     * Given a 2D grid of 1 (available) and 0 (unavailable),
     * find the area of the largest square containing only 1s.
     *
     * dp[i][j] = side length of largest square with bottom-right
     *            corner at position (i, j)
     *
     * Formula:
     *   If grid[i][j] == 0: dp[i][j] = 0
     *   If grid[i][j] == 1: dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1]) + 1
     *                                       (up)         (left)       (diagonal)
     *
     * Example:
     *   grid:          dp:
     *   [1, 0, 1]     [1, 0, 1]
     *   [1, 1, 1]     [1, 1, 1]
     *   [1, 1, 1]     [1, 2, 2]  ← max is 2, area = 4
     */
    public static void main(String[] args) {
        // Test 1
        System.out.println(solution1(new int[][]{
                {1, 0, 1, 0, 0},
                {1, 0, 1, 1, 1},
                {1, 1, 1, 1, 1},
                {1, 0, 0, 1, 0}
        }));
        // Expected: 4

        // Test 2
        System.out.println(solution1(new int[][]{
                {0, 1},
                {1, 0}
        }));
        // Expected: 1

        // Test 3
        System.out.println(solution1(new int[][]{
                {0, 0},
                {0, 0}
        }));
        // Expected: 0

        // Test 4: all 1s
        System.out.println(solution1(new int[][]{
                {1, 1, 1},
                {1, 1, 1},
                {1, 1, 1}
        }));
        // Expected: 9
    }

    public static int solution1(int[][] grid) {

        if (grid == null || grid.length == 0) {
            return 0;
        }

        int rows = grid.length;
        int cols = grid[0].length;
        var dp = new int[rows][cols];
        int maxSide = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 0) {
                    dp[i][j] = 0;
                } else if (i == 0 || j == 0) {
                    // First row or first column — max possible is 1×1
                    dp[i][j] = 1;
                } else {
                    dp[i][j] = Math.min(
                            Math.min(dp[i-1][j], dp[i][j-1]),
                            dp[i-1][j-1]
                    ) + 1;
                }

                maxSide = Math.max(maxSide, dp[i][j]);
            }
        }

        return maxSide * maxSide; // area
    }
}
