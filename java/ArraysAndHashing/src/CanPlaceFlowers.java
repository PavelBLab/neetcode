public class CanPlaceFlowers {

    public static void main(String[] args) {
        System.out.println(Solution1.canPlaceFlowers(new int[]{1,0,0,0,1}, 1));
        System.out.println(Solution1.canPlaceFlowers(new int[]{1,0,0,0,1}, 2));
    }

    static class Solution1 {
        public static boolean canPlaceFlowers(int[] flowerbed, int n) {
            var count = 0;

            for (var i = 1; i < flowerbed.length; i++) {
                if (flowerbed[i - 1] == 1 && flowerbed[i] == 0 && flowerbed[i + 1] == 0) {
                    count++;
                }
            }

            System.out.println(count == n);

            return count == n;
        }
    }
}
