package LeetCode.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a digit string, return all possible letter combinations that the number could represent.
 * 
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * 
 * Input:Digit string "23"
 * Output: ["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"].
 * 
 * Given a digit string, return all possible letter combinations that the number could represent.
 * A mapping of digit to letters (just like on the telephone buttons) is given below.

 * @author WinnieZhao
 *
 */
public class LetterCombinationsOfPhoneNumber {

    private String[] letters = {"", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv","wxyz"}; 
    
    public List<String> letterCombinations_better(String digits) {
        LinkedList<String> res = new LinkedList<>();
        if (digits == null || digits.isEmpty()) {
            return res;
        }

        String[] mapping = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        char[] chars = digits.toCharArray();
        res.add("");
        for (int i=0; i<chars.length; i++) {
            int x = chars[i] - '0';

            while (res.peek().length() == i) {
                String temp = res.remove();
                for (char c : mapping[x].toCharArray()) {

                    res.add(temp+c);
                }
            }

        }

        return res;
    }
    
    public List<String> letterCombinations(String digits) {
        List<String> result = new ArrayList<>();
        
        int length = digits.length();
        for (int i=0; i<length; i++) {
            int number = Integer.valueOf(digits.substring(i, i+1));
            
            String str = letters[number-1];
            
            this.letterCombinationHelper(result, str);
        }
        
        return result;
    }
    
    private void letterCombinationHelper(List<String> result, String str) {
        if (result.size() == 0) {
            for (int i=0; i<str.length(); i++) {
                result.add(String.valueOf(str.charAt(i)));
            }
        }
        else {
            List<String> temp = new ArrayList<>(result);
            for (int i=0; i<temp.size(); i++) {
                String r = temp.get(i);
                
                for (int j=0; j<str.length(); j++) {
                    String combination = r.concat(String.valueOf(str.charAt(j)));
                    result.add(combination);
                }
            }
            result.removeAll(temp);
        }
    }
    
    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber solution = new LetterCombinationsOfPhoneNumber();
        
        List<String> result = solution.letterCombinations("234");
        
        System.out.println(result.stream().collect(Collectors.joining(",")));
    }
}
