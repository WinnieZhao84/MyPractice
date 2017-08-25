package LeetCode.Hard;

/**
 *
 * Given n non-negative integers representing an elevation map where the width of each bar is 1,
 * compute how much water it is able to trap after raining.
 *
 * For example, Given [0,1,0,2,1,0,1,3,2,1,2,1], return 6.

 * Created by WinnieZhao on 4/25/2017.
 */
public class TrappingRainWater {

    public int trap(int[] height) {

        int peak = 0;
        int max = 0;

        for(int i=0; i< height.length; i++){
            if(height[i] > max){
                max = height[i];
                peak = i;
            }
        }

        int amount  =0;

        int leftMax =0;
        for(int i=0; i<peak; i++){
            if (height[i] >= leftMax){
                leftMax = height[i];
            }
            else{
                amount += (leftMax - height[i]);
            }
        }

        int rightMax =0;
        for(int i= height.length-1; i> peak; i--){
            if(height[i] >= rightMax){
                rightMax = height[i];
            }
            else{
                amount += (rightMax - height[i]);
            }
        }

        return amount;
    }

}
