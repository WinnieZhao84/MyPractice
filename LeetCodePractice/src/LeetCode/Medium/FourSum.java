package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c, and d in S such that a + b + c + d = target? 
 * Find all unique quadruplets in the array which gives the sum of target.
 * 
 * Note: The solution set must not contain duplicate quadruplets.
 * For example, given array S = [1, 0, -1, 0, -2, 2], and target = 0.
 * 
 * A solution set is:
 * 
 * [
 *  [-1,  0, 0, 1],
 *  [-2, -1, 1, 2],
 *  [-2,  0, 0, 2]
 * ]
 * 
 * 
 * @author WinnieZhao
 *
 */
public class FourSum {
    int len = 0;
    
    public List<List<Integer>> fourSum(int[] nums, int target) {
        len = nums.length;
        Arrays.sort(nums);
        return kSum(nums, target, 4, 0);
    }
    
    private ArrayList<List<Integer>> kSum(int[] nums, int target, int k, int index) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        if (index >= len) {
            return res;
        }
        if(k == 2) {
            int i = index, j = len - 1;
            while(i < j) {
                //find a pair
                if (target - nums[i] == nums[j]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(target-nums[i]);
                    res.add(temp);
                    //skip duplication
                    while(i<j && nums[i]==nums[i+1]) i++;
                    while(i<j && nums[j-1]==nums[j]) j--;
                    i++;
                    j--;
                //move left bound
                } else if (target - nums[i] > nums[j]) {
                    i++;
                //move right bound
                } else {
                    j--;
                }
            }
        } 
        else {
            for (int i = index; i < len - k + 1; i++) {
                //use current number to reduce ksum into k-1sum
                ArrayList<List<Integer>> temp = kSum(nums, target - nums[i], k-1, i+1);
                if(temp != null){
                    //add previous results
                    for (List<Integer> t : temp) {
                        t.add(0, nums[i]);
                    }
                    res.addAll(temp);
                }
                while (i < len-1 && nums[i] == nums[i+1]) {
                    //skip duplicated numbers
                    i++;
                }
            }
        }
        return res;
    }
    
    // Time Limit Exceed Solution
    public List<List<Integer>> fourSum_BFS(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if (nums == null || nums.length < 4) {
            return result;
        }
        
        Arrays.sort(nums);
        
        int len = nums.length;
        int max = nums[len - 1];
        if (4 * nums[0] > target || 4 * max < target) {
            return result;
        }
        
        this.fourSunHelper(result, new ArrayList<>(), nums, target, 0);
        
        return result;
    }
    
    private void fourSunHelper(List<List<Integer>> result, List<Integer> list, int[] nums, int target, int start) {
        if (list.size() == 4 && target == this.getSum(list)) {
            result.add(new ArrayList<>(list));
            return;
        }
        else if (list.size() > 4 || (list.size() == 4 && target != this.getSum(list))) {
            return;
        }
        else {
            for (int i=start; i<nums.length; i++) {
                list.add(nums[i]);
                this.fourSunHelper(result, list, nums, target, i+1);
                if (i+1 < nums.length && nums[i] == nums[i+1]) {
                    i++;
                }
                list.remove(list.size()-1);
            }
        }
    }
    
    private int getSum(List<Integer> list) {
        int sum = 0;
        for (int i : list) {
            sum+=i;
        }
        return sum;
    }
    
    public static void main(String[] args) {
        FourSum solution = new FourSum();
        
        int[] nums = {1, 0, -1, 0, -2, 2};
        int target = 0;
        
        List<List<Integer>> result = solution.fourSum_BFS(nums, target);
        result.forEach(list -> System.out.println(list));
        
        System.out.println("=================");
        
        int[] nums1 = {-3,-2,-1,0,0,1,2,3};
        int target1 = 0;
        
        List<List<Integer>> result1 = solution.fourSum_BFS(nums1, target1);
        result1.forEach(list -> System.out.println(list));
        
    }
}
