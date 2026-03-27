import java.util.Arrays;

public class LengthOfLastWord {

    public static void main(String[] args) {
        System.out.println(Solution1.lengthOfLastWord("Hello World"));
        System.out.println(Solution1.lengthOfLastWord("   fly me   to   the moon  "));
        System.out.println(Solution1.lengthOfLastWord("luffy is still joyboy"));
    }

    static class Solution1 {
        public static int lengthOfLastWord(String s) {
            var trimmedString = s.trim();
            System.out.println(trimmedString);

            var sArr = trimmedString.split(" ");
            System.out.println(Arrays.toString(sArr));
            var lastWord = sArr[sArr.length - 1];

            return lastWord.length();
        }
    }
}
