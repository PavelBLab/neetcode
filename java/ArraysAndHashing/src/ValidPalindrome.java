import java.util.ArrayList;
import java.util.stream.Collectors;

public class ValidPalindrome {

    public static void main(String[] args) {
        System.out.println(Solution1.isPalindrome("Was it a car or a cat I saw?"));
        System.out.println(Solution1.isPalindrome("tab a cat"));
        System.out.println("-----------------------------------------------");

        System.out.println(Solution2.isPalindrome("Was it a car or a cat I saw?"));
        System.out.println(Solution2.isPalindrome("tab a cat"));
        System.out.println("-----------------------------------------------");
    }

    static class Solution2 {
        public static boolean isPalindrome(String s) {
            if (s.isEmpty() || s.length() > 1000) {
                return false;
            }

            var formattedText = s.toLowerCase().replaceAll("[^a-z0-9]", "");
            var sBuilder = new StringBuilder();

            for (int i = formattedText.length() - 1; i >= 0; i--) {
                sBuilder.append(formattedText.charAt(i));
            }

            return formattedText.contains(sBuilder);
        }
    }

    static class Solution1 {
        public static boolean isPalindrome(String s) {
            if (s.isEmpty() || s.length() > 1000) {
                return false;
            }

            var formattedTest = s.toLowerCase().replaceAll("[^a-z0-9]+", "");
            System.out.println(formattedTest);

            var textArr = formattedTest.toCharArray();
            var reversedTextList = new ArrayList<Character>();

            for(var i = textArr.length - 1; i >= 0; i--) {
                reversedTextList.add(textArr[i]);
            }

            System.out.println(reversedTextList.stream().map(String::valueOf).collect(Collectors.joining("")));

            return formattedTest.equals(reversedTextList.stream().map(String::valueOf).collect(Collectors.joining("")));
        }
    }

}
