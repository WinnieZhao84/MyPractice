package LeetCode.Hard;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 411
 *
 * A string such as "word" contains the following abbreviations:
 * ["word", "1ord", "w1rd", "wo1d", "wor1", "2rd", "w2d", "wo2", "1o1d", "1or1", "w1r1", "1o2", "2r1", "3d", "w3", "4"]
 *
 * Given a target string and a set of strings in a dictionary, find an abbreviation of this target string with the smallest
 * possible length such that it does not conflict with abbreviations of the strings in the dictionary.
 *
 * Each number or letter in the abbreviation is considered length = 1. For example, the abbreviation "a32bc" has length = 4.
 *
 * Note:
 * In the case of multiple answers as shown in the second example below, you may return any one of them.
 * Assume length of target string = m, and dictionary size = n. You may assume that m ≤ 21, n ≤ 1000, and log2(n) + m ≤ 20.
 *
 * Examples:
 * "apple", ["blade"] -> "a4" (because "5" or "4e" conflicts with "blade")
 * "apple", ["plain", "amber", "blade"] -> "1p3" (other valid answers include "ap3", "a3e", "2p2", "3le", "3l1").


 * Created by WinnieZhao on 2017/7/17.
 */
public class MinimumUniqueWordAbbreviation {

    /**
     * This is a combination of Valid Word Abbreviation and Generalized Abbreviation.
     * We first find all abbreviations for the target word, then check with each word in the dict to see if it conflicts
     * with any one of them (by checking if the abbreviation is a valid one for the word in the dict). Since we need to
     * find the abbreviation with the minimum length, we use a priority queue which is ordered by length.
     * Thus the first valid abbreviation is the one we want.
     *
     * @param target
     * @param dictionary
     * @return
     */
    public String minAbbreviation(String target, String[] dictionary) {
        if (target.length() == 0 || dictionary.length == 0) {
            return "";
        }

        PriorityQueue<String> queue = new PriorityQueue<>(Comparator.comparing(String::length));

        getAbbreviations(queue, target, new StringBuilder(), 0, 0);

        while (!queue.isEmpty()) {
            String abbr = queue.poll();
            boolean allValid = true;

            for (String word : dictionary) {
                if (isValidAbbr(word, abbr)) {
                    allValid = false;
                    break;
                }
            }
            if (allValid) {
                return abbr;
            }
        }
        return "";
    }

    private void getAbbreviations(PriorityQueue<String> queue, String target,
                                  StringBuilder curr, int pos, int count) {
        int len = curr.length();
        if (pos == target.length()) {
            if (count > 0) {
                curr.append(count);
            }
            queue.add(curr.toString());
        }
        else {
            getAbbreviations(queue, target, curr, pos + 1, count + 1);

            if (count > 0) {
                curr.append(count);
            }
            curr.append(target.charAt(pos));

            getAbbreviations(queue, target, curr, pos + 1, 0);
        }

        curr.setLength(len);
    }

    private boolean isValidAbbr(String word, String abbr) {
        if (word.length() == 0) {
            return abbr.length() == 0;
        }

        int pW = 0, pA = 0;
        while (pW < word.length() && pA < abbr.length()) {
            char a = abbr.charAt(pA);

            if (Character.isLetter(a)) {
                if (word.charAt(pW) != abbr.charAt(pA)) {
                    return false;
                }
                pW++;
                pA++;
            }
            else if (Character.isDigit(a)) {
                if (a == '0') {
                    return false;
                }
                int org = pA;
                while (pA < abbr.length() && Character.isDigit(abbr.charAt(pA))) {
                    pA++;
                }
                int num = Integer.valueOf(abbr.substring(org, pA));
                while (pW < word.length() && num > 0) {
                    pW++;
                    num--;
                }
                if (num > 0) {
                    return false;
                }
            }
            else {
                return false;
            }
        }
        return true;

    }

}
