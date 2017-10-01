package LeetCode.Interview.Amazon.LevelTwo;

import java.util.*;

/**
 * For similar problems like:
 * 1. Item association
 *    input
 *    [itemA, itemB], 表示物品A 和物品B 相互关联。
 *    [itemB, itemC], 表示物品B 和物品C 相互关联。
 *    如果物品相互关联，就组成一个组。最后要求找出物品最多的那个组。
 *
 * 2. Find largest Friends Circle
 *    给出一串Pair, 每个ｐａｉｒ说明两个人互为朋友，[A,B] 说明Ａ和Ｂ是朋友，[Ｃ,B] 说明Ｃ和Ｂ是朋友，｛Ｄ，Ｅ｝说明Ｅ和Ｄ是朋友。
 *    找出人数最大的朋友圈，如果两个朋友圈人数相等，返回有着字典顺序最小朋友的那个圈。
 * Created by WinnieZhao on 9/28/2017.
 */
public class BigUnion {

    public static void main(String[] args){
        String[][] lists = {{"1","5"},
                            {"1","6"},
                            {"2","3"},
                            {"2","7"},
                            {"3","7"},
                            {"4","2"},
                            {"8","10"},
                            {"8","11"},
                            {"9","8"}};

        System.out.println(findLargestUnion(lists));
    }

    public static Set<String> findLargestUnion(String[][] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        Map<String, SortedSet<String>> relations = new HashMap<>();

        for (String[] list : lists) {
            SortedSet<String> set = relations.getOrDefault(list[0], new TreeSet<>());
            set.add(list[1]);

            relations.put(list[0], set);

            set = relations.getOrDefault(list[1], new TreeSet<>());
            set.add(list[0]);

            relations.put(list[1], set);
        }

        PriorityQueue<SortedSet<String>> pq = new PriorityQueue<>((set1, set2) -> {
            if (set2.size() == set1.size()) {
                //return Integer.valueOf(set1.first()).compareTo(Integer.valueOf(set2.first()));
                return set1.first().compareTo((set2.first()));
            }
            return set2.size() - set1.size();
        });

        for (String cur : relations.keySet()) {
            boolean existed = false;
            for (Set<String> s : pq) {
                if (s.contains(cur)) {
                    existed = true;
                    break;
                }
            }
            if (existed) {
                continue;
            }

            SortedSet<String> union = new TreeSet<>();
            Queue<String> queue = new LinkedList<>();
            queue.offer(cur);
            while (!queue.isEmpty()) {
                String element = queue.poll();
                union.add(element);

                for (String next : relations.get(element)) {
                    if (!union.contains(next) && !queue.contains(next)) {
                        queue.offer(next);
                    }
                }
            }

            pq.add(union);
        }

        return pq.poll();
    }
}
