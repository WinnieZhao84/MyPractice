package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Given a Binary Search Tree (BST), convert it to a Greater Tree such that every key of the original BST is changed to
 * the original key plus sum of all keys greater than the original key in BST.
 * 
 * Example:
 * 
 * Input: The root of a Binary Search Tree like this:
 *            5
 *          /   \
 *         2     13
 * Output: The root of a Greater Tree like this:
 *           18
 *          /   \
 *        20     13
 *
 * @author WinnieZhao
 *
 */
public class ConvertBSTToGreaterTree {

    int sum = 0;
    public TreeNode convertBST_recursive(TreeNode root) {

        if (root == null) {
            return null;
        }

        helper(root);

        return root;
    }

    private void helper(TreeNode node) {
        if (node == null) {
            return;
        }

        helper(node.right);

        sum += node.val;
        node.val = sum;

        helper(node.left);
    }

    List<Integer> treeList = new ArrayList<>();
    public TreeNode convertBST(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) {
            return root;
        }

        this.inorderTraverse(root);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();

            if (node != null) {
                node.val = this.findGreaterSum(node.val);
                queue.add(node.left);
                queue.add(node.right);
            }
        }

        return root;
    }

    private int findGreaterSum(int val) {
        int sum = 0;
        for (int i=treeList.size()-1; i>=0; i--) {
            if (treeList.get(i) < val) {
                break;
            }
            sum += treeList.get(i);
        }
        return sum;
    }

    private void inorderTraverse(TreeNode node) {
        if (node.left != null) {
            this.inorderTraverse(node.left);
        }

        treeList.add(node.val);

        if (node.right != null) {
            this.inorderTraverse(node.right);
        }
    }
}
