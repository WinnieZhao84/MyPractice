package LeetCode.Medium;

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
 * Created by WinnieZhao on 2017/3/28.
 */
public class BinaryTreeVerticalOrderTraversal {

    /**
     * Root index is x;
     * left index is x-1;
     * right index is x+1;
     * Loop through the tree if the index is the same
     * put the nodes into the same set
     *
     * @param root
     * @return
     */
    public List<List<Integer>> verticalOrder(TreeNode root) {

        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }

        Queue<Integer> columns = new LinkedList<>();
        Queue<TreeNode> nodes = new LinkedList<>();
        Map<Integer, List<Integer>> dict = new TreeMap<>();

        nodes.add(root);
        columns.add(0);

        while (!nodes.isEmpty()) {
            TreeNode node = nodes.poll();
            Integer column = columns.poll();

            if(!dict.containsKey(column)){
                dict.put(column, new ArrayList<>());
            }
            dict.get(column).add(node.val);

            if (node.left != null) {
                nodes.add(node.left);
                columns.add(column-1);
            }
            if (node.right != null) {
                nodes.add(node.right);
                columns.add(column+1);
            }

        }

        res.addAll(dict.values());
        return res;
    }

}
