package LeetCode.Hard;

import java.util.ArrayList;
import java.util.List;

/**
 * You are given an integer array nums and you have to return a new counts array.
 * The counts array has the property where counts[i] is the number of smaller
 * elements to the right of nums[i].
 *
 * Example: Given nums = [5, 2, 6, 1]
 *
 * To the right of 5 there are 2 smaller elements (2 and 1).
 * To the right of 2 there is only 1 smaller element (1).
 * To the right of 6 there is 1 smaller element (1).
 * To the right of 1 there is 0 smaller element.
 *
 * Return the array [2, 1, 1, 0].

 * Created by WinnieZhao on 6/5/2017.
 */
public class CountOfSmallerNumbersAfterSelf {

    public List<Integer> countSmaller(int[] nums) {

    }
    

    public static void main(String[] args) {
        CountOfSmallerNumbersAfterSelf solution = new CountOfSmallerNumbersAfterSelf();

        int[] nums = {5, 2, 6, 1};
        System.out.print(solution.countSmaller(nums).toString());
    }
}
