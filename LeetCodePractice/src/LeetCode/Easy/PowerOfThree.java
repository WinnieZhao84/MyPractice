package LeetCode.Easy;

/**
 * Given an integer, write a function to determine if it is a power of three.
 * 
 * Follow up:
 * Could you do it without using any loop / recursion?
 * 
 * @author ASUS-PC
 *
 */
public class PowerOfThree {
    public boolean isPowerOfThree(int n) {
    	
    	if (n<=0) return false;
    	if (n==1) return true;

        while (n >= 3) {
        	if (n%3 != 0) {
        		return false;
        	}
        	n = n/3;
        }
        
        return n == 1; 
    }
    
    public boolean isPowerOfThree_Better(int n) {
        // 1162261467 is 3^19,  3^20 is bigger than int  
        return ( n>0 &&  1162261467%n==0);
    }
    
    public static void main(String[] args) {
    	PowerOfThree solution = new PowerOfThree();
        System.out.println(solution.isPowerOfThree(81));

    }
}
