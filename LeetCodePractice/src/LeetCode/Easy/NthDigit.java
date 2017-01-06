package LeetCode.Easy;

/**
 * Find the nth digit of the infinite integer sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ...
 * Note:
 * n is positive and will fit within the range of a 32-bit signed integer (n < 231).
 * 
 * Example 1:
 * Input: 3
 * Output: 3
 * 
 * Example 2:
 * Input: 11
 * Output: 0
 * 
 * Explanation:
 * The 11th digit of the sequence 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, ... is a 0, which is part of the number 10.
 * 
 * @author WinnieZhao
 *
 */
public class NthDigit {
    public int findNthDigit(int n) {
        
        int length = 1;
        long multiple = 9;
        int start = 1;
        
        while (n > length * multiple) {
            n -= length * multiple;
            length++;
            multiple *= 10;
            start *= 10;
        }
        
        start += (n - 1) / length;
        String s = Integer.toString(start);
        return s.charAt((n - 1) % length) - '0';
    }
    
    public static void main(String[] args) {
        NthDigit solution = new NthDigit();
        System.out.println(solution.findNthDigit(15));
    }
}
