package LeetCode.Easy;

import LeetCode.Helper.TreeNode;

/**
 * You need to construct a string consists of parenthesis and integers from a binary tree with the preorder traversing way.
 *
 * The null node needs to be represented by empty parenthesis pair "()". And you need to omit all the empty parenthesis
 * pairs that don't affect the one-to-one mapping relationship between the string and the original binary tree.
 *
 * Example 1:
 * Input: Binary tree: [1,2,3,4]
 *      1
 *    /   \
 *   2     3
 *  /
 * 4
 *
 * Output: "1(2(4))(3)"
 * Explanation: Originally it needs to be "1(2(4)())(3()())",
 * but you need to omit all the unnecessary empty parenthesis pairs. And it will be "1(2(4))(3)".
 *
 * Example 2:
 * Input: Binary tree: [1,2,3,null,4]
 *     1
 *   /   \
 *  2     3
 *  \
 *   4
 *
 * Output: "1(2()(4))(3)"
 * Explanation: Almost the same as the first example, except we can't omit the first parenthesis pair to break the
 * one-to-one mapping relationship between the input and the output.

 * Created by WinnieZhao on 6/5/2017.
 */
public class ConstructStringFromBinaryTree {
    public String tree2str(TreeNode t) {
        if (t == null) return "";

        String result = this.helper(t);

        return result;
    }

    private String helper(TreeNode root) {
        if (root == null) {
            return "";
        }
        if (root.left == null && root.right == null) {
            return String.valueOf(root.val);
        }
        else if (root.right == null) {
            return String.valueOf(root.val) + "(" + this.helper(root.left) + ")";
        }

        return String.valueOf(root.val) +
                "(" + this.helper(root.left) + ")"
                + "(" + this.helper(root.right) + ")";

    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.right = new TreeNode(4);

        ConstructStringFromBinaryTree solution = new ConstructStringFromBinaryTree();
        System.out.println(solution.tree2str(root));
    }
}
