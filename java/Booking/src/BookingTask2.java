import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class BookingTask2 {

    public static void main(String[] args) {
//        System.out.println(Solution1.beautifulSubarray(List.of(1, 2, 3, 4, 5), 2)); // Expected: 4
//        System.out.println("-----------------------------------------------");
        System.out.println(Solution2.beautifulSubarray(List.of(1, 2, 3, 4, 5), 2)); // Expected: 4 [1,2,3], [1,2,3,4], [2,3,4,5], [3,4,5]
        System.out.println("-----------------------------------------------");
//        System.out.println(Solution3.beautifulSubarray(List.of(1, 2, 3, 4, 5), 2)); // Expected: 4
    }

    static class Solution2 {
        public static long beautifulSubarray(List<Integer> arr, int odds) {
            var oddCountFrequency = new HashMap<Integer, Long>();
            oddCountFrequency.put(0, 1L); // base case: We have seen oddsSoFar = 0 exactly 1 time
            var oddsSoFar = 0;
            var result = 0L;

            for (var num : arr) { // 1,2,3,4,5; odds 2
                if (num % 2 != 0) {
                    oddsSoFar++;
                }
                // How many previous prefixes had (oddsSoFar - odds) odds?
                // Each one forms a valid subarray ending here.
                System.out.println("current number: " + num);
                System.out.println("odds seen so far: " + oddsSoFar + " | looking for past position where oddsSoFar was: " + (oddsSoFar - odds));
                result += oddCountFrequency.getOrDefault(oddsSoFar - odds, 0L);
                System.out.println("subarrays found so far: " + result);
                oddCountFrequency.merge(oddsSoFar, 1L, Long::sum);
                System.out.println("oddCountFrequency (oddCount -> timesSeeen): " + oddCountFrequency);
                System.out.println("-------------------------------------------");
            }

            return result;
        }
    }

//    static class Solution3 {
//        public static long beautifulSubarray(List<Integer> arr, int odds) {
//            return atMost(arr, odds) - atMost(arr, odds - 1);
//        }
//
//        private static long atMost(List<Integer> arr, int k) {
//            if (k < 0) return 0;
//            var left = 0;
//            var oddCount = 0;
//            var result = 0L;
//
//            for (var right = 0; right < arr.size(); right++) {
//                if (arr.get(right) % 2 != 0) {
//                    oddCount++;
//                }
//                while (oddCount > k) {
//                    if (arr.get(left) % 2 != 0) {
//                        oddCount--;
//                    }
//                    left++;
//                }
//                // all subarrays ending at 'right' starting from left..right are valid
//                result += right - left + 1;
//            }
//
//            return result;
//        }
//    }

    static class Solution1 {
        public static long beautifulSubarray(List<Integer> arr, int odds) {
            if (odds < 0 || odds > Math.pow(10, 5)) {
                throw new IllegalArgumentException("Invalid input");
            }

            var result = 0L;

            for (var i = 0; i < arr.size(); i++) {
                var tempArr = new ArrayList<Integer>();
                var oddsCount = 0;

                for (var j = i; j < arr.size(); j++) {
                    if (arr.get(j) % 2 != 0) {
                        oddsCount++;
                        System.out.println("oddsCount: " + oddsCount);
                        System.out.println("Found odd number: " + arr.get(j));
                    }

                    System.out.println("Adding " + arr.get(j) + " to tempArr");
                    tempArr.add(arr.get(j));

                    if (oddsCount == odds) {
                        System.out.println("Found beautiful subarray: " + tempArr);
                        result++;
                    }

                    if (oddsCount > odds) {
                        break;
                    }
                }

                System.out.println("-----------------------------");
            }

            return result;
        }
    }
}
