package LeetCode.Hard;

import java.util.*;

/**
 * Given a list of words (without duplicates), please write a program that returns all concatenated words in the given list of words.
 *
 * A concatenated word is defined as a string that is comprised entirely of at least two shorter words in the given array.
 *
 * Example:
 * Input: ["cat","cats","catsdogcats","dog","dogcatsdog","hippopotamuses","rat","ratcatdogcat"]
 * Output: ["catsdogcats","dogcatsdog","ratcatdogcat"]
 *
 * Explanation: "catsdogcats" can be concatenated by "cats", "dog" and "cats";
 * "dogcatsdog" can be concatenated by "dog", "cats" and "dog";
 * "ratcatdogcat" can be concatenated by "rat", "cat", "dog" and "cat".
 *
 * Note:
 * The number of elements of the given array will not exceed 10,000
 * The length sum of elements in the given array will not exceed 600,000.
 * All the input string will only include lower case letters.
 * The returned elements order does not matter.

 * Created by WinnieZhao on 2017/5/17.
 */
public class ConcatenatedWords {

    public List<String> findAllConcatenatedWordsInADict(String[] words) {

        List<String> result = new ArrayList<>();
        if (words == null || words.length == 0) {
            return result;
        }

        Arrays.sort(words, Comparator.comparingInt(String::length));

        Set<String> preWords = new HashSet<>();

        for (String word : words) {
            if (this.helper(word, preWords)) {
                result.add(word);
            }
            preWords.add(word);
        }

        return result;
    }

    private boolean helper(String word, Set<String> smallerWords) {
        if (smallerWords.isEmpty()) return false;

        boolean[] dp = new boolean[word.length()+1];
        dp[0] = true;

        for (int i=1; i<=word.length(); i++) {
            for (int j=0; j<i; j++) {
                if (!dp[j]) continue;

                if (dp[j] && smallerWords.contains(word.substring(j, i))) {
                    dp[i] = true;

                    break;
                }
            }
        }

        return dp[word.length()];
    }

    public static void main(String[] args) {
        ConcatenatedWords solution = new ConcatenatedWords();
        String[] words = {"a","aa"};
        System.out.println(solution.findAllConcatenatedWordsInADict(words));
    }
}
