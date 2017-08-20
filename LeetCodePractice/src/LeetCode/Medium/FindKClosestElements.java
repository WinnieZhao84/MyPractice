package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Given a sorted array, two integers k and x, find the k closest elements to x in the array.
 * The result should also be sorted in ascending order. If there is a tie, the smaller elements are always preferred.
 *
 * Example 1:
 * Input: [1,2,3,4,5], k=4, x=3
 * Output: [1,2,3,4]
 *
 * Example 2:
 * Input: [1,2,3,4,5], k=4, x=-1
 * Output: [1,2,3,4]
 *
 * Note:
 * The value k is positive and will always be smaller than the length of the sorted array.
 * Length of the given array is positive and will not exceed 10^4
 * Absolute value of elements in the array and x will not exceed 10^4

 * Created by WinnieZhao on 8/19/2017.
 */
public class FindKClosestElements {

    // O(nlog(n)) Time Solution:
    public List<Integer> findClosestElements(List<Integer> arr, int k, int x) {
        List<Integer> result = new ArrayList<>();
        if (arr == null || arr.isEmpty() || k <= 0) {
            return result;
        }
        if (k >= arr.size()) {
            return arr;
        }

        List<Integer> res = arr.stream().sorted((a,b) -> a==b ? 0 : Math.abs(a-x) - Math.abs(b-x)).collect(Collectors.toList());

        res = res.subList(0, k);
        res.sort(Comparator.naturalOrder());

        return res;
    }

    public List<Integer> findClosestElements_better(List<Integer> arr, int k, int x) {
        int lo = 0;
        int hi = arr.size() - k;

        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (x - arr.get(mid) > arr.get(mid+k) - x)
                lo = mid + 1;
            else
                hi = mid;
        }
        return arr.subList(lo, lo + k);
    }

    public static void main(String[] args) {
        FindKClosestElements solution = new FindKClosestElements();

        Integer[] array = {1,2,3,4,5};
        List<Integer> list = Arrays.stream(array).collect(Collectors.toList());

        System.out.println(solution.findClosestElements_better(list, 4, 3));
    }
}
