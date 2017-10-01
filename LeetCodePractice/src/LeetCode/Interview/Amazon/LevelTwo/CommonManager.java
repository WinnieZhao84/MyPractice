package LeetCode.Interview.Amazon.LevelTwo;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 给你CEO 还有各个部门的经理人从属关系。让你找两个人的最近的公共管理人。其实就是K叉树 LCA
 * Created by WinnieZhao on 9/28/2017.
 */
public class CommonManager {

    public static class Employee {
        int id;
        List<Employee> reports = new ArrayList<>();

        public void setId(int val){
            id = val;
        }
        public int getId(){
            return id;
        }

        public void addReport(Employee emp){
            reports.add(emp);
        }

        public List<Employee> getReports(){
            return reports;
        }
    }

    public static Employee closestCommonManager(Employee ceo, Employee firstEmployee, Employee secondEmployee) {
        if (ceo == null || ceo == firstEmployee || ceo == secondEmployee){
            return ceo;
        }

        Stack<Employee> firstPath = new Stack<>();
        Stack<Employee> secondPath = new Stack<>();

        Employee root = ceo;

        dfs(root, firstEmployee, firstPath);
        dfs(root, secondEmployee, secondPath);

        if (firstPath.peek().getId() == firstEmployee.getId() && secondPath.peek().getId() == secondEmployee.getId()) {
            int size1 = firstPath.size();
            int size2 = secondPath.size();
            int diff = Math.abs(size2-size1);

            if (size1 > size2){
                moveUp(firstPath, diff);
            }
            else{
                moveUp(secondPath, diff);
            }

            while(firstPath.size() > 0 && firstPath.peek().getId() != secondPath.peek().getId()){
                firstPath.pop();
                secondPath.pop();
            }

            if (firstPath.size() > 0){
                return firstPath.pop();
            }
        }
        return null;
    }

    private static void moveUp(Stack<Employee> path, int diff) {
        while (diff > 0 && !path.isEmpty()) {
            path.pop();
            diff--;
        }
    }

    private static boolean dfs(Employee root, Employee target, Stack<Employee> path){
        path.push(root);

        if (root.getId() == target.getId()){
            return true;
        }

        for(Employee r : root.getReports()){
            boolean res = dfs(r, target, path);
            if(res){
                return true;
            }
        }

        path.pop();
        return false;
    }
}
