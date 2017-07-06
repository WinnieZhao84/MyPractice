package LeetCode.Hard;

import java.util.Arrays;

/**
 * You have a number of envelopes with widths and heights given as a pair of integers (w, h).
 * One envelope can fit into another if and only if both the width and height of one envelope
 * is greater than the width and height of the other envelope.
 *
 * What is the maximum number of envelopes can you Russian doll? (put one inside other)
 * Example:
 * Given envelopes = [[5,4],[6,4],[6,7],[2,3]], the maximum number of envelopes
 * you can Russian doll is 3 ([2,3] => [5,4] => [6,7]).

 * Created by WinnieZhao on 7/3/2017.
 */
public class RussianDollEnvelopes {
    /**
     * Sort the array. Ascend on width and descend on height if width are same.
     * Find the longest increasing sub sequence based on height.
     *
     * Since the width is increasing, we only need to consider height[3, 4] cannot contains [3, 3],
     * so we need to put [3, 4] before [3, 3] when sorting otherwise it will be counted as an
     * increasing number if the order is [3, 3], [3, 4]
     * 
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {

        if (envelopes==null || envelopes.length==0) {
            return 0;
        }

        Arrays.sort(envelopes, (int[] ev1, int[] ev2) -> {
            if (ev1[0] != ev2[0]){
                return ev1[0] - ev2[0];
            }
            else{
                return ev1[1] - ev2[1];
            }
        });

        int max = 1;
        int[] dp = new int[envelopes.length];

        for (int i=0; i<envelopes.length; i++) {
            dp[i] = 1;

            for (int j=i-1; j>=0; j--) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            max = Math.max(max, dp[i]);
        }

        return max;
    }
}
