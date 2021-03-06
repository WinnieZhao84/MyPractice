package LeetCode.Medium;

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
        int l = B[0].length;
        int[][] C = new int[m][l];

        for(int i=0; i< m; i++){
            for(int j = 0; j< n; j++){
                if(A[i][j] != 0){
                    for(int k = 0; k<l; k++)
                        C[i][k] += A[i][j]* B[j][k];
                }
            }
        }

        return C;
    }

    // Even direct multiplying gives better result. this question is expecting you to use hash table.
    public class Solution {
        public int[][] multiply(int[][] A, int[][] B) {
            int m = A.length;
            int n = A[0].length;
            int l = B[0].length;
            int[][] C = new int[m][l];
            // i , j, value
            Map<Integer, Map<Integer, Integer>> map = new HashMap<>();

            for(int i=0; i< n; i++){
                map.put(i, new HashMap<Integer, Integer>());
                for(int j = 0; j<l; j++){
                    if(B[i][j] != 0){
                        map.get(i).put(j, B[i][j]);
                    }
                }
            }
            for(int i=0; i< m; i++){
                for(int j =0; j<n; j++){
                    if(A[i][j] != 0){
                        for(int k : map.get(j).keySet()){
                            C[i][k] += A[i][j]  * map.get(j).get(k);
                        }
                    }
                }
            }
            return C;
        }
    }
}
