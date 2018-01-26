package LeetCode.Hard;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 *
 * Given an unsorted array of integers, find the length of the longest consecutive elements sequence.
 *
 * For example,
 * Given [100, 4, 200, 1, 3, 2],
 *
 * The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
 * Your algorithm should run in O(n) complexity.

 * Created by WinnieZhao on 4/26/2017.
 */
public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {

        Set<Integer> set = new HashSet<>();
        for(int i : nums){
            set.add(i);
        }
        int max = 0;
        for(int i=0; i< nums.length; i++){
            int low = nums[i] - 1;
            while(set.contains(low)){
                set.remove(low);
                low--;
            }

            int up = nums[i] + 1;
            while(set.contains(up)){
                set.remove(up);
                up++;
            }
            max = Math.max(max, (up - low - 1));
        }

        return max;
    }

    public int longestConsecutive_better(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int max = 1;
        for (int num : nums) {
            if (map.containsKey(num)) {
                continue;
            }
            else {
                int left = map.getOrDefault(num-1, 0);
                int right = map.getOrDefault(num+1, 0);

                int sum = left + right + 1;
                map.put(num, sum);
                max = Math.max(max, sum);

                /**
                 * Extend the length to the left & right boundary(s) of the sequence
                 * Will do nothing if n has no neighbors
                 */
                map.put(num-left, sum);
                map.put(num+right, sum);
            }
        }

        return max;
    }

    public static void main(String[] args) {
        LongestConsecutiveSequence solution = new LongestConsecutiveSequence();

        System.out.println(solution.longestConsecutive(new int[] {1,3,4,5}));
    }

}
