import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class LongestCommonPrefix {

    public static void main(String[] args) {
        System.out.println(Solution1.longestCommonPrefix(new String[]{"bat","bag","bank","band"}));
        System.out.println(Solution1.longestCommonPrefix(new String[]{"dance","dag","danger","damage"}));
        System.out.println(Solution1.longestCommonPrefix(new String[]{"neet","feet"}));
        System.out.println(Solution1.longestCommonPrefix(new String[]{"a", "a", "a"}));
        System.out.println(Solution1.longestCommonPrefix(new String[]{"abc", "abcde", "abcdef"}));
        System.out.println(Solution1.longestCommonPrefix(new String[]{"onlyone"}));
        System.out.println(Solution1.longestCommonPrefix(new String[]{"", "abc", "abcde"}));
        System.out.println(Solution1.longestCommonPrefix(new String[]{"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaa","aaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa","a"}));
    }

    static class Solution1 {
        public static String longestCommonPrefix(String[] strs) {
            var map = new HashMap<String, Integer>();

            if (strs.length == 1) {
                return strs[0];
            }

            for (var s : strs) {
                if (s.length() == 1) {
                    map.put(s, map.getOrDefault(s, 0) + 1);
                    continue;
                }

                for (var j = 0; j < s.length(); j++) {
                    var prefix = s.substring(0, j + 1);
                    map.put(prefix, map.getOrDefault(prefix, 0) + 1);
                }
            }

            System.out.println(map);

            Comparator<Map.Entry<String, Integer>> byValue = Comparator.comparingInt(Map.Entry::getValue);
            Comparator<Map.Entry<String, Integer>> byKeyLength = Comparator.comparingInt(e -> e.getKey().length());

            System.out.println(strs.length);
            var longestPref = map.entrySet().stream()
                    .filter(e -> e.getValue() == strs.length)
                    .sorted(
                            byValue.reversed().thenComparing(byKeyLength.reversed())
                    )
                    .map(Map.Entry::getKey)
                    .toList();
            System.out.println(longestPref);
            return longestPref.isEmpty() ? "" : longestPref.getFirst();
        }
    }

}
