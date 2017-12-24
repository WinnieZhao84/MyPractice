package LeetCode.Easy;

/**
 * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a^2 + b^2 = c.
 *
 * Example 1:
 *
 * Input: 5
 * Output: True
 * Explanation: 1 * 1 + 2 * 2 = 5
 *
 * Example 2:
 * Input: 3
 * Output: False

 * Created by WinnieZhao on 7/3/2017.
 */
public class SumOfSquareNumbers {

    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b)
                return true;
        }
        return false;
    }

    public boolean judgeSquareSum_better(int c) {
        if (c < 0) {
            return false;
        }
        if (c == 0) {
            return true;
        }

        int start = 0;
        int end = (int) Math.sqrt(c);

        while (start<=end) {
            int cur = start*start + end*end;

            if (cur == c) {
                return true;
            }
            else if (cur < c) {
                start++;
            }
            else if (cur > c) {
                end--;
            }
        }

        return false;
    }
}
