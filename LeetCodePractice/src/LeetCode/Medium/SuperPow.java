package LeetCode.Medium;


/**
 * Your task is to calculate a^b mod 1337 where a is a positive integer and b is an extremely large positive integer given in the form of an array.
 * 
 * Example1:
 * a = 2
 * b = [3]
 * Result: 8
 * 
 * Example2:
 * a = 2
 * b = [1,0]
 * Result: 1024
 * 
 * @author WinnieZhao
 *
 */
public class SuperPow {
    public int superPow(int a, int[] b) {
        if (a == 0) {
            return 0;
        }
        long base = a;
        long res = 1;
        for (int count = b.length - 1; count >= 0; count--) {
            for (int i = 0; i < b[count]; i++) {
                res *= base;
                if (res == 1337) {
                    return 0;
                }
                res = res % 1337;
            }
            if (count == 0) {
                break;
            }
            long tt = base;
            for (int i = 0; i < 9; i++) {
                base *= tt;
                if (base == 1337) {
                    return 0;
                }
                base = base % 1337;
            }
        }
        return (int)res;
    }
    
    public static void main(String[] args) {
        SuperPow solution = new SuperPow();
        System.out.println(solution.superPow(2, new int[] {3, 4}));
    }
}
