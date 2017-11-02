package LeetCode.Hard;

import java.util.*;

/**
 * Given a list of unique words, find all pairs of distinct indices (i, j) in the given list,
 * so that the concatenation of the two words, i.e. words[i] + words[j] is a palindrome.
 *
 * Example 1: Given words = ["bat", "tab", "cat"]
 * Return [[0, 1], [1, 0]]
 * The palindromes are ["battab", "tabbat"]
 *
 * Example 2: Given words = ["abcd", "dcba", "lls", "s", "sssll"]
 * Return [[0, 1], [1, 0], [3, 2], [2, 4]]
 * The palindromes are ["dcbaabcd", "abcddcba", "slls", "llssssll"]

 * Created by WinnieZhao on 2017/5/25.
 */
public class PalindromePairs {

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> result = new ArrayList<>();

        if (words == null || words.length < 2) return result;

        // word and index mapping
        Map<String, Integer> map = new HashMap<>();

        for (int i=0; i<words.length; i++) {
            map.put(words[i], i);
        }

        for (int i=0; i<words.length; i++) {
            for (int j=0; j<=words[i].length(); j++) { // notice it should be "j <= words[i].length()"
                String left = words[i].substring(0, j);
                String right = words[i].substring(j);

                if (isPalindrome(left)) {
                    String rightReverse = new StringBuilder(right).reverse().toString();

                    if (map.getOrDefault(rightReverse, i) != i) {
                        result.add(Arrays.asList(map.get(rightReverse), i));
                    }
                }
                if (isPalindrome(right) && right.length() != 0) {
                    String leftReverse = new StringBuilder(left).reverse().toString();
                    // check "str.length() != 0" to avoid duplicates

                    if (map.getOrDefault(leftReverse, i) != i) {
                        result.add(Arrays.asList(i, map.get(leftReverse)));
                    }
                }
            }
        }
        return result;
    }

    private boolean isPalindrome(String s) {
        int start=0;
        int end=s.length()-1;

        while (start<end) {
            if (s.charAt(start++) != s.charAt(end--)) {
                return false;
            }

        }

        return true;
    }

    public static void main(String[] args) {
        PalindromePairs solution = new PalindromePairs();

        String[] words = {"abcd", "dcba", "lls", "s", "sssll"};

        System.out.println(solution.palindromePairs(words));
    }
}
