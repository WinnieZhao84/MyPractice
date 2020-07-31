package LeetCode.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import LeetCode.Helper.TreeNode;

/**
 * 863. All Nodes Distance K in Binary Tree
 * We are given a binary tree (with root node root), a target node, and an integer value K.
 * 
 * Return a list of the values of all nodes that have a distance K from the target node.  The answer can be returned in any order.
 * Example 1:
 * Input: root = [3,5,1,6,2,0,8,null,null,7,4], target = 5, K = 2
 * Output: [7,4,1]
 * 
 * Explanation: 
 * The nodes that are a distance 2 from the target node (with value 5)
 * have values 7, 4, and 1.
 * 
 *                 3
 *           /            \
 *          5              1
 *       /     \       /       \
 *      6       2     0         8
 *            /   \            
 *           7     4            
 * @author WinnieZhao
 *
 */
public class AllNodesDistanceKInBinaryTree {

    Map<TreeNode, List<TreeNode>> graph = new HashMap();
    
    public List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        if (target == null) {
            return new ArrayList<>();
        }
        
        // Build the graph
        buildMap(root, null);
        
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        
        Set<TreeNode> visited = new HashSet<>();
        visited.add(target);
        
        List<Integer> res = new ArrayList<>();

        while(!queue.isEmpty()) {
            int size = queue.size();
            
            if (K == 0) {
                for (int i=0; i<size; i++) {
                    res.add(queue.poll().val);
                }
                return res;
            }
            for (int i=0; i<size; i++) {
                TreeNode cur = queue.poll();
                
                for (TreeNode next : graph.get(cur)) {
                    if (visited.add(next)) {
                        queue.add(next);
                    }
                }
            }
            K--;
        }
        return new ArrayList<>();
    }
    
    private void buildMap(TreeNode root, TreeNode parent) {
        if (root == null) {
            return;
        }
        
        if (!graph.containsKey(root)) {
            graph.put(root, new ArrayList<>());
            if (parent != null) {
                graph.get(root).add(parent);
                graph.get(parent).add(root);
            }
            buildMap(root.left, root);
            buildMap(root.right, root);
        }
    }
}
