import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class TopKFrequentElements {

    public static void main(String[] args) {
        System.out.println(Arrays.toString(Solution1.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(Solution1.topKFrequent(new int[]{1}, 1)));
        System.out.println("-----------------------------------------------");

        System.out.println(Arrays.toString(Solution2.topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));
        System.out.println(Arrays.toString(Solution2.topKFrequent(new int[]{1}, 1)));
        System.out.println("-----------------------------------------------");

    }

    static class Solution2 {
        public static int[] topKFrequent(int[] nums, int k) {
            if (nums.length < 1 || nums.length > Math.pow(10, 4)) {
                throw new IllegalArgumentException("Invalid input");
            }

            if (k < 1 || k > nums.length) {
                throw new IllegalArgumentException("Invalid input");
            }

            var result = new int[k];

            var frequencyMap = new HashMap<Integer, Integer>();

            for (int i = 0; i < nums.length; i++) {
                if (nums [i] < -1000 || nums[i] > 1000) {
                    throw new IllegalArgumentException("Invalid input");
                }

                frequencyMap.put(nums[i], frequencyMap.getOrDefault(nums[i], 0) + 1);
            }

            var sortedFrequencyList = frequencyMap.entrySet().stream()
                    .sorted(Comparator.comparingInt((Map.Entry<Integer, Integer> e) -> e.getValue()).reversed())
                    .toList();


            // Not working functionality
            // var set = Arrays.stream(nums)
            //         .boxed()
            //         .filter(n -> n > -1000 && n < 1000)
            //         .collect(Collectors.toSet());
            //
            // var reversedOrderedList = set.stream().sorted(Comparator.reverseOrder()).toList();

            System.out.println("Sorted frequency list: " + sortedFrequencyList);

            for (var i = 0; i < k; i++) {
                result[i] = sortedFrequencyList.get(i).getKey();
            }

            return result;
            //return sortedFrequencyList.subList(0, k).stream().mapToInt(Map.Entry::getKey).toArray();
        }
    }


    static class Solution1 {
        public static int[] topKFrequent(int[] nums, int k) {
            if (nums.length < 1 || nums.length > Math.pow(10, 4)) {
                throw new IllegalArgumentException("Invalid input");
            }

            if (k < 1 || k > nums.length) {
                throw new IllegalArgumentException("Invalid input");
            }

            var result = new HashMap<Integer, Integer>();

            for (int num : nums) {
                if (num < -1000 || num > 1000) {
                    throw new IllegalArgumentException("Invalid input");
                }

                result.put(num, result.getOrDefault(num, 0) + 1);
            }

            var sortedList = result.entrySet().stream().sorted(Comparator.comparingInt((Map.Entry<Integer, Integer> e) -> e.getValue()).reversed()).toList();
            System.out.println(sortedList);

            if (sortedList.size() < k) {
                throw new IllegalArgumentException("Invalid input");
            }

            return sortedList.subList(0, k).stream().mapToInt(Map.Entry::getKey).toArray();
        }

    }
}
