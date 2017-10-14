package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a list of positive integers, the adjacent integers will perform the float division.
 * For example, [2,3,4] -> 2 / 3 / 4.
 *
 * However, you can add any number of parenthesis at any position to change the priority of operations.
 * You should find out how to add parenthesis to get the maximum result, and return the corresponding
 * expression in string format. Your expression should NOT contain redundant parenthesis.
 *
 * Example:
 * Input: [1000,100,10,2]
 * Output: "1000/(100/10/2)"
 * Explanation:
 * 1000/(100/10/2) = 1000/((100/10)/2) = 200
 * However, the bold parenthesis in "1000/((100/10)/2)" are redundant,
 * since they don't influence the operation priority. So you should return "1000/(100/10/2)".
 *
 * Other cases:
 * 1000/(100/10)/2 = 50
 * 1000/(100/(10/2)) = 50
 * 1000/100/10/2 = 0.5
 * 1000/100/(10/2) = 2
 *
 * Note:
 * The length of the input array is [1, 10].
 * Elements in the given array will be in range [2, 1000].
 * There is only one optimal division for each test case.
 *
 * Created by WinnieZhao on 4/18/2017.
 */
public class OptimalDivision {

    public String optimalDivision(int[] nums) {
        if (nums.length == 1)
            return nums[0] + "";

        if (nums.length == 2)
            return nums[0] + "/" + nums[1];

        StringBuilder res = new StringBuilder(nums[0] + "/(" + nums[1]);
        for (int i = 2; i < nums.length; i++) {
            res.append("/" + nums[i]);
        }
        res.append(")");

        return res.toString();
    }

    // Normal recursion solution
    public class Solution {

        public String optimalDivision(int[] nums) {
            Map<String, Pair> memory = new HashMap<>();
            Pair sol = divide(nums,0,nums.length-1, memory);
            return sol.maxS;
        }

        public Pair divide(int[] nums, int start, int end, Map<String, Pair> memory){
            String key = start + " " + end;
            if(memory.containsKey(key)) {
                return memory.get(key);
            }
            if(start == end)    {
                return new Pair(nums[start], "" + nums[start],nums[start], "" + nums[start]);
            }

            Pair sol = new Pair(0,"",0,"");

            for(int i = start; i < end; i++){
                Pair left = divide(nums, start, i, memory);
                Pair right = divide(nums, i + 1, end, memory);

                double min = left.min / right.max;
                String minS = left.minS + "/" + (i + 1 == end ? right.maxS : "(" + right.maxS + ")");
                double max = left.max / right.min;
                String maxS = left.maxS + "/" + (i + 1 == end ? right.minS : "(" + right.minS + ")");
                if(sol.min == 0 || min < sol.min){
                    sol.min = min;
                    sol.minS = minS;
                }
                if(max > sol.max){
                    sol.max = max;
                    sol.maxS = maxS;
                }
            }
            memory.put(key, sol);
            return sol;
        }
    }

    class Pair{
        double min;
        String minS;
        double max;
        String maxS;

        public Pair(double min, String minS, double max, String maxS){
            this.min = min;
            this.minS = minS;
            this.max = max;
            this.maxS = maxS;
        }
    }
}

