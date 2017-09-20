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
     * Time Complexity: O(N * K)O(N∗K), where NN is the length of strs, and KK is the maximum length of a string in strs.
     * Counting each string is linear in the size of the string, and we count every string.
     *
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams_better(String[] strs) {
        if (strs.length == 0) return new ArrayList();
        Map<String, List> ans = new HashMap<>();
        int[] count = new int[26];
        for (String s : strs) {
            Arrays.fill(count, 0);
            for (char c : s.toCharArray()) {
                count[c - 'a']++;
            }

            StringBuilder sb = new StringBuilder("");
            for (int i = 0; i < 26; i++) {
                sb.append('#');
                sb.append(count[i]);
            }

            String key = sb.toString();
            if (!ans.containsKey(key)) {
                ans.put(key, new ArrayList());
            }
            ans.get(key).add(s);
        }

        return new ArrayList(ans.values());
    }

    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();
        
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        
        List<List<String>> result = solution.groupAnagrams(strs);
        System.out.println(result);
    }
}
