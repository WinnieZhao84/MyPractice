package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a sorted integer array without duplicates, return the summary of its ranges.
 * 
 * For example, given [0,1,2,4,5,7], return ["0->2","4->5","7"].
 * 
 * @author WinnieZhao
 *
 */
public class SummaryRanges {

    class Solution {
        public List<String> summaryRanges(int[] nums) {
            List<String> res = new ArrayList<>();

            if (nums == null || nums.length == 0) {
                return res;
            }

            if (nums.length == 1) {
                res.add(nums[0] + "");
                return res;
            }

            for (int i = 0; i < nums.length; i++) {
                int num = nums[i];
                while (i + 1 < nums.length && nums[i + 1] == nums[i] + 1) {
                    i++;
                }

                if (num != nums[i]) {
                    res.add(num + "->" + nums[i]);
                } else {
                    res.add(num + "");
                }
            }

            return res;
        }
    }

    public List<String> summaryRanges(int[] nums) {
        List<String> result = new ArrayList<>();
        if (nums == null || nums.length == 0) {
            return result;
        }
        
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        list.add(nums[0]);
        
        for (int i=1; i<nums.length; i++) {
            if (nums[i] == nums[i-1] + 1) {
                list.add(nums[i]);
            }
            else {
                res.add(list);
                list = new ArrayList<>();
                list.add(nums[i]);
            }
        }
        
        if (!list.isEmpty()) {
            res.add(list);
        }
        
        String str = "";
        for (List<Integer> l : res) {
            if (l.size() == 1) {
                result.add(String.valueOf(l.get(0)));
            }
            else {
                str = l.get(0) + "->" + l.get(l.size()-1);
                result.add(str);
            }

        }
        
        return result;
    }
    
    public static void main(String[] args) {
        int[] nums = {0,1,2,4,5,7};
        SummaryRanges solution = new SummaryRanges();
        
        System.out.println(solution.summaryRanges(nums));
    }
}
