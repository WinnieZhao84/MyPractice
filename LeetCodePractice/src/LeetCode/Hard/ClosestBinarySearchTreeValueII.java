package LeetCode.Hard;

import LeetCode.Helper.TreeNode;

import java.util.*;

/**
 * 272
 *
 * Given a non-empty binary search tree and a target value, find k values in the BST that are closest to the target.
 * Note: Given target value is a floating point.
 * You may assume k is always valid, that is: k ≤ total nodes.
 * You are guaranteed to have only one unique set of k values in the BST that are closest to the target.
 *
 * Follow up: Assume that the BST is balanced, could you solve it in less than O(n) runtime (where n = total nodes)?

 * Created by WinnieZhao on 4/24/2017.
 */
public class ClosestBinarySearchTreeValueII {

    /**
     * O(lgN) solution
     */
    public List<Integer> closestKValues(TreeNode root, double target, int k) {
        List<Integer> ret = new LinkedList<>();
        Stack<TreeNode> succ = new Stack<>();
        Stack<TreeNode> pred = new Stack<>();

        initializePredecessorStack(root, target, pred);
        initializeSuccessorStack(root, target, succ);

        // in case there exists a node with val == target
        if(!succ.isEmpty() && !pred.isEmpty() && succ.peek().val == pred.peek().val) {
            getNextPredecessor(pred);
        }

        while(k-- > 0) {
            if(succ.isEmpty()) {
                ret.add(getNextPredecessor(pred));
            }
            else if(pred.isEmpty()) {
                ret.add(getNextSuccessor(succ));
            }
            else {
                double succ_diff = Math.abs((double)succ.peek().val - target);
                double pred_diff = Math.abs((double)pred.peek().val - target);
                if(succ_diff < pred_diff) {
                    ret.add(getNextSuccessor(succ));
                } else {
                    ret.add(getNextPredecessor(pred));
                }
            }
        }
        return ret;
    }

    // build suc stack (contains only elements greater than target)
    private void initializeSuccessorStack(TreeNode root, double target, Stack<TreeNode> succ) {
        while(root != null) {
            if(root.val == target) {
                succ.push(root);
                break;
            } else if(root.val > target) {
                succ.push(root);
                root = root.left;
            } else {
                root = root.right;
            }
        }
    }

    // build pre stack (contains only elements smaller than target)
    private void initializePredecessorStack(TreeNode root, double target, Stack<TreeNode> pred) {
        while(root != null){
            if(root.val == target){
                pred.push(root);
                break;
            } else if(root.val < target){
                pred.push(root);
                root = root.right;
            } else{
                root = root.left;
            }
        }
    }

    // get the smallest element that's bigger
    private int getNextSuccessor(Stack<TreeNode> succ) {
        TreeNode curr = succ.pop();
        int ret = curr.val;
        curr = curr.right;
        while(curr != null) {
            succ.push(curr);
            curr = curr.left;
        }
        return ret;
    }

    // get the biggest element that's smaller
    private int getNextPredecessor(Stack<TreeNode> pred) {
        TreeNode curr = pred.pop();
        int ret = curr.val;
        curr = curr.left;
        while(curr != null) {
            pred.push(curr);
            curr = curr.right;
        }
        return ret;
    }

    /**
     * O(N) solution
     */
    public List<Integer> closestKValues_normal(TreeNode root, double target, int k) {
        LinkedList<Integer> res = new LinkedList<>();

        if (root == null) {
            return res;
        }

        this.inorder(res, root, target, k);

        return res;
    }

    private void inorder(LinkedList<Integer> res, TreeNode root, double target, int k) {
        if (root == null) {
            return;
        }

        inorder(res, root.left, target, k);

        if (res.size() == k && Math.abs(root.val - target) < Math.abs(res.peekFirst() - target)) {
            res.removeFirst();
        }
        res.add(root.val);

        inorder(res, root.right, target, k);
    }
}
