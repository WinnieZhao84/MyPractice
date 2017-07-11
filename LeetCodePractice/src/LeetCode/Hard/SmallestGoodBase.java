package LeetCode.Hard;

import java.math.BigInteger;

/**
 * For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.
 *
 * Now given a string representing n, you should return the smallest good base of n in string format.
 *
 * Example 1:
 * Input: "13"
 * Output: "3"
 * Explanation: 13 base 3 is 111.
 *
 * Example 2:
 * Input: "4681"
 * Output: "8"
 * Explanation: 4681 base 8 is 11111.
 *
 * Example 3:
 * Input: "1000000000000000000"
 * Output: "999999999999999999"
 * Explanation: 1000000000000000000 base 999999999999999999 is 11.
 *
 * Note:
 * The range of n is [3, 10^18].
 * The string representing n is always valid and will not have leading zeros.

 * Created by WinnieZhao on 7/11/2017.
 */
public class SmallestGoodBase {

    /**
     * Input: 13, output: 3
     * Because: 3^0 + 3^1 + 3^2 = 1 + 3 + 9 = 13 (111)
     *
     * Input 4681, output: 8
     * Because: 8^0 + 8^1 + 8^2 + 8^3 + 8^4 = 1 + 8 + 64 + 512 + 4096 = 4681
     *
     * so m stands for digits count when it's all "1", k is the base =>
     * n = 1 + k + k^2 + k^3 + ... + k^(m-1)
     *
     * (1)=> n-1 = k + k^2 + k^3 + ... + k^(m-1)
     *    => n-k^m-1 = 1 + k + k^2 + k^3 + ... + k^(m-2)
     * (2)=> k*(n-k^m-1) = k + k^2 + k^3 + ... + k^(m-1)
     *
     * so from (1) and (2):
     * n-1 = k*(n-k^m-1)
     *  k*n-k^m = n-1
     *  k^m = nk - n + 1
     *  n = k^m-1/k-1
     *  n(k-1) = k^m -1
     *
     * So, to find the smallest base means to find the longest possible representation "11111....1" based on k.
     * As n<=10^18, so k<= 60.
     *
     * @param n
     * @return
     */
    public String smallestGoodBase(String n) {
        long nn = Long.parseLong(n);
        long res = 0;

        for(int m = 60; m >= 2; m--){
            long start = 2;
            long end = nn;

            while(start < end){
                long mid = start + (end - start) / 2;

                BigInteger left = BigInteger.valueOf(mid);
                left = left.pow(m).subtract(BigInteger.ONE);

                BigInteger right = BigInteger.valueOf(nn).multiply(BigInteger.valueOf(mid).subtract(BigInteger.ONE));
                // if k^m -1 < n*(k-1), means k is too small
                int cmr = left.compareTo(right);

                if(cmr == 0){
                    res =  mid;
                    break;
                }
                else if(cmr < 0){
                    start = mid + 1;
                }
                else {
                    end = mid;
                }
            }

            if(res != 0) break;
        }

        return "" + res;
    }
}
