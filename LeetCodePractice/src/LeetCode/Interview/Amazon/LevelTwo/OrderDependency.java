package LeetCode.Interview.Amazon.LevelTwo;

import java.util.*;

/**
 * Similar to LeetCode Course Schedule
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class OrderDependency {
    static class Order{
        String orderName;
        public Order(String orderName) {
            this.orderName = orderName;
        }
    }

    static class Dependency {
        Order cur;
        Order pre;
        public Dependency(Order cur, Order pre) {
            this.cur = cur;
            this.pre = pre;
        }
    }

    public static List<Order> find(List<Dependency> orderDependencies) {
        List<Order> result = new ArrayList<>();
        if (orderDependencies == null || orderDependencies.size() == 0) {
            return result;
        }

        Map<Order, List<Order>> adjList = new HashMap<>();
        Map<Order, Integer> indegree = new HashMap<>();
        Queue<Order> queue = new LinkedList<>();

        // fill indegree and adjList
        for (Dependency dep : orderDependencies) {
            Order cur = dep.cur;
            Order pre = dep.pre;

            // add to adjList
            List<Order> list = adjList.getOrDefault(pre, new ArrayList<>());
            list.add(cur);
            adjList.put(pre, list);

            // add to indegree
            int degree = indegree.getOrDefault(cur, 0);
            indegree.put(cur, degree+1);

            indegree.putIfAbsent(pre, 0);
        }

        for (Order o : indegree.keySet()) {
            if (indegree.get(o) == 0) {
                queue.offer(o);
            }
        }

        // BFS
        while (!queue.isEmpty()) {
            Order order = queue.poll();
            indegree.remove(order);
            result.add(order);
            if (adjList.containsKey(order)) {
                for (Order cur : adjList.get(order)) {
                    if (indegree.containsKey(cur)) {
                        indegree.put(cur, indegree.get(cur) - 1);
                    }
                    if (indegree.get(cur) == 0) {
                        queue.offer(cur);
                    }
                }
            }
        }

        if (indegree.size() != 0) {
            return null;
        }
        return result;
    }
}
