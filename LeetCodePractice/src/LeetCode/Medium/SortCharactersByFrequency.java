package LeetCode.Medium;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Given a string, sort it in decreasing order based on the frequency of characters.
 * 
 * Example 1:
 * Input: "tree"
 * Output: "eert"
 * 
 * Explanation:
 * 'e' appears twice while 'r' and 't' both appear once. 
 * So 'e' must appear before both 'r' and 't'. Therefore "eetr" is also a valid answer.
 * 
 * Example 2:
 * Input: "cccaaa"
 * Output: "cccaaa"
 * 
 * Explanation:
 * Both 'c' and 'a' appear three times, so "aaaccc" is also a valid answer.
 * Note that "cacaca" is incorrect, as the same characters must be together.
 * 
 * Example 3:
 * Input: "Aabb"
 * Output: "bbAa"
 * 
 * Explanation: 
 * "bbaA" is also a valid answer, but "Aabb" is incorrect.
 * Note that 'A' and 'a' are treated as two different characters.
 * 
 * @author WinnieZhao
 *
 */
public class SortCharactersByFrequency {

    public String frequencySort(String s) {
        if (s == null || s.length() <=1 ) {
            return s;
        }

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : s.toCharArray()) {
            Integer count = map.getOrDefault(ch, 0);
            count++;
            map.put(ch, count);
        }

        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());

        pq.addAll(map.entrySet());

        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();

            for (int i=0; i<entry.getValue(); i++) {
                sb.append(entry.getKey());
            }

        }

        return sb.toString();
    }
}
