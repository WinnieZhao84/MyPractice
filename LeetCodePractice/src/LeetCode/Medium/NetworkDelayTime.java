package LeetCode.Medium;

import java.util.*;

/**
 * There are N network nodes, labelled 1 to N.
 *
 * Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node,
 * v is the target node, and w is the time it takes for a signal to travel from source to target.
 *
 * Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal?
 * If it is impossible, return -1.
 *
 * Note:
 * N will be in the range [1, 100].
 * K will be in the range [1, N].
 * The length of times will be in the range [1, 6000].
 * All edges times[i] = (u, v, w) will have 1 <= u, v <= N and 1 <= w <= 100.

 * Created by WinnieZhao on 1/1/2018.
 */
public class NetworkDelayTime {

    /**
     * Time Complexity O(E*logE): E is the length of times in the basic implementation.
     * Space Complexity O(N+E), the size of the graph O(E), plus the size of the other objects used O(N).
     */
    public int networkDelayTime(int[][] times, int N, int K) {

        if (times == null || times.length == 0) {
            return -1;
        }

        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] time : times) {
            if (!graph.containsKey(time[0])) {
                graph.put(time[0], new ArrayList<>());
            }
            graph.get(time[0]).add(new int[]{time[1], time[2]});
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        pq.add(new int[] {K, 0});

        Map<Integer, Integer> distanceMap = new HashMap<>();
        while (!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int distance = cur[1];

            if (distanceMap.containsKey(node)) {
                continue;
            }

            distanceMap.put(node, distance);

            if(graph.containsKey(node)) {
                for (int[] edge : graph.get(node)) {

                    if (!distanceMap.containsKey(edge[0])) {
                        pq.offer(new int[]{edge[0], edge[1]+distance});
                    }
                }
            }
        }

        if (distanceMap.size() != N) {
            return -1;
        }

        int res = 0;
        for (int dist : distanceMap.values())
            res = Math.max(res, dist);
        return res;
    }

    public static void main(String[] args) {
        NetworkDelayTime solution = new NetworkDelayTime();

        System.out.println(solution.networkDelayTime(new int[][] {{1,2,1},{2,3,2},{1,3,4}}, 3, 1));
        System.out.println(solution.networkDelayTime(new int[][] {{1,2,1},{2,3,2},{1,3,2}}, 3, 1));
        System.out.println(solution.networkDelayTime(new int[][] {{2,1,1},{2,3,1},{3,4,1}}, 4, 2));

    }
}
