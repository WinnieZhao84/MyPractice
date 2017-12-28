package LeetCode.Medium;

import java.util.*;

/**
 * Given an array of strings, group anagrams together.
 * 
 * For example, given: ["eat", "tea", "tan", "ate", "nat", "bat"], 
 * Return:
 * [
 *   ["ate", "eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 * 
 * Note: All inputs will be in lower-case.
 * 
 * @author WinnieZhao
 *
 */
public class GroupAnagrams {

    // O(N * KlogK). N is the number of string in strs array and K is the maximum length of string in the strs array
    public List<List<String>> groupAnagrams(String[] strs) {
        
        if(strs==null || strs.length == 0){
            return new ArrayList<>();
        }
        Map<String, List<String>> map = new HashMap<String, List<String>>();

        for (String s:strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            
            if(!map.containsKey(keyStr)) {
                map.put(keyStr, new ArrayList<>());
            }
            map.get(keyStr).add(s);
        }
        
        for(String key: map.keySet()) {
            Collections.sort(map.get(key));
        }
        return new ArrayList<>(map.values());
    }

    /**
     * Time Complexity: O(N * K), where N is the length of strs, and K is the maximum length of a string in strs.
     * Counting each string is linear in the size of the string, and we count every string.
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams_better(String[] strs) {
        if (strs == null || strs.length == 0) {
            return new ArrayList<>();
        }

        int[] digits = new int[26];
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {

            char[] chars = str.toCharArray();
            digits = new int[26];

            for (char ch : chars) {
                digits[ch - 'a']++;
            }

            StringBuilder sb = new StringBuilder();
            for (int i=0; i<26; i++) {
                sb.append('#');
                sb.append(digits[i]);
            }

            String key = sb.toString();
            if (!map.containsKey(key)) {
                map.put(key, new ArrayList<>());
            }
            map.get(key).add(str);

        }

        return new ArrayList(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();
        
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        
        List<List<String>> result = solution.groupAnagrams_better(strs);
        System.out.println(result);
    }
}
