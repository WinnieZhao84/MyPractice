package LeetCode.Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, determine if they are isomorphic.
 * 
 * Two strings are isomorphic if the characters in s can be replaced to get t.
 * 
 * All occurrences of a character must be replaced with another character while preserving the order of characters. 
 * No two characters may map to the same character but a character may map to itself.
 * 
 * For example,
 * Given "egg", "add", return true.
 * Given "foo", "bar", return false.
 * Given "paper", "title", return true.
 * 
 * Note:
 * You may assume both s and t have the same length.

 * @author ASUS-PC
 *
 */
public class IsomorphicStrings {
    public boolean isIsomorphic(String s, String t) {
        Map<Character, Character> m = new HashMap<Character, Character>();
    	
    	int length = s.length();
    	
    	for (int i=0; i<length; i++) {
    		char sCh = s.charAt(i);
    		char tCh = t.charAt(i);
    		
    		if (m.containsKey(sCh) && m.get(sCh) != tCh) {
    			return false;
    		}
    		if (m.containsValue(tCh) && !m.containsKey(sCh)) {
    			return false;
    		}
    		m.put(sCh, tCh);
    	}
    	
    	return true;
    }
    
    public boolean isIsomorphic_better(String sString, String tString) {

        char[] s = sString.toCharArray();
        char[] t = tString.toCharArray();

        int length = s.length;
        if(length != t.length) return false;

        char[] sm = new char[256];
        char[] tm = new char[256];

        for(int i=0; i<length; i++){
            char sc = s[i];
            char tc = t[i];
            if(sm[sc] == 0 && tm[tc] == 0){
                sm[sc] = tc;
                tm[tc] = sc;
            }else{
                if(sm[sc] != tc || tm[tc] != sc){
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
    	IsomorphicStrings solution = new IsomorphicStrings();
        
        System.out.println(solution.isIsomorphic_better("egg", "add"));
        System.out.println(solution.isIsomorphic("foo", "bar"));
        System.out.println(solution.isIsomorphic("ab", "aa"));
    }
}
