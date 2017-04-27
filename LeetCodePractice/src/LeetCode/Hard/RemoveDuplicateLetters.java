package LeetCode.Hard;

import java.util.*;


/**
 * Given a string which contains only lowercase letters, remove duplicate letters so that every letter appear once and only once.
 * You must make sure your result is the smallest in lexicographical order among all possible results.
 *
 * Example:
 * Given "bcabc"
 * Return "abc"
 *
 * Given "cbacdcbc"
 * Return "acdb"

 * Created by WinnieZhao on 2017/4/26.
 */
public class RemoveDuplicateLetters {

    public String removeDuplicateLetters(String s) {

        if (s == null || s.length() == 0 || s.length() == 1) return s;

        Stack<Character> stack = new Stack<>();

        int[] count = new int[26];
        boolean[] added = new boolean[26];
        for (char c : s.toCharArray()) {
            count[c-'a']++;
        }

        for (char c : s.toCharArray()) {
            int index = c - 'a';
            count[index]--;

            if (added[index]) continue;

            added[index] = true;

            // Compare current char with the top one (latest got from string) from stack
            // If the current one less than the top from stack and number is more than 0
            // pop that char from the stack
            while (!stack.isEmpty() && stack.peek() > c && count[stack.peek()-'a'] > 0) {
                char pop = stack.pop();
                added[pop-'a'] = false;
            }

            stack.push(c);
        }

        StringBuilder sb = new StringBuilder();
        while(!stack.isEmpty()) {
            sb.insert(0, stack.pop());
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveDuplicateLetters solution = new RemoveDuplicateLetters();

        System.out.println(solution.removeDuplicateLetters("bcabc"));
        System.out.println(solution.removeDuplicateLetters("cbacdcbc"));
    }
}
