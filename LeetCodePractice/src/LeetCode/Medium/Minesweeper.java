package LeetCode.Medium;

/**
 * Created by WinnieZhao on 2017/3/22.
 */
public class Minesweeper {

    int row;
    int column;
    public char[][] updateBoard(char[][] board, int[] click) {
        int x = click[0];
        int y = click[1];

        row = board.length;
        column = board[0].length;

        if (board[x][y] == 'M') {
            board[x][y] = 'X';
            return board;
        }

        this.updateBoard(board, x, y);

        return board;
    }

    private void updateBoard(char[][]board, int i, int j) {
        if (i<0 || i>=row || j<0 || j>=column) {
            return;
        }

        if (board[i][j] == 'E') {
            board[i][j] = 'B';

            updateBoard(board, i-1, j);
            updateBoard(board, i+1, j);
            updateBoard(board, i, j-1);
            updateBoard(board, i, j+1);

            updateBoard(board, i-1, j-1);
            updateBoard(board, i+1, j+1);
            updateBoard(board, i-1, j+1);
            updateBoard(board, i+1, j-1);
        }
        else if (board[i][j] == 'M') {
            board[i-1][j] = '1';
            board[i+1][j] = '1';
            board[i][j-1] = '1';
            board[i][j+1] = '1';

            board[i-1][j-1] = '1';
            board[i+1][j+1] = '1';
            board[i-1][j+1] = '1';
            board[i+1][j-1] = '1';
            return;
        }
    }
}
