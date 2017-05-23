package LeetCode.Hard;

import java.util.*;

/**
 * Design an in-memory file system to simulate the following functions:
 *
 * ls: Given a path in string format. If it is a file path, return a list that only contains this file's name.
 * If it is a directory path, return the list of file and directory names in this directory.
 * Your output (file and directory names together) should in lexicographic order.
 *
 * mkdir: Given a directory path that does not exist, you should make a new directory according to the path.
 * If the middle directories in the path don't exist either, you should create them as well. This function has void return type.
 *
 * addContentToFile: Given a file path and file content in string format.
 * If the file doesn't exist, you need to create that file containing given content.
 * If the file already exists, you need to append given content to original content.
 * This function has void return type.
 *
 * readContentFromFile: Given a file path, return its content in string format.
 *
 * Example:
 * Input: ["FileSystem","ls","mkdir","addContentToFile","ls","readContentFromFile"]
 *        [[],["/"],["/a/b/c"],["/a/b/c/d","hello"],["/"],["/a/b/c/d"]]
 *
 * Output: [null,[],null,null,["a"],"hello"]
 *
 * Explanation:
 *            Operation                      Output        Explanation
 *  FileSystem fs = new FileSystem()          null         The constructor returns nothing
 *  fs.ls(/)                                   []          Initially, directory "/" has nothing, so return empty list
 *  fs.mkdir("/a/b/c")                        null         Create directory a in directory /. Then create dir b in dir a. Finally
 *                                                         create dir c in dir b.
 *  fs.addContentToFile("/a/b/c/d", "hello")  null         Create a file named d with content "hello" in directory /a/b/c
 *  fs.ls(/)                                   "a"         Only directory "a" in directory "/".
 *  fs.readContentFromFile("/a/b/c/d")       "hello"       Output the file content
 *
 * Note:
 * You can assume all file or directory paths are absolute paths which begin with / and do not end with / except that the path is just "/".
 * You can assume that all operations will be passed valid parameters and users will not attempt to retrieve file content or list a directory or file that does not exist.
 * You can assume that all directory names and file names only contain lower-case letters, and same names won't exist in the same directory.

 * Created by WinnieZhao on 2017/5/23.
 */
public class DesignInMemoryFileSystem {
    /**
     * Your FileSystem object will be instantiated and called as such:
     * FileSystem obj = new FileSystem();
     * List<String> param_1 = obj.ls(path);
     * obj.mkdir(path);
     * obj.addContentToFile(filePath,content);
     * String param_4 = obj.readContentFromFile(filePath);
     */

     class Node {
        String name;
        boolean isFile;
        StringBuilder content;
        Map<String, Node> children;

        Node(String name) {
            this.content = new StringBuilder();
            this.name = name;
            this.children = new TreeMap<>();
        }
    }

    Node root;
    public DesignInMemoryFileSystem() {
        root = new Node("/");
    }

    private Node traverse(String filePath) {
        String[] arr = filePath.split("/");
        Node cur = root;

        // start from index 1 as 0th element is ""
        for(int i = 1; i < arr.length; i++) {
            if(!cur.children.containsKey(arr[i])) {
                Node nd = new Node(arr[i]);
                cur.children.put(arr[i], nd);
            }

            cur = cur.children.get(arr[i]);
        }

        return cur;
    }

    public List<String> ls(String path) {
        Node node = this.traverse(path);

        List<String> result = new ArrayList<>();
        if (node.isFile) {
            result.add(node.name);
        }
        else {
            for (String name : node.children.keySet()) {
                result.add(name);
            }
        }
        return result;

    }

    public void mkdir(String path) {
        this.traverse(path);
    }

    public void addContentToFile(String filePath, String content) {
        Node node = this.traverse(filePath);
        node.isFile = true;
        node.content.append(content);
    }

    public String readContentFromFile(String filePath) {
        Node node = this.traverse(filePath);
        return node.content.toString();
    }
}
