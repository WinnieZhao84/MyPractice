package LeetCode.Interview.Airbnb;

import java.util.*;

/**
 * 269
 *
 * There is a new alien language which uses the latin alphabet. However, the order among letters are unknown to you.
 * You receive a list of words from the dictionary, where words are sorted lexicographically by the rules of this new language.
 * Derive the order of letters in this language.
 *
 * For example, Given the following words in dictionary,
 * [ "wrt",
 *   "wrf",
 *   "er",
 *   "ett",
 *   "rftt"]
 *
 * The correct order is: "wertf".
 *
 * Note: You may assume all letters are in lowercase. If the order is invalid, return an empty string.
 * There may be multiple valid order of letters, return any one of them is fine.

 * Created by WinnieZhao on 2017/7/12.
 */
public class AlienDictionary {

    /**
     * Topological sorting problem
     *
     * 首先简单介绍一下拓扑排序，这是一个能够找出有向无环图顺序的一个方法
     *
     * 假设我们有3条边：A->C, B->C, C->D，先将每个节点的计数器初始化为0。然后我们对遍历边时，每遇到一个边，把目的节点的计数器都加1。
     * 然后，我们再遍历一遍，找出所有计数器值还是0的节点，这些节点就是有向无环图的“根”。然后我们从根开始广度优先搜索。
     * 具体来说，搜索到某个节点时，将该节点加入结果中，然后所有被该节点指向的节点的计数器减1，在减1之后，如果某个被指向节点的计数器变成0了，
     * 那这个被指向的节点就是该节点下轮搜索的子节点。在实现的角度来看，我们可以用一个队列，这样每次从队列头拿出来一个加入结果中，
     * 同时把被这个节点指向的节点中计数器值减到0的节点也都加入队列尾中。
     * 需要注意的是，如果图是有环的，则计数器会产生断层，即某个节点的计数器永远无法清零（有环意味着有的节点被多加了1，然而遍历的时候一次只减一个1，
     * 所以导致无法归零），这样该节点也无法加入到结果中。所以我们只要判断这个结果的节点数和实际图中节点数相等，就代表无环，不相等，则代表有环。

     *
     */
    public String alienOrder(String[] words) {
        if(words == null || words.length == 0) {
            return "";
        }

        Map<Character, Set<Character>> graph = new HashMap<>();
        Set<Character> set = new HashSet<>();

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                set.add(word.charAt(i));
            }
        }

        int[] inDegree = new int[26];

        for (int k = 1; k < words.length; k++) {
            String preStr = words[k - 1];
            String curStr = words[k];

            for (int i = 0; i < Math.min(preStr.length(), curStr.length()); i++) {
                char preChar = preStr.charAt(i);
                char curChar = curStr.charAt(i);

                if (preChar != curChar) {
                    if (!graph.containsKey(preChar)) {
                        graph.put(preChar, new HashSet<>());
                    }
                    if (!graph.get(preChar).contains(curChar)) {
                        inDegree[curChar - 'a']++;
                    }
                    graph.get(preChar).add(curChar);
                    break;
                }
            }
        }

        Queue<Character> queue = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++) {
            if (inDegree[i] == 0) {
                char c = (char)('a' + i);
                if (set.contains(c)) {
                    queue.offer(c);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            char c = queue.poll();
            sb.append(c);

            if (graph.containsKey(c)) {
                for (char l : graph.get(c)) {
                    inDegree[l - 'a']--;

                    if (inDegree[l - 'a'] == 0) {
                        queue.offer(l);
                    }
                }
            }
        }
        return sb.length() != set.size() ? "" : sb.toString();
    }
}
