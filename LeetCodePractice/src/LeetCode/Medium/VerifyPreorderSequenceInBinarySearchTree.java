package LeetCode.Medium;

import java.util.Stack;

/**
 * 255
 *
 * Given an array of numbers, verify whether it is the correct preorder traversal sequence of a binary search tree.
 *
 * You may assume each number in the sequence is unique.
 * Follow up: Could you do it using only constant space complexity?

 * Created by WinnieZhao on 2017/3/28.
 */
public class VerifyPreorderSequenceInBinarySearchTree {

    public boolean verifyPreorder(int[] preorder) {
        Stack<Integer> stack =new Stack<>();
        int min = Integer.MIN_VALUE;

        for(int val : preorder){
            if( val < min) return false;

            while(!stack.isEmpty() && val > stack.peek()){
                min = stack.pop();
            }
            stack.push(val);
        }
        return true;
    }

    public boolean verifyPreorder_constantSpace(int[] preorder) {
        int i = -1, min = Integer.MIN_VALUE;
        for(int num : preorder){
            if(num < min) return false;
            while(i >= 0 && num > preorder[i]){
                min = preorder[i--];
            }
            preorder[++i] = num;
        }
        return true;
    }

    public static void main(String[] args) {
        int[] preorder = {4, 5, 2};
        VerifyPreorderSequenceInBinarySearchTree solution = new VerifyPreorderSequenceInBinarySearchTree();
        System.out.print(solution.verifyPreorder_constantSpace(preorder));
    }
}
