package LeetCode.Easy;

/**
 * We have two special characters. The first character can be represented by one bit 0.
 * The second character can be represented by two bits (10 or 11).
 *
 * Now given a string represented by several bits. Return whether the last character must be a one-bit character or not.
 * The given string will always end with a zero.
 *
 * Example 1:
 *
 * Input:
 * bits = [1, 0, 0]
 * Output: True
 * Explanation:
 * The only way to decode it is two-bit character and one-bit character. So the last character is one-bit character.
 *
 * Example 2:
 * Input:
 * bits = [1, 1, 1, 0]
 * Output: False
 * Explanation:
 * The only way to decode it is two-bit character and two-bit character. So the last character is NOT one-bit character.
 *
 * Note:
 * 1 <= len(bits) <= 1000.
 * bits[i] is always 0 or 1.

 * Created by WinnieZhao on 12/28/2017.
 */
public class OneBitAndTwoBitCharacters {

    public boolean isOneBitCharacter(int[] bits) {
        if (bits == null || bits.length == 0) {
            return false;
        }

        int len = bits.length;

        int i=0;
        while(i<len-1) {
            int bit = bits[i];
            if (bit == 1) {
                i=i+2;
            }
            else {
                i++;
            }
        }

        return i == len-1;
    }

    public static void main(String[] args) {
        OneBitAndTwoBitCharacters solution = new OneBitAndTwoBitCharacters();

        System.out.println(solution.isOneBitCharacter(new int[] {1,0,0}));
        System.out.println(solution.isOneBitCharacter(new int[] {1,1,1,0}));
        System.out.println(solution.isOneBitCharacter(new int[] {0,1,1,0}));
    }
}
