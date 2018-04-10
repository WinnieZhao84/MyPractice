package LeetCode.Interview.LinkedIn.Onsite;

import java.util.HashSet;
import java.util.Set;

/**
 * 双向链表，但是每一个点还可以有up，down pointer， 已知一个链表里没有环，要求把这个链表变成标准双向链表，要求O(n)time, O(1)space.
 * Follow up: 有环，去除环，返回无环的链表
 * https://www.jiuzhang.com/qa/7325/
 */
public class FlattenDoublyLinkedList {

    class Node {
        int val;
        Node next;
        Node prev;
        Node up;
        Node down;
        public Node(int val){
            this.val = val;
            this.next = null;
            this.prev = null;
            this.up = null;
            this.down = null;
        }
    }

    public void flatten(Node head){
        if (head == null){
            return;
        }
        Node tail = head;
        while (tail.next != null){
            tail = tail.next;
        }
        Node curt = head;
        while (curt != null){
            if (curt.up != null){
                tail.next = curt.up;
                curt.up.prev = tail;
                while (tail.next != null){
                    tail = tail.next;
                }
                curt.up = null;
            }
            if (curt.down != null){
                tail.next = curt.down;
                curt.down.prev = tail;
                while (tail.next != null){
                    tail = tail.next;
                }
                curt.down = null;
            }
            curt = curt.next;
        }
    }

    public void flatten_withLoop(Node head){
        if (head == null){
            return;
        }
        Node tail = head;

        Set<Node> set = new HashSet<>();
        set.add(tail);
        while (tail.next != null){
            tail = tail.next;
        }

        Node curt = head;
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
