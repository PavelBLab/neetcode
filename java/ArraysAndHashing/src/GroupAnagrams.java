import java.util.*;

public class GroupAnagrams {

    public static void main(String[] args) {
        System.out.println(Solution1.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(Solution1.groupAnagrams(new String[]{"act","pots","tops","cat","stop","hat"}));
        System.out.println(Solution1.groupAnagrams(new String[]{"x"}));
        System.out.println(Solution1.groupAnagrams(new String[]{""}));
        System.out.println(Solution1.groupAnagrams(new String[]{"", ""}));
        System.out.println(Solution1.groupAnagrams(new String[]{"c", "c"}));
        System.out.println(Solution1.groupAnagrams(new String[]{"ant", "ant"}));
        System.out.println("-----------------------------------------------");

        System.out.println(Solution2.groupAnagrams(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        System.out.println(Solution2.groupAnagrams(new String[]{"act","pots","tops","cat","stop","hat"}));
        System.out.println(Solution2.groupAnagrams(new String[]{"x"}));
        System.out.println(Solution2.groupAnagrams(new String[]{""}));
        System.out.println(Solution2.groupAnagrams(new String[]{"", ""}));
        System.out.println(Solution2.groupAnagrams(new String[]{"c", "c"}));
        System.out.println(Solution2.groupAnagrams(new String[]{"ant", "ant"}));
        System.out.println("-----------------------------------------------");
    }


    static class Solution4 {
        public static List<List<String>> groupAnagrams(String[] strs) {
            var map = new HashMap<Map<Character, Integer>, List<String>>();
            for (String str : strs) {
                var tempMap = new HashMap<Character, Integer>();
                for (var j = 0; j < str.length(); j++) {
                    var ch = Character.toLowerCase(str.charAt(j));
                    tempMap.put(ch, tempMap.getOrDefault(ch, 0) + 1);
                }

                var list = map.getOrDefault(Map.copyOf(tempMap), new ArrayList<>());
                list.add(str);

                map.put(tempMap, list);
            }

            return new ArrayList<>(map.values());
        }
    }

    static class Solution3 {
        public static List<List<String>> groupAnagrams(String[] strs) {
            var map = new HashMap<String, List<String>>();

            for (var i = 0; i < strs.length; i++) {
                var sortedKey = strs[i].toCharArray();
                Arrays.sort(sortedKey);

                var stringKey = new String(sortedKey);

                var list = map.getOrDefault(stringKey, new ArrayList<>());
                list.add(strs[i]);

                map.put(stringKey, list);
            }

            return new ArrayList<>(map.values());
        }
    }

    static class Solution2 {
        public static List<List<String>> groupAnagrams(String[] strs) {
            var result = new ArrayList<List<String>>();
            var isVisitedMap = new HashMap<String, Boolean>();

            for (var i = 0; i < strs.length; i++) {
                var list = new ArrayList<String>();

                if (isVisitedMap.containsKey(strs[i])) {
                    continue;
                }

                for (var j = 0; j < strs.length; j++) {
                    if (isAnagram(strs[i], strs[j])) {
                        list.add(strs[j]);
                        isVisitedMap.put(strs[j], true);
                    }
                }

                result.add(list);
            }


            return  result;
        }

        private static boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            var sArr = s.toLowerCase().toCharArray();
            var tArr = t.toLowerCase().toCharArray();

            var sMap = new HashMap<Character, Integer>();
            var tMap = new HashMap<Character, Integer>();

            for (var i = 0; i < sArr.length; i++) {
                sMap.put(sArr[i], sMap.getOrDefault(sArr[i], 0) + 1);
                tMap.put(tArr[i], tMap.getOrDefault(tArr[i], 0) + 1);
            }

            return sMap.equals(tMap);
        }
    }

    static class Solution1 {
        public static List<List<String>> groupAnagrams(String[] strs) {
            var result = new ArrayList<List<String>>();
            var mapIsVisited = new HashMap<String, Boolean>();

            for (var i = 0; i < strs.length; i++) {
                var list = new ArrayList<String>();

                if (mapIsVisited.containsKey(strs[i])) {
                    continue;
                }

                list.add(strs[i]);

                for (var j = i + 1; j < strs.length; j++) {
                    if (isAnagram(strs[i], strs[j])) {
                        list.add(strs[j]);
                        mapIsVisited.put(strs[j], true);
                    }
                }
                result.add(list);
            }


            return result;
        }

        private static boolean isAnagram(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }

            var sMap = new HashMap<Character, Integer>();
            var tMap = new HashMap<Character, Integer>();

            var sArr = s.toLowerCase().toCharArray();
            var tArr = t.toLowerCase().toCharArray();

            for (var i = 0; i < sArr.length; i++) {
                sMap.put(sArr[i], sMap.getOrDefault(sArr[i], 0) + 1);
                tMap.put(tArr[i], tMap.getOrDefault(tArr[i], 0) + 1);
            }

            return sMap.equals(tMap);
        }
    }



}
