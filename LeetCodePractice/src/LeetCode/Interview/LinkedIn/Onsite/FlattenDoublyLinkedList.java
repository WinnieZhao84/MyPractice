package LeetCode.Interview.LinkedIn.Onsite;

import java.util.HashSet;
import java.util.Set;

/**
 * 双向链表，但是每一个点还可以有up，down pointer， 已知一个链表里没有环，要求把这个链表变成标准双向链表，要求O(n)time, O(1)space.
 * Follow up: 有环，去除环，返回无环的链表
 * https://www.jiuzhang.com/qa/7325/
 */
public class FlattenDoublyLinkedList {

    // every time we meet a node check if it has up/down node
    // if it has, connect it to the tail of the list
    // and keep traversing
    public MetaNode flatten(MetaNode input) {
        if (input == null) {
            return null;
        }
        MetaNode head = getHead(input);
        MetaNode tail = getTail(input);
        MetaNode mover = head;

        while (mover != null) {

            if (mover.up != null) {
                MetaNode up = mover.up;
                MetaNode subHead = getHead(up);
                tail.next = subHead;
                subHead.prev = tail;
                mover.up = null;
                tail = getTail(tail);
            }
            if (mover.down != null) {
                MetaNode down = mover.down;
                MetaNode subHead = getHead(down);
                tail.next = subHead;
                subHead.prev = tail;
                mover.down = null;
                tail = getTail(tail);
            }
            mover = mover.next;
        }
        return head;
    }

    private MetaNode getHead(MetaNode node) {
        while (node.prev != null) {
            node = node.prev;
        }
        return node;
    }

    private MetaNode getTail(MetaNode node) {
        while (node.next != null) {
            node = node.next;
        }
        return node;
    }

    class MetaNode {
        MetaNode up = null;
        MetaNode down = null;
        MetaNode prev = null;
        MetaNode next = null;
        int val;
        public MetaNode(int val) {
            this.val = val;
        }
    }

    public void flatten_withLoop(MetaNode head){
        if (head == null){
            return;
        }
        MetaNode tail = head;

        Set<MetaNode> set = new HashSet<>();
        set.add(tail);
        while (tail.next != null){
            tail = tail.next;
        }

        MetaNode curt = head;
        while (curt != null){
            set.add(curt);
            if (curt.up != null){
                if (!set.contains(curt.up)){
                    tail.next = curt.up;
                    curt.up.prev = tail;
                    while (tail.next != null){
                        tail = tail.next;
                    }
                }
                curt.up = null;
            }

            if (curt.down != null){
                if (!set.contains(curt.down)){
                    tail.next = curt.down;
                    curt.down.prev = tail;
                    while (tail.next != null){
                        tail = tail.next;
                    }
                }
                curt.down = null;
            }
            curt = curt.next;
        }
    }
}
