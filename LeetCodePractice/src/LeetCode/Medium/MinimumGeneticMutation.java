package LeetCode.Medium;

import java.util.*;

/**
 * A gene string can be represented by an 8-character long string, with choices from "A", "C", "G", "T".
 *
 * Suppose we need to investigate about a mutation (mutation from "start" to "end"),
 * where ONE mutation is defined as ONE single character changed in the gene string.
 *
 * For example, "AACCGGTT" -> "AACCGGTA" is 1 mutation.
 *
 * Also, there is a given gene "bank", which records all the valid gene mutations.
 * A gene must be in the bank to make it a valid gene string.
 *
 * Now, given 3 things - start, end, bank, your task is to determine what is the minimum number of mutations
 * needed to mutate from "start" to "end". If there is no such a mutation, return -1.
 *
 * Note:
 * Starting point is assumed to be valid, so it might not be included in the bank.
 * If multiple mutations are needed, all mutations during in the sequence must be valid.
 * You may assume start and end string is not the same.
 *
 * Example 1:
 * start: "AACCGGTT"
 * end:   "AACCGGTA"
 * bank: ["AACCGGTA"]
 *
 * return: 1
 *
 * Example 2:
 * start: "AACCGGTT"
 * end:   "AAACGGTA"
 * bank: ["AACCGGTA", "AACCGCTA", "AAACGGTA"]
 *
 * return: 2
 *
 * Example 3:
 * start: "AAAAACCC"
 * end:   "AACCCCCC"
 * bank: ["AAAACCCC", "AAACCCCC", "AACCCCCC"]
 *
 * return: 3

 * Created by WinnieZhao on 12/23/2017.
 */
public class MinimumGeneticMutation {

    public int minMutation(String start, String end, String[] bank) {
        if (bank == null || bank.length == 0) {
            return -1;
        }

        List<String> banks = Arrays.asList(bank);
        if (!banks.contains(end)) {
            return -1;
        }

        Queue<String> queue = new LinkedList<>();
        queue.add(start);

        Set<String> visited = new HashSet<>();
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i=0; i<size; i++) {
                String cur = queue.poll();

                if (cur.equals(end)) {
                    return count;
                }
                for (String gene : banks) {
                    if (!visited.contains(gene) && this.isValid(cur, gene)) {
                        queue.offer(gene);
                        visited.add(gene);
                    }
                }
            }
            count++;
        }

        return -1;
    }

    private boolean isValid(String cur, String target) {
        if (cur.length() != target.length()) {
            return false;
        }

        int length = cur.length();
        int i=0;
        int diff = 0;
        while (i<length) {
            if (diff > 1) {
                return false;
            }
            if (cur.charAt(i) != target.charAt(i)) {
                diff++;
            }
            i++;
        }
        return diff == 1;
    }
}
