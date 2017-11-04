package LeetCode.Medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 1. If click on a mine ('M'), mark it as 'X', stop further search.
 * 2. If click on an empty cell ('E'), depends on how many surrounding mine:
 * 2.1: Has surrounding mine(s), mark it with number of surrounding mine(s), stop further search.
 * 2.2: No surrounding mine, mark it as 'B', continue search its 8 neighbors.

 * Created by WinnieZhao on 2017/3/22.
 */
public class Minesweeper {
    int row;
    int column;
    int[][]direction = {{0,1},{1,0},{-1,0},{0,-1},{-1,-1},{1,1},{-1, 1},{1,-1}};
    char[][] board;
    class Board {
        int x;
        int y;
        Board(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];

        this.board = board;
        row = board.length;
        column = board[0].length;

        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }
        
        Queue<Board> queue = new LinkedList<>();
        queue.add(new Board(x, y));
        
        while (!queue.isEmpty()) {
            Board bd = queue.poll();
            int m = bd.x;
            int n = bd.y;
            
            if (board[m][n] == 'M') {
                board[m][n] = 'X';
            }

            if (board[m][n] == 'E') {
                this.markMine(queue, m, n);
            }
        }

        return board;
    }
    
    private void markMine(Queue<Board> queue, int i, int j) {
        int mineCount = 0;
        for (int[] dir : direction) {
            int x = i + dir[0];
            int y = j + dir[1];
            
            if (x<0 || y <0 || x>=row || y>=column) {
                continue;
            }
            
            if (board[x][y] == 'M') {
                mineCount++;
            }
        }
        if (mineCount > 0) {
            board[i][j] = (char) (mineCount + '0');
        }
        else {
            board[i][j] = 'B';
            this.addAdjChars(queue, i, j);
        }
    }
    
    private void addAdjChars(Queue<Board> queue, int i, int j) {
        for (int[] dir : direction) {
            
            int x = i + dir[0];
            int y = j + dir[1];
            
            if (x<0 || y <0 || x>=row || y>=column) {
                continue;
            }
            queue.add(new Board(x, y));
        }
        return;
    }
    
    public static void main(String[] args) {
        Minesweeper solution = new Minesweeper();
        char[][] board = {{'E', 'E', 'E', 'E', 'E'},
                          {'E', 'E', 'M', 'E', 'E'},
                          {'E', 'M', 'E', 'E', 'E'},
                          {'E', 'M', 'E', 'E', 'E'}};
        int[] click = {3,0};
        
        char[][] result = solution.updateBoard(board, click);
        for (char[] row : result) {
            System.out.println(Arrays.toString(row));
        }
    }
}
