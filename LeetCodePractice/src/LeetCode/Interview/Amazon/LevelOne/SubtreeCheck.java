package LeetCode.Interview.Amazon.LevelOne;

import LeetCode.Helper.TreeNode;

/**
 * Another approach refer to SubtreeOfAnotherTree
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class SubtreeCheck {
    public boolean isSubTree(TreeNode T1, TreeNode T2) {
        if (T2 == null) {
            return true;
        }
        if (T1 == null) {
            return false;
        }
        return (isSameTree(T1,T2) || isSubTree(T1.left, T2) || isSubTree(T1.right, T2));
    }

    private boolean isSameTree(TreeNode T1, TreeNode T2) {
        if (T1 == null && T2 == null)
            return true;
        if (T1 == null || T2 == null)
            return false;
        if (T1.val != T2.val)
            return false;

        return (isSameTree(T1.left, T2.left) && isSameTree(T1.right, T2.right));
    }
}
