package LeetCode.Easy;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a pattern and a string str, find if str follows the same pattern.
 * 
 * Here follow means a full match, such that there is a bijection between a letter in pattern and a non-empty word in str.
 * 
 * Examples:
 * pattern = "abba", str = "dog cat cat dog" should return true.
 * pattern = "abba", str = "dog cat cat fish" should return false.
 * pattern = "aaaa", str = "dog cat cat dog" should return false.
 * pattern = "abba", str = "dog dog dog dog" should return false.
 * 
 * Notes:
 * You may assume pattern contains only lowercase letters, and str contains lowercase letters separated by a single space.

 * @author ASUS-PC
 *
 */
public class WordPattern {

    public boolean wordPattern(String pattern, String str) {
        int pLength = pattern.length();
        String[] strArray = str.split(" ");
        int strLength = strArray.length;
        
        if (pLength != strLength) {
            return false;
        }
        
        Map<Character, String> map = new HashMap<Character, String>();
        map.put(pattern.charAt(0), strArray[0]);
        
        for (int i=1; i<pLength; i++) {
            if ((map.containsKey(pattern.charAt(i)) && !map.get(pattern.charAt(i)).equals(strArray[i])) 
                    || (map.containsValue(strArray[i]) && !map.keySet().contains(pattern.charAt(i)))) {
                return false;
            }
                        map.put(pattern.charAt(i), strArray[i]);
        }
        return true;
    }
}
