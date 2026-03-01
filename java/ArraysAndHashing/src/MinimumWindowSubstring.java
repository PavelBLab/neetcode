import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
    public static void main(String[] args) {
//        System.out.println(Solution1.minWindow("OUZODYXAZV", "XYZ"));
//        System.out.println(Solution1.minWindow("ab", "b"));
        System.out.println(Solution1.minWindow("aaaaaaaaaaaabbbbbcdd", "abcdd"));
    }

    static class Solution1 {
        public static String minWindow(String s, String t) {
            if (t.length() > s.length()) {
                return "";
            }

            var tArr = t.toCharArray();
            Arrays.sort(tArr);
            var sortedT = new String(tArr);

            var map = new HashMap<String, Integer>();
            var leftPointer = 0;
            var sb = new StringBuilder();
            var key = new StringBuilder();
            var keyMap = new HashMap<Character, Integer>();

            for (var c : sortedT.toCharArray()) {
                keyMap.put(c, keyMap.getOrDefault(c, 0) + 1);
            }

            System.out.println(keyMap);

            for (var rightPointer = 0; rightPointer < s.length(); rightPointer++) { // OUZODYXAZV -> XYZ
                var ch = s.charAt(rightPointer);
                sb.append(ch);

                if (sortedT.indexOf(ch) > -1) {
                        key.append(ch);
                }

                if (key.length() == t.length()) {
                    var sortedKey = key.toString().toCharArray();
                    Arrays.sort(sortedKey);

                    if (new String(sortedKey).equals(sortedT)) {
                        map.put(sb.toString(), sb.length());
                    }

                    leftPointer++;
                    rightPointer = leftPointer - 1;
                    key.setLength(0);
                    sb.setLength(0);
                }

            }

            System.out.println(map);

            var list = map.entrySet().stream()
                    .sorted(Comparator.comparingInt(Map.Entry::getValue))
                    .map(Map.Entry::getKey)
                    .toList();

            return list.isEmpty() ? "" : list.getFirst();
        }
    }
}
