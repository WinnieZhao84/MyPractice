package LeetCode.Interview.Amazon.LevelTwo;

import java.util.ArrayList;

/**
 * 就是给一棵多叉树,表示公司内部的上下级关系.每个节点表示一个员工, 节点包含的成员是他工作了几个月(int)，以及一个下属数组(ArrayList).
 * 目标就是找到一棵子树,满足: 这棵子树所有节点的工作月数的平均数是所有子树中最大的. 最后返回这棵子树的根节点.
 * 这题补充一点，返回的不能是叶子节点(因为叶子节点没有下属),一定要是一个有子节点的节点
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class MaximumSubtreeOfAverage {

    class Node {
        int val;
        ArrayList<Node> children;
        public Node(int val){
            this.val = val;
            children = new ArrayList<>();
        }
    }

    static class SumCount {
        int sum;
        int count;
        public SumCount(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
    }

    static Node res;
    static double max = 0;

    public static Node find(Node root) {
        // Initialize static variables is very important for AMZAON OA!
        res = null;
        max = 0;
        dfs(root);
        return res;
    }

    private static SumCount dfs(Node root) {
        if (root == null) {
            return new SumCount(0, 0);
        }
        if (root.children == null || root.children.isEmpty()) {
            return new SumCount(root.val, 1);
        }

        int sum = root.val;
        int count = 1;

        for(Node node : root.children) {
            SumCount sc = dfs(node);
            sum += sc.sum;
            count += sc.count;
        }
        if(count > 1 && (sum + 0.0) / count > max) {
            max = (sum + 0.0) / count;
            res = root;
        }
        return new SumCount(sum, count);
    }
}
