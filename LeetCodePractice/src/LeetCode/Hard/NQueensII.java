package LeetCode.Hard;

import java.util.ArrayList;
import java.util.List;

/**
 * Follow up for N-Queens problem.
 * Now, instead outputting board configurations, return the total number of distinct solutions.
 *
 * Created by WinnieZhao on 4/19/2017.
 */
public class NQueensII {

    int count = 0;
    public int totalNQueens(int n) {

        char[][] board = new char[n][n];
        for(int i=0; i< n;i++){
            for(int j=0; j< n; j++){
                board[i][j] = '.';
            }
        }

        this.dfs(board, n, 0);
        return count;
    }

    private void dfs(char[][] board, int n, int row) {
        if (row == n) {
            count++;
            return;
        }

        for (int col=0; col<n; col++) {
            if (this.isValid(board, row, col)) {
                board[row][col] = 'Q';
                this.dfs(board, n, row+1);
                board[row][col] = '.';
            }
        }
    }

    private boolean isValid(char[][] board, int row, int col) {
        // for same row
        for (int i=row-1; i>=0; i--) {
            if (board[i][col] == 'Q') return false;
        }

        // for diagonal from left top to current point
        for (int i=row-1, j=col-1; i>=0 && j>=0; i--,j--) {
            if (board[i][j] == 'Q') return false;
        }

        // for diagonal from right top to current point
        for (int i=row-1, j=col+1; i>=0 && j<board.length; i--, j++) {
            if (board[i][j] == 'Q') return false;
        }
        return true;
    }
}
