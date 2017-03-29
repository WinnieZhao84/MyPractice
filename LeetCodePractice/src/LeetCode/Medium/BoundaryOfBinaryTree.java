package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 545
 *
 * Given a binary tree, return the values of its boundary in anti-clockwise direction starting from root.
 * Boundary includes left boundary, leaves, and right boundary in order without duplicate nodes.
 *
 * Left boundary is defined as the path from root to the left-most node.
 * Right boundary is defined as the path from root to the right-most node.
 * If the root doesn't have left subtree or right subtree, then the root itself is left boundary or right boundary.
 *
 * Note this definition only applies to the input binary tree, and not applies to any subtrees.
 *
 * The left-most node is defined as a leaf node you could reach when you always firstly travel to the left subtree if exists.
 * If not, travel to the right subtree. Repeat until you reach a leaf node.
 * The right-most node is also defined by the same way with left and right exchanged.
 *
 * Example 1
 * Input:
 *     1
 *      \
 *       2
 *      / \
 *     3   4
 *
 * Ouput: [1, 3, 4, 2]
 *
 * Explanation:
 * The root doesn't have left subtree, so the root itself is left boundary.
 * The leaves are node 3 and 4.
 * The right boundary are node 1,2,4. Note the anti-clockwise direction means you should output reversed right boundary.
 * So order them in anti-clockwise without duplicates and we have [1,3,4,2].
 *
 * Example 2
 * Input:
 *      ____1_____
 *     /          \
 *    2            3
 *   / \          /
 *  4   5        6
 *     / \      / \
 *    7   8    9  10
 *
 * Ouput: [1,2,4,7,8,9,10,6,3]
 *
 * Explanation:
 * The left boundary are node 1,2,4. (4 is the left-most node according to definition)
 * The leaves are node 4,7,8,9,10.
 * The right boundary are node 1,3,6,10. (10 is the right-most node).
 * So order them in anti-clockwise without duplicate nodes we have [1,2,4,7,8,9,10,6,3].
 *
 * Created by WinnieZhao on 2017/3/28.
 */
public class BoundaryOfBinaryTree {

    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> list=new ArrayList<>();
        if (root==null) {
            return list;
        }
        list.add(root.val);

        getLeftPath(root.left, list);//add left boundary node and leaves node
        getRightPath(root.right, list);// add right boundary node and leaves node

        return list;
    }

    private void getLeftPath(TreeNode left, List<Integer> list) {
        if (left != null) {
            list.add(left.val);

            if (left.left != null) {
                this.getLeftPath(left.left, list);
                this.dfs(left.right, list);
            }
            else {// according to the rule, if the node has no left subtree,then the left path goes to right
                this.getLeftPath(left.right, list);
            }
        }
    }

    private void getRightPath(TreeNode right, List<Integer> list) {
        if (right != null) {

            if (right.right != null) {
                this.dfs(right.left, list);
                this.getRightPath(right.right, list);
            }
            else{ //according to the rule,if the node has no right subtree,then the right path goes to left
                getRightPath(right.left, list);
            }
            
            list.add(right.val);
        }
    }

    // Add leaves to list
    private void dfs(TreeNode node, List<Integer>list){
        if(node!=null) {
            if(node.left==null && node.right==null){
                list.add(node.val);
            }
            else{
                this.dfs(node.left, list);
                this.dfs(node.right,list);
            }
        }
    }

    public static void main(String[] args) {
        BoundaryOfBinaryTree solution = new BoundaryOfBinaryTree();
        TreeNode root = new TreeNode(1);
        root.right = new TreeNode(2);
        root.right.left = new TreeNode(3);
        root.right.right = new TreeNode(4);

        System.out.println(solution.boundaryOfBinaryTree(root).toString());


    }
}
