package LeetCode.Medium;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 249
 *
 * Given a string, we can "shift" each of its letter to its successive letter,
 * for example: "abc" -> "bcd". We can keep "shifting" which forms the sequence: "abc" -> "bcd" -> ... -> "xyz"
 * Given a list of strings which contains only lowercase alphabets, group all strings that belong to the same shifting sequence.
 *
 * For example, given: ["abc", "bcd", "acef", "xyz", "az", "ba", "a", "z"],
 * Return:
 * [
 *  ["abc","bcd","xyz"],
 *  ["az","ba"],
 *  ["acef"],
 *  ["a","z"]
 * ]
 *
 * Note: For the return value, each inner list's elements must follow the lexicographic order.
 * things to consider:
 * 1 does each group allow duplicates, such as ["a", "a"].
 * 2 how to calculate unique hash key.
 *
 * For Java to do a mod operation, you need to do (26 + a)%26, if a == -25, then without 26+, you get a%26 == -25.
 * This is not the case in Python.

 * Created by WinnieZhao on 4/10/2017.
 */
public class GroupShiftedStrings {

    /**
     * ["eqdf", "qcpr"]
     * (('q' - 'e') + 26) % 26 = 12, (('d' - 'q') + 26) % 26 = 13, (('f' - 'd') + 26) % 26 = 2
     * (('c' - 'q') + 26) % 26 = 12, (('p' - 'c') + 26) % 26 = 13, (('r' - 'p') + 26) % 26 = 2
     *
     * So "eqdf" and "qcpr" are shifted strings。
     *
     */
    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> map = new HashMap<>();

        for(String s : strings){
            String hash = getHash(s);
            if(map.containsKey(hash)){
                map.get(hash).add(s);
            }else{
                List<String> l = new ArrayList<>();
                l.add(s);
                map.put(hash, l);
            }
        }

        return new ArrayList<List<String>>(map.values());
    }

    private String getHash(String s){
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< s.length(); i++){
            sb.append((s.charAt(i) - s.charAt(0) + 26) % 26);
            sb.append('.');// to make sure there is no overlap.
        }

        return sb.toString();
    }
}
