import java.util.Arrays;
import java.util.HashMap;

public class TwoSum {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution1.twoSum(new int[]{3, 4, 5, 6}, 7)));
        System.out.println(Arrays.toString(Solution1.twoSum(new int[]{4, 5, 6}, 10)));
        System.out.println(Arrays.toString(Solution1.twoSum(new int[]{5, 5}, 10)));
        System.out.println("-----------------------------------------------");

        System.out.println(Arrays.toString(Solution2.twoSum(new int[]{3, 4, 5, 6}, 7)));
        System.out.println(Arrays.toString(Solution2.twoSum(new int[]{4, 5, 6}, 10)));
        System.out.println(Arrays.toString(Solution2.twoSum(new int[]{5, 5}, 10)));
        System.out.println("-----------------------------------------------");

        System.out.println(Arrays.toString(Solution3.twoSum(new int[]{3, 4, 5, 6}, 7)));
        System.out.println(Arrays.toString(Solution3.twoSum(new int[]{4, 5, 6}, 10)));
        System.out.println(Arrays.toString(Solution3.twoSum(new int[]{5, 5}, 10)));
        System.out.println(Arrays.toString(Solution3.twoSum(new int[]{3, 2, 3}, 6)));
        System.out.println("-----------------------------------------------");

        System.out.println(Arrays.toString(Solution4.twoSum(new int[]{3, 4, 5, 6}, 7)));
        System.out.println(Arrays.toString(Solution4.twoSum(new int[]{4, 5, 6}, 10)));
        System.out.println(Arrays.toString(Solution4.twoSum(new int[]{5, 5}, 10)));
        System.out.println(Arrays.toString(Solution4.twoSum(new int[]{3, 2, 3}, 6)));
        System.out.println("-----------------------------------------------");

        System.out.println(Arrays.toString(Solution5.twoSum(new int[]{3, 4, 5, 6}, 7)));
        System.out.println("-----------------------------------------------");
    }


    static class Solution5 {
        public static int[] twoSum(int[] nums, int target) { // target 3 [1,3,2]
            HashMap<Integer, Integer> differenceOfIndex = new HashMap<>();

            System.out.println("Target: " + target);
            System.out.println("Nums: " + java.util.Arrays.toString(nums));
            System.out.println("---");

            for (int i = 0; i < nums.length; i++) {
                System.out.println("Step " + i + ": looking at nums[" + i + "] = " + nums[i]);
                System.out.println("  Check: is " + nums[i] + " in map? " + differenceOfIndex.containsKey(nums[i]));

                if (differenceOfIndex.get(nums[i]) == null) {
                    int diff = target - nums[i];
                    System.out.println("  Not found. Storing: map[" + diff + "] = " + i + "  (because " + target + " - " + nums[i] + " = " + diff + ")");
                    differenceOfIndex.put(diff, i);
                } else {
                    int j = differenceOfIndex.get(nums[i]);
                    System.out.println("  Found! nums[" + j + "] + nums[" + i + "] = " + nums[j] + " + " + nums[i] + " = " + target);
                    System.out.println("  Return [" + j + ", " + i + "]");
                    return new int[]{j, i};
                }

                System.out.println("  Map state: " + differenceOfIndex);
                System.out.println("---");
            }

            System.out.println("No solution found.");
            return null;
        }
    }

    static class Solution4 {
        public static int[] twoSum(int[] nums, int target) { // target 3 [1,3,2]
            //Nums.length has to be greater than or equal to 2
            //Target and numbers in nums can be negative or positive or zero
            HashMap<Integer, Integer> differenceOfIndex = new HashMap<>();
            for (int i = 0; i < nums.length; i++) {
                if (differenceOfIndex.get(nums[i]) == null) {
                    differenceOfIndex.put(target - nums[i], i); // 2=0, 0=1
                } else {
                    return new int[]{differenceOfIndex.get(nums[i]), i}; // [0, 1]
                }
            }
            return null;
        }
    }

    static class Solution3 {
        public static int[] twoSum(int[] nums, int target) {
            if (nums.length < 2 || nums.length > 1000) {
                throw new IllegalArgumentException("Invalid input");
            }

            if (target < -10_000_000 || target > 10_000_000) {
                throw new IllegalArgumentException("Invalid input");
            }

            var map = new HashMap<Integer, Integer>();
            var result = new int[2];

            for (var i = 0; i < nums.length; i++) {
                if (nums[i] < -10_000_000 || nums[i] > 10_000_000) {
                    throw new IllegalArgumentException("Invalid input");
                }

                var currentValue = nums[i];
                var previousValue = target - currentValue;

                if (map.containsKey(previousValue)) {
                    result[0] = map.get(previousValue);
                    result[1] = i;
                    return result;
                }

                map.put(nums[i], i); // 3=0, 2=1, 3=2

            }

            return result;
        }
    }

    static class Solution2 {
        public static int[] twoSum(int[] nums, int target) {
            if (nums.length < 2 || nums.length > 1000) {
                throw new IllegalArgumentException("Invalid input");
            }

            if (target < -10_000_000 || target > 10_000_000) {
                throw new IllegalArgumentException("Invalid input");
            }

            var result = new int[2];

            var prepMap = new HashMap<Integer, Integer>();

            for (var i = 0; i < nums.length; i++) {
                if (nums[i] < -10_000_000 || nums[i] > 10_000_000) {
                    throw new IllegalArgumentException("Invalid input");
                }

                var currentValue = nums[i];
                var previousValue = target - currentValue;

                if (prepMap.containsKey(previousValue)) {
                    result[0] = prepMap.get(previousValue);
                    result[1] = i;
                    return result;
                }

                prepMap.put(nums[i], i);
            }
            return result;
        }
    }

    static class Solution1 {
        public static int[] twoSum(int[] nums, int target) {
            if (nums.length < 2 || nums.length > 1000) {
                throw new IllegalArgumentException("Invalid input");
            }

            if (target < -10_000_000 || target > 10_000_000) {
                throw new IllegalArgumentException("Invalid input");
            }

            var result = new int[2];

            for (var i = 0; i < nums.length; i++) {
                if (nums[i] < -10_000_000 || nums[i] > 10_000_000) {
                    throw new IllegalArgumentException("Invalid input");
                }


                for (var j = i + 1; j < nums.length; j++) {
                    if (nums[i] + nums[j] == target) {
                        result[0] = i;
                        result[1] = j;
                        return result;
                    }
                }
            }
            return result;
        }
    }

}
