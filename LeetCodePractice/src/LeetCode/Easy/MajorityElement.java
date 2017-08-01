package LeetCode.Easy;

import java.util.Arrays;

/**
 * Given an array of size n, find the majority element. The majority element is the element that appears more than n/2 times.

   You may assume that the array is non-empty and the majority element always exist in the array.
 * @author WinnieZhao
 *
 */
public class MajorityElement {

    // O(nlogn) time
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
    
    //O(n) time O(1) space fastest solution
    public int majorityElement_better(int[] num) {

        int major=num[0], count = 1;
        for(int i=1; i<num.length;i++){
            if(count==0){
                count++;
                major=num[i];
            }
            else if(major==num[i]){
                count++;
            }
            else {
                count--;
            }
            
        }
        return major;
    }
    
}
