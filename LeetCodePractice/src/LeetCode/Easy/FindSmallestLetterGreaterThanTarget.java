package LeetCode.Easy;

/**
 * Given a list of sorted characters letters containing only lowercase letters, and given a target letter target,
 * find the smallest element in the list that is larger than the given target.
 *
 * Letters also wrap around. For example, if the target is target = 'z' and letters = ['a', 'b'], the answer is 'a'.
 *
 * Examples:
 * Input: letters = ["c", "f", "j"]
 * target = "a"
 * Output: "c"
 *
 * Input: letters = ["c", "f", "j"]
 * target = "c"
 * Output: "f"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "d"
 * Output: "f"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "g"
 * Output: "j"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "j"
 * Output: "c"
 *
 * Input:
 * letters = ["c", "f", "j"]
 * target = "k"
 * Output: "c"
 *
 * Note:
 * letters has a length in range [2, 10000].
 * letters consists of lowercase letters, and contains at least 2 unique letters.
 * target is a lowercase letter.

 * Created by WinnieZhao on 12/29/2017.
 */
public class FindSmallestLetterGreaterThanTarget {

    public char nextGreatestLetter(char[] letters, char target) {

        int length = letters.length;

        if (letters[length-1] <= target || letters[0] > target) {
            return letters[0];
        }

        int l=0;
        int r=length;

        while (l<r) {
            int mid = l + (r-l)/2;

            if (letters[mid] <= target) {
                l = mid+1;
            }
            else if (letters[mid] > target) {
                r = mid;
            }
        }

        return letters[l];
    }

    class Solution {
        public char nextGreatestLetter(char[] letters, char target) {
            int lo = 0, hi = letters.length;
            while (lo < hi) {
                int mi = lo + (hi - lo) / 2;
                if (letters[mi] <= target) lo = mi + 1;
                else hi = mi;
            }
            return letters[lo % letters.length];
        }
    }

    public static void main(String[] args) {
        FindSmallestLetterGreaterThanTarget solution = new FindSmallestLetterGreaterThanTarget();

        System.out.println(solution.nextGreatestLetter(new char[]{'c', 'f', 'j'}, 'j'));
    }
}
