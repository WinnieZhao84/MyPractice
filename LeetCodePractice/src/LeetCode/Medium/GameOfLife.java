package LeetCode.Medium;

/**
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0). Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) 
 * using the following four rules (taken from the above Wikipedia article):
 * 
 * Any live cell with fewer than two live neighbors dies, as if caused by under-population.
 * Any live cell with two or three live neighbors lives on to the next generation.
 * Any live cell with more than three live neighbors dies, as if by over-population..
 * Any dead cell with exactly three live neighbors becomes a live cell, as if by reproduction.
 * 
 * Write a function to compute the next state (after one update) of the board given its current state.
 * 
 * Follow up: 
 * 
 * Could you solve it in-place? Remember that the board needs to be updated at the same time: You cannot update some cells first and then use their updated values to update other cells.
 * In this question, we represent the board using a 2D array. In principle, the board is infinite, which would cause problems when the active area encroaches the border of the array. 
 * How would you address these problems?

 * @author WinnieZhao
 *
 */
public class GameOfLife {
    
    final int DEAD = 0;
    final int LIVE = 1;
    
    // 0 -> 0
    final int DEAD_TO_DEAD = 0;
    // 1 -> 1
    final int ALIVE_TO_ALIVE = 1;
    // 1 -> 0
    final int ALIVE_TO_DEAD = 2;
    // 0 -> 1
    final int DEAD_TO_ALIVE = 3;

    private boolean isAliveOld(int obj) {
        if (obj == ALIVE_TO_ALIVE || obj == ALIVE_TO_DEAD) {
            return true;
        }
        else {
            return false;
        }
    }

    private boolean isAliveNew(int obj) {
        if (obj % 2 == 1) {
            return true;
        } else {
            return false;
        }
    }

    public void gameOfLife(int[][] board) {

        if (board == null || board.length == 0) return;
        
        int row = board.length;
        int column = board[0].length;
        
        int lives = 0;
        for (int i=0; i<row; i++) {
            for (int j=0; j<column; j++) {
                
                lives = 0;
                if (i > 0 && j > 0 && isAliveOld(board[i - 1][j - 1])) {
                    lives++;
                }
                if (i > 0 && isAliveOld(board[i - 1][j])) {
                    lives++;
                }
                if (i > 0 && j < column - 1 && isAliveOld(board[i - 1][j + 1])) {
                    lives++;
                }
                if (j > 0 && isAliveOld(board[i][j - 1])) {
                    lives++;
                }
                if (j < column - 1 && isAliveOld(board[i][j + 1])) {
                    lives++;
                }
                if (i < row - 1 && j > 0 && isAliveOld(board[i + 1][j - 1])) {
                    lives++;
                }
                if (i < row - 1 && isAliveOld(board[i + 1][j])) {
                    lives++;
                }
                if (i < row - 1 && j < column - 1 && isAliveOld(board[i + 1][j + 1])) {
                    lives++;
                }
                
                if (isAliveOld(board[i][j])) {
                    if (lives < 2) {
                        board[i][j] = ALIVE_TO_DEAD;
                    } 
                    else if (lives == 2 || lives == 3) {
                        board[i][j] = ALIVE_TO_ALIVE;
                    } 
                    else {
                        board[i][j] = ALIVE_TO_DEAD;
                    }
                } 
                else {
                    if (lives == 3) {
                        board[i][j] = DEAD_TO_ALIVE;
                    } else {
                        board[i][j] = DEAD_TO_DEAD;
                    }
                }
            }
        }
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                if (isAliveNew(board[i][j])) {
                    board[i][j] = LIVE;
                } 
                else {
                    board[i][j] = DEAD;
                }
            }
        }
    }
    

    class Solution {

        /**
         * [2nd bit, 1st bit] = [next state, current state]
         * 00  dead (next) <- dead (current)
         * 01  dead (next) <- live (current)
         * 10  live (next) <- dead (current)
         * 11  live (next) <- live (current)
         */

        int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0},{-1,-1},{1,1},{1,-1}, {-1,1}};

        public void gameOfLife(int[][] board) {

            if (board == null || board.length == 0) return;

            int row = board.length;
            int column = board[0].length;

            for (int i=0; i<row; i++) {
                for (int j = 0; j < column; j++) {

                    int lives = this.getNeighborLives(board, i, j, row, column);

                    if (board[i][j] == 1 && lives >= 2 && lives <= 3) {
                        board[i][j] = 3;
                    }
                    if (board[i][j] == 0 && lives == 3) {
                        board[i][j] = 2;
                    }
                }
            }

            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    board[i][j] >>= 1;  // Get the 2nd state.
                }
            }
        }

        private int getNeighborLives(int[][] board, int i, int j, int row, int col) {
            int lives = 0;
            for (int[] dir : dirs) {
                int x = i + dir[0];
                int y = j + dir[1];

                if (x >=0 && y >=0 && x < row && y < col) {
                    lives += (board[x][y] & 1);
                }
            }

            return lives;
        }
    }
}
