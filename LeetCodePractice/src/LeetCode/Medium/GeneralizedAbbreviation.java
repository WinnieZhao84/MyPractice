package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 320
 *
 * Write a function to generate the generalized abbreviations of a word.
 *
 * Example:
 * Given word = "word", return the following list (order does not matter):
 *
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]

 * Created by WinnieZhao on 2017/4/5.
 */
public class GeneralizedAbbreviation {

    public List<String> generateAbbreviations(String word) {
        List<String> res = new ArrayList<>();

        if (word == null || word.isEmpty()) {
            return res;
        }

        char[] chars = word.toCharArray();
        this.dfs(res, chars, new StringBuilder(), 0, 0);

        return res;
    }

    private void dfs(List<String> res, char[] chars, StringBuilder sb, int pos, int count) {

        int length = sb.length();
        if (pos == chars.length) {
            if (count > 0) {
                sb.append(count);
            }
            res.add(sb.toString());
        }
        else {
            // do abbreviation using number for pos char
            this.dfs(res, chars, sb, pos+1, count+1);

            if (count > 0) {
                sb.append(count);
            }
            sb.append(chars[pos]);

            // not do abbreviation for pos
            this.dfs(res, chars, sb, pos+1, 0);
        }
        // set the string builder to ""
        sb.setLength(length);
    }

    public static void main(String[] args) {
        GeneralizedAbbreviation solution = new GeneralizedAbbreviation();

        System.out.println(solution.generateAbbreviations("word").toString());
    }
}
