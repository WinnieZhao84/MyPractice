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

    // BFS
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) {
            return 0;
        }
         
         Queue<String> queue = new LinkedList<String>();
         queue.add(beginWord);
         
         Set<String> visited = new HashSet<String>();
         int step = 1;
         while(!queue.isEmpty()) {

             int size = queue.size();
             for (int i=0; i<size; i++) {
                 String word = queue.poll();
                 
                 char[] chs = word.toCharArray();
                 
                 for (int j=0; j<chs.length; j++) {
                     char old = chs[j];
                     for (char c = 'a'; c<='z'; c++) {
                         
                         chs[j] = c;
                         
                         String s = String.valueOf(chs);
                         if (endWord.equals(s)) {
                             return step+1;
                         }
                         if (!visited.contains(s) && wordSet.contains(s)) {
                             queue.add(s);
                             visited.add(s);
                         }
                         chs[j] = old;
                     }
                 }
             }
             step++;

         }
         return 0;
    }

    /**
     * Two ended BFS solution
     *
     * @param beginWord
     * @param endWord
     * @param wordList
     * @return
     */
    public int ladderLength_better(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;

        Set<String> wordSet = new HashSet<>(wordList);
        Set<String> beginSet = new HashSet<>();
        Set<String> endSet = new HashSet<>();

        int len = 1;
        HashSet<String> visited = new HashSet<>();

        beginSet.add(beginWord);
        endSet.add(endWord);

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet = temp;
            }

            Set<String> set = new HashSet<>();
            for (String word : beginSet) {
                char[] chs = word.toCharArray();

                for (int i = 0; i < chs.length; i++) {
                    char old = chs[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        chs[i] = c;
                        String target = String.valueOf(chs);

                        if (endSet.contains(target)) {
                            return len + 1;
                        }

                        if (!visited.contains(target) && wordSet.contains(target)) {
                            set.add(target);
                            visited.add(target);
                        }
                        chs[i] = old;
                    }
                }
            }

            beginSet = set;
            len++;
        }

        return 0;
    }
    
    public static void main(String[] args) {
        WordLadder solution = new WordLadder();
        String beginWord = "hit";
        String endWord = "cog";
        String[] wordList = {"hot","dot","dog","lot","log","cog"};
        System.out.println(solution.ladderLength(beginWord, endWord, Arrays.asList(wordList)));
    }
}
