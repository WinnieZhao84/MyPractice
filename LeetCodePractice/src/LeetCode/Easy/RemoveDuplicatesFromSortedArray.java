package LeetCode.Easy;

/**
 * Given a sorted array, remove the duplicates in place such that each element appear only once and return the new length.
 *
 * Do not allocate extra space for another array, you must do this in place with constant memory.
 *
 * For example, Given input array nums = [1,1,2],
 *
 * Your function should return length = 2, with the first two elements of nums being 1 and 2 respectively.
 * It doesn't matter what you leave beyond the new length.
 
 * @author ASUS-PC
 *
 */
public class RemoveDuplicatesFromSortedArray {

    public int removeDuplicates(int[] nums) {
     
    	if (nums.length <= 1) {
    		return nums.length;
    	}
    	
    	int length = 0;
    	for (int i=0; i<=nums.length-1; i++ ) {
    		if (nums[i] != nums[length]) {	
    			nums[++length] = nums[i];
    		}
    	}
    	
    	return length + 1;
    }
    
    public static void main(String[] args) {
    	RemoveDuplicatesFromSortedArray solution = new RemoveDuplicatesFromSortedArray();
        
    	int[] array = {1, 2, 2, 3, 3};
        System.out.println(solution.removeDuplicates(array));
        for (int a: array) {
        	System.out.print(a);
        }
    }
}
