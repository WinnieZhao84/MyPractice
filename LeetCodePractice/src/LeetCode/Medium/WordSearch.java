package LeetCode.Medium;

/**
 * Given a 2D board and a word, find if the word exists in the grid.
 * 
 * The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally 
 * or vertically neighboring. The same letter cell may not be used more than once.
 * 
 * For example,
 * 
 * Given board =
 * [
 *   ['A','B','C','E'],
 *   ['S','F','C','S'],
 *   ['A ','D','E','E']
 * ]
 * 
 * word = "ABCCED", -> returns true,
 * word = "SEE", -> returns true,
 * word = "ABCB", -> returns false.
 * 
 * @author WinnieZhao
 *
 */
public class WordSearch {

    /**
     * The time complexity is (mn*4^k) where k is the length of the string; mn for for loop and for the dfs method its 4^k.
     * Since the dfs method goes only as deep as the word length we have T(k)=4(T(k-1))=4*4T(k-2)=....=.. which will be 4^k.
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        if (board == null || board.length == 0 || word == null) {
            return false;
        }

        int row = board.length;
        int col = board[0].length;

        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (this.exist(board, word, row, col, i, j, 0)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean exist(char[][] board, String word, int row, int col, int i, int j, int pos) {

        if (pos == word.length()) {
            return true;
        }

        if (i<0 || j<0 || i>=row || j>=col || board[i][j] != word.charAt(pos)) {
            return false;
        }

        board[i][j] = '*';

        boolean res = this.exist(board, word, row, col, i-1, j, pos+1)
                || this.exist(board, word, row, col, i+1, j, pos+1)
                || this.exist(board, word, row, col, i, j-1, pos+1)
                || this.exist(board, word, row, col, i, j+1, pos+1);

        board[i][j] = word.charAt(pos);

        return res;
    }
    
    public static void main(String[] args) {
        WordSearch solution = new WordSearch();
        
        char[][] board = { {'A','B','C','E'}, {'S','F','C','S'}, {'A','D','E','E'} };

        System.out.println(solution.exist(new char[][] {{'a'}}, "ab"));
        System.out.println(solution.exist(board, "ABCCED"));
        System.out.println(solution.exist(board, "SEE"));
        System.out.println(solution.exist(board, "ABCB"));
    }
}
