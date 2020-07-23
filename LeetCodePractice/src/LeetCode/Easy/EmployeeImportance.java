package LeetCode.Easy;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * You are given a data structure of employee information, which includes the employee's unique id,
 * his importance value and his direct subordinates' id.
 *
 * For example, employee 1 is the leader of employee 2, and employee 2 is the leader of employee 3.
 * They have importance value 15, 10 and 5, respectively.
 * Then employee 1 has a data structure like [1, 15, [2]], and employee 2 has [2, 10, [3]],
 * and employee 3 has [3, 5, []]. Note that although employee 3 is also a subordinate of employee 1,
 * the relationship is not direct.
 *
 * Now given the employee information of a company, and an employee id, you need to return the total
 * importance value of this employee and all his subordinates.
 *
 * Example 1:
 * Input: [[1, 5, [2, 3]], [2, 3, []], [3, 3, []]], 1
 *
 * Output: 11
 * Explanation:
 * Employee 1 has importance value 5, and he has two direct subordinates: employee 2 and employee 3.
 * They both have importance value 3. So the total importance value of employee 1 is 5 + 3 + 3 = 11.

 * Created by WinnieZhao on 10/1/2017.
 */
public class EmployeeImportance {

    class Employee {
        // It's the unique id of each node;
        // unique id of this employee
        public int id;
        // the importance value of this employee
        public int importance;
        // the id of direct subordinates
        public List<Integer> subordinates;
    }

    int total = 0;

    public int getImportance(List<Employee> employees, int id) {
        Employee manager = employees.stream().filter(e -> e.id == id).collect(Collectors.toList()).get(0);

        total += manager.importance;
        manager.subordinates.forEach(subId -> getImportance(employees, subId));

        return total;
    }
    
    public int getImportance_BFS(List<Employee> employees, int id) {
        if (employees == null || employees.size() == 0) {
            return 0;
        }
        
        Queue<Integer> queue = new LinkedList<>();
        Set<Integer> visited = new HashSet<>();
        visited.add(id);
        queue.add(id);
        
        int total = 0;
        while (!queue.isEmpty()) {
            
            Integer cur = queue.poll();
            for (Employee em : employees) {
                if (em.id == cur) {
                    for (Integer sub : em.subordinates) {
                        if (!visited.contains(sub)) {
                            queue.add(sub);
                        }
                    }
                    total += em.importance;
                }
            }
        }
        
        return total;
    }
}
