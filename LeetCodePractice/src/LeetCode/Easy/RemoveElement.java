package LeetCode.Easy;

/**
 * 
 * Given an array and a value, remove all instances of that value in place and return the new length.

Do not allocate extra space for another array, you must do this in place with constant memory.

The order of elements can be changed. It doesn't matter what you leave beyond the new length.

Example:
Given input array nums = [3,2,2,3], val = 3

Your function should return length = 2, with the first two elements of nums being 2.
 * 
 * 
 * @author ASUS-PC
 *
 */
public class RemoveElement {

    public int removeElement(int[] nums, int val) {
    	
    	int size = 0;
    	int i = 0;
    	int j = 0;
        while (i < nums.length && j < nums.length) {
        	if (nums[i] == val) {
        		i++;
        	}
        	else {
        		nums[j] = nums[i];
        		i++;
        		j++;
        		size++;
        	}
        }
        
        return size;
    }
    
    public static void main(String[] args) {
    	RemoveElement solution = new RemoveElement();
        
    	int[] array = {1, 2, 2, 3, 4, 3};
        System.out.println(solution.removeElement(array,2));
        for (int a: array) {
        	System.out.print(a);
        }
    }
}
