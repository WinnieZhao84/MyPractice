package LeetCode.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import LeetCode.Helper.TreeNode;

/**
 * You need to find the largest value in each row of a binary tree.
 * 
 * Example:
 * Input: 
 *           1
 *          / \
 *         3   2
 *        / \   \  
 *       5   3   9 
 *       
 * Output: [1, 3, 9]

 * @author WinnieZhao
 *
 */
public class FindLargestValueInEachTreeRow {

    public List<Integer> largestValues(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        
        while (!queue.isEmpty()) {
            int size = queue.size();
            
            int max = Integer.MIN_VALUE;
            for (int i=0; i<size; i++) {
                TreeNode node = queue.poll();
                
                if (node != null) {
                    if (node.val >= max) {
                        max = node.val;
                    }
                    
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }
            if (!queue.isEmpty()) {
                result.add(max);
            }

        }
        
        return result;
    }
    
    public static void main(String[] args) {
        FindLargestValueInEachTreeRow solution = new FindLargestValueInEachTreeRow();
        
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.right = new TreeNode(2);
        
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(3);
        
        root.right.left = null;
        root.right.right = new TreeNode(9);
        
        System.out.println(solution.largestValues(root));
    }
}
