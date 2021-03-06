package LeetCode.Interview.Amazon.LevelTwo;

import java.util.*;

/**
 * You are asked to cut off trees in a forest for a golf event. The forest is represented as a non-negative 2D map,
 * in this map:
 *
 * 0 represents the obstacle can't be reached.
 * 1 represents the ground can be walked through.
 *
 * The place with number bigger than 1 represents a tree can be walked through,
 * and this positive number represents the tree's height.
 *
 * You are asked to cut off all the trees in this forest in the order of tree's height -
 * always cut off the tree with lowest height first. And after cutting, the original place
 * has the tree will become a grass (value 1).
 *
 * You will start from the point (0, 0) and you should output the minimum steps you need to
 * walk to cut off all the trees. If you can't cut off all the trees, output -1 in that situation.
 *
 * You are guaranteed that no two trees have the same height and there is at least one tree needs to be cut off.
 *
 * Example 1:
 * Input:
 * [ [1,2,3],
 *   [0,0,4],
 *   [7,6,5] ]
 * Output: 6
 *
 * Example 2:
 * Input:
 * [ [1,2,3],
 *   [0,0,0],
 *   [7,6,5] ]
 * Output: -1
 *
 * Example 3:
 * Input:
 * [ [2,3,4],
 *   [0,0,5],
 *   [8,7,6] ]
 * Output: 6
 * Explanation: You started from the point (0,0) and you can cut off the tree in (0,0) directly without walking.
 *
 * Hint: size of the given matrix will not exceed 50x50.

 * Created by WinnieZhao on 9/17/2017.
 */
public class CutOffTreesForGolfEvent {

    /**
     * Since we have to cut trees in order of their height, we first put trees (int[] {row, col, height})
     * into a priority queue and sort by height.
     * Poll each tree from the queue and use BFS to find out steps needed.
     * The worst case time complexity could be O(m^2 * n^2) (m = number of rows, n = number of columns)
     * since there are m * n trees and for each BFS worst case time complexity is O(m * n) too.
     */
    static int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};

    class Tree {
        int x;
        int y;
        int h;

        Tree(int x, int y, int h) {
            this.x = x;
            this.y = y;
            this.h = h;
        }
    }

    public int cutOffTree(List<List<Integer>> forest) {
        if (forest == null || forest.isEmpty()) {
            return -1;
        }

        int row = forest.size();
        int col = forest.get(0).size();

        PriorityQueue<Tree> pq = new PriorityQueue<>((t1, t2) -> t1.h - t2.h);
        for (int i=0; i<row; i++) {
            for (int j=0; j<col; j++) {
                if (forest.get(i).get(j) > 0) {
                    Tree tree = new Tree(i, j, forest.get(i).get(j));
                    pq.add(tree);
                }
            }
        }


        Tree start = new Tree(0,0,forest.get(0).get(0));

        int sum = 0;
        while(!pq.isEmpty()) {
            Tree target = pq.poll();

            int step = this.findMinStep(start, target, forest, row, col);

            if (step == -1) {
                return -1;
            }

            sum += step;
            start = target;
        }

        return sum;

    }

    private int findMinStep(Tree start, Tree target, List<List<Integer>> forest, int row, int col) {

        LinkedList<Tree> queue = new LinkedList();
        queue.add(start);
        boolean[][] visited = new boolean[row][col];

        int step = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i=0; i<size; i++) {
                Tree cur = queue.poll();

                if (cur.x == target.x && cur.y == target.y) {
                    return step;
                }

                for (int[] dir : dirs) {
                    int x = dir[0] + cur.x;
                    int y = dir[1] + cur.y;

                    if (x >= row || x< 0 || y>=col || y<0 || visited[x][y] || forest.get(x).get(y) == 0) {
                        continue;
                    }

                    visited[x][y] = true;
                    queue.offer(new Tree(x, y, forest.get(x).get(y)));
                }
            }
            step++;

        }

        return -1;
    }

    public static void main(String[] args) {
        CutOffTreesForGolfEvent solution = new CutOffTreesForGolfEvent();

        List<Integer> list1 = Arrays.asList(1,2,3);
        List<Integer> list2 = Arrays.asList(0,0,4);
        List<Integer> list3 = Arrays.asList(7,6,5);

        List<List<Integer>> forest = new ArrayList<>();
        forest.add(list1);
        forest.add(list2);
        forest.add(list3);

        System.out.println(solution.cutOffTree(forest));
    }
}
