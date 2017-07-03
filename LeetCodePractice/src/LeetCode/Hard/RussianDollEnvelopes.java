package LeetCode.Hard;

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

    }
}
