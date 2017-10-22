package LeetCode.Easy;

/**
 * Given a positive integer, check whether it has alternating bits: namely,
 * if two adjacent bits will always have different values.
 *
 * Example 1:
 * Input: 5
 * Output: True
 * Explanation:
 * The binary representation of 5 is: 101
 *
 * Example 2:
 * Input: 7
 * Output: False
 * Explanation:
 * The binary representation of 7 is: 111.
 *
 * Example 3:
 * Input: 11
 * Output: False
 * Explanation:
 * The binary representation of 11 is: 1011.
 *
 * Example 4:
 * Input: 10
 * Output: True
 * Explanation:
 * The binary representation of 10 is: 1010.

 * Created by WinnieZhao on 10/21/2017.
 */
public class BinaryNumberWithAlternatingBits {

    public boolean hasAlternatingBits(int n) {
        int d = n & 1;
        while ((n & 1) == d) {
            d ^= 1;
            n >>= 1;
        }
        return n == 0;
    }

    public static void main(String[] args) {
        BinaryNumberWithAlternatingBits solution = new BinaryNumberWithAlternatingBits();

        System.out.println(solution.hasAlternatingBits(7));
        System.out.println(solution.hasAlternatingBits(11));
        System.out.println(solution.hasAlternatingBits(10));
        System.out.println(solution.hasAlternatingBits(5));
    }
}
