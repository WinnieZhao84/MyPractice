package LeetCode.Easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s and a non-empty string p, find all the start indices of p's anagrams in s.

Strings consists of lowercase English letters only and the length of both strings s and p will not be larger than 20,100.

The order of output does not matter.

Example 1:

Input:
s: "cbaebabacd" p: "abc"

Output:
[0, 6]

Explanation:
The substring with start index = 0 is "cba", which is an anagram of "abc".
The substring with start index = 6 is "bac", which is an anagram of "abc".
Example 2:

Input:
s: "abab" p: "ab"

Output:
[0, 1, 2]

Explanation:
The substring with start index = 0 is "ab", which is an anagram of "ab".
The substring with start index = 1 is "ba", which is an anagram of "ab".
The substring with start index = 2 is "ab", which is an anagram of "ab".

 * @author WinnieZhao
 *
 */
public class FindAllAnagramsInAString {

    public List<Integer> findAnagrams_better(String s, String p) {
        List<Integer> res = new ArrayList<>();

        if (s== null || p == null || s.isEmpty() || p.isEmpty()) {
            return res;
        }

        int[] hash = new int[26];
        for (int i=0; i<p.length(); i++) {
            hash[p.charAt(i) - 'a']++;
        }

        int count = p.length();
        int left = 0;
        int right = 0;
        while (right < s.length()) {

            if (hash[s.charAt(right++) - 'a']-- >= 1) {
                count--;
            }

            if (count == 0) {
                res.add(left);
            }

            if (right - left == p.length() &&  hash[s.charAt(left++) - 'a']++ >= 0) {
                count++;
            }
        }
        return res;
    }
    
    public static void main(String[] agrs) {
        FindAllAnagramsInAString solution = new FindAllAnagramsInAString();
        
        System.out.println(solution.findAnagrams_better("bbacbabacd", "abc"));
    }
}
