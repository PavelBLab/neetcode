public class MaxConsecutiveOnes {

    public static void main(String[] args) {
        System.out.println(Solution1.findMaxConsecutiveOnes(new int[]{1,1,0,1,1,1}));
        System.out.println(Solution1.findMaxConsecutiveOnes(new int[]{1,0,1,1,0,1}));
    }

    static class Solution1 {
        public static int findMaxConsecutiveOnes(int[] nums) {
            var maxConsecutiveOnes = 0;
            var count = 0;

            for (int num : nums) {
                if (num != 1) {
                    count = 0;
                    continue;
                }

                count++;

                if (maxConsecutiveOnes < count) {
                    maxConsecutiveOnes = count;
                }
            }

            return maxConsecutiveOnes;
        }
    }
}
