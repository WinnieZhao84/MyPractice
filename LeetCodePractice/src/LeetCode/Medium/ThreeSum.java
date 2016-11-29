package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array S of n integers, are there elements a, b, c in S such that a + b + c = 0? Find all unique triplets in 
 * the array which gives the sum of zero.
 * 
 * Note: The solution set must not contain duplicate triplets.
 * For example, given array S = [-1, 0, 1, 2, -1, -4],
 * 
 * A solution set is:
 * [
 *   [-1, 0, 1],
 *   [-1, -1, 2] 
 * ]
 *   
 * @author WinnieZhao
 *
 */
public class ThreeSum {

    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(nums);
        
        for (int i=0; i<= nums.length-3; i++) {
            List<List<Integer>> res = this.twoSum(nums, 0-nums[i], i);
            if (res != null && !res.isEmpty()) {
                for (List<Integer> l : res) {
                    l.add(0, nums[i]);
                    result.add(l);
                }
            }
            while (i < nums.length-1 && nums[i] == nums[i+1]) {
                i++;
            }
        }
        
        return result;
    }
    
    private List<List<Integer>> twoSum(int[] nums, int target, int index) {
        ArrayList<List<Integer>> res = new ArrayList<List<Integer>>();
        
        int left = index+1;
        int right = nums.length-1;
        
        while (left < right) {
            List<Integer> list = new ArrayList<>();
            
            if (target - nums[left] == nums[right]) {
                list.add(nums[left]);
                list.add(nums[right]);
                res.add(list);
                
                //skip duplication
                while(left < right && nums[left]==nums[left+1]) left++;
                while(left < right && nums[right-1]==nums[right]) right--;
                left++;
                right--;
            }
            else if (target - nums[left] > nums[right]) {
                left++;
            }
            else {
                right--;
            }
        }
        return res;
    }
    
    public static void main(String[] args) {
        ThreeSum solution = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        
        List<List<Integer>> result = solution.threeSum(nums);
        result.forEach(list -> System.out.println(list));
        
    }
}
