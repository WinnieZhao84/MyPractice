package LeetCode.Hard;

import java.util.*;

/**
 * You have k lists of sorted integers in ascending order. Find the smallest range that includes at least
 * one number from each of the k lists.
 *
 * We define the range [a,b] is smaller than range [c,d] if b-a < d-c or a < c if b-a == d-c.
 *
 * Example 1:
 * Input:[[4,10,15,24,26], [0,9,12,20], [5,18,22,30]]
 * Output: [20,24]
 *
 * Explanation:
 * List 1: [4, 10, 15, 24,26], 24 is in range [20,24].
 * List 2: [0, 9, 12, 20], 20 is in range [20,24].
 * List 3: [5, 18, 22, 30], 22 is in range [20,24].
 *
 * Note:
 * The given list may contain duplicates, so ascending order means >= here.
 * 1 <= k <= 3500
 * -10^5 <= value of elements <= 10^5.
 * For Java users, please note that the input type has been changed to List<List<Integer>>.
 * And after you reset the code template, you'll see this point.

 * Created by WinnieZhao on 7/3/2017.
 */
public class SmallestRange {

    /**
     * Image you are merging k sorted array using a heap. Then every time you pop the smallest element out
     * and add the next element of that array to the heap. By keep doing this, you will have the smallest range.
     *
     * @param nums
     * @return
     */
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(nums.size(), (array1, array2) -> array1[0] - array2[0]);

        int max = nums.get(0).get(0);
        for(int i=0; i<nums.size(); i++) {
            minHeap.add(new int[]{nums.get(i).get(0), i, 0});
            max = Math.max(max, nums.get(i).get(0));
        }

        int minRange = Integer.MAX_VALUE;
        int start = -1;
        while(minHeap.size() == nums.size()) {
            int[] t = minHeap.poll();
            if(max - t[0] < minRange) {
                minRange = max - t[0];
                start = t[0];
            }

            if(t[2]+1 < nums.get(t[1]).size()) {
                t[0] = nums.get(t[1]).get(t[2]+1);
                t[2]++;
                minHeap.add(t);
                max = Math.max(max, t[0]);
            }
        }

        return new int[]{start, start+minRange};
    }

    public static void main(String[] args) {
        SmallestRange solution = new SmallestRange();

        Integer[] nums1 = {4, 10, 15, 24, 26};
        Integer[] nums2 = {0, 9, 12, 20};
        Integer[] nums3 = {5, 18, 22, 30};

        List<List<Integer>> nums = new ArrayList<>();
        nums.add(Arrays.asList(nums1));
        nums.add(Arrays.asList(nums2));
        nums.add(Arrays.asList(nums3));

        int[] res = solution.smallestRange(nums);
        Arrays.stream(res).forEach(e -> System.out.println(e));
    }
}
