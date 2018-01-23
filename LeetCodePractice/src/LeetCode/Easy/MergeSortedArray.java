package LeetCode.Easy;

import java.util.Arrays;

/**
 * Given two sorted integer arrays nums1 and nums2, merge nums2 into nums1 as one sorted array.
 * 
 * Note:
 * You may assume that nums1 has enough space (size that is greater or equal to m + n) 
 * to hold additional elements from nums2. The number of elements initialized in nums1 and nums2 are m and n respectively.
 * 
 * @author ASUS-PC
 *
 */
public class MergeSortedArray {
    // nums1: 1,3,5,7
    // nums2: 2,4,6,8
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int size = m+n;
        int pos = size-1;

        m=m-1;
        n=n-1;

        while (m >=0 && n >=0) {
            if (nums1[m] <= nums2[n]) {
                nums1[pos] = nums2[n];
                pos--;
                n--;
            }
            else {
                nums1[pos] = nums1[m];
                pos--;
                m--;
            }
        }

        // if nums2 still has sth left 
        for (int i = 0; i <= n; i++) {
            nums1[i] = nums2[i];
        }
    }
    
    public static void main(String[] args) {
    	MergeSortedArray solution = new MergeSortedArray();
    	
    	int[] nums1 = new int[8];
    	nums1[0] = 1;
    	nums1[1] = 3;
    	nums1[2] = 5;
    	nums1[3] = 7;
    	
    	//int[] nums2 = {9,10,11,12};
    	//int[] nums2 = {-5,-4,-3,-2};
    	int[] nums2 = {2,4,6,8};
    	
    	solution.merge(nums1, 4, nums2, 4);
    	
    	System.out.println(Arrays.toString(nums1));
    }
}
