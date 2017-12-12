package LeetCode.Medium;

import LeetCode.Helper.TreeNode;

import java.util.HashMap;
import java.util.Map;

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
        if (node == null) {
            return result;
        }

        int resultLeft[] = dfsSearch(node.left);
        int resultRight[] = dfsSearch(node.right);

        result[0] = resultLeft[1] + resultRight[1]; // Money avoiding root itself
        result[1] = Math.max(resultLeft[0] + resultRight[0] + node.val, result[0]); // Money allowing root to be stolen
        
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

    public int rob_step_I(TreeNode root) {
        if (root == null) return 0;

        int val = 0;
        if (root.left != null) {
            val += rob(root.left.left) + rob(root.left.right);
        }
        if (root.right != null) {
            val += rob(root.right.left) + rob(root.right.right);
        }

        return Math.max(val + root.val, rob(root.left) + rob(root.right));
    }

    /**
     * In step I, we only considered the aspect of "optimal substructure", but think little about the possibilities of
     * overlapping of the sub problems.
     * For example, to obtain rob(root), we need
     * rob(root.left), rob(root.right), rob(root.left.left), rob(root.left.right), rob(root.right.left), rob(root.right.right);
     * but to get rob(root.left), we also need rob(root.left.left), rob(root.left.right), similarly for rob(root.right).
     * The naive solution above computed these sub problems repeatedly, which resulted in bad time performance.
     * Now if you recall the two conditions for dynamic programming: "optimal substructure" + "overlapping of sub problems",
     * we actually have a DP problem. A naive way to implement DP here is to use a hash map to record the results for visited subtrees.
     *
     * @param root
     * @return
     */
    public int rob_step_II(TreeNode root) {
        return robSub(root, new HashMap<>());
    }

    private int robSub(TreeNode root, Map<TreeNode, Integer> map) {
        if (root == null) return 0;
        if (map.containsKey(root)) return map.get(root);

        int val = 0;

        if (root.left != null) {
            val += robSub(root.left.left, map) + robSub(root.left.right, map);
        }

        if (root.right != null) {
            val += robSub(root.right.left, map) + robSub(root.right.right, map);
        }

        val = Math.max(val + root.val, robSub(root.left, map) + robSub(root.right, map));
        map.put(root, val);

        return val;
    }

    /**
     * In step I, we defined our problem as rob(root), which will yield the maximum amount of money that can be robbed
     * of the binary tree rooted at root. This leads to the DP problem summarized in step II.
     *
     * If we were able to maintain the information about the two scenarios for each tree root, let's see how it plays out.
     * Redefine rob(root) as a new function which will return an array of two elements, the first element of which denotes
     * the maximum amount of money that can be robbed if root is not robbed, while the second element signifies the maximum
     * amount of money robbed if it is robbed.
     *
     * @param root
     * @return
     */
    public int rob_step_III(TreeNode root) {
        int[] res = robSub(root);
        return Math.max(res[0], res[1]);
    }

    private int[] robSub(TreeNode root) {
        if (root == null) return new int[2];

        int[] left = robSub(root.left);
        int[] right = robSub(root.right);
        int[] res = new int[2];

        res[0] = Math.max(left[0], left[1]) + Math.max(right[0], right[1]);
        res[1] = root.val + left[0] + right[0];

        return res;
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);
        TreeNode left = new TreeNode(2);
        TreeNode right = new TreeNode(3);
        
        root.left = left;
        root.right = right;
        
        HouseRobberIII solution = new HouseRobberIII();
        System.out.println(solution.rob(root));
    }
}
