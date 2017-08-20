package LeetCode.Medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

/**
 * Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation
 * sequence from beginWord to endWord, such that:
 * 
 * Only one letter can be changed at a time.
 * 
 * Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
 * 
 * For example,
 * Given: beginWord = "hit" endWord = "cog"
 * wordList = ["hot","dot","dog","lot","log","cog"]
 * 
 * As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog", return its length 5.
 * 
 * Note:
 * Return 0 if there is no such transformation sequence.
 * All words have the same length.
 * All words contain only lowercase alphabetic characters.
 * You may assume no duplicates in the word list.
 * You may assume beginWord and endWord are non-empty and are not the same.
 * 
 * UPDATE (2017/1/20): The wordList parameter had been changed to a list of strings (instead of a set of strings).
 * Please reload the code definition to get the latest changes.

 * @author WinnieZhao
 *
 */
public class WordLadder {

    // BFS, Time Limit Exceed
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
         if (!wordList.contains(endWord)) return 0;
         
         Queue<String> queue = new LinkedList<String>();
         queue.add(beginWord);
         
         Set<String> visited = new HashSet<String>();
         int length = 1;
         while(!queue.isEmpty()) {

             int size = queue.size();
             for (int i=0; i<size; i++) {
                 String word = queue.poll();
                 
                 if (word.equals(endWord)) {
                     return length;
                 }
                 
                 for (String target : wordList) {

                     if (!visited.contains(target) && this.isValid(word, target)) {
                         queue.add(target);
                         visited.add(target);
                     }

                 }
             }
             length++;

         }
         return 0;
        
    }
    
    private boolean isValid(String word, String target) {
        int diff = 0;
        for (int i = 0; i < word.length(); i++) {
            if (word.charAt(i) != target.charAt(i)) diff++;
            if (diff > 1) return false;
        }
        return true;
    }
    
    public static void main(String[] args) {
        WordLadder solution = new WordLadder();
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        System.out.println(solution.ladderLength(beginWord, endWord, Arrays.asList(wordList)));
    }
}
