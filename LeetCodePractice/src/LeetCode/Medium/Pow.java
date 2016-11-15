package LeetCode.Medium;

/**
 * Implement pow(x, n).
 * 
 * @author WinnieZhao
 *
 */
public class Pow {

    public double myPow(double x, int n) {
        
        if(n == 0) { return 1.0; }
        if(x == 0) { return 0.0; }
        
        if(n % 2 == 0) {
            return myPow(x * x, n / 2);
        } else {
            return (n > 0 ? x : 1.0 / x ) * myPow(x * x, n / 2) ;
        }
    }
    
    public static void main(String[] args) {
        Pow solution = new Pow();
        
        System.out.println(solution.myPow(4, 2));
        System.out.println(solution.myPow(4, -2));
    }
}
