package LeetCode.Medium;

import java.util.Arrays;

/**
 * 370
 *
 * Assume you have an array of length n initialized with all 0's and are given k update operations
 * Each operation is represented as a triplet: [startIndex, endIndex, inc] which increments each
 * element of subarray A[startIndex ... endIndex] (startIndex and endIndex inclusive) with inc.
 *
 * Return the modified array after all k operations were executed.
 * Example:
 * Given: length = 5,
 * updates =
 * [
 *  [1,  3,  2],
 *  [2,  4,  3],
 *  [0,  2, -2]
 * ]
 *
 * Output: [-2, 0, 3, 5, 3]
 * Explanation:
 * Initial state: [ 0, 0, 0, 0, 0 ]
 *
 * After applying operation [1, 3, 2]: [ 0, 2, 2, 2, 0 ]
 * After applying operation [2, 4, 3]: [ 0, 2, 5, 5, 3 ]
 * After applying operation [0, 2, -2]: [-2, 0, 3, 5, 3 ]
 *
 * Hint:
 * Thinking of using advanced data structures? You are thinking it too complicated.
 * For each update operation, do you really need to update all elements between i and j?
 * Update only the first and end element is sufficient.
 * The optimal time complexity is O(k + n) and uses O(1) extra space.

 * Created by WinnieZhao on 2017/3/29.
 */
public class RangeAddition {
    public int[] getModifiedArray(int length, int[][] updates) {
        if (updates == null || updates.length == 0) {
            return new int[length];
        }

        /**
         * We update the value at start index, because it will be used in the future when we are adding up the values
         * for the sum at each index between start index and end index (both inclusive). We update the negative value
         * at the end index + 1, because the positive value of it should be only added at its previous indices
         * (from start index to end index). Thus, when we accumulate the sum at the end for each index, we will get
         * the correct values for each index. If the end index is the last index in the resulting array, we don't
         * have to do the end index + 1 part, because there is no more index after the last index and there will
         * be no error when we accumulate the sum.
         *
         */
        int[] res = new int[length+1];

        for(int i=0; i< updates.length; i++){
            int[] inc = updates[i];
            res[inc[0]] += inc[2];
            res[inc[1] +1] -= inc[2];
        }

        for(int i=1; i<res.length; i++){
            res[i] += res[i-1];
        }

        return Arrays.copyOfRange(res, 0, res.length-1);

    }

    public static void main(String[] args) {
        RangeAddition solution = new RangeAddition();

        int[][] updates = {{1,  3,  2}, {2,  4,  3}, {0,  2, -2}};

        System.out.print(solution.getModifiedArray(5, updates));
    }
}