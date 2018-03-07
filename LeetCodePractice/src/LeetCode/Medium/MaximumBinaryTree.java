package LeetCode.Medium;

import LeetCode.Helper.TreeNode;


/**
 * Given an integer array with no duplicates. A maximum tree building on this array is defined as follow:
 *
 * The root is the maximum number in the array.
 * The left subtree is the maximum tree constructed from left part subarray divided by the maximum number.
 * The right subtree is the maximum tree constructed from right part subarray divided by the maximum number.
 * Construct the maximum tree by the given array and output the root node of this tree.
 *
 * Example 1:
 * Input: [3,2,1,6,0,5]
 * Output: return the tree root node representing the following tree:
 *    6
 *  /   \
 * 3     5
 * \    /
 *  2  0
 *   \
 *    1
 *
 *  Note: The size of the given array will be in the range [1,1000].

 * Created by WinnieZhao on 8/9/2017.
 */
public class MaximumBinaryTree {

    public TreeNode constructMaximumBinaryTree(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }

        TreeNode root = this.build(nums, 0, nums.length-1);

        return root;
    }

    private TreeNode build(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        }

        int[] rootResult = this.findMax(nums, start, end);
        int index = rootResult[0];
        int value = rootResult[1];

        TreeNode root = new TreeNode(value);

        root.left = this.build(nums, start, index-1);
        root.right = this.build(nums, index+1, end);
        return root;
    }

    private int[] findMax(int[] nums, int start, int end) {
        int max = Integer.MIN_VALUE;
        int maxIndex = -1;
        int[] result = new int[2];

        for (int i=start; i<=end; i++) {
            if (max < nums[i]) {
                maxIndex = i;
                max = nums[i];
            }
        }
        result[0] = maxIndex;
        result[1] = max;
        return result;
    }

    class Solution {
        public TreeNode constructMaximumBinaryTree(int[] nums) {
            if (nums == null || nums.length == 0 ) {
                return null;
            }

            if (nums.length == 1) {
                return new TreeNode(nums[0]);
            }

            return buildTree(nums, 0, nums.length-1);
        }

        private TreeNode buildTree(int[] nums, int start, int end) {

            if (start > end) {
                return null;
            }

            int rootIndex = -1;
            int max = Integer.MIN_VALUE;

            for (int i=start; i<=end; i++) {

                if (nums[i] >= max) {
                    max = nums[i];
                    rootIndex = i;
                }
            }

            TreeNode root = new TreeNode(max);

            root.left = buildTree(nums, start, rootIndex-1);
            root.right = buildTree(nums, rootIndex+1, end);

            return root;
        }
    }
}
