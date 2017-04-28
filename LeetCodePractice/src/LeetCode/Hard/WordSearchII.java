package LeetCode.Hard;

import LeetCode.Helper.TreeNode;

import java.util.*;

/**
 *
 * Given a 2D board and a list of words from the dictionary, find all words in the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring.
 * The same letter cell may not be used more than once in a word.
 *
 * For example,
 * Given words = ["oath","pea","eat","rain"] and board =
 * [
 *   ['o','a','a','n'],
 *   ['e','t','a','e'],
 *   ['i','h','k','r'],
 *   ['i','f','l','v']
 * ]
 *
 * Return ["eat","oath"].
 *
 * Note: You may assume that all inputs are consist of lowercase letters a-z.
 *
 * You would need to optimize your backtracking to pass the larger test. Could you stop backtracking earlier?
 *
 * If the current candidate does not exist in all words' prefix, you could stop backtracking immediately.
 * What kind of data structure could answer such query efficiently? Does a hash table work? Why or why not?
 * How about a Trie? If you would like to learn how to implement a basic trie, please work on this problem: Implement Trie (Prefix Tree).

 * Created by WinnieZhao on 2017/4/27.
 */
public class WordSearchII {

    Set<String> result = new HashSet<>();
    boolean[][] visited;
    private Trie dict = new Trie();

    public List<String> findWords(char[][] board, String[] words) {

        if (board == null || board.length == 0 || board[0].length == 0) return new ArrayList<String>();

        for (String word : words) {
            dict.insert(word);
        }

        int m = board.length;
        int n = board[0].length;
        visited = new boolean[m][n];

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                this.dfs(board, i, j, "");
            }
        }

        return new ArrayList<>(result);

    }


    private void dfs(char[][]board, int i, int j, String current) {

        if (i<0 || i>board.length-1 || j<0 || j>board[0].length-1 || visited[i][j]) {
            return;
        }

        current = current + board[i][j];

        if (!dict.startsWith(current)) return;
        if (dict.search(current)) {
            result.add(current);
        }

        visited[i][j] = true;

        this.dfs(board,i-1, j, current);
        this.dfs(board,i+1, j, current);
        this.dfs(board,  i, j-1, current);
        this.dfs(board, i, j+1, current);

        visited[i][j] = false;

        return;
    }

    class TrieNode {
        // Initialize your data structure here.
        TrieNode[] children = new TrieNode[26];
        String word = "";

        public TrieNode() {}
    }

    class Trie {
        private TrieNode root;

        public Trie() {
            root = new TrieNode();
        }

        // Inserts a word into the trie.
        public void insert(String word) {
            TrieNode node = root;

            for (char ch : word.toCharArray()) {

                if (node.children[ch - 'a'] == null) {
                    node.children[ch - 'a'] = new TrieNode();
                }
                node = node.children[ch - 'a'];
            }
            node.word = word;

        }

        // Returns if the word is in the trie.
        public boolean search(String word) {
            TrieNode node = root;
            for (char ch : word.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    return false;
                }
                node = node.children[ch - 'a'];
            }
            return node.word.equals(word);
        }

        // Returns if there is any word in the trie
        // that starts with the given prefix.
        public boolean startsWith(String prefix) {
            TrieNode node = root;
            for(char ch : prefix.toCharArray()) {
                if (node.children[ch - 'a'] == null) {
                    return false;
                }
                node = node.children[ch - 'a'];
            }
            return true;
        }
    }

}
