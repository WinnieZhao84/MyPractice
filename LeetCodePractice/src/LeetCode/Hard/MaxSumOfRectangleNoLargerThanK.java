package LeetCode.Hard;

import java.util.TreeSet;

/**
 * Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such
 * that its sum is no larger than k.
 *
 * Example:
 * Given matrix = [
 *   [1,  0, 1],
 *   [0, -2, 3]
 * ]
 * k = 2
 * The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).
 *
 * Note:
 * The rectangle inside the matrix must have an area > 0.
 * What if the number of rows is much larger than the number of columns?

 * Created by WinnieZhao on 7/3/2017.
 */
public class MaxSumOfRectangleNoLargerThanK {

    /** first consider the situation matrix is 1D we can save every sum of 0~i(0<=i<len)
     * and binary search previous sum to find possible result for every index, time complexity is O(NlogN).
     * so in 2D matrix, we can sum up all values from row i to row j and create a 1D array
     * to use 1D array solution.
     * If col number is less than row number, we can sum up all values from col i to col j
     * then use 1D array solution.
     **/
    public int maxSumSubmatrix(int[][] matrix, int k) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            return 0;
        }

        int row = matrix.length;
        if(row==0) return 0;
        int col = matrix[0].length;

        int m = Math.min(row,col);
        int n = Math.max(row,col);

        //indicating sum up in every row or every column
        boolean colIsBig = col>row;
        int res = Integer.MIN_VALUE;

        for(int i = 0;i<m;i++){
            int[] array = new int[n];
            // sum from row j to row i
            for(int j = i; j>=0; j--){
                int sum = 0;
                TreeSet<Integer> set = new TreeSet<>();
                set.add(0);
                //traverse every column/row and sum up
                for(int x = 0; x<n; x++){
                    array[x] = array[x] + (colIsBig ? matrix[j][x] : matrix[x][j]);

                    sum = sum + array[x];

                    // use TreeMap to binary search previous sum to get possible result
                    // find a least value as subres，make sum – k <= x，so it will ensure => sum – x <=k
                    Integer subres = set.ceiling(sum-k);
                    if (subres != null) {
                        res=Math.max(res,sum-subres);
                    }
                    set.add(sum);
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaxSumOfRectangleNoLargerThanK solution = new MaxSumOfRectangleNoLargerThanK();
        int[][] matrix = {{1,  0, 1},{0, -2, 3}};

        System.out.println(solution.maxSumSubmatrix(matrix, 2));
    }
}
