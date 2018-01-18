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

    private String[] mapping = new String[] {"", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

    public List<String> letterCombinations_better(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }

        char[] chs = digits.toCharArray();
        LinkedList<String> res = new LinkedList<>();

        res.add("");

        for (int i=0; i<chs.length; i++) {
            while (!res.isEmpty() && res.peek().length() == i) {
                String cur = res.poll();
                String s = mapping[chs[i] - '0'];

                for (char ch : s.toCharArray()) {
                    res.add(cur + ch);
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
        
        List<String> result = solution.letterCombinations_better("23");
        
        System.out.println(result.stream().collect(Collectors.joining(",")));
    }
}
