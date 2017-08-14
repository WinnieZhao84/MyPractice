package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

import LeetCode.Easy.PathSumIII;
import LeetCode.Helper.TreeNode;

/**
 * Given a binary tree and a sum, find all root-to-leaf paths where each path's sum equals the given sum.
    
    For example:
    Given the below binary tree and sum = 22,
                  5
                 / \
                4   8
               /   / \
              11  13  4
             /  \    / \
            7    2  5   1
    return
    [
       [5,4,11,2],
       [5,8,4,5]
    ]

 * @author WinnieZhao
 *
 */
public class PathSumII {

    public List<List<Integer>> pathSum(TreeNode root, int sum) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        
        if (root == null) {
            return result;
        }
        
        this.pathSumHelper(root, sum, new ArrayList<>(), result);
        
        return result;
    }
    
    private void pathSumHelper(TreeNode root, int sum, List<Integer> list, List<List<Integer>> result) {
        if (root == null) {
            return;
        }
        
        if (sum == root.val && root.left == null && root.right == null) {
            list.add(root.val);
            result.add(new ArrayList<>(list));
            return;
        }
        
        list.add(root.val);
        
        if (root.left != null) {
            this.pathSumHelper(root.left, sum - root.val, list, result);
            list.remove(list.size() - 1);
        }
        
        if (root.right != null) {
            this.pathSumHelper(root.right, sum - root.val, list, result);
            list.remove(list.size() - 1);
        }
    }
    
    public static void main(String[] args) {
        PathSumII solution = new PathSumII();
        
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(9);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(7);
        
        System.out.println(solution.pathSum(root, 12));
    }
}
