package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

/**
 *
 * Given a binary tree, write a function to get the maximum width of the given tree.
 * The width of a tree is the maximum width among all levels. The binary tree has the
 * same structure as a full binary tree, but some nodes are null.
 *
 * The width of one level is defined as the length between the end-nodes
 * (the leftmost and right most non-null nodes in the level, where the null nodes
 * between the end-nodes are also counted into the length calculation.
 *
 * Example 1:
 * Input:
 *     1
 *   /   \
 *  3     2
 * / \     \
 *5   3     9
 *
 * Output: 4
 * Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
 *
 * Example 2:
 * Input:
 *    1
 *   /
 *  3
 * / \
 *5   3
 *
 * Output: 2
 * Explanation: The maximum width existing in the third level with the length 2 (5,3).
 *
 * Example 3:
 * Input:
 *     1
 *    / \
 *   3   2
 *  /
 * 5
 *
 * Output: 2
 * Explanation: The maximum width existing in the second level with the length 2 (3,2).
 *
 * Example 4:
 * Input:
 *         1
 *        / \
 *       3   2
 *      /     \
 *     5       9
 *    /         \
 *   6           7
 *
 * Output: 8
 * Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).

 * Created by WinnieZhao on 8/21/2017.
 */
public class MaximumWidthOfBinaryTree {

    public int widthOfBinaryTree(TreeNode root) {

        if (root == null) {
            return 0;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        Queue<Integer> count = new LinkedList<>();
        count.offer(0);

        int max = 1;
        while (!queue.isEmpty()) {
            int left = 0;
            int right = 0;
            int size = queue.size();
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                int index = count.poll();
                if(i == 0) {
                    left = index;
                }
                if(i == size-1)  {
                    right = index;
                }

                if (node.left != null) {
                    queue.add(node.left);
                    count.add(index*2);
                }
                if (node.right != null) {
                    queue.add(node.right);
                    count.offer(index*2 + 1);
                }
            }
            max = Math.max(right-left+1, max);
        }

        return max;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        MaximumWidthOfBinaryTree solution = new MaximumWidthOfBinaryTree();
        System.out.println(solution.widthOfBinaryTree(root));
    }
}
