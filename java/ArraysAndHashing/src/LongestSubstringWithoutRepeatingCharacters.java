import java.util.ArrayList;
import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

    public static void main(String[] args) {
//        System.out.println(Solution1.lengthOfLongestSubstring("abcabcbb")); // Expected: 3
//        System.out.println("-----------------------------------------------");
//
//        System.out.println(Solution2.lengthOfLongestSubstring("abcabcbb"));
//        System.out.println(Solution2.lengthOfLongestSubstring("pwwkew"));
//        System.out.println(Solution2.lengthOfLongestSubstring(" "));
//        System.out.println("-----------------------------------------------");

        System.out.println(Solution3.lengthOfLongestSubstring("zxyzxyz"));
        System.out.println(Solution3.lengthOfLongestSubstring("xxxx"));
        System.out.println(Solution3.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(Solution3.lengthOfLongestSubstring("dvdf"));
        System.out.println(Solution3.lengthOfLongestSubstring("pwwkew"));
        System.out.println(Solution3.lengthOfLongestSubstring(" "));
        System.out.println("-----------------------------------------------");

        System.out.println(Solution4.lengthOfLongestSubstring("zxyzxyz"));
        System.out.println(Solution4.lengthOfLongestSubstring("xxxx"));
        System.out.println(Solution4.lengthOfLongestSubstring("abcabcbb"));
        System.out.println(Solution4.lengthOfLongestSubstring("dvdf"));
        System.out.println(Solution4.lengthOfLongestSubstring("pwwkew"));
        System.out.println(Solution4.lengthOfLongestSubstring(" "));
        System.out.println("-----------------------------------------------");
    }


    static class Solution4 {
        public static int lengthOfLongestSubstring(String s) {
            var maxSubStringLength = 0;
            var counter = 0;
            var sArr = s.toCharArray();
            var sb = new StringBuilder();

            for (var i = 0; i < sArr.length; i++) {
                var l = String.valueOf(sArr[i]);
                if (sb.indexOf(l) < 0) {
                    sb.append(sArr[i]);
                    counter++;
                } else {
                    var endIndex = sb.indexOf(l); // abwwrtjg
                    sb.delete(0, endIndex + 1);
                    sb.append(sArr[i]);
                    counter = sb.length();
                }

                if (maxSubStringLength < counter) {
                    maxSubStringLength = counter;
                }
            }

            return maxSubStringLength;
        }
    }


    static class Solution3 {
        public static int lengthOfLongestSubstring(String s) {
            var maxSubStringLength = 0;
            var counter = 0;
            var sArr = s.toCharArray();
            var stringBuilder = new StringBuilder();

            for (var i = 0; i < sArr.length; i++) {
                if (stringBuilder.indexOf(String.valueOf(sArr[i])) < 0) {
                    stringBuilder.append(sArr[i]);
                    counter++;
                } else {
                    var endIndex = stringBuilder.indexOf(String.valueOf(sArr[i]));
                    stringBuilder.delete(0, endIndex + 1);
                    stringBuilder.append(sArr[i]);
                    counter = stringBuilder.length();
                }

                if (maxSubStringLength < counter) {
                    maxSubStringLength = counter;
                }
            }

            return maxSubStringLength;
        }
    }






















    static class Solution2 {
        public static int lengthOfLongestSubstring(String s) {
            var maxSubStringLength = 0;
            var counter = 0;
            var sArr = s.toCharArray();
            var stringBuilder = new StringBuilder();


            for (var i = 0; i < sArr.length; i++) {
                if (stringBuilder.indexOf(String.valueOf(sArr[i])) < 0) {
                    stringBuilder.append(sArr[i]);
                    System.out.println(stringBuilder);
                    counter++;
                } else {
                    stringBuilder.setLength(0);
                    stringBuilder.append(sArr[i]);
                    counter = 1;
                }

                if (maxSubStringLength < counter) {
                    maxSubStringLength = counter;
                }
            }

            return maxSubStringLength;
        }
    }


    static class Solution1 {
        public static int lengthOfLongestSubstring(String s) {
            var set = new HashSet<Character>();
            var maxLength = 0;
            var left = 0;

            for (var right = 0; right < s.length(); right++) {
                while (set.contains(s.charAt(right))) {
                    set.remove(s.charAt(left));
                    left++;
                }
                set.add(s.charAt(right));
                maxLength = Math.max(maxLength, right - left + 1);
            }

            return maxLength;
        }
    }
}
