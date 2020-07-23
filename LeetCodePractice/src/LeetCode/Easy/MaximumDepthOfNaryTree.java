package LeetCode.Easy;

import java.util.List;

class Node {
    public int val;
    public List<Node> children;

    public Node() {}

    public Node(int _val) {
        val = _val;
    }

    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
};

/**
 * 559. Maximum Depth of N-ary Tree
 * Given a n-ary tree, find its maximum depth.
 * The maximum depth is the number of nodes along the longest path from the root node down to the farthest leaf node.
 * Nary-Tree input serialization is represented in their level order traversal, each group of children is separated by the null value (See examples).
 * @author WinnieZhao
 *
 */
public class MaximumDepthOfNaryTree {
    public int maxDepth(Node root) {
        if (root == null) {
            return 0;
        }
        
        int maxDepth = 0;
        
        for (Node node : root.children) {
            maxDepth = Math.max(maxDepth, maxDepth(node));
        }
        
        return maxDepth+1;
    }
}
