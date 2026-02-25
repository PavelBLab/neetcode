import java.util.Arrays;
import java.util.HashSet;

public class TwoIntegerSum2 {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution1.twoSum(new int[]{1, 2, 3, 4}, 3)));
        System.out.println(Arrays.toString(Solution1.twoSum(new int[]{2, 3, 4}, 6)));
        System.out.println("-----------------------------------------------");
    }

    static class Solution1 {
        public static int[] twoSum(int[] numbers, int target) {
            if (numbers.length < 2 || numbers.length > 1000) {
                throw new IllegalArgumentException("Invalid input");
            }

            if (target < -1000 || target > 1000) {
                throw new IllegalArgumentException("Invalid input");
            }

            var result = new int[2];

            for (var i = 0; i < numbers.length; i++) {
                for (var j = i + 1; j < numbers.length; j++) {
                    if (numbers[i] + numbers[j] == target) {
                        result[0] = i + 1;
                        result[1] = j + 1;
                        return result;
                    }
                }
            }

            return result;
        }
    }
}
