package LeetCode.Interview.LinkedIn.LeetCode;

/**
 * 243
 *
 * Given a list of words and two words word1 and word2, return the shortest distance between these two words in the list.
 *
 * For example, Assume that words = ["practice", "makes", "perfect", "coding", "makes"].
 * Given word1 = “coding”, word2 = “practice”, return 3.
 * Given word1 = "makes", word2 = "coding", return 1.
 *
 * Created by WinnieZhao on 2017/3/23.
 */
public class ShortestWordDistance {

    public int shortestDistance(String[] words, String word1, String word2) {
        int l = -1, r= -1;
        int shortest = words.length;
        for(int i=0; i< words.length; i++){
            if(word1.equals(words[i])){
                l = i;
            }
            else if(word2.equals(words[i])){
                r = i;
            }
            if (l >=0 && r >=0){
                shortest = Math.min(shortest, Math.abs(l-r));
            }
        }
        return shortest;
    }
}
