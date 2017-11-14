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

    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    public List<String> letterCombinations(String digits) {
        List<String> res = new LinkedList<>();
        combination("", digits, 0, res);
        return res;
    }

    private void combination(String prefix, String digits, int offset, List<String> res) {
        if (offset >= digits.length()) {
            res.add(prefix);
            return;
        }

        String letters = KEYS[(digits.charAt(offset) - '0')];
        for (int i = 0; i < letters.length(); i++) {
            combination(prefix + letters.charAt(i), digits, offset + 1, res);
        }
    }
    
    public static void main(String[] args) {
        LetterCombinationsOfPhoneNumber solution = new LetterCombinationsOfPhoneNumber();
        
        List<String> result = solution.letterCombinations("23");
        
        System.out.println(result.stream().collect(Collectors.joining(",")));
    }
}
