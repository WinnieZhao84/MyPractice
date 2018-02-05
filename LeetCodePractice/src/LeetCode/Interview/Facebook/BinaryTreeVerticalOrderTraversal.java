package LeetCode.Interview.Facebook;

import LeetCode.Helper.TreeNode;

import java.util.*;

/**
 * 314
 *
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
 *
 * Examples: Given binary tree [3,9,20,null,null,15,7],
 *       3
 *      /\
 *     /  \
 *    9  20
 *       /\
 *      /  \
 *     15   7
 * return its vertical order traversal as:
 * [ [9], [3,15], [20], [7] ]
 *
 * Given binary tree [3,9,8,4,0,1,7],
 *       3
 *      /\
 *     /  \
 *    9   8
 *   /\  /\
 *  /  \/  \
 *  4  01   7
 *  return its vertical order traversal as:
 *  [ [4], [9], [3,0,1], [8], [7] ]
 *
 *  Given binary tree [3,9,8,4,0,1,7,null,null,null,2,5] (0's right child is 2 and 1's left child is 5),
 *        3
 *       /\
 *      /  \
 *     9   8
 *    /\  /\
 *   /  \/  \
 *  4  0 1   7
 *     /\
 *    /  \
 *    5   2
 * return its vertical order traversal as:
 * [ [4], [9,5], [3,0,1], [8,2], [7] ]
 *
 * Created by WinnieZhao on 2/3/2018.
 */
public class BinaryTreeVerticalOrderTraversal {

    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root != null) {
            return res;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        Queue<Integer> columns = new LinkedList<>();

        Map<Integer, List<Integer>> map = new HashMap<>();

        queue.add(root);
        columns.add(0);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            Integer index = columns.poll();

            if (!map.containsKey(index)) {
                map.put(index, new ArrayList<>());
            }
            map.get(index).add(node.val);

            if (node.left != null) {
                queue.offer(node.left);
                columns.offer(index-1);
            }
            if (node.right != null) {
                queue.offer(node.right);
                columns.offer(index+1);
            }
        }

        res.addAll(map.values());
        return res;
    }
}
