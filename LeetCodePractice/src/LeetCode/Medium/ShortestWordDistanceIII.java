package LeetCode.Medium;

/**
 * 245
 *
 * This is a follow up of Shortest Word Distance. The only difference is now word1 could be the same as word2
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 * word1 and word2 may be the same and they represent two individual words in the list.
 *
 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “makes”, word2 = “coding”, return 1. Given word1 = "makes", word2 = "makes", return 3.
 *
 * Created by WinnieZhao on 2017/3/23.
 */
public class ShortestWordDistanceIII {

    public int shortestWordDistance(String[] words, String word1, String word2) {
        int l=-1, r= -1;
        int shortest = Integer.MAX_VALUE;

        // if word1 == word2, then r is the last index, and l is current index;
        // if not equal, then l and r is for different word
        for (int i=0; i<words.length; i++) {
            if (word1.equals(words[i])) {
                l = i;

                if (l>=0 && r>=0) {
                    shortest = l == r ? shortest : Math.min(shortest, Math.abs(l-r));
                }
            }

            if (word2.equals(words[i])) {
                r = i;

                if (l>=0 && r>=0) {
                    shortest = l == r ? shortest : Math.min(shortest, Math.abs(l-r));
                }
            }
        }

        return shortest;

    }
}
