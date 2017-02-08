package LeetCode.Medium;

/**
 * Given an unsorted array return whether an increasing subsequence of length 3 exists or not in the array.
 * 
 * Formally the function should:
 * Return true if there exists i, j, k 
 * such that arr[i] < arr[j] < arr[k] given 0 ¡Ü i < j < k ¡Ü n-1 else return false.
 * Your algorithm should run in O(n) time complexity and O(1) space complexity.
 * 
 * Examples:
 * Given [1, 2, 3, 4, 5],
 * return true.
 * 
 * Given [5, 4, 3, 2, 1],
 * return false.

 * @author WinnieZhao
 *
 */
public class IncreasingTripletSubsequence {

    public boolean increasingTriplet(int[] nums) {
        if (nums == null || nums.length < 3) return false;
        
        int dp[] = new int[nums.length];

        for(int i=0; i<nums.length;i++) {
            dp[i] = 1;
            
            for (int j=0; j<i; j++) {
                
                if(nums[j]<nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    if (dp[i] >= 3) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
    
    public boolean increasingTriplet_better(int[] nums) {
        // start with two largest values, as soon as we find a number bigger than both, while both have been updated, return true.
        int small = Integer.MAX_VALUE, big = Integer.MAX_VALUE;
        for (int n : nums) {
            // update small if n is smaller than both
            if (n <= small) { 
                small = n; 
            }
            // update big only if greater than small but smaller than big
            else if (n <= big) {
                big = n;
            }
            else {
                return true; // return if you find a number bigger than both
            }
        }
        return false;
    }
}
