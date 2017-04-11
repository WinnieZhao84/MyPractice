package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * 267
 *
 * Given a string s, return all the palindromic permutations (without duplicates) of it.
 * Return an empty list if no palindromic permutation could be form.
 *
 * For example:
 *
 * Given s = "aabb", return ["abba", "baab"].
 * Given s = "abc", return [].
 *
 * Hint:
 * If a palindromic permutation exists, we just need to generate the first half of the string.
 * To generate all distinct permutations of a (half of) string, use a similar approach from: Permutations II or Next Permutation.
 *
 * Created by WinnieZhao on 4/10/2017.
 */
public class PalindromePermutationII {

    private List<String> list = new ArrayList<>();

    // We only need to generate the first part of palindrome string,
    // and the remaining part will be a middle character with the reverse of first part.
    public List<String> generatePalindromes(String s) {
        int numOdds = 0; // How many characters that have odd number of count
        int[] map = new int[256]; // Map from character to its frequency

        for (char c: s.toCharArray()) {
            map[c]++;
            numOdds = (map[c] & 1) == 1 ? numOdds+1 : numOdds-1;
        }
        if (numOdds > 1)   return list;

        String mid = "";
        int length = 0;
        for (int i = 0; i < 256; i++) {
            if (map[i] > 0) {
                if ((map[i] & 1) == 1) { // Char with odd count will be in the middle
                    mid = "" + (char)i;
                    map[i]--;
                }
                map[i] /= 2; // Cut in half since we only generate half string
                length += map[i]; // The length of half string
            }
        }
        generatePalindromesHelper(map, length, "", mid);
        return list;
    }

    private void generatePalindromesHelper(int[] map, int length, String s, String mid) {
        if (s.length() == length) {
            StringBuilder reverse = new StringBuilder(s).reverse(); // Second half
            list.add(s + mid + reverse);
            return;
        }

        for (int i = 0; i < 256; i++) { // backtracking just like permutation
            if (map[i] > 0) {
                map[i]--;
                generatePalindromesHelper(map, length, s + (char)i, mid);
                map[i]++;
            }
        }
    }
}
