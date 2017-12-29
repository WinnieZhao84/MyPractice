package LeetCode.Easy;

import java.util.ArrayList;
import java.util.List;

/**
 * A self-dividing number is a number that is divisible by every digit it contains.
 *
 * For example, 128 is a self-dividing number because 128 % 1 == 0, 128 % 2 == 0, and 128 % 8 == 0.
 * Also, a self-dividing number is not allowed to contain the digit zero.
 *
 * Given a lower and upper number bound, output a list of every possible self dividing number, including the bounds if possible.
 *
 * Example 1:
 * Input:
 * left = 1, right = 22
 * Output: [1, 2, 3, 4, 5, 6, 7, 8, 9, 11, 12, 15, 22]
 *
 * Note: The boundaries of each input argument are 1 <= left <= right <= 10000.

 * Created by WinnieZhao on 12/29/2017.
 */
public class SelfDividingNumbers {

    public List<Integer> selfDividingNumbers(int left, int right) {

        List<Integer> res = new ArrayList<>();
        for (int i=left; i<=right; i++) {
            int num = i;
            boolean good = true;
            if (num < 10) {
                res.add(num);
                continue;
            }
            while (num > 0) {
                int div = num % 10;
                if (div == 0) {
                    good = false;
                    break;
                }
                if (i % div != 0) {
                    good = false;
                    break;
                }
                num = num / 10;
            }

            if (good) {
                res.add(i);
            }
        }

        return res;
    }

    /**
     * Time Complexity: O(D), where D is the number of integers in the range [L, R],
     * and assuming log(R) is bounded. (In general, the complexity would be O(DlogR))
     * Space Complexity: O(D), the length of the answer.
     */
    class Solution {
        public List<Integer> selfDividingNumbers(int left, int right) {
            List<Integer> ans = new ArrayList();
            for (int n = left; n <= right; ++n) {
                if (selfDividing(n)) {
                    ans.add(n);
                }
            }
            return ans;
        }

        public boolean selfDividing(int n) {
            for (char c : String.valueOf(n).toCharArray()) {
                if (c == '0' || (n % (c - '0') > 0))
                    return false;
            }
            return true;
        }
    }

    public static void main(String[] args) {
        SelfDividingNumbers solution = new SelfDividingNumbers();

        System.out.println(solution.selfDividingNumbers(1, 22));
    }

}
