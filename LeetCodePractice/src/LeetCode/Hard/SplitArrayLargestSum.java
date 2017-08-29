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
        long sum = 0;
        int max = 0;
        for(int num: nums){
            max = Math.max(max, num);
            sum += num;
        }
        return (int)binary(nums, m, sum, max);
    }

    private long binary(int[] nums, int m, long high, long low){
        long mid = 0;

        while(low < high){
            mid = (high + low)/2;
            if(valid(nums, m, mid)){
                high = mid;
            }
            else{
                low = mid + 1;
            }
        }
        return high;
    }

    private boolean valid(int[] nums, int m, long max){
        int cur = 0;
        int count = 1;
        for(int num: nums){
            cur += num;
            if(cur > max){
                cur = num;
                count++;
                if(count > m){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SplitArrayLargestSum solution = new SplitArrayLargestSum();
        int[] nums = {1,2,3,4,5};
        System.out.println(solution.splitArray(nums, 3));
    }
}
