package LeetCode.Medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Determine if a Sudoku is valid, according to: Sudoku Puzzles - The Rules.
 * The Sudoku board could be partially filled, where empty cells are filled with the character '.'.
 * A partially filled sudoku which is valid.
 * 
 * Note: 
 * A valid Sudoku board (partially filled) is not necessarily solvable. Only the filled cells need to be validated.
 * @author ASUS-PC
 *
 */
public class ValidSudoku {

    public boolean isValidSudoku(char[][] board) {
        for(int i = 0; i<9; i++){
            Set<Character> rows = new HashSet<>();
            Set<Character> columns = new HashSet<>();
            Set<Character> cube = new HashSet<>();
            for (int j = 0; j < 9;j++){
                if (board[i][j]!='.' && !rows.add(board[i][j])) {
                    return false;
                }
                if(board[j][i]!='.' && !columns.add(board[j][i])) {
                    return false;
                }
                int RowIndex = 3*(i/3);
                int ColIndex = 3*(i%3);

                /**
                 * Use % for horizontal traversal. Because % increments by 1 for each j : 0%3 = 0 , 1%3 = 1, 2%3 = 2, and resets back.
                 * So this covers horizontal traversal for each block by 3 steps.
                 * Use / for vertical traversal. Because / increments by 1 after every 3 j: 0/3 = 0; 1/3 = 0; 2/3 =0; 3/3 = 1.
                 */
                if(board[RowIndex + j/3][ColIndex + j%3]!='.' && !cube.add(board[RowIndex + j/3][ColIndex + j%3])) {
                    return false;
                }
            }
        }
        return true;
    }
    
    public static void main(String[] args) {
    	ValidSudoku solution = new ValidSudoku();
    	String[] strArr = {".87654321","2........","3........","4........","5........","6........","7........","8........","9........"};
    	
    	char[][] board = new char[9][9];
    	for (int i=0; i<strArr.length; i++) {
    		String str = strArr[i];
    		for (int j=0; j<str.length(); j++) {
    			char ch = str.charAt(j);
    			board[i][j] = ch;
    		}
    	}
    	
    	System.out.print(solution.isValidSudoku(board));
    }
}
