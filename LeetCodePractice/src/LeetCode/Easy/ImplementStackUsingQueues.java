package LeetCode.Easy;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Implement the following operations of a stack using queues.
 * 
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * empty() -- Return whether the stack is empty.
 * 
 * Notes:
 * You must use only standard operations of a queue -- which means only push to back, peek/pop from front, size, and is empty operations are valid.
 * Depending on your language, queue may not be supported natively. You may simulate a queue by using a list or deque (double-ended queue), 
 * as long as you use only standard operations of a queue.
 * You may assume that all operations are valid (for example, no pop or top operations will be called on an empty 

 * @author ASUS-PC
 *
 */
public class ImplementStackUsingQueues {

    LinkedList<Integer> queue = new LinkedList<>();
	
	// Push element x onto stack.
    // Time Complexity : O(n)
	public void push(int x) {
        queue.add(x);

        int size = queue.size();
        while (size > 1) {
            queue.add(queue.remove());
            size--;
        }
    }

    // Removes the element on top of the stack.
    // Time Complexity : O(1)
    public int pop() {
        return queue.remove();
    }

    // Get the top element.
    // Time Complexity : O(1)
    public int top() {
        return queue.peek();
    }

    // Return whether the stack is empty.
    // Time Complexity : O(1)
    public boolean empty() {
        return queue.isEmpty();
    }
}
