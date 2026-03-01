public class ScoreOfString {

    public static void main(String[] args) {
        System.out.println(Solution1.scoreOfString("code"));
    }

    static public class Solution1 {
        public static int scoreOfString(String s) {
            var count = 0;

            for (var i = 0; i < s.length() - 1; i++) {
                count += Math.abs(s.charAt(i + 1) - s.charAt(i));
            }

            return count;
        }
    }

}
