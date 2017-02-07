package LeetCode.Medium;

/**
 * 
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND of all numbers in this range, inclusive.
 * 
 * For example, given the range [5, 7], you should return 4.

 * @author WinnieZhao
 *
 */
public class BitwiseANDOfNumbersRange {

    
    // Time Limit Exceeded
    public int rangeBitwiseAnd(int m, int n) {
        if (m==0) return 0;
        if (m == n) return m;
        
        int res = m;
        for (int i=m+1; i<=n; i++) {
            res = res & i;
        }
        
        return res;
    }
    
    // last bit of (odd number & even number) is 0.
    // when m != n, There is at least an odd number and an even number, so the last bit position result is 0.
    // Move m and n rigth a position.
    // Keep doing step 1,2,3 until m equal to n, use a factor to record the iteration time.
    public int rangeBitwiseAnd_pass(int m, int n) {
        if(m == 0){
            return 0;
        }
        int i = 0;
        while (m != n) {
            m >>= 1;
            n >>= 1;
            ++i;
        }
        return (m << i);
    }
    
    public static void main(String[] args) {
        BitwiseANDOfNumbersRange solution = new BitwiseANDOfNumbersRange();
        
        System.out.println(solution.rangeBitwiseAnd_pass(2, 4));
    }
}
