package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Given a string s and a list of strings dict, you need to add a closed pair of bold tag <b> and </b> to
 * wrap the substrings in s that exist in dict. If two such substrings overlap, you need to wrap them together
 * by only one pair of closed bold tag. Also, if two substrings wrapped by bold tags are consecutive,
 * you need to combine them.
 *
 * Example 1:
 * Input:
 * s = "abcxyz123"
 * dict = ["abc","123"]
 * Output:
 * "<b>abc</b>xyz<b>123</b>"
 *
 * Example 2:
 * Input:
 * s = "aaabbcc"
 * dict = ["aaa","aab","bc"]
 * Output:
 * "<b>aaabbc</b>c"
 *
 * Note:
 * The given dict won't contain duplicates, and its length won't exceed 100.
 * All the strings in input have length in range [1, 1000].
 * Created by WinnieZhao on 6/12/2017.
 */
public class AddBoldTagInString {

    public String addBoldTag(String s, String[] dict) {

        List<int[]> list = new ArrayList<>();
        for (String d : dict) {
            for (int i=0; i<=s.length()-d.length(); i++) {
                if (s.substring(i, i + d.length()).equals(d)) {
                    list.add(new int[] {i, i+d.length()-1});
                }
            }
        }

        if (list.size() == 0) {
            return s;
        }

        Collections.sort(list, (a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);

        int start = 0;
        int end = 0;
        int prev = 0;
        StringBuilder builder = new StringBuilder();

        for (int i=0; i<list.size(); i++) {
            builder.append(s.substring(prev, list.get(i)[0]));

            start = i;
            end = list.get(start)[1];

            while (i<list.size()-1 && list.get(i+1)[0] <= end + 1) {
                end = Math.max(end, list.get(i+1)[1]);
                i++;
            }
            builder.append("<b>" + s.substring(list.get(start)[0], end + 1) + "</b>");
            prev = end + 1;

        }
        builder.append(s.substring(end+1, s.length()));

        return builder.toString();
    }

    // Better because of less space complexity
    public String addBoldTag_better(String s, String[] dict) {
        boolean[] bold = new boolean[s.length()];

        // A True value at bold[i] indicates that the current character is a part of the substring which is present in dict.
        for (String d: dict) {
            for (int i = 0; i <= s.length() - d.length(); i++) {
                if (s.substring(i, i + d.length()).equals(d)) {
                    for (int j = i; j < i + d.length(); j++)
                        bold[j] = true;
                }
            }
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < s.length();) {
            if (bold[i]) {
                res.append("<b>");
                while (i < s.length() && bold[i])
                    res.append(s.charAt(i++));
                res.append("</b>");
            } else
                res.append(s.charAt(i++));
        }
        return res.toString();
    }

    public static void main(String[] args) {
        AddBoldTagInString solution = new AddBoldTagInString();
        String[] dict = {"abc","123"};
        String s = "abcxyz123";
        System.out.println(solution.addBoldTag(s, dict));

        String[] dict1 = {"aaa","aab","bc"};
        String s1 = "aaabbcc";
        System.out.println(solution.addBoldTag(s1, dict1));
    }
}
