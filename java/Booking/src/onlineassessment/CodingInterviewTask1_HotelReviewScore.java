package onlineassessment;

import java.util.*;

public class CodingInterviewTask1_HotelReviewScore {


    /*
     * Problem: Hotel Review Score
     *
     * Given a list of reviews and a list of positive keywords,
     * return the indices of reviews sorted by their score in descending order.
     * A review's score = number of words matching positive keywords.
     * If two reviews have the same score, smaller index comes first.
     */
    public static void main(String[] args) {
        var positiveKeywords = List.of("clean", "comfortable", "great", "friendly", "location");
        var reviews = List.of(
                "the hotel was clean and comfortable",
                "great location and friendly staff",
                "the room was dirty and noisy",
                "clean room great location friendly staff comfortable bed");

        System.out.println(solution5(positiveKeywords, reviews));
        // Expected: [3, 1, 0, 2]
    }


    public static List<Integer> solution5(List<String> positiveKeywords, List<String> reviews) {
        var reviewScore = new HashMap<Integer, Integer>();
        var positiveKeywordsSet = new HashSet<String>(positiveKeywords);

        for (var i = 0; i < reviews.size(); i++) {
            var review = reviews.get(i);
            reviewScore.put(i, 0);

            var wordArr = review.toLowerCase().replaceAll("[^a-z0-9 ]", "").split(" ");

            for (var word : wordArr) {
                if (positiveKeywordsSet.contains(word)) {
                    reviewScore.put(i, reviewScore.get(i) + 1);
                }
            }
        }


        return reviewScore.entrySet().stream()
                .sorted(
                        Comparator.comparingInt((Map.Entry<Integer, Integer> e) -> e.getValue())
                                .reversed()
                                .thenComparingInt(Map.Entry::getKey)
                )
                .map(Map.Entry::getKey)
                .toList();
    }


    public static List<Integer> solution4(List<String> positiveKeywords, List<String> reviews) {
        var scoreMap = new HashMap<Integer, Integer>();
        var positiveKeywordSet = new HashSet<>(positiveKeywords);

        for (var i = 0; i < reviews.size(); i++) {
            scoreMap.put(i, scoreMap.getOrDefault(i, 0) + getScore(positiveKeywordSet, reviews.get(i)));
        }

        return scoreMap.entrySet().stream()
                .sorted(
                        Comparator.comparingInt((Map.Entry<Integer, Integer> e) -> e.getValue())
                                .reversed()
                                .thenComparing(Map.Entry::getKey)
                )
                .map(Map.Entry::getKey)
                .toList();
    }

    private static int getScore(Set<String> positiveKeywordSet, String review) {
        var score = 0;
        var formatedReviewArr = review.toLowerCase().replaceAll("[^a-z0-9 ]", " ").split(" ");

        for (var word : formatedReviewArr) {
            if (positiveKeywordSet.contains(word)) {
                score++;
            }
        }

        return score;
    }

    public static List<Integer> solution3(List<String> positiveKeywords, List<String> reviews) {
        var scores = new HashMap<Integer, Integer>();
        var positiveKeywordsSet = new HashSet<>(positiveKeywords);

        for (var i = 0; i < reviews.size(); i++) {
            var wordArr = reviews.get(i).toLowerCase().replaceAll("[^a-z0-9 ]", "").split(" ");
            scores.put(i, 0);

            for (var word : wordArr) {
                if (positiveKeywordsSet.contains(word)) {
                    scores.put(i, scores.get(i) + 1);
                }
            }
        }

        return scores.entrySet().stream()
                .sorted(
                        Comparator.comparingInt((Map.Entry<Integer, Integer> e) -> e.getValue())
                                .reversed()
                                .thenComparingInt(Map.Entry::getKey)
                )
                .map(Map.Entry::getKey)
                .toList();
    }

    public static List<Integer> solution2(List<String> positiveKeywords, List<String> reviews) {
        var positiveKeywordsSet = new HashSet<>(positiveKeywords);
        var score = new HashMap<Integer, Integer>();

        for (var i = 0; i < reviews.size(); i++) {
            var wordsList = reviews.get(i).split(" ");
            score.put(i, 0);

            for (var word : wordsList) {
                if (positiveKeywordsSet.contains(word)) {
                    score.put(i, score.get(i) + 1);
                }
            }
        }

        return score.entrySet().stream()
                .sorted(
                        Comparator.comparingInt((Map.Entry<Integer, Integer> e) -> e.getValue())
                                .reversed()
                                .thenComparingInt(Map.Entry::getKey))
                .map(Map.Entry::getKey)
                .toList();
    }

    public static List<Integer> solution1(final List<String> positiveKeywords, final List<String> reviews) {
        var positiveKeywordsSet = new HashSet<>(positiveKeywords);
        var scoreMap = new HashMap<Integer, Integer>();

        for (var i = 0; i < reviews.size(); i++) {
            var reviewArr = reviews.get(i).toLowerCase().replaceAll("[^a-z0-9 ]", "").split(" ");

            scoreMap.put(i, scoreMap.getOrDefault(i, 0) + calculateScore(reviewArr, positiveKeywordsSet));
        }


        return scoreMap.entrySet().stream()
                .sorted(
                        Comparator.comparingInt((Map.Entry<Integer, Integer> e) -> e.getValue())
                                .reversed()
                                .thenComparingInt(Map.Entry::getKey)
                )
                .map(Map.Entry::getKey)
                .toList();
    }

    private static Integer calculateScore(final String[] reviewArr, final Set<String> positiveKeywords) {
        var score = 0;

        for (var word : reviewArr) {
            if (positiveKeywords.contains(word)) {
                score++;
            }
        }

        System.out.println(score);
        return score;
    }
}
