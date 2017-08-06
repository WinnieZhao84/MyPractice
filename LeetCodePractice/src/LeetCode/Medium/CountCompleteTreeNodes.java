package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

/**
 * Given a complete binary tree, count the number of nodes.
 * 
 * Definition of a complete binary tree from Wikipedia:
 * In a complete binary tree every level, except possibly the last, is completely filled, and all nodes in the last level
 * are as far left as possible.
 * It can have between 1 and 2h nodes inclusive at the last level h.

 * @author WinnieZhao
 *
 */
public class CountCompleteTreeNodes {

    /** Time Limit Exceeded */
    public int countNodes(TreeNode root) {
        if (root == null) return 0;
        
        return 1 + this.countNodes(root.left) + this.countNodes(root.right);
    }

    int height(TreeNode root) {
        return root == null ? -1 : 1 + height(root.left);
    }

    public int countNodes_lognSulution(TreeNode root) {
        int nodes = 0;
        int h = height(root);
        while (root != null) {
            if (height(root.right) == h - 1) {
                nodes += 1 << h;
                root = root.right;
            }
            else {
                nodes += 1 << h-1;
                root = root.left;
            }
            h--;
        }
        return nodes;
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(7);
        
        root.right = new TreeNode(8);

        CountCompleteTreeNodes solution = new CountCompleteTreeNodes();
        System.out.println(solution.countNodes_lognSulution(root));
    }
}
