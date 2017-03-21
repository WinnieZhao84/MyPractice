package LeetCode.Easy;

/**
 * Write a function that takes a string as input and returns the string reversed.
 * 
 * Example: Given s = "hello", return "olleh".

 * @author WinnieZhao
 *
 */
public class ReverseString {
    public String reverseString(String s) {
        if (s == null || s.equals("")) {
            return s;
        }
        int length = s.length();
        StringBuilder result = new StringBuilder();
        for (int i=length-1; i>=0; i--) {
            result.append(s.charAt(i));
        }
        return result.toString();
    }
}
