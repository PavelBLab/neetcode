package onlineassessment;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class EntryTask1 {

    public static void main(String[] args) {
//        System.out.println(Solution1.conditionForMaximumOccurringCharacter("1223334444"));
//        System.out.println(Solution1.conditionForMaximumOccurringCharacter("abcABCabc"));
//        System.out.println("-----------------------------------------------");

//        System.out.println(Solution2.conditionForMaximumOccurringCharacter("1223334444"));
//        System.out.println(Solution2.conditionForMaximumOccurringCharacter("abcABCacb"));
//        System.out.println("-----------------------------------------------");

//        System.out.println(Solution3.conditionForMaximumOccurringCharacter("1223334444"));
//        System.out.println(Solution3.conditionForMaximumOccurringCharacter("abcABCabc"));
//        System.out.println("-----------------------------------------------");

//        System.out.println(Solution4.conditionForMaximumOccurringCharacter("1223334444"));
//        System.out.println(Solution4.conditionForMaximumOccurringCharacter("abcABCabc"));
//        System.out.println("-----------------------------------------------");

        System.out.println(Solution5.conditionForMaximumOccurringCharacter("1223334444"));
        System.out.println(Solution5.conditionForMaximumOccurringCharacter("abcABCabc"));
        System.out.println("-----------------------------------------------");

    }

    static class Solution5 {
        public static char conditionForMaximumOccurringCharacter(String s) {
            var sMap = new HashMap<Character, Integer>();
            var highestFirstOccurance = 0;
            var result = s.charAt(0);

            for (var i = 0; i < s.length(); i++) {
                var ch = s.charAt(i);
                sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);

                if (highestFirstOccurance < sMap.get(ch)) {
                    highestFirstOccurance = sMap.get(ch);
                    result = ch;
                }
            }

            return result;
        }
    }

    static class Solution4 {
        public static char conditionForMaximumOccurringCharacter(String s) {
            var sMap = new HashMap<Character, Integer>();

            for (var i = 0; i < s.length(); i++) {
                sMap.put(s.charAt(i), sMap.getOrDefault(s.charAt(i), 0) + 1);
            }

            System.out.println(sMap);
            return sMap.entrySet().stream()
                    .sorted(
                            Comparator.comparingInt((Map.Entry<Character, Integer> e) -> e.getValue())
                                    .reversed()
                                    .thenComparing(Map.Entry::getKey)
                    )
                    .map(Map.Entry::getKey)
                    .toList().getFirst();
        }
    }


    static class Solution3 {
        public static char conditionForMaximumOccurringCharacter(String s) {
            if (s.isEmpty() || s.length() > Math.pow(10, 5)) {
                throw new IllegalArgumentException("Invalid input");
            }

            var map = new HashMap<Character, Integer>();
            var maxCount = 0;
            var result = s.charAt(0);

            for (var i = 0; i < s.length(); i++) {
                map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
                var characterCount = map.get(s.charAt(i));

                if (characterCount > maxCount) {
                    maxCount = characterCount;
                    result = s.charAt(i);
                }
            }

            return result;
        }
    }

    static class Solution2 {
        public static char conditionForMaximumOccurringCharacter(String s) {
            var counts = new HashMap<Character, Integer>();
            var maxCount = 0;
            var result = s.charAt(0);

            for (var i = 0; i < s.length(); i++) {
                counts.put(s.charAt(i), counts.getOrDefault(s.charAt(i), 0) + 1);
                var characterCount = counts.get(s.charAt(i));

                if (characterCount > maxCount) {
                    maxCount = characterCount;
                    result = s.charAt(i);
                }
            }

            return result;
        }
    }

    static class Solution1 {
        private static class CharacterCount {
            char character;
            int count;
            int index;

            public CharacterCount(char character, int count, int index) {
                this.character = character;
                this.count = count;
                this.index = index;
            }

            @Override
            public String toString() {
                return "CharacterCount{" +
                        "character=" + character +
                        ", count=" + count +
                        ", index=" + index +
                        '}';
            }
        }

        public static char conditionForMaximumOccurringCharacter(String s) {
            var sArr = s.toCharArray();
            var map = new HashMap<Character, CharacterCount>();

            for (var i = 0; i < sArr.length; i++) {
                map.put(sArr[i], map.getOrDefault(sArr[i], new CharacterCount(sArr[i], 0, i)));
                map.get(sArr[i]).count++;
            }

            System.out.println(map);
            var x = map.entrySet().stream()
                    .sorted(Comparator.comparingInt((Map.Entry<Character, CharacterCount> e) -> e.getValue().count).reversed()
                            .thenComparingInt(e -> e.getValue().index))
                    .map(Map.Entry::getKey)
                    .toList();
            System.out.println(x);
            return x.getFirst();
        }
    }
}
