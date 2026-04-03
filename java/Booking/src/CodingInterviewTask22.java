import java.util.ArrayList;
import java.util.List;

public class CodingInterviewTask22 {

    /*
     * Problem: Delta Encoding
     *
     * Booking.com compresses price history data using delta encoding.
     * Given a list of numbers, output the difference between each
     * consecutive pair. The first value stays the same.
     *
     * Reported in Booking.com OA.
     *
     * Example 1: [100, 102, 105, 103, 110] → [100, 2, 3, -2, 7]
     *   102-100=2, 105-102=3, 103-105=-2, 110-103=7
     *
     * Example 2: [50] → [50]
     * Example 3: [10, 10, 10] → [10, 0, 0]
     */
    public static void main(String[] args) {
        System.out.println(solution1(new int[]{100, 102, 105, 103, 110}));
        // Expected: [100, 2, 3, -2, 7]

        System.out.println(solution1(new int[]{50}));
        // Expected: [50]

        System.out.println(solution1(new int[]{10, 10, 10}));
        // Expected: [10, 0, 0]
    }

    public static List<Integer> solution1(int[] nums) {
        if (nums.length == 0) {
            return List.of();
        }

        var result = new ArrayList<Integer>();
        result.add(nums[0]);


        for (var i = 0; i < nums.length - 1; i++) {
            var delta = nums[i + 1] - nums[i];
            result.add(delta);
        }


        return result;
    }
}
