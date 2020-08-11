package LeetCode.Hard;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 * 864. Shortest Path to Get All Keys
 * 
 * We are given a 2-dimensional grid. "." is an empty cell, "#" is a wall, "@" is the starting point, ("a", "b", ...) are keys, and ("A", "B", ...) are locks.
 * We start at the starting point, and one move consists of walking one space in one of the 4 cardinal directions.  We cannot walk outside the grid, or walk into a wall.  
 * If we walk over a key, we pick it up.  We can't walk over a lock unless we have the corresponding key.
 * 
 * For some 1 <= K <= 6, there is exactly one lowercase and one uppercase letter of the first K letters of the English alphabet in the grid.  
 * This means that there is exactly one key for each lock, and one lock for each key; and also that the letters used to represent the keys and locks 
 * were chosen in the same order as the English alphabet.
 * 
 * Return the lowest number of moves to acquire all keys.  If it's impossible, return -1.
 * Example 1:
 * 
 * Input: ["@.a.#","###.#","b.A.B"]
 * Output: 8
 * 
 * Example 2:
 * Input: ["@..aA","..B#.","....b"]
 * Output: 6
 * 
 * Note:
 * 1 <= grid.length <= 30
 * 1 <= grid[0].length <= 30
 * grid[i][j] contains only '.', '#', '@', 'a'-'f' and 'A'-'F'
 * The number of keys is in [1, 6].  Each key has a different letter and opens exactly one lock.
 *
 */
public class ShortestPathToGetAllKeys {
    class Node {
        int x;
        int y;
        String path;
        
        Node(int x, int y) {
            this.x=x;
            this.y=y;
            this.path = "";
        }
        
        public String toString() {
            return this.x + "-" + this.y + "-" + this.path;
        }
    }
    
    int[][] dirs = new int[][]{{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int shortestPathAllKeys(String[] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();
       
        int keys = 0;
        
        for (int i=0; i<grid.length; i++) {
            char[] chs = grid[i].toCharArray();
            for (int j=0; j<chs.length; j++) {
                char ch = chs[j];
                if (ch == '@') {
                    Node start = new Node(i, j);
                    queue.add(start);
                    visited.add(start.toString());
                }
                if (ch >= 'a' && ch <= 'z') {
                    keys++;
                }
            }
        }
        
        int step = 0;
        while(!queue.isEmpty()) {
            
            int size = queue.size();
            
            for (int i=0; i<size; i++) {
                
                Node cur = queue.poll();
                
                char curCh = grid[cur.x].charAt(cur.y);
                if (Character.isLowerCase(curCh) && cur.path.indexOf(curCh) == -1){
                   cur.path += curCh;
                }
                
                if (cur.path.length() == keys) {
                    return step;
                }
                
                for (int[] dir : dirs) {
                    int x = dir[0] + cur.x;
                    int y = dir[1] + cur.y;
                     
                    if (x<0 || y<0 || x>= grid.length || y >= grid[0].length() || grid[x].charAt(y) == '#') {
                        continue;
                    }
                    char ch = grid[x].charAt(y);
                    if (Character.isUpperCase(ch) && cur.path.indexOf(Character.toLowerCase(ch)) == -1) {
                        continue;
                    }
                    
                    Node next = new Node(x, y);
                    next.path = cur.path;
                    
                    if (visited.add(next.toString())) {
                        queue.offer(next);
                    }
                }
            }
            step++;
        }
        
        return -1;
    }
}
