package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

/**
 * 250
 *
 * Given a binary tree, count the number of uni-value subtrees. A Uni-value subtree means all nodes of the subtree have the same value.
 * For example: Given binary tree,
 *     5
 *    / \
 *   1   5
 *  / \   \
 * 5   5   5
 *
 * return 4.

 * Created by WinnieZhao on 2017/3/28.
 */
public class CountUnivalueSubtrees {

    private int count;

    /**
     * Subtree: [5, 5, 5, 5->5]
     * @param root
     * @return
     */
    public int countUnivalSubtrees(TreeNode root) {
        if (root == null) {
            return 0;
        }
        count = 0;
        this.countUnivalSubtreesHelper(root);
        return count;
    }

    private boolean countUnivalSubtreesHelper(TreeNode node) {
        if (node == null) {
            return true;
        }
        boolean left = this.countUnivalSubtreesHelper(node.left);
        boolean right = this.countUnivalSubtreesHelper(node.right);

        if (left && right) {
            if ((node.left != null && node.val != node.left.val) || (node.right != null && node.val != node.right.val)) {
                return false;
            }
            count++;
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        CountUnivalueSubtrees solution = new CountUnivalueSubtrees();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(1);
        root.right = new TreeNode(5);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(5);

        System.out.print(solution.countUnivalSubtrees(root));
    }
}
