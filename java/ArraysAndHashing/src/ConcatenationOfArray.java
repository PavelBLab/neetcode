import java.util.Arrays;

public class ConcatenationOfArray {

    public static void main(String[] args) {
        System.out.println(Solution1.getConcatenation(new int[]{1,4,1,2}));
    }

    static class Solution1 {
        public static int[] getConcatenation(int[] nums) {
            var newArrayLength = nums.length * 2;
            var newArr = new int[newArrayLength];

            for (var i = 0; i < nums.length; i++) {
                var n = nums[i];
                newArr[i] = n;
                newArr[i + nums.length] = n;
            }

            System.out.println(Arrays.toString(newArr));

            return newArr;
        }
    }
}
