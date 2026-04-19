package livecodinginterview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class CodingInterviewTask10_MergeOverlappingReservations {

    /*
     * Problem: Merge Overlapping Reservations
     *
     * Merge overlapping hotel reservations into continuous blocks.
     * Two reservations overlap if one starts before the other ends.
     * Return merged list sorted by start time.
     *
     * [[1,3],[2,6],[8,10],[15,18]] → [[1,6],[8,10],[15,18]]
     */
    public static void main(String[] args) {
        System.out.println(format(solution6(new int[][]{{1, 3}, {2, 6}, {8, 10}, {15, 18}})));
        // Expected: [[1,6], [8,10], [15,18]]

        System.out.println(format(solution6(new int[][]{{1, 4}, {4, 5}})));
        // Expected: [[1,5]]

        System.out.println(format(solution6(new int[][]{{1, 4}, {2, 3}})));
        // Expected: [[1,4]]

        System.out.println(format(solution6(new int[][]{{5, 8}, {1, 3}})));
        // Expected: [[1,3], [5,8]]

        System.out.println(format(solution6(new int[][]{{1, 5}})));
        // Expected: [[1,5]]
    }

    private static String format(List<int[]> list) {
        return list.stream().map(Arrays::toString).toList().toString();
    }

    public static List<int[]> solution6(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return List.of();
        }

        var result = new ArrayList<int[]>();
        var list = Arrays.stream(intervals)
                .sorted(
                        Comparator.comparingInt(e -> e[0])
                )
                .toList();

        var overlapReservation = list.getFirst();
        result.add(overlapReservation);

        for (var i = 1; i < list.size(); i++) {
            var currentInterval = list.get(i);

            if (overlapReservation[1] >= currentInterval[0]) {
                overlapReservation[1] = Math.max(overlapReservation[1], currentInterval[1]);
            } else {
                overlapReservation = currentInterval;
                result.add(overlapReservation);
            }
        }

        return result;
    }


    public static List<int[]> solution5(int[][] intervals) {
        var result = new ArrayList<int[]>();

        var list = Arrays.stream(intervals)
                .filter(e -> e.length == 2)
                .sorted(
                        Comparator.comparingInt(e -> e[0])
                )
                .toList();

        var reservation = list.getFirst();
        result.add(reservation);

        for (var i = 1; i < intervals.length; i++) {
            var currentReservation = list.get(i);

            if (reservation[1] >= currentReservation[0]) {
                reservation[1] = Math.max(reservation[1], currentReservation[1]);
            } else {
                reservation = currentReservation;
                result.add(reservation);
            }
        }

        return result;
    }


    public static List<int[]> solution4(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return List.of();
        }

        var list = Arrays.stream(intervals)
                .filter(e -> e.length == 2)
                .sorted(Comparator.comparingInt(
                        e -> e[0]
                )).toList();

        var reservation = list.getFirst();
        var result = new ArrayList<int[]>();
        result.add(reservation);


        for (var i = 1; i < list.size(); i++) {
            var currentReservation = list.get(i);

            if (reservation[1] >= currentReservation[0]) {
                reservation[1] = Math.max(reservation[1], currentReservation[1]);
            } else {
                reservation = currentReservation;
                result.add(reservation);
            }
        }

        return result;
    }


    public static List<int[]> solution3(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return List.of();
        }

        var list = Arrays.stream(intervals)
                .sorted(Comparator.comparingInt(e -> e[0]))
                .toList();

        var reservation = list.getFirst();
        var result = new ArrayList<int[]>();
        result.add(reservation);

        for (var i = 1; i < list.size(); i++) {
            if (reservation[1] >= list.get(i)[0]) {
                reservation[1] = Math.max(reservation[1], list.get(i)[1]);
            } else {
                reservation = list.get(i);
                result.add(reservation);
            }
        }

        return result;
    }
}
