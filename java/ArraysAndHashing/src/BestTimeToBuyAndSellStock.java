import java.util.Arrays;

public class BestTimeToBuyAndSellStock {

    public static void main(String[] args) {
        System.out.println(Solution1.maxProfit(new int[]{10,1,5,6,7,1}));
        System.out.println(Solution1.maxProfit(new int[]{10,8,7,5,2}));
        System.out.println(Solution1.maxProfit(new int[]{7,6,4,3,1}));
        System.out.println("-----------------------------------------------");

//        System.out.println(Solution2.maxProfit(new int[]{10,1,5,6,7,1}));
//        System.out.println(Solution2.maxProfit(new int[]{10,8,7,5,2}));
//        System.out.println(Solution2.maxProfit(new int[]{7,6,4,3,1}));
//        System.out.println("-----------------------------------------------");
    }

    static class Solution1 {
        public static int maxProfit(int[] prices) {
            var maxProfit = 0;
            var lowestPrice = prices[0];

            for (int i = 0; i < prices.length - 1; i++) {
                System.out.println("rightPointer: " + i);
                System.out.println("leftPointer: " + (i + 1) );

                if (lowestPrice > prices[i]) {
                    lowestPrice = prices[i];
                }

                var profit = prices[i + 1] - lowestPrice;

                if (maxProfit < profit) {
                    maxProfit = profit;
                    System.out.println("  profit: " + maxProfit);
                }


                System.out.println(" -------------------- ");
            }

            System.out.println("maxProfit: " + maxProfit);
            return maxProfit;
        }
    }
}
