package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Given a non-empty array of integers, return the k most frequent elements.
 * 
 * For example,
 * 
 * Given [1,1,1,2,2,3] and k = 2, return [1,2].
 * 
 * Note: 
 * You may assume k is always valid, 1 ¡Ü k ¡Ü number of unique elements.
 * Your algorithm's time complexity must be better than O(n log n), where n is the array's size.

 * @author ASUS-PC
 *
 */
public class TopKFrequentElements {
    @SuppressWarnings("unchecked")
	public List<Integer> topKFrequent(int[] nums, int k) {
    	Map<Integer, Integer> frequencyMap = new HashMap<Integer, Integer>();

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
    
    public static void main (String[] args) {
    	TopKFrequentElements solution = new TopKFrequentElements();
    	
    	int[] nums = {1,1,1,1,2,3};
    	
    	System.out.print(Arrays.toString(solution.topKFrequent(nums, 5).toArray()));
    }
}
