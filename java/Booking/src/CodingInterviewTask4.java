import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CodingInterviewTask4 {

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
        System.out.println(solution4(List.of(
                new int[]{1, 3}, new int[]{5, 8}, new int[]{10, 14}
        ))); // Expected: [[3,5], [8,10]]

        System.out.println(solution4(List.of(
                new int[]{0, 24}
        ))); // Expected: []

        System.out.println(solution4(List.of(
                new int[]{2, 4}, new int[]{4, 8}, new int[]{9, 12}
        ))); // Expected: [[8,9]]
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
}
