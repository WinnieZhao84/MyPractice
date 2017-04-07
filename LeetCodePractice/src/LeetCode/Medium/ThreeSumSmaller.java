package LeetCode.Medium;

import java.util.Arrays;

/**
 * 259
 *
 * Given an array of n integers nums and a target, find the number of index triplets i, j, k with 0 <= i < j < k < n
 * that satisfy the condition nums[i] + nums[j] + nums[k] < target.
 *
 * For example, given nums = [-2, 0, 1, 3], and target = 2.
 * Return 2.
 * Because there are two triplets which sums are less than 2:
 * [-2, 0, 1] [-2, 0, 3]
 *
 * Follow up: Could you solve it in O(n2) runtime?
 *

 * Created by WinnieZhao on 2017/4/7.
 */
public class ThreeSumSmaller {

    /**
     * Fix first number, then use two pointers, notice that when you find a i(fixed),j,k triplet,
     * which means all combine between k-j is valid triplet.
     */
    // why sort array is ok? because sort array won't change the fact that a good triplet still stands
    public int threeSumSmaller(int[] nums, int target) {
        Arrays.sort(nums);// even though sorting scrambles the indice. however it doesn't change the number of good triplet.

        int count = 0;
        for(int i=0; i<nums.length-2; i++){
            int j = i+1, k = nums.length-1;
            while(j < k){
                if((nums[i] + nums[j] + nums[k]) >= target) k--;
                else{
                    count += k -j; // all the combine between k-j are good triplet.
                    j++;

                }
            }
        }

        return count;
    }

    public static void main(String[] args) {
        ThreeSumSmaller solution = new ThreeSumSmaller();

        int[] nums = {-2, 0, 1, 3};
        System.out.print(solution.threeSumSmaller(nums, 2));

    }
}
