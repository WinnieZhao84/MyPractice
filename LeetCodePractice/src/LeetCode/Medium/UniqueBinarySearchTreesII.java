package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

import LeetCode.Helper.TreeNode;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * 
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * 
 * 1         3     3      2      1
 *  \       /     /      / \      \
 *   3     2     1      1   3      2
 *  /     /       \                 \
 * 2     1         2                 3
 *
 * @author WinnieZhao
 *
 */
public class UniqueBinarySearchTreesII {

    public List<TreeNode> generateTrees(int n) {
        if (n== 0) {
            return new ArrayList<>();
        }
        return this.generateTrees(1, n);
    }
    
    private List<TreeNode> generateTrees(int start, int end) {
        List<TreeNode> result = new ArrayList<TreeNode>();
        if (start > end) {
            result.add(null);
            return result;
        }
        
        if (start == end){
            result.add(new TreeNode(start));
            return result;
        }
        
        for (int i=start; i<=end; i++) {
            
            List<TreeNode> lefts = this.generateTrees(start, i-1);
            List<TreeNode> rights = this.generateTrees(i+1, end);
            
            for (TreeNode left : lefts) {
                for (TreeNode right : rights) {
                    TreeNode root = new TreeNode(i);
                    
                    root.left = left;
                    root.right = right;
                    
                    result.add(root);
                }
            }
        }
        
        return result;
    }
    
    public static void main(String[] args) {
        UniqueBinarySearchTreesII solution = new UniqueBinarySearchTreesII();
        
        List<TreeNode> result = solution.generateTrees(3);
        System.out.println(result.size());
    }
}
