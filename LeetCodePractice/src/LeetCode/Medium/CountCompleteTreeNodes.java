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

    private int rightHeight(TreeNode root) {
        return root == null ? 0 : 1 + rightHeight(root.right);
    }

    private int leftHeight(TreeNode root) {
        return root == null ? 0 : 1 + leftHeight(root.left);
    }

    /**
     * O(log(n)^2) solution
     * @param root
     * @return
     */
    public int countNodes_better(TreeNode root) {

        int leftHeight = this.leftHeight(root);
        int rightHeight = this.rightHeight(root);

        if (leftHeight == rightHeight) {
            return (1 << leftHeight) - 1;
        }
        else {
            return 1 + countNodes_better(root.left) + countNodes_better(root.right);
        }
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(7);
        
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(4);

        CountCompleteTreeNodes solution = new CountCompleteTreeNodes();
        System.out.println(solution.countNodes_better(root));
    }
}
