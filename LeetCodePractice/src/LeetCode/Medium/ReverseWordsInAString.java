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

    public String reverseWords_test(String s) {
        if (s == null || s.isEmpty()) {
            return s;
        }

        s=s.trim();
        String rev = this.reverse(s, 0, s.length()-1);

        String[] strs = rev.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String str : strs) {
            if (str.isEmpty()) {
                continue;
            }
            sb.append(this.reverse(str, 0, str.length()-1));
            sb.append(" ");
        }

        return sb.toString().trim();
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
        System.out.println(solution.reverseWords_test("   a   b "));
    }
}
