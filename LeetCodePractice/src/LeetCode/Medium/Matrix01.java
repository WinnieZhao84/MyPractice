package LeetCode.Medium;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
 *
 * The distance between two adjacent cells is 1
 *
 * Example 1:
 * Input:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 *
 * Output:
 * 0 0 0
 * 0 1 0
 * 0 0 0
 *
 * Example 2:
 * Input:
 * 0 0 0
 * 0 1 0
 * 1 1 1
 *
 * Output:
 * 0 0 0
 * 0 1 0
 * 1 2 1
 *
 * Note:
 * The number of elements of the given matrix will not exceed 10,000.
 * There are at least one 0 in the given matrix.
 * The cells are adjacent in only four directions: up, down, left and right.
 *
 * Created by WinnieZhao on 2017/3/21.
 */
public class Matrix01 {

    int row;
    int column;
    public List<List<Integer>> updateMatrix(List<List<Integer>> matrix) {
        if (matrix == null || matrix.size() == 0) {
            return matrix;
        }

        row = matrix.size();
        column = matrix.get(0).size();

        for (List<Integer> list : matrix) {
            for (int j = 0; j < column; j++) {
                list.set(j, list.get(j) * 10000);
            }
        }

        // From left and top
        for (int rowIndex=0; rowIndex<row; rowIndex++) {
            for (int columnIndex=0; columnIndex<column; columnIndex++) {
                this.update(matrix, rowIndex, columnIndex,  rowIndex-1, columnIndex);
                this.update(matrix, rowIndex, columnIndex, rowIndex, columnIndex-1);
            }

        }

        // From right and bottom
        for (int rowIndex=row-1; rowIndex>=0; rowIndex--) {
            for (int columnIndex=column-1; columnIndex>=0; columnIndex--) {
                this.update(matrix, rowIndex, columnIndex,  rowIndex+1, columnIndex);
                this.update(matrix, rowIndex, columnIndex, rowIndex, columnIndex+1);
            }
        }

        return matrix;
    }

    private void update(List<List<Integer>> matrix, int rowIndex, int columnIndex, int i, int j) {
        if (i < 0 || i >= row || j < 0 || j >= column) {
            return;
        }

        int min = Math.min(matrix.get(i).get(j)+1, matrix.get(rowIndex).get(columnIndex));
        matrix.get(rowIndex).set(columnIndex, min);
    }

    public int[][] updateMatrix_BFS(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        
        int[][] distance = new int[m][n]; // Keep a distance matrix for DP
        boolean[][] visited = new boolean[m][n];
        
        Queue<int[]> queue = new LinkedList<>();
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (matrix[i][j] == 0) {
                    queue.add(new int[] {i, j});
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
        int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            visited[cur[0]][cur[1]] = true;
            
            for (int[] dir : dirs) {
                int x = cur[0] + dir[0];
                int y = cur[1] + dir[1];
                
                if (x <0 || y<0 || x>=m || y >= n || visited[x][y]) {
                    continue;
                }
                
                if (distance[x][y] > distance[cur[0]][cur[1]] + 1) {
                    distance[x][y] = distance[cur[0]][cur[1]] + 1;
                    queue.offer(new int[] {x, y});
                }
            }
        }
        return distance;
    }
    
    public static void main(String[] args) {
        List<Integer> row1 = new ArrayList<>();
        row1.add(1);
        row1.add(1);
        row1.add(0);

        List<Integer> row2 = new ArrayList<>();
        row2.add(1);
        row2.add(0);
        row2.add(0);

        List<Integer> row3 = new ArrayList<>();
        row3.add(1);
        row3.add(1);
        row3.add(1);

        List<List<Integer>> matrix = new ArrayList<List<Integer>>();
        matrix.add(row1);
        matrix.add(row2);
        matrix.add(row3);

        Matrix01 solution = new Matrix01();
        System.out.println(solution.updateMatrix(matrix));

        row1.clear();
        row2.clear();
        matrix.clear();

        row1.add(0);
        row2.add(1);

        matrix.add(row1);
        matrix.add(row2);
        System.out.println(solution.updateMatrix(matrix));
    }
}
