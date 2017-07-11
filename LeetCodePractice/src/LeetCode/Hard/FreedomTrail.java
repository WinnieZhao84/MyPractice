package LeetCode.Hard;

/**
 * In the video game Fallout 4, the quest "Road to Freedom" requires players to reach a metal dial called the "Freedom Trail Ring",
 * and use the dial to spell a specific keyword in order to open the door.
 *
 * Given a string ring, which represents the code engraved on the outer ring and another string key, which represents the keyword
 * needs to be spelled. You need to find the minimum number of steps in order to spell all the characters in the keyword.
 *
 * Initially, the first character of the ring is aligned at 12:00 direction. You need to spell all the characters in the string key
 * one by one by rotating the ring clockwise or anticlockwise to make each character of the string key aligned at 12:00 direction
 * and then by pressing the center button
 *
 * At the stage of rotating the ring to spell the key character key[i]:
 * 1. You can rotate the ring clockwise or anticlockwise one place, which counts as 1 step. The final purpose of the rotation is to
 * align one of the string ring's characters at the 12:00 direction, where this character must equal to the character key[i].
 *
 * 2. If the character key[i] has been aligned at the 12:00 direction, you need to press the center button to spell, which also counts
 * as 1 step. After the pressing, you could begin to spell the next character in the key (next stage), otherwise, you've finished all
 * the spelling.
 *
 * Example:
 * Input: ring = "godding", key = "gd"
 * Output: 4
 *
 * Explanation:
 * For the first key character 'g', since it is already in place, we just need 1 step to spell this character.
 * For the second key character 'd', we need to rotate the ring "godding" anticlockwise by two steps to make it become "ddinggo".
 * Also, we need 1 more step for spelling.
 * So the final output is 4.
 *
 * Note:
 * Length of both ring and key will be in range 1 to 100.
 * There are only lowercase letters in both strings and might be some duplcate characters in both strings.
 * It's guaranteed that string key could always be spelled by rotating the string ring.

 *
 * Created by WinnieZhao on 7/10/2017.
 */
public class FreedomTrail {

    public int findRotateSteps(String ring, String key) {

        /**
         * dp[i][j] represents the minimum cost (number of rotations without pressing button),
         * for spelling a whole sub key starting at i-th character, when j-th character of the ring is at 12 o clock.
         * The whole for(k=0; k<n; k++) loop represents a search for i-th character of key in the ring string.
         * The diff and n - diff represents costs for rotating k-th character found in ring to 12 o clock.
         * One is clockwise and another is counterclockwise.
         * step is the minimum cost between clockwise and counterclockwise rotation.
         * Diff and step do not include cost for pressing the center button, because this cost for the whole key will be
         * always equal to number of characters in the key.
         * So at the end the code returns dp[0][0] + m.
         * dp[0][0] means cost (without pressing center button) for spelling the key starting at 0-th character
         * when 0-th character of ring is at 12 o' clock.
         * m represents cost of pressing center button for every character in key in this context.
         *
         * The dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]) represents the recursive relation for the problem
         * and sub problem.
         * Minimum cost for spelling the key starting at i-th character when j-th ring character is at 12 o' clock
         * (dp[i][j]) is equal to the minimal number of steps required to rotate the k-th character of ring to 12 o'clock
         * (step) + the minimum cost for spelling the key starting at i-th + 1 character when k-th character is already
         * at 12 o'clock (dp[i+1][k]).

         */
        int n = ring.length();
        int m = key.length();
        int[][] dp = new int[m + 1][n];

        for (int i = m - 1; i >= 0; i--) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = Integer.MAX_VALUE;

                for (int k = 0; k < n; k++) {
                    if (ring.charAt(k) == key.charAt(i)) {
                        int diff = Math.abs(j - k);
                        int step = Math.min(diff, n - diff);

                        dp[i][j] = Math.min(dp[i][j], step + dp[i + 1][k]);
                    }
                }
            }
        }

        return dp[0][0] + m;
    }
}
