package LeetCode.Medium;

/**
 * Given a positive integer num, write a function which returns True if num is a perfect square else False.
 * 
 * Note: Do not use any built-in library function such as sqrt.
 * 
 * Example 1:
 * 
 * Input: 16
 * Returns: True
 * 
 * Example 2:
 * 
 * Input: 14
 * Returns: False
 * 
 * @author WinnieZhao
 *
 */
public class ValidPerfectSquare {

    /*
     * Another option:
     * 
     * This is a math problem£º
     * 1 = 1
     * 4 = 1 + 3
     * 9 = 1 + 3 + 5
     * 16 = 1 + 3 + 5 + 7
     * 25 = 1 + 3 + 5 + 7 + 9
     * 36 = 1 + 3 + 5 + 7 + 9 + 11
     * ....
     * so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = nn
     * 
     * 
     */
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        
        int start = 0;
        int end = num/2;
        
        while (start <= end) {
            int mid = start + (end - start)/2;
            
            if (mid  > num/mid) {
                end = mid -1;
            }
            else if (mid  < num/mid) {
                start = mid + 1;
            }
            else {
                if (num % mid == 0) {
                    return true;
                }
                else {
                    return false;
                }
            }
        }
        
        return false;
    }
    
    public static void main(String[] args) {
        ValidPerfectSquare solution = new ValidPerfectSquare();
        
        System.out.println(solution.isPerfectSquare(16));
        System.out.println(solution.isPerfectSquare(808201));
        System.out.println(solution.isPerfectSquare(5));
        System.out.println(solution.isPerfectSquare(14));
    }
}
