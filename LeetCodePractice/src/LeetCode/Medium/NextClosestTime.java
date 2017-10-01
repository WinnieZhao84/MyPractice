package LeetCode.Medium;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a time represented in the format "HH:MM", form the next closest time by reusing the current digits.
 * There is no limit on how many times a digit can be reused.
 *
 * You may assume the given input string is always valid. For example, "01:34", "12:09" are all valid.
 * "1:34", "12:9" are all invalid.
 *
 * Example 1:
 *
 * Input: "19:34"
 * Output: "19:39"
 * Explanation: The next closest time choosing from digits 1, 9, 3, 4, is 19:39,
 * which occurs 5 minutes later.  It is not 19:33, because this occurs 23 hours and 59 minutes later.
 *
 * Example 2:
 *
 * Input: "23:59"
 * Output: "22:22"
 * Explanation: The next closest time choosing from digits 2, 3, 5, 9, is 22:22.
 * It may be assumed that the returned time is next day's time since it is smaller than the input time numerically.

 * Created by WinnieZhao on 10/1/2017.
 */
public class NextClosestTime {

    /**
     * We have up to 4 different allowed digits, which naively gives us 4 * 4 * 4 * 4 possible times.
     * For each possible time, let's check that it can be displayed on a clock: ie., hours < 24 and mins < 60.
     * The best possible time != start is the one with the largest (time - start) % (24 * 60).
     *
     * Also, we should make sure to handle "cand_elapsed" carefully.
     * When our current candidate time cur is equal to the given starting time,
     * then "cand_elapsed" will be 0 and we should handle this case appropriately.

     * @param time
     * @return
     */
    public String nextClosestTime(String time) {
        int start = 60 * Integer.parseInt(time.substring(0, 2));
        start += Integer.parseInt(time.substring(3));

        int ans = start;
        int elapsed = 24 * 60;

        Set<Integer> allowed = new HashSet();
        for (char c: time.toCharArray()) {
            if (c != ':') {
                allowed.add(c - '0');
            }
        }

        for (int h1: allowed) {
            for (int h2 : allowed) {
                if (h1 * 10 + h2 < 24) {
                    for (int m1 : allowed) {
                        for (int m2 : allowed) {
                            if (m1 * 10 + m2 < 60) {
                                int cur = 60 * (h1 * 10 + h2) + (m1 * 10 + m2);
                                int candElapsed = Math.floorMod(cur - start, 24 * 60);
                                if (candElapsed > 0 && candElapsed < elapsed) {
                                    ans = cur;
                                    elapsed = candElapsed;
                                }
                            }
                        }
                    }
                }
            }
        }

        return String.format("%02d:%02d", ans / 60, ans % 60);
    }
}
