package LeetCode.Medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a 2D board containing 'X' and 'O' (the letter O), capture all regions surrounded by 'X'.
 * 
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * 
 * For example,
 * 
 * X X X X
 * X O O X
 * X X O X
 * X O X X
 * 
 * After running your function, the board should be:
 * 
 * X X X X
 * X X X X
 * X X X X
 * X O X X
 * 
 * 
 * @author WinnieZhao
 *
 */
public class SurroundedRegions {

    class Point {
        int x;
        int y;
        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public void solve(char[][] board) {
        if (board == null || board.length == 0)
            return;
        int rows = board.length, columns = board[0].length;
        int[][] direction = { { -1, 0 }, { 1, 0 }, { 0, 1 }, { 0, -1 } };
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                
                if ((i == 0 || i == rows - 1 || j == 0 || j == columns - 1) && board[i][j] == 'O') {
                    Queue<Point> queue = new LinkedList<>();
                    board[i][j] = 'B';
                    queue.offer(new Point(i, j));
                    
                    while (!queue.isEmpty()) {
                        Point point = queue.poll();
                        for (int k = 0; k < 4; k++) {
                            int x = direction[k][0] + point.x;
                            int y = direction[k][1] + point.y;
                            
                            if (x >= 0 && x < rows && y >= 0 && y < columns && board[x][y] == 'O') {
                                board[x][y] = 'B';
                                queue.offer(new Point(x, y));
                            }
                        }
                    }
                }
            }
        }
        
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (board[i][j] == 'B')
                    board[i][j] = 'O';
                else if (board[i][j] == 'O')
                    board[i][j] = 'X';
            }
        }
    }
    
    public static void main(String[] args) {
        char[][] board = new char[][]{{'X','X','X','X'},{'X', 'O', 'O', 'X'},{'X', 'X', 'O', 'X'},{'X', 'O', 'X', 'X'}};
        
        SurroundedRegions solution = new SurroundedRegions();
        solution.solve(board);
        
        for (char[] b : board) {
            System.out.println(Arrays.toString(b));
        }
        
    }
}
