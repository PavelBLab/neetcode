package livecodinginterview;

import java.util.List;

public class CodingInterviewTask13_LogRangeSearch {

    /*
     * Problem: Duplicate Booking Detector
     *
     * Each log: [timestamp, level, message]
     * Timestamps are in epoch seconds, logs are SORTED by timestamp.
     *
     * STAGE 1: Given a sorted list of logs, find all logs between
     * startTime and endTime (inclusive).
     *
     * Think about: what algorithm is best for searching sorted data?
     */
    public static void main(String[] args) {
        var logs = List.of(
                new String[]{"1000", "INFO",  "Server started"},
                new String[]{"1050", "DEBUG", "Connection pool initialized"},
                new String[]{"1100", "INFO",  "Request received"},
                new String[]{"1150", "WARN",  "Slow query detected"},
                new String[]{"1200", "ERROR", "Database timeout"},
                new String[]{"1250", "INFO",  "Request completed"},
                new String[]{"1300", "INFO",  "Health check OK"},
                new String[]{"1350", "ERROR", "Connection refused"},
                new String[]{"1400", "INFO",  "Cache cleared"}
        );

        System.out.println(searchLogs(logs, 1100, 1300));
        // Expected: logs from timestamp 1100 to 1300 inclusive
        // [1100 INFO Request received], [1150 WARN Slow query],
        // [1200 ERROR Database timeout], [1250 INFO Request completed],
        // [1300 INFO Health check OK]
    }

    public static List<String[]> searchLogs(List<String[]> logs, long startTime, long endTime) {
        // your code here
    }

}