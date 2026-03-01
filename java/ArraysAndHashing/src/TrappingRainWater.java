public class TrappingRainWater {
    public static void main(String[] args) {
        System.out.println(new Solution1().trap(new int[]{0,2,0,3,1,0,1,3,2,1})); // Expected: 9
        System.out.println("-----------------------------------------------");
    }

    static class Solution1 {
        public int trap(int[] height) {
            var left = 0;
            var right = height.length - 1;
            var maxLeft = 0;
            var maxRight = 0;
            var totalWater = 0;

            while (left < right) {
                if (height[left] < height[right]) {
                    if (height[left] > maxLeft) {
                        maxLeft = height[left];
                    } else {
                        totalWater += maxLeft - height[left];
                    }
                    left++;
                } else {
                    if (height[right] > maxRight) {
                        maxRight = height[right];
                    } else {
                        totalWater += maxRight - height[right];
                    }
                    right--;
                }
            }

            return totalWater;
        }
    }
}
