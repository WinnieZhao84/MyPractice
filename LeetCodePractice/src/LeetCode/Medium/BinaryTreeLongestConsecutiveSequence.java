package LeetCode.Medium;

import LeetCode.Helper.TreeNode;
import sun.reflect.generics.tree.Tree;

/**
 * 298
 *
 * Given a binary tree, find the length of the longest consecutive sequence path.
 *
 * The path refers to any sequence of nodes from some starting node to any node in
 * the tree along the parent-child connections. The longest consecutive path need to
 * be from parent to child (cannot be the reverse).
 *
 * For example,
 *
 *        1
 *         \
 *          3
 *         / \
 *        2   4
 *             \
 *              5
 * Longest consecutive sequence path is 3-4-5, so return 3.
 *
 *       2
 *        \
 *         3
 *        /
 *       2
 *      /
 *     1
 * Longest consecutive sequence path is 2-3,not3-2-1, so return 2.

 * Created by WinnieZhao on 2017/3/27.
 */
public class BinaryTreeLongestConsecutiveSequence {

    public int longestConsecutive(TreeNode root) {
        if(root == null){
            return 0;
        }
        return this.longestConsecutiveHelper(root, root.val-1, 0);
    }

    private int longestConsecutiveHelper(TreeNode node, int parentValue, int length) {
        if (node == null) {
            return length;
        }

        int currLen = node.val == parentValue + 1 ? length+1 : length;
        int left = this.longestConsecutiveHelper(node.left, node.val, currLen);
        int right = this.longestConsecutiveHelper(node.right, node.val, currLen);

        return Math.max(currLen, Math.max(left, right));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(2);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(5);

        BinaryTreeLongestConsecutiveSequence solution = new BinaryTreeLongestConsecutiveSequence();

        System.out.print(solution.longestConsecutive(root));
    }

}
