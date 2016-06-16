package LeetCode.Easy;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:
Given s = "hello", return "holle".

Example 2:
Given s = "leetcode", return "leotcede".

 * @author ASUS-PC
 *
 */
public class ReverseVowelsOfString {
	public String reverseVowels(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        } 
        List<Character> vowels = new ArrayList<Character>();
        
        vowels.add('a');
        vowels.add('e');
        vowels.add('i');
        vowels.add('o');
        vowels.add('u');
        
        vowels.add('A');
        vowels.add('E');
        vowels.add('I');
        vowels.add('O');
        vowels.add('U');
        
        int i=0;
        int j=s.length()-1;
        
        char[] chs = s.toCharArray();
        while (i<j) {
            
            if (vowels.contains(chs[i]) && vowels.contains(chs[j])) {
                char temp =  chs[i];
                chs[i] = chs[j];
                chs[j] = temp;
                i++;
                j--;
            }
            else if (!vowels.contains(chs[i])) {
                i++;
            }
            else if (!vowels.contains(chs[j])) {
                j--;
            }
        }
        
        return String.valueOf(chs);
    }
}
