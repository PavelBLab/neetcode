import java.util.ArrayDeque;
import java.util.Arrays;

public class CodingInterviewTask15 {

    /*
     * Problem: Backspacing Strings
     *
     * Given a string containing letters and '#' characters,
     * '#' means backspace — it deletes the previous character.
     * Return the final string after processing all backspaces.
     *
     * This is a reported Booking.com live coding question.
     *
     * Example 1: "abc#def##" → "abde"
     *   a, b, c, # (delete c), d, e, f, # (delete f), # (delete e) → "abd"
     *
     * Example 2: "##abc" → "abc"
     *   # (nothing to delete), # (nothing), a, b, c → "abc"
     *
     * Example 3: "abc###" → ""
     *   a, b, c, # (delete c), # (delete b), # (delete a) → ""
     */
    public static void main(String[] args) {
        // Test 1: mixed backspaces
        System.out.println(solution2("abc#def##"));
        // Expected: "abd"

        // Test 2: backspaces at start
        System.out.println(solution2("##abc"));
        // Expected: "abc"

        // Test 3: all deleted
        System.out.println(solution2("abc###"));
        // Expected: ""

        // Test 4: no backspaces
        System.out.println(solution2("hello"));
        // Expected: "hello"

        // Test 5: empty string
        System.out.println(solution2(""));
        // Expected: ""

        // Test 6: backspaces at start
        System.out.println(solution2("#abc"));
        // Expected: "abc"
    }

    public static String solution2(String input) {
        var sb = new StringBuilder();

        for(var c : input.toCharArray()) {
            if (c == '#') {
                if (!sb.isEmpty()) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public static String solution1(String input) {
        var charactersQueue = new ArrayDeque<String>();
        var charactersArr = input.split("");
        var sb = new StringBuilder();
        var result = new StringBuilder();

        for (var i : charactersArr) {
            if (i.equals("#") && !charactersQueue.isEmpty()) {
                charactersQueue.pop();
            }

            if (!i.equals("#")) {
                charactersQueue.push(i);
            }
        }

        while (!charactersQueue.isEmpty()) {
            sb.append(charactersQueue.pop());
        }

        for (var i = sb.length() - 1; i >= 0; i--) {
            result.append(sb.charAt(i));
        }

        return result.toString();
    }

}
