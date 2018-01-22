package LeetCode.Medium;

/**
 * Design a data structure that supports the following two operations:
 *
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or ..
 * A . means it can represent any one letter.
 *
 * For example:
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 *
 * Note: You may assume that all words are consist of lowercase letters a-z.

 * Created by WinnieZhao on 11/27/2017.
 */
public class AddAndSearchWordDataStructureDesign {

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = "";
    }

    TrieNode root = null;

    /** Initialize your data structure here. */
    public AddAndSearchWordDataStructureDesign() {
        root = new TrieNode();
    }

    /** Adds a word into the data structure. */
    public void addWord(String word) {
        insertWord(word);
    }

    /** Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter. */
    public boolean search(String word) {
        if (word == null || word.isEmpty()) {
            return false;
        }
        return this.search(word.toCharArray(), root, 0);
    }

    private void insertWord(String word) {
        char[] chars = word.toCharArray();
        TrieNode node = root;
        for (char ch : chars) {
            if (node.children[ch - 'a'] == null) {
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        node.word = word;
    }

    private boolean search(char[] chs, TrieNode node, int pos) {
        if (pos == chs.length) {
            return !node.word.equals("");
        }

        if (chs[pos] == '.') {
            for (TrieNode next : node.children) {
                if (next == null) {
                    continue;
                }
                if (this.search(chs, next, pos+1)) {
                    return true;
                }
            }
        }
        else {
            return node.children[chs[pos] - 'a'] != null && this.search(chs, node.children[chs[pos] - 'a'], pos+1);
        }

        return false;
    }

    public static void main(String [] args) {
        AddAndSearchWordDataStructureDesign solution = new AddAndSearchWordDataStructureDesign();

        solution.addWord("ran");
        solution.addWord("rune");
        solution.addWord("runner");
        solution.addWord("runs");
        solution.addWord("add");
        solution.addWord("adds");
        solution.addWord("adder");
        solution.addWord("addee");
        System.out.println(solution.search("....e."));
    }
}
