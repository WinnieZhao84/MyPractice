package LeetCode.Medium;

import java.util.*;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * For example,
 * 
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * 
 * Note: 
 * You may assume k is always valid, 1 <= k <= number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 * @author ASUS-PC
 *
 */
public class TopKFrequentElements {

    /*
     * Use an array to save numbers into different bucket whose index is the frequency
     * O(n) solution
     */
    public List<Integer> topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int n : nums) {
            frequencyMap.put(n, frequencyMap.getOrDefault(n, 0) + 1);
        }

        List<Integer>[] bucket = new ArrayList[nums.length+1];

        for (int key : frequencyMap.keySet()) {
            int frequency = frequencyMap.get(key);
            if (bucket[frequency] == null) {
                bucket[frequency] = new ArrayList<>();
            }
            bucket[frequency].add(key);

        }

        List<Integer> res = new ArrayList<>();

        for (int pos = bucket.length - 1; pos >= 0 && res.size() < k; pos--) {
            if (bucket[pos] != null) {
                res.addAll(bucket[pos]);
            }
        }
        return res;
    }

    /** Use max Heap. Put entry into maxHeap so we can always poll a number with largest frequency
     *  O(NlogN) solution
     */
    public class Solution_I {
        public List<Integer> topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();
            for(int n: nums){
                map.put(n, map.getOrDefault(n,0)+1);
            }

            PriorityQueue<Map.Entry<Integer, Integer>> maxHeap = new PriorityQueue<>((a, b) -> (b.getValue()-a.getValue()));
            for(Map.Entry<Integer,Integer> entry: map.entrySet()){
                maxHeap.add(entry);
            }

            List<Integer> res = new ArrayList<>();
            while(res.size()<k){
                Map.Entry<Integer, Integer> entry = maxHeap.poll();
                res.add(entry.getKey());
            }
            return res;
        }
    }

    /**
     *   Use TreeMap. Use frequency as the key so we can get all frequencies in order
     *   O(NlogN) solution
     *
     *   TreeMap implementation provides guaranteed log(n) time cost for the containsKey, get, put and remove operations
      */
    public class Solution_II {
        public List<Integer> topKFrequent(int[] nums, int k) {
            Map<Integer, Integer> map = new HashMap<>();

            for(int n: nums){
                map.put(n, map.getOrDefault(n,0)+1);
            }

            TreeMap<Integer, List<Integer>> freqMap = new TreeMap<>();
            for(int num : map.keySet()){
                int freq = map.get(num);
                if(!freqMap.containsKey(freq)){
                    freqMap.put(freq, new LinkedList<>());
                }
                freqMap.get(freq).add(num);
            }

            List<Integer> res = new ArrayList<>();
            while(res.size()<k){
                Map.Entry<Integer, List<Integer>> entry = freqMap.pollLastEntry();
                res.addAll(entry.getValue());
            }
            return res;
        }
    }
    
    public static void main (String[] args) {
    	TopKFrequentElements solution = new TopKFrequentElements();
    	
    	int[] nums = {1,1,1,1,2,3};
    	
    	System.out.print(Arrays.toString(solution.topKFrequent(nums, 3).toArray()));
    }
}
