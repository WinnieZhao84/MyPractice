package LeetCode.Easy;

/**
 * Given an integer, write a function to determine if it is a power of two.
 * @author ASUS-PC
 *
 */
public class PowerOfTwo {
    public boolean isPowerOfTwo(int n) {
    	if (n <=0) return false;
        return (n & (n-1)) == 0 ;
    }
}
