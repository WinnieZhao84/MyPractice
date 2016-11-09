package LeetCode.Easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example:
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2, 2].
 * 
 * Note:
 * Each element in the result should appear as many times as it shows in both arrays.
 * The result can be in any order.
 * 
 * Follow up:
 * What if the given array is already sorted? How would you optimize your algorithm?
 * What if nums1's size is small compared to nums2's size? Which algorithm is better?
 * What if elements of nums2 are stored on disk, and the memory is limited such that you cannot load all elements into the memory at once?
 * 
 * @author WinnieZhao
 *
 */
public class IntersectionOfTwoArraysII {
    
    public int[] intersect(int[] nums1, int[] nums2) {
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int len1 = nums1.length;
        int len2 = nums2.length;
        
        List<Integer> s = new ArrayList<Integer>();
        
        int i=0;
        int j=0;
        while (i<len1 && j<len2) {
            if (nums1[i] == nums2[j]) {
                s.add(nums1[i]);
                i++;
                j++;
            }
            else if (nums1[i] < nums2[j]) {
                i++;
            }
            else if (nums1[i] > nums2[j]) {
                j++;
            }
        }
        
        int[] res = new int[s.size()];
        int x = 0;
        for(Integer m : s) {
            res[x] = m;
            x++;
        }
        
        return res;
    }
}
