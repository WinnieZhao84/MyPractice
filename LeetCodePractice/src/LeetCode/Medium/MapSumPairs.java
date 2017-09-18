package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Implement a MapSum class with insert, and sum methods.
 *
 * For the method insert, you'll be given a pair of (string, integer).
 * The string represents the key and the integer represents the value.
 * If the key already existed, then the original key-value pair will be overridden to the new one.
 *
 * For the method sum, you'll be given a string representing the prefix,
 * and you need to return the sum of all the pairs' value whose key starts with the prefix.
 *
 * Example 1:
 * Input: insert("apple", 3), Output: Null
 * Input: sum("ap"), Output: 3
 * Input: insert("app", 2), Output: Null
 * Input: sum("ap"), Output: 5
 *
 * Created by WinnieZhao on 9/18/2017.
 */
public class MapSumPairs {

    /**
     * Your MapSum object will be instantiated and called as such:
     * MapSum obj = new MapSum();
     * obj.insert(key,val);
     * int param_2 = obj.sum(prefix);
     */
    /** Initialize your data structure here. */

    Map<String, Integer> map = null;
    Map<String, Integer> score = null;
    public MapSumPairs() {
        map = new HashMap<>();
        score = new HashMap<>();
    }

    public void insert(String key, int val) {
        int delta = val - map.getOrDefault(key, 0);

        map.put(key, val);

        String prefix = "";
        for (char ch : key.toCharArray()) {
            prefix += ch;
            score.put(prefix, score.getOrDefault(prefix, 0) + delta);
        }
    }

    public int sum(String prefix) {
        return score.getOrDefault(prefix, 0);
    }
}
