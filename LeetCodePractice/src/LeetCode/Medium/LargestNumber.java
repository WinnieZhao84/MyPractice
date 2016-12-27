package LeetCode.Medium;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Given a list of non negative integers, arrange them such that they form the largest number.
 * For example, given [3, 30, 34, 5, 9], the largest formed number is 9534330.
 * Note: The result may be very large, so you need to return a string instead of an integer.

 * @author WinnieZhao
 *
 */
public class LargestNumber {

    public String largestNumber(int[] nums) {
        if (nums == null || nums.length == 0) return "";
        
        // Convert int array to String array, so we can sort later on
        String[] s_nums = new String[nums.length];
        for (int i = 0; i < nums.length; i++) {
            s_nums[i] = String.valueOf(nums[i]);
        }
        
        StringBuilder build = new StringBuilder();
        Arrays.sort(s_nums, new Comparator<String>() {
            @Override
            public int compare(String str1, String str2) {
                String s1 = str1 + str2;
                String s2 = str2 + str1;
                
                return s2.compareTo(s1);
            }
        });
        
        for (String s : s_nums) {
            build.append(s);
        }
        
        String res = build.toString();
        if (res.startsWith("0")) return "0";
        
        return res;
    }
    
    public static void main(String[] args) {
        LargestNumber solution = new LargestNumber();
        int[] nums = {3, 30, 34, 5, 9};
        System.out.println(solution.largestNumber(nums));
    }
}
