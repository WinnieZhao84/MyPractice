package LeetCode.Medium;

/**
 * Given n, how many structurally unique BST's (binary search trees) that store values 1...n?
 * 
 * For example,
 * Given n = 3, there are a total of 5 unique BST's.
 * 
 * 1         3     3      2      1
 *  \       /     /      / \      \
 *   3     2     1      1   3      2
 *  /     /       \                 \
 * 2     1         2                 3
 *
 * @author WinnieZhao
 *
 */
public class UniqueBinarySearchTrees {

    /**
     * Details for this problem explanation:
     * https://www.youtube.com/watch?v=UfA_v0VmiDg
     * 
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] tree = new int[n+1];
        tree[0] = 1;

        /**
         * 定义f(n)为unique BST的数量，以n = 3为例：
         * 构造的BST的根节点可以取{1, 2, 3}中的任一数字
         * 如以1为root，则left subtree只能有0个节点，而right subtree有2, 3两个节点。所以left/right subtree一共的combination数量为：f(0) * f(2) = 2
         * 以2为root，则left subtree只能为1，right subtree只能为2：f(1) * f(1) = 1
         * 以3为root，则left subtree有1, 2两个节点，right subtree有0个节点：f(2)*f(0) = 2
         *
         * 总结规律：
         * f(0) = 1
         * f(n) = f(0)*f(n-1) + f(1)*f(n-2) + ... + f(n-2)*f(1) + f(n-1)*f(0)

         */
        for (int i=1; i<=n; i++) {
            for (int j=0; j<i; j++) {
                tree[i] += tree[j] * tree[i-1-j];
            }

        }
        
        return tree[n];
    }
    
    public static void main(String[] args) {
        UniqueBinarySearchTrees solution = new UniqueBinarySearchTrees();
        
        System.out.println(solution.numTrees(2));
        System.out.println(solution.numTrees(3));
        System.out.println(solution.numTrees(4));
    }
}
