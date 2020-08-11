package LeetCode.Medium;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 909. Snakes and Ladders
 * On an N x N board, the numbers from 1 to N*N are written boustrophedonically starting from the bottom left of the board, and alternating direction each row.  
 * 
 * You start on square 1 of the board (which is always in the last row and first column).  Each move, starting from square x, consists of the following:
 * 
 * You choose a destination square S with number x+1, x+2, x+3, x+4, x+5, or x+6, provided this number is <= N*N.
 * (This choice simulates the result of a standard 6-sided die roll: ie., there are always at most 6 destinations, regardless of the size of the board.)
 * If S has a snake or ladder, you move to the destination of that snake or ladder.  Otherwise, you move to S.
 * A board square on row r and column c has a "snake or ladder" if board[r][c] != -1.  The destination of that snake or ladder is board[r][c].
 * 
 * Note that you only take a snake or ladder at most once per move: if the destination to a snake or ladder is the start of another snake or ladder, 
 * you do not continue moving.  (For example, if the board is `[[4,-1],[-1,3]]`, and on the first move your destination square is `2`, 
 * then you finish your first move at `3`, because you do not continue moving to `4`.)
 * 
 * Return the least number of moves required to reach square N*N.  If it is not possible, return -1.
 * 
 * Example 1:
 * 
 * Input: [
 * [-1,-1,-1,-1,-1,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,35,-1,-1,13,-1],
 * [-1,-1,-1,-1,-1,-1],
 * [-1,15,-1,-1,-1,-1]]
 * 
 * Output: 4
 * Explanation: 
 * At the beginning, you start at square 1 [at row 5, column 0].
 * You decide to move to square 2, and must take the ladder to square 15.
 * You then decide to move to square 17 (row 3, column 5), and must take the snake to square 13.
 * You then decide to move to square 14, and must take the ladder to square 35.
 * You then decide to move to square 36, ending the game.
 * It can be shown that you need at least 4 moves to reach the N*N-th square, so the answer is 4.
 * 
 * Note:
 * 
 * 2 <= board.length = board[0].length <= 20
 * board[i][j] is between 1 and N*N or is equal to -1.
 * The board square with number 1 has no snake or ladder.
 * The board square with number N*N has no snake or ladder.
 *
 */
public class SnakesAndLadders {
    
    /**
     * Time Complexity: O(N^2) where N is the length of the board.
     * Space Complexity: O(N^2)
     * @param board
     * @return
     */
    public int snakesAndLadders(int[][] board) {
        if (board == null || board.length == 0) {
            return 0;
        }
        
        int n=board.length;
       
        boolean[][] visited = new boolean[n][n];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(1);
        visited[n-1][0] = true;
        
        int step = 0;
        while(!queue.isEmpty()) {
            int size = queue.size();
            
            for (int i=0; i<size; i++) {
                
                Integer cur = queue.poll();
                
                if (cur == n*n) {
                    return step;
                }
                
                for (int k=1; k<=6; k++) {
                    
                    if (cur + k > n*n) {
                        break;
                    }
                    
                    int pos[] = findCoordinates(cur + k, n);
                    int row=pos[0];
                    int col=pos[1];
                    
                    if (visited[row][col]) {
                        continue;
                    }
                    
                    visited[row][col]=true;
                    if(board[row][col] == -1) {
                        queue.add(cur+k);
                    }
                    else {
                        queue.add(board[row][col]);
                    }
                }
            }
            step++;
        }
        
        return -1;
    }
    
    public int[] findCoordinates(int curr, int n) {
        /**
         * 然后除以n得到横坐标，对n取余得到纵坐标。但这里得到的横纵坐标都还不是正确的，因为前面说了数字标记是蛇形环绕的，
         * 当行号是奇数的时候，列数需要翻转一下，即用 n-1 减去当前列数。又因为代码中的二维数组起点位置在左上角，
         * 同样需要翻转一样，这样得到的才是正确的横纵坐标
         */
        int x = (curr - 1) / n;
        int y = (curr - 1) % n;
        if (x % 2 == 1) {
            y = n - 1 - y;
        }
        x = n - 1 - x;
        return new int[] {x, y};
    }

}
