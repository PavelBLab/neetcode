package onlineassessment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class CodingInterviewTask4_EmployeeBreakFinder {

    /*
     * Problem: Employee Break Finder
     *
     * Booking.com's customer support center tracks when each employee is working.
     * Each employee has a list of working intervals [start, end] during the day.
     * Working hours are from 0 to 24 (representing hours of the day).
     *
     * Given an employee's work schedule (sorted, non-overlapping intervals),
     * find all their break periods during the workday.
     *
     * A break is any gap between two consecutive work intervals.
     * Do NOT include time before first shift or after last shift.
     *
     * Example 1:
     *   schedule = [[1, 3], [5, 8], [10, 14]]
     *   breaks = [[3, 5], [8, 10]]
     *   (gap between shift 1 end and shift 2 start, gap between shift 2 end and shift 3 start)
     *
     * Example 2:
     *   schedule = [[0, 24]]
     *   breaks = []
     *   (no gaps, works all day)
     *
     * Example 3:
     *   schedule = [[2, 4], [4, 8], [9, 12]]
     *   breaks = [[8, 9]]
     *   ([2,4] and [4,8] are back-to-back so no break, but gap between 8 and 9)
     */
    public static void main(String[] args) {
        System.out.println(solution5(List.of(
                new int[]{1, 3}, new int[]{5, 8}, new int[]{10, 14}
        ))); // Expected: [[3,5], [8,10]]

        System.out.println(solution5(List.of(
                new int[]{0, 24}
        ))); // Expected: []

        System.out.println(solution5(List.of(
                new int[]{2, 4}, new int[]{4, 8}, new int[]{9, 12}
        ))); // Expected: [[8,9]]
    }

    public static List<int[]> solution5(List<int[]> schedule) {
        if (schedule.isEmpty()) {
            return List.of();
        }

        var breakList = new ArrayList<int[]>();

        for (var i = 0; i < schedule.size() - 1; i++) {
            var shiftEnd = schedule.get(i)[1];
            var shiftStart = schedule.get(i + 1)[0];

            if (shiftEnd < shiftStart) {
                breakList.add(new int[] {shiftEnd, shiftStart});
            }
        }

        return breakList;
    }


    public static List<int[]> solution4(List<int[]> schedule) {
        var breakList = new ArrayList<int[]>();

        if (schedule == null || schedule.isEmpty()) {
            return List.of();
        }

        for (var i = 0; i < schedule.size() - 1; i++) {
            var shiftEnd = schedule.get(i)[1];
            var shiftStart = schedule.get(i + 1)[0];
            var breakArr = new int[2];

            if (shiftEnd < shiftStart) {
                breakArr[0] = shiftEnd;
                breakArr[1] = shiftStart;

                breakList.add(breakArr);
            }
        }

        return breakList;
    }


    public static List<int[]> solution3(List<int[]> schedule) {
        var result = new ArrayList<int[]>();

        if (schedule.isEmpty() || schedule.size() == 1) {
            return List.of();
        }

        for (var i = 0; i < schedule.size() - 1; i++) {
            var shiftEnd = schedule.get(i)[1];
            var shiftStart = schedule.get(i + 1)[0];

            if (shiftStart !=  shiftEnd) {
                result.add(new int[]{shiftEnd, shiftStart});
            }
        }

        return result;
    }


    public static List<int[]> solution2(List<int[]> schedule) {
        var result = new ArrayList<int[]>();

        if (schedule.size() == 1) {
            return List.of();
        }

        for (var i = 0; i < schedule.size() - 1; i++) {
            var breakGap = new int[2];

            if (schedule.get(i)[1] < schedule.get(i + 1)[0]) {
                breakGap[0] = schedule.get(i)[1];
                breakGap[1] = schedule.get(i + 1)[0];

                result.add(breakGap);
            }
        }

        return result;
    }


    public static List<int[]> solution1(List<int[]> schedule) {
        var result = new ArrayList<int[]>();

        for (var i = 0; i < schedule.size() - 1; i++) {
            int[] breakIntervals = new int[2];
            var currentShiftEnd = schedule.get(i)[1];
            var nextShiftStart = schedule.get(i + 1)[0];


            if (currentShiftEnd != nextShiftStart) {
                breakIntervals[0] = currentShiftEnd;
                breakIntervals[1] = nextShiftStart;

                result.add(breakIntervals);
            }

        }

        return result;
    }

    public static class CodingInterviewTask13_GroupAnagrams {

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
            System.out.println(solution2(new String[]{"eat", "tea", "tan", "ate", "nat", "bat"}));
            // Expected: [[eat, tea, ate], [tan, nat], [bat]]

            System.out.println(solution2(new String[]{""}));
            // Expected: [[]]

            System.out.println(solution2(new String[]{"a"}));
            // Expected: [[a]]
        }

        public static List<List<String>> solution2(String[] words) {
            var map = new HashMap<String, List<String>>();

            for (var word : words) {
                var wordArr = word.toCharArray();
                Arrays.sort(wordArr);

                map.computeIfAbsent(new String(wordArr), a -> new ArrayList<>()).add(word);
            }

            return map.values().stream()
                    .toList();
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
}
