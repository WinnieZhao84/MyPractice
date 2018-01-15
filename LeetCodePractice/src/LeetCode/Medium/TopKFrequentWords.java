package LeetCode.Medium;

import java.util.*;

/**
 * Given a non-empty list of words, return the k most frequent elements.
 *
 * Your answer should be sorted by frequency from highest to lowest.
 * If two words have the same frequency, then the word with the lower alphabetical order comes first.
 *
 * Example 1:
 * Input: ["i", "love", "leetcode", "i", "love", "coding"], k = 2
 * Output: ["i", "love"]
 * Explanation: "i" and "love" are the two most frequent words.
 * Note that "i" comes before "love" due to a lower alphabetical order.
 *
 * Example 2:
 * Input: ["the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"], k = 4
 * Output: ["the", "is", "sunny", "day"]
 * Explanation: "the", "is", "sunny" and "day" are the four most frequent words,
 * with the number of occurrence being 4, 3, 2 and 1 respectively.
 *
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ number of unique elements.
 * Input words contain only lowercase letters.
 *
 * Follow up: Try to solve it in O(n log k) time and O(n) extra space.

 * Created by WinnieZhao on 10/21/2017.
 */
public class TopKFrequentWords {

    public List<String> topKFrequent(String[] words, int k) {

        List<String> result = new LinkedList<>();
        Map<String, Integer> frequencies = new HashMap<>();

        for (String word : words) {
            frequencies.put(word, frequencies.getOrDefault(word, 0)+1);
        }

        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a,b) -> a.getValue() == b.getValue() ? b.getKey().compareTo(a.getKey()) : a.getValue() - b.getValue());

        for (Map.Entry<String, Integer> entry : frequencies.entrySet()) {
            pq.offer(entry);
            if (pq.size() > k) {
                pq.poll();
            }
        }

        while (!pq.isEmpty()) {
            result.add(0, pq.poll().getKey());
        }

        return result;
    }

    public List<String> topKFrequent_better(String[] words, int k) {
        List<String> result = new LinkedList<>();
        Map<String, Integer> frequencies = new HashMap<>();

        for (String word : words) {
            frequencies.put(word, frequencies.getOrDefault(word, 0)+1);
        }

        List<String>[] buckets = new ArrayList[words.length];

        for (String word : frequencies.keySet()) {
            Integer frequency = frequencies.get(word);

            if (buckets[frequency] == null) {
                buckets[frequency] = new ArrayList<>();
            }
            buckets[frequency].add(word);
        }

        Queue<String> res = new LinkedList<>();
        for (int i=buckets.length-1; i>=0 && result.size() < k; i--) {
            if (buckets[i] != null) {
                List<String> list = buckets[i];

                list.sort(String::compareTo);
                res.addAll(list);
            }
        }

        while(result.size() < k) {
            result.add(res.poll());
        }
        return result;
    }

    public static void main(String[] args) {
        TopKFrequentWords solution = new TopKFrequentWords();

        System.out.println(solution.topKFrequent_better(new String[] {
                "the", "day", "is", "sunny", "the", "the", "the", "sunny", "is", "is"}, 4));

        System.out.println(solution.topKFrequent_better(new String[] {
                "i", "love", "leetcode", "i", "love", "coding"}, 1));
    }
}
