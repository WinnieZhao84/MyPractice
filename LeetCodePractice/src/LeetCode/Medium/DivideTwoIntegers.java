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
        int sign = 1;
        
        long lDividend = (long)dividend;
        long lDivisor = (long)divisor;
        
        if(lDividend<0){
            lDividend = -lDividend;
            sign = -sign;
        }
        
        if(lDivisor<0){
            lDivisor = -lDivisor;
            sign = -sign;
        }
        
        long cnt = helper(lDividend, lDivisor);
        
        cnt = sign>0?cnt:-cnt;
        
        if (cnt>Integer.MAX_VALUE) 
            return Integer.MAX_VALUE;
        else if(cnt< Integer.MIN_VALUE) 
            return Integer.MIN_VALUE;
        else
            return (int)cnt;
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
        System.out.println(solution.divide(15, 3));
        System.out.println(solution.divide(8, 5));
    }
}
