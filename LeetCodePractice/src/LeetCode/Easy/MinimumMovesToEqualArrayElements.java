package LeetCode.Easy;

/**
 * Given a non-empty integer array of size n, find the minimum number of moves required to make all array elements equal, 
 * where a move is incrementing n - 1 elements by 1.
 * 
 * Example:
 * Input:
 * [1,2,3]
 * Output: 3
 * 
 * Explanation:
 * Only three moves are needed (remember each move increments two elements): [1,2,3]  =>  [2,3,3]  =>  [3,4,3]  =>  [4,4,4]
 * 
 * @author WinnieZhao
 *
 */
public class MinimumMovesToEqualArrayElements {

    /**
     * t: target value when all nums are equal
     * sum: sum of arrays
     * m: how many times for the move
     * min: the minimum from all nums
     * n: how many nums
     * 
     * 1) sum + (n-1) * m = n * t
     * 2) t - min * 1 = m  
     * 
     * 2) => n*t - n* min = n*m
     * 1) => sum + (n-1)*m = n*min + n*m
     * sum + n*m - m = n*min + n*m
     * sum -n*min = m
     * 
     * 
     * @param nums
     * @return
     */
    public int minMoves(int[] nums) {
        if (nums.length == 0) return 0;
        
        int res = 0;
        int min = Integer.MAX_VALUE;
        
        int sum = 0;
        for (int num : nums) {
            if (num < min) {
                min = num;
            }
            sum += num;
        }
        
        res = sum - nums.length * min;
        return res;
    }
    
    public static void main(String[] args) {
        MinimumMovesToEqualArrayElements solution = new MinimumMovesToEqualArrayElements();
        int[] nums = {1,2,3,4};
        System.out.println(solution.minMoves(nums));
    }
}
