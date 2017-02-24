package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
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
    
    private int N = 0;
    
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

    class Course {
        boolean visited = false;
        boolean tested = false;
        int number;
        List<Course> pre = new ArrayList<Course>();
        public Course(int i) {
            number = i;
        }
        public void add(Course c) {
            pre.add(c);
        }
    }
    
    public int[] findOrder_DFS(int numCourses, int[][] prerequisites) {
        N = 0;
        int[] result = new int[numCourses];
        Course[] courses = new Course[numCourses];
        for (int i = 0; i < numCourses; i++) {
            courses[i] = new Course(i);
        }
        for (int i = 0; i < prerequisites.length; i++) {
            courses[prerequisites[i][0]].add(courses[prerequisites[i][1]]);
        }
        for (int i = 0; i < numCourses; i++) {
            if (isCyclic(courses[i], result)) {
                return new int[0];
            }
        }
        return result;
    }

    private boolean isCyclic(Course cur, int[] result) {
        if (cur.tested == true) return false;
        if (cur.visited == true) return true;
        cur.visited = true;
        for (Course c : cur.pre) {
            if (isCyclic(c, result)) {
                return true;
            }
        }
        cur.tested = true;
        result[N++] = cur.number;
        return false;
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
