package LeetCode.Medium;

import java.util.Arrays;

/**
 * Implement next permutation, which rearranges numbers into the lexicographically next greater permutation of numbers.
 * 
 * If such arrangement is not possible, it must rearrange it as the lowest possible order (ie, sorted in ascending order).
 * The replacement must be in-place, do not allocate extra memory.
 * 
 * Here are some examples. Inputs are in the left-hand column and its corresponding outputs are in the right-hand column.
 * 
 * 1,2,3 ¡ú 1,3,2
 * 3,2,1 ¡ú 1,2,3
 * 1,1,5 ¡ú 1,5,1
 * 
 * @author WinnieZhao
 *
 */
public class PermutationNext {
    public void nextPermutation(int[] nums) {
        if (nums == null || nums.length <= 1) return;
        
        int length = nums.length;
        
        if (nums[length-1] > nums[length-2]) {
            int temp = nums[length-1];
            nums[length-1] = nums[length-2];
            nums[length-2] = temp;
            return;
        }
        else {
            int i=length-1;
            for (; i>=1; i--) {
                if( nums[i] > nums[i-1]) { //find first number which is smaller than it's after number
                    break;
                }
            }
            
            if(i!=0){
                swap(nums,i-1); //if the number exist,which means that the nums not like{5,4,3,2,1}
            }
            
            reverse(nums,i);
                
        }
    }
    
    private void swap(int[] a,int i){
        for(int j = a.length-1;j>i;j--){
            if(a[j]>a[i]){
                int t = a[j];
                a[j] = a[i];
                a[i] = t;
                break;
            }
        }
    }
    
    private void reverse(int[] a,int i){//reverse the number after the number we have found
        int first = i;
        int last = a.length-1;
        while(first<last){
            int t = a[first];
            a[first] = a[last];
            a[last] = t;
            first ++;
            last --;
        }
    }
    
    public static void main(String[] args) {
        int[] nums = {1,2,4,4};
        
        PermutationNext solution = new PermutationNext();
        solution.nextPermutation(nums);
        
        System.out.println(Arrays.toString(nums));
    }
}
