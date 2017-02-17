package LeetCode.Medium;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i ¡Ü j), inclusive.
 * 
 * The update(i, val) function modifies nums by updating the element at index i to val.
 * Example:
 * Given nums = [1, 3, 5]
 * sumRange(0, 2) -> 9
 * update(1, 2)
 * sumRange(0, 2) -> 8
 * 
 * Note:
 * The array is only modifiable by the update function.
 * You may assume the number of calls to update and sumRange function is distributed evenly.

 * @author WinnieZhao
 *
 */
public class RangeSumQueryMutable {

    /**
     * Your NumArray object will be instantiated and called as such:
     * NumArray obj = new NumArray(nums);
     * obj.update(i,val);
     * int param_2 = obj.sumRange(i,j);
     */
    class SegmentTreeNode {
        int start, end;
        SegmentTreeNode left, right;
        int sum;

        public SegmentTreeNode(int start, int end) {
            this.start = start;
            this.end = end;
            this.left = null;
            this.right = null;
            this.sum = 0;
        }
    }
    
    SegmentTreeNode root = null;
    
    public RangeSumQueryMutable(int[] nums) {
        root = buildTree(nums, 0, nums.length-1);
    }
    
    private SegmentTreeNode buildTree(int[] nums, int start, int end) {
        if (start > end) {
            return null;
        } 
        else {
            SegmentTreeNode ret = new SegmentTreeNode(start, end);
            if (start == end) {
                ret.sum = nums[start];
            } 
            else {
                int mid = start  + (end - start) / 2;
                ret.left = buildTree(nums, start, mid);
                ret.right = buildTree(nums, mid + 1, end);
                ret.sum = ret.left.sum + ret.right.sum;
            }         
            return ret;
        }
    }
    
    public void update(int i, int val) {
        this.update(root, i, val);
    }
    
    
    public int sumRange(int i, int j) {
        return sumRange(root, i, j);
    }
    
    private void update(SegmentTreeNode root, int i, int val) {
        if (root == null) return;
        
        if (i < root.start || i > root.end) {
            return;
        }
        
        if (root.start == root.end) {
            root.sum = val;
            return;
        }
        
        int mid = root.start + (root.end - root.start) / 2;
        if (i <= mid) {
            this.update(root.left, i, val);
        }
        else {
            this.update(root.right, i, val);
        }
        
        root.sum = root.left.sum + root.right.sum;
    }
    
    private int sumRange(SegmentTreeNode root, int start, int end) {

        if (start == root.start && end == root.end) {
            return root.sum;
        }
        else {
            
            int mid = root.start + (root.end - root.start) / 2;
            if (end <= mid) {
                return this.sumRange(root.left, start, end);
            }
            else if (start > mid) {
                return this.sumRange(root.right, start, end);
            }
            else {
                return this.sumRange(root.left, start, mid) + this.sumRange(root.right, mid+1, end);
            }
        }

    }
}
