package LeetCode.Medium;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * There are a total of n courses you have to take, labeled from 0 to n - 1.
 * 
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1, which is expressed as a pair: [0,1]
 * 
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you should take to finish all courses.
 * 
 * There may be multiple correct orders, you just need to return one of them. If it is impossible to finish all courses, return an empty array.
 * 
 * For example:
 * 
 * 2, [[1,0]]
 * 
 * There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1]
 * 
 * 4, [[1,0],[2,0],[3,1],[3,2]]
 * There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. 
 * Both courses 1 and 2 should be taken after you finished course 0. So one correct course order is [0,1,2,3]. Another correct ordering is[0,2,1,3].
 * 
 * @author WinnieZhao
 *
 */
public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] result = new int[numCourses];
        
        if (numCourses == 0) return result;
        
        int length = prerequisites.length;
        if (length == 0) {
            
        }

        // Convert graph presentation from edges to indegree of adjacent list.
        int indegree[] = new int[numCourses];
        
        // Indegree - how many prerequisites are needed.
        for (int i = 0; i < length; i++) 
            indegree[prerequisites[i][0]]++;

        Queue<Integer> queue = new LinkedList<Integer>();

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }
        
        // How many courses don't need prerequisites.
        int count = 0;
        int canFinishCount = queue.size();  
        while (!queue.isEmpty()) {
            int prerequisite = queue.remove(); // Already finished this prerequisite course.
            result[count++] = prerequisite;

            for (int i = 0; i < prerequisites.length; i++)  {
                if (prerequisites[i][1] == prerequisite) { 
                    indegree[prerequisites[i][0]]--;
                    if (indegree[prerequisites[i][0]] == 0) {
                        canFinishCount++;
                        queue.add(prerequisites[i][0]);
                    }
                }
            }
        }
        
        if (canFinishCount == numCourses) {
            return result;
        }
        else {
            return new int[0];
        }
    }
    
    public static void main(String[] args) {
        CourseScheduleII solution = new CourseScheduleII();
        
        int[][] prerequisites =  {{1,0},{0,1}};
        
        System.out.println(Arrays.toString(solution.findOrder(2, prerequisites)));
        
        int[][] prerequisites1 =  {{1,0}};
        
        System.out.println(Arrays.toString(solution.findOrder(2, prerequisites1)));
        
        int[][] prerequisites2 =  {{1,0}, {2,1}, {3,2}, {4,3}, {4,2}};
        
        System.out.println(Arrays.toString(solution.findOrder(5, prerequisites2)));
    }
}
