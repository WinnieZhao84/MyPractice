package LeetCode.Medium;

/**
 * Given a non-negative integer, you could swap two digits at most once to get the maximum valued number.
 * Return the maximum valued number you could get.
 *
 * Example 1:
 * Input: 2736
 * Output: 7236
 * Explanation: Swap the number 2 and the number 7.
 *
 * Example 2:
 * Input: 9973
 * Output: 9973
 * Explanation: No swap.
 *
 * Note:The given number is in the range [0, 10^8]

 */
public class MaximumSwap {

    /**
     * Time Complexity: O(N), where N is the total number of digits in the input number.
     * Every digit is considered at most once.
     * Space Complexity: O(1). The additional space used by last only has up to 10 values.
     */
    public int maximumSwap(int num) {
        String n = String.valueOf(num);

        int len = n.length();

        if (len == 1) {
            return num;
        }

        char[] chs = n.toCharArray();

        int[] buckets = new int[10];
        for (int i=0; i<len; i++) {
            buckets[chs[i] - '0'] = i;
        }

        for (int i=0; i<len; i++) {
            int digit = chs[i] - '0';

            for (int k=9; k>digit; k--) {
                if (buckets[k] > i) {
                    int index = buckets[k];

                    char c = chs[i];
                    chs[i] = chs[index];
                    chs[index] = c;

                    return Integer.valueOf(new String(chs));
                }
            }
        }

        return num;
    }

    public static void main(String[] args) {
        MaximumSwap solution = new MaximumSwap();

        System.out.println(solution.maximumSwap(120));
        System.out.println(solution.maximumSwap(1993));
        System.out.println(solution.maximumSwap(2736));
        System.out.println(solution.maximumSwap(9973));
        System.out.println(solution.maximumSwap(98368));
    }
}
