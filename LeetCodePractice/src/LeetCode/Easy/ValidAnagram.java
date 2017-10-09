package LeetCode.Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two strings s and t, write a function to determine if t is an anagram of s.
 * 
 * For example,
 * s = "anagram", t = "nagaram", return true.
 * s = "rat", t = "car", return false.
 * 
 * Note:
 * You may assume the string contains only lowercase alphabets.
 * 
 * Follow up:
 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
 * @author ASUS-PC
 *
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        
    	Map<Character, Integer> sChars = new HashMap<Character, Integer>();
    	for (char c : s.toCharArray()) {
    		if (sChars.containsKey(c)) {
    			sChars.put(c, sChars.get(c) + 1); 
    		}
    		else {
    			sChars.put(c, 1);
    		}
    	}
    	
    	Map<Character, Integer> tChars = new HashMap<Character, Integer>();
    	for (char c : t.toCharArray()) {
    		if (tChars.containsKey(c)) {
    			tChars.put(c, tChars.get(c) + 1); 
    		}
    		else {
    			tChars.put(c, 1);
    		}
    	}

    	return s.length() == t.length() && tChars.equals(sChars);
    }

	public boolean isAnagram_better(String s, String t) {
		if (s == null || t == null) {
			return s == t;
		}

		if (s.length() != t.length()) {
			return false;
		}

		int[] alphabet = new int[26];
		for (int i = 0; i < s.length(); i++) {
			alphabet[s.charAt(i) - 'a']++;
		}
		for (int i = 0; i < t.length(); i++) {
			alphabet[t.charAt(i) - 'a']--;
		}
		for (int i : alphabet) {
			if (i != 0) {
				return false;
			}
		}
		return true;
	}

	/**
	 * What if the inputs contain unicode characters? How would you adapt your solution to such case?
	 * Integer.toHexString(char)
	 *
	 * @param args
	 */
    
    public static void main(String[] args) {
    	ValidAnagram solution = new ValidAnagram();
    	System.out.print(solution.isAnagram("anagram", "aagaram"));
    }
}
