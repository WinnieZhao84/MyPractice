package LeetCode.Interview.LinkedIn.LeetCode;

import java.util.*;

/**
 * 244
 *
 * This is a follow up of Shortest Word Distance. The only difference is now you are given the list of words and
 * your method will be called repeatedly many times with different parameters. How would you optimize it?
 *
 * Design a class which receives a list of words in the constructor, and implements a method that takes
 * two words word1 and word2 and return the shortest distance between these two words in the list.
 *
 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.

 * Created by WinnieZhao on 2017/3/23.
 */
public class ShortestWordDistanceII {

    Map<String, List<Integer>> cache = new HashMap<>();

    public ShortestWordDistanceII(String[] words) {
        for(int i =0; i< words.length; i++) {
            List<Integer> list = cache.getOrDefault(words[i], new ArrayList<>());
            list.add(i);

            cache.put(words[i], list);
        }
    }

    // Your WordDistance object will be instantiated and called as such:
    // WordDistance wordDistance = new WordDistance(words);
    // wordDistance.shortest("word1", "word2");
    // wordDistance.shortest("anotherWord1", "anotherWord2");

    public int shortest(String word1, String word2) {
        int shortest = Integer.MAX_VALUE;
        List<Integer> list1 = cache.get(word1);
        List<Integer> list2 = cache.get(word2);

        int i=0;
        int j=0;
        while (i<list1.size() && j<list2.size()) {
            shortest = Math.min(shortest, Math.abs(list1.get(i) - list2.get(j)));
            if (list1.get(i) > list2.get(j)) {
                j++;
            }
            else {
                i++;
            }
        }

        return shortest;
    }
}
