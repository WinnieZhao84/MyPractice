package LeetCode.Easy;

import java.util.Stack;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary search tree with non-negative values, find the minimum absolute difference between values of any two nodes.
 * 
 * Example:
 * 
 * Input:
 *  1
 *   \
 *    3
 *   /
 *  2
 *  
 *  Output: 1
 *  
 *  Explanation:
 *  
 *  The minimum absolute difference is 1, which is the difference between 2 and 1 (or between 2 and 3).
 *  Note: There are at least two nodes in this BST.

 * @author WinnieZhao
 *
 */
public class MinimumAbsoluteDifferenceInBST {
    
    public int getMinimumDifference(TreeNode root) {
        
        int min = Integer.MAX_VALUE;
        Stack<TreeNode> stack = new Stack<>();
        
        TreeNode cur = root, pre = null;
        while (cur != null || !stack.empty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            } 
            cur = stack.pop(); 
            if (pre != null) {
                min = Math.min(min, cur.val - pre.val);
            }
            pre = cur; 
            cur = cur.right; 
        }
        return min;
    }
    


}
