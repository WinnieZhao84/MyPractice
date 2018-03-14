package LeetCode.Medium;

/**
 * Implement int sqrt(int x). 
 * 
 * Compute and return the square root of x.

 * @author WinnieZhao
 *
 */
public class SqrtX {

    //Time complexity = O(lg(x)) = O(32)=O(1)
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

    /**
     * Solution2 - Newton Solution: Time complexity = O(lg(x))
     * Newton solution will not be faster than Solution1(Binary Search),
     * because i = (i + x / i) / 2, the two factors i and x / i are with opposite trends.
     * So time complexity in the best case is O(lgx).
     */
    public class Solution {
        public int mySqrt(int x) {
            if (x == 0) {
                return 0;
            }

            long i = x;
            while(i > x / i) {
                i = (i + x / i) / 2;
            }
            return (int)i;
        }
    }

    // Solution3 - Brute Force: Time complexity = O(sqrt(x))
    public int sqrt(int x) {
        if (x == 0) {
            return 0;
        }

        for (int i = 1; i <= x / i; i++)
            if (i <= x / i && (i + 1) > x / (i + 1))// Look for the critical point: i*i <= x && (i+1)(i+1) > x
                return i;
        return -1;
    }


    public static void main(String[] args) {
        SqrtX solution = new SqrtX();
        
        System.out.println(solution.mySqrt(12));
    }
}
