package LeetCode.Interview.Microsoft.LeetCode;

import java.util.*;

/**
 * 358
 *
 * Given a non-empty string str and an integer k, rearrange the string such that the same characters are at least distance k from each other.
 *
 * All input strings are given in lowercase letters. If it is not possible to rearrange the string, return an empty string "".
 *
 * Example 1:
 * str = "aabbcc", k = 3
 * Result: "abcabc"
 * The same letters are at least distance 3 from each other.
 *
 * Example 2:
 * str = "aaabc", k = 3
 * Answer: ""
 * It is not possible to rearrange the string.
 *
 * Example 3:
 * str = "aaadbbcc", k = 2
 * Answer: "abacabcd"
 * Another possible answer is: "abcabcda"
 * The same letters are at least distance 2 from each other.
 *
 * Created by WinnieZhao on 2/25/2018.
 */
public class RearrangeStringKDistanceApart {

    public String rearrangeString(String str, int k) {

        if (str == null || str.length() == 0 || k <= 0) {
            return str;
        }

        int len = str.length();

        Map<Character, Integer> map = new HashMap<>();

        for (char ch : str.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }

        PriorityQueue<Character> pq = new PriorityQueue<>((Character a, Character b) -> {
            if (map.get(a).intValue() == map.get(b).intValue()) {
                return a.compareTo(b);
            }
            else {
                return map.get(b).intValue() - map.get(a).intValue();
            }
        });

        for (Character ch : map.keySet()) {
            pq.offer(ch);
        }

        StringBuilder sb = new StringBuilder();

        while (!pq.isEmpty()) {
            int cnt = Math.min(k, len);

            List<Character> chs = new ArrayList<>();

            for (int i=0; i<cnt; i++) {
                if(pq.isEmpty()) {
                    return "";
                }
                char cur = pq.poll();
                sb.append(String.valueOf(cur));

                map.put(cur, map.get(cur)-1);

                if (map.get(cur) > 0) {
                    chs.add(cur);
                }

                len--;
            }

            for(char c : chs) {
                pq.offer(c);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        RearrangeStringKDistanceApart solution = new RearrangeStringKDistanceApart();

        System.out.println(solution.rearrangeString("aabbcc", 3));
        System.out.println(solution.rearrangeString("aaadbbcc", 2));
        System.out.println(solution.rearrangeString("aaabc", 3));
    }
}
