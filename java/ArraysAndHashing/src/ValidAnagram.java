import java.util.Arrays;
import java.util.HashMap;

public class ValidAnagram {

    public static void main(String[] args) {
        System.out.println(Solution1.isAnagram("anagram", "nagaram"));
        System.out.println(Solution1.isAnagram("rat", "car"));
        System.out.println("-----------------------------------------------");

        System.out.println(Solution2.isAnagram("anagram", "nagaram"));
        System.out.println(Solution2.isAnagram("rat", "car"));
        System.out.println("-----------------------------------------------");

        System.out.println(Solution3.isAnagram("anagram", "nagaram"));
        System.out.println(Solution3.isAnagram("rat", "car"));
        System.out.println("-----------------------------------------------");

        System.out.println(Solution4.isAnagram("anagram", "nagaram"));
        System.out.println(Solution4.isAnagram("rat", "car"));
        System.out.println("-----------------------------------------------");

    }

    static class Solution5 {
        public static boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            var map = new HashMap<Character, Integer>();

            for (int i = 0; i < s.length(); i++) {
                map.merge(s.charAt(i), 1, (a, b) -> a + b);
                map.merge(t.charAt(i), -1, (a, b) -> a + b);
            }

            for (int v : map.values()) if (v != 0) return false;

            return true;
        }
    }


    static class Solution4 {
        public static boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            var sMap = new HashMap<Character, Integer>();
            var tMap = new HashMap<Character, Integer>();

            for(var i = 0; i < s.length(); i++) {
                var sCh = Character.toLowerCase(s.charAt(i));
                var tCh = Character.toLowerCase(t.charAt(i));

                sMap.put(sCh, sMap.getOrDefault(sCh, 0) + 1);
                tMap.put(tCh, tMap.getOrDefault(tCh, 0) + 1);
            }

            return sMap.equals(tMap);
        }
    }


    static class Solution3 {
        public static boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            var sMap = new HashMap<Character, Integer>();
            var tMap = new HashMap<Character, Integer>();

            var sArr = s.toLowerCase().toCharArray();
            var tArr = t.toLowerCase().toCharArray();

            System.out.println(Arrays.toString(sArr) + " " + Arrays.toString(tArr));

            for (var i = 0; i < sArr.length; i++) {
                sMap.put(sArr[i], sMap.getOrDefault(sArr[i],0) + 1);
                tMap.put(tArr[i], tMap.getOrDefault(tArr[i],0) + 1);
            }

            return sMap.equals(tMap);
        }
    }

    static class Solution2 {
        public static boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            var sMap = new HashMap<Character, Integer>();
            var tMap = new HashMap<Character, Integer>();

            System.out.println(s.chars() + " " + t.chars());

            s.chars().forEach(c -> {
                char ch = (char) Character.toLowerCase(c);
                sMap.put(ch, sMap.getOrDefault(ch,0) + 1);
            });

            t.chars().forEach(c -> {
                char ch = (char) Character.toLowerCase(c);
                tMap.put(ch, tMap.getOrDefault(ch,0) + 1);
            });


            return sMap.equals(tMap);
        }
    }

    static class Solution1 {
        public static boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            char[] sArr = s.toLowerCase().toCharArray();
            Arrays.sort(sArr);

            char[] tArr = t.toLowerCase().toCharArray();
            Arrays.sort(tArr);

            return Arrays.equals(sArr, tArr);
        }
    }

}


