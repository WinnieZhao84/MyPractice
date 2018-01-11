package LeetCode.Medium;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

import LeetCode.Helper.NestedInteger;

/**
 * Given a nested list of integers, implement an iterator to flatten it.
 * 
 * Each element is either an integer, or a list -- whose elements may also be integers or other lists.
 * 
 * Example 1:
 * Given the list [[1,1],2,[1,1]],
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
 * 
 * Example 2:
 * Given the list [1,[4,[6]]],
 * By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 * 
 * @author WinnieZhao
 *
 */
public class FlattenNestedListIterator implements Iterator<Integer> {
    /**
     * Your NestedIterator object will be instantiated and called as such:
     * NestedIterator i = new NestedIterator(nestedList);
     * while (i.hasNext()) v[f()] = i.next();
     */
    Stack<NestedInteger> stack = new Stack<>();
    
    public FlattenNestedListIterator(List<NestedInteger> nestedList) {
        for(int i = nestedList.size() - 1; i >= 0; i--) {
            stack.push(nestedList.get(i));
        }
    }

    @Override
    public Integer next() {
        return stack.pop().getInteger();
    }

    @Override
    public boolean hasNext() {
        while(!stack.isEmpty()) {
            NestedInteger curr = stack.peek();
            if(curr.isInteger()) {
                return true;
            }
            stack.pop();
            for(int i = curr.getList().size() - 1; i >= 0; i--) {
                stack.push(curr.getList().get(i));
            }
        }
        return false;
    }

    class solution {
        public class NestedIterator implements Iterator<Integer> {

            List<NestedInteger> nestedList = null;
            LinkedList<Integer> queue = new LinkedList<>();

            public NestedIterator(List<NestedInteger> nestedList) {
                this.nestedList = nestedList;

                for (NestedInteger ni : nestedList) {
                    this.addNestedInteger(ni);
                }
            }

            private void addNestedInteger(NestedInteger ni) {
                if (ni.isInteger()) {
                    queue.add(ni.getInteger());
                }
                else {
                    List<NestedInteger> nis = ni.getList();
                    if (nis != null) {
                        for (NestedInteger n : nis) {
                            this.addNestedInteger(n);
                        }
                    }
                }
            }

            @Override
            public Integer next() {
                return queue.poll();
            }

            @Override
            public boolean hasNext() {
                return !queue.isEmpty();
            }
        }
    }
}
