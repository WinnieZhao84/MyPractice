package LeetCode.Medium;

/**
 * Implement int sqrt(int x). 
 * 
 * Compute and return the square root of x.

 * @author WinnieZhao
 *
 */
public class SqrtX {

    public int mySqrt(int x) {
        
        if (x<=1) {
            return x;
        }
        
        int left = 1, right = x;
        while (left <= right) {
            
            int mid = left + (right - left) / 2;
            if (mid == x / mid) {
                return mid;
            } 
            else if (mid < x / mid) {
                left = mid + 1;
            } 
            else {
                right = mid - 1;
            }
        }
        
        return right;
    }
    
    public static void main(String[] args) {
        SqrtX solution = new SqrtX();
        
        System.out.println(solution.mySqrt(12));
    }
}
