package LeetCode.Medium;

import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order, 
 * not the kth distinct element.
 * 
 * For example,
 * Given [3,2,1,5,6,4] and k = 2, return 5.
 * 
 * Note: 
 * You may assume k is always valid, 1 ¡Ü k ¡Ü array's length.
 * 
 * @author WinnieZhao
 *
 */
public class KthLargestElementInAnArray {

    public int findKthLargest(int[] nums, int k) {
        final PriorityQueue<Integer> pq = new PriorityQueue<>();
        for(int val : nums) {
            pq.offer(val);

            if(pq.size() > k) {
                pq.poll();
            }
        }
        return pq.peek();
    }
    
    public static void main(String[] args) {
        KthLargestElementInAnArray solution = new KthLargestElementInAnArray();
        
        int[] nums = {3,2,1,5,6,4};
        System.out.println(solution.findKthLargest(nums, 3));
    }
}
