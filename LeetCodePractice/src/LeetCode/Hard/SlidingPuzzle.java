package LeetCode.Hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 773. Sliding Puzzle
 * On a 2x3 board, there are 5 tiles represented by the integers 1 through 5, and an empty square represented by 0.
 * A move consists of choosing 0 and a 4-directionally adjacent number and swapping it.
 * 
 * The state of the board is solved if and only if the board is [[1,2,3],[4,5,0]].
 * 
 * Given a puzzle board, return the least number of moves required so that the state of the board is solved. If it is impossible for the state of the board to be solved, return -1.
 * 
 * Examples:
 * Input: board = [[1,2,3],[4,0,5]]
 * Output: 1
 * Explanation: Swap the 0 and the 5 in one move.
 * Input: board = [[1,2,3],[5,4,0]]
 * Output: -1
 * Explanation: No number of moves will make the board solved.
 * 
 * Input: board = [[4,1,2],[5,0,3]]
 * Output: 5
 * Explanation: 5 is the smallest number of moves that solves the board.
 * An example path:
 * After move 0: [[4,1,2],[5,0,3]]
 * After move 1: [[4,1,2],[0,5,3]]
 * After move 2: [[0,1,2],[4,5,3]]
 * After move 3: [[1,0,2],[4,5,3]]
 * After move 4: [[1,2,0],[4,5,3]]
 * After move 5: [[1,2,3],[4,5,0]]
 * Input: board = [[3,2,4],[1,5,0]]
 * Output: 14
 * 
 * Note:
 * board will be a 2 x 3 array as described above.
 * board[i][j] will be a permutation of [0, 1, 2, 3, 4, 5]
 * @author WinnieZhao
 *
 */
public class SlidingPuzzle {
    
 int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int slidingPuzzle(int[][] board) {
        int row = board.length;
        int col = board[0].length;
        
        String target = "123450";
        
        Queue<String> queue = new LinkedList<>();
        
        StringBuilder sb = new StringBuilder();
        for (int[] r : board) {
            for (int i: r) sb.append(i);
        }
        queue.add(sb.toString());
        
        Set<String> visited = new HashSet<>();
        visited.add(sb.toString());
        
        int step = 0;
        while(!queue.isEmpty()) {
            
            int size = queue.size();
            
            for (int i=0; i<size; i++) {
                String cur = queue.poll();
                
                if (cur.equals(target)) {
                    return step;
                }
                
                int index = cur.indexOf('0');
                int x = index / col;
                int y = index % col;
                
                for (int[] dir : dirs) {
                    
                    int m = x + dir[0];
                    int n = y + dir[1];
                    
                    if (m >= row || n >= col || m <0 || n < 0) {
                        continue;
                    }
                        
                    String next = swap(cur, index, m*col+n);
                    if (visited.add(next)) {
                        queue.add(next);
                    }
                }
            }
            step++;
        }
        
        return -1;
    }

    
    private String swap(String str, int i, int j) {
        char[] arr = str.toCharArray();
        char tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
        return new String(arr);
    }

}
