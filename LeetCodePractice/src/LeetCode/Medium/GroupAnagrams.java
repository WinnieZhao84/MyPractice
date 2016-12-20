package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

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
            return new ArrayList<List<String>>();
        }
        HashMap<String, List<String>> map = new HashMap<String, List<String>>();

        for (String s:strs) {
            char[] ca = s.toCharArray();
            Arrays.sort(ca);
            String keyStr = String.valueOf(ca);
            
            if(!map.containsKey(keyStr)) {
                map.put(keyStr, new ArrayList<String>());
            }
            map.get(keyStr).add(s);
        }
        
        for(String key: map.keySet()) {
            Collections.sort(map.get(key));
        }
        return new ArrayList<List<String>>(map.values());
    }

    public static void main(String[] args) {
        GroupAnagrams solution = new GroupAnagrams();
        
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        
        List<List<String>> result = solution.groupAnagrams(strs);
        System.out.println(result);
    }
}
