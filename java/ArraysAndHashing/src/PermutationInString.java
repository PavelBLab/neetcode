import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

public class PermutationInString {

    public static void main(String[] args) {
//        System.out.println(Solution1.checkInclusion("abc", "lecabee"));
//        System.out.println(Solution1.checkInclusion("abc", "lecaabee"));
//        System.out.println("-----------------------------------------------");

        System.out.println(Solution2.checkInclusion("abc", "lecabee"));
        System.out.println(Solution2.checkInclusion("abc", "lecaabee"));
    }

    static class Solution2 {
        public static boolean checkInclusion(String s1, String s2) {
            var set = new HashSet<String>();
            var chArr = s1.toCharArray();
            Arrays.sort(chArr);

            var sortedString = new String(chArr);

            for (var i = 0; i < s2.length(); i++) {
                if (i + s1.length() > s2.length()) {
                    break;
                }

                var substr = s2.substring(i, i + s1.length()); // 0,3; 1,4
                var subChArr = substr.toCharArray();

                Arrays.sort(subChArr);
                set.add(new String(subChArr));
            }

            System.out.println(set);

            return set.contains(sortedString);
        }
    }


    static class Solution1 {
        public static boolean checkInclusion(String s1, String s2) {
            var map = new HashMap<String, Integer>();

            for (var i = 0; i < s2.length(); i++) {
                var endIndex = i + s1.length();

                if (endIndex > s2.length()) {
                    break;
                }

                var key = s2.substring(i, endIndex).toCharArray();
                Arrays.sort(key);
                map.put(new String(key), map.getOrDefault(new String(key), 0));
            }

            System.out.println(map);

            var s1Arr = s1.toCharArray();
            Arrays.sort(s1Arr);

            System.out.println(Arrays.toString(s1.toCharArray()));

            return map.containsKey(new String(s1Arr));

            //  ┌────────────────┬──────────┬───────────┬─────────────────┐
            //  │      Type      │    ==    │ .equals() │ content compare │
            //  ├────────────────┼──────────┼───────────┼─────────────────┤
            //  │ String literal │ ✓ (pool) │ ✓         │ .equals()       │
            //  ├────────────────┼──────────┼───────────┼─────────────────┤
            //  │ String new     │ ✗        │ ✓         │ .equals()       │
            //  ├────────────────┼──────────┼───────────┼─────────────────┤
            //  │ char[]         │ ✗        │ ✗         │ Arrays.equals() │
            //  ├────────────────┼──────────┼───────────┼─────────────────┤
            //  │ ArrayList      │ ✗        │ ✓         │ .equals()       │
            //  └────────────────┴──────────┴───────────┴─────────────────┘
        }
    }
}
