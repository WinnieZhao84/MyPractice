package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a non-empty string s and a dictionary wordDict containing a list of non-empty words, 
 * determine if s can be segmented into a space-separated sequence of one or more dictionary words. 
 * You may assume the dictionary does not contain duplicate words.
 * 
 * For example, given
 * s = "leetcode",
 * dict = ["leet", "code"].
 * Return true because "leetcode" can be segmented as "leet code".
 * 
 * @author WinnieZhao
 *
 */
public class WordBreak {

    public boolean wordBreak(String s, List<String> wordDict) {
        if (wordDict.isEmpty()) return false;
        
        boolean[] f = new boolean[s.length() + 1];
        f[0] = true;
        
        for(int i=1; i <= s.length(); i++){
            for(int j=0; j < i; j++){
                if(f[j] && wordDict.contains(s.substring(j, i))){
                    f[i] = true;
                    break;
                }
            }
        }
        
        return f[s.length()];
    }
    
    public static void main(String[] args) {
        WordBreak solution = new WordBreak();
        List<String> wordDict = new ArrayList<>();
        wordDict.add("leet");
        wordDict.add("code");
        
        System.out.println(solution.wordBreak("leetleet", wordDict));
    }
}
