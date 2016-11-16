package LeetCode.Medium;

/**
 * Write an efficient algorithm that searches for a value in an m x n matrix. This matrix has the following properties:
 * 
 * Integers in each row are sorted from left to right. 
 * The first integer of each row is greater than the last integer of the previous row.
 * 
 * For example, 
 * 
 * Consider the following matrix:
 * [
 *   [1,   3,  5,  7],
 *   [10, 11, 16, 20],
 *   [23, 30, 34, 50]
 * ]
 * 
 * Given target = 3, return true.

 * @author WinnieZhao
 *
 */
public class Search2DMatrix {

    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix == null || matrix.length == 0) return false;
        
        int row = matrix.length;
        int column = matrix[0].length;
        int totLength = row * column;
        
        int start = 0;
        int end = totLength-1;
        while (start <= end) {
            int mid = start + (end - start)/2;
            
            int rowIndex = mid/column;
            int columnIndex = mid%column;
            
            if (matrix[rowIndex][columnIndex] == target) {
                return true;
            }
            
            if (matrix[rowIndex][columnIndex] > target) {
                end = mid-1;
            }
            else {
                start = mid+1;
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        int[][] matrix = {{1,2},{4,5}};
        
        Search2DMatrix solution = new Search2DMatrix();
        System.out.println(solution.searchMatrix(matrix, 2));
    }
}
