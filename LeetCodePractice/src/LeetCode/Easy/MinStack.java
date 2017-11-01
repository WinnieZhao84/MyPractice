package LeetCode.Easy;

import java.util.Stack;


/**
 * Design a stack that supports push, pop, top, and retrieving the minimum element in constant time.
 * 
 * 
 * push(x) -- Push element x onto stack.
 * pop() -- Removes the element on top of the stack.
 * top() -- Get the top element.
 * getMin() -- Retrieve the minimum element in the stack.

 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(x);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

public class MinStack {
    /** initialize your data structure here. */
	
	Stack<Integer> stack = null;
	int min = Integer.MAX_VALUE;
	
    public MinStack() {
    	stack = new Stack<>();
    }
    
    public void push(int x) {
        // only push the old minimum value when the current
        // minimum value changes after pushing the new value x
        if (x <= min) {
        	stack.push(min); 
        	min=x;
        }
        stack.push(x);
    }
    
    public void pop() {
        // if pop operation could result in the changing of the current minimum value,
        // pop twice and change the current minimum value to the last minimum value.
        if (stack.pop() <= min) {
        	min = stack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        return min;
    }
}
