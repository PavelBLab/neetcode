public class IsSubsequence {

    public static void main(String[] args) {
        System.out.println(Solution1.isSubsequence("node", "neetcode"));
        System.out.println("-----------------------------------------------");
        System.out.println(Solution2.isSubsequence("node", "neetcode"));

    }

    static class Solution2 {
        public static boolean isSubsequence(String s, String t) {
            var count = 0;

            for (var i = 0; i < t.length(); i++) {
              if (t.charAt(i) == s.charAt(count)) {
                    count++;
                }
            }

            return count == s.length();
        }
    }

    static class Solution1 {
        public static boolean isSubsequence(String s, String t) {
            var count = 0;
            var sb = new StringBuilder();

            for (var i = 0; i < t.length(); i++) {
                if (count == s.length()) {
                    break;
                }

                if (t.charAt(i) == s.charAt(count)) {
                    sb.append(t.charAt(i));
                    count++;
                }
            }

            System.out.println(sb);

            return s.contentEquals(sb);
        }
    }

}
