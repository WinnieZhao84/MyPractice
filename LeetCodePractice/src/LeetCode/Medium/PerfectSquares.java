package LeetCode.Medium;

import java.util.Arrays;

/**
 * Given a positive integer n, find the least number of perfect square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * 
 * For example, given n = 12, return 3 because 12 = 4 + 4 + 4; given n = 13, return 2 because 13 = 4 + 9.

 * @author WinnieZhao
 *
 */
public class PerfectSquares {

    /**
     * dp[0] = 0
     * dp[1] = dp[0]+1 = 1
     * dp[2] = dp[1]+1 = 2
     * dp[3] = dp[2]+1 = 3
     * dp[4] = Min{ dp[4-1*1]+1, dp[4-2*2]+1 }
     *       = Min{ dp[3]+1, dp[0]+1 }
     *       = 1
     * dp[5] = Min{ dp[5-1*1]+1, dp[5-2*2]+1 }
     *       = Min{ dp[4]+1, dp[1]+1 }
     *       = 2
     * .
     * .
     * .
     * dp[13] = Min{ dp[13-1*1]+1, dp[13-2*2]+1, dp[13-3*3]+1 }
     *        = Min{ dp[12]+1, dp[9]+1, dp[4]+1 }
     *        = 2
     * .
     * .
     * .
     * dp[n] = Min{ dp[n - i*i] + 1 },  n - i*i >=0 && i >= 1
     *
     */
    public int numSquares(int n) {
        if (n <= 0) {
            return 0;
        }

        int[] dp = new int[n+1];

        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i=1; i<=n; i++) {

            for (int j=1; j*j<=i; j++) {
                dp[i] = Math.min(dp[i], dp[i-j*j] + 1);
            }
        }

        return dp[n];
    }
    
    public int numSquares_BFS(int n) {
        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        
        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            step++;
            
            for (int i=0; i<size; i++) {
                Integer cur = queue.poll();
                
                for (int j=1; j<n; j++) {
                    int sum = j*j + cur;
                    if (sum == n) {
                        return step;
                    }
                    else if (sum < n) {
                        if (!visited.contains(sum)) {
                            queue.add(sum);
                            visited.add(sum);
                        }
                    } 
                    else {
                        break;
                    }
                }
            }
        }
        return step;
    }
    
    public static void main(String[] args) {
        PerfectSquares solution = new PerfectSquares();
        
        System.out.println(solution.numSquares(6));
        System.out.println(solution.numSquares(12));
    }
    
}
