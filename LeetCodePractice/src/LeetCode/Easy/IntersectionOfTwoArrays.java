package LeetCode.Easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given two arrays, write a function to compute their intersection.
 * 
 * Example:
 * 
 * Given nums1 = [1, 2, 2, 1], nums2 = [2, 2], return [2].
 * 
 * Note:
 * 
 * Each element in the result must be unique.
 * The result can be in any order.
 * 
 * @author WinnieZhao
 *
 */
public class IntersectionOfTwoArrays {

    public int[] intersection(int[] nums1, int[] nums2) {
        
        if (nums1 == null || nums1.length == 0 || nums2 == null || nums2.length == 0) {
            return new int[0];
        }
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        
        int len1 = nums1.length;
        int len2 = nums2.length;
        
        Set<Integer> s = new HashSet<Integer>();
        
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
    
    public static void main(String[] args) {
        IntersectionOfTwoArrays solution = new IntersectionOfTwoArrays();
        int[] nums1 = {1,2,4,6,8};
        int[] nums2 = {2,4,7};
        
        System.out.println(Arrays.toString(solution.intersection(nums1, nums2)));
    }
}
