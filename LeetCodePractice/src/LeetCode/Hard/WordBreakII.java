package LeetCode.Hard;

import java.util.*;

/**
 *
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words,
 * add spaces in s to construct a sentence where each word is a valid dictionary word.
 * You may assume the dictionary does not contain duplicate words.
 *
 * Return all such possible sentences.
 *
 * For example, given
 * s = "catsanddog",
 * dict = ["cat", "cats", "and", "sand", "dog"].
 * A solution is ["cats and dog", "cat sand dog"].

 * Created by WinnieZhao on 2017/5/17.
 */
public class WordBreakII {

    public List<String> wordBreak(String s, List<String> wordDict) {
        List<String> result = new ArrayList<>();

        if (s == null || s.isEmpty() || wordDict == null || wordDict.isEmpty()) {
            return result;
        }

        int length = s.length();
        boolean[] dp = new boolean[length+1];
        dp[0] = true;

        for (int i=1; i<length; i++) {
            for (int j=0; j<i; j++) {
                if (dp[j] && wordDict.contains(s.substring(j, i))) {
                    dp[i] = true;
                }
            }
        }

        HashMap<String, List<String>> memo = new HashMap<>();

        return this.helper(s, wordDict, memo, dp);
    }

    private List<String> helper(String s, List<String> wordDict, HashMap<String, List<String>> map, boolean[] dp) {
        if (map.containsKey(s)) {
            return map.get(s);
        }
        List<String> res = new ArrayList<>();
        if (s.length() == 0) {
            return res;
        }
        if (wordDict.contains(s)) {
            res.add(s);
        }
        int end = s.length();
        for (int i = end - 1; i >= 0; i--) {
            String cur = s.substring(i, end);

            if (wordDict.contains(cur) && dp[i]) {

                List<String> list = this.helper(s.substring(0, i), wordDict, map, dp);

                for (String str : list) {
                    res.add(str + " " + cur);
                }
            }
        }
        map.put(s, res);
        return res;
    }

    public class Solution {
        public List<String> wordBreak(String s, Set<String> wordDict) {
            return DFS(s, wordDict, new HashMap<>());
        }

        // DFS function returns an array including all substrings derived from s.
        private List<String> DFS(String s, Set<String> wordDict, HashMap<String, LinkedList<String>>map) {
            if (map.containsKey(s))
                return map.get(s);

            LinkedList<String>res = new LinkedList<>();
            if (s.length() == 0) {
                res.add("");
                return res;
            }
            for (String word : wordDict) {
                if (s.startsWith(word)) {
                    List<String>sublist = DFS(s.substring(word.length()), wordDict, map);
                    for (String sub : sublist)
                        res.add(word + (sub.isEmpty() ? "" : " ") + sub);
                }
            }
            map.put(s, res);
            return res;
        }
    }

    public static void main(String[] args) {
        WordBreakII solution = new WordBreakII();
        List<String> wordDict = Arrays.asList("cat", "cats", "and", "sand", "dog");

        System.out.println(solution.wordBreak("catsanddog", wordDict));
    }

}
