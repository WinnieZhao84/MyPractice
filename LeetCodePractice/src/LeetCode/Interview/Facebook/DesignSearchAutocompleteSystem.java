package LeetCode.Interview.Facebook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 642
 *
 * Created by WinnieZhao on 2/4/2018.
 */
public class DesignSearchAutocompleteSystem {

    /**
     * Your AutocompleteSystem object will be instantiated and called as such:
     * AutocompleteSystem obj = new AutocompleteSystem(sentences, times);
     * List<String> param_1 = obj.input(c);
     */

    class Node {
        Node(String st, int t) {
            sentence = st;
            times = t;
        }
        String sentence;
        int times;
    }

    class Trie {
        int times;
        Trie[] branches = new Trie[27];
    }

    private int int_(char c) {
        return c == ' ' ? 26 : c - 'a';
    }

    private void insert(Trie t, String s, int times) {
        for (int i = 0; i < s.length(); i++) {
            if (t.branches[int_(s.charAt(i))] == null) {
                t.branches[int_(s.charAt(i))] = new Trie();
            }
            t = t.branches[int_(s.charAt(i))];
        }
        t.times += times;
    }

    private List<Node> lookup(Trie t, String s) {
        List<Node> list = new ArrayList< >();
        for (int i = 0; i < s.length(); i++) {
            if (t.branches[int_(s.charAt(i))] == null) {
                return new ArrayList<>();
            }
            t = t.branches[int_(s.charAt(i))];
        }
        traverse(s, t, list);
        return list;
    }

    private void traverse(String s, Trie t, List <Node> list) {
        if (t.times > 0) {
            list.add(new Node(s, t.times));
        }
        for (char i = 'a'; i <= 'z'; i++) {
            if (t.branches[i - 'a'] != null) {
                traverse(s + i, t.branches[i - 'a'], list);
            }
        }
        if (t.branches[26] != null) {
            traverse(s + ' ', t.branches[26], list);
        }
    }

    Trie root;
    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        root = new Trie();
        for (int i = 0; i < sentences.length; i++) {
            insert(root, sentences[i], times[i]);
        }
    }

    String cur_sent = "";
    public List <String> input(char c) {
        List <String> res = new ArrayList <> ();
        if (c == '#') {
            insert(root, cur_sent, 1);
            cur_sent = "";
        }
        else {
            cur_sent += c;
            List <Node> list = lookup(root, cur_sent);
            Collections.sort(list, (a, b) -> a.times == b.times ? a.sentence.compareTo(b.sentence) : b.times - a.times);
            for (int i = 0; i < Math.min(3, list.size()); i++) {
                res.add(list.get(i).sentence);
            }
        }
        return res;
    }
}
