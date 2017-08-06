package LeetCode.Medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * 
 * For example:
 * 
 * 2, [[1,0]]
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So it is possible.
 * 
 * 2, [[1,0],[0,1]]
 * 
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0, and to take course 0
 * you should also have finished course 1. So it is impossible.

 * @author WinnieZhao
 *
 */
public class CourseSchedule {
    List<Integer> valid = new ArrayList<>();
    
    public boolean canFinish_DFS(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }

        List<List<Integer>> preCourses = new ArrayList<List<Integer>>();
        for (int i=0; i<numCourses; i++) {
            preCourses.add(new ArrayList<>());
        }

        for (int i=0; i<prerequisites.length; i++) {
            int course = prerequisites[i][0];
            int preCourse = prerequisites[i][1];
            preCourses.get(course).add(Integer.valueOf(preCourse));
        }

        List<Integer> visited = new ArrayList<>();

        for (int i=0; i<numCourses; i++) {
            if (!this.dfs(preCourses, visited, i)) {
                return false;
            }
        }

        return true;
    }
    
    private boolean dfs(List<List<Integer>> preCourses, List<Integer> visited, int course) {
        if (visited.contains(course)) {
            return false;
        }
        if (valid.contains(course)) {
            return true;
        }
        visited.add(Integer.valueOf(course));
        for (Integer pre : preCourses.get(course)) {

            if (!this.dfs(preCourses, visited, pre)) {
                return false;
            }
            valid.add(pre);
        }
        visited.remove(Integer.valueOf(course));

        return true;
    }
    
    public boolean canFinish_BFS(int numCourses, int[][] prerequisites) {
        if (prerequisites == null || prerequisites.length == 0) {
            return true;
        }
        
        int[] indegree = new int[numCourses];
        for (int i=0; i<prerequisites.length; i++) {
            int course = prerequisites[i][0];
            indegree[course]++;
        }
        
        Queue<Integer> nonPreCourses = new LinkedList<>();
        int canFinishCourses = 0;
        for (int i=0; i<numCourses; i++) {
            if (indegree[i] == 0) {
                nonPreCourses.add(Integer.valueOf(i));
                canFinishCourses++;
            }
        }

        if (canFinishCourses == numCourses) {
            return true;
        }

        while(!nonPreCourses.isEmpty()) {
            Integer preCourse = nonPreCourses.poll();

            for (int i=0; i<prerequisites.length; i++) {
                if (prerequisites[i][1] == preCourse) {
                    int course = prerequisites[i][0];
                    indegree[course]--;

                    if (indegree[course] == 0) {
                        canFinishCourses++;
                        nonPreCourses.add(Integer.valueOf(course));
                    }
                }
            }

        }

        return canFinishCourses == numCourses;
    }
    
    public static void main(String[] args) {
        CourseSchedule solution = new CourseSchedule();
        
        int[][] prerequisites =  {{1,0},{2,1},{3,2},{1,3}};
        
        System.out.println(solution.canFinish_BFS(4, prerequisites));
        
        int[][] prerequisites1 =  {{1,0}};
        
        System.out.println(solution.canFinish_BFS(2, prerequisites1));
        
        int[][] prerequisites2 =  {{1,0}, {2,1}, {3,2}, {4,3}, {3,4}};
        
        System.out.println(solution.canFinish_BFS(5, prerequisites2));
    }
}
