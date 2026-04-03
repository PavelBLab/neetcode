import java.util.*;

public class CodingInterviewTask3 {

    /*
     * Problem: Award Top K Hotels
     *
     * Booking.com wants to recognize top performing hotels by analyzing reviews.
     * Each review belongs to a hotel. A review's score is calculated as:
     *   - Each positive keyword found in the review: +3 points
     *   - Each negative keyword found in the review: -1 point
     *
     * A hotel's total score is the sum of scores across ALL its reviews.
     * Return the IDs of the top k hotels sorted by total score descending.
     * If two hotels have the same score, the one with the smaller ID comes first.
     *
     * Constraints:
     *   - Matching is case-insensitive
     *   - Ignore dots and commas in reviews
     *   - If a keyword appears twice in a review, count it twice
     *   - Keywords are always single words (never "swimming pool")
     */
    public static void main(String[] args) {
        var positiveKeywords = "breakfast beach citycenter location metro view staff price";
        var negativeKeywords = "noise dirty unfair";

        var hotelIds = List.of(1, 2, 1, 1, 2);
        var reviews = List.of(
                "This hotel has a nice view of the citycenter. The location is perfect.",
                "The breakfast is ok. Regarding location, it is quite far from citycenter but the price is cheap so it is worth.",
                "Location is excellent, 5 minutes from citycenter. There is also a metro station very close to the hotel.",
                "They said I couldn't take my dog and there were other guests with dogs! That is not fair.",
                "Very friendly staff and a good cost-benefit ratio. Its location is a bit far from citycenter."
        );

        int k = 2;
        System.out.println(solution3(positiveKeywords, negativeKeywords, hotelIds, reviews, k));
        // Expected: [2, 1]
    }

    public static List<Integer> solution3(String positiveKeywords, String negativeKeywords,
                                          List<Integer> hotelIds, List<String> reviews, int k) {
        if (hotelIds.size() != reviews.size()) {
            throw new IllegalArgumentException("hotelIds size not equal reviews size.");
        }

        var hotelScoreMap = new HashMap<Integer, Integer>();
        var positiveKeywordsSet = convertKeywordsToSet(positiveKeywords);
        var negativeKeywordsSet = convertKeywordsToSet(negativeKeywords);

        for (var i = 0; i < reviews.size(); i++) {
            hotelScoreMap.put(
                    hotelIds.get(i),
                    hotelScoreMap.getOrDefault(hotelIds.get(i), 0)
                            + calculateScore(reviews.get(i), positiveKeywordsSet, negativeKeywordsSet));
        }


        return hotelScoreMap.entrySet().stream()
                .sorted(
                        Comparator.comparingInt((Map.Entry<Integer, Integer> e) -> e.getValue())
                                .reversed()
                                .thenComparingInt(Map.Entry::getKey)
                )
                .limit(k)
                .map(Map.Entry::getKey)
                .toList();
    }

    public static int calculateScore(String review,
                                     Set<String> positiveKeywordsSet,
                                     Set<String> negativeKeywordsSet) {
        var score = 0;
        var formatedReviewArr = review.toLowerCase().replaceAll("[^a-z0-9 ]", "").split(" ");

        for (var word : formatedReviewArr) {
            if (positiveKeywordsSet.contains(word)) {
                score += 3;
            }

            if (negativeKeywordsSet.contains(word)) {
                score--;
            }
        }


        return score;
    }

    public static Set<String> convertKeywordsToSet(final String keywords) {
        var arr = keywords.toLowerCase().split(" ");
        var set = new HashSet<String>();

        for (var word : arr) {
            set.add(word);
        }

        return set;
    }

    public static List<Integer> solution2(final String positiveKeywords,
                                          final String negativeKeywords,
                                          List<Integer> hotelIds,
                                          List<String> reviews,
                                          int k) {
        if (hotelIds.size() != reviews.size()) {
            throw new IllegalArgumentException("hotelIds and reviews contains different number of elements");
        }

        var score = new HashMap<Integer, Integer>();
        var positiveKeywordsSet = new HashSet<>(List.of(positiveKeywords.split(" ")));
        var negativeKeywordsSet = new HashSet<>(List.of(negativeKeywords.split(" ")));

        for (var i = 0; i < reviews.size(); i++) {
            var wordArr = reviews.get(i).toLowerCase().replaceAll("[^a-z0-9 ]", "").split(" ");
            var hotelId = hotelIds.get(i);

            score.put(hotelId, score.getOrDefault(hotelId, 0) + calculateScore(wordArr, positiveKeywordsSet, negativeKeywordsSet));
        }

        return score.entrySet().stream()
                .sorted(
                        Comparator.comparingInt((Map.Entry<Integer, Integer> e) -> e.getValue())
                                .reversed()
                                .thenComparingInt(Map.Entry::getKey)
                )
                .limit(k)
                .map(Map.Entry::getKey)
                .toList();
    }

    private static int calculateScore(final String[] formatedReviewArr,
                                   final Set<String> positiveKeywordsSet,
                                   final Set<String> negativeKeywordsSet) {
        var score = 0;

        for (var word : formatedReviewArr) {
            if (positiveKeywordsSet.contains(word)) {
                score += 3;
            }

            if (negativeKeywordsSet.contains(word)) {
                score--;
            }
        }

        return score;
    }







    public static List<Integer> solution1(String positiveKeywords, String negativeKeywords,
                                          List<Integer> hotelIds, List<String> reviews, int k) {
        // Let's write constraints and validations
        if (hotelIds.size() != reviews.size()) {
            throw new IllegalArgumentException("hotelIds size is not same size as reviews");
        }


        var positiveKeywordsSet = Set.of(positiveKeywords.toLowerCase().split(" ")); // Search O(1)
        var negativeKeywordsSet = Set.of(negativeKeywords.toLowerCase().split(" ")); // Search O(1)
        var result = new HashMap<Integer, Integer>(); // Search O(1)


        for (var i = 0; i < hotelIds.size(); i++) {
            var score = calculateReviewScore(reviews.get(i), positiveKeywordsSet, negativeKeywordsSet);
            result.put(hotelIds.get(i), result.getOrDefault(hotelIds.get(i), 0) + score);
        }

        return result.entrySet().stream()
                .sorted(
                        Comparator.comparingInt((Map.Entry<Integer, Integer> e) -> e.getValue())
                                .reversed()
                                .thenComparingInt(Map.Entry::getKey)
                )
                .limit(k)
                .map(Map.Entry::getKey)
                .toList();
    }

    private static int calculateReviewScore(final String review,
                               final Set<String> positiveKeywordsSet,
                               final Set<String> negativeKeywordsSet) {
        var score = 0;

        var formatReview = formatReview(review);

        for (var word : formatReview) {
            if (positiveKeywordsSet.contains(word)) {
              score += 3;
            }

            if (negativeKeywordsSet.contains(word)) {
                score--;
            }
        }

        return score;
    }

    private static String[] formatReview(final String review) {
        return review.toLowerCase().replaceAll("[^a-z0-9 ]", "").split(" ");
    }
}
