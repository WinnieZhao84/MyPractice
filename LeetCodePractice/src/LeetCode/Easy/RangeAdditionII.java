package LeetCode.Easy;

/**
 * Given an m * n matrix M initialized with all 0's and several update operations.
 *
 * Operations are represented by a 2D array, and each operation is represented by an array with two positive integers a and b,
 * which means M[i][j] should be added by one for all 0 <= i < a and 0 <= j < b.
 *
 * You need to count and return the number of maximum integers in the matrix after performing all the operations.
 *
 * Example 1:
 * Input: m = 3, n = 3
 * operations = [[2,2],[3,3]]
 * Output: 4
 *
 * Explanation:
 * Initially, M =
 * [[0, 0, 0],
 *  [0, 0, 0],
 *  [0, 0, 0]]
 *
 * After performing [2,2], M =
 * [[1, 1, 0],
 *  [1, 1, 0],
 *  [0, 0, 0]]
 *
 * After performing [3,3], M =
 * [[2, 2, 1],
 *  [2, 2, 1],
 *  [1, 1, 1]]
 *
 *  So the maximum integer in M is 2, and there are four of it in M. So return 4.
 *
 *  Note:
 *  The range of m and n is [1,40000].
 *  The range of a is [1,m], and the range of b is [1,n].
 *  The range of operations size won't exceed 10,000.
 *
 * Created by WinnieZhao on 5/30/2017.
 */
public class RangeAdditionII {

    // Brute Force will get Time Limit Exceeded
    // As per the given problem statement, all the operations are performed on a rectangular sub-matrix of the initial
    // all 0's MM matrix. The upper left corner of each such rectangle is given by the index (0, 0)(0,0) and the lower
    // right corner for an operation [i, j][i,j] is given by the index (i, j)(i,j). The maximum element will be the one
    // on which all the operations have been performed.
    // Further, we can observe that to count the number of elements lying in this intersection region, we don't actually
    // need to perform the operations, but we need to determine the lower right cornerof the intersecting region only.
    // This corner is given by (x,y)=(min(op[0],min(op[1])),
    // where min(op[i]) represents the minimum value of op[i] from among all the op[i]'s in the given set of operations.
    // Thus, the resultant count of elements lying in the intersection is given by: x*y.
    public int maxCount(int m, int n, int[][] ops) {
        for (int[] op: ops) {
            m = Math.min(m, op[0]);
            n = Math.min(n, op[1]);
        }
        return m * n;
    }
}
