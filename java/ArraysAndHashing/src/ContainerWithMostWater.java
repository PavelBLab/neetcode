public class ContainerWithMostWater {

    public static void main(String[] args) {
        System.out.println(Solution1.maxArea(new int[]{1,7,2,5,4,7,3,6}));
        System.out.println(Solution1.maxArea(new int[]{2, 2, 2}));
        System.out.println(Solution1.maxArea(new int[]{1,7,2,5,12,3,500,500,7,8,4,7,3,6}));
        System.out.println("-----------------------------------------------");
    }

    static class Solution1 {
        public static int maxArea(int[] heights) {
            var maxAmountOfWater = 0;
            var rightPointer = 0;
            var leftPointer = heights.length - 1;

            while (leftPointer > rightPointer) {
                var rightBarLength = heights[rightPointer];
                var leftBarLength = heights[leftPointer];

                var minBarLength = Math.min(rightBarLength, leftBarLength);
                System.out.println("rightBarLength: " + rightBarLength+ "; leftBarLength: " + leftBarLength);

                var distance = leftPointer - rightPointer;
                System.out.println("distance: " + distance);

                var container = minBarLength * distance;
                System.out.println("container: " + container);

                if (container > maxAmountOfWater) {
                    maxAmountOfWater = container;
                }

                if (rightBarLength < leftBarLength) {
                    rightPointer++;
                } else {
                    leftPointer--;
                }

                System.out.println(" ---------------------------- ");
            }
            System.out.println("result: " + maxAmountOfWater);
            return maxAmountOfWater;

        }
    }
}
