package livecodinginterview;

import java.util.ArrayDeque;

public class CodingInterviewTask4_BackspacingStrings {

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
        System.out.println(solution5("abc#def##")); // Expected: "abd"
        System.out.println(solution5("##abc"));      // Expected: "abc"
        System.out.println(solution5("abc###"));     // Expected: ""
        System.out.println(solution5("hello"));      // Expected: "hello"
        System.out.println(solution5(""));           // Expected: ""
    }

    public static String solution5(String input) {
        var sb = new StringBuilder();

        for (var c : input.toCharArray()) {
            if (c == '#') {
                if (!sb.isEmpty()) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(c);
            }
        }

        return new String(sb);
    }


    public static String solution4(String input) {
        var sb = new StringBuilder();
        var stack = new ArrayDeque<Character>();

        for (var c : input.toCharArray()) {
            if (c == '#') {
                if(!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(c);
            }
        }

        while (!stack.isEmpty()) {
            var layerSize = stack.size();
            var arr = new char[layerSize];

            for (var i = layerSize - 1; i >=0; i--) {
                arr[i] = stack.pop();
            }

            sb.append(arr);
        }

        return new String(sb);
    }

    public static String solution3(String input) {
        var sb = new StringBuilder();

        for (var c : input.toCharArray()) {
            if (c == '#') {
                if (!sb.isEmpty()) {
                    sb.deleteCharAt(sb.length() - 1);
                }
            } else {
                sb.append(c);
            }
        }

        return new String(sb);
    }


    public static String solution2(String input) {
        var sb = new StringBuilder();

        for (var c : input.toCharArray()) {
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
