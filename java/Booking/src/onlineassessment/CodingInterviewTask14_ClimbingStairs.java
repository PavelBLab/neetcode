package onlineassessment;

import java.util.Arrays;

public class CodingInterviewTask14_ClimbingStairs {

    /*
     * Dynamic Programming (DP) Problem: Climbing Stairs
     *
     * A hotel has a staircase with n steps. You can climb 1 or 2 steps
     * at a time. How many distinct ways can you reach the top?
     *
     * Example 1: n=1 → 1 way:  [1]
     * Example 2: n=2 → 2 ways: [1,1] or [2]
     * Example 3: n=3 → 3 ways: [1,1,1] or [1,2] or [2,1]
     * Example 4: n=4 → 5 ways: [1,1,1,1] or [1,1,2] or [1,2,1] or [2,1,1] or [2,2]
     *
     * Notice: ways(4) = ways(3) + ways(2) = 3 + 2 = 5
     * Because from step 4 you either came from step 3 (1 step) or step 2 (2 steps).
     * This is the Fibonacci pattern!
     */
    public static void main(String[] args) {
        System.out.println(solution4(1));  // Expected: 1
        System.out.println(solution4(2));  // Expected: 2
        System.out.println(solution4(3));  // Expected: 3
        System.out.println(solution4(4));  // Expected: 5
        System.out.println(solution4(5));  // Expected: 8
    }

    public static int solution4(int n) {
        if (n == 0) {
            throw new IllegalArgumentException("Value n cannot be equal to 0");
        }

        var firstStep = 1;
        if (n == 1) return 1;

        var nextStep = 2;

        for (var i = 3; i <= n; i++) {
            var temp = nextStep;
            nextStep = firstStep + nextStep;
            firstStep = temp;
        }

        return nextStep;
    }




    public static int solution3(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Wrong n");
        }

        var dpArr = new int[n + 1];
        dpArr[0] = 0;
        dpArr[1] = 1;

        if (n == 1) return 1;
        if (n == 2) return 2;

        dpArr[2] = 2;

        for (var i = 3; i <= n; i++) {
            dpArr[i] = dpArr[i -2] + dpArr[i - 1];
        }

        return dpArr[n];
    }

    public static int solution2(int n) {
        var dpArr = new int[n + 1];
        dpArr[0] = 0;
        dpArr[1] = 1;

        if (n <= 2) return n;

        dpArr[2] = 2;

        for (var i = 3; i <= n; i++) {
            dpArr[i] = dpArr[i - 2] + dpArr[i - 1];
        }

        return dpArr[n];
    }


    public static int solution1(int n) {
        var dpArr = new int[n + 1];
        dpArr[0] = 1;
        dpArr[1] = 2;

        for (var i = 3; i <= n; i++) {
            dpArr[i] = dpArr[i - 1] + dpArr[i - 2];
        }


        return dpArr[n];
    }
}
