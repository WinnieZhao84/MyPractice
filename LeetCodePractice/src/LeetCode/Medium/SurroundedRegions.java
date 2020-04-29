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
 * BFS Time Complexity: O(mn) Space Complexity: O(mn)
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

    /**
     * DFS solution
     * Time Complexity: O(mn) Space Complexity: O(mn)
     */
    class Solution {
        public void solve(char[][] board) {
            if (board == null || board.length == 0) {
                return;
            }

            int m = board.length;
            int n = board[0].length;

            boolean[][] visited = new boolean[m][n];
            for (int i=0; i<m; i++) {
                for (int j=0; j<n; j++) {

                    if ((i == 0 || j ==0 || i==m-1 || j== n-1) && board[i][j] == 'O') {
                        dfs(board, i, j, m, n, visited);
                    }
                }
            }

            for (int i=0; i<m; i++) {
                for (int j=0; j<n; j++) {
                    if (board[i][j] == 'B') {
                        board[i][j] = 'O';
                    }
                    else if (board[i][j] == 'O') {
                        board[i][j] = 'X';
                    }
                }
            }
        }

        private void dfs(char[][] board, int x, int y, int m, int n, boolean[][] visited) {
            if (x < 0 || y < 0 || x >= m || y >=n || board[x][y] == 'X' || visited[x][y]) {
                return;
            }

            board[x][y] = 'B';
            visited[x][y] = true;

            dfs(board, x+1, y, m, n, visited);
            dfs(board, x-1, y, m, n, visited);
            dfs(board, x, y+1, m, n, visited);
            dfs(board, x, y-1, m, n, visited);
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
