package LeetCode.Medium;

/**
 * Given an integer array nums, find the sum of the elements between indices i and j (i <= j), inclusive.
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

    /**
     * Time Complexity for tree construction is O(n). There are total 2n-1 nodes, and value of every node is calculated
     * only once in tree construction.
     *
     * Time complexity to query is O(Logn).
     *
     * The time complexity of update is also O(Logn). To update a leaf value, we process one node at every level and number
     * of levels is O(Logn).

     * Segment tree is a very flexible data structure, because it is used to solve numerous range query problems
     * like finding minimum, maximum, sum, greatest common divisor, least common denominator in array in logarithmic time.
     *
     * Representation of Segment trees:
     * Leaf Nodes are the elements of the input array.
     * Each internal node represents some merging of the leaf nodes. The merging may be different for different problems.
     * For this problem, merging is sum of leaves under a node
     *
     */
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
