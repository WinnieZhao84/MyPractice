package LeetCode.Hard;

/**
 * Start from integer 1, remove any integer that contains 9 such as 9, 19, 29...
 *
 * So now, you will have a new integer sequence: 1, 2, 3, 4, 5, 6, 7, 8, 10, 11, ...
 *
 * Given a positive integer n, you need to return the n-th integer after removing.
 * Note that 1 will be the first integer.
 *
 * Example 1:
 *
 * Input: 9
 * Output: 10
 *
 * Hint: n will not exceed 9 x 10^8.

 * Created by WinnieZhao on 8/19/2017.
 */
public class Remove9 {

     // 9 -> 10; 19 -> 21
    public int newInteger(int n) {
        return Integer.valueOf(Integer.toString(n,9));
    }

    public int newInteger_DIY(int n) {
        int res=0;
        int base = 1;
        while(n>0) {
            res += n%9 * base;
            n = n/9;
            base = base * 10;
        }
        return res;
    }

    public static void main(String[] args) {
        Remove9 solution = new Remove9();
        System.out.println(solution.newInteger_DIY(19));
    }
}
