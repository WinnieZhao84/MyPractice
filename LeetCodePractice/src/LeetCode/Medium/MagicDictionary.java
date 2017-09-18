package LeetCode.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implement a magic directory with buildDict, and search methods.
 *
 * For the method buildDict, you'll be given a list of non-repetitive words to build a dictionary.
 *
 * For the method search, you'll be given a word, and judge whether if you modify exactly one character into
 * another character in this word, the modified word is in the dictionary you just built.
 *
 * Example 1:
 * Input: buildDict(["hello", "leetcode"]), Output: Null
 * Input: search("hello"), Output: False
 * Input: search("hhllo"), Output: True
 * Input: search("hell"), Output: False
 * Input: search("leetcoded"), Output: False
 *
 * Note:
 * You may assume that all the inputs are consist of lowercase letters a-z.
 * For contest purpose, the test data is rather small by now. You could think about
 * highly efficient algorithm after the contest.
 * Please remember to RESET your class variables declared in class MagicDictionary,
 * as static/class variables are persisted across multiple test cases.
 * Please see here for more details.

 * Created by WinnieZhao on 9/17/2017.
 */
public class MagicDictionary {

    /**
     * Your MagicDictionary object will be instantiated and called as such:
     * MagicDictionary obj = new MagicDictionary();
     * obj.buildDict(dict);
     * boolean param_2 = obj.search(word);
     */

    Map<String, List<int[]>> dict = new HashMap<>();

    /** Initialize your data structure here. */
    public MagicDictionary() {
    }

    /** Build a dictionary through a list of words */
    public void buildDict(String[] dict) {
        for (String word : dict) {
            for (int i=0; i<word.length(); i++) {
                String key = word.substring(0, i) + word.substring(i+1);
                int[] pair = new int[] {i, word.charAt(i)};

                List<int[]> list = this.dict.getOrDefault(key, new ArrayList<>());
                list.add(pair);

                this.dict.put(key, list);
            }
        }
    }

    /** Returns if there is any word in the trie that equals to the given word after modifying exactly one character */
    public boolean search(String word) {
        for (int i=0; i<word.length(); i++) {
            String key = word.substring(0, i) + word.substring(i+1);

            if (this.dict.containsKey(key)) {
                for (int[] pair : this.dict.get(key)) {
                    if (pair[0] == i && pair[1] != word.charAt(i)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
