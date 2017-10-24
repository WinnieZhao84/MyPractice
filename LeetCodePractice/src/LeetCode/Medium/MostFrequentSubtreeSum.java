package LeetCode.Medium;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import LeetCode.Helper.TreeNode;

/**
 * Given the root of a tree, you are asked to find the most frequent subtree sum. The subtree sum of a node is defined 
 * as the sum of all the node values formed by the subtree rooted at that node (including the node itself). 
 * So what is the most frequent subtree sum value? If there is a tie, return all the values with the highest frequency in any order.
 * 
 * Examples 1
 * Input:
 * 
 *   5
 *  /  \
 * 2   -3
 * 
 * return [2, -3, 4], since all the values happen only once, return all of them in any order.
 * 
 * Examples 2
 * Input:
 *   5
 *  /  \
 * 2   -5
 *
 * return [2], since 2 happens twice, however -5 only occur once.
 * Note: You may assume the sum of values in any subtree is in the range of 32-bit signed integer.

 * @author WinnieZhao
 *
 */
public class MostFrequentSubtreeSum {

    Map<Integer, Integer> sumMap = new HashMap<>();

    public int[] findFrequentTreeSum(TreeNode root) {

        if (root == null) {
            return new int[0];
        }

        this.getSum(root);

        int max = 0;
        for (Map.Entry<Integer, Integer> item : sumMap.entrySet()) {
            max = Math.max(max, item.getValue());
        }
        int check = max;
        int[] result = sumMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(check))
                .mapToInt(entry -> entry.getKey()).toArray();

        return result;
    }

    private int getSum(TreeNode node) {

        if (node == null) {
            return 0;
        }

        int sum = node.val + this.getSum(node.left) + this.getSum(node.right);

        sumMap.put(sum, sumMap.getOrDefault(sum, 0) + 1);

        return sum;
    }
    
    public static void main(String[] args) {
        MostFrequentSubtreeSum solution = new MostFrequentSubtreeSum();
        
        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(2);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(-1);
        root.right = new TreeNode(-5);
        root.right.left = new TreeNode(1);
        root.right.right = new TreeNode(-1);
        
        System.out.println(Arrays.toString(solution.findFrequentTreeSum(root)));
    }
}
