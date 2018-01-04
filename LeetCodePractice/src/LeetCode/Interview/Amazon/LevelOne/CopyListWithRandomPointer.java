package LeetCode.Interview.Amazon.LevelOne;

import LeetCode.Helper.RandomListNode;

import java.util.HashMap;
import java.util.Map;

/**
 * Same as LeetCode
 *
 * Created by WinnieZhao on 9/30/2017.
 */
public class CopyListWithRandomPointer {

    public RandomListNode copyRandomList(RandomListNode head) {
        if (head == null) {
            return null;
        }

        Map<RandomListNode, RandomListNode> map = new HashMap<>();

        RandomListNode node = head;
        while (node != null) {
            map.put(node, new RandomListNode(node.label));
            node = node.next;
        }

        node = head;
        while(node != null) {
            map.get(node).next = map.get(node.next);
            map.get(node).random = map.get(node.random);

            node = node.next;
        }

        return map.get(head);
    }

    public class Solution {
        Map<Integer, RandomListNode> map = new HashMap<>();

        public RandomListNode copyRandomList(RandomListNode head) {
            if (head == null) {
                return null;
            }

            if (map.containsKey(head.label)) {
                return map.get(head.label);
            }

            RandomListNode clone = new RandomListNode(head.label);
            map.put(head.label, clone);

            clone.next = this.copyRandomList(head.next);
            clone.random = this.copyRandomList(head.random);

            return clone;
        }
    }
}
