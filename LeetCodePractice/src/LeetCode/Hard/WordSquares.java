package LeetCode.Hard;

import java.util.ArrayList;
import java.util.List;

/**
 * 425
 *
 * Given a set of words (without duplicates), find all word squares you can build from them.
 * A sequence of words forms a valid word square if the kth row and column read the exact same string,
 * where 0 ≤ k < max(numRows, numColumns).
 *
 * For example, the word sequence ["ball","area","lead","lady"] forms a word square because each word reads
 * the same both horizontally and vertically.
 *
 * b a l l
 * a r e a
 * l e a d
 * l a d y
 *
 * Note:
 * There are at least 1 and at most 1000 words.
 * All words will have the exact same length.
 * Word length is at least 1 and at most 5.
 * Each word contains only lowercase English alphabet a-z.
 *
 * Example 1:
 * Input: ["area","lead","wall","lady","ball"]
 * Output:
 * [
 *   [ "wall",
 *     "area",
 *     "lead",
 *     "lady"
 *   ],
 *   [ "ball",
 *     "area",
 *     "lead",
 *     "lady"
 *   ]
 * ]
 *
 * Explanation:
 * The output consists of two word squares. The order of output does not matter
 * (just the order of words in each word square matters).
 *
 * Example 2:
 * Input: ["abat","baba","atan","atal"]
 * Output:
 * [
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atan"
 *   ],
 *   [ "baba",
 *     "abat",
 *     "baba",
 *     "atal"
 *   ]
 * ]
 *
 * Explanation: The output consists of two word squares. The order of output does not matter
 * (just the order of words in each word square matters).

 * Created by WinnieZhao on 4/20/2017.
 */
public class WordSquares {

    class TrieNode {
        List<String> startWith;
        TrieNode[] children;

        TrieNode() {
            startWith = new ArrayList<>();
            children = new TrieNode[26];
        }
    }

    class Trie {
        TrieNode root;

        Trie(String[] words) {
            root = new TrieNode();

            for (String word : words) {
                TrieNode cur = root;
                for (char ch : word.toCharArray()) {
                    int idx = ch - 'a';

                    if (cur.children[idx] == null) {
                        cur.children[idx] = new TrieNode();
                    }

                    cur.children[idx].startWith.add(word);
                    cur = cur.children[idx];
                }
            }
        }

        List<String> findByPrefix(String prefix) {
            List<String> ans = new ArrayList<>();
            TrieNode cur = root;
            for (char ch : prefix.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null) {
                    return ans;
                }

                cur = cur.children[idx];
            }
            ans.addAll(cur.startWith);
            return ans;
        }
    }

    /**
     * A better approach is to check the validity of the word square while we build it
     * Example: ["area","lead","wall","lady","ball"]
     * We know that the sequence contains 4 words because the length of each word is 4.
     * Every word can be the first word of the sequence, let's take "wall" for example.
     *
     * Which word could be the second word? Must be a word start with "a" (therefore "area"),
     * because it has to match the second letter of word "wall".
     *
     * Which word could be the third word? Must be a word start with "le" (therefore "lead"),
     * because it has to match the third letter of word "wall" and the third letter of word "area".
     * What about the last word? Must be a word start with "lad" (therefore "lady")

     * @param len
     * @param tr
     * @param result
     * @param list
     */
    private void search(int len, Trie tr, List<List<String>> result, List<String> list) {
        if (list.size() == len) {
            result.add(new ArrayList<>(list));
            return;
        }

        int idx = list.size();
        StringBuilder prefixBuilder = new StringBuilder();

        for (String s : list) {
            prefixBuilder.append(s.charAt(idx));
        }

        List<String> startWith = tr.findByPrefix(prefixBuilder.toString());

        for (String sw : startWith) {
            list.add(sw);
            search(len, tr, result , list);
            list.remove(list.size() - 1);
        }
    }

    public List<List<String>> wordSquares(String[] words) {
        List<List<String>> res = new ArrayList<>();

        if (words == null || words.length == 0) return res;
        int len = words[0].length();
        Trie trie = new Trie(words);

        List<String> list = new ArrayList<>();
        for (String w : words) {
            list.add(w);

            this.search(len, trie, res, list);

            list.remove(list.size() - 1);
        }

        return res;
    }

    public static void main(String[] args) {
        WordSquares solution = new WordSquares();

        String[] word = {"wall", "area","lead","lady","ball"};
        solution.wordSquares(word).forEach(list -> System.out.println(list.toString()));
    }
}
