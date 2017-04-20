package LeetCode.Hard;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 *
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * Each solution contains a distinct board configuration of the n-queens' placement,
 * where 'Q' and '.' both indicate a queen and an empty space respectively.
 *
 * For example,
 * There exist two distinct solutions to the 4-queens puzzle:
 * [
 *   [".Q..",  // Solution 1
 *    "...Q",
 *    "Q...",
 *    "..Q."],
 *
 *   ["..Q.",  // Solution 2
 *    "Q...",
 *    "...Q",
 *    ".Q.."]
 * ]
 *
 * Created by WinnieZhao on 4/19/2017.
 */
public class NQueens {

    List<List<String>> res = new ArrayList<>();

    public List<List<String>> solveNQueens(int n) {

        char[][] board = new char[n][n];
        for(int i=0; i< n;i++){
            for(int j=0; j< n; j++){
                board[i][j] = '.';
            }
        }

        this.dfs(board, n, 0);
        return res;
    }

    private void dfs(char[][] board, int n, int row) {
        if (row == n) {
            List<String> list = new ArrayList<>();
            for (char[] chs : board) {
                String str = String.valueOf(chs);
                list.add(str);
            }
            res.add(list);
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
