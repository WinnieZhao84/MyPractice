package LeetCode.Easy;

import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Given a string, you need to reverse the order of characters in each word within a sentence while still preserving whitespace and initial word order.
 *
 * Example 1:
 * Input: "Let's take LeetCode contest"
 * Output: "s'teL ekat edoCteeL tsetnoc"
 *
 * Note: In the string, each word is separated by single space and there will not be any extra space in the string.

 * Created by WinnieZhao on 4/18/2017.
 */
public class ReverseWordsInAStringIII {

    public String reverseWords(String s) {
        if (s == null || s.length() == 0) {
            return s;
        }

        return Arrays.stream(s.split(" "))
                .map(StringBuilder::new)
                .map(StringBuilder::reverse)
                .collect(Collectors.joining(" "));
    }
}
