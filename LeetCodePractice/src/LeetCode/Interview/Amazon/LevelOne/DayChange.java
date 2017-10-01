package LeetCode.Interview.Amazon.LevelOne;

/**
 * Given an array and number of days.
 * Rules: if arr[i-1] == arr[i+1], arr = 0 else arr = 1, do that for n days.
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class DayChange {

    public static int[] dayChange(int[] input, int day) {
        if(input == null || input.length == 0 || day <= 0) return input;
        for(int k = 0; k < day; k++) {
            for(int i = 0; i < input.length; i++) {
                if(i - 1 >= 0 && i + 1 < input.length && (input[i - 1] & 1) != (input[i + 1] & 1)) {
                    input[i] += 2;
                }
            }
            for(int i = 0; i < input.length; i++) {
                input[i] >>= 1;
            }
        }
        return input;
    }
}
