public class AppendCharactersToStringToMakeSubsequence {

    public static void main(String[] args) {
        System.out.println(Solution1.appendCharacters("coaching", "coding"));
        System.out.println(Solution1.appendCharacters("abcde", "a"));
        System.out.println(Solution1.appendCharacters("z", "abcde"));
        System.out.println(Solution1.appendCharacters("caaat", "cat"));
        System.out.println("-----------------------------------------------");
        System.out.println(Solution2.appendCharacters("coaching", "coding"));
        System.out.println(Solution2.appendCharacters("abcde", "a"));
        System.out.println(Solution2.appendCharacters("z", "abcde"));
        System.out.println(Solution2.appendCharacters("caaat", "cat"));
    }

    static class Solution2 {
        public static int appendCharacters(String s, String t) {
            int i = 0, tIndexPointer = 0;

            while(i < s.length() && tIndexPointer < t.length()) {
                if (s.charAt(i) == t.charAt(tIndexPointer)) {
                    tIndexPointer++;

                }
                i++;
            }
            return t.length() - tIndexPointer;
        }
    }


    static class Solution1 {
        public static int appendCharacters(String s, String t) {
            var tIndexPointer = 0;

            for (var i = 0; i < s.length(); i++) {
                if (tIndexPointer == t.length()) {
                    break;
                }

                if (s.charAt(i) == t.charAt(tIndexPointer)) {
                    tIndexPointer++;
                }
            }

            System.out.println(s + t.substring(tIndexPointer));

            return t.length() - tIndexPointer;
        }
    }
}
