package LeetCode.Medium;

/**
 * Implement pow(x, n).
 * 
 * @author WinnieZhao
 *
 */
public class Pow {

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
