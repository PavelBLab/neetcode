package livecodinginterview.mockinterview;

import java.util.*;
import java.util.stream.Collectors;

public class Task1 {

    public static void main(String[] args) {
        var reviews = List.of(
                new String[]{"H1", "Anna", "5", "Great!"},
                new String[]{"H1", "Bob", "3", "OK"},
                new String[]{"H2", "Clara", "4", "Nice"},
                new String[]{"H1", "Dan", "4", "Good"},
                new String[]{"H2", "Eve", "2", "Bad"},
                new String[]{"H3", "Frank", "5", "Perfect"},
                new String[]{"H3", "Grace", "5", "Amazing"}
        );

        System.out.println(solution1(reviews, 2, 2));


        var suspiciousReviews = List.of(
                new String[]{"H1", "Anna",  "5", "Great!"},
                new String[]{"H1", "Bob",   "3", "OK"},
                new String[]{"H1", "Anna",  "1", "Terrible!"},  // Anna reviewed H1 twice!
                new String[]{"H2", "Clara", "4", "Nice"},
                new String[]{"H2", "Clara", "5", "Amazing"},     // Clara reviewed H2 twice!
                new String[]{"H1", "Anna",  "1", "Terrible2!"}  // Anna reviewed H1 twice!
        );

        System.out.println(findSuspiciousReviews(suspiciousReviews));
    }

    public static Map<String, Double> solution1(final List<String[]> reviews, int topN, int minReviews) {
        if (reviews == null || reviews.isEmpty()) {
            return Map.of();
        }

        var hotelCountAndTotalScoreMap = new HashMap<String, double[]>();

        for (var review : reviews) {
            if (review.length != 4) {
                continue;
            }

            var hotelId = review[0];
            var score = review[2];

            var scoreAndCount = hotelCountAndTotalScoreMap.getOrDefault(hotelId, new double[2]);

            double scoreDouble;

            try {
                scoreDouble = Double.parseDouble(score);
                if (scoreDouble < 1 || scoreDouble > 5) {
                    continue;
                }

            } catch (NumberFormatException e) {
                continue;
            }

            scoreAndCount[0] += scoreDouble;
            scoreAndCount[1] += 1;

            hotelCountAndTotalScoreMap.put(hotelId, scoreAndCount);
        }

        return hotelCountAndTotalScoreMap.entrySet().stream()
                .filter(e -> e.getValue()[1] >= minReviews)
                .sorted(
                        Comparator.comparingDouble((Map.Entry<String, double[]> e) -> e.getValue()[0]/e.getValue()[1])
                                .reversed()
                                .thenComparing(Map.Entry::getKey)
                )
                .limit(topN)
                .collect(
                        Collectors.toMap(
                                Map.Entry::getKey,
                                e -> e.getValue()[0]/e.getValue()[1],
                                (a, b) -> a,
                                LinkedHashMap::new
                        )
                );
    }

    public static List<List<String>> findSuspiciousReviews(final List<String[]> reviews) {
        if (reviews == null || reviews.isEmpty()) {
            return List.of();
        }

        var uniqueGuests = new HashSet<List<String>>();
        var suspiciousReviews = new ArrayList<List<String>>();

        for (var review : reviews) {
            if (review.length != 4) {
                continue;
            }

            var hotelAndGuest = List.of(review[0], review[1]);

            if (!uniqueGuests.add(hotelAndGuest)) {
                suspiciousReviews.add(hotelAndGuest);
            }

        }

        return suspiciousReviews;
    }

}
