package LeetCode.Easy;

import java.util.Arrays;

/**
 * 
 * Winter is coming! Your first job during the contest is to design a standard heater with fixed warm radius to warm all the houses.
 * 
 * Now, you are given positions of houses and heaters on a horizontal line, find out minimum radius of heaters
 * so that all houses could be covered by those heaters.
 * 
 * So, your input will be the positions of houses and heaters separately, and your expected output will be the minimum
 * radius standard of heaters.
 * 
 * Note:
 * Numbers of houses and heaters you are given are non-negative and will not exceed 25000.
 * Positions of houses and heaters you are given are non-negative and will not exceed 10^9.
 * As long as a house is in the heaters' warm radius range, it can be warmed.
 * All the heaters follow your radius standard and the warm radius will the same.
 * 
 * Example 1:
 * Input: [1,2,3],[2]
 * Output: 1
 * Explanation: The only heater was placed in the position 2, and if we use the radius 1 standard,
 * then all the houses can be warmed.
 * 
 * Example 2:
 * Input: [1,2,3,4],[1,4]
 * Output: 1
 * Explanation: The two heater was placed in the position 1 and 4. We need to use radius 1 standard,
 * then all the houses can be warmed.

 * @author WinnieZhao
 *
 */
public class Heaters {

    public int findRadius(int[] houses, int[] heaters) {
        if (houses == null || houses.length ==0) return 0;
        if (heaters == null || heaters.length == 0)  return Integer.MAX_VALUE;

        int length = heaters.length;
        
        Arrays.sort(houses);
        Arrays.sort(heaters);
        
        int res = 0;
        int i = 0;
        for (int house : houses) {
            while (i<length-1 && Math.abs(house - heaters[i]) >= Math.abs(house - heaters[i+1])) {
                i++;
            }
            res = Math.max(res, Math.abs(house-heaters[i]));
        }
        return res;
    }
    
    public static void main(String[] args) {
        Heaters solution = new Heaters();
        
        int[] houses = {1,2,3};
        int[] heaters = {2};
        System.out.println(solution.findRadius(houses, heaters));
    }
}
