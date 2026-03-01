import java.util.*;

public class BookingTask3 {

    public static void main(String[] args) {
        System.out.println(Solution1.awardTopKHotels(
                "breakfast beach citycentre location metro view staff price",
                "not",
                List.of(1, 2, 1, 1, 2),
                List.of(
                        "This hotel has a nice view of the citycentre. The location is perfect.",
                        "The breakfast is ok. Regarding location, it is quite far from citycenter but price is cheap so it is worth.",
                        "Location is excellent, 5 minutes from citycentre. There is also a metro station very close to the hotel.",
                        "They said I couldn't take my dog and there were other guests with dogs! That is not fair.",
                        "Very friendly staff and good cost-benefit ratio. Its location is bit far from citycenter."),
                2
        ));
    }

    static class Solution1 {
        public static List<Integer> awardTopKHotels(
                String positiveKeywords,
                String negativeKeywords,
                List<Integer> hotelIds,
                List<String> reviews,
                int k) {

            var hotelScoreMap = new HashMap<Integer, Integer>();
            var positiveSet = new HashSet<>(List.of(positiveKeywords.split(" ")));
            var negativeSet = new HashSet<>(List.of(negativeKeywords.split(" ")));

            for (var i = 0; i < hotelIds.size(); i++) {
                hotelScoreMap.put(hotelIds.get(i), hotelScoreMap.getOrDefault(hotelIds.get(i), 0) + calculateScore(reviews.get(i), positiveSet, negativeSet));
            }

            return hotelScoreMap.entrySet().stream().sorted(Comparator.comparingInt((Map.Entry<Integer, Integer> e) -> e.getValue()).reversed())
                    .limit(k)
                    .map(Map.Entry::getKey)
                    .toList();
        }

        private static Integer calculateScore(String review, Set<String> positiveKeywords, Set<String> negativeKeywords) {
            var score = 0;
            var formattedReview = review.replaceAll("[^a-zA-Z ]", "").toLowerCase().split(" ");

            for (var word : formattedReview) {

                if (positiveKeywords.contains(word)) {
                    score += 3;
                }

                if (negativeKeywords.contains(word)) {
                    score -= 1;
                }
            }

            return score;
        }
    }
}
