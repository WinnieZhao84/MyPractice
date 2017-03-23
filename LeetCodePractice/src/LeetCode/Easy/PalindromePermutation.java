package LeetCode.Easy;

import java.util.HashSet;
import java.util.Set;

/**
 * 266
 * Given a string, determine if a permutation of the string could form a palindrome.
 * For example,
 *
 * "code" -> False, "aab" -> True, "carerac" -> True.
 *
 * Hint:
 * Consider the palindromes of odd vs even length. What difference do you notice?
 * Count the frequency of each character.
 * If each character occurs even number of times, then it must be a palindrome. How about character which occurs odd number of times?
 *
 * Created by WinnieZhao on 2017/3/22.
 */
public class PalindromePermutation {

    public boolean canPermutePalindrome(String s) {
        if (s == null) {
            return false;
        }
        /**
         * If the set already contains the character then remove the character
         * after this, the even count chars within the set will be removed
         * so if the set remains only <=1 char then it should be a palindromes
         */
        Set<Character> set = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (!set.add(c)) {
                set.remove(c);
            }
        }
        return set.size() <= 1;

    }
}
