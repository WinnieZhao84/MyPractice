package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

/**
 * 333
 *
 * Given a binary tree, find the largest subtree which is a Binary Search Tree (BST),
 * where largest means subtree with largest number of nodes in it.
 *
 * Note: A subtree must include all of its descendants. Here's an example:
 *      10
 *     / \
 *    5  15
 *   / \   \
 *  1   8   7
 *
 * The Largest BST Subtree in this case is the highlighted one. The return value is the subtree's size, which is 3.
 *
 * Created by WinnieZhao on 2017/3/28.
 */
public class LargestBSTSubtree {

    private int size;
    public int largestBSTSubtree(TreeNode root) {
        if (root == null) {
            return 0;
        }
        size = 0;
        this.isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE);

        return size;
    }

    private boolean isBST(TreeNode root, Integer low, Integer high) {
        if (root == null) {
            return true;
        }
        boolean left = isBST(root.left, low, root.val);
        boolean right = isBST(root.right, root.val, high);

        if (left && right && root.val > low && root.val < high) {
            size++;
            return true;
        }

        return false;
    }

    /*
    in brute-force solution, we get information in a top-down manner.
    for O(n) solution, we do it in bottom-up manner, meaning we collect information during backtracking.
    */
    public class Solution {

        class Result {  // (size, rangeLower, rangeUpper) -- size of current tree, range of current tree [rangeLower, rangeUpper]
            int size;
            int lower;
            int upper;

            Result(int size, int lower, int upper) {
                this.size = size;
                this.lower = lower;
                this.upper = upper;
            }
        }

        int max = 0;

        public int largestBSTSubtree(TreeNode root) {
            if (root == null) { return 0; }
            traverse(root, null);
            return max;
        }

        private Result traverse(TreeNode root, TreeNode parent) {
            if (root == null) {
                return new Result(0, parent.val, parent.val);
            }

            Result left = traverse(root.left, root);
            Result right = traverse(root.right, root);

            if (left.size==-1 || right.size==-1 || root.val<left.upper || root.val>right.lower) {
                return new Result(-1, 0, 0);
            }
            int size = left.size + 1 + right.size;
            max = Math.max(size, max);
            return new Result(size, left.lower, right.upper);
        }
    }

    public static void main(String[] args) {
        LargestBSTSubtree solution = new LargestBSTSubtree();

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(8);
        root.right.right = new TreeNode(7);

        System.out.println(solution.largestBSTSubtree(root));
    }

}
