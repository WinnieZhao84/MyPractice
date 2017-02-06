package LeetCode.Medium;

/**
 * A magical string S consists of only '1' and '2' and obeys the following rules:
 * 
 * The string S is magical because concatenating the number of contiguous occurrences of characters '1' and '2' generates the string S itself.
 * 
 * The first few elements of string S is the following: S = "1221121221221121122бнбн"
 * If we group the consecutive '1's and '2's in S, it will be:
 * 1 22 11 2 1 22 1 22 11 2 11 22 ......
 * 
 * and the occurrences of '1's or '2's in each group are:
 * 1 2 2 1 1 2 1 2 2 1 2 2 ......
 * 
 * You can see that the occurrence sequence above is the S itself.
 * 
 * Given an integer N as input, return the number of '1's in the first N number in the magical string S.
 * Note: N will not exceed 100,000.
 * 
 * Example 1:
 * Input: 6
 * Output: 3
 * Explanation: The first 6 elements of magical string S is "122112" and it contains three 1's, so return 3.

 * @author WinnieZhao
 *
 */
public class MagicalString {

    public int magicalString(int n) {
        if (n==0) return 0;
        StringBuffer magic = new StringBuffer("122");
        int length = 3;
        if (n<=length) {
            return 1;
        }
        
        int lastDigit = 2;
        int i = 2;
        while(length <= n) {
            int count = magic.charAt(i) - '0';
            
            if (lastDigit == 1) {
                if (count == 1) {
                    magic.append("2");
                }
                else {
                    magic.append("22");
                }
            }
            else {
                if (count == 1) {
                    magic.append("1");
                }
                else {
                    magic.append("11");
                }
            }

            length = magic.length();
            lastDigit = magic.charAt(length-1) - '0';
            length++;
            i++;
        }
        
        System.out.println(magic);
        
        int count = 0;
        for(int k=0;k<n;k++) {
            if(magic.charAt(k)=='1') count++;
        }
        return count;
    }

    public static void main(String[] args) {
        MagicalString solution = new MagicalString();
        System.out.println(solution.magicalString(10));
    }
}
