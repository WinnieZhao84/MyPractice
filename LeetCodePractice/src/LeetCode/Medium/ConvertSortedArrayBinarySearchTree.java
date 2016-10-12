package LeetCode.Medium;

import java.util.Arrays;

import LeetCode.Helper.TreeNode;

/**
 * Given an array where elements are sorted in ascending order, convert it to a height balanced BST.
 * @author WinnieZhao
 *
 */
public class ConvertSortedArrayBinarySearchTree {

    public TreeNode sortedArrayToBST(int[] nums) {
        if (nums == null || nums.length == 0) {
            return null;
        }
        
       
        int length = nums.length;
        int middle = length / 2;
        
        int rootNum = nums[middle];
        
        TreeNode root = new TreeNode(rootNum);

        this.makeLeftAndRightTree(root, nums, middle);
        
        return root;
    }
    
    
    private void makeLeftAndRightTree(TreeNode root, int[] nums, int middle) {

        int[] leftNums = Arrays.copyOfRange(nums, 0, middle);
        int[] rightNums = Arrays.copyOfRange(nums, middle+1, nums.length);
        
        if (leftNums.length == 1 && rightNums.length == 1) {
            root.left = new TreeNode(leftNums[0]);
            root.right = new TreeNode(rightNums[0]);
            return;
        }
        else {
            int leftLength = leftNums.length;
            int leftMiddle = leftLength / 2;
            
            int rightLength = rightNums.length;
            int rightMiddle = rightLength / 2;
            
            if (leftLength == 0) {
                root.left = null;
            }
            else {
                root.left = new TreeNode(leftNums[leftMiddle]);
            }

            if (rightLength == 0) {
                root.right = null;
            }
            else {
                root.right = new TreeNode(rightNums[rightMiddle]);
            }
            
            if (leftMiddle != 0) {
                this.makeLeftAndRightTree(root.left, leftNums, leftMiddle);
            }
            if (rightMiddle != 0) {
                this.makeLeftAndRightTree(root.right, rightNums, rightMiddle);
            }
        }
    } 
    
    public TreeNode sortedArrayToBST_better(int[] nums) {
        if(nums == null || nums.length == 0) return null;
        return dfs(nums,0,nums.length-1);
    }
    
    private TreeNode dfs(int[] nums,int start,int end){
        if(start > end) return null;
        int mid = (start + end)/2;
        TreeNode root  = new TreeNode(nums[mid]);
        root.left = dfs(nums,start,mid-1);
        root.right = dfs(nums,mid+1,end);
        return root;
    }
    
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 4, 5, 6};
        
        ConvertSortedArrayBinarySearchTree solution = new ConvertSortedArrayBinarySearchTree();
        
        TreeNode root = solution.sortedArrayToBST(nums);
        
        System.out.println("Root is " + root.val);
    }
}
