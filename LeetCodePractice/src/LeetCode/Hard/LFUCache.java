package LeetCode.Hard;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
 * put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its capacity,
 * it should invalidate the least frequently used item before inserting a new item.
 * For the purpose of this problem, when there is a tie (i.e., two or more keys that have the same frequency),
 * the least recently used key would be evicted.
 *
 * Follow up: Could you do both operations in O(1) time complexity?
 *
 * Example: LFUCache cache = new LFUCache(2); 2 -- Capacity
 *          cache.put(1, 1);
 *          cache.put(2, 2);
 *          cache.get(1);       // returns 1
 *          cache.put(3, 3);    // evicts key 2
 *          cache.get(2);       // returns -1 (not found)
 *          cache.get(3);       // returns 3.
 *          cache.put(4, 4);    // evicts key 1.
 *          cache.get(1);       // returns -1 (not found)
 *          cache.get(3);       // returns 3
 *          cache.get(4);       // returns 4
 *
 * Created by WinnieZhao on 2017/5/2.
 *
 */
public class LFUCache {

    private Map<Integer, Integer> vals;
    private Map<Integer, Integer> counts;
    private Map<Integer, LinkedHashSet<Integer>> lists;
    private int min = -1;
    private final int cap;

    public LFUCache(int capacity) {
        this.cap = capacity;
        vals = new HashMap<>();
        counts = new HashMap<>();
        lists = new HashMap<>();
        lists.put(1, new LinkedHashSet<>());
    }

    public int get(int key) {
        if (!vals.containsKey(key)) {
            return -1;
        }

        int count = counts.get(key);
        counts.put(key, count+1);

        lists.get(count).remove(key);
        if (count == min && lists.get(count).size() == 0) {
            min = count + 1;
        }

        // lists.compute(count+1, (k, v) -> k == null? new LinkedHashSet<>() : lists.get(count+1)).add(key);
        if (!lists.containsKey(count+1)) {
            lists.put(count+1, new LinkedHashSet<>());
        }
        lists.get(count+1).add(key);

        return vals.get(key);

    }

    public void put(int key, int value) {
        if(cap<=0) return;
        if (vals.containsKey(key)) {
            vals.put(key, value);
            this.get(key);
            return;
        }

        if (vals.size() >= this.cap) {
            int removedKey = lists.get(min).iterator().next();
            vals.remove(removedKey);
            lists.get(min).remove(removedKey);
        }

        vals.put(key, value);
        min = 1;
        counts.put(key, 1);
        lists.get(1).add(key);
    }

    public static void main(String[] args) {
        LFUCache cache = new LFUCache(2);

        cache.get(3);
        cache.put(1, 1);
        cache.get(1);
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
