package LeetCode.Interview.Amazon.LevelOne;

/**
 * 给一个int[][]的matirx，对于一条从左上到右下的path pi，pi中的最小值是mi，求所有mi中的最大值。只能往下或右.
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class MaximumMinimumPath {

    public static int find(int[][] input) {
        int m = input.length;
        int n = input[0].length;

        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i == 0 && j == 0) {
                    continue;
                }
                int a = Integer.MIN_VALUE;
                int b = Integer.MIN_VALUE;

                if(i - 1 >= 0) {
                    a = Math.min(input[i][j], input[i - 1][j]);
                }
                if(j - 1 >= 0) {
                    b = Math.min(input[i][j], input[i][j - 1]);
                }
                input[i][j] = Math.max(a, b);
            }
        }
        return input[m - 1][n - 1];
    }

    public static void main(String[] args) {
        int[][] input = {{2, 2, 3 ,4}, {1, 2, 3, 4},{1, 2, 3, 4}};
        System.out.println(find(input));
    }
}
