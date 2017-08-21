package LeetCode.Hard;

import java.util.HashMap;
import java.util.Map;

/**
 * A sequence of numbers is called arithmetic if it consists of at least three elements and if the difference between
 * any two consecutive elements is the same.
 *
 * For example, these are arithmetic sequences:
 * 1, 3, 5, 7, 9
 * 7, 7, 7, 7
 * 3, -1, -5, -9
 *
 * The following sequence is not arithmetic.
 * 1, 1, 2, 5, 7
 *
 * A zero-indexed array A consisting of N numbers is given. A subsequence slice of that array is any sequence of integers
 * (P0, P1, ..., Pk) such that 0 ≤ P0 < P1 < ... < Pk < N.
 *
 * A subsequence slice (P0, P1, ..., Pk) of array A is called arithmetic if the sequence A[P0], A[P1], ..., A[Pk-1], A[Pk]
 * is arithmetic. In particular, this means that k ≥ 2.
 *
 * The function should return the number of arithmetic subsequence slices in the array A.
 *
 * The input contains N integers. Every integer is in the range of -2^31 and 2^31-1 and 0 ≤ N ≤ 1000.
 * The output is guaranteed to be less than 2^31-1.
 *
 * Example:
 * Input: [2, 4, 6, 8, 10]
 * Output: 7
 *
 * Explanation:
 * All arithmetic subsequence slices are:
 * [2,4,6]
 * [4,6,8]
 * [6,8,10]
 * [2,4,6,8]
 * [4,6,8,10]
 * [2,4,6,8,10]
 * [2,6,10]
 *
 * Created by WinnieZhao on 2017/7/7.
 */
public class ArithmeticSlicesIISubsequence {


    /**
     *  T(i, d), which denotes the total number of arithmetic subsequence slices ending at index i with difference d.
     *  The base case and recurrence relation are as follows:
     *
     *  Base case: T(0, d) = 0 (This is true for any d).
     *  Recurrence relation: T(i, d) = summation of (1 + T(j, d)) as long as 0 <= j < i && d == A[i] - A[j].

     * @param A
     * @return
     */
    public int numberOfArithmeticSlices(int[] A) {
        int res = 0;
        Map<Integer, HashMap<Long, Integer>> diffMaps = new HashMap<>();

        for (int i = 0; i < A.length; i++) {
            HashMap<Long, Integer> diffMap = new HashMap<>();

            diffMaps.put(i, diffMap);
            int num = A[i];

            for (int j = 0; j < i; j++) {
                if ((long) num - A[j] > Integer.MAX_VALUE)
                    continue;
                if ((long) num - A[j] < Integer.MIN_VALUE)
                    continue;

                long diff = (long) num - A[j];
                int count = diffMaps.get(j).getOrDefault(diff, 0);

                diffMaps.get(i).put(diff, diffMaps.get(i).getOrDefault(diff, 0) + count + 1);

                res += count;
            }
        }
        return res;
    }

}
