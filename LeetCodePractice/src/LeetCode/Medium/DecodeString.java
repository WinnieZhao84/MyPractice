package LeetCode.Medium;

import java.util.Stack;

/**
 * Given an encoded string, return it's decoded string.
 * 
 * The encoding rule is: k[encoded_string], where the encoded_string inside the square brackets is being repeated
 * exactly k times. Note that k is guaranteed to be a positive integer.
 * 
 * You may assume that the input string is always valid; No extra white spaces, square brackets are well-formed, etc.
 * 
 * Furthermore, you may assume that the original data does not contain any digits and that digits are only for
 * those repeat numbers, k. For example, there won't be input like 3a or 2[4].
 * 
 * Examples:
 * 
 * s = "3[a]2[bc]", return "aaabcbc".
 * s = "3[a2[c]]", return "accaccacc".
 * s = "2[abc]3[cd]ef", return "abcabccdcdcdef".
 * 
 * @author WinnieZhao
 *
 */
public class DecodeString {
    
    public String decodeString(String s) {

        if (s == null || s.isEmpty() || s.equals("[]")) {
            return "";
        }

        Stack<Integer> counts = new Stack<>();
        Stack<String> resStack = new Stack<>();

        String result = "";
        int i=0;
        while (i<s.length()) {

            if (Character.isDigit(s.charAt(i))) {
                int count = 0;
                while (Character.isDigit(s.charAt(i))) {
                    count = 10 * count + (s.charAt(i) - '0');
                    i++;
                }
                counts.push(count);
            }
            else if (s.charAt(i) == '[') {
                resStack.push(result);
                result = "";
                i++;
            }
            else if (s.charAt(i) == ']') {
                StringBuilder sb = new StringBuilder(resStack.pop());
                int count = counts.pop();

                while (count > 0) {
                    sb.append(result);
                    count--;
                }
                result = sb.toString();
                i++;
            }
            else {
                result += s.charAt(i++);
            }
        }

        return result;
    }
    
    public static void main(String[] args) {
        DecodeString solution = new DecodeString();
            
        System.out.println(solution.decodeString("3[a]2[bc]"));
        
        System.out.println(solution.decodeString("3[a2[c]]"));
        
        System.out.println(solution.decodeString("2[abc]3[cd]ef"));
    }

}
