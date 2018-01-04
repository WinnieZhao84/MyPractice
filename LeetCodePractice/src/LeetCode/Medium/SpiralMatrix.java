package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.
 * 
 * For example,
 * Given the following matrix:
 * [
 *  [ 1, 2, 3 ],
 *  [ 4, 5, 6 ],
 *  [ 7, 8, 9 ]
 * ]
 * 
 * You should return [1,2,3,6,9,8,7,4,5].

 * @author WinnieZhao
 *
 */
public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        
        if (matrix == null || matrix.length == 0) {
            return res;
        }

        int rowEnd=matrix.length-1;
        int colEnd=matrix[0].length-1;

        int rowStart=0;
        int colStart=0;
        
        while(rowStart <= rowEnd && colStart <= colEnd) {
            // Traverse Right
            for (int i=colStart; i<=colEnd; i++) {
                res.add(matrix[rowStart][i]);
            }
            rowStart++;
            
            // Traverse down
            for (int i=rowStart; i<=rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd--;
            
            // Traverse left
            if (rowStart <= rowEnd) {
                for (int i=colEnd; i>=colStart; i--) {
                    res.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;
            
            // Traverse up
            if (colStart <= colEnd) {
                for (int i=rowEnd; i>=rowStart; i--) {
                    res.add(matrix[i][colStart]);
                }
            }
            colStart++;
        }
        
        return res;
    }
    
    public static void main(String[] args) {
        SpiralMatrix solution = new SpiralMatrix();
        int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        
        System.out.println(solution.spiralOrder(matrix));
    }
}
