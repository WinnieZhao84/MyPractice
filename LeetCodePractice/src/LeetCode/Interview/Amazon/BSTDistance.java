package LeetCode.Interview.Amazon;

import LeetCode.Helper.TreeNode;

/**
 * Distance between two nodes in a bst
 *
 * 给一个数组A[5，6，3，1，2，4]，先建立BST，然后搜索两个node 之间的距离。
 * 已知第一个元素5 是root，剩下的是无序的！！注意后面有可能是右子树先出现（6，对应root-­5），
 * 也有可能是左子树先出现（1，2，对应root-­3）！
 * 如果有node 不存在的话，返回 ­1
 *
 * Created by WinnieZhao on 9/28/2017.
 */
public class BSTDistance {
    public static void main(String[] args){
        int[] input = {5,6,3,1,2,4};
        System.out.println(bstDistance(input,6,2,4));
        System.out.println(bstDistance(input,6,2,6));
    }

    public static int bstDistance(int[] vals, int n, int node1, int node2) {
        if (vals == null || vals.length == 0){
            return 0;
        }

        TreeNode root = new TreeNode(vals[0]);

        for (int i=1; i<vals.length; i++) {
            createBST(root, vals[i]);
        }

        int len1 = findDistance(root, node1) - 1;
        int len2 = findDistance(root, node2) - 1 ;
        if (len1 == -1 || len2 == -1){
            return -1;
        }

        TreeNode lca = findLCA(root,node1,node2);
        int mid = findDistance(root, lca.val) - 1;

        return len1+len2-2*mid;

    }

    public static TreeNode findLCA(TreeNode root, int val1, int val2){
        if (root == null || val1 == root.val || val2 == root.val){
            return root;
        }
        if ((root.val <= val1 && root.val >= val2) || (root.val <= val2 && root.val >= val1)) {
            return root;
        }
        else {
            if (root.val > Math.max(val1, val2)) {
                return findLCA(root.left, val1, val2);
            }
            else {
                return findLCA(root.right, val1, val2);
            }
        }
    }

    private static int findDistance(TreeNode root, int nodeVal) {
        if (root == null) {
            return 0;
        }

        int dis = 0;
        if (root.val == nodeVal){
            return dis + 1;
        }
        else if (root.val < nodeVal ){
            dis = findDistance(root.right, nodeVal);
        }
        else {
            dis = findDistance(root.left, nodeVal);
        }

        if (dis > 0){
            return dis + 1;
        }
        else{
            return 0;
        }
    }

    private static void createBST(TreeNode root, int val) {
        if (val < root.val) {
            if (root.left == null) {
                root.left = new TreeNode(val);
            }
            else {
                createBST(root.left, val);
            }
        }
        else {
            if (root.right == null) {
                root.right = new TreeNode(val);
            }
            else {
                createBST(root.right, val);
            }
        }
    }
}
