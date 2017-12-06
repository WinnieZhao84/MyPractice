package LeetCode.Medium;

/**
 * Given an array nums containing n + 1 integers where each integer is between 1 and n (inclusive),
 * prove that at least one duplicate number must exist. Assume that there is only one duplicate number,
 * find the duplicate one.
 * 
 * Note:
 * You must not modify the array (assume the array is read only).
 * You must use only constant, O(1) extra space.
 * Your runtime complexity should be less than O(n2).
 * There is only one duplicate number in the array, but it could be repeated more than once.

 * @author WinnieZhao
 *
 */
public class FindTheDuplicateNumber {

    /**
     * Time complexity, nlog(n) solution
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        // if n = 10, mid = 5;
        int start = 1;
        int end = nums.length-1;
        int mid = 0;
        while (start < end) {
            mid = start + (end - start)/2;
            
            int count = 0;
            for (int n : nums) {
                if (n <= mid) {
                    count++;
                }
            }
            
            if (count <= mid) {
                start = mid + 1;
            }
            else {
                end = mid;
            }
        }
        
        return start;
    }

    /**
     * Time Complexity: O(n) solution
     *  The first while loop ensures you goes in the correct cycle which has duplicates.
     *  For example: index = [0 1 2 3 4 5 6 7]; nums=[5 2 1 3 5 7 6 4].
     *  slow = (0)5 (5)7 (7)4 (4)5;
     *  fast = (0)5 (7)4 (5)7 (4)5; ----> when they meets at (idx=4)(value=5), you know you have a cycle.
     *
     *  Take a look at the cycle by the indices and values:
     *  idx: 0--->5--->7--->4-->(goes back to idx=5)
     *  val: 5--->7--->4--->5-->(goes back to val=7)
     *
     *  The second while loop will stop when "fast=0" and "slow=4" (their values = 5, the duplicate number).
     *  The duplicate number 5 is the reason why the two pointers will meet at a same index (next number).
     *  In fact, the second loop will always stop right before they meet at the first item of the cycle.
     *  (This is very similar to the problem Linked List Cycle II. )

     * @param nums
     * @return
     */
    public int findDuplicate_better(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int slow = nums[0];
        int fast = nums[nums[0]];

        while (slow != fast) {
            slow = nums[slow];
            fast = nums[nums[fast]];
        }

        fast = 0;
        while (slow != fast) {
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
    
}
