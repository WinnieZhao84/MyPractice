package LeetCode.Medium;

/**
 * Divide two integers without using multiplication, division and mod operator.
 * 
 * If it is overflow, return MAX_INT.

 * @author WinnieZhao
 *
 */
public class DivideTwoIntegers {

    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == divisor) {
            return 1;
        }
        int sign = 1;

        long lDividend = (long)dividend;
        long lDivisor = (long)divisor;

        if (divisor < 0 || dividend < 0) {
            sign = -1;

            if (lDividend < 0 && lDivisor < 0) sign = 1;
            if (lDividend < 0) lDividend = -lDividend;
            if (lDivisor < 0) lDivisor = -lDivisor;
        }

        long count = this.helper(lDividend, lDivisor);

        count = sign > 0 ? count : -count;

        if (count > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        else if(count < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        else
            return (int)count;
    }
    
    private long helper(long dividend, long divisor){
        if(dividend<divisor) return 0;
        long origin = divisor;
        long cnt =1;
        while(divisor<<1 < dividend){
            divisor <<= 1;
            cnt <<=1;
        }
        if(divisor == dividend) return cnt;
        else{
            cnt+=helper(dividend-divisor, origin);
        }
        return cnt;
    }
    
    public static void main(String[] args) {
        DivideTwoIntegers solution = new DivideTwoIntegers();
        System.out.println(solution.divide(5, 2));
        System.out.println(solution.divide(8, 5));
    }
}
