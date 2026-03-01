import java.util.Arrays;
import java.util.HashSet;

class ContainsDuplicate {

    public static void main(String[] args) {
//        System.out.println(Solution1.hasDuplicate(new int[]{1, 2, 3, 3}));
//        System.out.println(Solution1.hasDuplicate(new int[]{1, 2, 3, 4}));
//        System.out.println("-----------------------------------------------");
//
//        System.out.println(Solution2.hasDuplicate(new int[]{1, 2, 3, 3}));
//        System.out.println(Solution2.hasDuplicate(new int[]{1, 2, 3, 4}));
//        System.out.println("-----------------------------------------------");
//
//        System.out.println(Solution3.hasDuplicate(new int[]{1, 2, 3, 3}));
//        System.out.println(Solution3.hasDuplicate(new int[]{1, 2, 3, 4}));
//        System.out.println("-----------------------------------------------");
//
//        System.out.println(Solution4.hasDuplicate(new int[]{1, 2, 3, 3}));
//        System.out.println(Solution4.hasDuplicate(new int[]{1, 2, 3, 4}));
//        System.out.println("-----------------------------------------------");

        System.out.println(Solution5.hasDuplicate(new int[]{1, 2, 3, 3}));
        System.out.println(Solution5.hasDuplicate(new int[]{1, 2, 3, 4}));
        System.out.println("-----------------------------------------------");
    }

    static class Solution5 {
        public static boolean hasDuplicate(int[] nums) {
            var set = new HashSet<Integer>();

            for (var n : nums) {
                if (!set.add(n)) {
                    return true;
                }
            }

            return false;
        }
    }

    static class Solution4 {
        public static boolean hasDuplicate(int[] nums) {
            var set = new HashSet<Integer>();

            for (var num : nums) {
                if (!set.add(num)) {
                    return true;
                }
            }

            return false;
        }
    }


    static class Solution3 {
        public static boolean hasDuplicate(int[] nums) {

            var set = new HashSet<Integer>();

            for (var num : nums) {
                if (set.contains(num)) {
                    return true;
                }
                set.add(num);
            }

            return false;
        }
    }

    static class Solution2 {
        public static boolean hasDuplicate(int[] nums) {

            var set = new HashSet<Integer>();

            for (var num : nums) {
                set.add(num);
            }

            return set.size() < nums.length;
        }
    }

    static class Solution1 {
        public static boolean hasDuplicate(int[] nums) {

            for (var i = 0; i < nums.length; i++) {
                for (var j = i + 1; j < nums.length; j++) {
                    if (nums[i] == nums[j]) {
                        return true;
                    }
                }
            }
            return false;
        }
    }
}
