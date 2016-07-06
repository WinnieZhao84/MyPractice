package LeetCode.Medium;

/**
 * Given a positive integer n, break it into the sum of at least two positive integers and maximize the product of those integers. Return the maximum product you can get.
 * 
 * For example, given n = 2, return 1 (2 = 1 + 1); given n = 10, return 36 (10 = 3 + 3 + 4).
 * Note: You may assume that n is not less than 2 and not larger than 58.
 * 
 * @author WinnieZhao
 *
 */
public class IntegerBreak {

    /**
     * 2 == 1 + 1; result = 1。
     * 3 == 1 + 2; result = 2。
     * 4 == 2 + 2; result = 4
     * 5 == 2 + 3; result = 6。
     * 6 == 3 + 3; result = 9
     * 7 == 3 + 4; result = 12
     * 8 == 3 + 3 + 2; result = 18。
     * 9 == 3 + 3 + 3; result = 27
     * 10 == 3 + 3 + 4; result = 36
     * 
     * All the combinations with most "3" will be the biggest product result
     * when n > 4, find all 3 until remaining is 2

     * @param n
     * @return
     */
    public int integerBreak(int n) {
        
        if (n == 2) return 1;
        if (n == 3) return 2;
        if (n == 4) return n;
        
        int result = 1;
        while (n>4) {
            n = n - 3;
            result = result * 3;
        }
        
        result = result * n;
        return result;
    }
    
    public static void main(String[] args) {
        IntegerBreak solution = new IntegerBreak();
        System.out.println(solution.integerBreak(9));
    }
}
