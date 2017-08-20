package LeetCode.Medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Add and Search Word - Data structure design
 * 
 * Design a data structure that supports the following two operations:
 * 
 * void addWord(word)
 * bool search(word)
 * search(word) can search a literal word or a regular expression string containing only letters a-z or ..
 * A . means it can represent any one letter.
 * 
 * For example: 
 * 
 * addWord("bad")
 * addWord("dad")
 * addWord("mad")
 * search("pad") -> false
 * search("bad") -> true
 * search(".ad") -> true
 * search("b..") -> true
 * 
 * Note:You may assume that all words are consist of lowercase letters a-z.
 * 
 * @author WinnieZhao
 *
 */
public class WordDictionary {

    Map<Integer, Set<String>> words = new HashMap<Integer, Set<String>>();
    
    // Adds a word into the data structure.
    public void addWord(String word) {
        if (word != null) {
            if (words.containsKey(word.length())) {
                words.get(word.length()).add(word);
            }
            else {
                Set<String> set = new HashSet<>();
                set.add(word);
                words.put(word.length(), set);
            }
        }
    }

    // Returns if the word is in the data structure. A word could
    // contain the dot character '.' to represent any one letter.
    public boolean search(String word) {
        if (words == null || words.isEmpty() || word== null ||  words.get(word.length()) == null) {
            return false;
        }
        
        if (word.length() == 1 && word.charAt(0) == '.' ) {
            return words.containsKey(word.length());
        }
        
        for (String existingWord : words.get(word.length())) {
            if (this.exist(word, existingWord)) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean exist(String word, String existingWord) {
        int wordLength = word.length();
        int existingWordLength = existingWord.length();
        
        if (wordLength != existingWordLength) {
            return false;
        }
        
        for (int i=0; i<wordLength; i++) {
            if (word.charAt(i) == '.' || existingWord.charAt(i) == word.charAt(i)) {
                if (i == wordLength-1) {
                    return true;
                }
            }
            else {
                return false;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
        wordDictionary.addWord("word");
        System.out.println(wordDictionary.search("pattern"));
        
        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        System.out.println(wordDictionary.search("pad")); // false
        System.out.println(wordDictionary.search("bad")); // true
        System.out.println(wordDictionary.search(".ad")); // true
        System.out.println(wordDictionary.search("b..")); // true
    }
}

// Your WordDictionary object will be instantiated and called as such:
// WordDictionary wordDictionary = new WordDictionary();
// wordDictionary.addWord("word");
// wordDictionary.search("pattern");