package LeetCode.Medium;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * In this problem, a tree is an undirected graph that is connected and has no cycles.
 *
 * The given input is a graph that started as a tree with N nodes (with distinct values 1, 2, ..., N),
 * with one additional edge added. The added edge has two different vertices chosen from 1 to N,
 * and was not an edge that already existed.
 *
 * The resulting graph is given as a 2D-array of edges. Each element of edges is a pair [u, v] with u < v,
 * that represents an undirected edge connecting nodes u and v.
 *
 * Return an edge that can be removed so that the resulting graph is a tree of N nodes.
 * If there are multiple answers, return the answer that occurs last in the given 2D-array.
 * The answer edge [u, v] should be in the same format, with u < v.
 *
 * Example 1:
 * Input: [[1,2], [1,3], [2,3]]
 * Output: [2,3]
 * Explanation: The given undirected graph will be like this:
 *    1
 *   / \
 *  2 - 3
 *
 * Example 2:
 * Input: [[1,2], [2,3], [3,4], [1,4], [1,5]]
 * Output: [1,4]
 * Explanation: The given undirected graph will be like this:
 * 5 - 1 - 2
 *     |   |
 *     4 - 3
 *
 * Note:
 * The size of the input 2D-array will be between 3 and 1000.
 * Every integer represented in the 2D-array will be between 1 and N, where N is the size of the input array.

 * Created by WinnieZhao on 10/1/2017.
 */
public class RedundantConnection {

    public int[] findRedundantConnection(int[][] edges) {

        if (edges == null || edges.length == 0) {
            return new int[0];
        }

        int[] res = new int[2];
        UnionFind uf = new UnionFind();
        for (int[] edge : edges) {
            if (!uf.union(edge)) {
                res[0] = edge[0];
                res[1] = edge[1];
                break;
            }
        }

        return res;
    }

    public static class UnionFind {
        Map<Integer, Integer> map = new HashMap<>();
        int setNum = 0;

        public boolean union(int[] edge) {
            if (!map.containsKey(edge[0]) && !map.containsKey(edge[1])) {
                map.put(edge[0], setNum);
                map.put(edge[1], setNum);
                setNum++;
                return true;
            }
            if (map.containsKey(edge[0]) && !map.containsKey(edge[1])) {
                map.put(edge[1], map.get(edge[0]));
                return true;
            }
            if (!map.containsKey(edge[0]) && map.containsKey(edge[1])) {
                map.put(edge[0], map.get(edge[1]));
                return true;
            }
            if (map.containsKey(edge[0]) && map.containsKey(edge[1])) {
                int num1 = map.get(edge[0]);
                int num2 = map.get(edge[1]);

                if (num1 == num2) {
                    return false;
                }
                for (Integer key : map.keySet()) {
                    if (map.get(key) == num1) {
                        map.put(key, num2);
                    }
                }
                return true;
            }

            return false;
        }
    }
}
