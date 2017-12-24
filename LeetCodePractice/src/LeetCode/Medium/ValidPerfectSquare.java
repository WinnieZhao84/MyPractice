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
     * This is a math problem:
     * 1 = 1
     * 4 = 1 + 3
     * 9 = 1 + 3 + 5
     * 16 = 1 + 3 + 5 + 7
     * 25 = 1 + 3 + 5 + 7 + 9
     * 36 = 1 + 3 + 5 + 7 + 9 + 11
     * ....
     * so 1+3+...+(2n-1) = (2n-1 + 1)n/2 = nn
     * 
     * Time complexity is O(sqrt(n))
     */
    public boolean isPerfectSquare(int num) {
        int i = 1;
        while (num > 0) {
            num -= i;
            i += 2;
        }
        return num == 0;
    }

    /**
     * More efficient one using binary search
     * Time complexity is O(log(n)):
     *
     * @param num
     * @return
     */
    public boolean isPerfectSquare_better(int num) {
        if (num == 1) return true;

        int low = 1;
        int high = num;
        while (low <= high) {
            long mid = (low + high) >>> 1;

            if (mid * mid == num) {
                return true;
            }
            else if (mid * mid < num) {
                low = (int) mid + 1;
            }
            else {
                high = (int) mid - 1;
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
