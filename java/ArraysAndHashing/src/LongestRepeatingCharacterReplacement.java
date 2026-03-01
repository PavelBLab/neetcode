import java.util.HashMap;

public class LongestRepeatingCharacterReplacement {

    public static void main(String[] args) {
//        System.out.println(Solution2.characterReplacement("XYYX", 2)); // 4
        System.out.println(Solution2.characterReplacement("AAABABB", 1)); // 5
//        System.out.println(Solution3.characterReplacement("ABAA", 0));
//        System.out.println(Solution3.characterReplacement("ABBB", 2)); // 4
//        System.out.println(Solution3.characterReplacement("BAAAB", 2)); // 5
//        System.out.println(Solution3.characterReplacement("AAAAABBBBCBB", 3)); // 9


    }

    static class Solution3 {
        public static int characterReplacement(String s, int k) {
            var count = new HashMap<Character, Integer>();
            var leftPointer = 0;
            var maxSameLatterFrequency = 0;
            var longestSubstring = 0;

            for (var rightPointer = 0; rightPointer < s.length(); rightPointer++) {
                count.put(s.charAt(rightPointer), count.getOrDefault(s.charAt(rightPointer), 0) + 1);
                maxSameLatterFrequency = Math.max(maxSameLatterFrequency, count.get(s.charAt(rightPointer)));

                var windowSize = rightPointer - leftPointer + 1;

                if (windowSize - maxSameLatterFrequency > k) {
                    count.put(s.charAt(leftPointer), count.getOrDefault(s.charAt(leftPointer), 0) -1);
                    leftPointer++;
                }

                longestSubstring = Math.max(longestSubstring, rightPointer - leftPointer + 1);
            }

            return longestSubstring;
        }
    }

    static class Solution2 {
        public static int characterReplacement(String s, int k) {
            var count = new HashMap<Character, Integer>();
            var leftPointer = 0;
            var maxFreq = 0;
            var result = 0;

            for (var rightPointer = 0; rightPointer < s.length(); rightPointer++) {
                count.put(s.charAt(rightPointer), count.getOrDefault(s.charAt(rightPointer), 0) + 1); // same count.merge(s.charAt(rightPointer), 1, Integer::sum);
                maxFreq = Math.max(maxFreq, count.get(s.charAt(rightPointer)));

                var windowSize = rightPointer - leftPointer + 1;
                if (windowSize - maxFreq > k) { // 5(AAABA) - 4(AAAA) > 1; AAABABB
                    count.put(s.charAt(leftPointer), count.getOrDefault(s.charAt(leftPointer), 0) - 1); // count.merge(s.charAt(leftPointer), -1, Integer::sum);
                    leftPointer++;
                }

                result = Math.max(result, rightPointer - leftPointer + 1);
            }

            return result;
        }
    }

    static class Solution1 {
        public static int characterReplacement(String s, int k) {
            var sArr = s.toCharArray();
            var ch = sArr[0];
            var longestString = 0;
            var counter = 0;
            var replaceMentCountRestart = 0;

            for (var i = 0; i < sArr.length; i++) {
                if (ch != sArr[i]) {
                    replaceMentCountRestart++;
                }

                if (k < replaceMentCountRestart) {
                    ch = sArr[i];
                    replaceMentCountRestart = 0;
                    counter = 1;
                    continue;
                }

                counter++;

                if (longestString < counter) {
                    longestString = counter;
                }
            }

            return longestString;
        }
    }

}
