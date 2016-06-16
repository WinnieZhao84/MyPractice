package LeetCode.Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Rotate an array of n elements to the right by k steps.
 * 
 * For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
 * 
 * Note:
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * 
 * @author ASUS-PC
 *
 */
public class RotateArray {
    public void rotate(int[] nums, int k) {
        
    	List<Integer> list = new ArrayList<Integer>();
    	
    	k = k % nums.length;
    	for (int i=nums.length-1; i>=nums.length-k; i--) {
    		list.add(0,nums[i]);
    	}
    	
    	for (int i=0; i<=nums.length-1-k; i++) {
    		list.add(nums[i]);
    	}
    	
    	int i=0;
    	for (Integer val : list) {
    		nums[i] = val;
    		i++;
    	}
    }
    
    public void rotate_better(int[] nums, int k) {
    	k = k % nums.length;
    	
    	this.reverse(nums, 0, nums.length-1);
    	this.reverse(nums, 0, k-1);
    	this.reverse(nums, k, nums.length-1);
    }
    
    private void reverse(int[] nums, int l, int r) {
    	while (l < r) {
    		int temp = nums[r];
    		nums[r] = nums[l];
    		nums[l] = temp;
    		l++;
    		r--;
    	}
    }
    
    public static void main(String[] args) {
    	RotateArray solution = new RotateArray();
    	
    	int[] nums = {1,2,3,4,5,6,7};
    	solution.rotate_better(nums, 4);
    	
    	System.out.print(Arrays.toString(nums));
    }
}
