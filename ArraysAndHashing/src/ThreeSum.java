import java.util.*;

public class ThreeSum {

    public static void main(String[] args) {
        System.out.println(Solution1.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }

    static class Solution1 {
        public static List<List<Integer>> threeSum(int[] nums) {
            if (nums.length < 3 || nums.length > 1000) {
                throw new IllegalArgumentException("Invalid input");
            }

            Arrays.sort(nums);

            var result = new HashSet<List<Integer>>();
            var limit = Math.pow(10, 5);


            for (var i = 0; i < nums.length; i++) {
                if (nums[i] < -limit || nums[i] > limit) {
                    throw new IllegalArgumentException("Invalid input");
                }

                for (int j = i + 1; j < nums.length; j++) {
                    for (int k = j + 1; k < nums.length; k++) {
                        if (nums[i] + nums[j] + nums[k] == 0) {
                            var list = new ArrayList<Integer>();
                            list.add(nums[i]);
                            list.add(nums[j]);
                            list.add(nums[k]);

                            result.add(list);
                        }
                    }
                }
            }

            return new ArrayList<>(result);
        }
    }
}