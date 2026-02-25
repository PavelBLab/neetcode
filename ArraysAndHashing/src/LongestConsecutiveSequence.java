import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.stream.Collectors;

public class LongestConsecutiveSequence {

    public static void main(String[] args) {

        System.out.println(Solution1.longestConsecutive(new int[]{2, 20, 4, 10, 3, 4, 5}));
        System.out.println(Solution1.longestConsecutive(new int[]{0, 3, 2, 5, 4, 6, 1, 1}));
        System.out.println(Solution1.longestConsecutive(new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6}));
        System.out.println("-----------------------------------------------");
//
//        System.out.println(Solution2.longestConsecutive(new int[]{2, 20, 4, 10, 3, 4, 5}));
//        System.out.println(Solution2.longestConsecutive(new int[]{0, 3, 2, 5, 4, 6, 1, 1}));
//        System.out.println(Solution2.longestConsecutive(new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6}));
//        System.out.println("-----------------------------------------------");

        System.out.println(Solution4.longestConsecutive(new int[]{2, 20, 4, 10, 3, 4, 5}));
        System.out.println(Solution4.longestConsecutive(new int[]{0, 3, 2, 5, 4, 6, 1, 1}));
        System.out.println(Solution4.longestConsecutive(new int[]{9, 1, 4, 7, 3, -1, 0, 5, 8, -1, 6}));
        System.out.println("-----------------------------------------------");
    }

    static class Solution4 {
        public static int longestConsecutive(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }

            Arrays.sort(nums);
            System.out.println(Arrays.toString(nums));

            var maxCount = 1;
            var innerCount = 1;

            // [21,22,20,9,8,7,1,4,3,2,0]
            for (var i = 0; i < nums.length - 1; i++) {
                var currentValue = nums[i];
                var nextValue = nums[i + 1];

                if (currentValue == nextValue) {
                    continue;
                }

                if (currentValue + 1 == nextValue) {
                    innerCount++;
                } else {
                    innerCount = 1;
                    continue;
                }

                if (maxCount < innerCount) {
                    maxCount = innerCount;
                }
            }

            return maxCount;
        }
    }


    static class Solution3 {
        public static int longestConsecutive(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }

            var set = Arrays.stream(nums).boxed().collect(Collectors.toSet());

            var maxCount = 1;

            // [21,22,20,9,8,7,1,4,3,2,0]
            for (var n : nums) {
                if (set.contains(n - 1)) {
                    continue;
                };

                var innerCount = 1;

                for (var i = 1; ; i++) {
                    if (set.contains(n + i)) {
                        innerCount++;
                    } else {
                        if (maxCount < innerCount) {
                            maxCount = innerCount;
                        }
                        break;
                    }
                }
            }

            System.out.println(set);

            return maxCount;
        }
    }

    static class Solution2 {

        public static int longestConsecutive(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            var set = Arrays.stream(nums).boxed().collect(Collectors.toSet());
            var list = set.stream().sorted().toList();
            System.out.println(list);
            var result = new ArrayList<Integer>();

            for (var i = 0; i < list.size(); i++) {
                if (nums[i] < -Math.pow(10, 9) || nums[i] > Math.pow(10, 9)) {
                    throw new IllegalArgumentException("Invalid input");
                }

                var count = 1;

                for (var j = i; j < list.size() - 1; j++) {
                    var currentValue = list.get(j);
                    var nextValue = list.get(j + 1);

                    if (currentValue + 1 != nextValue) {
                        break;
                    }
                    count++;
                }
                result.add(count);

            }

            System.out.println(result);

            return result.stream().max(Comparator.comparingInt(o -> o)).orElseThrow();
        }
    }

    static class Solution1 {

        public static int longestConsecutive(int[] nums) {
            if (nums == null || nums.length == 0) {
                return 0;
            }

            if (nums.length > 1000) {
                throw new IllegalArgumentException("Invalid input");
            }

            Arrays.sort(nums);
            System.out.println(Arrays.toString(nums));

            var result = new ArrayList<Integer>();

            for (var i = 0; i < nums.length; i++) {
                if (nums[i] < -Math.pow(10, 9) || nums[i] > Math.pow(10, 9)) {
                    throw new IllegalArgumentException("Invalid input");
                }

                var count = 1;

                for (var j = i; j < nums.length - 1; j++) {
                    if (nums[j] == nums[j + 1]) {
                        continue;
                    }

                    if (nums[j] + 1 != nums[j + 1]) {
                        i = j;
                        break;
                    }

                    count++;
                }

                result.add(count);
            }

            System.out.println(result);

            return result.stream().max(Comparator.comparingInt(o -> o)).orElseThrow();
        }
    }

}
