package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to
 * finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses,
 * return an empty array.
 * 
 * For example:
 * 
 * 2, [[1,0]]
 * 
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course
 * order is [0,1]
 * 
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
 * Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3].
 * Another correct ordering is[0,2,1,3].
 * 
 * @author WinnieZhao
 *
 */
public class CourseScheduleII {
    
    private int N = 0; // For DFS
    
    // Time Complexity: O(V + E) where VV represents the number of vertices and EE represents the number of edges. We pop each node exactly once from the zero in-degree queue and that gives us VV. 
    // Also, for each vertex, we iterate over its adjacency list and in totality, we iterate over all the edges in the graph which gives us EE. Hence, O(V + E)O(V+E)
    // Space Complexity: O(V + E) We use an intermediate queue data structure to keep all the nodes with 0 in-degree. In the worst case, there won't be any prerequisite relationship 
    // and the queue will contain all the vertices initially since all of them will have 0 in-degree. That gives us O(V)O(V). Additionally, we also use the adjacency list to represent our graph initially. 
    // The space occupied is defined by the number of edges because for each node as the key, we have all its adjacent nodes in the form of a list as the value. Hence, O(E)O(E). So, the overall space complexity is O(V + E).
    public int[] findOrder_BFS(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];

        int[] indegree = new int[numCourses];
        for (int i=0; i<prerequisites.length; i++) {
            int course = prerequisites[i][0];
            indegree[course]++;
        }

        Queue<Integer> nonPreCourses = new LinkedList<>();
        int count = 0;
        int canFinishCourses = 0;
        for (int i=0; i<numCourses; i++) {
            if (indegree[i] == 0) {
                canFinishCourses++;
                nonPreCourses.add(Integer.valueOf(i));
                result[count++] = i;
            }
        }

        while(!nonPreCourses.isEmpty()) {
            Integer preCourse = nonPreCourses.poll();
            for (int i=0; i<prerequisites.length; i++) {
                if (prerequisites[i][1] == preCourse) {
                    int course = prerequisites[i][0];
                    indegree[course]--;

                    if (indegree[course] == 0) {
                        canFinishCourses++;
                        result[count++] = course;
                        nonPreCourses.add(course);
                    }
                }
            }
        }

        if (canFinishCourses != numCourses) {
            return new int[0];
        }

        return result;
    }

    // Time Complexity: O(N) considering there are N courses in all. We essentially perform a complete depth first search covering all the nodes in the forest. 
    // It's a forest and not a graph because not all nodes will be connected together. There can be disjoint components as well.
    // Space Complexity: O(N) the space utilized by the recursion stack (not the stack we used to maintain the topologically sorted order)
    public int[] findOrder_DFS(int numCourses, int[][] prerequisites) {
        if (numCourses == 0 || prerequisites == null) {
            return new int[0];
        }
        
        List<List<Integer>> preCourses = new ArrayList<>();
        
        for (int i=0; i<numCourses; i++) {
            preCourses.add(new ArrayList<>());
        }
        
        for (int i=0; i<prerequisites.length; i++) {
            int pre = prerequisites[i][1];
            int cur = prerequisites[i][0];
            
            preCourses.get(cur).add(Integer.valueOf(pre));
        }
        
        int[] visited = new int[numCourses];
        
        int[] res = new int[numCourses];
        
        for (int i=0; i<numCourses; i++) {
            if (!dfs(res, preCourses, visited, i)) {
                return new int[0];
            }
        }
        
        return res;
    }
    
    // visited == 1 means the node and all its linked ones have been visited and this is a good node
    // visited == 2 means there is a loop, the same node is visiting.
    private boolean dfs(int[] res, List<List<Integer>> precourses, int[] visited, int course) {
        if (visited[course] == 1) {
            return true;
        }
        
        if (visited[course] == 2) {
            return false;
        }
        
        visited[course] = 2;
        for (Integer pre : precourses.get(course)) {
            if (!dfs(res, precourses, visited, pre)) {
                return false;
            }
        }
        
        visited[course] = 1; 
        res[N++] = course;
        
        return true;
    }
    
    public static void main(String[] args) {
        CourseScheduleII solution = new CourseScheduleII();
        
        int[][] prerequisites =  {{1,0},{0,1}};
        
        System.out.println(Arrays.toString(solution.findOrder_DFS(2, prerequisites)));
        
        int[][] prerequisites1 =  {{0, 1}};
        
        System.out.println(Arrays.toString(solution.findOrder_DFS(2, prerequisites1)));
        
        int[][] prerequisites2 =  {{1,0},{2,0},{3,1},{3,2}};
        
        System.out.println(Arrays.toString(solution.findOrder_DFS(4, prerequisites2)));
    }
}
