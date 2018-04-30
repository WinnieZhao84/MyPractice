package LeetCode.Medium;

import java.util.Arrays;
import java.util.Collections;

/**
 * Given an input string, reverse the string word by word.
 * 
 * For example,
 * Given s = "the sky is blue",
 * return "blue is sky the".
 * 
 * @author WinnieZhao
 *
 */
public class ReverseWordsInAString {

    public String reverseWords(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }
        
        /**
         *  Using regex:
         *  String[] parts = s.trim().split("\\s+");
         */
        String[] array = s.split(" ");
        
        StringBuilder builder = new StringBuilder();
        for (int i=array.length-1; i>=0; i--) {
            if (array[i].trim().equals("")) {
                continue;
            }
            builder.append(array[i]);
            builder.append(" ");
        }
        
        return builder.toString().trim();
    }

    public String reverseWords_better(String s) {
        // "+" in regex means at least one, so here means at least one space
        String[] words = s.trim().split(" +");

        Collections.reverse(Arrays.asList(words));

        return String.join(" ", words);
    }

    public String reverseString(String input) {
        if (input == null || input.length() == 0) {
            return "";
        }
        char[] letters = input.toCharArray();
        reverse(0, letters.length - 1, letters);
        int slow = 0;
        while (slow < letters.length) {
            if (letters[slow] == ' ') {
                slow++;
            }
            else {
                int fast = slow + 1;
                while (fast < letters.length && letters[fast] != ' ') {
                    fast++;
                }
                reverse(slow, fast - 1, letters);
                slow = fast;
            }
        }
        return String.valueOf(letters);
    }

    private void reverse(int start, int end, char[] input) {
        while (start <= end) {
            char temp = input[start];
            input[start] = input[end];
            input[end] = temp;
            start++;
            end--;
        }
    }

    private String reverse(String s, int start, int end) {
        char[] chs = s.toCharArray();
        while (start < end) {
            char temp = chs[start];
            chs[start] = chs[end];
            chs[end] = temp;

            start++;
            end--;
        }
        return String.valueOf(chs);
    }
    
    public static void main(String[] args) {
        ReverseWordsInAString solution = new ReverseWordsInAString();
        System.out.println(solution.reverseWords("   a   b "));
    }
}
