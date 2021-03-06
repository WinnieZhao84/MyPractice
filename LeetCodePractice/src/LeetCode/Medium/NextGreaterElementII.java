package LeetCode.Medium;

/**
 * Given a circular array (the next element of the last element is the first element of the array),
 * print the Next Greater Number for every element.
 * The Next Greater Number of a number x is the first greater number to its traversing-order next in the array,
 * which means you could search circularly to find its next greater number.
 * If it doesn't exist, output -1 for this number.

    Example 1:
    Input: [1,2,1]
    Output: [2,-1,2]
    
    Explanation: The first 1's next greater number is 2; 
    The number 2 can't find next greater number; 
    The second 1's next greater number needs to search circularly, which is also 2.
    Note: The length of given array won't exceed 10000.

 * @author WinnieZhao
 *
 */
public class NextGreaterElementII {

    /**
     * The typical way to solve circular array problems is to extend the original array to twice length, 
     * 2nd half has the same element as first half. Then everything become simple.
     * 
     * @param nums
     * @return
     */
    public int[] nextGreaterElements(int[] nums) {
        
        int n = nums.length;
        int[] result = new int[n];
        int[] temp = new int[n * 2];
        
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n * 2; i++) {
            temp[i] = nums[i % n];
            max = Math.max(max, nums[i % n]);
        }
        
        for (int i = 0; i < n; i++) {
            result[i] = -1;
            if (nums[i] == max) continue;
            
            for (int j = i + 1; j < n * 2; j++) {
                if (temp[j] > nums[i]) {
                    result[i] = temp[j];
                    break;
                }
            }
        }
        
        return result;
    }
}
