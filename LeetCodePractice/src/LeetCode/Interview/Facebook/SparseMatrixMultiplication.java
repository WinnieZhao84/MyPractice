package LeetCode.Interview.Facebook;

import java.util.HashMap;
import java.util.Map;

/**
 * 311
 *
 * Given two sparse matrices A and B, return the result of AB.
 * You may assume that A's column number is equal to B's row number.
 * Example:
 * A = [
 *      [ 1, 0, 0],
 *      [-1, 0, 3]
 *     ]
 *
 * B = [
 *      [ 7, 0, 0 ],
 *      [ 0, 0, 0 ],
 *      [ 0, 0, 1 ]
 *     ]
 *
 *
 *      |  1 0 0 |   | 7 0 0 |   |  7 0 0 |
 * AB = | -1 0 3 | x | 0 0 0 | = | -7 0 3 |
 *                   | 0 0 1 |
 *
 * Created by WinnieZhao on 2017/4/4.
 */
public class SparseMatrixMultiplication {

    public int[][] multiply(int[][] A, int[][] B) {
        int m = A.length;
        int n = A[0].length;
        int k = B[0].length;

        Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

        int[][] res = new int[m][k];

        for (int i=0; i<n; i++) {
            map.put(i, new HashMap<>());
            for (int j=0; j<k; j++) {
                if (B[i][j] != 0) {
                    map.get(i).put(j, B[i][j]);
                }
            }
        }

        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (A[i][j] != 0) {
                    for (int key : map.keySet()) {
                        res[i][key] += A[i][j] * map.get(j).get(key);
                    }
                }
            }
        }

        return res;
    }
}
