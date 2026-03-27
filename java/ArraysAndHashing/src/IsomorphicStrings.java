import java.util.*;
import java.util.stream.Collectors;

public class IsomorphicStrings {

    public static void main(String[] args) {
        System.out.println(Solution1.isIsomorphic("egg", "add"));
        System.out.println(Solution1.isIsomorphic("foo", "bar"));
        System.out.println(Solution1.isIsomorphic("bbbaaaba", "aaabbbba"));
        System.out.println("-----------------------------------------------");

//        System.out.println(Solution2.isIsomorphic("egg", "add"));
//        System.out.println(Solution2.isIsomorphic("foo", "bar"));

    }

    static class Solution2 {
        public static boolean isIsomorphic(final String s, final String t) {
            var sb = new StringBuilder(s);
            System.out.println(sb);

            for (var i = 0; i < s.length(); i++) {
                if (s.charAt(i) == t.charAt(i)) {
                    return false;
                }

                sb.setCharAt(i, t.charAt(i));
            }

            return t.contentEquals(sb);
        }
    }

    static class Solution1 {
        public static boolean isIsomorphic(final String s, final String t) {
            var sMap = new HashMap<Character, Integer>();
            var tMap = new HashMap<Character, Integer>();

            for (var i = 0; i < s.length(); i++) {
                if (s.charAt(i) == t.charAt(i)) {
                    return false;
                }

                sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
                tMap.put(t.charAt(i), tMap.getOrDefault(t.charAt(i), 0) + 1);
            }

            System.out.println(sMap + " " + tMap);

            var sList = sMap.entrySet().stream().map(Map.Entry::getValue).toList();
            var tList = tMap.entrySet().stream().map(Map.Entry::getValue).toList();


            System.out.println(sList + " " + tList);

            return sList.equals(tList);
        }
    }

}
