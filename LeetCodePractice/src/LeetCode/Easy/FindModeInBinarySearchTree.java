package LeetCode.Easy;

import LeetCode.Helper.TreeNode;

/**
 * Given a binary search tree (BST) with duplicates, find all the mode(s) (the most frequently occurred element) in the given BST.
 * 
 * Assume a BST is defined as follows:
 * 
 * The left subtree of a node contains only nodes with keys less than or equal to the node's key.
 * The right subtree of a node contains only nodes with keys greater than or equal to the node's key.
 * Both the left and right subtrees must also be binary search trees.
 * 
 * For example:
 * Given BST [1,null,2,2],
 *  1
 *   \
 *    2
 *   /
 *  2
* return [2].

* Note: If a tree has more than one mode, you can return them in any order.

* Follow up: Could you do that without using any extra space?
 * (Assume that the implicit stack space incurred due to recursion does not count).

 * @author WinnieZhao
 *
 */
public class FindModeInBinarySearchTree {

    /**
     * The way to do it properly is to do two passes.
     * One to find the highest number of occurrences of any value,
     * and then a second pass to collect all values occurring that often.
     *
     * Here's a (two-pass) solution that I think can rightfully be called O(1) space.
     * Both passes keep track of the current value etc, and the second pass additionally
     * collects the modes in the result array.
     * Also, this way you could very easily replace the recursive in-order traversal with
     * for example Morris traversal. Then you wouldn't even need to disregard the recursion
     * stack space in order to claim O(1) extra space usage.
     */
    private int currVal = 0;
    private int currCount = 0;
    private int maxCount = 0;
    private int modeCount = 0;
    
    private int[] modes;
    
    public int[] findMode(TreeNode root) {
        inorder(root);
        modes = new int[modeCount];
        modeCount = 0;
        currCount = 0;
        currVal = 0;
        inorder(root);
        return modes;
    }
    
    private void handleValue(int val) {
        if (val != currVal) {
            currVal = val;
            currCount = 0;
        }
        currCount++;
        if (currCount > maxCount) {
            maxCount = currCount;
            modeCount = 1;
        }
        else if (currCount == maxCount) {
            if (modes != null)
                modes[modeCount] = currVal;
            modeCount++;
        }
    }
    
    private void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        handleValue(root.val);
        inorder(root.right);
    }

}
