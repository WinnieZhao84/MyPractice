package LeetCode.Medium;

/**
 * Given a positive integer a, find the smallest positive integer b whose multiplication of each digit equals to a.
 *
 * If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.
 *
 * Example 1
 *
 * Input: 48
 * Output: 68
 *
 * Example 2
 * Input: 15
 * Output: 35

 * Created by WinnieZhao on 2017/6/18.
 */
public class MinimumFactorization {

    public int smallestFactorization(int a) {
        if (a <=1) return a;

        long res=0;
        long mul=1;

        for (int i=9; i>=2; i--) {
            while (a%i == 0) {
                res += i*mul;
                a = a/i;
                mul *= 10;
            }
        }

        return a < 2 && res <= Integer.MAX_VALUE ? (int)res : 0;
    }
}
