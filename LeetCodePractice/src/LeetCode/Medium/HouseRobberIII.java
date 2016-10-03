package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

/**
 * The thief has found himself a new place for his thievery again. There is only one entrance to this area, called the "root." 
 * Besides the root, each house has one and only one parent house. After a tour, the smart thief realized that "all houses in this place forms a binary tree". 
 * It will automatically contact the police if two directly-linked houses were broken into on the same night.
 * 
 * Determine the maximum amount of money the thief can rob tonight without alerting the police.
 * 
    Example 1:
         3
        / \
       2   3
        \   \ 
         3   1
    Maximum amount of money the thief can rob = 3 + 3 + 1 = 7.
    
    Example 2:
         3                  (i)
        / \ 
       4   5                (i-1)
      / \   \ 
     1   3   1              (i-2)
    Maximum amount of money the thief can rob = 4 + 5 = 9.
 * 
 * @author WinnieZhao
 *
 */
public class HouseRobberIII {
    
    public int rob(TreeNode root) {

       if (root == null) return 0;
 
       int[] result = this.dfsSearch(root);

        return result[1];
    }
    
    // DP Solution: max[i] = Math.max(max[i-2]+val(i), max[i-1]) 
    private int[] dfsSearch(TreeNode node) {
        int[] result = {0,0};
        
        if (node != null) {
            int resultLeft[] = dfsSearch(node.left);
            int resultRight[] = dfsSearch(node.right);

            result[0] = resultLeft[1] + resultRight[1];
            result[1] = Math.max(resultLeft[0]+resultRight[0]+node.val, result[0]);
        }
        
        return result;
    }
    
    public int rob_dfs(TreeNode root) {
        if (root == null) return 0;
        
        if (root.left == null && root.right == null) {
            return root.val;
        }
        // Case 1: rob root
        int left = rob_dfs(root.left.left) + rob_dfs(root.left.right);
        int right = rob_dfs(root.right.left) + rob_dfs(root.right.right);
        int maxRoot = left + right + root.val;
        
        // case 2: not rob root
        left = rob_dfs(root.left);
        right = rob_dfs(root.right);
        int maxNoRoot = left + right;
                
        return Math.max(maxRoot, maxNoRoot);
    }
    
    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        
        root.left = left;
        root.right = right;
        
        HouseRobberIII solution = new HouseRobberIII();
        System.out.println(solution.rob_dfs(root));
    }
}
