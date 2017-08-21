package LeetCode.Hard;

import LeetCode.Helper.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 *
 * For example:
 * Given binary tree {1,#,2,3},
 * 1
 *  \
 *   2
 *  /
 * 3
 *
 * return [3,2,1].
 *
 * Note: Recursive solution is trivial, could you do it iteratively?

 * Created by WinnieZhao on 4/24/2017.
 */
public class BinaryTreePostorderTraversal {

    public List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();

        if (root == null) {
            return result;
        }

        this.recursion(root, result);

        return result;

    }

    private void recursion(TreeNode node, List<Integer> result) {
        if (node != null) {
            result.add(0, node.val);
        }

        if (node.right != null) {
            recursion(node.right, result);
        }

        if (node.left != null) {
            recursion(node.left, result);
        }

    }

    public List<Integer> postorderTraversal_iterate(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(root == null) return res;
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()){
            TreeNode node = stack.pop();

            res.add(node.val);
            if(node.left != null) stack.push(node.left);
            if(node.right != null) stack.push(node.right);

        }

        Collections.reverse(res);

        return res;

    }

    public static void main(String[] args) {
        BinaryTreePostorderTraversal solution = new BinaryTreePostorderTraversal();

        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);

        List<Integer> result = solution.postorderTraversal(root);
        System.out.println(result.toString());
    }

}
