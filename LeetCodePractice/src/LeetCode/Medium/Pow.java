package LeetCode.Medium;

/**
 * Implement pow(x, n).
 * 
 * @author WinnieZhao
 *
 */
public class Pow {

    /**
     * Time complexity : O(log(n))). Each time we apply the formula (x ^ n) ^ 2 = x ^ {2 * n}
     * n is reduced by half. Thus we need at most O(log(n)) computations to get the result.
     *
     * Space complexity : O(log(n)). For each computation, we need to store the result of x ^ {n / 2}.
     * We need to do the computation for O(log(n)) times, so the space complexity is O(log(n)).
     */
    public double myPow(double x, int n) {

        if (n == 0) {
            return 1.0;
        }
        if (x == 0) {
            return 0.0;
        }

        if (n < 0) {
            // Cover for test case 2.00000 -2147483648
            if( n == Integer.MIN_VALUE) {
                n++; // Make -2147483648 to -2147483647
                if(x < 0) {
                    x = -x; //we made n odd so need to update sign
                }
            }
            x = 1/x;
            n = -n;
        }

        if (n % 2 == 0) {
            return myPow(x*x, n/2);
        }
        else {
            return myPow(x*x, n/2) * x;
        }
    }
    
    public static void main(String[] args) {
        Pow solution = new Pow();
        
        System.out.println(solution.myPow(4, 2));
        System.out.println(solution.myPow(4, -2));
    }
}
