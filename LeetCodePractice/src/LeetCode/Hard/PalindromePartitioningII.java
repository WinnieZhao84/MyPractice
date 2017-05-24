package LeetCode.Hard;

/**
 * Given a string s, partition s such that every substring of the partition is a palindrome.
 *
 * Return the minimum cuts needed for a palindrome partitioning of s.
 * For example, given s = "aab",
 * Return 1 since the palindrome partitioning ["aa","b"] could be produced using 1 cut.

 * Created by WinnieZhao on 2017/5/24.
 */
public class PalindromePartitioningII {

    public int minCut(String s) {
        if (s == null || s.isEmpty()) return 0;



    }

    private boolean isPalindrome(String s, int left, int right) {

        while (s.charAt(left) == s.charAt(right)) {
            left++;
            right--;

            if (left >= right) {
                return true;
            }
        }

        return false;
    }
}
