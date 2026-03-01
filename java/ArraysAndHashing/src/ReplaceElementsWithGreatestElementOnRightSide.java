import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

public class ReplaceElementsWithGreatestElementOnRightSide {

    public static void main(String[] args) {
        System.out.println(Solution1.replaceElements(new int[]{2,4,5,3,1,2}));
    }

    static class Solution1 {
        public static int[] replaceElements(int[] arr) {
            var updatedArr = new int[arr.length];
            var highestInt = 0;

            for (var i = 0; i < arr.length; i++) {
                if (i == arr.length -1) {
                    updatedArr[i] = -1;
                    break;
                }

                for (var j = i + 1; j < arr.length; j++) {
                    if (highestInt < arr[j]) {
                        highestInt = arr[j];
                    }
                }

                updatedArr[i] = highestInt;
                highestInt = 0;
            }

            System.out.println(Arrays.toString(updatedArr));

            return updatedArr;
        }
    }

}
