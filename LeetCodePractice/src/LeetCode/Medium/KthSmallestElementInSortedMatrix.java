package LeetCode.Medium;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * Given a n x n matrix where each of the rows and columns are sorted in ascending order, 
 * find the kth smallest element in the matrix.
 * 
 * Note that it is the kth smallest element in the sorted order, not the kth distinct element.
 * 
 * Example:
 * 
 * matrix = [
 *     [ 1,  5,  9],
 *     [10, 11, 13],
 *     [12, 13, 15]
 * ],
 * 
 * k = 8,
 * 
 * return 13
 * 
 * Note: 
 * 
 * You may assume k is always valid, 1 <= k <= n^2.
 * 
 * @author WinnieZhao
 *
 */
public class KthSmallestElementInSortedMatrix {

    // Complexity n ^ 2
    public int kthSmallest(int[][] matrix, int k) {
        List<Integer> index = new ArrayList<Integer>();
        int length = matrix.length;
        
        for (int i=0; i<length; i++) {
            for (int j=0; j<length; j++) {
                index.add(matrix[i][j]);
            }
        }
        Collections.sort(index);
        
        return index.get(k-1);
    }
    
    private class Value implements Comparable<Value> {
        int val;
        int x;
        int y;
        
        public Value(int val, int x, int y) {
            this.val = val;
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Value that) {
            return (this.val - that.val);
        }
    }
    
    // O(k*log(k) time complexity
    public int kthSmallest_better(int[][] matrix, int k) {
        PriorityQueue<Value> queue = new PriorityQueue<Value>();
        
        queue.offer(new Value(matrix[0][0], 0, 0));
        for (int i=0; i<k-1; i++) {
            Value head = queue.poll();
            
            // push the element on the right to the queue
            if (head.x < matrix.length-1) {
                queue.offer(new Value(matrix[head.x+1][head.y], head.x + 1, head.y));
            }
            
            // avoid duplicates, only push the bottom element from matrix for the 1st row element
            if (head.x == 0 && head.y < matrix.length-1) {
                queue.offer(new Value(matrix[head.x][head.y+1], head.x, head.y + 1));
            }
        }
        
        return queue.peek().val;
    }
    

    
    public static void main (String[] args) {
        KthSmallestElementInSortedMatrix solution = new KthSmallestElementInSortedMatrix();
        
        int[][] matrix =  {{ 1, 6, 9}, {3, 7, 10}, {4, 8, 2}};
        
        System.out.println(solution.kthSmallest_better(matrix, 3));
        
        int[][] matrix1 =  {{-5}};
        System.out.println(solution.kthSmallest_better(matrix1, 1));
        
        int[][] matrix2 =  {{ 1, 2, 5, 9}, {10, 11, 11, 13}, {12, 12, 13, 15}, {13,13, 14, 16}};
        System.out.println(solution.kthSmallest_better(matrix2, 8));
    }
}
