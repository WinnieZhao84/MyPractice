package LeetCode.Medium;

/**
 * Implement a trie with insert, search, and startsWith methods.
 * 
 * Note:
 * 
 * You may assume that all inputs are consist of lowercase letters a-z.

 * @author WinnieZhao
 *
 */
public class ImplementTrie {

    class TrieNode {
        public char ch;
        public boolean isWord; 
        public TrieNode[] children = new TrieNode[26];
        public TrieNode(char ch) {
            this.ch = ch;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */
    private TrieNode root;
    
    /** Initialize your data structure here. */
    public ImplementTrie() {
        root = new TrieNode(' ');
    }
    
    /** Inserts a word into the trie. */
    public void insert(String word) {
        if (word == null || word.length() == 0) {
            return;
        }
        
        TrieNode ws = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if (ws.children[ch - 'a'] == null) {
                ws.children[ch - 'a'] = new TrieNode(ch);
            }
            ws = ws.children[ch - 'a'];
        }
        ws.isWord = true;
    }
    
    /** Returns if the word is in the trie. */
    public boolean search(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }
        
        TrieNode ws = root;
        for (int i=0; i<word.length(); i++) {
            char ch = word.charAt(i);
            if (ws.children[ch - 'a'] == null) {
                return false;
            }
            ws = ws.children[ch - 'a'];
        }
        return ws.isWord;
    }
    
    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        TrieNode ws = root; 
        for(int i = 0; i < prefix.length(); i++){
            char c = prefix.charAt(i);
            if(ws.children[c - 'a'] == null) return false;
            ws = ws.children[c - 'a'];
        }
        return true;
        
    }
}
