package LeetCode.Hard;

/**
 * Given an array which consists of non-negative integers and an integer m, you can split the array into m non-empty continuous subarrays.
 * Write an algorithm to minimize the largest sum among these m subarrays.
 *
 * Note:
 * If n is the length of array, assume the following constraints are satisfied:
 *
 * 1 ≤ n ≤ 1000
 * 1 ≤ m ≤ min(50, n)
 *
 * Examples:
 * Input: nums = [7,2,5,10,8] m = 2
 *
 * Output: 18
 *
 * Explanation:
 * There are four ways to split nums into two subarrays. The best way is to split it into [7,2,5] and [10,8],
 * where the largest sum among the two subarrays is only 18.
 *
 * Created by WinnieZhao on 2017/6/7.
 */
public class SplitArrayLargestSum {

    /**
     * 如果m和数组nums的个数相等，那么每个数组都是一个子数组，所以返回nums中最大的数字即可，
     * 如果m为1，那么整个nums数组就是一个子数组，返回nums所有数字之和，所以对于其他有效的m值，
     * 返回的值必定在上面两个值之间，所以我们可以用二分搜索法来做。
     * 我们用一个例子来分析，nums = [1, 2, 3, 4, 5], m = 3，
     * 我们将left设为数组中的最大值5，right设为数字之和15，然后我们算出中间数为10，
     * 我们接下来要做的是找出和最大且小于等于10的子数组的个数，[1, 2, 3, 4], [5]，可以看到我们无法分为3组，说明mid偏大，所以我们让right=mid，
     * 然后我们再次进行二分查找哦啊，算出mid=7，再次找出和最大且小于等于7的子数组的个数，[1,2,3], [4], [5]，我们成功的找出了三组，
     * 说明mid还可以进一步降低，我们让right=mid，然后我们再次进行二分查找哦啊，算出mid=6，再次找出和最大且小于等于6的子数组的个数，[1,2,3], [4], [5]，
     * 我们成功的找出了三组，我们尝试着继续降低mid，我们让right=mid，然后我们再次进行二分查找哦啊，算出mid=5，
     * 再次找出和最大且小于等于5的子数组的个数，[1,2], [3], [4], [5]，发现有4组，此时我们的mid太小了，应该增大mid，
     * 我们让left=mid+1，此时left=6，right=5，循环退出了，我们返回left即可.
     */
    public int splitArray(int[] nums, int m) {

        if (nums == null || nums.length == 0 || m <= 0) {
            return 0;
        }

        int sum=0;
        int max=0;
        for (int num : nums) {
            sum += num;
            max = Math.max(num, max);
        }

        return this.binarySearch(nums, m, max, sum);

    }

    /**
     * Time complexity : O(n * log(sum of array)).
     * The binary search costs O(log(sum of array)), where sum of array is the sum of elements in nums.
     * For each computation of sum, the time complexity is O(n) since we only need to go through the whole array.
     *
     * Space complexity : O(n).
     * Same as the Brute Force approach. We only need the space to store the array.
     */
    private int binarySearch(int[] nums, int m, int low, int high) {

        while (low <= high) {
            int mid = low + (high-low)/2;

            if (this.noLargerThanM(nums, m, mid)) {
                high = mid-1;
            }
            else {
                low = mid+1;
            }
        }

        return low;
    }

    /**
     * For nums array like a, b, c, d, e, f, g
     * when a+b <= max, a+b+c > max. add count
     * try another sub array with c and following nums
     *
     * Do same process here, to find all numbers of sub array which less or equal than M
     * If count > m, it means that in order to find sum of sub arrays less than current Max,
     * it has to separate to more ( >M ) sub arrays, hence return false as the current max is too small
     * and we need to increase the low
     * otherwise, there is possibility we could find smaller max.
     */
    private boolean noLargerThanM(int[] nums, int m, int max) {
        int sum = 0;
        int count = 1;
        for (int num : nums) {
            sum += num;

            if (sum > max) {
                count++;
                sum = num;

                if (count > m) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Define dp[i][j] to be the minimum largest sub array sum for splitting nums[0..i] into j parts.
     * Consider the jth sub array. We can split the array from a smaller index k to i to form it.
     * Thus dp[i][j] can be derived from max(dp[k][j - 1], nums[k + 1] + ... + nums[i]).
     * For all valid index k, dp[i][j] should choose the minimum value of the above formula.
     *
     * The final answer should be dp[n][m], where n is the size of the array.
     * For corner situations, all the invalid dp[i][j] should be assigned with INFINITY,
     * dp[0][0] should be initialized with 0.

     * Time complexity : O(n^2 * m)
     * The total number of states is O(n * m). To compute each state dp[i][j],
     * we need to go through the whole array to find the optimum k. This requires another O(n) loop.
     * So the total time complexity is O(n ^ 2 * m).
     *
     * Space complexity : O(n * m).
     * The space complexity is equivalent to the number of states, which is O(n * m).
     */
    public int splitArray_dp(int[] nums, int m) {
        if (nums == null || nums.length == 0 || m <= 0) {
            return 0;
        }

        int n = nums.length;
        int[] sums = new int[n+1];

        sums[0] = 0;
        for (int i=0; i<n; i++) {
            sums[i+1] = sums[i] + nums[i];
        }

        if (m == 1) {
            return sums[n];
        }

        int[][] dp = new int[n+1][m+1];

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[0][0] = 0;
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                for (int k=0; k<i; k++) {
                    dp[i][j] = Math.min(dp[i][j], Math.max(dp[k][j-1], sums[i]-sums[k]));
                }
            }
        }

        return dp[n][m];
    }

    public static void main(String[] args) {
        SplitArrayLargestSum solution = new SplitArrayLargestSum();
        int[] nums = {1,2,3,4,5};
        System.out.println(solution.splitArray(nums, 3));
    }
}
