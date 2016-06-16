package LeetCode.Easy;

/**
 * You are climbing a stair case. It takes n steps to reach to the top.

 * Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?
 * 
 * @author WinnieZhao
 *
 */
public class ClimbStairs {

    public int climbStairs(int n) {
        
        if (n <= 0) return 0;
        
        int result[] = new int[n+1];
        result[0] = 1;
        result[1] = 1;
        
        for (int i=2; i<=n; i++) {
            result[i] = result[i-1] + result[i-2];
        }
        
        return result[n];
    }
    
    public static void main(String[] args) {
        ClimbStairs solution = new ClimbStairs();
        
        System.out.println(solution.climbStairs(3));
        
        System.out.println(solution.climbStairs(20));
    }
}
