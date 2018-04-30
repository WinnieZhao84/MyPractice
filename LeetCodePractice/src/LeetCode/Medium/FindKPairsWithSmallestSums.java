package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

/**
 * You are given two integer arrays nums1 and nums2 sorted in ascending order and an integer k.
 * Define a pair (u,v) which consists of one element from the first array and one element from the second array.
 * 
 * Find the k pairs (u1,v1),(u2,v2) ...(uk,vk) with the smallest sums.
 * 
 * Example 1: Given nums1 = [1,7,11], nums2 = [2,4,6],  k = 3 
 * Return: [1,2],[1,4],[1,6]
 * 
 * The first 3 pairs are returned from the sequence: [1,2],[1,4],[1,6],[7,2],[7,4],[11,2],[7,6],[11,4],[11,6]
 * 
 * Example 2: Given nums1 = [1,1,2], nums2 = [1,2,3],  k = 2
 * Return: [1,1],[1,1]
 * 
 * The first 2 pairs are returned from the sequence: [1,1],[1,1],[1,2],[2,1],[1,2],[2,2],[1,3],[1,3],[2,3]
 * 
 * Example 3: Given nums1 = [1,2], nums2 = [3],  k = 3 
 * Return: [1,3],[2,3] 
 * 
 * All possible pairs are returned from the sequence: [1,3],[2,3]

 * @author WinnieZhao
 *
 */
public class FindKPairsWithSmallestSums {

    public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        List<int[]> result = new ArrayList<>();
        
        if (nums1 == null || nums2 == null || nums1.length == 0 || nums2.length == 0) {
            return result;
        }
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        
        int length1 = nums1.length;
        int length2 = nums2.length;
        
        for (int i=0; i<length1; i++) {
            for (int j=0; j<length2; j++) {
                Pair p = new Pair(nums1[i], nums2[j]);
                queue.offer(p);
            }
        }
        
        while (!queue.isEmpty() && result.size() != k) {
            int[] num = new int[2];
            Pair val = queue.poll();
            num[0] = val.num1;
            num[1] = val.num2;
            result.add(num);
        }
        
        return result;
    }
    
    private class Pair implements Comparable<Pair> {
        int num1;
        int num2;
        
        private Pair(int num1, int num2) {
            this.num1 = num1;
            this.num2 = num2;
        }
        
        @Override
        public int compareTo(Pair p) {
            return this.num1 + this.num2 - (p.num1 + p.num2);
        }
        
    }
    
    /**
     * Better solution: O(KlogK) time complexity
     * 
     * @author WinnieZhao
     *
     */
    public class Solution {
        class Pair{
            int[] pair;
            int idx; // current index to nums2
            int sum;
            Pair(int idx, int n1, int n2){
                this.idx = idx;
                pair = new int[]{n1, n2};
                sum = n1 + n2;
            }
        }

        public List<int[]> kSmallestPairs(int[] nums1, int[] nums2, int k) {
            List<int[]> ret = new ArrayList<>();
            if (nums1==null || nums2==null || nums1.length ==0 || nums2.length ==0) return ret;
            int len1 = nums1.length, len2=nums2.length;

            PriorityQueue<Pair> pq = new PriorityQueue<>(Comparator.comparingInt(p -> p.sum));

            for (int i=0; i<len1 && i<k ; i++) { // only need first k number in nums1 to start
                pq.offer( new Pair(0, nums1[i],nums2[0]) );
            }
            for (int i=1; i<=k && !pq.isEmpty(); i++) { // get the first k sums
                Pair p = pq.poll();
                ret.add( p.pair );
                if (p.idx < len2 -1 ) { // get to next value in nums2
                    int next = p.idx+1;
                    pq.offer( new Pair(next, p.pair[0], nums2[next]) );
                }
            }
            return ret;
        }
    }
    
    public static void main(String[] args) {
        FindKPairsWithSmallestSums solution = new FindKPairsWithSmallestSums();
        
        int[] nums1 = {1,1,2};
        int[] nums2 = {1,2,3};

        List<int[]> result = solution.kSmallestPairs(nums1, nums2, 12);
        result.stream().forEach(r -> System.out.println(Arrays.toString(r)));
    }
}
