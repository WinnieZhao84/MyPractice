package LeetCode.Easy;

/**
 * Given a 2D integer matrix M representing the gray scale of an image, you need to design a smoother to
 * make the gray scale of each cell becomes the average gray scale (rounding down) of all the 8 surrounding
 * cells and itself. If a cell has less than 8 surrounding cells, then use as many as you can.
 *
 * Example 1:
 * Input:
 * [[1,1,1],
 *  [1,0,1],
 *  [1,1,1]]
 *
 * Output:
 * [[0, 0, 0],
 *  [0, 0, 0],
 *  [0, 0, 0]]
 *
 * Explanation:
 * For the point (0,0), (0,2), (2,0), (2,2): floor(3/4) = floor(0.75) = 0
 * For the point (0,1), (1,0), (1,2), (2,1): floor(5/6) = floor(0.83333333) = 0
 * For the point (1,1): floor(8/9) = floor(0.88888889) = 0

 * Created by WinnieZhao on 8/21/2017.
 */
public class ImageSmoother {

    public int[][] imageSmoother(int[][] M) {
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1},{0,0},{1,1},{-1,1},{-1,-1},{1,-1}};

        int row = M.length;
        int column = M[0].length;
        int[][] res = new int[row][column];

        for (int i=0; i<row; i++) {
            for (int j=0; j<column; j++) {

                int count = 0;
                int sum = 0;
                for (int[] dir: dirs) {
                    int x = dir[0] + i;
                    int y = dir[1] + j;

                    if (x<row && x>=0 && y>=0 && y<column) {
                        sum+= M[x][y];
                        count++;

                    }
                }
                res[i][j] = sum/count;
            }
        }

        return res;
    }
}
