package LeetCode.Medium;

import java.util.HashMap;
import java.util.Map;

import LeetCode.Helper.RandomListNode;

/**
 * A linked list is given such that each node contains an additional random pointer which could point to
 * any node in the list or null.
 * 
 * Return a deep copy of the list.

 * @author WinnieZhao
 *
 */
public class CopyListWithRandomPointer {

    public RandomListNode copyRandomList(RandomListNode head) {
        
        if (head == null) return null;
        
        Map<RandomListNode, RandomListNode> map = new HashMap<>();
        
        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }
        
        node = head;
        while (node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);
            
            node = node.next;
        }
        
        return map.get(head);
    }
    
    /**
     * O(1) space
     * @param head
     * @return
     */
    public RandomListNode copyRandomList_constantSpace(RandomListNode head) {
        RandomListNode iter = head, next;

        // First round: make copy of each node,
        // and link them together side-by-side in a single list.
        while (iter != null) {
            next = iter.next;

            RandomListNode copy = new RandomListNode(iter.label);
            iter.next = copy;
            copy.next = next;

            iter = next;
        }

        // Second round: assign random pointers for the copy nodes.
        iter = head;
        while (iter != null) {
            if (iter.random != null) {
                iter.next.random = iter.random.next;
            }
            iter = iter.next.next;
        }

        // Third round: restore the original list, and extract the copy list.
        iter = head;
        RandomListNode pseudoHead = new RandomListNode(0);
        RandomListNode copy, copyIter = pseudoHead;

        while (iter != null) {
            next = iter.next.next;

            // extract the copy
            copy = iter.next;
            copyIter.next = copy;
            copyIter = copy;

            // restore the original list
            iter.next = next;

            iter = next;
        }

        return pseudoHead.next;
    }
}
