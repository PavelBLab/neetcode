package livecodinginterview;

import java.util.Arrays;
import java.util.HashMap;

public class CodingInterviewTask8_TwoSum {

    /*
     * Problem: Two Sum
     *
     * Given an array of integers and a target sum, return the indices
     * of the two numbers that add up to the target.
     * Each input has exactly one solution.
     * You may not use the same element twice.
     *
     * Classic interview question — reported at Booking.com.
     *
     * Example 1: nums=[2, 7, 11, 15], target=9 → [0, 1] (2+7=9)
     * Example 2: nums=[3, 2, 4], target=6 → [1, 2] (2+4=6)
     * Example 3: nums=[3, 3], target=6 → [0, 1] (3+3=6)
     */
    public static void main(String[] args) {
        System.out.println(Arrays.toString(solution4(new int[]{2, 7, 11, 15}, 9)));
        // Expected: [0, 1]

        System.out.println(Arrays.toString(solution4(new int[]{3, 2, 4}, 6)));
        // Expected: [1, 2]

        System.out.println(Arrays.toString(solution4(new int[]{3, 3}, 6)));
        // Expected: [0, 1]
    }

    public static int[] solution4(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("Nums is empty or null");
        }

        var result = new int[2];
        var map = new HashMap<Integer, Integer>();

        for (var i = 0; i < nums.length; i++) {
            var firstValue = target - nums[i];

            if (map.containsKey(firstValue)) {
                result[0] = map.get(firstValue);
                result[1] = i;

                return result;
            } else {
                map.put(nums[i], i);
            }

        }

        return null;
    }


    public static int[] solution3(int[] nums, int target) {
        var result = new int[2];
        var map = new HashMap<Integer, Integer>();

        for (var i = 0; i < nums.length; i++) {
            var firstValue = nums[i];
            var diff = target - firstValue;

            if (map.containsKey(diff)) {
                result[0] = map.get(diff);
                result[1] = i;

                return result;
            }

            map.put(firstValue, i);
        }

        return result;
    }

    public static int[] solution2(int[] nums, int target) {
        var result = new int[2];
        var map = new HashMap<Integer, Integer>();

        for (var i = 0; i < nums.length; i++) {
            var firstValue = nums[i];
            var diff = target - firstValue;

            if (map.containsKey(diff)) {
                result[0] = map.get(diff);
                result[1] = i;

                return result;
            }

            map.put(firstValue, i);
        }

        return result;
    }

    public static int[] solution1(int[] nums, int target) {
        var result = new int[2];

        for (var i = 0; i < nums.length; i++) {
            for (var j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                }
            }
        }

        return result;
    }

}
