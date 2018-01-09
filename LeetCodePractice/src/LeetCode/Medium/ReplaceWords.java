package LeetCode.Medium;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In English, we have a concept called root, which can be followed by some other words to form another longer word -
 * let's call this word successor. For example, the root an, followed by other, which can form another word another.
 *
 * Now, given a dictionary consisting of many roots and a sentence. You need to replace all the successor in the sentence
 * with the root forming it. If a successor has many roots can form it, replace it with the root with the shortest length.
 *
 * You need to output the sentence after the replacement.
 *
 * Example 1:
 * Input: dict = ["cat", "bat", "rat"]
 * sentence = "the cattle was rattled by the battery"
 * Output: "the cat was rat by the bat"
 *
 * Note:
 * The input will only have lower-case letters.
 * 1 <= dict words number <= 1000
 * 1 <= sentence words number <= 1000
 * 1 <= root length <= 100
 * 1 <= sentence words length <= 1000
 *
 * Created by WinnieZhao on 2017/7/23.
 */
public class ReplaceWords {

    public String replaceWords(List<String> dict, String sentence) {
        dict.sort(Comparator.comparing(String::length));

        String[] array = sentence.split(" ");
        StringBuilder res = new StringBuilder();

        for (int i=0; i<array.length; i++) {
            String str = array[i];

            Optional<String> optional = this.replace(str, dict);
            if (optional.isPresent()) {
                res.append(optional.get());
            }
            else {
                res.append(str);
            }
            if (i != array.length-1) {
                res.append(" ");
            }
        }

        return res.toString();
    }

    private Optional<String> replace (String s, List<String> dicts) {
        return  dicts.stream().filter(dict -> s.length() >= dict.length() && s.substring(0, dict.length()).equals(dict)).findFirst();
    }

    /**
     * The only modification to the standard Trie, is that we need a function getShortestPrefix
     * that returns the shortest prefix of the given word in the trie,
     * if the shortest prefix exists or return the original word. Once we have this, all we have
     * to do is iterate through the sentence and replace each word
     * with the getShortestPrefix(word) in the trie.
     */
    public String replaceWords_better(List<String> dict, String sentence) {
        TrieNode root = new TrieNode();
        for (String word : dict) {
            TrieNode cur = root;
            for (char letter: word.toCharArray()) {
                if (cur.children[letter - 'a'] == null)
                    cur.children[letter - 'a'] = new TrieNode();
                cur = cur.children[letter - 'a'];
            }
            cur.word = word;
        }

        StringBuilder ans = new StringBuilder();

        for (String word: sentence.split("\\s+")) {
            if (ans.length() > 0)
                ans.append(" ");

            TrieNode cur = root;
            for (char letter: word.toCharArray()) {
                if (cur.children[letter - 'a'] == null || cur.word != null)
                    break;
                cur = cur.children[letter - 'a'];
            }
            ans.append(cur.word != null ? cur.word : word);
        }
        return ans.toString();
    }


    class TrieNode {
        TrieNode[] children;
        String word;

        TrieNode() {
            children = new TrieNode[26];
        }
    }


    public static void main(String[] args) {
        ReplaceWords solution = new ReplaceWords();

        List<String> dict = new ArrayList<>();
        dict.add("cat");
        dict.add("bat");
        dict.add("rat");

        System.out.println(solution.replaceWords(dict, "the cattle was rattled by the battery"));
    }
}
