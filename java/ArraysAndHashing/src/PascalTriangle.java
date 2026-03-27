import java.util.ArrayList;
import java.util.List;

public class PascalTriangle {

    public static void main(String[] args) {
        System.out.println(Solution1.generate(5));
    }

    static class Solution1 {
        public static List<List<Integer>> generate(int numRows) {
            var result = new ArrayList<List<Integer>>();

            for (var i = 0; i < numRows; i++) {
                var list = new ArrayList<Integer>();
                List<Integer> previousResult = i > 0 ? result.get(i - 1) : new ArrayList<>();

                for (var j = 0; j < i + 1; j++) {
                    if (j == 0 || j == i) {
                        list.add(1);
                    } else {
                        list.add(previousResult.get(j - 1) + previousResult.get(j));
                    }
                }

                result.add(list);
            }
            return result;
        }
    }
}
