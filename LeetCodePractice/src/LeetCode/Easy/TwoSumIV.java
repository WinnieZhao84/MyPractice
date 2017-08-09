package LeetCode.Easy;

import LeetCode.Helper.TreeNode;

import java.util.*;


/**
 * Two Sum IV - Input is a BST
 *
 * Given a Binary Search Tree and a target number, return true if there exist two elements in the BST
 * such that their sum is equal to the given target.
 *
 *  Example 1:
 *  Input:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 9
 * Output: True
 *
 * Example 2:
 * Input:
 *     5
 *    / \
 *   3   6
 *  / \   \
 * 2   4   7
 *
 * Target = 28
 * Output: False
 *
 * Created by WinnieZhao on 8/9/2017.
 */
public class TwoSumIV {

    public boolean findTarget(TreeNode root, int k) {
        if (root == null) {
            return false;
        }
        List<Integer> lists = new ArrayList<>();
        this.inorder(root, lists);

        int l = 0;
        int r = lists.size()-1;
        while (l < r) {
            int sum = lists.get(l) + lists.get(r);
            if (sum < k) {
                l++;
            }
            else if (sum > k) {
                r--;
            }
            else {
                return true;
            }
        }
        return false;
    }

    private void inorder(TreeNode root, List<Integer> lists) {
        if (root == null) {
            return;
        }
        inorder(root.left, lists);
        lists.add(root.val);
        inorder(root.right, lists);
    }

    public static void main(String[] args) {
        TwoSumIV solution = new TwoSumIV();
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(3);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(2);
        root.left.right = new TreeNode(4);
        root.right.right = new TreeNode(7);

        System.out.println(solution.findTarget(root, 9));
    }
}
