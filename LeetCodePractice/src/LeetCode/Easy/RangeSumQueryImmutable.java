package LeetCode.Easy;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i <= j), inclusive.
 * 
 * Example:
 * Given nums = [-2, 0, 3, -5, 2, -1]
 * 
 * [-2, -2, 1, -4, -2, -3]
 * 
 * sumRange(0, 2) -> 1
 * sumRange(2, 5) -> -1
 * sumRange(0, 5) -> -3
 * 
 * Note:
 * You may assume that the array does not change.
 * There are many calls to sumRange function.
 * 
 * @author ASUS-PC
 *
 */

public class RangeSumQueryImmutable {

    //Your NumArray object will be instantiated and called as such:
    //NumArray numArray = new NumArray(nums);
    //numArray.sumRange(0, 1);
    //numArray.sumRange(1, 2)
    public RangeSumQueryImmutable (int[] nums) {

    	table = new int[nums.length];
    	int sum = 0;
    	for (int i=0; i<nums.length; i++) {
    		sum += nums[i];
    		table[i] = sum;
    	}
    	
    }

    public int sumRange(int i, int j) {
    	if (i == 0) {
    		return table[j];
    	}
    	
    	return table[j] - table[i-1];
    }
    
    private int[] table;
    
    public static void main(String[] args) {
    	int[] nums = {-2, 0, 3, -5, 2, -1};
    	
    	RangeSumQueryImmutable numArray = new RangeSumQueryImmutable(nums);
    	System.out.println(numArray.sumRange(0, 2));
    	System.out.println(numArray.sumRange(0, 5));
    	System.out.println(numArray.sumRange(2, 5));
    }
}
