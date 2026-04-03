import java.util.*;

public class CodingInterviewTask17 {

    /*
     * Problem: Group Anagrams
     *
     * Given an array of strings, group the anagrams together.
     * Two strings are anagrams if they contain the same characters
     * in any order.
     *
     * Reported in Booking.com OA.
     *
     * Example:
     *   input: ["eat", "tea", "tan", "ate", "nat", "bat"]
     *   output: [["eat","tea","ate"], ["tan","nat"], ["bat"]]
     *   (group order doesn't matter)
     */
    public static void main(String[] args) {
        System.out.println(solution1(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
        // Expected: [[eat, tea, ate], [tan, nat], [bat]]

        System.out.println(solution1(new String[]{""}));
        // Expected: [[]]

        System.out.println(solution1(new String[]{"a"}));
        // Expected: [[a]]
    }

    public static List<List<String>> solution1(String[] words) {
        var map = new HashMap<String, List<String>>();

        for (var word : words) {
            var key = word.toCharArray();
            Arrays.sort(key);
            map.computeIfAbsent(new String(key), l -> new ArrayList<>()).add(word);
        }

        return map.values().stream() // alternative new ArrayList<>(map.values())
                .toList();
    }

}
