package LeetCode.Interview.Microsoft.LeetCode;

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
 */
public class ShortestWordDistanceIII {

    public int shortestWordDistance(String[] words, String word1, String word2) {
        if (words == null || words.length == 0) {
            return 0;
        }

        int index1 = -1;
        int index2 = -1;

        int shortest = Integer.MAX_VALUE;
        for (int i=0; i<words.length; i++) {

            if (words[i].equalsIgnoreCase(word1)) {
                index1 = i;

                if (index1>=0 && index2>=0) {
                    shortest = index1 == index2 ? shortest : Math.min(shortest, Math.abs(index1-index2));
                }
            }

            if (words[i].equalsIgnoreCase(word2)) {
                index2 = i;

                if (index1>=0 && index2>=0) {
                    shortest = index1 == index2 ? shortest : Math.min(shortest, Math.abs(index1-index2));
                }
            }
        }

        return shortest;
    }

    public static void main(String[] args) {
        ShortestWordDistanceIII solution = new ShortestWordDistanceIII();

        String[] words = new String[] {"practice", "makes", "perfect", "coding", "makes"};

        System.out.println(solution.shortestWordDistance(words, "makes", "coding"));
        System.out.println(solution.shortestWordDistance(words, "makes", "makes"));
    }

}
