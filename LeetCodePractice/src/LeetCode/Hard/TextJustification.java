package LeetCode.Hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an array of words and a length L, format the text such that each line has exactly L characters and is fully (left and right) justified.
 *
 * You should pack your words in a greedy approach; that is, pack as many words as you can in each line. Pad extra spaces ' ' when necessary
 * so that each line has exactly L characters.
 *
 * Extra spaces between words should be distributed as evenly as possible. If the number of spaces on a line do not divide evenly between words,
 * the empty slots on the left will be assigned more spaces than the slots on the right.
 *
 * For the last line of text, it should be left justified and no extra space is inserted between words.
 *
 * For example,
 * words: ["This", "is", "an", "example", "of", "text", "justification."]
 * L: 16.
 *
 * Return the formatted lines as:
 * [
 * "This    is    an",
 * "example  of text",
 * "justification.  "
 * ]
 *
 * Note: Each word is guaranteed not to exceed L in length.
 *
 * Corner Cases:
 * A line other than the last line might contain only one word. What should you do in this case?
 * In this case, that line should be left-justified.

 * Created by WinnieZhao on 2017/6/26.
 */
public class TextJustification {

    public List<String> fullJustify(String[] words, int maxWidth) {
        List<String> res = new ArrayList<String>();

        if (words.length == 0 || maxWidth == 0) {
            res.add(""); //for some reason OJ expects list with empty string for empty array input
            return res;
        }

        for(int i = 0, w; i < words.length; i = w) {
            int len = -1; //We need to skip the space for last word hence start len = -1
            //check how many words fit into the line

            for (w = i; w < words.length && len + words[w].length() + 1 <= maxWidth; w++) {
                len += words[w].length() + 1; // 1 extra for the space
            }

            int evenlyDistributedSpaces = 1;
            int extraSpaces = 0;
            int numOfGapsBwWords = w-i-1; //w is already pointing to next index and -1 since n words have n-1 gaps between them

            // Moreover we don't need to do this computation if we reached the last word
            // of array or there is only one word that can be accommodate on the line
            // then we don't need to do any justify text. In both cases text can be left-aligned

            if (w != i+1 && w != words.length) {
                //additional 1 for the default one space between words
                evenlyDistributedSpaces = ((maxWidth-len) / numOfGapsBwWords) + 1;
                extraSpaces = (maxWidth-len)%numOfGapsBwWords;
            }

            StringBuilder sb = new StringBuilder(words[i]);
            for(int j = i+1; j < w; j++) {
                for(int s = 0; s < evenlyDistributedSpaces; s++) {
                    sb.append(' ');
                }
                if (extraSpaces > 0) {
                    sb.append(' ');
                    extraSpaces--;
                }
                sb.append(words[j]);
            }

            //Handle the above two cases we skipped, where there is only one word on line
            //or we reached end of word array. Last line should remain left aligned.
            int remaining = maxWidth-sb.length();
            while (remaining > 0) {
                sb.append(' ');
                remaining--;
            }
            res.add(sb.toString());
        }

        return res;

    }
}
