import java.util.ArrayList;
import java.util.Arrays;

public class RemoveElement {

    public static void main(String[] args) {
        System.out.println(Solution1.removeElement(new int[]{1,1,2,3,4}, 1));
    }

    static class Solution1 {
        public static int[] removeElement(int[] nums, int val) {
            var list = new ArrayList<Integer>();

            for (int num : nums) {
                if (num != val) {
                    list.add(num);
                }
            }

            System.out.println(list);

            return list.stream().mapToInt(i -> i.intValue()).toArray();
        }
    }
}
