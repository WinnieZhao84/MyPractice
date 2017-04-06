package LeetCode.Medium;

/**
 * 360
 *
 * Given a sorted array of integers nums and integer values a, b and c.
 * Apply a function of the form f(x) = ax^2 + bx + c to each element x in the array.
 *
 * The returned array must be in sorted order.
 * Expected time complexity: O(n)
 *
 * Example:
 * nums = [-4, -2, 2, 4], a = 1, b = 3, c = 5,
 * Result: [3, 9, 15, 33]
 *
 * nums = [-4, -2, 2, 4], a = -1, b = 3, c = 5
 * Result: [-23, -5, 1, 7]

 * Created by WinnieZhao on 2017/4/5.
 */
public class SortTransformedArray {

    public int[] sortTransformedArray(int[] nums, int a, int b, int c) {
        if(nums == null || nums.length <=1) return nums;

        int[] res = new int[nums.length];
        if(a > 0){
            int k = res.length-1;
            int l = 0, r = k;
            while(k >=0){
                int tl = getT(nums[l],a,b,c);
                int tr = getT(nums[r],a,b,c);

                if(tl > tr){
                    res[k] = tl;
                    l++;
                }else{
                    res[k] = tr;
                    r--;
                }
                k--;
            }
        }
        else if(a < 0){
            int k = 0;
            int l = 0, r = res.length-1;
            while(k < nums.length){
                int tl = getT(nums[l],a,b,c);
                int tr = getT(nums[r],a,b,c);

                if(tl < tr){
                    res[k] = tl;
                    l++;
                }else{
                    res[k] = tr;
                    r--;
                }
                k++;
            }
        }else{
            for(int i= 0; i< res.length;i++){
                res[i] = getT(nums[i], 0,b,c);
            }
            if(b<0){
                int l =0, r = res.length-1;
                while(l < r){
                    int tmp = res[l];
                    res[l] = res[r];
                    res[r] = tmp;
                    l++;
                    r--;
                }
            }
        }

        return res;
    }

    int getT(int x, int a, int b, int c){
        return a*x*x + b*x + c;
    }

}
