package LeetCode.Hard;

import java.util.Arrays;
import java.util.Comparator;

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
     * O(n2) solution
     *
     * @param envelopes
     * @return
     */
    public int maxEnvelopes(int[][] envelopes) {

        if (envelopes == null || envelopes.length == 0
                || envelopes[0]   == null || envelopes[0].length == 0){
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>(){
            @Override
            public int compare(int[] e1, int[] e2){
                return Integer.compare(e1[0], e2[0]);
            }
        });

        int n = envelopes.length;
        int[] dp = new int[n];

        int ret = 0;
        for (int i = 0; i < n; i++){
            dp[i] = 1;

            for (int j = 0; j < i; j++){
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]){
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            }

            ret = Math.max(ret, dp[i]);
        }

        return ret;
    }

    /**
     *  O(nlogn) solution
     *
     *  Sort the array. Ascend on width and descend on height if width are same.
     *  Find the longest increasing subsequence based on height. Since the width is increasing,
     *  we only need to consider height.
     *
     *  [3, 4] cannot contains [3, 3], so we need to put [3, 4] before [3, 3] when sorting
     *  otherwise it will be counted as an increasing number if the order is [3, 3], [3, 4]
      */

    public int maxEnvelopes_better(int[][] envelopes) {
        if (envelopes == null || envelopes.length == 0
                || envelopes[0] == null || envelopes[0].length != 2) {
            return 0;
        }

        Arrays.sort(envelopes, new Comparator<int[]>(){
            public int compare(int[] arr1, int[] arr2){
                if(arr1[0] == arr2[0])
                    return arr2[1] - arr1[1];
                else
                    return arr1[0] - arr2[0];
            }
        });

        int dp[] = new int[envelopes.length];
        int len = 0;

        /**
         * [[1, 1], [2, 3], [4, 6], [4, 5], [6, 7]]
         *
         * Searching for height of 1
         * Result of search: -1
         * Inserting height at index: 0
         * 1 0 0 0 0
         * len is now 1
         *
         * Searching for height of 3
         * Result of search: -2
         * Inserting height at index: 1
         * 1 3 0 0 0
         * len is now 2
         *
         * Searching for height of 6
         * Result of search: -3
         * Inserting height at index: 2
         * 1 3 6 0 0
         * len is now 3
         *
         * Searching for height of 5
         * Result of search: -3
         * Inserting height at index: 2
         * 1 3 5 0 0
         * len is now 3
         *
         * Searching for height of 7
         * Result of search: -4
         * Inserting height at index: 3
         * 1 3 5 7 0
         * len is now 4
         *
         * Max number of envelopes: 4
         */
        for(int[] envelope : envelopes){
            int index = Arrays.binarySearch(dp, 0, len, envelope[1]);
            if (index < 0) {
                index = -(index + 1);
            }

            dp[index] = envelope[1];
            if (index == len)
                len++;
        }
        return len;
    }
}
