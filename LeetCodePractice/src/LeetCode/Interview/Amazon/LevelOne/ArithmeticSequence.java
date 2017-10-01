package LeetCode.Interview.Amazon.LevelOne;

/**
 * Same as LeetCode ArithmeticSlices
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class ArithmeticSequence {

    public static int count(int[] input) {
        if (input == null || input.length < 3) {
            return 0;
        }

        int cur = 0;
        int sum = 0;
        for (int i=2; i<input.length; i++) {
            if (input[i] - input[i-1] == input[i-1] - input[i-2]) {
                cur += 1;
                sum += cur;
            }
            else {
                cur = 0;
            }
        }

        return sum;
    }
}
