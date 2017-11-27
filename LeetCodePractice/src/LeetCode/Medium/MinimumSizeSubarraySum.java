package LeetCode.Medium;

/**
 * Given an array of n positive integers and a positive integer s, find the minimal length of a subarray of which the sum >= s.
 * If there isn't one, return 0 instead.
 * 
 * For example, given the array [2,3,1,2,4,3] and s = 7, the subarray [4,3] has the minimal length under the problem constraint.
 * 
 * More practice:
 * 
 * If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).

 * @author WinnieZhao
 *
 */
public class MinimumSizeSubarraySum {

    /**
     * O(n) solution: Keep a moving window expand until sum>=s, then shrink util sum<s. Each time after shrinking, update length
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int length = nums.length;
        int minLength = Integer.MAX_VALUE;

        int sum = 0;
        int pt1=0;
        int pt2=0;
        
        while(pt1<length) {
            sum += nums[pt1++];
            while(sum >= s) {
                minLength = Math.min(minLength, pt1-pt2);
                sum -= nums[pt2++];
            }
        }

        return minLength == Integer.MAX_VALUE ? 0 : minLength;
    }

    /**
     * O(nlogn) solution
     *
     * @param s
     * @param nums
     * @return
     */
    public int minSubArrayLen_2(int s, int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int[] sum = new int[nums.length];

        int left=1;
        int right=nums.length;
        int min = 0;

        while (left <= right) {
            int mid = left + (right-left)/2;
            if (windowExist(nums, mid, s)) {
                right = mid - 1;
                min = mid;
            }
            else {
                left = mid + 1;
            }
        }

        return min;
    }

    private boolean windowExist(int[] nums, int size, int s) {
        int sum = 0;

        for (int i=0; i<nums.length; i++) {
            if (i>=size) {
                sum -= nums[i-size];
            }

            sum += nums[i];
            if (sum >= s) {
                return true;
            }

        }
        return false;
    }
    
    public static void main(String[] args) {
        MinimumSizeSubarraySum solution = new MinimumSizeSubarraySum();
        
        int[] nums = {2,3,1,2,4,3};
        System.out.println(solution.minSubArrayLen_2(7, nums));
        
    }
}
