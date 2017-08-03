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
        
    	Set<Integer> valid = new HashSet<Integer>();
    	for (int i=0; i<9; i++) {
    		valid.clear();
    		for (int j=0; j<9; j++) {
    			if (board[i][j] == '.') {
    				continue;
    			}
    			if (!valid.add(board[i][j] - '0')) {
    				return false;
    			}
    		}
    	}
    	
    	for (int i=0; i<9; i++) {
    		valid.clear();
    		for (int j=0; j<9; j++) {
    			
    			if (board[j][i] == '.') {
    				continue;
    			}
    			if (!valid.add(board[j][i] - '0')) {
    				return false;
    			}
    		}
    	}
    	
    	for (int i=0; i<9; i=i+3) {
    		for (int j=0; j<9; j=j+3) {
        		valid.clear();
    			for (int k=0; k<9; k++) {
    				if (board[i+k/3][j+k%3] == '.') {
        				continue;
        			}
        			if (!valid.add(board[i+k/3][j+k%3]-'0')) {
        				return false;
        			}
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
