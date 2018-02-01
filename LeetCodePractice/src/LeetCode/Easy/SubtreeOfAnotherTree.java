package LeetCode.Easy;

import LeetCode.Helper.TreeNode;

/**
 * Given two non-empty binary trees s and t, check whether tree t has exactly the same structure and node values with a subtree of s.
 * A subtree of s is a tree consists of a node in s and all of this node's descendants. The tree s could also be considered as a subtree of itself.
 *
 * Example 1:
 * Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *
 *  Given tree t:
 *    4
 *   / \
 *  1   2
 *
 *  Return true, because t has the same structure and node values with a subtree of s
 *
 *  Example 2:
 *  Given tree s:
 *
 *      3
 *     / \
 *    4   5
 *   / \
 *  1   2
 *     /
 *    0
 *
 * Given tree t:
 *    4
 *   / \
 *  1   2
 *
 *  Return false.

 * Created by WinnieZhao on 2017/5/8.
 */
public class SubtreeOfAnotherTree {

    public boolean isSubtree(TreeNode s, TreeNode t) {
        String s1 = this.inorder(s);
        String s2 = this.inorder(t);

        return s1.contains(s2);
    }

    private String inorder(TreeNode root) {
        if (root == null) {
            return ",#";
        }
        String s = ","+root.val;
        s += this.inorder(root.left);
        s += this.inorder(root.right);

        return s;
    }

    /**
     * Better solution
     * Time complexity : O(m*n). In worst case(skewed tree) traverse function takes O(m*n) time.
     * Space complexity : O(n). The depth of the recursion tree can go up to n. n refers to the number of nodes in s.

     */
    class Solution {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            return (s != null) && (this.isEqual(s, t) || this.isSubtree(s.left, t) || this.isSubtree(s.right, t));
        }

        private boolean isEqual(TreeNode s, TreeNode t) {
            if (s == null || t == null) {
                return s == t;
            }

            return (s.val == t.val) && this.isEqual(s.left, t.left) && this.isEqual(s.right, t.right);
        }
    }

    public static void main(String[] args) {
        TreeNode s = new TreeNode(12);
        TreeNode t = new TreeNode(2);

        SubtreeOfAnotherTree solution = new SubtreeOfAnotherTree();

        System.out.println(solution.inorder(s));
        System.out.println(solution.inorder(t));
        System.out.println(solution.isSubtree(s, t));
    }
}
