package onlineassessment;

public class CodingInterviewTask13_StringRotation {

    /*
     * Problem: String Rotation
     *
     * Booking.com encodes confirmation codes using string rotation.
     * Given a string, a left-shift value, and a right-shift value,
     * apply left rotation first, then right rotation.
     *
     * Left rotation by k: move first k characters to the end.
     * Right rotation by k: move last k characters to the front.
     *
     * Reported in Booking.com OA.
     *
     * Example 1: str="abcdef", left=2, right=3
     *   Left rotate by 2:  "abcdef" → "cdefab"
     *   Right rotate by 3: "cdefab" → "fabcde"
     *   Result: "fabcde"
     *
     * Example 2: str="hello", left=0, right=0
     *   No rotation → "hello"
     *
     * Example 3: str="abcd", left=6, right=0
     *   left=6 but string is 4 chars → effective left = 6 % 4 = 2
     *   Left rotate by 2: "abcd" → "cdab"
     *   Result: "cdab"
     */
    public static void main(String[] args) {
        System.out.println(solution3("abcdef", 2, 3));
        // Expected: "fabcde"

        System.out.println(solution3("hello", 0, 0));
        // Expected: "hello"

        System.out.println(solution3("abcd", 6, 0));
        // Expected: "cdab"

        System.out.println(solution3("a", 5, 3));
        // Expected: "a"

        System.out.println(solution3("abcdef", 0, 2));
        // Expected: "efabcd"
    }

    public static String solution3(String str, int left, int right) {
        if (str.length() == 1) {
            return str;
        }

        var sb = new StringBuilder(str);

        if (left > str.length()) {
            left = left % str.length();
        }

        if (right > str.length()) {
            right = right % str.length();
        }

        var leftRotation = sb.substring(0, left);
        sb.append(leftRotation);
        sb.delete(0, left);

        var rightRotation = sb.substring(sb.length() - right);
        sb.delete(sb.length() - right, sb.length());

        return rightRotation + new String(sb);
    }


    public static String solution2(String str, int left, int right) {
        var sb = new StringBuilder(str);

        if (left > str.length()) {
            left = left % str.length();
        }

        if (right > str.length()) {
            right = right % str.length();
        }

        var leftRotation = sb.substring(0, left);
        sb.append(leftRotation);
        sb.delete(0, left);

        var rightRotation = sb.substring(sb.length() - right, sb.length());
        sb.delete(sb.length() - right, sb.length());

        return rightRotation + sb;
    }


    public static String solution1(String str, int left, int right) {
        var sb = new StringBuilder(str);

        if (left > str.length()) {
            left = left % str.length();
        }

        if (right > str.length()) {
            right = right % str.length();
        }


        var leftSubstring = sb.substring(0, left);
        sb.append(leftSubstring);
        sb.delete(0, left);

        var rightSubstring = sb.substring(sb.length() - right, str.length());
        sb.delete(sb.length() - right, sb.length());

        return rightSubstring + sb;
    }
}
