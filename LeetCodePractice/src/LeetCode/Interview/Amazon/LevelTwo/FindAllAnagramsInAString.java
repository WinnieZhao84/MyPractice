package LeetCode.Interview.Amazon.LevelTwo;

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

        if (s == null || s.length() == 0 || p == null || p.length() == 0) {
            return res;
        }

        int[] count = new int[26];
        for (int i=0; i<p.length(); i++) {
            char ch = p.charAt(i);
            count[ch - 'a']++;
        }

        int start = 0;
        int end = 0;
        int length = p.length();

        while (end < s.length()) {
            char ch = s.charAt(end++);

            if (count[ch - 'a']-- >= 1) {
                length--;
            }

            if (length == 0) {
                res.add(start);
            }

            if (end-start == p.length() && count[s.charAt(start++) - 'a']++ >=0) {
                length++;
            }
        }

        return res;
    }
    
    public static void main(String[] args) {
        FindAllAnagramsInAString solution = new FindAllAnagramsInAString();
        
        System.out.println(solution.findAnagrams_better("cbaebabacd", "abc"));
    }
}
