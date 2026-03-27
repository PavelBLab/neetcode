import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StringMatchingInArray {

    public static void main(String[] args) {
//        System.out.println(Solution1.stringMatching(new String[]{"mass", "as", "hero", "superhero"}));
//        System.out.println(Solution1.stringMatching(new String[]{"neetcode","neeet","neet","code"}));
//        System.out.println(Solution1.stringMatching(new String[]{"blue","green","bu"}));
        System.out.println(Solution1.stringMatching(new String[]{"cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"}));
    }

    static class Solution1 {
        public static List<String> stringMatching(String[] words) {
            var result = new ArrayList<String>();

            for (String word : words) {
                for (String s : words) {

                    if (result.contains(s)) {
                        continue;
                    }

                    if (!word.equals(s) && word.contains(s)) {
                        result.add(s);
                    }
                }
            }

            return result;
        }
    }
}
