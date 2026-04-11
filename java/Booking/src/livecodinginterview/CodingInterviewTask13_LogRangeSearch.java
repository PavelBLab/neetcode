package livecodinginterview;

import java.util.*;
import java.util.stream.Collectors;

public class CodingInterviewTask13_LogRangeSearch {

    /*
     * Problem: Log Range Search
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
                new String[]{"1000", "INFO", "Server started"},
                new String[]{"1050", "DEBUG", "Connection pool initialized"},
                new String[]{"1100", "INFO", "Request received"},
                new String[]{"1150", "WARN", "Slow query detected"},
                new String[]{"1200", "ERROR", "Database timeout"},
                new String[]{"1250", "INFO", "Request completed"},
                new String[]{"1300", "INFO", "Health check OK"},
                new String[]{"1350", "ERROR", "Connection refused"},
                new String[]{"1400", "INFO", "Cache cleared"}
        );

        System.out.println(searchLogs3(logs, 1100, 1300, Set.of("INFO","ERROR")));
        // Expected:
        // ERROR → [[1200, ERROR, Database timeout]]
        // INFO  → [[1100, INFO, Request received], [1250, INFO, Request completed],
    }


    public static Map<String, List<String[]>> searchLogs3(List<String[]> logs,
                                                          long startTime,
                                                          long endTime,
                                                          Set<String> levels) {
        if (logs == null || logs.isEmpty()) {
            return Map.of();
        }

        var startLogIndex = binarySearchStartTime(logs, startTime);
        var endLogIndex = binarySearchEndTime(logs, endTime) + 1;

        if (startLogIndex >= endLogIndex) {
            return Map.of();
        }

        var subLogList = logs.subList(startLogIndex, endLogIndex);

        return subLogList.stream()
                .filter(e -> levels.contains(e[1]))
                .collect(Collectors.groupingBy(e -> e[1]));
    }


    private static int binarySearchEndTime(final List<String[]> logs, final long endTime) {
        var highPointer = logs.size() - 1;
        var lowPointer = 0;

        while (lowPointer <= highPointer) {
            var midPointer = (highPointer + lowPointer) / 2;

            long midTimeLong;

            try {
                midTimeLong = Long.parseLong(logs.get(midPointer)[0]);
            } catch (NumberFormatException e) {
                throw new NullPointerException("Wrong format, cannot parse to long");
            }

            if (endTime < midTimeLong) {
                highPointer = midPointer - 1;
            } else {
                lowPointer = midPointer + 1;
            }
        }
        return highPointer;
    }

    private static int binarySearchStartTime(final List<String[]> logs, final long startTime) {
        var highPointer = logs.size() - 1;
        var lowPointer = 0;

        while (lowPointer <= highPointer) {
            var midPointer = (highPointer + lowPointer) / 2;

            long midTimeLong;

            try {
                midTimeLong = Long.parseLong(logs.get(midPointer)[0]);
            } catch (NumberFormatException e) {
                throw new NullPointerException("Wrong format, cannot parse to long");
            }

            if (startTime <= midTimeLong) {
                highPointer = midPointer - 1;
            } else {
                lowPointer = midPointer + 1;
            }
        }
        return lowPointer;
    }





    // More efficient O(log n) binary search
    public static Map<String, List<String[]>> searchLogs2(List<String[]> logs,
                                                          long startTime,
                                                          long endTime,
                                                          Set<String> levels) {
        if (logs == null || logs.isEmpty()) {
            return Map.of();
        }

        if (startTime > endTime) {
            throw new IllegalArgumentException("startTime cannot be larger than endTime");
        }

        var timestampList = new ArrayList<Long>();

        for (var log : logs) {
            if (log.length != 3) {
                throw new IllegalArgumentException("Log structure is malformed");
            }

            long logTimestamp;

            try {
                logTimestamp = Long.parseLong(log[0]);
            } catch (Exception e) {
                // logs ....
                throw new IllegalArgumentException("Timestamp cannot be converted to long");
            }

            timestampList.add(logTimestamp);
        }


        var startTimeStampIndex = findStart(timestampList, startTime);
        var endTimeStampIndex = findEnd(timestampList, endTime) + 1;

        if (startTimeStampIndex >= endTimeStampIndex) {
            return Map.of();
        }

        return logs.subList(startTimeStampIndex, endTimeStampIndex).stream()
                .filter(e -> levels.contains(e[1]))
                .collect(Collectors.groupingBy(e -> e[1]));
    }

    private static int findEnd(List<Long> timestampList, long endTime) {
        var highPointer = timestampList.size() - 1;
        var lowPointer = 0;

        while (lowPointer <= highPointer) {
            var midPointer = (highPointer + lowPointer) / 2;

            if (endTime < timestampList.get(midPointer)) {
                highPointer = midPointer - 1;
            } else {
                lowPointer = midPointer + 1;
            }
        }

        return highPointer;
    }

    private static int findStart(List<Long> timestampList, long startTime) {
        var highPointer = timestampList.size() - 1;
        var lowPointer = 0;

        while (lowPointer <= highPointer) {
            var midPointer = (highPointer + lowPointer) / 2;

            if (startTime <= timestampList.get(midPointer)) {
                highPointer = midPointer - 1;
            } else {
                lowPointer = midPointer + 1;
            }
        }

        return lowPointer;
    }


    // Brut force O(n)
    public static List<String[]> searchLogs1(List<String[]> logs, long startTime, long endTime) {
        if (logs == null || logs.isEmpty()) {
            return List.of();
        }

        if (startTime > endTime) {
            throw new IllegalArgumentException("startTime cannot be larger than endTime");
        }

        var result = new ArrayList<String[]>();

        for (var log : logs) {
            if (log.length != 3) {
                continue;
            }

            long timestamp;

            try {
                timestamp = Long.parseLong(log[0]);
            } catch (Exception e) {
                // logs ....
                continue;
            }

            if (timestamp >= startTime && timestamp <= endTime) {
                result.add(log);
            }

            if (timestamp > endTime) {
                break;  // everything after is also > endTime
            }
        }

        return result;
    }

}