package LeetCode.Medium;

import java.util.*;

/**
 * Given an absolute path for a file (Unix-style), simplify it.
 * 
 * For example,
 * path = "/home/", => "/home"
 * path = "/a/./b/../../c/", => "/c"
 * 
 * Corner Cases:Did you consider the case where path = "/../"?
 * In this case, you should return "/".
 * Another corner case is the path might contain multiple slashes '/' together, such as "/home//foo/".
 * In this case, you should ignore redundant slashes and return "/home/foo".
 * 
 * @author WinnieZhao
 *
 */
public class SimplifyPath {

    /**
     *  "." means current folder, ".." means parent folder.
     *  So "/a/b/.." means from a to b folder, and from b to b's parent folder,
     *  finally we can simplified it as "/a"
     *  Examples:
     *  "/b/c/" - directory 'b ' - > directory 'c'
     *  "." - current directory
     *  "./" - current directory
     *  "../" - one directory up
     *  "/" : root directory
     *  "b/c/../" : it will go from c to b
     *  "c/b/./" : it is still in directory b
     *
     *
     * The main idea is to push to the stack every valid file name (not in {"",".",".."}),
     * popping only if there's smth to pop and we met "..".
     * @param path
     * @return
     */
    public String simplifyPath(String path) {
        Deque<String> stack = new LinkedList<>();
        Set<String> skip = new HashSet<>(Arrays.asList("..",".",""));
        for (String dir : path.split("/")) {
            if (dir.equals("..") && !stack.isEmpty()) {
                stack.pop();
            }
            else if (!skip.contains(dir)) {
                stack.push(dir);
            }
        }
        String res = "";
        for (String dir : stack) {
            res = "/" + dir + res;
        }
        return res.isEmpty() ? "/" : res;
    }

    class Solution {
        public String simplifyPath(String path) {
            if (path == null || path.length() == 0) {
                return path;
            }

            Stack<String> stack = new Stack<>();
            String[] dirs = path.split("/");

            for (String dir : dirs) {
                if (dir.equals("")) {
                    continue;
                }
                else if (dir.equals(".")) {
                    continue;
                }
                else if (dir.equals("..")) {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    }
                }
                else {
                    stack.push(dir);
                }
            }

            if (stack.isEmpty()) {
                return "/";
            }

            StringBuilder sb = new StringBuilder();
            for (String str : stack) {
                sb.append("/");
                sb.append(str);
            }

            return sb.toString();
        }
    }
    
    public static void main(String[] args) {
        SimplifyPath solution = new SimplifyPath();
        
        System.out.println(solution.simplifyPath("/a/./b/../../c/"));
    }
}
