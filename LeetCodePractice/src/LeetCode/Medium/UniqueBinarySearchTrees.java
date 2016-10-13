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
        tree[1] = 1;
        
        for (int i=2; i<=n; i++) {
            for (int j=1; j<=i; j++) {
                tree[i] += tree[j-1] * tree[i-j];
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
