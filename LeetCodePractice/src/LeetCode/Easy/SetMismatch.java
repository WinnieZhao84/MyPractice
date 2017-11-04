package LeetCode.Easy;

import java.util.HashSet;
import java.util.Set;

/**
 * The set S originally contains numbers from 1 to n. But unfortunately, due to the data error, one of the numbers
 * in the set got duplicated to another number in the set, which results in repetition of one number and loss of
 * another number.
 *
 * Given an array nums representing the data status of this set after the error. Your task is to firstly find the
 * number occurs twice and then find the number that is missing. Return them in the form of an array.
 *
 * Example 1:
 * Input: nums = [1,2,2,4]
 * Output: [2,3]
 *
 * Note:
 * The given array size will in the range [2, 10000]
 * The given array's numbers won't have any order.
 *
 * Created by WinnieZhao on 2017/7/23.
 */
public class SetMismatch {

    public int[] findErrorNums(int[] nums) {

        Set<Integer> dups = new HashSet<Integer>();
        int sum = 0;
        int dup = 0;
        for (int n : nums) {
            if (!dups.contains(n)) {
                sum += n;
                dups.add(n);
            }
            else {
                dup = n;
            }
        }

        int n = nums.length;
        int all = 0;
        for (int i=1; i<=n; i++) {
            all += i;
        }

        int[] res = new int[2];
        res[0] = dup;
        res[1] = all -sum;

        return res;
    }

    public int[] findErrorNums_better(int[] nums) {

        int[] res = new int[2];

        for (int num : nums) {
            if (nums[Math.abs(num) -1] < 0) {
                res[0] = Math.abs(num);
            }
            else {
                nums[Math.abs(num)-1] = nums[Math.abs(num)-1] * -1;
            }
        }

        for (int i=0;i<nums.length;i++) {
            if (nums[i] > 0) {
                res[1] = i+1;
            }
        }

        return res;
    }

    public static void main(String[] args) {
        SetMismatch solution = new SetMismatch();

        int[] nums = {2,2};
        int[] res = solution.findErrorNums_better(nums);
        System.out.println(res[0]);
        System.out.println(res[1]);
    }
}
