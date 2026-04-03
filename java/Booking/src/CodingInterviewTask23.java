import java.util.ArrayList;
import java.util.List;

public class CodingInterviewTask23 {

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
//        System.out.println(solution1("abcdef", 2, 3));
        // Expected: "fabcde"

//        System.out.println(solution1("hello", 0, 0));
        // Expected: "hello"

//        System.out.println(solution1("abcd", 6, 0));
        // Expected: "cdab"

//        System.out.println(solution1("a", 5, 3));
        // Expected: "a"

        System.out.println(solution1("abcdef", 0, 2));
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
