package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Shuffle a set of numbers without duplicates.
 * // Init an array with set 1, 2, and 3.
 * 
 * int[] nums = {1,2,3};
 * Solution solution = new Solution(nums);
 * 
 * 
 * // Shuffle the array [1,2,3] and return its result. Any permutation of [1,2,3] must equally likely to be returned.
 * solution.shuffle();
 * 
 * // Resets the array back to its original configuration [1,2,3].
 * solution.reset();
 * 
 * // Returns the random shuffling of array [1,2,3].
 * solution.shuffle();
 * 
 * 
 * @author WinnieZhao
 *
 */
public class ShuffleAnArray {

    private final int[] nums;
    private Random random;
    
    public ShuffleAnArray(int[] nums) {
        this.nums = nums;
        random = new Random();
    }
    
    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.nums;
    }
    
    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int size = nums.length;
        int[] shuffledNums = new int[size];

        List<Integer> index = new ArrayList<Integer>();
        while(index.size() < size) {
            int value = random.nextInt(size);
            if (!index.contains(value)) {
                index.add(value);
            }
        }
        
        for (int i=0; i<size; i++) {
            shuffledNums[i] = nums[index.get(i)];
        }
        
        return shuffledNums;
    }

    private int randRange(int min, int max) {
        return random.nextInt(max - min) + min;
    }
    
    public int[] shuffle_better() {
        int size = nums.length;
        int[] shuffledNums = Arrays.copyOf(nums, size);
        
        for (int i=0; i<size; i++) {
            // Pick an element to swap from the range [i, j)
            this.swap(shuffledNums, i, this.randRange(i, size));
        }
        
        return shuffledNums;
    }
    
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
    
    public static void main(String args[]) {
        int[] nums = {1,2,3};
        
        ShuffleAnArray solution = new ShuffleAnArray(nums);
        for (int count = 0; count<1000; count++) {
            System.out.println(Arrays.toString(solution.shuffle_better()));
        }
        
        System.out.println(Arrays.toString(solution.reset()));
    }
}


/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int[] param_1 = obj.reset();
 * int[] param_2 = obj.shuffle();
 */
